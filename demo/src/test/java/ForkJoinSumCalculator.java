import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
       this(numbers,0,numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if(length <= THRESHOLD) {
            return computeSequentially();
        }

        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers,start,start+length/2);
        leftTask.fork();

        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers,start+length/2,end);
        rightTask.fork();

        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();


        return rightResult + leftResult;
    }


    private long computeSequentially() {
        long sum = 0;

        for (int i = start;i<end;i++) {
            sum += numbers[i];
        }

        return sum;
    }



    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static void main(String[] args) {
        long n = 10000000;
        long start = System.currentTimeMillis();
        System.out.println(forkJoinSum(n));
        System.out.println("time1 :" +(System.currentTimeMillis()-start));

        long sum = 0;
        start = System.currentTimeMillis();
        for(long i = 0;i<n;i++) {
            sum+=i;
        }

        System.out.println(sum);
        System.out.println("time2 :" +(System.currentTimeMillis()-start));
    }
}
