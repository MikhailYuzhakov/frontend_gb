package ru.gb.jdk.two.online.common;

import ru.gb.jdk.two.online.CanvasRepaintListener;
import ru.gb.jdk.two.online.circles.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * Нет прямого запрета на написании логики будущего движка или игры в классе канвы, но это архитектурно неверное
 * решение, ведь на канве должно происходить только рисование и точка.
 */
public class MainCanvas extends JPanel {
    private final CanvasRepaintListener controller; //ссылка на главное окно и метод с бизнес-логикой
    private long lastFrameTime;
    public MainCanvas(CanvasRepaintListener controller) {
        setBackground(Color.WHITE);
        this.controller = controller;
        lastFrameTime = System.nanoTime();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            Thread.sleep(1); //отправляем поток в сон на 16 мс, что соответствует частоте 62.5 Гц (~60FPS)
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        float deltaTime = (System.nanoTime() - lastFrameTime) * 0.000000001f;
        controller.onDrawFrame(this, g, deltaTime);
        lastFrameTime = System.nanoTime();
        repaint(); //перересует этот компонент
    }

    public int getLeft() {return 0;}
    public int getRight() {return getWidth() - 1;}
    public int getTop() {return 0;}
    public int getBottom() {return getHeight() - 1;}
}
