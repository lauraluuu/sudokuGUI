package sudoku;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class stores date relating to the Sudoku Game and functionality of the
 * sudoku game
 *
 * @author Peifen Lu 18008550
 */
public class Data {

    boolean loginNotify = false;
    boolean quitNotify = false;
    boolean submitNotify = false;
    boolean newGameNotify = false;
    boolean gameRuleNotify = false;
    boolean solved = false;
    int playerScore = 0;

    public int[][] aBoard = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},};

    /**
     * This method generate an ArrayList with 9 numbers (1-9)
     *
     * @return numbers
     */
    public ArrayList<Integer> generateNineNums() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        for (int i = 1; i < 10; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        return numbers;
    }

    /**
     * This method generate a completed Sudoku puzzle aBoard
     *
     * @return answerPuzzle
     */
    public int[][] generateAnswer() {
        ArrayList<Integer> newList = generateNineNums();

        for (int col = 0; col < 9; col++) {
            {
                aBoard[0][col] = newList.get(col);
            }
        }

        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                switch (i) {
                    case 1:
                    case 2:
                    case 4:
                    case 5:
                    case 7:
                    case 8:
                        if (j < 6) {
                            aBoard[i][j] = aBoard[i - 1][j + 3];
                        } else {
                            aBoard[i][j] = aBoard[i - 1][j - 6];
                        }
                        break;
                    case 3:
                    case 6:
                        int temp = aBoard[i - 1][0];
                        if (j != 8) {
                            aBoard[i][j] = aBoard[i - 1][j + 1];
                        } else {
                            aBoard[i][j] = temp;
                        }
                        break;
                }
            }
        }

        int[][] answerPuzzle = new int[9][9];

        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                answerPuzzle[i][j] = this.aBoard[i][j];
            }
        }
        return answerPuzzle;
    }
}
