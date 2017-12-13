/* Good Work
 * Total score 10
 */
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;

public class MyJson {

    public static void main(String[] args) throws IOException {
        File file = new File("Question3_camino.txt");
        ArrayList<Vehicle> vehicles = readAndGetVehicles(file);
        String s = getJsonString(vehicles);
        File jsonFile = writeToFile(s, file.getParent());
        //extra credit
        File newWriteFile = new File("new_write_vehicle.txt");
        if(!newWriteFile.exists())
            newWriteFile.createNewFile();
        jsonFileToObjectFile(jsonFile, newWriteFile);
    }

    private static ArrayList<Vehicle> readAndGetVehicles(File file) throws IOException {
        ArrayList<Vehicle> list = new ArrayList<>();
        if(!file.exists())
            return list;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        line = reader.readLine();
        while (line != null){
            list.add(new Vehicle(line.split("~")));
            line = reader.readLine();
        }
        reader.close();
        return list;
    }

    public static String getJsonString(ArrayList<Vehicle> vehicles){
        if(vehicles == null || vehicles.size() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        sb.append("{\n\"").append(vehicles.get(0).webId).append("\" : [\n");
        for(int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            sb.append("{\n");
            sb.append("\"id\" : \"").append(vehicle.id).append("\",\n");
            sb.append("\"category\" : \"").append(vehicle.category).append("\",\n");
            sb.append("\"year\" : \"").append(vehicle.year).append("\",\n");
            sb.append("\"make\" : \"").append(vehicle.make).append("\",\n");
            sb.append("\"model\" : \"").append(vehicle.model).append("\",\n");
            sb.append("\"trim\" : \"").append(vehicle.trim).append("\",\n");
            if(vehicle.type.isEmpty()){
                sb.append("\"type\" : null,\n");
            }else {
                sb.append("\"type\" : \"").append(vehicle.type).append("\",\n");
            }
            sb.append("\"price\" : ").append(vehicle.price).append(",\n");
            sb.append("\"photo\" : \"").append(vehicle.photo).append("\"\n");
            sb.append("}");
            if(i != vehicles.size() - 1){
                sb.append(",\n");
            }else {
                sb.append("\n");
            }
        }
        sb.append("]\n}");
        return sb.toString();
    }

    public static File writeToFile(String inputToWrite, String filePath) throws IOException {
        File file = new File((filePath == null ? "" : filePath+"/")  + "json_vehicle.txt");
        file.createNewFile();
        PrintWriter pw = new PrintWriter(new FileWriter(file));
        pw.print(inputToWrite);
        pw.close();
        return file;
    }

    public static void jsonFileToObjectFile(File json, File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("id~webId~category~year~make~model~trim~type~price~photo\n");
        BufferedReader reader = new BufferedReader(new FileReader(json));
        String line = reader.readLine();
        String webId = "";
        while (line != null){
            if(line.contains("[")){
                webId = line.substring(line.indexOf("\"")+1,line.lastIndexOf("\""));
                break;
            }
            line = reader.readLine();
        }
        line = reader.readLine();
        while (line != null && line.contains("{")){
            line = reader.readLine();
            while (!line.contains("}")){
                String value = line.split(" : ")[1];
                if(value.contains("\"")){
                    sb.append(value.substring(value.indexOf("\"") + 1,value.lastIndexOf("\"")));
                }else if(!value.contains("null")){
                    sb.append(value.substring(0,value.length()-1));
                }
                if(!line.contains("photo")){
                    sb.append("~");
                }
                if(line.contains("id")){
                    sb.append(webId).append("~");
                }
                line = reader.readLine();
            }
            sb.append("\n");
            line = reader.readLine();
        }
        reader.close();
        PrintWriter pw = new PrintWriter(new FileWriter(file));
        pw.print(sb.substring(0,sb.lastIndexOf("\n")));
        pw.close();
    }
}
class Vehicle{

    String id;
    String webId;
    Category category;
    Year year;
    String make;
    String model;
    String trim;
    String type;
    double price;
    URL photo;

    Vehicle(String[] arr){
        this.id = arr[0];
        this.webId = arr[1];
        this.category = Category.getCategory(arr[2].toLowerCase());
        this.year = Year.parse(arr[3]);
        this.make = arr[4];
        this.model = arr[5];
        this.trim = arr[6];
        this.type = arr[7];
        this.price = Double.parseDouble(arr[8]);
        try {
            this.photo = new URL(arr[9]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}

enum Category{
    NEW , USED, CERTIFIED;

    public static Category getCategory(String cat){
        switch (cat){
            case "used": return USED;
            case "new": return NEW;
            case "certified": return CERTIFIED;
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        switch (this){
            case NEW: return "new";
            case USED: return "used";
            case CERTIFIED: return "certified";
            default: throw new IllegalArgumentException();
        }
    }
}
