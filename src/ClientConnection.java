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
        try {
            toClient = new PrintWriter(clientSocket.getOutputStream(), true);
            fromClient = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            name = fromClient.readLine();
            System.out.println("Name Assigned: " + name);
            System.out.println("New client created...waiting for packets");
            String inputLine;
            while ((inputLine = fromClient.readLine()) != null) {
                System.out.println("Server received a packet");
                String function;
                Scanner s = new Scanner(inputLine);
                function = s.next();

                if(function.equals("besthand")){
                    server.giveBestHand(this, parseHand(inputLine));
                } else if (function.equals("bet")){
                    server.bet(this, s.nextInt());
                } else if (function.equals("hand")){
                    server.giveHand(this, parseHand(inputLine));
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
        ArrayList<Card> cards = new ArrayList<>();
        Scanner s = new Scanner(input);
        while(s.hasNext()){
            String suit = s.next();
            if(suit.equals("hearts")){
                cards.add(new Card(s.nextInt(), false, false));
            } else if(suit.equals("diamond")){
                cards.add(new Card(s.nextInt(), false, true));
            } else if(suit.equals("spades")){
                cards.add(new Card(s.nextInt(), true, false));
            } else if(suit.equals("clubs")){
                cards.add(new Card(s.nextInt(), true, true));
            }
        }
        return cards;
    }

    public void sendMessage(String message){
        toClient.println(message);
    }

    public void dealCards(Dealer dealer, int num){

    }

    public void sendRoundOver(){

    }
}
