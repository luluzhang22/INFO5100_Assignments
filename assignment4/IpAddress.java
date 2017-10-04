
public class IpAddress {
    private String dottedDecimal;
    private int firstOctet;
    private int secondOctet;
    private int thirdOctet;
    private int fourthOctet;

    IpAddress(String dottedDecimal){
        this.dottedDecimal = dottedDecimal;
        String[] octets = dottedDecimal.split("\\.");
        firstOctet = Integer.parseInt(octets[0]);
        secondOctet = Integer.parseInt(octets[1]);
        thirdOctet = Integer.parseInt(octets[2]);
        fourthOctet = Integer.parseInt(octets[3]);
    }

    public String getDottedDecimal() {
        return dottedDecimal;
    }

    public int getOctet(int position){
        switch (position){
            case 1:
                return firstOctet;
            case 2:
                return secondOctet;
            case 3:
                return thirdOctet;
            case 4:
                return fourthOctet;
            default:
                System.out.println("please input right position between 1 and 4");
                return -1;
        }
    }
}
