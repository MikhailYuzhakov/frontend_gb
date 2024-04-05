package ru.gb.chat.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private Date messageDateTime;
    SimpleDateFormat timeFormat;
    private String senderName;
    private StringBuilder messageText;
    private String recieverName;
    public Message(String senderName, String text) {
        messageText = new StringBuilder();
        messageDateTime = new Date();
        this.senderName = senderName;
        this.messageText.append(text);
        timeFormat = new SimpleDateFormat("HH:mm:ss");
    }

    public String getRecieverName() {
        return recieverName;
    }

    public String getMessageText() {
        StringBuilder content = new StringBuilder();
        if (recieverName != null) {
            content.append("@");
            content.append(recieverName);
            content.append(" ");
        }
        content.append(timeFormat.format(messageDateTime));
        content.append(" <");
        content.append(senderName);
        content.append(">: ");
        content.append(messageText);
        return content.toString();
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getMessage() {
        return messageText.toString();
    }
}
