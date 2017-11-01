
public class GroupOfCards {
    private Card[] cards;

    private int currentSize = 0;

    GroupOfCards(int num){
        cards = new Card[num];
    }

    public Card getCard(int i) {
        return cards[i];
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void addCard(Card card){
        if(currentSize == cards.length){
            System.out.println("Wrong: Can not add card! Current size -> "+currentSize);
        }
        cards[currentSize++] = card;
    }

    public Card removeCard(int index){
        if(index < 0 || index >= currentSize){
            System.out.println("Wrong: Can not remove a card! Index -> " + index +", current size -> " + currentSize);
        }
        Card card = cards[index];
        currentSize--;
        for(int i = index; i < currentSize; i ++){
            cards[i] = cards[i+1];
        }
        cards[currentSize] = null;
        return card;
    }

    public void display(){
        for (Card c : cards)
            c.display();
    }
}
