package ru.gb.jdk.lecture1.tictactoe;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;
    private static final String BTN_START_LABEL = "Start new game";
    private static final String SETTING_WINDOW_LABEL = "Settings";
    private static final String FIELD_SIZE_LABEL = "Размер игрового поля: ";
    private static final String WIN_LENGTH_LABEL = "Выйгрышная длина: ";
    private static final String RADIO_BTN_PVP_LABEL = "PvP";
    private static final String RADIO_BTN_PVE_LABEL = "PvE";
    private static final String GAME_MODE_LABEL = "Режим игры";
    private static final String PVE_BTN_LABEL = "Игрок VS Компьютер";
    private static final String PVP_BTN_LABEL = "Игрок VS Игрок";
    private static final int GAME_FIELD_MAX_SIZE = 10;
    private static final int GAME_FIELD_MIN_SIZE = 3;
    private static final int PVP_MODE = 1;
    private static final int PVE_MODE = 0;

    private static final int FIRST_GRID_ROW = 0;
    private static final int SECOND_GRID_ROW = 1;
    private static final int THIRD_GRID_ROW = 2;
    private static final int FOURTH_GRID_ROW = 3;

    JButton btnStart;
    JRadioButton pvpBtn;
    JRadioButton pveBtn;
    JPanel settingField;
    JSlider fieldSizeSlider;
    JSlider winLengthSlider;
    JLabel fieldSizeLabel;
    JLabel winLengthLabel;
    GameWindow gameWindow;

    private static int fieldSize = GAME_FIELD_MIN_SIZE;
    private static int winLength = fieldSize;
    private static int gameMode = PVE_MODE;
    /**
     * Конструктор второго окна будет принимать экземпляр игрового окна. В первую очередь это сделано для передачи п
     * араметров игры, а во-вторых, чтобы красиво отцентрировать его относительно основного.
     */
    public SettingsWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setLocationRelativeTo(gameWindow); //позиция относительно главного окна
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle(SETTING_WINDOW_LABEL);

        createSettingsSliders();
        createSettingsButtons();
        add(settingField);
        addListeners();
    }

    void createSettingsSliders() {
        final int ROWS_SIZE = 3;
        final int COLS_SIZE = 1;
        JPanel gameFieldPanel = new JPanel();
        JPanel winSizePanel = new JPanel();
        fieldSizeLabel = new JLabel(FIELD_SIZE_LABEL + GAME_FIELD_MIN_SIZE);
        winLengthLabel = new JLabel(WIN_LENGTH_LABEL + GAME_FIELD_MIN_SIZE);

        fieldSizeSlider = new JSlider();
        winLengthSlider = new JSlider();

        fieldSizeSlider.setMaximum(GAME_FIELD_MAX_SIZE);
        fieldSizeSlider.setMinimum(GAME_FIELD_MIN_SIZE);

        winLengthSlider.setMinimum(GAME_FIELD_MIN_SIZE);
        winLengthSlider.setMaximum(fieldSizeSlider.getValue());

        settingField = new JPanel(new GridLayout(ROWS_SIZE, COLS_SIZE));
        gameFieldPanel.add(fieldSizeLabel);
        gameFieldPanel.add(fieldSizeSlider);
        winSizePanel.add(winLengthLabel);
        winSizePanel.add(winLengthSlider);

        fieldSizeSlider.setValue(GAME_FIELD_MIN_SIZE);
        fieldSizeSlider.setValue(GAME_FIELD_MIN_SIZE);

        settingField.add(gameFieldPanel, FIRST_GRID_ROW);
        settingField.add(winSizePanel, SECOND_GRID_ROW);
    }

    void createSettingsButtons() {
        JPanel gameModePanel = new JPanel();
        btnStart = new JButton(BTN_START_LABEL);
        pvpBtn = new JRadioButton(PVP_BTN_LABEL);
        pveBtn = new JRadioButton(PVE_BTN_LABEL);
        pveBtn.setSelected(true);
        ButtonGroup btnModeGroup = new ButtonGroup();
        btnModeGroup.add(pvpBtn);
        btnModeGroup.add(pveBtn);

        gameModePanel.add(pvpBtn);
        gameModePanel.add(pveBtn);
        add(btnStart, BorderLayout.SOUTH);
        settingField.add(gameModePanel, THIRD_GRID_ROW);
    }

    void addListeners() {
        fieldSizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fieldSize = fieldSizeSlider.getValue();
                System.out.println(fieldSize);
                fieldSizeLabel.setText(FIELD_SIZE_LABEL + fieldSize);
                winLengthSlider.setMaximum(fieldSize);
            }
        });

        winLengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                winLength = winLengthSlider.getValue();
                winLengthLabel.setText(WIN_LENGTH_LABEL + winLength);
            }
        });

        pvpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMode = PVP_MODE;
                System.out.println("PVP режим");
            }
        });

        pveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMode = PVE_MODE;
                System.out.println("PVE");
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.startNewGame(gameMode, fieldSize, fieldSize, winLength);
            }
        });
    }
}
