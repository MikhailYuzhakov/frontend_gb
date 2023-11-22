package ru.gb.jdk.lecture1.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/*
Создаем карту для игры, наследник JPanel
 */
public class Map extends JPanel {
    private int panelWidth;
    private int panelHeight;
    private int cellHeight;
    private int cellWidth;
    private static final Random RANDOM = new Random(); //генератор псевдослучайных чисел
    private final int HUMAN_DOT = 1;
    private final int PVP_MODE = 1;
    private final int PVE_MODE = 0;
    private final int AI_DOT = 2;
    private final int EMPTY_DOT = 0;
    private int fieldSizeY = 3;
    private int fieldSizeX = 3;
    private int gameMode = 0;
    private int wLen = 3;
    private char[][] field;
    private int gameOverType;
    private static final int STATE_DRAW = 0;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_AI = 2;
    private static final int STATE_WIN_HUMAN_2 = 3;
    private static final String MSG_WIN_HUMAN = "Победил игрок 1!";
    private static final String MSG_WIN_HUMAN_2 = "Победил игрок 2!";
    private static final String MSG_WIN_AI = "Победил компьютер!";
    private static final String MSG_DRAW = "Ничья";
    private boolean isGameOver;
    private boolean isInitialized;
    private boolean turn = true;

    Map () {
        isInitialized = false;
        addMouseListener(new MouseAdapter() { //обработчик нажатия мыши
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e, gameMode); //метод выведет в терминал номер ячейки куда кликнули
            }
        });
    }

    /**
     *
     * @param mode режим игры
     * @param fSzX размер поля по абциссе
     * @param fSzY размер поля по ординате
     * @param wLen длина для победы
     */
    void startNewGame(int mode, int fSzX, int fSzY, int wLen) {
        System.out.printf("\nMode: %d;\nSize: x=%d, y=%d;\nWin Length: %d", mode, fSzX, fSzY, wLen);
        isGameOver = false;
        isInitialized = true;
        this.gameMode = mode;
        this.fieldSizeX = fSzX;
        this.fieldSizeY = fSzY;
        this.wLen = wLen;
        initMap();
        repaint();
    }
    private void initMap() {
        field = new char[fieldSizeY][fieldSizeX];
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }
    /*
    Для этого будет создан ещё один метод void render(Graphics g), который будет вызываться из
    переопределённого paintComponent(). из самого paintComponent() вызов метода родительского
    класса удалять не следует, поскольку там, скорее всего, происходит что-то важное. Для вызова
    же метода фреймворка, необходимо в нужный момент сказать фреймворку что требуется перерисовать
    панель, фреймворк поставит метод paintComponent() в очередь сообщений окна, и когда очередь
    дойдёт до выполнения этого метода – окно выполнит перерисовку.
    Действие полностью асинхронно и косвенно зависит от наших вызовов.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }
    //ход игрока по событию клика мышки
    private void update(MouseEvent e, int gameMode) {
        if (isGameOver || !isInitialized) return;
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        //если ячека не валидна или не пустая, то функция возвратится
        if (!isValidCell(cellX, cellY) || isEmptyCell(cellX, cellY)) return;

        if (gameMode == PVE_MODE) { //если игрок против компьютера
            field[cellY][cellX] = HUMAN_DOT;
            if (checkEndGame(HUMAN_DOT, STATE_WIN_HUMAN)) return;
            aiTurn();
            repaint();
            if (checkEndGame(AI_DOT, STATE_WIN_AI)) return;
        } else if (gameMode == PVP_MODE) { //если игрок против игрока
            if (turn) {
                field[cellY][cellX] = AI_DOT; //второй игрок вместо AI ходит
                if (checkEndGame(AI_DOT, STATE_WIN_HUMAN_2)) return;
            } else {
                field[cellY][cellX] = HUMAN_DOT;
                if (checkEndGame(HUMAN_DOT, STATE_WIN_HUMAN)) return;
            }
            repaint();
            turn = !turn;
        } else throw new RuntimeException("Unexpected game mode!");
        repaint();
    }
    private boolean checkEndGame(int dot, int gameOverType) {
        if (checkWin(dot)) {
            isGameOver = true;
            this.gameOverType = gameOverType;
            repaint();
            return true;
        }
        if (isMapFull()) {
            isGameOver = true;
            this.gameOverType = STATE_DRAW;
            repaint();
            return true;
        }
        return false;
    }
    private boolean checkWin(int dot) {
        return (checkWinnerHorVert(dot) | checkWinnerDiagonal(dot));
    }

    /**
     * Метод проверяет условие победы по горизонтали и вертикали игрового поля
     * @param dot символ для проверки
     * @return истину или ложь
     */
    private boolean checkWinnerHorVert(int dot) {
        int countDotHor = 0;
        int countDotVert = 0;
        for (int i = 0; i < fieldSizeY; i++) { //строки перебирает
            for (int j = 0; j < fieldSizeX; j++) { //столбцы перебирает
                if (field[i][j] == dot) countDotHor++; else countDotHor = 0;
                if (field[j][i] == dot) countDotVert++; else countDotVert = 0;
                if (countDotHor == wLen | countDotVert == wLen) {
                    return true;
                }
            }
            countDotHor = 0;
            countDotVert = 0;
        }
        return false;
    }

    /**
     * Метод проверяет условие победы по главное и побочной диагонали игрового поля
     * @param dot символ для проверки
     * @return истину или ложь
     */
    private boolean checkWinnerDiagonal(int dot) {
        int diagMainCount = 0;
        int diagSecondCount = 0;
        for (int i = 0; i < fieldSizeY; i++) {
            if (field[i][fieldSizeX-1 - i] == dot) diagSecondCount++; else diagSecondCount = 0;
            if (field[i][i] == dot) diagMainCount++; else diagMainCount = 0;
            if (diagMainCount == wLen | diagSecondCount == wLen) return true;
        }
        return false;
    }

    private void render(Graphics g) {
        if (!isInitialized) return;
        panelWidth = getWidth(); //получаем ширину панели
        panelHeight = getHeight(); //получаем высоту панели
        cellHeight = panelHeight / this.fieldSizeY; //вычисляем высоту и ширину клетки
        cellWidth = panelWidth / this.fieldSizeY;

        g.setColor(Color.BLACK);
        //рисуем горизонтальные и вертикальные линии
        for (int h = 0; h < this.fieldSizeY; h++) {
            int y = h * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }
        for (int w = 0; w < this.fieldSizeX; w++) {
            int x = w * cellHeight;
            g.drawLine(x, 0, x, panelHeight);
        }
        paintMap(g);
        if (isGameOver) showMessageGameOver(g);
        repaint(); //полностью перерисовывает компонент панели
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200, getWidth(),70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));
        switch (gameOverType) {
            case STATE_DRAW -> {
                g.drawString(MSG_DRAW, 180, getHeight() / 2);
            }
            case STATE_WIN_AI -> {
                g.drawString(MSG_WIN_AI, 20, getHeight() / 2);
            }
            case STATE_WIN_HUMAN -> {
                g.drawString(MSG_WIN_HUMAN, 70, getHeight() / 2);
            }
            case STATE_WIN_HUMAN_2 -> {
                g.drawString(MSG_WIN_HUMAN_2, 70, getHeight() / 2);
            }
            default -> throw new RuntimeException("Unexpected gameOver state: " + gameOverType);
        }
    }

    //проверит лежит ли курсор в заданых пределах
    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }
    private boolean isEmptyCell(int x, int y) {
        return field[y][x] != EMPTY_DOT;
    }
    private void paintMap(Graphics g) {
        final int DOT_PADDING = (int) (cellWidth * 0.05);
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == EMPTY_DOT) continue;

                if (field[y][x] == HUMAN_DOT) {
                    g.setColor(Color.BLUE);
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else if (field[y][x] == AI_DOT) {
                    g.setColor(Color.RED);
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else {
                    throw new RuntimeException("Unexpected value " + field[y][x] +
                            " in cell: " + "x=" + x + " y=" + y);
                }
            }
        }
    }
    //ход компьютера
    private void aiTurn() {
        if (isPreventWinAi()) return; //AI ищет комбинацию в которой он победит за свой ход
        if (isPreventHumanWin()) return; //AI ищет комбинацию, чтобы не дать игроку победить
        randomTurn(); //если не нашли вышеукзанный комбинаций, то делаем рандомный ход
    }

    private boolean isPreventWinAi() {
        int x, y; //превентивно ищем комбинацию для победы AI и используем её
        for (y = 0; y < fieldSizeY; y++) {
            for (x = 0; x < fieldSizeX; x++) {
                if (!isEmptyCell(x, y)) {
                    field[y][x] = AI_DOT;
                    if (checkWin(AI_DOT)) {
                        return true;
                    } else {
                        field[y][x] = EMPTY_DOT;
                    }
                }
            }
        }
        return false;
    }

    private boolean isPreventHumanWin() {
        int x, y; //превентивно ищем комбинацию для победы игрока и блокируем её
        for (y = 0; y < fieldSizeY; y++) {
            for (x = 0; x < fieldSizeX; x++) {
                if (!isEmptyCell(x, y)) {
                    field[y][x] = HUMAN_DOT;
                    if (checkWin(HUMAN_DOT)) {
                        field[y][x] = AI_DOT;   //препятствуем победе игрока может перерисовать кружок игрока
                        return true;
                    } else {
                        field[y][x] = EMPTY_DOT;
                    }
                }
            }
        }
        return false;
    }

    private void randomTurn() {
        int x,y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (isEmptyCell(x, y));
        field[x][y] = AI_DOT;
    }
    //проверка на ничью
    private boolean isMapFull() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }
}
