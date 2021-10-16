public class Dealer {
    private Deck deck;

    public Dealer() {
        deck = new Deck();
    }

    public Card dealCard() {
        return deck.draw();
    }

    public void newRound() {
        deck.newDeck();
    }

    public void shuffleDeck() {
        deck.shuffle();
    }

}
