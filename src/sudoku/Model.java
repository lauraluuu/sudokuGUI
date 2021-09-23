package sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Random;

/**
 * This class stores data relating to the player and functionalities of the sudoku game 
 * @author Peifen Lu 18008550
 */
public class Model extends Observable {

    public Database db;
    public Data data;
    public String username;

    /**
     * Constructor: initialize the database and sets up the connection between
     * program and database
     */
    public Model() {
        this.db = new Database();
        this.db.dbsetup();
    }

    /**
     * This method checks whether the player exists in the database
     * after the player login and call creating a puzzle method after successful
     * login and notify the changes
     * @param name
     * @param pin 
     */
    public void checkName(String name, String pin) {
        this.username = name;

        this.data = this.db.checkName(name, pin);

        if (data.loginNotify) {
            this.newPuzzle();
        }

        this.setChanged();

        this.notifyObservers(this.data);
    }


    /**
     * This method randomly empty positions from the completed puzzle to
     * generate a Sudoku puzzle for player to play
     * @param rightPuzzle
     * @return rightPuzzle
     */
    public int[][] generatePuzzle(int rightPuzzle[][]) {

        Random r = new Random();
        int index = 80;

        for (int space = 0; space < 40; space++) {
            int temp = r.nextInt(index + 1);
            int x = temp % 9;
            int y = temp / 9;
            rightPuzzle[x][y] = 0;
        }
        return rightPuzzle;
    }

    /**
     * This method generates a new puzzle
     * @return onePuzzle
     */
    public int[][] getPuzzle() {
        int[][] puzzleAnswer = data.generateAnswer();

        int[][] onePuzzle = generatePuzzle(puzzleAnswer);

        return onePuzzle;
    }

    /**
     * This method update the puzzle/board in the data
     */
    public void newPuzzle() {
        this.data.aBoard = getPuzzle();
    }
    /**
     * This method generate an ArrayList with 9 numbers (1-9)
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
     * This method generate a solved Sudoku puzzle Board
     * @return answerPuzzle
     */
    public int[][] generateAnswer() {
        ArrayList<Integer> newList = generateNineNums();
        int[][] aBoard = new int[9][9];

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
                answerPuzzle[i][j] = aBoard[i][j];
            }
        }
        return answerPuzzle;
    }

    /**
     * This method updates the data in the database after the player quit the game
     * and notify the changes
     */
    public void quitGame() {
        this.db.quitGame(this.data.playerScore, this.username);
        this.data.quitNotify = true;
        this.setChanged();
        this.notifyObservers(this.data);
    }
    /**
     * This method checks the puzzle aBoard and determine whether or not
     * the Sudoku has been completed
     * @return boolean to decide whether the Sudoku is completed
     */
    public boolean isFullSudoku(int[][] userBoard) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (userBoard[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * This method calls three methods above to determine whether the Sudoku
     * puzzle has been solved or not
     * @return boolean to decide whether the Sudoku is solved by the player
     */
    public void puzzleChecker(int[][] userBoard) {
        data.solved = false;
        if (isRowOK(userBoard) && isColOK(userBoard) && isFullSudoku(userBoard)) {
            data.solved = true;
        }
    }
    /**
     * This isRowOK method determine whether each row in this board 
     * has 9 numbers with a sum of 45
     * @return a boolean to decide whether each row meets Sudoku Game Rule
     */
    public boolean isRowOK(int[][] userBoard) {
        for (int i = 0; i < 9; i++) {
            int sum = 0;
            for (int j = 0; j < 9; j++) {
                sum = sum + userBoard[i][j];
            }
            if (sum != 45) {
                return false;
            }
        }
        return true;
    }
    /**
     * This isColOK method determine whether each column in this board 
     * has 9 numbers with a sum of 45
     * @return a boolean to decide whether each column meets Sudoku Game Rule
     */
    public boolean isColOK(int[][] userBoard) {
        for (int j = 0; j < 9; j++) {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum = sum + userBoard[i][j];
            }
            if (sum != 45) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method notify that the game rule button has been pressed
     */
    public void isGameRule() {
        this.data.gameRuleNotify = true;
        this.setChanged();
        this.notifyObservers(this.data);
    }
    /**
     * This method calls the newPuzzle() method to create a new puzzle
     */
    public void isNewGame(){
        this.data.newGameNotify=true;
        newPuzzle();
        this.setChanged();
        this.notifyObservers(this.data);
    }
    /**
     * This method calls the puzzleChecker() method the check the puzzle
     */
    public void isSubmit(int[][] userBoard){
        this.data.submitNotify=true;
        puzzleChecker(userBoard);
        this.setChanged();
        this.notifyObservers(this.data);
    }
}
