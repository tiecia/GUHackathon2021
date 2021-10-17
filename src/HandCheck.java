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
            for (int i = 1; i < bestHand.length; i++)
                bestHand[i] = i + 8;
        }
        else if (hasStraightFlush(pool))
        {
            bestHand[0] = 1;
            for (int i = c; i > 1; i--)
                if ((pool.get(i).getValue() != pool.get(i - 1).getValue() + 1)
                        || !pool.get(i).isSameSuit(pool.get(i - 1))
                        || (pool.get(i - 1).getValue() != pool.get(i - 2).getValue() + 1)
                        || !pool.get(i - 1).isSameSuit(pool.get(i - 2)))
                    c--;
                else
                    break;
            for (int j = bestHand.length - 1; j > 0; j--)
            {
                bestHand[j] = pool.get(c).getValue();
                c--;
            }
        }
        else if (hasQuad(pool))
        {
            bestHand[0] = 2;
            for (int i = c; i > 2; i--)
                if (pool.get(i).getValue() != pool.get(i - 1).getValue()
                        || pool.get(i - 1).getValue() != pool.get(i - 2).getValue()
                        || pool.get(i - 2).getValue() != pool.get(i - 3).getValue())
                    c--;
                else
                    break;
            if (pool.get(c).getValue() < pool.get(pool.size() - 1).getValue())
            {
                bestHand[bestHand.length - 1] = pool.get(pool.size() - 1).getValue();
                for (int j = 1; j < bestHand.length - 1; j++)
                    bestHand[j] = pool.get(c).getValue();
            }
            else
            {
                bestHand[1] = pool.get(c - 4).getValue();
                for (int j = 2; j < bestHand.length; j++)
                    bestHand[j] = pool.get(c).getValue();
            }
        }
        else if (hasHouse(pool))
        {
            bestHand[0] = 3;
            int k = c;
            for (int i = c; i > 1; i--)
            {
                if (pool.get(i).getValue() == pool.get(i - 1).getValue()
                        && pool.get(i - 1).getValue() == pool.get(i - 2).getValue())
                {
                    c = i;
                    break;
                }
            }
            for (int i = k; i > 0; i--)
            {
                if (pool.get(i).getValue() == pool.get(i - 1).getValue())
                {
                    k = i;
                    break;
                }
            }
            if (pool.get(k).getValue() < pool.get(c).getValue())
            {
                for (int i = 1; i < 3; i++)
                    bestHand[i] = pool.get(k).getValue();
                for (int j = 3; j < bestHand.length; j++)
                    bestHand[j] = pool.get(c).getValue();
            }
            else
            {
                for (int i = 1; i < 4; i++)
                    bestHand[i] = pool.get(c).getValue();
                for (int j = 4; j < bestHand.length; j++)
                    bestHand[j] = pool.get(k).getValue();
            }
        }
        else if (hasFlush(pool))
        {
            bestHand[0] = 4;
            for (int i = c; i > 1; i--)
                if (!pool.get(i).isSameSuit(pool.get(i - 1)) || !pool.get(i - 1).isSameSuit(pool.get(i - 2)))
                    c--;
                else
                    break;
            for (int j = bestHand.length - 1; j > 0; j--)
            {
                bestHand[j] = pool.get(c).getValue();
                c--;
            }
        }
        else if (hasStraight(pool))
        {
            bestHand[0] = 5;
            for (int i = c; i > 1; i--)
                if ((pool.get(i).getValue() != pool.get(i - 1).getValue() + 1)
                        || (pool.get(i - 1).getValue() != pool.get(i - 2).getValue() + 1))
                    c--;
                else
                    break;
            for (int j = bestHand.length - 1; j > 0; j--)
            {
                bestHand[j] = pool.get(c).getValue();
                c--;
            }
        }
        else if (hasTrio(pool))
        {
            bestHand[0] = 6;
            for (int i = c; i > 1; i--)
            {
                if (pool.get(i).getValue() == pool.get(i - 1).getValue()
                        && pool.get(i - 1).getValue() == pool.get(i - 2).getValue()) {
                    c = i;
                    break;
                }
            }
            int len = bestHand.length - 1;
            int count = 0;
            for (int j = pool.size() - 1; j > c && count < 2; j--)
            {
                bestHand[len] = pool.get(j).getValue();
                len--;
                count++;
            }
            while (len > 0)
            {
                bestHand[len] = pool.get(c).getValue();
                len--;
                c--;
            }
        }
        else if (countPairs(pool) == 2)
        {
            bestHand[0] = 7;
            for (int i = c; i > 0; i--)
            {
                if (pool.get(i).getValue() == pool.get(i - 1).getValue())
                {
                    c = i;
                    break;
                }
            }
            int k = c - 2;
            for (int j = k; j > 0; j--)
            {
                if (pool.get(j).getValue() == pool.get(j - 1).getValue())
                {
                    k = j;
                    break;
                }
            }
            if (pool.get(c).getValue() < pool.get(pool.size() - 1).getValue())
            {
                bestHand[bestHand.length - 1] = pool.get(pool.size() - 1).getValue();
                for (int j = 1; j < 3; j++)
                    bestHand[j] = pool.get(k).getValue();
                for (int h = 3; h < bestHand.length - 1; h++)
                    bestHand[h] = pool.get(c).getValue();
            }
            else if (pool.get(k).getValue() < pool.get(c - 2).getValue())
            {
                bestHand[3] = pool.get(c - 2).getValue();
                for (int j = 1; j < 3; j++)
                    bestHand[j] = pool.get(k).getValue();
                for (int h = 4; h < bestHand.length; h++)
                    bestHand[h] = pool.get(c).getValue();
            }
            else
            {
                bestHand[1] = pool.get(k - 2).getValue();
                for (int j = 2; j < 4; j++)
                    bestHand[j] = pool.get(k).getValue();
                for (int h = 4; h < bestHand.length; h++)
                    bestHand[h] = pool.get(c).getValue();
            }
        }
        else if (countPairs(pool) == 1)
        {
            bestHand[0] = 8;
            for (int i = c; i > 0; i--)
            {
                if (pool.get(i).getValue() == pool.get(i - 1).getValue())
                {
                    c = i;
                    break;
                }
            }
            int len = bestHand.length - 1;
            int count = 0;
            for (int j = pool.size() - 1; j > c && count < 3; j--)
            {
                bestHand[len] = pool.get(j).getValue();
                len--;
                count++;
            }
            while (len > 0)
            {
                bestHand[len] = pool.get(c).getValue();
                len--;
                c--;
            }
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

    public boolean hasStraight(ArrayList<Card> pool)
    {
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

    public boolean hasTrio(ArrayList<Card> pool)
    {
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