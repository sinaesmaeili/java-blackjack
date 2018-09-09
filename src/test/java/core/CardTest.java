package core;

import junit.framework.TestCase;

public class CardTest extends TestCase {

    public void testCard() {
        Card card = new Card("D", "5");
        assertEquals("D", card.getSuit());
        assertEquals("5", card.getRank());
    }

    public void testCardValue() {
        Card card = new Card("D", "5");
        Card card2 = new Card("C", "A");
        Card card3 = new Card("B", "J");

        assertEquals(5, card.getCardValue());
        assertEquals(11, card2.getCardValue());
        assertEquals(10, card3.getCardValue());
    }
}
