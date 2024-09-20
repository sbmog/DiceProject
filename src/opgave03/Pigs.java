package opgave03;

import java.util.Scanner;

public class Pigs {
    private static int totalRolls = 0;
    private static int player1Rounds = 0;
    private static int player2Rounds = 0;

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
        int player1Score = 0;
        int player2Score = 0;
        boolean player1Turn = true;

        //Skifter mellem spilleren og giver besked ift. udfaldet.
        while (player1Score != winningScore && player2Score != winningScore) {

            if (player1Turn) {
                System.out.println("Spiller 1's tur");
                player1Rounds++;
                int roundScore = playOneRoundWithTwoDice(player1Score, player2Score);
                //Hvis spiller kommer over vinderpoint

                if (player1Score + roundScore > winningScore) {
                    System.out.println("Spiller 1 overskred det samlede antal point. Runden slutter, og spilleren fik ingen point for runden");
                } else {
                    player1Score += roundScore;
                }
                System.out.println("Spiller 1'samlese point: " + player1Score);

            } else {
                System.out.println("Spiller 2's tur");
                player2Rounds++;
                int roundScore = playOneRoundWithTwoDice(player1Score, player2Score);

                if (player2Score + roundScore > winningScore) {
                    System.out.println("Spiller 2 overskred det samlede antal point. Runden slutter, og spilleren fik ingen point for runden");
                } else {
                    player2Score += roundScore;
                }
                System.out.println("Spiller 2' samlede point: " + player2Score);
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


    private static int playOneRoundWithTwoDice(int player1Score, int player2Score) {
        Scanner input = new Scanner(System.in);
        int sumForThisRound = 0;
        boolean continueRolling = true;

        while (continueRolling) {
            System.out.println("Rul en terning? ('ja/nej')");
            String answer = input.nextLine();
            if (answer.equals("nej")) {
                break;
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
                sumForThisRound = 0;
                break;
            } else if (face[0] == 1 || face[1] == 1) {
                System.out.println("Du rulle en 1'er! Ingen point for denne runde.");
                sumForThisRound = 0;
                break;
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