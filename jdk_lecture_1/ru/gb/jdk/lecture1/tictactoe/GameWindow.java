package ru.gb.jdk.lecture1.tictactoe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//наследуем все методы библиотеки Swing, наследую методы JFrame
public class GameWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    JButton btnStart = new JButton("New Game");
    JButton btnExit = new JButton("Exit");
    Map map;
    SettingsWindow settings;
    //модификатор package-private
    public GameWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE); //завершит приложение при закрытии этого окна
        setLocation(WINDOW_POSX, WINDOW_POSY); //установит окно в заданную позицию
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT); //устанавливает размеры окна
        setTitle("TicTacToe"); //задаем заголовок окна
        setResizable(false); //запрещаем изменять размеры окна

        map = new Map(); //создаем игровое поле, наследник JPanel
        settings = new SettingsWindow(this); //передаем в окно ссылку на объек GameWindow

        //создаем новую панель и для нее используем компоновщик GridLayout
        JPanel panBottom = new JPanel(new GridLayout());
        panBottom.add(btnStart); //добавляем кнопки именно в панель
        panBottom.add(btnExit);

        //добавляем обработчик нажатия кнопки, передаем в качестве аргумента объект
        //ActionListener и переопределяем метод actionPerformed()
        //выходим из игры при нажатии кнопки
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //при нажатии кнопки делаем видимым окно настроек
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(true);
            }
        });
        /*
        в окне GameWindow по-умолчанию используется компановщик BorderLayout
        определяем расположение панели panBottom на "юге" (внизу)
         */
        add(panBottom, BorderLayout.SOUTH);
        //добавляем панель для игрового поля
        add(map);

        setVisible(true); //делает окно при вызове конструктора видимым
    }

    void startNewGame(int mode, int fSzX, int fSzY, int wLen) {
        map.startNewGame(mode, fSzX, fSzY, wLen);
    }
}
