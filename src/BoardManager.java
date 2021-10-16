import java.util.ArrayList;

public class BoardManager {

    private ArrayList<Card> currentBoard;

    public BoardManager() {
        newRound();
    }

    public void dealCards(Dealer dealer, int num) {
        dealer.dealCard();
        for(int count = 0; count < num; ++count) {
            currentBoard.add(dealer.dealCard());
        }
    }

    public void newRound() {
        currentBoard = new ArrayList<Card>();
    }

}
