package ru.gb.notebook;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Note {
    String dateString;
    String noteName;
    String text;
    SimpleDateFormat f1;
    public Note(String noteName) {
        this.noteName = noteName;
        f1 = new SimpleDateFormat("dd.MM.yyyy");
    }

    public void addNote(String text) {
        Date date = new Date();
        dateString = f1.format(date);
        this.text = text;
    }

    public String getDate() {
        return dateString;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return getDate() + " -> " + getText() + "\n";
    }
}
