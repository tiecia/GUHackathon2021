import java.util.*;

public class WinDecider
{
    private int[][] hands;
    private ArrayList<Integer> competing;

    public int findWinner(int[][] playerHands)
    {
        this.hands = playerHands;
        competing = new ArrayList<>();
        int lowest = 10;

        for (int i = 0; i < hands.length; i++)
        {
            if (hands[i][0] < lowest)
            {
                lowest = hands[i][0];
                competing.clear();
                competing.add(i);
            }
            else if (hands[i][0] == lowest)
                competing.add(i);
        }

        if (competing.size() == 1)
            return competing.get(0);
        else
        {
            if (lowest == 1 || lowest == 4 || lowest == 5)
                return judgeLargest();
            else if (lowest == 2)
                return judgeQuad();
            else if (lowest == 9)
                return judgeHigh();
            return -1;
        }
    }

    private int judge(int index)
    {
        int largest = 0;
        for (int player : competing)
            if (hands[player][index] > largest)
                largest = player;
        return largest;
    }

    private int judgeLargest()
    {
        return judge(5);
    }

    private int judgeQuad()
    {
        return judge(3);
    }

    private int judgeHigh()
    {
        return judge(2);
    }
}