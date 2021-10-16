import java.util.*;

public class Player
{
    private final String name;
    private int money;
    private boolean folded;
    private ArrayList<Card> hand;
    private HandCheck hc;

    public Player(String name, int money)
    {
        this.name = name;
        this.money = money;
        folded = false;
        hand = new ArrayList<>();
        hc = new HandCheck(hand);
    }

    public String getName()
    {
        return name;
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
}