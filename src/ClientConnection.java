import jdk.nashorn.internal.parser.JSONParser;

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

    private String name;

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
                String function;
                Scanner s = new Scanner(inputLine);
                function = s.next();

                if(function.equals("besthand")){

                } else if (function.equals("bet")){
                    server.bet(this, s.nextInt());
                } else if (function.equals("hand")){

                }
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

    private ArrayList<Card> parseHand(String input){
        Scanner s = new Scanner(input);
    }

    public void sendMessage(String message){
        toClient.println(message);
    }

    public void dealCards(Dealer dealer, int num){

    }

    public void sendRoundOver(){

    }
}
