package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private static final String[] RANK = { "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2", "A" };
    private static final String[] SUIT = { "S", "C", "D", "H" };
    private List<Card> deck = new ArrayList<Card>();

    public Deck() {
        generateDeck();
    }

    public Card takeCard() {
        Random rand = new Random();
        return deck.remove(rand.nextInt(deck.size()));
    }

    public int getDeckSize() {
        return deck.size();
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
