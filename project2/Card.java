
public class Card {
    private int num;

    private int suit;

    //suit scope
    public static final int CLUBS = 0;

    public static final int DIAMONDS = 1;

    public static final int HEARTS = 2;

    public static final int SPADES = 3;

    //number scope
    public static final int NUM_SCOPE = 13;
    public static final int START_NUM = 2;

    public static final int JACK = 11;

    public static final int QUEEN = 12;

    public static final int KING = 13;

    public static final int ACE = 14;

    Card(int num, int suit){
        this.num = num;
        this.suit = suit;
    }

    public int getNum() {
        return num;
    }

    public int getSuit() {
        return suit;
    }

    public void display(){
        switch (num){
            case JACK:
                System.out.print("Jack");
                break;
            case QUEEN:
                System.out.print("Queen");
                break;
            case KING:
                System.out.print("King");
                break;
            case ACE:
                System.out.print("Ace");
                break;
            default:
                System.out.print(num);
        }

        System.out.print(" of ");
        switch (suit){
            case CLUBS:
                System.out.println("clubs");
                break;
            case DIAMONDS:
                System.out.println("diamonds");
                break;
            case HEARTS:
                System.out.println("hearts");
                break;
            case SPADES:
                System.out.println("spades");
                break;
                default:
                    System.out.println("Wrong suit number -> " + suit);
        }
    }
}
