import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String inBuffer;
        ServerConnection serverConnection = new ServerConnection();
        Game game;

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

        game = new Game(serverConnection);

        //Client
        String response = serverConnection.sendMessage("Message From Client");
        System.out.println("Response From Server: " + response);


        if(Server.getSingleton() != null){
            Server.stopServer();
        }
        serverConnection.stopConnection();
        System.out.println("Game Stopped");
    }
}