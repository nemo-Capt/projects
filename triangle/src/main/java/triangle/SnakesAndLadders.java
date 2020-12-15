package triangle;

class SnakesAndLadders {
    private int[] positionArray = {0, 0};
    private int turn = 1;
    private boolean gameOver = false;

    String play(int die1, int die2) {
        int oldTurn = turn;
        if (gameOver) {
            return "Game over!";
        }

        if (positionArray[turn - 1] + die1 + die2 == 100) {
            gameOver = true;
            return "Player " + turn + " Wins!";
        }

        positionArray[turn - 1] = doMove(die1, die2, positionArray[turn - 1]);
        positionArray[oldTurn - 1] = checkLadder(positionArray[oldTurn - 1]);
        positionArray[oldTurn - 1] = checkSnake(positionArray[oldTurn - 1]);
        return "Player " + oldTurn + " is on square " + positionArray[oldTurn - 1];
    }

    private int doMove(int die1, int die2, int position) {
        if (die1 == die2) {
            position += die1 + die2;
        } else {
            position += die1 + die2;
            if (turn == 1) {
                turn = 2;
            } else {
                turn = 1;
            }
        }
        if (position > 100) {
            position = 100 - (position - 100);
        }
        return position;
    }

    private int checkLadder(int position) {
        switch (position) {
            case 2:
                return 38;
            case 7:
                return 14;
            case 8:
                return 31;
            case 15:
                return 26;
            case 21:
                return 42;
            case 28:
                return 84;
            case 36:
                return 44;
            case 51:
                return 67;
            case 71:
                return 91;
            case 78:
                return 98;
            case 87:
                return 94;
            default:
                return position;
        }
    }

    private int checkSnake(int position) {
        switch (position) {
            case 16:
                return 6;
            case 46:
                return 25;
            case 49:
                return 11;
            case 62:
                return 19;
            case 64:
                return 60;
            case 74:
                return 53;
            case 89:
                return 68;
            case 92:
                return 88;
            case 95:
                return 75;
            case 99:
                return 80;
            default:
                return position;
        }
    }

}
