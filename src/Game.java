/**
 * Gameloop to play a single turn
 * on a players side, weather thats
 * fold, call, check, or raise.
 */

public class Game {

    private ServerConnection serverConnection;
    private boolean playing;

    private Player player;

    public Game(ServerConnection serverConnection){
        this.serverConnection = serverConnection;
        playing = true;
        player = new Player(1000);
    }

    public void startGame(){
        while(playing) {
            //Game loop
        }
    }
}
