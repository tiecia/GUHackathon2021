import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String inBuffer;
        Game game;
        ServerConnection serverConnection = new ServerConnection();

        System.out.println("Type your name: ");
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
        System.out.println("Main on thread " + Thread.currentThread());

        serverConnection.setPlayerName(name);

        //game = new Game(serverConnection);

        //Client
        serverConnection.sendMessage("hand diamond 3 heart 11");
        serverConnection.sendMessage("besthand fh diamond 3 heart 4 diamond 3 heart 11 spade 9");
        serverConnection.sendMessage("bet 6");



        if(Server.getSingleton() != null){
            Server.stopServer();
        }
        serverConnection.stopConnection();
        System.out.println("Game Stopped");
    }
}