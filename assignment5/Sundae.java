
public class Sundae extends IceCream{
    private int costOfTopping;

    Sundae(String nameOfIceCream, int costOfIceCream, String nameOfTopping, int costOfTopping){
        super(nameOfTopping+" Sundae with "+ nameOfIceCream ,costOfIceCream);
        this.costOfTopping = costOfTopping;
    }

    @Override
    public int getCost(){
        return super.getCost()+costOfTopping;
    }
}
