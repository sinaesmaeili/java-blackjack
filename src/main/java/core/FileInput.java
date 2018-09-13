package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileInput {

    public ArrayList<String> commands;
    private ArrayList<Card> playerHand = new ArrayList<Card>();
    private ArrayList<Card> dealerHand = new ArrayList<Card>();
    private int playerTotal = 0;
    private int dealerTotal = 0;
    private String fileName;
    private boolean dealerWins;

    public static void main(String[] arg) {
        FileInput game = new FileInput("src/assets/input.txt");
        game.readFile();
        game.dealCards();
        game.mainLoop();
        game.checkWin();
    }

    public FileInput(String file) {
        this.fileName = file;
    }

    public void readFile() {
        String fileName = this.fileName;
        String line;

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                this.commands = new ArrayList<String>(Arrays.asList(line.split("\\s+")));
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    public void dealCards() {
        for (int i = 0; i < 4; i++) {
            Card card;
            if (this.commands.get(i).charAt(1) == '1' && this.commands.get(i).charAt(2) == '0') {
                String cardRank = "10";
                card = new Card(this.commands.get(i).charAt(0), cardRank);
            } else {
                card = new Card(this.commands.get(i).charAt(0), this.commands.get(i).charAt(1));
            }

            if (i < 2) this.playerHand.add(card); else this.dealerHand.add(card);
        }

        System.out.println("Player receives the " + this.playerHand.get(0).printCard() + " and " + this.playerHand.get(1).printCard());
        System.out.println("Dealer receives the " + this.dealerHand.get(0).printCard() + " and " + this.dealerHand.get(1).printCard());
    }

    public void mainLoop() {
        if (this.commands.size() == 4) {
            checkWin();
        } else {
            boolean commandsRemain = true;
            int i = 4; // Starts at 4 due to first four cards dealt to player and dealer

            while (commandsRemain) {
                if (this.commands.get(i).equals("H")) {
                    System.out.println("Player hits");
                    Card card = new Card(this.commands.get(i+1).charAt(0), this.commands.get(i+1).charAt(1));
                    this.playerHand.add(card);
                    System.out.println("Player receives " + card.printCard());
                } else if (this.commands.get(i).equals("S")) {
                    System.out.println("Player stands");
                    for (int j = i+1; j < this.commands.size(); j++) {
                        Card card = new Card(this.commands.get(j).charAt(0), this.commands.get(j).charAt(1));
                        this.dealerHand.add(card);
                        System.out.println("Dealer receives " + card.printCard());
                    }

                    commandsRemain = false;
                }
                i++;
            }
        }

        this.tallyPointsPlayer();
        this.tallyPointsDealer();
        this.checkWin();
    }

    public void tallyPointsPlayer() {
        for (Card card: this.playerHand) {
            playerTotal += card.getCardValue();
        }

        this.countAcePlayer();
    }

    public void tallyPointsDealer() {
        for (Card card: this.dealerHand) {
            dealerTotal += card.getCardValue();
        }

        this.countAceDealer();
    }

    public void countAcePlayer() {
        for (int i = 0; i < this.playerHand.size(); i++) {
            if (this.playerHand.get(i).getRank().equals("A") && this.playerTotal > 21) {
                this.playerTotal -= 10;
            }
        }
        System.out.println("player: " + playerTotal);
    }

    public void countAceDealer() {
        for (int i = 0; i < this.dealerHand.size(); i++) {
            if (this.dealerHand.get(i).getRank().equals("A") && this.dealerTotal > 21) {
                this.dealerTotal -= 10;
            }
        }
        System.out.println("dealer: " + dealerTotal);
    }

    public void checkWin() {
        if (dealerTotal == playerTotal) {
            System.out.println("Dealer wins");
            this.dealerWins = true;
        } else if (dealerTotal == 21) {
            System.out.println("Dealer wins");
            this.dealerWins = true;
        } else if (playerTotal == 21) {
            System.out.println("Player wins");
            this.dealerWins = false;
        } else if (playerTotal > 21) {
            System.out.println("Dealer wins");
            this.dealerWins = true;
        } else if (dealerTotal > 21) {
            System.out.println("Player wins");
            this.dealerWins = false;
        } else if (playerTotal < dealerTotal) {
            System.out.println("Dealer wins");
            this.dealerWins = true;
        } else {
            System.out.println("Player wins");
            this.dealerWins = false;
        }
    }

    public boolean winnerIsDealer() {
        return dealerWins;
    }

    public ArrayList<Card> getPlayerHand() {
        return this.playerHand;
    }

    public ArrayList<Card> getDealerHand() {
        return this.dealerHand;
    }

    public int getPlayerTotal() {
        return this.playerTotal;
    }

    public int getDealerTotal() {
        return this.dealerTotal;
    }
}
