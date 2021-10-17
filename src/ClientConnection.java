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

    private boolean reading;

    private String name;

    public ClientConnection(Server server, Socket socket, int port){
        this.clientSocket = socket;
        this.server = server;
        this.reading = true;
        try {
            toClient = new PrintWriter(clientSocket.getOutputStream(), true);
            fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            //toClient = new PrintWriter(clientSocket.getOutputStream(), true);
            //fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.print("New client created...");
            String inputLine;
            while (true) {
                System.out.println("waiting for packets...on thread " + super.toString());
                //System.out.println(fromClient.ready());
                inputLine = fromClient.readLine();
                //inputLine = "playername drew";

                if(inputLine != null){
                    String function;
                    Scanner s = new Scanner(inputLine);
                    function = s.next();
                    System.out.println("Server received a packet");

                    if(function.equals("besthand")){
                        server.giveBestHand(this, parseHand(s.nextLine()));
                    } else if (function.equals("bet")){
                        server.bet(this, s.nextInt());
                        System.out.println("bet");
                    } else if (function.equals("hand")){
                        server.giveHand(this, parseHand(s.nextLine()));
                        System.out.println("hand");
                    } else if (function.equals("playername")){
                        name = s.nextLine();
                        System.out.println("New player name set: " + name);
                    }

                    //System.out.println("Server received the line: " + inputLine);
                    //sendMessage(inputLine);
                    //Analyze input packets here
                }
            }
            //fromClient.close();
            //toClient.close();
            //clientSocket.close();
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
