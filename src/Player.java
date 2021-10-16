import java.util.*;

public class Player
{
    private final String name;
    private boolean flopped;
    private ArrayList<Card> hand;
    private HandCheck hc;

    public Player(String name)
    {
        this.name = name;
        flopped = false;
        hand = new ArrayList<>();
        hc = new HandCheck(hand);
    }

    public String getName()
    {
        return name;
    }

    public boolean getFlopped()
    {
        return flopped;
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public void flop()
    {
        if (!flopped)
            flopped = true;
    }

    public void receiveCard(Card card)
    {
        hand.add(card);
    }

    public String receiveBoard(ArrayList<Card> board)
    {
        return hc.getBestHand(board);
    }
}