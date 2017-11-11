
public class Device {

    private boolean startup = false;

    public void startup(){
        startup = true;
        System.out.println("Device started");
    }

    public void shutdown(){
        startup = false;
        System.out.println("Device shutting down due to maintenance");
    }

    public boolean getStartUp(){
        return startup;
    }
}
