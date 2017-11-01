
public class Game {
    public final int PLAYERS;

    private Deck deck = new Deck();

    private Hand[] players;

    private Trick[] tricks;

    private int numberOfTricks = 0;

    private boolean hearts = false;

    private boolean queenOfSpades = false;

    Game(int numberOfPlayers){
        PLAYERS = numberOfPlayers;
        players  = new Hand[numberOfPlayers];
        int numberOfCard = deck.TOTAL_CARDS/numberOfPlayers;

        for(int i = 0; i < numberOfPlayers; i ++){
            players[i] = new Hand(i,numberOfCard);
        }
        tricks = new Trick[numberOfCard];
    }

    public int getNumberOfTricks() {
        return numberOfTricks;
    }

    public boolean getHearts() {
        return hearts;
    }

    public boolean getQueenOfSpades() {
        return queenOfSpades;
    }

    public void playAGame(){
        //shuffle cards
        deck.shuffle();
        int cardsLeft = deck.TOTAL_CARDS % PLAYERS;
        if(cardsLeft > 0){
            deck.validateCardsLeft(cardsLeft);
        }

        //deal card
        for(int i = 0; i < tricks.length; i++){
            for(Hand player : players){
                player.addCard(deck.dealCard());
            }
        }

        //find the identification number of the player having the lowest club
        int playerNum = 0;
        int min = Integer.MAX_VALUE;
        int index = tricks.length -1;

        //sort each hand and find each player's best void opportunity
        System.out.println("Output - first part:\n");
        for(Hand player : players){
            player.sort();
            player.setShortest();
            Card temp = player.getCard(index);

            if(temp.getSuit() == Card.CLUBS && temp.getNum() < min){
                min = temp.getNum();
                playerNum = player.NUM;
            }
            player.display();
        }

        //play a card
        System.out.println("\n\nOutput - second part:\n");
        Card card;
        while (numberOfTricks < tricks.length){
            Trick trick = new Trick(PLAYERS);
            Hand player = players[playerNum];
            if(numberOfTricks == 0){
                card = player.removeCard(index);
                trick.update(playerNum,card);
            } else{
                card = player.playACard(this, trick);
            }
            System.out.print("player "+playerNum+"        ");
            card.display();

            for(int j = 1; j < PLAYERS; j++){
                playerNum = (playerNum + 1)%PLAYERS;
                player = players[playerNum];
                card = player.playACard(this,trick);
                System.out.print("player " + playerNum + "        ");
                card.display();
            }
            playerNum = trick.getWinner();

            //add undelt cards
            if (numberOfTricks == 0){
                while (deck.getCurrentSize()>0) {
                    card = deck.dealCard();
                    trick.addCard(card);
                    System.out.print("undelt card     ");
                    card.display();
                }
            }
            System.out.println();

            tricks[numberOfTricks++] = trick;
        }

        for(int i = 0; i < PLAYERS; i++){
            System.out.println("Player "+ i + " score = " + computePoints(i));
        }
    }

    public void updateHeartsAndQueen(Card card){
        int suit = card.getSuit();
        if(!hearts){
            if(suit == Card.HEARTS){
                System.out.println("Hearts is now broken!");
                hearts = true;
            }
        }
        if(!queenOfSpades) {
            if (suit == Card.SPADES && card.getNum() == Card.QUEEN) {
                queenOfSpades = true;
            }
        }
    }

    private int computePoints(int playerNum){
        int point = 0;
        for(Trick trick : tricks){
            if(trick.getWinner() == playerNum){
                for(int i = 0; i < trick.getCurrentSize(); i++){
                    if(trick.getCard(i).getSuit() == Card.HEARTS) {
                        point++;
                    }else if(trick.getCard(i).getNum() == Card.QUEEN
                        && trick.getCard(i).getSuit() == Card.SPADES){
                        point += 13;
                    }
                }
            }
        }
        return point;
    }
}
