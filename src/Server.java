import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server extends Thread {
    private static Server singleton;
    private static int port;
    private static boolean acceptingConnections = true;

    private ServerSocket serverSocket;

    private ArrayList<ClientConnection> clients;

    public static void start(int portInput) throws IOException {
        if (singleton == null) {
            port = portInput;
            singleton = new Server();
            singleton.start();
        }
    }

    public static Server getSingleton(){
        return singleton;
    }

    public Server() throws IOException {
        serverSocket = new ServerSocket(port);
        clients = new ArrayList<>();
    }

    public void run() {
        try {
            System.out.print("Server started...");
            while (acceptingConnections) {
                System.out.println("waiting for connection...");
                ClientConnection connection = new ClientConnection(this, serverSocket.accept(), 4444);
                connection.start();
                clients.add(connection);
                System.out.println("Client added");
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGame(){
        GameManager.startGame(clients);
        for (ClientConnection client: clients) {
            client.startGame(client.getName());
        }
    }

    public static void stopServer(){
        acceptingConnections = false;
    }

    public void giveBestHand(ClientConnection player, ArrayList<Card> hand){
        System.out.println("Best hand given " + hand);
    }

    public void giveHand(ClientConnection player, ArrayList<Card> hand){
        System.out.println("Hand given" + hand);
    }

    public void bet(ClientConnection player, int amount){
        System.out.println("Player bet " + amount);
        GameManager.getSingleton().bet(player.getName(), amount);
    }

    public void nextTurn(){
        GameManager.getSingleton().nextTurn();
    }
}
