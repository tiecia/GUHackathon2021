import java.util.*;

public class HandCheck
{
    private ArrayList<Card> hand;

    public HandCheck(ArrayList<Card> hand)
    {
        updateHand(hand);
    }

    public void updateHand(ArrayList<Card> hand)
    {
        this.hand = hand;
    }

    public String getBestHand(ArrayList<Card> board)
    {
        String bestHand = "";
        return bestHand;
    }
}