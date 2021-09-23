package sudoku;

/**
 * This main class store view, model and control
 * @author Peifen Lu 18008550
 */
public class SudokuMain {

    public static void main(String[] args) {
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);
        model.addObserver(view); 
    }
}
