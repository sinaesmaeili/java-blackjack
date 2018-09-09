package core;

import junit.framework.TestCase;

public class DeckTest extends TestCase {

    public void testNumOfCardsInDeck() {
        Deck deck = new Deck();
        assertEquals(52, deck.getDeckSize());
    }

    public void testCardReturnedFromTakeCard() {
        Deck deck = new Deck();
        assertTrue(deck.takeCard() instanceof Card);
    }

    public void testForCompleteDeck() {
        Deck deck = new Deck();

        int numOfSpades = 0;

        for (int i = 0; i < 52; i++) {
            Card card = deck.takeCard();

            if (card.printCard().charAt(0) == 'S') numOfSpades++;
            if (numOfSpades == 4) break;
        }

        assertEquals(4, numOfSpades);
    }
}
