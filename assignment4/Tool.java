
public class Tool { // score 2
    private int strength;

    private char type;

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public boolean fight(Tool tool){

        return this.strength>tool.getStrength();
        /*char aType = this.type;
        int aStrength = this.strength;
        char bType = tool.getType();
        int bStrength = tool.getStrength();

        if((aType == 'r' && bType == 's')
                || (aType == 's' && bType == 'p')
                || (aType == 'p' && bType == 'r'))
            return 2*aStrength > bStrength;
        else if((aType == 'r' && bType == 'p')
                || (aType == 's' && bType == 'r')
                || (aType == 'p' && bType == 's'))
            return aStrength > 2*bStrength;
        else
            return aStrength > bStrength;*/
    }
}
