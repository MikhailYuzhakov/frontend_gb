package ru.gb.notebook;

import java.util.ArrayList;

public class Presenter {
    public static void main(String[] args) {
        ArrayList<Note> notes = new ArrayList<>();
        TerminalUI tUI = new TerminalUI();
        tUI.viewMenu();
        Note note = new Note(tUI.getText());
        note.addNote(tUI.getText());
        Export export = new Export(note.getNoteName());
        export.saveToFile(note);
    }
}
