
public class MyIndexOutOfBoundException extends Exception{ // score 2
    private int lowerBound;
    private int upperBound;
    private int index;

    MyIndexOutOfBoundException(int lowerBound, int upperBound, int index){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.index = index;
    }

    public String toString(){
        return "Error Message: Index: " + index + ", but lower bound: " + lowerBound + ", Upper bound:" + upperBound;
    }
}
