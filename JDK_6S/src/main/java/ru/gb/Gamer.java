package ru.gb;

public class Gamer {
    public Gamer() {}

    public void pickDoor(Door door) {
        door.pick();
    }

    public void unpickDoor(Door door) {
        door.unpick();
    }
}
