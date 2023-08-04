import java.io.IOException;

public class Presenter {
    enum Color {
        RED("#FF0000"), GREEN("#00FF00"), BLUE("0000FF");
        private String code;
        Color(String code) {
            this.code = code;
        }
        public String getCode() {
            return code;
        }
    }

    public static void main(String[] args) {
        Task1 hw = new Task1();
        int[][] matrix = {{1, 2, 3, 4},
                          {4, 5, 6, 6},
                          {7, 8, 9, 1},
                          {1, 2, 3, 4},
                          {4, 5, 3, 2}};
        try {
            System.out.println(hw.matrixSum(matrix));
        } catch (RowConversionException | ColumnConversionException e) {
            e.printStackTrace();
        }
//        Lecture004 l4 = new Lecture004();
//        l4.enums();
//        for (Color c: Color.values()) {
//            System.out.printf("%s (%s) \n", c, c.getCode());
//        }
//
//        /*
//        Использование вложенного класса
//         */
//        Orange orange = new Orange();
//        orange.squeezeJuice();
//        // пример использования статического вложенного класса
//        Cat.Voice voice = new Cat.Voice();

//        MyException mexp = new MyException();
//        mexp.div0(1, 0);
//        mexp.methodA();
//        mexp.methodB();


//        try (TestStream stream = new TestStream()) {
//            int a = stream.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        до java 8
//        TestStream stream = null;
//        try {
//            stream = new TestStream();
//            int a = stream.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (stream != null) {
//                try {
//                    stream.close();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                stream.close();
//            }

        }

    }
