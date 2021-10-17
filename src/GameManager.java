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
    private boolean betMade;
    private int currentTurn;

    private Dealer dealer;
    private BoardManager board;
    private ChipManager chips;

    private ArrayList<ClientConnection> clientList;

    public GameManager(ArrayList<ClientConnection> clientList) {
        this.clientList = clientList;
        dealer = new Dealer();
        board = new BoardManager();
        chips = new ChipManager();
        playing = true;
        dealCards();
        board.dealCards(dealer, 3);
        start();
    }

    public void start() {
        clientList.get(0).yourTurn(board.getCurrentBoard());
        currentTurn = 0;
        betMade = false;
        while(playing) {
            if(board.getCards() == 5) {
                playing = false;
                decideWinner();
            }
        }
    }

    public void nextTurn() {
        if(currentTurn + 1 < clientList.size()) {
            clientList.get(currentTurn + 1).yourTurn(board.getCurrentBoard());
        }
        else {
            if(!betMade) {
                board.dealCards(dealer,1);
                start();
            }
            else {
                betMade = false;
                currentTurn = 0;
            }
        }
    }

    public void dealCards() {
        for(ClientConnection client : clientList) {
            client.dealCards(dealer,2);
        }
    }

    public void bet(String name, int amount) {
        for(ClientConnection client : clientList) {
            if(!client.getName().equals(name)) {
                client.bet(amount);
            }
        }
        betMade = true;
    }

    public void roundOver() {
        for(ClientConnection client : clientList) {
            client.sendRoundOver(client.getName());
        }
    }

    public void gameOver() {
        for(ClientConnection client : clientList) {
            client.sendGameOver(board.getCurrentBoard());
        }
    }

    public void decideWinner() {

    }




}
