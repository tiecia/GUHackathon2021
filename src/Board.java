import java.util.ArrayList;

/**
 * starts to combine the use of multiple classes into a more central
 * main class. this class acts as the logic of the table. with the methods
 * and will run a round of the game when told.
 *
 *
 *                                             OBSOLETE CLASS NOW! Moved board logic to board manager to ease server/client connection
 *                                             Kept for refrence purpose
 */

public class Board {

    private Deck deckOfCards;
    private ArrayList<Player> players;

    private ArrayList<Card> currentBoard;
    private ArrayList<Card> burned;

    private int pot;

    /**
     * Board constructor
     * @param playerList list of created player objects playing with
     *                   correct naming
     */
    public Board(ArrayList<Player> playerList) {
        deckOfCards = new Deck();
        players = playerList;
        currentBoard = new ArrayList<>();
        burned = new ArrayList<>();
        pot = 0;
    }

    /**
     * Draws the flop from the board while also
     * burning the starting card.
     */
    public void drawFlop() {
        burn();
        for(int count = 0; count < 3; ++count) {
            currentBoard.add(deckOfCards.draw());
        }
    }

    /**
     * draws either the turn or the river again
     * starting with a burn
     */
    public void drawTurnRiver() {
        burn();
        currentBoard.add(deckOfCards.draw());
    }

    /**
     * draws 2 cards for each player, but one card per then
     * another round drawing one per. this is too keep
     * true casino random.
     */
    public void drawPlayer() {
        for(int count = 0; count < 2; ++count) {
            for (Player player : players) {
                player.receiveCard(deckOfCards.draw());
            }
        }
    }

    /**
     * burns a card that will not be used or known
     * during the playing of the game.
     * but not actually. cuz that would be bad
     */
    private void burn() {
        burned.add(deckOfCards.draw());
    }

    /**
     * adds the money from the player to the pot. or doesnt
     * not my descision
     * @param player the player money is being taken from
     * @param playerBet money added to pot
     */
    public boolean addPot(Player player, int playerBet) {
        if(player.bet(playerBet)) {
            pot += playerBet;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * gives the pot to the winner. obviously?
     * comon. dont you know poker?
     * @param player the winner of the current pot
     */
    public void givePot(Player player) {
        player.givePot(pot);
    }

}
