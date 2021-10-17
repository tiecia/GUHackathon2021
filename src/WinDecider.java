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
            else if (lowest == 3)
                return judgeHouse();
            else if (lowest == 6)
                return judgeTrio();
            else if (lowest == 7 || lowest == 8)
                return judgePair();
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

    private int judgeHouse()
    {
        int largest = 0;
        boolean tie = false;
        for (int player : competing)
        {
            if (hands[player][3] > largest)
                largest = player;
            else
                tie = true;
        }
        if (tie)
        {
            int player1 = 0;
            int largest1 = 0;
            int player2 = 0;
            int largest2 = 0;
            int count = 0;
            for (int player: competing)
            {
                if (count < 1)
                {
                    if (hands[player][0] == hands[player][3])
                        largest1 = hands[player][5];
                    else
                        largest1 = hands[player][0];
                    player1 = player;
                    count++;
                }
                else
                {
                    if (hands[player][0] == hands[player][3])
                        largest2 = hands[player][5];
                    else
                        largest2 = hands[player][0];
                    player2 = player;
                }
            }
            if (largest1 > largest2)
                return player1;
            else
                return player2;
        }
        return largest;
    }

    private int judgeTrio()
    {
        int largest = 0;
        boolean tie = false;
        for (int player : competing)
        {
            if (hands[player][3] > largest)
                largest = player;
            else
                tie = true;
        }
        if (tie)
        {
            int player1 = 0;
            int largest1 = 0;
            int player2 = 0;
            int largest2 = 0;
            int count = 0;
            for (int player: competing)
            {
                if (count < 1)
                {
                    largest1 = hands[player][5];
                    player1 = player;
                    count++;
                }
                else
                {
                    largest2 = hands[player][5];
                    player2 = player;
                }
            }
            if (largest1 > largest2)
                return player1;
            else
                return player2;
        }
        return largest;
    }

    private int judgePair()
    {
        // Unimplemented
        return -1;
    }

    private int judgeHigh()
    {
        return judge(2);
    }
}