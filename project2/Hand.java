
public class Hand extends GroupOfCards{
    public final int NUM;

    private int shortest = 0;

    /**
     * constructor
     * @param playerNum: player's identification
     * @param numberOfCards: maximum number of cards the player will receive
     */
    Hand(int playerNum, int numberOfCards){
        super(numberOfCards);
        NUM = playerNum;
    }

    /**
     * selection sort by (13 * suit + number)
     * the cards should be sorted by suit from Ace of spades down to 2 of clubs
     */
    public void sort(){
        int unsorted = getCurrentSize();
        for(;unsorted>0;unsorted--){
            int maxIndex = unsorted - 1;
            int maxValue = Card.NUM_SCOPE * getCard(maxIndex).getSuit() + getCard(maxIndex).getNum();
            for(int i = 0; i < unsorted - 1; i ++){
                int cur = Card.NUM_SCOPE * getCard(i).getSuit() + getCard(i).getNum();
                if( cur > maxValue){
                    maxIndex = i;
                    maxValue = cur;
                }
            }
            addCard(removeCard(maxIndex));
        }
    }

    public int getShortest() {
        return shortest;
    }

    /**
     * determine the best suit to play early in the game, to establish a void as quickly as possible
     * Start with shortest = clubs.
     * If the number of diamonds is less than or equal to the number of clubs, change shortest to diamonds.
     * If the number of spades is less than or equal to the shorter of those two,
     * and your spades do not include Ace, King, or Queen, change shortest to spades.
     */
    public void setShortest() {
        int count = count(shortest) == 0 ? Integer.MAX_VALUE : count(shortest);
        int curCount = count(Card.DIAMONDS);
        if(curCount > 0 && curCount <= count){
            count = curCount;
            shortest = Card.DIAMONDS;
        }
        curCount = count(Card.SPADES);
        if(curCount > 0 &&  curCount <= count
                && find(Card.ACE,Card.SPADES) == -1
                && find(Card.KING,Card.SPADES) == -1
                && find(Card.QUEEN, Card.SPADES) == -1) {
            shortest = Card.SPADES;
        }
    }

    /**
     * choose a card in different situation: 1.first hand -> highest card in shortest suit , or lowest card in any suit
     * 2.last hand -> if the trick does not have bad cards, then find highest card in suit
     * 3.last hand with bad cards, or middle hand -> find highest below or middle high
     * 4.your are void -> able to discard a bad card or your own
     * @param game: current game
     * @param trick: current trick
     * @return
     */
    public Card playACard(Game game, Trick trick){
        int size = trick.getCurrentSize();
        int index;
        //first hand
        if(size == 0){
            //highest card in shortest suit
            if((index = findHighest(shortest)) == -1) {
                //have developed a void, find lowest card in any suit unless hearts when hearts have not been broken
                if ((index = findLowest(game)) == -1) {
                    //only hearts left and hearts have not been broken
                    index = findLowest(Card.HEARTS);
                }
            }
        }
        //last hand
        else if(size == game.PLAYERS-1
                && !trick.getHearts() && !trick.getQueen()
                && (index = findLastHigh(trick.getWinningCard().getSuit())) >= 0);
        //middle hand
        else if((index = findHighestBelow(trick.getWinningCard())) >= 0);
        else if((index = findMiddleHigh(game, trick.getWinningCard().getSuit())) >= 0);
        //you are void
        else if((index = find(Card.QUEEN, Card.SPADES)) >= 0);
        else if((index = find(Card.ACE, Card.SPADES)) >= 0);
        else if((index = find(Card.KING, Card.SPADES)) >= 0);
        else if((index = findHighest(Card.HEARTS)) >= 0);
        else{
            index = findHighest();
        }

        Card card = removeCard(index);
        trick.update(NUM, card);
        game.updateHeartsAndQueen(card);
        return card;
    }

    /**
     * return the index of the lowest numbered card in the indicated suit
     * two situation invoke this method: 1. find the lowest club dealt, to start the game
     * 2. when you have the lead, hearts have not been broken, and hearts are all you have left in your hand
     * @param suit
     * @return
     */
    public int findLowest(int suit){
        for(int i = getCurrentSize()-1; i >= 0; i--){
            if(getCard(i).getSuit() == suit)
                return i;
            if(getCard(i).getSuit()>suit)
                break;
        }
        return -1;
    }

