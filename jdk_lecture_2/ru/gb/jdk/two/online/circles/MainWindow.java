package ru.gb.jdk.two.online.circles;

import com.sun.jdi.event.ThreadDeathEvent;
import ru.gb.jdk.two.online.CanvasRepaintListener;
import ru.gb.jdk.two.online.common.Interactable;
import ru.gb.jdk.two.online.common.MainCanvas;
import ru.gb.jdk.two.online.common.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame implements CanvasRepaintListener, Thread.UncaughtExceptionHandler {
    private static final int POX_X = 400;
    private static final int POX_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int  WINDOW_HEIGHT = 600;
    private final Interactable[] sprites = new Interactable[16];
    private int counter = 0; //счетчик шариков

    public MainWindow() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POX_X, POX_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");

        MainCanvas canvas = new MainCanvas(this);
        add(canvas);
        setVisible(true);

        sprites[0] = new Background();
        sprites[1] = new Ball();
        counter++;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    addNewBall(e.getX(), e.getY());
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    rmBall(e.getX(), e.getY());
                }
            }
        });
    }

    /**
     * Метод периодически будет вызываться канвой. Здесь реализована бизнес-логика.
     */
    @Override
    public void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime); //отвечает за обновление положений кругов
        render(canvas, g); //отвечает за отрисовку на канве
    }

    private void update(MainCanvas canvas, float deltaTime) {
        for (int i = 0; i <= counter; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void addNewBall(int x, int y) {
        counter++;
        sprites[counter] = new Ball(x, y);
    }

    private void rmBall(int x, int y) {
        sprites[counter] = null;
        counter--;
    }

    private void render(MainCanvas canvas, Graphics g) {
        for (int i = 0; i <= counter; i++) {
            sprites[i].render(canvas, g);
        }
    }

    /**
     * Перехватывает поток с исключением в графическом режиме
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка!",
                JOptionPane.ERROR_MESSAGE);
    }
}
