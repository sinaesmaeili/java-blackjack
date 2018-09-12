package core;

import junit.framework.TestCase;


public class FileInputTest extends TestCase {

    public void testReadFile() {
        FileInput fileGame = new FileInput("src/assets/input.txt");
        fileGame.readFile();

        assertFalse(fileGame.commands.isEmpty());
        assertEquals(4, fileGame.commands.size());
    }

    public void testDealCards() {
        FileInput fileGame = new FileInput("src/assets/input.txt");
        fileGame.readFile();
        fileGame.dealCards();

        assertEquals("SK", fileGame.getPlayerHand().get(0).printCard());
        assertEquals("HA", fileGame.getPlayerHand().get(1).printCard());
        assertEquals("HQ", fileGame.getDealerHand().get(0).printCard());
        assertEquals("CA", fileGame.getDealerHand().get(1).printCard());
    }

    public void testTallyPointsPlayer() {
        FileInput fileGame = new FileInput("src/assets/input.txt");
        fileGame.readFile();
        fileGame.dealCards();
        fileGame.tallyPointsPlayer();

        assertEquals(21, fileGame.getPlayerTotal());
    }

    public void testTallyPointsDealer() {
        FileInput fileGame = new FileInput("src/assets/input.txt");
        fileGame.readFile();
        fileGame.dealCards();
        fileGame.tallyPointsDealer();

        assertEquals(21, fileGame.getDealerTotal());
    }

    public void testMainLoop() {
        FileInput fileGame = new FileInput("src/assets/test/double_ace_input.txt");
        fileGame.readFile();
        fileGame.dealCards();
        fileGame.mainLoop();

        assertEquals(12, fileGame.getPlayerTotal());
        assertEquals(19, fileGame.getDealerTotal());
    }

    public void testCheckWinDealerTieWithPlayer() {
        FileInput fileGame = new FileInput("src/assets/test/check_win_test1.txt");
        fileGame.readFile();
        fileGame.dealCards();
        fileGame.mainLoop();

        assertTrue(fileGame.winnerIsDealer());
    }

    public void testCheckWinDealerBlackjack() {
        FileInput fileGame = new FileInput("src/assets/test/check_win_test2.txt");
        fileGame.readFile();
        fileGame.dealCards();
        fileGame.mainLoop();

        assertTrue(fileGame.winnerIsDealer());
    }

    public void testCheckWinPlayerBlackjack() {
        FileInput fileGame = new FileInput("src/assets/test/check_win_test3.txt");
        fileGame.readFile();
        fileGame.dealCards();
        fileGame.mainLoop();

        assertFalse(fileGame.winnerIsDealer());
    }

    public void testCheckWinPlayerBusts() {
        FileInput fileGame = new FileInput("src/assets/test/check_win_test4.txt");
        fileGame.readFile();
        fileGame.dealCards();
        fileGame.mainLoop();

        assertTrue(fileGame.winnerIsDealer());
    }

    public void testCheckWinDealerBusts() {
        FileInput fileGame = new FileInput("src/assets/test/check_win_test5.txt");
        fileGame.readFile();
        fileGame.dealCards();
        fileGame.mainLoop();

        assertFalse(fileGame.winnerIsDealer());
    }

    public void testCheckWinPlayerLessThanDealer() {
        FileInput fileGame = new FileInput("src/assets/test/check_win_test6.txt");
        fileGame.readFile();
        fileGame.dealCards();
        fileGame.mainLoop();

        assertTrue(fileGame.winnerIsDealer());
    }

    public void testCheckWinPlayerGreaterThanDealer() {
        FileInput fileGame = new FileInput("src/assets/test/check_win_test7.txt");
        fileGame.readFile();
        fileGame.dealCards();
        fileGame.mainLoop();

        assertFalse(fileGame.winnerIsDealer());
    }
}
