import java.util.*;

public class Player
{
    private final String name;
    private ArrayList<Card> hand;
    private HandCheck hc;

    public Player(String name)
    {
        this.name = name;
        hand = new ArrayList<>();
        hc = new HandCheck(hand);
    }

    public String getName()
    {
        return name;
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