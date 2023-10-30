import java.io.*;

public class BufferedTest {
    private static final File file = new File("buffer_test.txt");
    /*
    Запишем в файл 1_000_000 символов и посчитаем время записи
     */
    public static void createTestFile() {
        try {
            long timeStart = System.currentTimeMillis();
            BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(file)); //создали буфер
            for (int i = 1000000; --i >=0;) {
                outStream.write(i);
            }

            long time = System.currentTimeMillis() - timeStart;
            System.out.println("Writing time: " + time + " millisecond");
            outStream.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            e.printStackTrace();
        }
    }

    public static void directReadFile() {
        long timeStart = System.currentTimeMillis();
        try {
            InputStream inStream = new FileInputStream(file);
            while (inStream.read() != -1) {}
            long time = System.currentTimeMillis() - timeStart;
            inStream.close();
            System.out.println("Direct read time: " + (time) + " milliseconds");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void bufferReadFile() {
        long timeStart = System.currentTimeMillis();
        try {
            BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
            while (inStream.read() != -1);

            long time = System.currentTimeMillis() - timeStart;
            inStream.close();
            System.out.println("Buffered read time: " + (time) + " milliseconds");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
