import java.util.ArrayList;

public class Board {

    Deck deckOfCards;
    ArrayList<Integer> players;

    ArrayList<Card> currentBoard;
    ArrayList<Card> burned;

    public Board(int playerCount) {
        deckOfCards = new Deck();
        players = new ArrayList<>();
        currentBoard = new ArrayList<>();
        burned = new ArrayList<>();

    }

    private void returnCards() {
        while(currentBoard.size() != 0) {
            deckOfCards.cardsBack(currentBoard.remove(0));
        }
        while(burned.size() != 0) {
            deckOfCards.cardsBack(burned.remove(0));
        }
    }

    private void drawFlop() {
        burn();
        for(int count = 0; count < 3; ++count) {
            currentBoard.add(deckOfCards.draw());
        }
    }

    private void drawTurnRiver() {
        burn();
        currentBoard.add(deckOfCards.draw());
    }

    private void drawPlayer() {

    }

    private void burn() {
        burned.add(deckOfCards.draw());
    }
}
