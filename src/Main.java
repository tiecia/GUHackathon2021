import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException {
        //Server
        Server.start(4444);

        //Client
        ServerConnection serverConnection = new ServerConnection();
        serverConnection.startConnection("127.0.0.1", 4444);

        String response = serverConnection.sendMessage("Message From Client");
        System.out.println("Response From Server: " + response);

        Server.stopServer();
        serverConnection.stopConnection();

        System.out.println("Game Stopped");
    }
}