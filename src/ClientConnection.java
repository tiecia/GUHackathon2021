import java.io.*;
import java.lang.reflect.Array;
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
            System.out.print("New client created...");
            String inputLine;
            while (reading) {
                System.out.println("waiting for packets...on thread " + super.toString());
                inputLine = fromClient.readLine();

                if(inputLine != null){
                    String function;
                    Scanner s = new Scanner(inputLine);
                    function = s.next();
                    System.out.println("Server received a packet");

                    if(function.equals("besthand")){
                        server.giveBestHand(this, parseHand(s.nextLine()));
                    } else if (function.equals("bet")){
                        server.bet(this, s.nextInt());
                        s.next(); //Name from bet packet
                        System.out.println("bet");
                    } else if (function.equals("hand")){
                        server.giveHand(this, parseHand(s.nextLine()));
                        System.out.println("hand");
                    } else if (function.equals("playername")){
                        name = s.nextLine();
                        System.out.println("New player name set: " + name);
                    }
                }
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
        String message = "deal ";
        for(int i = 0; i<num; i++){
            Card newCard = dealer.dealCard();
            message += cardToString(newCard.getColor(), newCard.getSuit()) + " ";
            message += newCard.getValue() + " ";
        }
        sendMessage(message);
    }

    private String cardToString(boolean color, boolean suit){
        if (color) {
            if (suit)
                return "clubs";
            else
                return "spades";
        }
        else {
            if (suit)
                return "diamond";
            else
                return "heart";
        }
    }

    public void bet(int amount){
        sendMessage("bet " + amount + " " + name);
    }

    public void yourTurn(ArrayList<Card> board){
        String boardString = "";
        for(Card card : board) {
            boardString += cardToString(card.getColor(), card.getSuit()) + " ";
            boardString += card.getValue() + " ";
        }
        sendMessage("yourturn ");
    }

    public void win(String result){
        sendMessage("win " + result);
    }

    public void sendRoundOver(String name){
        sendMessage("roundover " + name);
    }

    public void sendGameOver(ArrayList<Card> board) {
        String boardString = "";
        for(Card card : board) {
            boardString += cardToString(card.getColor(), card.getSuit()) + " ";
            boardString += card.getValue() + " ";
        }
        sendMessage("gameover ");
    }
}
