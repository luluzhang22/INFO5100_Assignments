
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
        this.value += 0.05;
    }

    public void run(){
        while (device.getStartUp()) {
            synchronized (device) {
                double oldValue = value;
                updateValue();
                if (value != oldValue) {
                    device.notify();
                }
            }
        }
    }
}
