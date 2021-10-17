import java.util.ArrayList;

/**
 * this class is going to combine
 * chip manager, board manager and
 * dealer into a header class to run the game
 * because i am super duper awesome
 * jk it was tylers idea
 *
 * @author drew
 */

public class GameManager {

    private boolean playing;

    private Dealer dealer;
    private BoardManager board;
    private ChipManager chips;

    private ArrayList<ClientConnection> clientList;

    public GameManager(ArrayList<ClientConnection> clientList) {
        this.clientList = clientList;
        dealer = new Dealer();
        board = new BoardManager();
        chips = new ChipManager();
    }

    public void start() {

    }

    public void nextTurn() {

    }

    public void dealCards() {
        for(ClientConnection client : clientList) {
            client.dealCards(dealer,2);
        }
    }

    public void bet() {

    }

    public void roundOver() {
        for(ClientConnection client : clientList) {
            client.sendRoundOver();
        }
    }


}
