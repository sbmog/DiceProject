package opgave02;

public class Craps {
    public static void main(String[] args) {
        System.out.println("Velkommen til spillet Craps.");
        printRules();
        System.out.println();

        playCraps();

        System.out.println();
        System.out.println("Tak for at spille Craps.");
    }

    public static void playCraps() {

        int[] faces = rollDice();
        int sum = faces[0] + faces[1];
        System.out.println("Du rullede: [" + faces[0] + ", " + faces[1] + "] -> summen er: " + sum);

        if (sum == 7 || sum == 11) {
            System.out.println("Tillykke! Du har vundet med dit første kast.");
        } else if (sum == 2 || sum == 3 || sum == 12) {
            System.out.println("Øv! Du har tabt.");
        } else {
            System.out.println("Din sum er nu dine point");
            System.out.println();
            boolean win = rollForPoint(sum);

            if (win) {
                System.out.println("Tillykke! Du har ramt dit point og vundet.");
            } else {
                System.out.println("Øv! Du ramte 7 og har tabt.");
            }
        }
    }

    private static int[] rollDice() {
        int[] rollDice = {(int) (Math.random() * 6 + 1), (int) (Math.random() * 6 + 1)};
        return rollDice;
    }

    public static boolean rollForPoint(int point) {
        while (true) {
            int[] dice = rollDice();
            int sum = dice[0] + dice[1];
            System.out.println("Du rullede: [" + dice[0] + ", " + dice[1] + "] -> summen er: " + sum);

            if (sum == point) {
                return true;
            } else if (sum == 7) {
                return false;
            }
        }
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for Craps");
        System.out.println("Spilleren ruller to terningerne, til sit første kast 'come out roll'.");
        System.out.println("Udfaldet af et kast er summen af terningernes øjne.");
        System.out.println("Spillerne vinder med det samme, så frem det første kast er 7 eller 11");
        System.out.println("Spilleren taber med det samme, så frem det første kast er 2, 3, eller 10");
        System.out.println("Hvis spillerens første kast er 4, 5, 6, 8, 9 eller 10, vil dette nu være spillerens point.");
        System.out.println("Spilleren kaster indtil de har kastet sit point igen eller kaster 7.");
        System.out.println("Kaster man sit point, så har man vundet. Kaster man 7, så har man tabt.");
        System.out.println("=====================================================");
    }
}
