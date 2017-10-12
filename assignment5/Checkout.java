
import java.util.ArrayList;

public class Checkout {
    private ArrayList<DessertItem> dessertItems;

    public Checkout(){
        dessertItems = new ArrayList<>();
    }
    public void enterItem(DessertItem item){
        dessertItems.add(item);
    }

    public void clear(){
        dessertItems = new ArrayList<>();
    }

    public int numberOfItems(){
        return dessertItems.size();
    }

    public int totalCost(){
        int total = 0;
        for(DessertItem item : dessertItems){
            total += item.getCost();
        }
        return total;
    }

    public int totalTax(){
        return (int)Math.round(totalCost()*DessertShoppe.taxRate/100.00);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        //title
        sb.append(DessertShoppe.formatTitle());

        //items
        for(DessertItem item : dessertItems){
            sb.append(item);
            sb.append(DessertShoppe.formatDisplay(item.getName(),item.getCost()));
        }

        //tax
        sb.append("\n"+DessertShoppe.formatDisplay("Tax",totalTax()));
        sb.append(DessertShoppe.formatDisplay("Total Cost",totalCost()+totalTax()));
        return sb.toString();
    }
}
