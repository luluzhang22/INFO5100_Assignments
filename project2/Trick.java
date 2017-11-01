
public class Trick extends GroupOfCards {
    private int winner;

    private Card winningCard;

    private boolean hearts = false;

    private boolean queen = false;

    /**
     * call the superclass’s constructor with one less than twice parameter,
     * to allow room in the first trick for undelt cards
     * @param players: number of players
     */
    Trick(int players) {
        super(2*players - 1);
    }

    public int getWinner() {
        return winner;
    }

    public Card getWinningCard() {
        return winningCard;
    }

    public boolean getHearts() {
        return hearts;
    }

    public boolean getQueen() {
        return queen;
    }

    /**
     * If the current card is the winner, set winner equal to current player’s number
     * and set the winning card equal to the current card.
     * If the current card is a heart, set hearts to true.
     * If the current card is the queen of spades, set queen to true.
     * @param playerNum
     * @param card
     */
    public void update(int playerNum, Card card){
        addCard(card);
        if(isWinner(card)){
            winner = playerNum;
            winningCard = card;
        }
        if(!hearts && card.getSuit() == Card.HEARTS)
            hearts = true;
        else if(!queen && card.getNum() == Card.QUEEN && card.getSuit() == Card.SPADES)
            queen = true;
    }

    /**
     * should return true unless the previous winning card is not null
     * and the current card is not in the suit being played
     * or its number is less than the winning card’s number
     * @param card
     * @return
     */
    private boolean isWinner(Card card){
        if(winningCard!=null&&
                (card.getSuit()!=winningCard.getSuit()
                        || card.getNum() < winningCard.getNum()))
            return false;
        return true;
    }
}
