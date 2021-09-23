package sudoku;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class stores six unit tests to test the functionality of sudoku game
 * @author Peifen Lu 18008550
 * 
 */
public class ModelTest {

    public ModelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isFullSudoku method, of class Model.
     */
    @Test
    public void testIsFullSudokuWithPuzzleIsFull() {
        System.out.println("isFullSudoku");

        int[][] userBoard = {
            {5, 1, 9, 6, 2, 3, 8, 4, 7},
            {6, 8, 4, 9, 1, 7, 5, 2, 3},
            {7, 2, 3, 5, 8, 4, 9, 6, 1},
            {3, 9, 6, 7, 4, 8, 1, 5, 2},
            {4, 7, 1, 2, 5, 6, 3, 9, 8},
            {8, 5, 2, 1, 3, 9, 6, 7, 4},
            {9, 6, 8, 3, 7, 2, 4, 1, 5},
            {2, 4, 5, 8, 6, 1, 7, 3, 9},
            {1, 3, 7, 4, 9, 5, 2, 8, 6},};

        Model instance = new Model();
        boolean expResult = true;
        boolean result = instance.isFullSudoku(userBoard);
        assertEquals(expResult, result);
    }

    /**
     * Test of isFullSudoku method, of class Model.
     */
    @Test
    public void testIsFullSudokuWithPuzzleIsNotFull() {
        System.out.println("isFullSudoku");

        int[][] userBoard = {
            {0, 0, 0, 6, 2, 3, 8, 4, 7},
            {6, 8, 4, 9, 1, 7, 5, 2, 3},
            {7, 2, 3, 5, 8, 4, 9, 6, 1},
            {3, 9, 6, 7, 4, 8, 1, 5, 2},
            {4, 7, 1, 2, 5, 6, 3, 9, 8},
            {8, 5, 2, 1, 3, 9, 6, 7, 4},
            {9, 6, 8, 3, 7, 2, 4, 1, 5},
            {2, 4, 5, 8, 6, 1, 7, 3, 9},
            {1, 3, 7, 4, 9, 5, 2, 8, 6},};

        Model instance = new Model();
        boolean expResult = false;
        boolean result = instance.isFullSudoku(userBoard);
        assertEquals(expResult, result);
    }

    /**
     * Test of isRowOK method, of class Model.
     */
    @Test
    public void testIsRowOKwithRightPuzzle() {
        System.out.println("isRowOK");

        int[][] userBoard = {
            {5, 1, 9, 6, 2, 3, 8, 4, 7},
            {6, 8, 4, 9, 1, 7, 5, 2, 3},
            {7, 2, 3, 5, 8, 4, 9, 6, 1},
            {3, 9, 6, 7, 4, 8, 1, 5, 2},
            {4, 7, 1, 2, 5, 6, 3, 9, 8},
            {8, 5, 2, 1, 3, 9, 6, 7, 4},
            {9, 6, 8, 3, 7, 2, 4, 1, 5},
            {2, 4, 5, 8, 6, 1, 7, 3, 9},
            {1, 3, 7, 4, 9, 5, 2, 8, 6},};

        Model instance = new Model();
        boolean expResult = true;
        boolean result = instance.isRowOK(userBoard);
        assertEquals(expResult, result);
    }

    /**
     * Test of isRowOK method, of class Model.
     */
    @Test
    public void testIsRowOKwithWrongPuzzle() {
        System.out.println("isRowOK");

        int[][] userBoard = {
            {5, 1, 9, 6, 2, 3, 8, 4, 7},
            {6, 8, 4, 9, 1, 7, 5, 2, 3},
            {7, 2, 3, 5, 8, 4, 9, 6, 1},
            {3, 9, 6, 7, 4, 8, 1, 5, 2},
            {4, 7, 1, 2, 5, 6, 3, 9, 8},
            {8, 5, 2, 1, 3, 9, 6, 7, 4},
            {9, 6, 8, 3, 7, 2, 4, 1, 5},
            {2, 4, 5, 8, 6, 1, 7, 3, 9},
            {1, 3, 7, 4, 9, 5, 2, 8, 6},};

        Model instance = new Model();
        boolean expResult = true;
        boolean result = instance.isRowOK(userBoard);
        assertEquals(expResult, result);
    }

    /**
     * Test of isColOK method, of class Model.
     */
    @Test
    public void testIsColOKwithRightPuzzle() {
        System.out.println("isColOK");

        int[][] userBoard = {
            {5, 1, 9, 6, 2, 3, 8, 4, 7},
            {6, 8, 4, 9, 1, 7, 5, 2, 3},
            {7, 2, 3, 5, 8, 4, 9, 6, 1},
            {3, 9, 6, 7, 4, 8, 1, 5, 2},
            {4, 7, 1, 2, 5, 6, 3, 9, 8},
            {8, 5, 2, 1, 3, 9, 6, 7, 4},
            {9, 6, 8, 3, 7, 2, 4, 1, 5},
            {2, 4, 5, 8, 6, 1, 7, 3, 9},
            {1, 3, 7, 4, 9, 5, 2, 8, 6},};

        Model instance = new Model();
        boolean expResult = true;
        boolean result = instance.isColOK(userBoard);
        assertEquals(expResult, result);
    }

    /**
     * Test of isColOK method, of class Model.
     */
    @Test
    public void testIsColOKwithWrongPuzzle() {
        System.out.println("isColOK");

        int[][] userBoard = {
            {1, 1, 9, 6, 2, 3, 8, 4, 7},
            {1, 8, 4, 9, 1, 7, 5, 2, 3},
            {7, 2, 3, 5, 8, 4, 9, 6, 1},
            {3, 9, 6, 7, 4, 8, 1, 5, 2},
            {4, 7, 1, 2, 5, 6, 3, 9, 8},
            {8, 5, 2, 1, 3, 9, 6, 7, 4},
            {9, 6, 8, 3, 7, 2, 4, 1, 5},
            {2, 4, 5, 8, 6, 1, 7, 3, 9},
            {1, 3, 7, 4, 9, 5, 2, 8, 6},};

        Model instance = new Model();
        boolean expResult = false;
        boolean result = instance.isColOK(userBoard);
        assertEquals(expResult, result);
    }
}
