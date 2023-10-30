import java.io.*;

public class InputOutputExample {
    private static final byte[] bytesToWrite = {0, 10, 12, 55, 13, 23};
    private static byte[] bytesToRead = new byte[10];
    public static void bytes() {
        File file = new File("bytes.txt");
        try {
            FileOutputStream outFile = new FileOutputStream(file); //создаем объект для потока вывода в файл
            outFile.write(bytesToWrite); outFile.close(); //записываем байты и закрываем поток
            System.out.println("Bytes written");

            FileInputStream inFile = new FileInputStream(file);
            int bytesAvailable = inFile.available(); //сколько байт доступно в файле
            System.out.println("Ready to read " + bytesAvailable + " bytes");

            int count = inFile.read(bytesToRead, 0, bytesAvailable); //вернет кол-во считанных байт
            for (int i = 0; i < count; i++) {
                System.out.print(" " + bytesToRead[i]);
            }
            System.out.println(); inFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
