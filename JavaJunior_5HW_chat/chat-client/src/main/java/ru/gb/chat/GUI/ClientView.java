package ru.gb.chat.GUI;

interface ClientView {
    void showMessage(String text);
    void disconnectFromServer();
    void getCredentials();
}
