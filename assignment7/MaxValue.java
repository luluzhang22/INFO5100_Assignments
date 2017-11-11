
import java.util.Arrays;

/**
 * 7.1 Write a program called MaxValue.java that finds the maximum value in an array of ints using 4 threads.
 * Your main should be similar as the one in SumThread example, though you should construct your array of random numbers instead of increasing numbers.
 * You may assume in your threaded code that the array has at least 4 elements.
 */
public class MaxValue extends Thread{
    private int lo,hi;
    private int[] arr;
    private int max = Integer.MIN_VALUE;

    public MaxValue(int[] arr,int lo, int hi){
        this.lo = lo;
        this.hi = hi;
        this.arr = arr;
    }

    @Override
    public void run(){
        for(int i = lo; i < hi; i++) {
            max = Math.max(max,arr[i]);
        }
    }

    public static int max(int[] arr) throws InterruptedException {
        int len = arr.length;
        int max = Integer.MIN_VALUE;

        MaxValue[] maxTread = new MaxValue[4];
        for (int i = 0; i < 4; i++) {
            maxTread[i] = new MaxValue(arr, (i * len) / 4, ((i + 1) * len / 4));
            maxTread[i].start();
        }

        for (int i = 0; i < 4; i++) {
            maxTread[i].join();
            max = Math.max(max, maxTread[i].max);
        }
        return max;
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * arr.length);
        }
        System.out.println("arr: " + Arrays.toString(arr));
        int max = max(arr);
        System.out.println("Max: " + max);
    }
}