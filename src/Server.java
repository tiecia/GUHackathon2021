import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server extends Thread {
    private static Server singleton;
    private static int port;
    private static boolean acceptingConnections = true;
    private static Thread serverThread;

    private ServerSocket serverSocket;

    private Dealer dealer;
    private BoardManager board;
    private ChipManager chips;

    private ArrayList<ClientConnection> clients;

    public static void start(int portInput) throws IOException {
        if (singleton == null) {
            port = portInput;
            singleton = new Server();
            singleton.start();
        }
    }

    public Server() throws IOException {
        serverSocket = new ServerSocket(port);
        clients = new ArrayList<>();
    }

    public void run() {
        try {
            System.out.println("Server started...waiting for connection");
            while (acceptingConnections) {
                ClientConnection connection = new ClientConnection(serverSocket.accept());
                connection.start();
                clients.add(connection);
                System.out.println("Client Added");
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopServer(){
        acceptingConnections = false;
    }
}
