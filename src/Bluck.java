import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import parcs.*;

public class Bluck {

    private static final int NODES = 2;

    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("MyClass.jar");
        int n = readData(curtask.findFile("input"));

        long startTime = System.nanoTime();
        AMInfo info = new AMInfo(curtask, null);
        List<channel> channels = new ArrayList<>();
        for (int i = 0; i < NODES; i++) {
            point p = info.createPoint();
            channel c = p.createChannel();
            channels.add(c);
            p.execute("MyClass");
        }

        for (int i = 0; i < channels.size(); i++) {
            int count = n / channels.size();
            int first = 1 + count * i;
            int last = i == channels.size() - 1 ? n : count * (i + 1);
            channels.get(i).write(new int[]{first, last});
        }

        System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms took");

        for (int i = 0; i < channels.size(); i++) {
            channel channel = channels.get(i);
            int[] ans = (int[])channel.readObject();
            for (int j = 0; j < ans.length; j++) {
                System.out.println(ans[j]);
            }
        }
        long stopTime = System.nanoTime();
        System.out.println("End of task in " + (stopTime - startTime) / 1000000 + " ms");
        curtask.end();
    }

    public static int readData(String filename) throws Exception {
        Scanner sc = new Scanner(new File(filename));
        return sc.nextInt();
    }
}
