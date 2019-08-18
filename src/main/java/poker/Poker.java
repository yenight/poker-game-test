package poker;

public class Poker {

    private String cardName;
    private int number;
    private String pokerColor;

    public Poker(String cardName) {
        this.cardName = cardName;
        this.number = Integer.parseInt(cardName.substring(0, 1));
        this.pokerColor = cardName.substring(1, 2);
    }

    public String getCardName() {
        return cardName;
    }

    public int getNumber() {
        return number;
    }

    public String getPokerColor() {
        return pokerColor;
    }

    public boolean compareNumber(int number2) {
        return true;
    }

}
