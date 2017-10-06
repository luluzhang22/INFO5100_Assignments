
public class Paper extends Tool {
    Paper(int strength){
        setStrength(strength);
        setType('p');
    }

    public boolean fight(Tool tool){
        if(tool.getType() == 'r')
            return 2*getStrength() > tool.getStrength();
        else
            return getStrength() > 2*tool.getStrength();
    }
}
