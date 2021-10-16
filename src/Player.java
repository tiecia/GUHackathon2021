import java.util.*;

public class Player
{
    private int money;
    private boolean folded;
    private ArrayList<Card> hand;
    private HandCheck hc;

    public Player(int money)
    {
        this.money = money;
        folded = false;
        hand = new ArrayList<>();
        hc = new HandCheck(hand);
    }

    public boolean getFolded()
    {
        return folded;
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public void fold()
    {
        folded = true;
    }

    public boolean bet(int amount)
    {
        boolean valid = false;
        if (amount <= money)
        {
            valid = true;
            money -= amount;
        }
        return valid;
    }

    public void receiveCard(Card card)
    {
        hand.add(card);
        Collections.sort(hand);
    }

    public String receiveBoard(ArrayList<Card> board)
    {
        return hc.getBestHand(board);
    }

    public void givePot(int winnings) {
        money += winnings;
    }
}