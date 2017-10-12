
public class Cookie extends DessertItem {
    private int number;

    private int pricePerDozen;

    Cookie(String name, int number, int pricePerDozen){
        super(name);
        this.number = number;
        this.pricePerDozen = pricePerDozen;
    }

    @Override
    public int getCost() {
        return (int)Math.round(number*pricePerDozen/12.0);
    }

    @Override
    public String toString(){
        return number + " @ " + DessertShoppe.cents2dollarsAndCents(pricePerDozen) + " /dz.\n";
    }
}
