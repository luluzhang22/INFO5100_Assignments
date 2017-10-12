
public class IceCream extends DessertItem{

    private int cost;

    IceCream(String name, int cost){
        super(name);
        this.cost = cost;
    }

    @Override
    public int getCost() {
        return cost;
    }
}
