import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The connection from the server to the client.
 * How the server communicates with the client.
 */
public class ClientConnection extends Thread {
    private Server server;
    private Socket clientSocket;
    private PrintWriter toClient;
    private BufferedReader fromClient;

    public ClientConnection(Server server, Socket socket){
        this.clientSocket = socket;
        this.server = server;
    }

    public void start(int port) throws IOException {
        toClient = new PrintWriter(clientSocket.getOutputStream(), true);
        fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void run() {
        System.out.println("New client created...waiting for packets");
        //boolean listening = true;
        try {
            toClient = new PrintWriter(clientSocket.getOutputStream(), true);
            fromClient = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = fromClient.readLine()) != null) {

                Scanner s = new Scanner(inputLine);



                //System.out.println("Server received the line: " + inputLine);
                //sendMessage(inputLine);
                //Analyze input packets here
            }
            fromClient.close();
            toClient.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message){
        toClient.println(message);
    }

    public void dealCards(Dealer dealer, int num){

    }

    public void sendRoundOver(){

    }
}
