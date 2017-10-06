
public class Scissors extends Tool {
    Scissors(int strength){
        setStrength(strength);
        setType('s');
    }

    public boolean fight(Tool tool){
        if(tool.getType() == 'r')
            return getStrength()>2*tool.getStrength();
        else
            return 2*getStrength()>tool.getStrength();
    }
}
