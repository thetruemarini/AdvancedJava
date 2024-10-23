import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NumeriPrimiApp {
    public static void main(String[] args) throws Exception {
        long nMax = 1_000_000_000;
        int nTask = 50;

        System.err.println("START...");
        Chrono c = new Chrono();

        ExecutorService exec = Executors.newFixedThreadPool(20);
        ArrayList<Future<Long>> result = new ArrayList<>();

        long valoriPerTask = nMax / nTask;

        for (int i = 0; i < nTask; i++) {
            NumeriPrimi task = new NumeriPrimi(
                    i * valoriPerTask + 1,
                    Math.min((i + 1) * valoriPerTask, nMax));

            result.add(exec.submit(task));
        }

        long nPrimi = 0; //NumeriPrimi.contaPrimi(1, nMax);
        for(Future<Long> f : result)
            nPrimi += f.get();

        exec.shutdown();
        c.stop();
        System.out.printf("%d -> %s\n", nPrimi, c);
    }
}
