Metoden playOneRoundWithTwoDice(int player1Score, int player2Score) har to parameter. Da typen af de parameter er int bliver den
kaldt med call by value, hvilket vil sige at værdien af parameterene i selve kaldet

    int roundScore = playOneRoundWithTwoDice(player1Score, player2Score);

bliver kopieret. Så når I nulstiller scorene.

    player1Score = 0;

nulstiller I parameter variablerne ikke variablerne som der bliver kaldt med. 
Så

        int player1Score = 0;
        int player2Score = 0;

bliver aldrig nulstillet.

---

if delen og else delen i denne if-else sætning 

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

er næsten ens. Kan I lave en metode der tager et parameter og simplificerer dette?

---

Ret godt at I kan gennemskue denne kode

    player1Turn = !player1Turn;

til at skifte spiller

---

Jeg er ikke vild med breaks som I har i linje 93 og 97. Da det eneste I har efter while løkken er

    return sumForThisRound;

kan I bare lave returns i stedet for de breaks.

---

