package core;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public int playerTotal;
    public int dealerTotal;
    public Deck gameDeck;
    public enum Players {
        USER,
        DEALER
    }

    private ArrayList<Card> playerHand = new ArrayList<Card>();
    private ArrayList<Card> dealerHand = new ArrayList<Card>();
    private Players winner;

    public static void main(String[] arg) {
        Game game = new Game();
        game.askInputType();
    }

    // Asks user for console or file input
    public void askInputType() {
        System.out.println("Start the game with console (c) or file (f) input?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        if (input.equals("c")) {
            playConsole();
        } else if (input.equals("f")) {
            playFromFile();
        } else {
            System.out.println("Incorrect input selected");
        }
    }

    public void playConsole() {
        gameDeck = new Deck();
        this.dealCards();

        // Checking for double ace before beginning loops
        this.countAcePlayer();
        this.countAceDealer();

        this.playerLoop();
    }

    public void playFromFile() {
        FileInput playGameFromFile = new FileInput("src/assets/" + this.askForInputFile());
        playGameFromFile.readFile();
        playGameFromFile.dealCards();
        playGameFromFile.mainLoop();
    }

    public String askForInputFile() {
        System.out.println("Enter name of any file in the assets folder. Example: input.txt");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    // Deal cards to player and dealer hand and calculates current total of each hand
    public void dealCards() {
        for (int i = 0; i < 2; i++) {
            this.playerHand.add(this.gameDeck.takeCard());
            this.dealerHand.add(this.gameDeck.takeCard());
        }

        System.out.println("Player hand: " + this.playerHand.get(0).printCard() + " " + this.playerHand.get(1).printCard());
        System.out.println("Dealer hand: " + this.dealerHand.get(0).printCard() + "\n");

        this.playerTotal = this.playerHand.get(0).getCardValue() + this.playerHand.get(1).getCardValue();
        this.dealerTotal = this.dealerHand.get(0).getCardValue() + this.dealerHand.get(1).getCardValue();
    }

    public void playerLoop() {
        // First checks for double ace of dealer or player
        this.countAcePlayer();
        this.countAceDealer();

        // Checking for starting blackjack
        if (dealerTotal == 21) {
            this.gameWin(Players.DEALER);
        } else if (playerTotal == 21) {
            this.gameWin(Players.USER);
        } else {
            while (playerTotal < 21) {
                System.out.println("Player total: " + this.playerTotal);
                System.out.println("Dealer total: " + this.dealerTotal);

                System.out.println("Hit (H) or Stand (S)?");
                Scanner sc = new Scanner(System.in);
                String input = sc.nextLine();

                if (input.equals("H")) {
                    this.playerHand.add(this.gameDeck.takeCard());
                    Card currentCard = this.playerHand.get(playerHand.size()-1);
                    this.playerTotal += currentCard.getCardValue();

                    if (currentCard.getRank().equals("A")) this.countAcePlayer();

                    System.out.println("New card: " + currentCard.printCard());
                } else if (!input.equals("H") && !input.equals("S")) {
                    System.out.println("Invalid input");
                } else if (input.equals("S")) {
                    break;
                }
            }

            if (playerTotal > 21) this.playerBust(); else this.dealerLoop();
        }
    }

    public void dealerLoop() {
        // Check for less than 16 or soft 17 from dealer
        while (dealerTotal <= 16 || this.dealerHasSoft17()) {
            this.dealerHand.add(this.gameDeck.takeCard());
            Card currentCard = this.dealerHand.get(dealerHand.size()-1);
            this.dealerTotal += currentCard.getCardValue();

            if (currentCard.getRank().equals("A")) this.countAceDealer();

            System.out.println("Dealer new card: " + currentCard.printCard());
        }

        if (dealerTotal > 21) this.dealerBust(); else this.checkWin();
    }

    public void countAcePlayer() {
        for (int i = 0; i < this.playerHand.size(); i++) {
            if (this.playerHand.get(i).getRank().equals("A") && this.playerTotal > 21) {
                this.playerTotal -= 10;
            }
        }
    }

    public void countAceDealer() {
        for (int i = 0; i < this.dealerHand.size(); i++) {
            if (this.dealerHand.get(i).getRank().equals("A") && this.dealerTotal > 21) {
                this.dealerTotal -= 10;
            }
        }
    }

    public void checkWin() {
        if (dealerTotal == playerTotal) {
            gameWin(Players.DEALER);
        } else if (dealerTotal == 21) {
            gameWin(Players.DEALER);
        } else if (playerTotal == 21) {
            gameWin(Players.USER);
        } else if (playerTotal < dealerTotal) {
            gameWin(Players.DEALER);
        } else {
            gameWin(Players.USER);
        }
    }

    public boolean dealerHasSoft17() {
        for (Card card: this.dealerHand) {
            if (card.getRank().equals("A") && this.dealerTotal == 17) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Card> getPlayerHand() {
        return this.playerHand;
    }

    public ArrayList<Card> getDealerHand() {
        return this.dealerHand;
    }

    public void addToPlayerHand(Card card) {
        this.playerHand.add(card);
    }

    public void addToDealerHand(Card card) {
        this.dealerHand.add(card);
    }

    public void setPlayerTotal(int newTotal) {
        this.playerTotal = newTotal;
    }

    public void setDealerTotal(int newTotal) {
        this.dealerTotal = newTotal;
    }

    public Players getWinner() {
        return this.winner;
    }

    private void gameWin(Players player) {
        String output = (player == Players.USER) ? "Player wins!" : "Dealer wins!";
        System.out.println(output);
        System.out.println("Player Total: " + this.playerTotal);
        System.out.println("Dealer Total: " + this.dealerTotal);
        this.printDealerCards();
        this.winner = player;
    }

    private void playerBust() {
        System.out.println("Player busts with " + this.playerTotal);
        System.out.println("Dealer wins with "  + this.dealerTotal);
        this.printDealerCards();
    }

    private void dealerBust() {
        System.out.println("Dealer busts with " + this.dealerTotal);
        System.out.println("Player wins wtih " + this.playerTotal);
        this.printDealerCards();
    }

    private void printDealerCards() {
        System.out.print("Dealers Cards: ");
        for (Card card: this.dealerHand) {
            System.out.print(card.printCard() + " ");
        }
        System.out.print("\n");
    }
}
