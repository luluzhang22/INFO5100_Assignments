
public class Candy extends DessertItem {
    private double weight;

    private int pricePerPound;

    Candy(String name, double weight, int pricePerPound){
        super(name);
        this.weight = weight;
        this.pricePerPound = pricePerPound;
    }

    @Override
    public int getCost() {
        return (int)Math.round(weight*pricePerPound);
    }

    @Override
    public String toString(){
        return weight + " lbs. @ " + DessertShoppe.cents2dollarsAndCents(pricePerPound) + " /lb.\n";
    }
}
