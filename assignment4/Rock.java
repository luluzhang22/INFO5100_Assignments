
public class Rock extends Tool {

    Rock(int strength){
        setStrength(strength);
        setType('r');
    }

    public boolean fight(Tool tool){
        if(tool.getType() == 's')
            return 2*getStrength()>tool.getStrength();
        else
            return getStrength()>2*tool.getStrength();
    }
}
