import java.util.ArrayList;

/**
 * Gameloop to play a single turn
 * on a players side, weather thats
 * fold, call, check, or raise.
 */

public class Game {

    private ServerConnection serverConnection;
    private boolean playing;

    private Player player;

    private int needToBet;

    public Game(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
        playing = true;
        player = new Player(1000);
    }

    public void startGame() {
        while(playing) {

        }
    }

    public void newBetStatus(int bet) {
        needToBet = bet;
    }

    public void packetReceived() {
        System.out.println("Current Board: ");
    }

    public void hand(ArrayList<Card> hand) {
        for(Card card : hand) {
            player.receiveCard(card);
        }
    }

}
