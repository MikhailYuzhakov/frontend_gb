package ru.gb.jdk.two.online.common;

import java.awt.*;

/**
 * Интерфейс описывает объекты, которые должны уметь рисоваться и обновляться.
 */
public interface Interactable {
    void update(MainCanvas canvas, float deltaTime);
    void render(MainCanvas canvas, Graphics g);
    boolean isValidPosition(int x, int y);
}
