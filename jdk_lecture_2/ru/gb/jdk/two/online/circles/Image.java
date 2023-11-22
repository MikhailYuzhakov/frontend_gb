package ru.gb.jdk.two.online.circles;

import ru.gb.jdk.two.online.common.MainCanvas;
import ru.gb.jdk.two.online.common.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;

public class Image extends Sprite {
    private static Random rnd = new Random();
    private StringBuilder paths;
    private float vX;
    private float vY;
    private BufferedImage image;
    public Image(Path path) {
        try {
            image = ImageIO.read(path.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        halfHeight = 20 + (float)(Math.random() * 50f);
        halfWidth = halfHeight;
        vX = 100f + (float) (Math.random() * 200f);
        vY = 100f + (float) (Math.random() * 200f);
    }

    @Override
    public void update(MainCanvas canvas, float deltaTime) {
        super.update(canvas, deltaTime);
        x += vX * deltaTime;
        y += vY * deltaTime;

        if (getLeft() < canvas.getLeft()) {
            setLeft(canvas.getLeft());
            vX = -vX;
        }
        if (getRight() > canvas.getRight()) {
            setRight(canvas.getRight());
            vX = -vX;
        }
        if (getTop() < canvas.getTop()) {
            setTop(canvas.getTop());
            vY = -vY;
        }
        if (getBottom() > canvas.getBottom()) {
            setBottom(canvas.getBottom());
            vY = -vY;
        }
    }

    @Override
    public void render(MainCanvas canvas, Graphics g) {
        g.drawImage(image, (int)getLeft(), (int)getTop(), (int)getWidth(), (int)getHeight(), canvas);
    }
}
