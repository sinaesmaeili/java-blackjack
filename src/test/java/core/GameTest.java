package core;

import junit.framework.TestCase;

public class GameTest extends TestCase {

    public void testDeckCreationWhenConsoleGame() {
        Game game = new Game();
        game.playConsole();

        assertNotNull(game.gameDeck);
    }

    public void testPlayerHasTwoCardsAtStart() {
        Game game = new Game();

        assertEquals(2, game.getPlayerHand().size());
    }

    public void testDealerHasTwoCardsAtStart() {
        Game game = new Game();

        assertEquals(2, game.getDealerHand().size());
    }

    public void testCheckWinnerWhenPlayerAndDealerTie() {
        Game game = new Game();
        game.setPlayerTotal(21);
        game.setDealerTotal(21);
        game.checkWin();

        assertEquals(Game.Players.DEALER, game.getWinner());
    }

    public void testCheckWinnerWhenDealer21() {
        Game game = new Game();
        game.setPlayerTotal(20);
        game.setDealerTotal(21);
        game.checkWin();

        assertEquals(Game.Players.DEALER, game.getWinner());
    }

    public void testCheckWinnerWhenPlayer21() {
        Game game = new Game();
        game.setPlayerTotal(21);
        game.setDealerTotal(20);
        game.checkWin();

        assertEquals(Game.Players.USER, game.getWinner());
    }

    public void testCheckWinnerWhenDealerGreaterThanPlayer() {
        Game game = new Game();
        game.setPlayerTotal(19);
        game.setDealerTotal(10);
        game.checkWin();

        assertEquals(Game.Players.DEALER, game.getWinner());
    }

    public void testCheckWinnerWhenPlayerGreaterThanDealer() {
        Game game = new Game();
        game.setPlayerTotal(20);
        game.setDealerTotal(10);
        game.checkWin();

        assertEquals(Game.Players.USER, game.getWinner());
    }

    public void testCountAcePlayer() {
        Game game = new Game();
        Card card = new Card("S", "A");
        game.addToPlayerHand(card);
        game.addToPlayerHand(card);
        game.playerTotal += card.getCardValue() + card.getCardValue();
        game.countAcePlayer();

        assertEquals(12, game.playerTotal);
    }

    public void testCountAceDealer() {
        Game game = new Game();
        Card card = new Card("S", "A");
        game.addToDealerHand(card);
        game.addToDealerHand(card);
        game.dealerTotal += card.getCardValue() + card.getCardValue();
        game.countAceDealer();

        assertEquals(12, game.dealerTotal);
    }

    public void testConsoleGameLoop() {
        Game game = new Game();
        game.playerLoop();
        game.dealerLoop();
    }

}
