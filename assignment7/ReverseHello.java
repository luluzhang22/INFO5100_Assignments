
/**
 * 7.2 create a thread (let's call it Thread 1).
 * Thread 1 creates another thread (Thread 2); Thread 2 creates Thread 3; and so on, up to Thread 50.
 * Each thread should print "Hello from Thread num!"
 * but you should structure your program such that the threads print their greetings in reverse order.
 */
public class ReverseHello { // score 2
    public static void main(String[] args){
        ReverseThread thread1 = new ReverseThread(1);
        thread1.start();
    }

    static class ReverseThread extends Thread{
        private int num;

        ReverseThread(int num){
            this.num = num;
        }

        @Override
        public void run(){
            if(num < 50){
                createThread(num+1);
            }
            System.out.println("Hello form Thread " + num);
        }

        private void createThread(int num){
            ReverseThread rt = new ReverseThread(num);
            rt.start();
            try{
                rt.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
