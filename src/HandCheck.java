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

    public int[] getBestHand(ArrayList<Card> board)
    {
        int[] bestHand = {-1, -1, -1, -1, -1, -1};

        ArrayList<Card> pool = new ArrayList<>();
        pool.addAll(hand);
        pool.addAll(board);
        Collections.sort(pool);

        int c = pool.size() - 1;
        if (hasRoyalFlush(pool))
        {
            bestHand[0] = 0;
            for (int i = 9; i < 13; i++)
                bestHand[i - 8] = i;
        }
        else if (hasStraightFlush(pool))
        {
            bestHand[0] = 1;

            for (int i = c; i > 2; i--)
            {
                if ((pool.get(i).getValue() != pool.get(i - 1).getValue() + 1)
                        || !pool.get(i).isSameSuit(pool.get(i - 1))
                        || (pool.get(i - 1).getValue() != pool.get(i - 2).getValue() + 1)
                        || !pool.get(i - 1).isSameSuit(pool.get(i - 2))
                        || (pool.get(i - 2).getValue() != pool.get(i - 3).getValue() + 1)
                        || !pool.get(i - 2).isSameSuit(pool.get(i - 3)))
                    c--;
            }
            for (int j = bestHand.length - 1; j > 0; j--)
            {
                bestHand[j] = pool.get(c).getValue();
                c--;
            }
        }
        else if (hasQuad(pool))
        {
            bestHand[0] = 2;
            int i = 0;
            while (pool.get(i).getValue() != pool.get(i + 1).getValue()
                    && pool.get(i).getValue() == pool.get(i + 2).getValue())
                i++;
        }
        else if (hasHouse(pool))
        {
            bestHand[0] = 3;
        }
        else if (hasFlush(pool))
        {
            bestHand[0] = 4;
            for (int i = c; i > 2; i--)
            {
                if (!pool.get(i).isSameSuit(pool.get(i - 1)) || !pool.get(i - 1).isSameSuit(pool.get(i - 2))
                        || !pool.get(i - 2).isSameSuit(pool.get(i - 3)))
                    c--;
            }
            for (int j = bestHand.length - 1; j > 0; j--)
            {
                bestHand[j] = pool.get(c).getValue();
                c--;
            }
        }
        else if (hasStraight(pool))
        {
            bestHand[0] = 5;
            for (int i = c; i > 2; i--)
            {
                if ((pool.get(i).getValue() != pool.get(i - 1).getValue() + 1)
                        || (pool.get(i - 1).getValue() != pool.get(i - 2).getValue() + 1)
                        || (pool.get(i - 2).getValue() != pool.get(i - 3).getValue() + 1))
                    c--;
            }
            for (int j = bestHand.length - 1; j > 0; j--)
            {
                bestHand[j] = pool.get(c).getValue();
                c--;
            }
        }
        else if (hasTrio(pool))
        {
            bestHand[0] = 6;
        }
        else if (countPairs(pool) == 2)
        {
            bestHand[0] = 7;
        }
        else if (countPairs(pool) == 1)
        {
            bestHand[0] = 8;
        }
        else
        {
            bestHand[0] = 9;
            for (int i = 0; i < hand.size(); i++)
                bestHand[i + 1] = hand.get(i).getValue();
        }
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