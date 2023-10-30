import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {
    public static void read() {
        try (RandomAccessFile file = new RandomAccessFile("buffer_test.txt", "rw")) {
            FileChannel inChannel = file.getChannel(); //создаем канал для чтения
            ByteBuffer buf = ByteBuffer.allocate(100); //создаем буффер размером 100 байт
            int bytesRead = inChannel.read(buf);

            while (bytesRead != -1) {
                System.out.println("Read " + bytesRead + " bytes");
                //устанавливаем режим буфера на чтение
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.printf(" %x", buf.get());
                }
                System.out.println();
                buf.clear();
                bytesRead = inChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
