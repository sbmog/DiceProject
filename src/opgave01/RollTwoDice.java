package opgave01;

import java.util.Scanner;

public class RollTwoDice {
    private static int rollCount = 0;
    private static int totalSum = 0;
    private static int sameThrowCount = 0;
    private static int biggestThrow = 0;
    private static int[] valueOccur = new int[6];

    public static void main(String[] args) {
        System.out.println("Velkommen til spillet, rul to terninger.");
        printRules();
        System.out.println();

        playTwoDice();

        System.out.println();
        System.out.println("Tak for at spille, rul to terninger.");
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for rul to terninger");
        System.out.println("Spilleren ruller terningerne, så længe man har lyst.");
        System.out.println("=====================================================");
    }

    private static void playTwoDice() {
        Scanner input = new Scanner(System.in);
        System.out.print("Rul terningerne? ('ja/nej') ");
        String answer = input.nextLine();
        while (!answer.equals("nej")) {
            int[] faces = rollDice();
            System.out.println("Du rullede: [" + faces[0] + ", " + faces[1] + "]");
            System.out.println();

            updateStatistics(faces);

            System.out.print("Rul terningerne? ('ja/nej') ");
            answer = input.nextLine();
        }

        printStatistics();
        input.close();
    }

    private static int[] rollDice() {
        int[] rollDice = {(int) (Math.random() * 6 + 1), (int) (Math.random() * 6 + 1)};
        return rollDice;
    }


    private static void updateStatistics(int[] diceThrow) {
        rollCount++;
        totalSum += diceThrow[0] + diceThrow[1];
        if (diceThrow[0] == diceThrow[1]) {
            sameThrowCount++;
        }
        int currentSumOfThrow = diceThrow[0] + diceThrow[1];
        if (currentSumOfThrow > biggestThrow) {
            biggestThrow = currentSumOfThrow;
        }
        valueOccur[diceThrow[0] - 1]++;
        valueOccur[diceThrow[1] - 1]++;
    }

    private static void printStatistics() {
        System.out.println("\nResults:");
        System.out.println("-------");
        System.out.printf("%45s %4d\n", "Antal rul:", rollCount);
        System.out.printf("%45s %4d\n", "Summen af alle rul:", totalSum);
        System.out.printf("%45s %4d\n", "Antal gange med samme antal øjne i et kast:", sameThrowCount);
        System.out.printf("%45s %4d\n", "Summen af det største kast:", biggestThrow);
        for (int indeks = 0; indeks < valueOccur.length; indeks++) {
            System.out.printf("%45s %4d\n", "Antal" + (indeks + 1) + "'ere:", valueOccur[indeks]);
        }
    }
}

