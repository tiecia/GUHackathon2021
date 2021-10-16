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

        ArrayList<Card> pool = new ArrayList<>();
        pool.addAll(hand);
        pool.addAll(board);
        Collections.sort(pool);

        return bestHand;
    }

    public boolean hasRoyalFlush(ArrayList<Card> pool)
    {
        return hasStraightFlush(pool) && pool.get(0).getValue() == 9;
    }

    public boolean hasStraightFlush(ArrayList<Card> pool)
    {
        return hasFlush(pool) && hasStraight(pool);
    }

    public boolean hasQuad(ArrayList<Card> pool)
    {
        for (int i = 0; i < pool.size() - 3; i++)
        {
            int current = pool.get(i).getValue();

            if (current == pool.get(i + 1).getValue() && current == pool.get(i + 2).getValue()
                    && current == pool.get(i + 3).getValue())
                return true;
        }
        return false;
    }

    public boolean hasHouse(ArrayList<Card> pool)
    {
        return hasTrio(pool) && countPairs(pool) == 2;
    }

    public boolean hasFlush(ArrayList<Card> pool)
    {
        for (int i = 0; i < pool.size() - 1; i++)
            if (!pool.get(i).isSameSuit(pool.get(i + 1)))
                return false;
        return true;
    }

    public boolean hasStraight(ArrayList<Card> pool) {
        for (int i = 0; i < pool.size() - 1; i++)
        {
            if (pool.get(i).getValue() > 0)
                if (pool.get(i).getValue() != pool.get(i + 1).getValue() - 1)
                    return false;
            else
                if (pool.get(pool.size() - 1).getValue() != 12)
                    return false;
        }
        return true;
    }

    public boolean hasTrio(ArrayList<Card> pool) {
        for (int i = 0; i < pool.size() - 2; i++)
            if (pool.get(i).getValue() == pool.get(i + 1).getValue()
                    && pool.get(i).getValue() == pool.get(i + 2).getValue())
                return true;
        return false;
    }

    public int countPairs(ArrayList<Card> pool)
    {
        int count = 0;

        for (int i = 0; i < pool.size() - 1; i++)
        {
            if (pool.get(i).getValue() == pool.get(i + 1).getValue())
            {
                count++;
                i++;
            }
        }
        return count;
    }
}