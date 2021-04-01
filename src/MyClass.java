import parcs.*;

import java.util.ArrayList;
import java.util.List;

public class MyClass implements AM {
    public void run(AMInfo info) {
        int[] node = (int[])info.parent.readObject();
        System.out.println("Build started.");
        long s = System.nanoTime();
        List<Integer> ans = new ArrayList<>();
        for (int i = node[0]; i < node[1]; i++) {
            if (isPrime(i)) {
                ans.add(i);
            }
        }
        int[] a = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            a[i] = ans.get(i);
        }
        System.out.println("ans is " + ans);
        System.out.println("Build finished in " + (System.nanoTime() - s) / 1000000 + " ms");
        info.parent.write(a);
    }

    private boolean isPrime(int n) {
        for(int i = 0; i*i <= n; i++) {
            if (n%i == 0) {
                return false;
            }
        }
        return true;
    }
}
