package opgave03;

import java.util.Scanner;

public class Pigs {
    private static int totalRolls = 0;
    private static int player1Rounds = 0;
    private static int player2Rounds = 0;
    private static int player1Score = 0;
    private static int player2Score = 0;

    public static void main(String[] args) {
        System.out.println("Velkommen til spillet Pigs.");
        printRules();
        System.out.println();

        playPics();

        System.out.println();
        System.out.println("Tak for at spille Pigs.");
    }

    public static void playPics() {
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast det antal point, der ønskes at spilles til. F.eks. 100:");
        int winningScore = input.nextInt();
        boolean player1Turn = true;

        //Skifter mellem spilleren og giver besked ift. udfaldet.
        while (player1Score != winningScore && player2Score != winningScore) {

            if (player1Turn) {
                System.out.println("Spiller 1's tur");
                player1Rounds++;
                playerPlaysARound(player1Score, winningScore);

            } else {
                System.out.println("Spiller 2's tur");
                player2Rounds++;
                playerPlaysARound(player2Score, winningScore);
            }
            player1Turn = !player1Turn;
        }

        if (player1Score == winningScore) {
            System.out.println("Spiller 1 har vundet!");
        } else if (player2Score == winningScore) {
            System.out.println("Spiller 2 har vundet!");
        }
        printStatistics();
        input.close();
    }


    public static void playerPlaysARound(int thePlayerPlaying, int winningScore) {
        int roundScore = playOneRoundWithTwoDice();
        //Hvis spiller kommer over vinderpoint

        if (thePlayerPlaying + roundScore > winningScore) {
            System.out.println("Spilleren overskred det samlede antal point. Runden slutter, og spilleren fik ingen point for runden");
        } else {
            thePlayerPlaying += roundScore;
        }
        System.out.println("Spillerens samlede point: " + thePlayerPlaying);
    }

    private static int playOneRoundWithTwoDice() {
        Scanner input = new Scanner(System.in);
        int sumForThisRound = 0;
        boolean continueRolling = true;

        while (continueRolling) {
            System.out.println("Rul en terning? ('ja/nej')");
            String answer = input.nextLine();
            if (answer.equals("nej")) {
                return sumForThisRound;
            }
            int[] face = rollTwoDice();
            totalRolls++;
            System.out.println("Du rullede: [" + face[0] + ", " + face[1] + "]");

            if (face[0] == 1 && face[1] == 1) {
                System.out.println("Du rullede to 1'er! Dine point bliver nu nulstillet.");
                if (player1Rounds > player2Rounds) {
                    player1Score = 0;
                } else {
                    player2Score = 0;
                }
                return sumForThisRound = 0;
            } else if (face[0] == 1 || face[1] == 1) {
                System.out.println("Du rulle en 1'er! Ingen point for denne runde.");
                return sumForThisRound = 0;
            } else {
                sumForThisRound += face[0] + face[1];
                System.out.println("Rundens point: " + sumForThisRound);
            }
        }
        return sumForThisRound;
    }

    private static void printStatistics() {
        double averageRollsPerTurn1 = (double) totalRolls / player1Rounds;
        double averageRollsPerTurn2 = (double) totalRolls / player2Rounds;
        System.out.println("\nSpilstatestik:");
        System.out.println("-------");
        System.out.printf("%45s %4d\n", "Antal rul i alt:", totalRolls);
        System.out.printf("%45s %4d\n", "Gennemsnitligt antal kast pr. runde for spiller 1: ", averageRollsPerTurn1);
        System.out.printf("%45s %4d\n", "Gennemsnitligt antal kast pr. runde for spiller 1: ", averageRollsPerTurn2);

    }

    private static int[] rollTwoDice() {
        int[] rollDice = {(int) (Math.random() * 6 + 1), (int) (Math.random() * 6 + 1)};
        return rollDice;
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for Pigs - et to-personersspil.");
        System.out.println("Spiller 1 ruller terningerne, indtil de kaster en 1'er eller beslutter sig for at stoppe.");
        System.out.println("Hvis der kastes en 1'er, får man ingen point for runden.");
        System.out.println("Hvis man vælger at stoppe lægges summen af alle kast for runden til ens samlede antal point.");
        System.out.println("Herefter bliver det spiller 2's tur.");
        System.out.println("Den første, som når det antal point, der er aftalt at spille til (f.eks. 100), vinder!");
        System.out.println("=====================================================");
    }

}