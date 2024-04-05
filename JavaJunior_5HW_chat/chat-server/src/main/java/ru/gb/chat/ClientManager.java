package ru.gb.chat;

import ru.gb.chat.GUI.ChatServerGUI;
import ru.gb.chat.common.Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class ClientManager implements Runnable {

    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    private ChatServerGUI gui;

    public final static ArrayList<ClientManager> clients = new ArrayList<>();

    public ClientManager(Socket socket, ChatServerGUI gui) {
        this.socket = socket;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            clients.add(this);
            Message message = new Message("Server", name + " подключился к чату.\n");
            gui.appendToLog(message.getMessageText());
            broadcastMessage(message);
        }
        catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }


    }

    @Override
    public void run() {
        String messageFromClient;

        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                System.out.println("Message from client (string): " + messageFromClient);
                Message message = parseMessage(messageFromClient);
                broadcastMessage(message);
            }
            catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    private Message parseMessage(String content) {
        Message message;
        String text = content.split(": ")[1];
        String senderMessage = content.split("<")[1];
        senderMessage = senderMessage.split(">")[0];
        message = new Message(senderMessage, text);

        if (content.contains("@")) {
            String recieverMessage = content.split("@")[1];
            recieverMessage = recieverMessage.split(" ")[0];
            message.setRecieverName(recieverMessage);
        }
        return message;
    }

    private void broadcastMessage(Message message) {
        try {
            if (message.getRecieverName() != null) {
                for (ClientManager client: clients) {
                    if (Objects.equals(client.name, message.getRecieverName())) {
                        client.bufferedWriter.write(message.getMessageText());
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    }
                }
            } else {
                for (ClientManager client: clients) {
                        if (!client.name.equals(name)) {
                            client.bufferedWriter.write(message.getMessageText());
                            client.bufferedWriter.newLine();
                            client.bufferedWriter.flush();
                        }
                    }
                }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }

    }


    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        // Удаление клиента из коллекции
        removeClient();
        try {
            // Завершаем работу буфера на чтение данных
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            // Завершаем работу буфера для записи данных
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            // Закрытие соединения с клиентским сокетом
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeClient(){
        clients.remove(this);
        Message message = new Message("Server", name + " покинул чат.\n");
        gui.appendToLog(message.getMessageText());
        broadcastMessage(message);
    }
}
