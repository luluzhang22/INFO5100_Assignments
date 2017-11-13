
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

        synchronized (device) {
            while (heat.getValue() <= 70 && pressure.getValue() <= 100) {
                try {
                    device.wait();
                    System.out.print("heat -> " + String.format("%.2f",heat.getValue()));
                    System.out.println(", pressure -> " + String.format("%.2f",pressure.getValue()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        device.shutdown();
    }
}
