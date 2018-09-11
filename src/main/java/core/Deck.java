package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final String[] RANK = { "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2", "A" };
    private static final String[] SUIT = { "S", "C", "D", "H" };
    private List<Card> deck = new ArrayList<Card>();

    public Deck() {
        this.generateDeck();
        this.shuffle();
    }

    public Card takeCard() {
        return deck.remove(0);
    }

    public int getDeckSize() {
        return deck.size();
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    private void generateDeck() {
        for (int i = 0; i < SUIT.length; i++) {
            for (int j = 0; j < RANK.length; j++) {
                Card card = new Card(SUIT[i], RANK[j]);
                deck.add(card);
            }
        }
    }
}
