package ru.gb.jdk.two.online;

import ru.gb.jdk.two.online.common.MainCanvas;

import java.awt.*;
public interface CanvasRepaintListener {
    void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime);
}
