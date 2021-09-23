package sudoku;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class displays an interface for player according to the model
 * and updates its interface according to the change of the model data
 * @author Peifen Lu 18008550
 */
public class View extends JFrame implements Observer {

    private JLabel userName = new JLabel("Username: ");
    private JLabel userPin = new JLabel("Password: ");
    JTextField userInput = new JTextField();
    JTextField passwordInput = new JTextField();
    private JLabel wrongLogin = new JLabel("Wrong login information!!!");

    private JButton newButton = new JButton("New Game");
    private JButton quitButton = new JButton("Quit");
    private JButton loginButton = new JButton("Login");
    private JButton submitButton = new JButton("Submit");
    private JButton ruleButton = new JButton("Game Rule");
    JTextField[][] puzzleField = new JTextField[9][9];

    private boolean isBegined = false;

    /**
     * Constructor: sets up the player login interface
     */
    public View() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        userName.setLocation(10, 10);
        userName.setSize(80, 30);
        userName.setBackground(Color.LIGHT_GRAY);

        userInput.setLocation(85 , 10);
        userInput.setSize(80, 30);
        userInput.setBackground(Color.LIGHT_GRAY);
        

        userPin.setLocation(85 * 2 + 10, 10);
        userPin.setSize(80, 30);
        userPin.setBackground(Color.LIGHT_GRAY);

        passwordInput.setLocation(85 * 3 , 10);
        passwordInput.setSize(80, 30);
        passwordInput.setBackground(Color.LIGHT_GRAY);

        loginButton.setLocation(85 * 4 + 10, 10);
        loginButton.setSize(80, 30);
        loginButton.setBackground(Color.LIGHT_GRAY);
        
        getContentPane().add(userName);
        getContentPane().add(userInput);
        getContentPane().add(userPin);
        getContentPane().add(passwordInput);
        getContentPane().add(loginButton);

        setSize(470, 100);
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * This method sets up the sudoku game interface after login
     */
    public void startGame() {

        this.getContentPane().removeAll();

        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        newButton.setLocation(10, 10);
        newButton.setSize(100, 30);

        quitButton.setLocation(100 + 10 + 15, 10);
        quitButton.setSize(100, 30);

        submitButton.setLocation(100 * 2 + 10 + 15 * 2, 10);
        submitButton.setSize(100, 30);

        ruleButton.setLocation(100 * 3 + 10 + 15 * 3, 10);
        ruleButton.setSize(100, 30);

        getContentPane().add(newButton);
        getContentPane().add(quitButton);
        getContentPane().add(submitButton);
        getContentPane().add(ruleButton);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                puzzleField[i][j] = new JTextField("");
                puzzleField[i][j].setHorizontalAlignment(JTextField.CENTER);
                puzzleField[i][j].setLocation(10 + j * 50, 50 + i * 50);
                puzzleField[i][j].setSize(50, 50);
                puzzleField[i][j].setBorder(BorderFactory.createLineBorder(Color.black,1));
                
                if(j>=0&&j<=2){
                    if(i>=3&&i<=5){
                        puzzleField[i][j].setBackground(Color.LIGHT_GRAY);
                    }
                }else if(j>=3&&j<=5){
                    if(i<3||i>5){
                        puzzleField[i][j].setBackground(Color.LIGHT_GRAY);
                    }
                }else{
                    if(i>=3&&i<=5){
                        puzzleField[i][j].setBackground(Color.LIGHT_GRAY);
                        
                    }
                }
                getContentPane().add(puzzleField[i][j]);
            }
        }

        this.revalidate();
        this.repaint();

        setSize(490, 550);
        setLocationRelativeTo(null);
    }

    public void addActionListener(ActionListener listener) {
        this.loginButton.addActionListener(listener);
        this.submitButton.addActionListener(listener);
        this.quitButton.addActionListener(listener);
        this.ruleButton.addActionListener(listener);
        this.newButton.addActionListener(listener);
    }

    /**
     * This method sets up an interface for quiting the game
     * @param score 
     */
    private void quitGame(int score) {
        this.getContentPane().removeAll();

        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLabel scoreLabel = new JLabel("Your final score is: " + score);
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 20));
        scoreLabel.setLocation(120, 200);
        scoreLabel.setSize(200, 50);
        getContentPane().add(scoreLabel);

        scoreLabel.revalidate();
        scoreLabel.repaint();
        setSize(500, 500);
        setLocationRelativeTo(null);
    }

    /**
     * This method checks whether all the field has been filled up yet or has any String input
     * @return boolean indicates the text field has any text
     */
    public boolean checkText() {

        int[][] tempAnswer=new int[9][9];
        
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                try {
                    tempAnswer[x][y] = 0;
                    tempAnswer[x][y] = Integer.parseInt(puzzleField[x][y].getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "You have to fill in all empty spot. please input again");
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * This method defines the events when the model has been changed.
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {

        Data data = (Data) arg;

        if (!data.loginNotify) {
            this.userInput.setText("");
            this.passwordInput.setText("");
            JOptionPane.showMessageDialog(null, "Wrong password! Please login again!!!", "Login Result", JOptionPane.INFORMATION_MESSAGE);
        } else if (!this.isBegined) {
            this.startGame();
            this.isBegined = true;
            this.setPuzzle(data.aBoard);
            
        } else if (data.quitNotify) {
            this.quitGame(data.playerScore);
        } else if (data.newGameNotify) {
            this.setPuzzle(data.aBoard);
            data.newGameNotify = false;
        } else if (data.submitNotify) {
            if (checkText()) {
                if (data.solved) {
                    JOptionPane.showMessageDialog(null, "You solved the puzzle!", "Game Result", JOptionPane.INFORMATION_MESSAGE);
                    data.playerScore += 10;
                } else {
                    JOptionPane.showMessageDialog(null, "You did not solve the puzzle!", "Game Result", JOptionPane.INFORMATION_MESSAGE);
                    data.playerScore -= 10;
                }
            }
            data.submitNotify = false;
        } else if (data.gameRuleNotify) {
            JOptionPane.showMessageDialog(null, "Fill empty squares so each number appears exactly once in each row, column and 3x3 box", "Game Rule", JOptionPane.INFORMATION_MESSAGE);
            data.gameRuleNotify = false;
        }
    }

    /**
     * This method updates the puzzle
     * @param board 
     */
    public void setPuzzle(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0) {
                    puzzleField[i][j].setText("" + board[i][j]);
                    puzzleField[i][j].setEditable(false);

                } else {
                    puzzleField[i][j].setText("");
                    puzzleField[i][j].setEditable(true);

                }
            }
        }
        this.repaint();
    }
}
