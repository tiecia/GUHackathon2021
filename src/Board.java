import java.util.ArrayList;

/**
 * starts to combine the use of multiple classes into a more central
 * main class. this class acts as the logic of the table. with the methods
 * and will run a round of the game when told.
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

    }

    /**
     * returns the cards back to the deck that were
     * taken throughout the game.
     */
    private void returnCards() {
        while(currentBoard.size() != 0) {
            deckOfCards.cardsBack(currentBoard.remove(0));
        }
        while(burned.size() != 0) {
            deckOfCards.cardsBack(burned.remove(0));
        }
        for(Player player : players) {
            while(player.getHand().size() != 0) {
                deckOfCards.cardsBack(player.getHand().remove(0));
            }
        }
    }

    /**
     * Draws the flop from the board while also
     * burning the starting card.
     */
    private void drawFlop() {
        burn();
        for(int count = 0; count < 3; ++count) {
            currentBoard.add(deckOfCards.draw());
        }
    }

    /**
     * draws either the turn or the river again
     * starting with a burn
     */
    private void drawTurnRiver() {
        burn();
        currentBoard.add(deckOfCards.draw());
    }

    /**
     * draws 2 cards for each player, but one card per then
     * another round drawing one per. this is too keep
     * true casino random.
     */
    private void drawPlayer() {
        for(int count = 0; count < 2; ++count) {
            for (Player player : players) {
                player.receiveCard(deckOfCards.draw());
            }
        }
    }

    /**
     * burns a card that will not be used or known
     * during the playing of the game.
     */
    private void burn() {
        burned.add(deckOfCards.draw());
    }

    private void addPot(Player player, int playerBet) {

    }

}
