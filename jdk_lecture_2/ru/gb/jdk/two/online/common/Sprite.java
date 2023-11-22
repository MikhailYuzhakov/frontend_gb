package ru.gb.jdk.two.online.common;

import ru.gb.jdk.two.online.common.MainCanvas;

import java.awt.*;

/**
 * Класс описывает общие для всех рисуемых объектов в программе поведение и свойства.
 */
public abstract class Sprite implements Interactable {
    protected float x;
    protected float y;
    protected float halfWidth;
    protected float halfHeight;

    /**
     * Отдает левую границу объекта
     */
    protected float getLeft() {return x - halfWidth;}
    protected void setLeft(float left) {x = left + halfWidth;}
    protected float getRight() {return x + halfWidth;}
    protected void setRight(float right) {x = right - halfWidth;}
    protected float getTop() {return y - halfHeight;}
    protected void setTop(float top) {y = Math.abs(top - halfHeight);}
    protected float getBottom() {return y + halfHeight;}
    protected void setBottom(float bottom) {y = bottom - halfHeight;}

    protected float getWidth() {return 2f * halfWidth;}
    protected float getHeight() {return 2f * halfHeight;}

    public boolean isValidPosition(int posX, int posY) {
        return getRight() >= posX && getBottom() >= posY;
    }

    public void update(MainCanvas canvas, float deltaTime) { }
    public void render(MainCanvas canvas, Graphics g) { }
}
