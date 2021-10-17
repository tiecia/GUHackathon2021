/**
 * manages the chips
 * obviously
 * dont be stupid
 */
public class ChipManager {

    private int pot;

    /**
     * sets pot to 0
     * i hope
     */
    public ChipManager() {
        pot = 0;
    }

    /**
     * gives the chips from player to pot
     * @param money from the player
     */
    public void giveChips(int money) {
        pot += money;
    }

    /**
     * returns the pot for the player
     * @return the pot of money
     */
    public int getPot() {
        return pot;
    }

    /**
     * again, i hope this sets it to 0
     */
    public void newRound() {
        pot = 0;
    }
}
