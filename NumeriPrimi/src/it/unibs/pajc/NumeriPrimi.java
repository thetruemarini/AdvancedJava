import java.util.concurrent.Callable;

public class NumeriPrimi implements Callable<Long> {

    private long start;
    private long end;
    private long nPrimi;

    public NumeriPrimi(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public Long call() {
        this.nPrimi = contaPrimi(start, end);
        return nPrimi;
    }

    public static long contaPrimi(long start, long end) {
        long nPrimes = 0;
        for (long i = start; i <= end; i++) {
            if (isPrimo(i))
                nPrimes++;
        }
        return nPrimes;
    }

    public static boolean isPrimo(long k) {
        if (k == 2)
            return true;
        if (k <= 1 || k % 2 == 0)
            return false;

        long kmax = (long) Math.sqrt(k);

        for (long i = 3; i <= kmax; i += 2) {
            if (k % i == 0)
                return false;
        }

        return true;
    }
}
