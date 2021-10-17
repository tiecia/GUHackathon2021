import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Gameloop to play a single turn
 * on a players side, weather thats
 * fold, call, check, or raise.
 *
 * @author drew
 */

public class Game {

    private ServerConnection serverConnection;
    private boolean playing;

    private Player player;

    private int needToBet;

    public Game(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
        playing = true;
        player = new Player(1000);
    }

    public void startGame() {
        while(playing) {

        }
    }

    public void newBetStatus(int bet) {
        needToBet = bet;
    }

    public void packetReceived(String name) {
        if(needToBet - player.getMoneyBet() == 0) {
            System.out.println("No new bets were made");
        } else {
            System.out.println("Bet was made by " + name);
            System.out.println("You need to put $" + (needToBet - player.getMoneyBet()) + " in to stay. Otherwise, fold");
        }
    }

    public void turn(ArrayList<Card> board) {
        System.out.println("Current Board:");
        for(Card card : board){
            System.out.print(" [" + card + "]");
        }

        int betRequired = (needToBet - player.getMoneyBet());

        if(betRequired == 0) {
            System.out.println("Would you like to Check(c), Raise(c), or Fold(f)?");
        }
        else {
            System.out.println("Would you like to Call(c), Raise(r), or Fold(f)?");
        }
        Scanner userIn = new Scanner(System.in);
        String choice = userIn.next().toLowerCase();
        if(choice.equals("c")) {
            serverConnection.makeBet(betRequired);
        }
        else if(choice.equals("r")) {
            System.out.println("You currently have: " + player.getMoney() + "    Current bet is: " + betRequired);
            System.out.println("How much would you like to raise?");
            int bet = userIn.nextInt() + betRequired;
            if(player.bet(bet)) {
                serverConnection.makeBet(bet);
            }
            else {
                System.out.println("You cant make that bet. You fold");
                serverConnection.fold();
            }
        }
        else {
            System.out.println("You Fold");
            serverConnection.fold();
        }

    }

    public void giveCard(Card card) {
        player.receiveCard(card);
    }



}
