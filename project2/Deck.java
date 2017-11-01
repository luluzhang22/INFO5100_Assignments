
public class Deck extends GroupOfCards{
    public static final int TOTAL_CARDS = 52;

    Deck(){
        super(TOTAL_CARDS);
        for(int i = Card.CLUBS; i <= Card.SPADES; i++){
            for(int j = Card.START_NUM; j <= Card.ACE; j++){
                addCard(new Card(j,i));
            }
        }
    }

    public void shuffle(){
        int unshuffled = getCurrentSize();
        for(;unshuffled>0;unshuffled--){
            Card card= removeCard((int) (Math.random()*unshuffled));
            addCard(card);
        }
    }

    public Card dealCard(){
        return removeCard(0);
    }

    /**
     * make sure the undelt cards are not bad cards
     * @param cardsLeft
     */
    public void validateCardsLeft(int cardsLeft){
        int num = TOTAL_CARDS - cardsLeft;
        for(int i = cardsLeft; i >= 1; i--){
            int index = num;
            Card card = getCard(index);
            while (card.getSuit() == Card.HEARTS
                    || (card.getNum() == Card.QUEEN && card.getSuit() == Card.SPADES)){
                index = (int)(Math.random()*num);
                card = getCard(index);
            }
            addCard(removeCard(index));
        }
    }
}
