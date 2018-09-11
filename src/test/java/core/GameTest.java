package core;

import junit.framework.TestCase;

public class GameTest extends TestCase {

    public void testDeckCreationWhenConsoleGame() {
        Game game = new Game();

        assertNotNull(game.gameDeck);
    }

    public void playerHasTwoCardsAtStart() {
        Game game = new Game();

        assertEquals(2, game.getPlayerHand().size());
    }

    public void dealerHasTwoCardsAtStart() {
        Game game = new Game();

        assertEquals(2, game.getDealerHand().size());
    }

    public void testCheckWinnerWhenPlayerAndDealerTie() {
        Game game = new Game();
        game.setPlayerTotal(21);
        game.setDealerTotal(21);
        game.checkWin();

        assertEquals(game.Players.DEALER, game.getWinner);
    }

    public void testCheckWinnerWhenDealer21() {
        Game game = new Game();
        game.setPlayerTotal(20);
        game.setDealerTotal(21);
        game.checkWin();

        assertEquals(game.Players.DEALER, game.getWinner);
    }

    public void testCheckWinnerWhenPlayer21() {
        Game game = new Game();
        game.setPlayerTotal(21);
        game.setDealerTotal(20);
        game.checkWin();

        assertEquals(game.Players.USER, game.getWinner);
    }

    public void testCheckWinnerWhenDealerGreaterThanPlayer() {
        Game game = new Game();
        game.setPlayerTotal(19);
        game.setDealerTotal(10);
        game.checkWin();

        assertEquals(game.Players.DEALER, game.getWinner);
    }

    public void testCheckWinnerWhenPlayerGreaterThanDealer() {
        Game game = new Game();
        game.setPlayerTotal(20);
        game.setDealerTotal(10);
        game.checkWin();

        assertEquals(game.Players.USER, game.getWinner);
    }

    public void testConsoleGameLoop() {
        Game game = new Game();
        game.playerLoop();
        game.dealerLoop();
    }

}
