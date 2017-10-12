

public class DessertShoppe {
    public static final double taxRate = 6.5;

    public static final String shopName = "M & M Dessert Shoppe";

    public static final int maxSizeOfItemName = 25;

    public static final int widthOfDisplayCosts = 35;

    public static String cents2dollarsAndCents (int cents){
        double result = cents/100.00;
        String s = result+"";
        if(result<1)
            return s.substring(1);
        else
            return s;
    }

    public static String formatName(String name){
        if(name.length()>maxSizeOfItemName){
            for(int i = maxSizeOfItemName-1; i >= 0;i--){
                if(name.charAt(i) == ' ')
                    return name.substring(0,i)+"\n"+formatName(name.substring(i+1));
            }
            return name.substring(0,maxSizeOfItemName-1) + "-\n" + formatName(name.substring(maxSizeOfItemName-1));
        }else {
            StringBuilder sb = new StringBuilder(name);
            int i = maxSizeOfItemName - name.length();
            sb.append(addBlank(i));
            return sb.toString();
        }
    }

    public static String formatDisplay(String name, int cost){
        StringBuilder sb = new StringBuilder(formatName(name));
        String dollarsAndCents = cents2dollarsAndCents(cost);
        int diff = widthOfDisplayCosts - maxSizeOfItemName - dollarsAndCents.length();
        sb.append(addBlank(diff));
        sb.append(dollarsAndCents+"\n");
        return sb.toString();
    }

    public static String formatTitle(){
        StringBuilder sb = new StringBuilder();
        int diff = (widthOfDisplayCosts - shopName.length())/2;
        sb.append(addBlank(diff));
        sb.append(shopName+"\n");
        sb.append(addBlank(diff));
        for(int i = 0; i < shopName.length(); i++){
            sb.append("-");
        }
        sb.append("\n\n");
        return sb.toString();
    }

    private static String addBlank(int n){
        StringBuilder sb = new StringBuilder();
        for (;n>0;n--){
            sb.append(" ");
        }
        return sb.toString();
    }
}
