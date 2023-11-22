package ru.gb.jdk.two.online.common;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exceptional extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Exception!", JOptionPane.ERROR_MESSAGE);
    }
}
