import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The connection from the client to the server.
 * How the client communicates with the server.
 */
public class ServerConnection extends Thread {
    private Socket clientSocket;
    private PrintWriter toServer;
    private BufferedReader fromServer;
    private Game game;

    public ServerConnection(Game game) {
        this.game = game;
    }

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        toServer = new PrintWriter(clientSocket.getOutputStream(), true);
        fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.start();
    }

    public void setPlayerName(String name){
        sendMessage("playername " + name);
    }

    public void run(){
        try{
            String inputLine;
            while((inputLine = fromServer.readLine()) != null){
                Scanner s = new Scanner(inputLine);
                String function = s.next();
                if(function.equals("deal")){

                } else if(function.equals("bet")) {
                    game.newBetStatus(s.nextInt());
                    game.packetReceived(s.nextLine());
                } else if(function.equals("yourturn")) {
                    game.turn(parseDeck(s.nextLine()));
                } else if(function.equals("win")){
                    String name = s.next();
                    int pot = s.nextInt();
                    String hand = s.nextLine();
                    game.playerWon(name, hand, pot);
                } else if(function.equals("roundover")){
                    game.roundOver(s.nextLine());
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private ArrayList<Card> parseDeck(String input){
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
        toServer.println(message);
    }


    public void stopConnection() throws IOException {
        fromServer.close();
        toServer.close();
        clientSocket.close();
    }

    public void makeBet(int bet) {
        sendMessage("bet " + bet);
    }

    public void fold() {
        sendMessage("fold");
    }

}
