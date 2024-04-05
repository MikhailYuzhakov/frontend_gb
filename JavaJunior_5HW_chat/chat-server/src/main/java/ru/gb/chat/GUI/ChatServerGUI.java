package ru.gb.chat.GUI;

import ru.gb.chat.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.rmi.UnknownHostException;

public class ChatServerGUI extends JFrame {
    private static final int POS_X = 200;
    private static final int POS_Y = 200;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    private JTextField portTextField, maxClientsTextField;
    private JPanel sendPanel, settingsPanel;
    private JButton btnStartSrv, btnStopSrv;
    private JTextArea logLabel;
    private ServerSocket serverSocket;
    private Server server;
    private JScrollPane scrollPane;

    public ChatServerGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setTitle("Сервер чата");
        setResizable(false);
        createPanels();
        setVisible(true);

        btnStartSrv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
        });

        btnStopSrv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopServer();
            }
        });
    }

    private void stopServer() {
        if (!serverSocket.isClosed()) {
            server.closeSocket();
            settingsPanel.setVisible(true);
        }
    }

    private void startServer() {
        try
        {
            int port = Integer.parseInt(portTextField.getText());
            InetAddress ip = InetAddress.getLocalHost();
            serverSocket = new ServerSocket(port);
            server = new Server(serverSocket, this);
            Thread thread = new Thread(server);
            appendToLog(serverSocket.getInetAddress().toString() + "\n");
            appendToLog(serverSocket.getLocalSocketAddress().toString() + "\n");
            appendToLog(String.valueOf(serverSocket.getLocalPort()) + "\n");
            appendToLog("Сервер запущен!");
            thread.start();
        }
        catch (UnknownHostException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void createSettingsPanel() {
        sendPanel = new JPanel(new GridLayout(1, 2));
        String START_BUTTON_LABEL = "Старт сервера";
        btnStartSrv = new JButton(START_BUTTON_LABEL);
        String STOP_BUTTON_LABEL = "Стоп сервера";
        btnStopSrv = new JButton(STOP_BUTTON_LABEL);
        settingsPanel = new JPanel(new GridLayout(1, 2));
        portTextField = new JTextField();
        portTextField.setText("1400");
        maxClientsTextField = new JTextField();
        settingsPanel.add(portTextField);
//        settingsPanel.add(maxClientsTextField);
    }

    public void appendToLog(String message) {
        logLabel.append(message);
    }

    private void createLogLabel() {
        logLabel = new JTextArea();
        Font font = new Font("Calibri", Font.BOLD, 13);
        logLabel.setFont(font);
        scrollPane = new JScrollPane(logLabel);
        add(scrollPane);
        sendPanel.add(btnStartSrv, 0);
        sendPanel.add(btnStopSrv, 1);
    }

    private void createPanels() {
        createSettingsPanel();
        createLogLabel();
        add(sendPanel, BorderLayout.SOUTH);
        add(settingsPanel, BorderLayout.NORTH);
    }
}
