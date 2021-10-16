import java.util.ArrayList;
import java.util.Collections;

/**
 * Combines the cards into a standard deck to
 * branch functionality away from a central class
 *
 * @author drew
 */

public class Deck {

    private ArrayList<Card> deck;

    /**
     * CONSTRUCTORRRRRRRRRRRRRRSA
     * EFGAS;'KDJL'SF;GBLKAS
     * FG;'LKASJ;LDGKJASK
     * DGL;JKAS'DGL;
     * Ka
     * why do i still comment
     */
    public Deck() {
        createDeck();
    }

    /**
     * creates the deck with a nested for loop
     * utalizes binary booleans to change suit or color
     * puts all cards into a class deck.
     */
    private void createDeck() {
        deck = new ArrayList<>();

        boolean color = true;
        boolean suit = true;
        // color and suit work as binary operators on the card
        // true on suit is either clubs or diamonds depending on color
        // false is either spades or hearts depending on color
        for (int colorCount = 0; colorCount < 4; ++colorCount) {
            for (int cardCount = 0; cardCount < 13; ++cardCount) {
                deck.add(new Card(cardCount, color, suit));
            }
            suit = !suit;
            if (colorCount == 1) {
                color = !color;
            }
        }
    }

    /**
     * Shuffles the deck
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * @return removes a card from the deck and gives it to the caller
     */
    public Card draw() {
        return deck.remove(0);
    }

    /**
     * Cards returned from user and put back into the deck to reduce
     * the amount of recreation of cards needed as the game is played
     * @param card the card given back after a round
     */
    public void cardsBack(Card card) {
        deck.add(card);
    }


}
