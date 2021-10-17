import java.util.ArrayList;

/**
 * Does the basic work of a board. Only manages the cards
 * currently on the board, thats it.
 * we scrapped the old one cuz drew is dumb
 *
 * i am drew
 * i can confirm it was not smart
 *
 * @author drew
 *
 */
public class BoardManager {

    private ArrayList<Card> currentBoard;

    /**
     * CAOSgkajsdkg;jla's;lkdg
     * We love constructors
     * how do you comment these things?
     * like hey
     * this constrcuts stuff
     * yeahhhhh
     */
    public BoardManager() {
        newRound();
    }

    /**
     * deals the card from the dealer object into the board
     * @param dealer the dealer object from server
     * @param num the number of cards needing dealing
     */
    public void dealCards(Dealer dealer, int num) {
        dealer.dealCard();
        for(int count = 0; count < num; ++count) {
            currentBoard.add(dealer.dealCard());
        }
    }

    /**
     * sets current to a new list of cards to wipe board
     */
    public void newRound() {
        currentBoard = new ArrayList<Card>();
    }

}
