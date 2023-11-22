package ru.gb.jdk.two.online.bricks;

import ru.gb.jdk.two.online.CanvasRepaintListener;
import ru.gb.jdk.two.online.circles.Image;
import ru.gb.jdk.two.online.common.ImageSearcher;
import ru.gb.jdk.two.online.common.Interactable;
import ru.gb.jdk.two.online.common.MainCanvas;
import ru.gb.jdk.two.online.common.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainWindow extends JFrame implements CanvasRepaintListener {
    private static final int POX_X = 400;
    private static final int POX_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int  WINDOW_HEIGHT = 600;
    private final Interactable[] sprites = new Interactable[9];
    private final String DIR = "E:\\04_GeekBrains\\07_backend_java\\jdk_lecture_2\\ru\\gb\\jdk\\two\\online\\img";
    private final ImageSearcher imgSrch;

    public MainWindow() {
        imgSrch = new ImageSearcher(DIR);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POX_X, POX_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Rectangles");

        sprites[0] = new Background();
        for (int i = 1; i < imgSrch.getImagesCount(); i++) {
            sprites[i] = new Image(imgSrch.getNextImagePath());
            System.out.println(sprites[i]);
        }

        MainCanvas canvas = new MainCanvas(this);
        add(canvas);
        setVisible(true);
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
        for (Interactable sprite : sprites) {
            System.out.println(sprite);
            sprite.update(canvas, deltaTime);
        }
    }

    private void render(MainCanvas canvas, Graphics g) {
        for (Interactable sprite : sprites) {
            sprite.render(canvas, g);
        }
    }
}
