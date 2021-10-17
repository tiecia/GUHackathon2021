import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String inBuffer;
        Game game = null;
        ServerConnection serverConnection = new ServerConnection();
        game = new Game(serverConnection);
        serverConnection.setGame(game);

        System.out.println("Type your name (with no space): ");
        String name = in.nextLine();
        if(name.length() == 0) {
            name = "Dummy";
        }

        System.out.println("Host a server? (y/n)");
        inBuffer = in.nextLine();

        if (inBuffer.equals("y")) {
            Server.start(4444);
            serverConnection.startConnection("127.0.0.1", 4444);
        } else if (inBuffer.equals("n")) {
            System.out.println("Please type the IP Address of the host computer: ");
            inBuffer = in.nextLine();
            serverConnection.startConnection(inBuffer, 4444);
        }
        //System.out.println("Main on thread " + Thread.currentThread());
        serverConnection.setPlayerName(name);

        if(Server.getSingleton() != null){
            System.out.println("Once all players have joined, press enter to start game");
            Scanner s = new Scanner(System.in);
            s.nextLine();
            Server.getSingleton().startGame();
        }

        while(true){

        }

//        if(Server.getSingleton() != null){
//            Server.stopServer();
//        }
//        serverConnection.stopConnection();
//        System.out.println("Game Stopped");
    }
}