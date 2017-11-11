
import java.util.Random;

public class Sensor extends Thread {
    private final Device device;

    private double value;

    public Sensor(Device device){
        this.device = device;
    }

    public double getValue(){
        return value;
    }

    public void updateValue(){
        this.value += (new Random().nextDouble() * 60 +1);
    }

    public void run(){
        synchronized (device) {
            while (device.getStartUp()) {
                try {

                    updateValue();

                    device.wait();
                    device.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
