public class Game {

    private ServerConnection serverConnection;
    private boolean playing;

    public Game(ServerConnection serverConnection){
        this.serverConnection = serverConnection;
        playing = true;
    }

    public void startGame(){
        while(playing){
            //Game loop
        }
    }
}
