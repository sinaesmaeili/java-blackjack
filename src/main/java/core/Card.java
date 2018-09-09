package core;

public class Card {

    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String printCard() {
        return this.suit + this.rank;
    }

    public int getCardValue() {
        if (this.rank.equals("K") || this.rank.equals("Q") || this.rank.equals("J")) {
            return 10;
        } else if (this.rank.equals("A")) {
            return 11;
        } else {
            return Integer.parseInt(this.rank);
        }
    }

    public String getSuit() {
        return this.suit;
    }

    public String getRank() {
        return this.rank;
    }

}
