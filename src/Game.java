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

    private Player player;

    private int needToBet;

    /**
     * rahhhhhhhhh
     * constructor
     * @param serverConnection
     */
    public Game(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
        player = new Player(1000);
    }

    /**
     * new bet status when a change occurs
     * when some weirdo wants to raise the pot
     * @param bet - bet change
     */
    public void newBetStatus(int bet) {
        needToBet = bet;
    }

    /**
     * basic method to be called when a packet is received to give info
     * to the user on what is going on
     * @param name - name of user who packet pretains too
     */
    public void packetReceived(String name) {
        if(needToBet - player.getMoneyBet() == 0) {
            System.out.println("No new bets were made");
        } else {
            System.out.println("Bet was made by " + name);
            System.out.println("You need to put $" + (needToBet - player.getMoneyBet()) + " in to stay.");
        }
    }

    /**
     * does the basic logic around the turn
     * allows the player to choose check, raise, call, or fold
     * like a normal poker game obviously
     * @param board - current board of cards
     */
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

    /**
     * gives a card to this player
     * @param card - card given
     */
    public void giveCard(Card card) {
        player.receiveCard(card);
    }

    /**
     * gets the data of the player winning and
     * prints the statements
     * blah blah blah who cares anyway
     * not me
     * @param name - name of player than won
     * @param winningHand - winning hand
     * @param pot - current pot won
     */
    public void playerWon(String name, String winningHand, int pot) {
        System.out.println(name + " won the pot of $" + pot + " with a " + winningHand + "!");
    }

    /**
     * gives the winning to the current player
     * which is never me...
     * @param winnings - winnings
     */
    public void chipsGiven(int winnings) {
        player.givePot(winnings);
    }

    public void roundOver(String name){
        System.out.println(name + "'s turn is over.");
    }

    public int[] gameOver(ArrayList<Card> board) {
        return player.receiveBoard(board);
    }



}
