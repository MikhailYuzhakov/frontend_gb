package ru.gb.chat.GUI;

import ru.gb.chat.Client;
import ru.gb.chat.common.Message;

import javax.swing.*;
import javax.swing.text.Style;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ChatClientGUI extends JFrame implements Thread.UncaughtExceptionHandler, ClientView, KeyListener {
    private static final int POS_X = 600;
    private static final int POS_Y = 200;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    private String ipAddress, login, port;
    private char[] password;
    private JButton btnLogin, btnSend;
    private JTextArea logLabel;
    private JPanel connectPanel;
    private JScrollPane scrollPane;
    private JTextField ipAddressField, loginField, portNumberField, msgTextField;
    private JPasswordField passField;
    private Client client;
    private String userName;

    public ChatClientGUI() {
//        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setTitle("Клиент чата");
        setResizable(false);

        createPanel();

        setVisible(true);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InetAddress ip = InetAddress.getByName(ipAddressField.getText());
                    int port = Integer.parseInt(portNumberField.getText());
                    userName = loginField.getText();
                    client = new Client(userName, getThis());
                    client.connectToServer(ip, port);
                    if (client.isClientConnected()) {
                        connectPanel.setVisible(false);
                        logLabel.setText(client.getConnectInfo());
                        client.listenForMessage();
                    }
                } catch (UnknownHostException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        msgTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        Message message;
        if (msgTextField.getText().contains("@")) {
            String recieverMessage = msgTextField.getText().split("@")[1];
            String privateMessage = getMessageFromSplit(recieverMessage.split(" "));
            recieverMessage = recieverMessage.split(" ")[0];
            message = new Message(userName, privateMessage);
            message.setRecieverName(recieverMessage);
        } else {
            message = new Message(userName, msgTextField.getText());
        }

        if (client.isClientConnected()) {
            client.sendMessage(message.getMessageText());
            msgTextField.setText("");
        } else {
            System.out.println("Клиент не подключен к серверу");
            connectPanel.setVisible(true);
        }
    }

    private String getMessageFromSplit(String[] message) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < message.length; i++) {
            sb.append(message[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    private ChatClientGUI getThis() {
        return this;
    }

    public void appendToLog(String message) {
        logLabel.append(message);
    }

    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        logLabel = new JTextArea();
        Font font = new Font("Calibri", Font.BOLD, 13);
        logLabel.setFont(font);
        scrollPane = new JScrollPane(logLabel);

        add(scrollPane);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        connectPanel = new JPanel(new GridLayout(2, 3, 2, 2));
        String LOGIN_BUTTON_LABEL = "Войти";
        btnLogin = new JButton(LOGIN_BUTTON_LABEL);
        ipAddressField = new JTextField("127.0.0.1");
        portNumberField = new JTextField("1400");
        loginField = new JTextField("user");
        passField = new JPasswordField();
        connectPanel.add(ipAddressField);
        connectPanel.add(portNumberField);
        connectPanel.add(loginField);
        connectPanel.add(passField);
        connectPanel.add(btnLogin);
        return connectPanel;
    }

    private JPanel createBottomPanel() {
        JPanel sendPanel = new JPanel(new GridLayout(1, 2));
        String SEND_BUTTON_LABEL = "Отправить";
        btnSend = new JButton(SEND_BUTTON_LABEL);
        msgTextField = new JTextField();
        sendPanel.add(msgTextField, 0);
        sendPanel.add(btnSend, 1);
        return sendPanel;
    }


     //TODO Написать функционал проверки правильности заполнения полей

    private void checkFields() {

    }

    private void getFieldsContent() {
        ipAddress = ipAddressField.getText();
        port = portNumberField.getText();
        login = loginField.getText();
        password = passField.getPassword();
        System.out.printf("%s:%s\n%s\n", ipAddress, port, login);
        for (char c:password) {
            System.out.printf("%c", c);
        }
    }

    @Override
    public String toString() {
        return "client.ChatClient{" +
                "ipAddress='" + ipAddress + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Сервер не запущен!", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showMessage(String text) {

    }

    @Override
    public void disconnectFromServer() {

    }

    @Override
    public void getCredentials() {
        client.setCredentials(loginField.getText(), passField.getPassword());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            System.out.println("Key Typed!");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            System.out.println("Key Pressed!");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            System.out.println("Key Released!");
    }
}
