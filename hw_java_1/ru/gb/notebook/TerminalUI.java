package ru.gb.notebook;

import java.util.Scanner;

public class TerminalUI {
    String res = "";
    public TerminalUI() {
        super();
    }

    public void viewMenu() {
        System.out.println("Заметки v0.1");
    }

    public String getText() {
        Scanner in = new Scanner(System.in, "cp866");
        return in.nextLine();
    }
}