    private int count(int suit){
        int count = 0;
        for(int i = getCurrentSize() - 1; i >= 0; i --){
            int curSuit = getCard(i).getSuit();
            if(curSuit == suit)
                count++;
            else if(curSuit > suit)
                break;
        }
        return count;
    }

    private int find(int num, int suit){
        for(int i = getCurrentSize() - 1; i >= 0; i --){
            Card card = getCard(i);
            if(card.getSuit() == suit && card.getNum() == num)
                return i;
            if(card.getSuit() > suit)
                break;
        }
        return -1;
    }

    /**
     * return the index of the card having the highest numerical value in the suit
     * 1. first hand: find the highest card in shortest suit to develop a void as fast as possible early int the game
     * 2. select the highest heart to discard on somebody else's suit
     * 3. last hand && no bad cards
     * @param suit
     * @return
     */
    private int findHighest(int suit){
        for(int i = 0; i < getCurrentSize(); i ++){
            if(getCard(i).getSuit() == suit)
                return i;
            if(getCard(i).getSuit() < suit)
                break;
        }
        return -1;
    }

    /**
     * when leading, after you have developed your void.
     * Return the lowest number in your hand, but not a heart until after hearts have been broken.
     * If hearts have not been broken and all you have left is hearts, return -1.
     * @param game
     * @return
     */
    private int findLowest(Game game){
        int lowest = -1;
        int min = Integer.MAX_VALUE;
        boolean isBroken = game.getHearts();
        for(int i = getCurrentSize()-1; i >= 0; i--){
            Card card = getCard(i);
            if(card.getSuit() == Card.HEARTS && !isBroken)
                continue;
            if(card.getNum() < min) {
                min = card.getNum();
                lowest = i;
            }
        }
        return lowest;
    }

    /**
     * return the highest card in the suit led when there are no bad cards
     * if this card is the queen of spades, and you have another spade, return the highest card you have below your queen
     * @param suit
     * @return
     */
    private int findLastHigh(int suit){
        int index = findHighest(suit);
        if(index != -1){
            Card card = getCard(index);
            if(suit == Card.SPADES && card.getNum() == Card.QUEEN
                    && index < getCurrentSize()-1 && getCard(index+1).getSuit() == suit){
                return index+1;
            }
        }
        return index;
    }

    /**
     * middle hand
     * find the first one equals the winning card's suit and haves a number less than the winning card's number
     * @param winningCard
     * @return
     */
    private int findHighestBelow(Card winningCard){
        int suit = winningCard.getSuit();
        int number = winningCard.getNum();
        for(int i = 0; i < getCurrentSize(); i ++){
            Card card = getCard(i);
            if(card.getSuit() < suit)
                break;
            if(card.getSuit() == suit && card.getNum() < number) {
                return i;
            }
        }
        return -1;
    }

    /**
     * if the findHighestBelow method returned -1, find the highest card in the suit
     * but if the suit is spades and the queen of spades has not been played yet,
     * try to find a spade that is not higher than the Jack of spades
     * @param game
     * @param suit
     * @return
     */
    private int findMiddleHigh(Game game, int suit){
        int index = findHighest(suit);
        if(index >= 0 && !game.getQueenOfSpades() && suit == Card.SPADES && getCard(index).getNum() > Card.JACK){
            for(int i = index + 1; i < getCurrentSize(); i++){
                if(getCard(i).getSuit()<suit)
                    break;
                if(getCard(i).getNum() < Card.QUEEN)
                    index = i;
            }
        }
        return index;
    }

    /**
     * if you cannot follow suit and you no longer have the Queen, Ace, or King of spades and no longer have any hearts,
     * discard the highest remaining card in your hand, regardless of suit
     * @return
     */
    private int findHighest(){
        int index = 0;
        int highest = getCard(index).getNum();
        for(int i = 1; i < getCurrentSize(); i++){
            if(getCard(i).getNum() > highest) {
                index = i;
                highest = getCard(index).getNum();
            }
        }
        return index;
    }

    @Override
    public void display(){
        System.out.println("        player " + NUM + " shortest = "+shortest);
        super.display();
    }
}
