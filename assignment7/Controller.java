
public class Controller extends Thread {
    private Device device;
    private Sensor heat;
    private Sensor pressure;

    public Controller(Device device, Sensor heat, Sensor pressure) {
        this.device = device;
        this.heat = heat;
        this.pressure = pressure;
    }

    @Override
    public void run() {
        device.startup();
        while (heat.getValue() < 70 && pressure.getValue() < 100) {
            try {
                synchronized (device){
                    System.out.print("heat -> " + heat.getValue());
                    System.out.println(", pressure -> " + pressure.getValue());
                    device.notifyAll();
                    device.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("heat -> " + heat.getValue());
        System.out.println(", pressure -> " + pressure.getValue());
        device.shutdown();
    }
}
