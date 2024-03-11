package ru.gb;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int counts = 1000;
        float sum = 0;
        HashMap<Integer, Float> statIfChangePick = new HashMap<>();
        HashMap<Integer, Float> statIfNotChangePick = new HashMap<>();
        
        //прогоняем всю игру на 1000 итераций и считаем проценты побед
        for (int i = 1; i <= counts; i++) {
            sum = sum + gamerChangePick();
            statIfChangePick.put(i, sum / i * 100);
        }
        System.out.println("Процент побед при смене выбора = " + sum / counts * 100 + "%");
        System.out.println(statIfChangePick);

        sum = 0;
        for (int i = 1; i <= counts; i++) {
            sum = sum + gamerNotChangePick();
            statIfNotChangePick.put(i, sum / i * 100);
        }
        System.out.println("Процент побед при неизменном выборе = " + sum / counts * 100 + "%");
        System.out.println(statIfNotChangePick);
    }

    static int gamerChangePick() {
        return startGame(true);
    }

    static int gamerNotChangePick() {
        return startGame(false);
    }

    static int startGame(boolean isChangePick) {
        Random random = new Random();
        List<Door> doors = new ArrayList<>(); //массив дверей

        Gamer gamer = new Gamer(); //игрок
        Master master = new Master(); //ведущий

        //создаем три двери
        doors.add(new Door("КОЗА"));
        doors.add(new Door("АВТОМОБИЛЬ"));
        doors.add(new Door("КОЗА"));

        int gamerChoice = random.nextInt(0, 3); //игром случайно выбирает дверь
        gamer.pickDoor(doors.get(gamerChoice));
//        System.out.println("Игрок выбирает дверь № " + gamerChoice);

        //ведущий открывает случайную дверь с козой (не равную той, что выбрал игрок)
        int masterChoice = random.nextInt(0, 3);
        while (masterChoice == gamerChoice || doors.get(masterChoice).viewSurprice().equals("АВТОМОБИЛЬ")) {
            masterChoice = random.nextInt(0, 3);
        }
//        System.out.println("Ведущий открывает дверь № " + masterChoice + ", внутри " + master.openDoor(doors.get(masterChoice)));

        //игром либо меняет выбор, либо нет
        if (isChangePick) {
            int i = 0;
            while (doors.get(i).isOpen() || doors.get(i).isPicked()) { //подбираем закрытую и не выбранную игроком дверь
                i++;
            }
            gamer.unpickDoor(doors.get(gamerChoice)); //сняли выбор с предыдущей двери
            gamerChoice = i;
            gamer.pickDoor(doors.get(gamerChoice)); //выбрали другую дверь
//            System.out.println("Игрок передумал и выбрал дверь № " + gamerChoice);
        } else {
//            System.out.println("Игрок не меняет выбор, дверь № " + gamerChoice);
        }

        //ведущий открывает выбранную игроком дверь
        String result = master.openDoor(doors.get(gamerChoice));
//        System.out.println("Ведущий открывает дверь № " + gamerChoice + ", внутри " + result);

        //проверяем условие победы
        if (result.equals("АВТОМОБИЛЬ")) {
//            System.out.println("Игрок ПОБЕДИЛ!");
            return 1;
        } else {
//            System.out.println("Игрок ПРОИГРАЛ!");
            return 0;
        }
    }
}