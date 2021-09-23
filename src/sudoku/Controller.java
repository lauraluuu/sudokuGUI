package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class translates the player's interactions with the view into actions
 * that model will perform
 * @author Peifen Lu 18008550
 */
public class Controller implements ActionListener {

    public View view;
    public Model model;

    /**
     * Constructor: sets up view and model
     * @param view
     * @param model 
     */
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this); 
    }

    /**
     * This override method defines the ActionEvents according to the buttons
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); 
        if (command.equals("Login")) {
            String username = this.view.userInput.getText(); 
            String password = this.view.passwordInput.getText(); 
            System.out.println("You clicked the Login button!");
            this.model.checkName(username, password); 
        } else if (command.equals("New Game")) { 
            System.out.println("You clicked the New Game button!");
            this.model.isNewGame();
        } else if (command.equals("Quit")) { 
            System.out.println("You clicked the Quit button!");
            this.model.quitGame(); 
        }else if(command.equals("Submit")){
            System.out.println("You clicked the Submit button!");
            
            int[][] userBoard=new int[9][9];
            
            if(this.view.checkText()){
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        userBoard[i][j] = Integer.parseInt(this.view.puzzleField[i][j].getText());
                    }
                }
            }
            
            this.model.isSubmit(userBoard);
        }else if(command.equals("Game Rule")){
            System.out.println("You clicked the Game Rule button!");
            this.model.isGameRule();
        }
    }

}
