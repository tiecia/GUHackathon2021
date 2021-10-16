public class Dealer {
    private Deck deck;

    public Dealer() {
        deck = new Deck();
    }

    /**
     * takes a card from the deck and gives
     * it away like this is a communist
     * society. do you think computer
     * chips have governments
     * @return
     */
    public Card dealCard() {
        return deck.draw();
    }

    /**
     * gives a new deck so cardss
     * dont need to be returned
     */
    public void newRound() {
        deck.newDeck();
    }

    /**
     * shuffles the current deck
     */
    public void shuffleDeck() {
        deck.shuffle();
    }

}
