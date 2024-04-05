package ru.gb.chat;

import ru.gb.chat.GUI.ChatClientGUI;
import ru.gb.chat.common.Message;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private final String name;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private ChatClientGUI gui;

    public Client(String userName, ChatClientGUI gui){
        name = userName;
        this.gui = gui;
    }

    public void connectToServer(InetAddress ip, int port) throws IOException {
        socket = new Socket(ip, port);
        try
        {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter.write(name);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public String getConnectInfo() {
        StringBuilder connectInfo = new StringBuilder();
        InetAddress inetAddress = socket.getInetAddress();
        connectInfo.append("IP address: ").append(inetAddress).append("\n");
        String remoteIp = inetAddress.getHostAddress();
        connectInfo.append("Remote IP: ").append(remoteIp).append("\n");
        connectInfo.append("LocalPort: ").append(socket.getLocalPort()).append("\n");
        return connectInfo.toString();
    }

    public boolean isClientConnected() {
        return socket.isConnected();
    }

    /**
     * Слушатель для входящих сообщений
     */
    public void listenForMessage(){
        new Thread(new Runnable() {
            String message;
            @Override
            public void run() {
                while (socket.isConnected()){
                    try {
                        message = bufferedReader.readLine();
                        System.out.println(message);
                        int length = message.split("<").length;
                        String name = message.split("<")[length - 1];
                        name = name.split(">")[0];
                        String content = message.split(">:")[length - 1];
                        Message message = new Message(name, content);
                        gui.appendToLog(message.getMessageText() + "\n");
                    }
                    catch (IOException e){
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    /**
     * Отправить сообщение
     */
    public void sendMessage(String message){
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            gui.appendToLog(message + "\n");
        } catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void setCredentials(String text, char[] password) {
    }
}
