public class StringTest {
    public static void timeToString() {
        long timeStart = System.nanoTime();

        String s = "Example";
        for (int i = 0; i < 200_000; i++) {
            s = s + i;
        }
        double deltaTime = (System.nanoTime() - timeStart) * 0.000000001;
        System.out.println("Delta time (sec): " + deltaTime);
    }

    public static void timeToStringBuilder() {
        long timeStart = System.nanoTime();

        StringBuilder sb = new StringBuilder("Example");
        for (int i = 0; i < 200_000; i++) {
            sb.append(i);
        }
        double deltaTime = (System.nanoTime() - timeStart) * 0.000000001;
        System.out.println("Delta time (sec): " + deltaTime);
    }
}
