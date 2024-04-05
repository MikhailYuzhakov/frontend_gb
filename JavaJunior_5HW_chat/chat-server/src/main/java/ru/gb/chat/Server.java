package ru.gb.chat;

import ru.gb.chat.GUI.ChatServerGUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private Thread thread;
    private ChatServerGUI gui;

    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket, ChatServerGUI gui) {
        this.serverSocket = serverSocket;
        this.gui = gui;
    }

    @Override
    public void run(){
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientManager clientManager = new ClientManager(socket, gui);
                thread = new Thread(clientManager);
                thread.start();
            }
        }
        catch (IOException e){
            closeSocket();
        }
    }

    public void closeSocket(){
        try {
            if (serverSocket != null) {
                serverSocket.close();
                thread.join();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
