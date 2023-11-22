package ru.gb.jdk.two.online.bricks;

import ru.gb.jdk.two.online.common.Interactable;
import ru.gb.jdk.two.online.common.MainCanvas;
import ru.gb.jdk.two.online.common.Sprite;

import java.awt.*;

public class Background implements Interactable {
    @Override
    public void update(MainCanvas canvas, float deltaTime) {

    }

    @Override
    public void render(MainCanvas canvas, Graphics g) {
        canvas.setBackground(Color.WHITE);
    }

    @Override
    public boolean isValidPosition(int x, int y) {
        return false;
    }
}
