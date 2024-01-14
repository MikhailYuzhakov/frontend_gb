package Lecture;

import java.util.TreeMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Lecture {
    private static Integer object = 0;
    private static Integer counter = 0;
    private static Integer count = 0;

    private static Integer resource = 0;

    public static void main(String[] args) throws InterruptedException {
//        createThreadsMethod1();
//        createThreadMethod2();
//        interruptExample1();
//        interruptExample2();
//        threadJoinExample1();
//        conflictExample1();
//        synchronizedThreadsEx1();
//        threadLocalExample();
//        semaphoreExample();
//        exchangeExample();
//        countDownLatchExample();
//        cyclicBarrierExample();
        reetrantLock();
    }


    //универсальный метод и best practice для создания потоков
    static void createThreadsMethod1() {
        System.out.println(Thread.currentThread().getName());
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println("make some work!");
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    static void createThreadMethod2() {
        System.out.println(Thread.currentThread().getName());
        ExampleThread exampleThread = new ExampleThread();
        exampleThread.start();
    }

    //предлагаем потоку остановиться при реализации метода run блокирующим поток методом sleep
    static void interruptExample1() {
        Runnable task = () -> {
            try {
                Thread.sleep(10000); //поток вызываем прерывание метода блокирования потока - exception
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        thread.interrupt();
    }

     //выполнится сперва реализация, а затем поток прервется без exception
    static void interruptExample2() {
        Runnable task = () -> {
            while (!Thread.currentThread().isInterrupted()) {

            }
            System.out.println("finished");
        };
        Thread thread = new Thread(task);
        thread.start();
        thread.interrupt();
    }

    //метод yield() - отдает процессорное время другим потокам системы
    static void threadJoinExample1() throws InterruptedException {
        Runnable task = () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        thread.join(); //текущий поток (т.е. main) блокируется до окончания выполнения поток thread
        System.out.println("Finished");
    }

    static void conflictExample1() {
        Runnable task = () -> {
            object = object + 1;
            System.out.println(Thread.currentThread().getName());
        };
        Thread thread = new Thread(task);
        thread.start();
        System.out.println(Thread.currentThread().getName());
        object = object + 1;
        System.out.println(object.intValue());
    }

    static void synchronizedThreadsEx1() throws InterruptedException {
        Object objectToLook = new Object();

        //задача над синхронизированным экземпляром класса, пока объект занят - поток будет ждать
        Runnable task = () -> {
            synchronized (objectToLook) {
                System.out.println(Thread.currentThread().getName());
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        thread.join(); //если сделать так, то сперва отработает thread, а потом main

        //здесь поток main будет пытаться получить доступ к синхронизированному объекту
        synchronized (objectToLook) {
            for (int i = 0; i < 10; i++) {
                Thread.currentThread().sleep(1000);
                System.out.println("step" + i);
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

//    public synchronized void doSmth() - можно синхронизировать методы классов
//     public static void doWork() {
//        synchronized (MyClass.class) {
//
//        }
//     }

    /**
     * Для каждого локального потока создаются свои переменные недостпуные другим
     * объект класса ThreadLocal хранит внутри не одно конкретное
     * значение, а хэш-таблицу (поток и соответствующее значение), и при использовании
     * обращается к значению для текущего потока
     */
    static void threadLocalExample() {
        new Thread(new ThreadTask()).start();
        new Thread(new ThreadTask()).start();
    }

    /**
     * Semaphore один из примитивов синхронизации, позволяющий определить N
     * потоков, которым позволено исполнять критическую секцию кода
     */
    static void semaphoreExample() {
        Semaphore sem = new Semaphore(2);

        new Thread(new CountThread(sem)).start();
        new Thread(new CountThread(sem)).start();
        new Thread(new CountThread(sem)).start();
    }

    /**
     * Exchanger — точка синхронизации, позволяющая двум потокам обмениваться
     * значениями.
     */
    static void exchangeExample() {
        Exchanger<String> ex = new Exchanger<>();
        new Thread(new NewThread(ex, "First message", "First Thread")).start();
        new Thread(new NewThread(ex, "Second message", "Second Thread")).start();
    }

    /**
     * CowntDownLatch — это счетчик значение которого уменьшается каждый раз, когда
     * поток использует счетчик (поток при этом блокируется). Когда значение счетчика
     * будет равно нулю, все заблокированные потоки будут одновременно запущены.
     */
    static void countDownLatchExample() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3); //надо дождаться 3х потоков для запуска

        new Car(3000, latch, "CAR-1").start();
        new Car(2000, latch, "CAR-2").start();
        new Car(1000, latch, "CAR-3").start();
        latch.await(); //ждем три потока

        System.out.println(Thread.currentThread().getName() + " has finished");
    }

    /**
     * Работает аналогично CountDownLatch
     * класс CyclicBarrier можно использовать повторно. Как только значение
     * счетчика становится равным нулю, оно восстанавливается, и объект класса
     * можно использовать заново
     */
    static void cyclicBarrierExample() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        new Car2(3000, cyclicBarrier, "CAR-1").start();
        new Car2(2000, cyclicBarrier, "CAR-2").start();
        new Car2(1000, cyclicBarrier, "CAR-3").start();
        System.out.println(Thread.currentThread().getName() + " has finished");
    }

    static void reetrantLock() throws InterruptedException {
        ReentrantLock locker = new ReentrantLock();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new LockThread(locker));
            thread.start();
            thread.join();
        }
    }

    static class ThreadTask implements Runnable {
        ThreadLocal<Integer> threadCounter = new ThreadLocal<>();
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                counter++;
                if (threadCounter.get() != null) {
                    threadCounter.set(threadCounter.get() + 1);
                } else {
                    threadCounter.set(0);
                }
            }
            System.out.println("Counter: " + counter);
            System.out.println("threadLocalCounter: " + threadCounter.get());
        }
    }

    static class CountThread implements Runnable {
        Semaphore sem;

        CountThread(Semaphore sem) {
            this.sem = sem;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " ожидает разрешения");
                sem.acquire(); //получение разрешения у семафора
                for (int i = 1; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                    count++;
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(Thread.currentThread().getName() + " освобождает разрешение");
            sem.release();
        }
    }

    static class NewThread implements Runnable {
        Exchanger<String> exchanger;
        String name;
        String message;

        public NewThread(Exchanger<String> exchanger, String message, String name) {
            this.exchanger = exchanger;
            this.message = message;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                message = exchanger.exchange(message);
                System.out.println(name + " has received: " + message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class ExampleThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            System.out.println("make some work!");
        }
    }

    static class Car extends  Thread {
        private int delay;
        private CountDownLatch latch;

        public Car(int delay, CountDownLatch latch, String name) {
            super(name);
            this.delay = delay;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(delay);
                latch.countDown();
                System.out.println(Thread.currentThread().getName() + " finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Car2 extends  Thread {
        private int delay;
        private CyclicBarrier cyclicBarrier;

        public Car2(int delay, CyclicBarrier cyclicBarrier, String name) {
            super(name);
            this.delay = delay;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(delay);
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " finished");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ReentrantLock. Он
     * позволяет одному и тому же потоку вызывать метод lock, даже если он его вызывал
     * ранее, без освобождения блокировки.
     */
    static class LockThread implements Runnable {
        ReentrantLock locker;
        LockThread (ReentrantLock locker) {
            this.locker = locker;
        }

        @Override
        public void run() {
            locker.lock(); //устанавливаем блокировку
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.printf("%s %d \n", Thread.currentThread().getName(), resource);
                    resource++;
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } finally {
                locker.unlock(); //снимаем блокировку
            }
        }
    }
}




