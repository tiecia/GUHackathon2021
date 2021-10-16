public class Card implements Comparable<Card>
{
    private int value; // [0,12]
    private boolean color; // F=Red, T=Black
    private boolean suit; // Red- F=Hearts, T=Diamonds; Black- F=Spades, T=Clubs

    public static final String[] CARDS = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Jack", "Queen", "King"};

    public Card(int value, boolean color, boolean suit)
    {
        this.value = value;
        this.color = color;
        this.suit = suit;
    }

    public int getValue()
    {
        return value;
    }

    public boolean getColor()
    {
        return color;
    }

    public boolean getSuit()
    {
        return suit;
    }

    public String toString()
    {
        String str = CARDS[value] + " of ";

        if (color)
        {
            if (suit)
                str += "Clubs";
            else
                str += "Spades";
        }
        else
        {
            if (suit)
                str += "Diamonds";
            else
                str += "Hearts";
        }
        return str;
    }

    public int compareTo(Card other)
    {
        return value - other.value;
    }
}