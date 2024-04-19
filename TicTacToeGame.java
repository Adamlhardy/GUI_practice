// Name: Adam Hardy
package GUI_practice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

public class TicTacToeGame extends MyJFrame {

    // Variables to keep track of winner count 
    int xWon = 0;
    int oWon = 0;
    
    JPanel jPanel; // we want this to be accessible throughout the class
    ArrayList<JButton> buttons = new ArrayList<JButton>();
    int ROWS = 3, COLUMNS = 3;
    String currentPlayer = "x";
    Font font;
    JMenuBar menuBar;
    JMenu menu;
    JMenu score;
    JMenuItem resetGameItem;

    //Constructor has no parameters
    public TicTacToeGame() {
        //call the parent classes constructor and pass in a title 
        super("Tic Tac Toe Game");

        menuBar = new JMenuBar(); // create the menu bar
        menu = new JMenu("Game options"); // create the menu
        score = new JMenu("Score");
        resetGameItem = new JMenuItem("Reset game"); //create the menu item 
        resetGameItem.addActionListener(e -> ResetGame()); // this registers a listener that will listen for clicks on this button 

        menu.add(resetGameItem); // add the menu item to the game
        menuBar.add(menu); // add the menu to the menu bar
        menuBar.add(score); // add the score to the menu bar
        setJMenuBar(menuBar); // add the menu bar to the JFrame

        jPanel = new JPanel(); // needs to be instantiated
        //jPanel.setLayout(new BorderLayout());

        // jPanel.add(new JButton("OK"), BorderLayout.CENTER);
        // jPanel.add(new JButton("Cancel"), BorderLayout.CENTER);

        
        jPanel.setLayout(new GridLayout(ROWS, COLUMNS));
        // instantiated the font
        font = new Font(Font.SERIF, Font.HANGING_BASELINE, 100);

        for(int i = 0; i < ROWS * COLUMNS; i++){
            JButton btn = new JButton();
            btn.addActionListener(event -> ButtonClicked(event));
            //set the font to on the button
            btn.setFont(font);
            buttons.add(btn);
            jPanel.add(btn);
            
        }

        setContentPane(jPanel);
        setVisible(true);
    }

    /**
     * Will reset the game. This involves resetting who the current player is
     * and resetting the text, color, and enabled status of each button.
     */
    public void ResetGame() {
        currentPlayer = "x";
        for(int i = 0; i < buttons.size(); i++) {
            JButton btn = buttons.get(i);
            btn.setText("");
            btn.setBackground(null);
            btn.setEnabled(true);
        }
    }

    public void ButtonClicked(ActionEvent event) {


        JButton btnClicked = ((JButton)event.getSource());
        btnClicked.setText(currentPlayer);
        //btnClicked.setEnabled(false);
        
        if(currentPlayer == "x") {
            btnClicked.setBackground(Color.RED);
        } else {
            btnClicked.setBackground(Color.GREEN);
        }
        
        boolean winnerFound = CheckWinner();

        boolean allButtonsClicked = true;
        for (JButton button : buttons) { 
            if (button.getText().isEmpty()) {
                allButtonsClicked = false;
                break;
            }
        }
        
        // Increments winner count when player x or player o wins 
        if(winnerFound) {
            if(currentPlayer == "x") {
                xWon++;
            } else {
                oWon++;
            }

            // Updates and displays the new winner record
            updateWinTracker();
            JOptionPane.showMessageDialog(null, currentPlayer + " has won the game!");
        
            // Disables buttons as the game is played
            for(int i = 0; i < buttons.size(); i++) {
                buttons.get(i).setEnabled(false);
            }

        // Else if option for tied game    
        } else if (allButtonsClicked) {
            // Display message for a tied match.
            JOptionPane.showMessageDialog(null, "It's a tie!");

            // Disables buttons as the game is played
            for(int i = 0; i < buttons.size(); i++) {
                buttons.get(i).setEnabled(false);
            }
        }
        
        System.out.println(winnerFound);
        
        SwitchPlayer();
        
    }

    // win tracker function to update and display wins record for each player 
    private void updateWinTracker() {
        score.setText("Score - X Wins: " + xWon + ", O Wins: " + oWon);
    }

    // Allows current player to switch after each turn
    public void SwitchPlayer() {
        if(currentPlayer == "x") {
            currentPlayer = "o";
        } else {
            currentPlayer = "x";
        } 
    }

    public boolean CheckWinner() {

        // first row
        if(buttons.get(0).getText().equals(currentPlayer) && buttons.get(1).getText().equals(currentPlayer) && buttons.get(2).getText().equals(currentPlayer)) {
            return true;
        }

        // second row
        if(buttons.get(3).getText().equals(currentPlayer) && buttons.get(4).getText().equals(currentPlayer) && buttons.get(5).getText().equals(currentPlayer)) {
            return true;
        }
        
        // third row
        if(buttons.get(6).getText().equals(currentPlayer) && buttons.get(7).getText().equals(currentPlayer) && buttons.get(8).getText().equals(currentPlayer)) {
            return true;
        }

        // diagonal 1
        if(buttons.get(0).getText().equals(currentPlayer) && buttons.get(4).getText().equals(currentPlayer) && buttons.get(8).getText().equals(currentPlayer)) {
            return true;
        }
        
        // diagonal 2
        if(buttons.get(2).getText().equals(currentPlayer) && buttons.get(4).getText().equals(currentPlayer) && buttons.get(6).getText().equals(currentPlayer)) {
            return true;
        }
        
        // column 1 
        if(buttons.get(0).getText().equals(currentPlayer) && buttons.get(3).getText().equals(currentPlayer) && buttons.get(6).getText().equals(currentPlayer)) {
            return true;
        }
        
        // column 2
        if(buttons.get(1).getText().equals(currentPlayer) && buttons.get(4).getText().equals(currentPlayer) && buttons.get(7).getText().equals(currentPlayer)) {
            return true;
        }
        
        // column 3
        if(buttons.get(2).getText().equals(currentPlayer) && buttons.get(5).getText().equals(currentPlayer) && buttons.get(8).getText().equals(currentPlayer)) {
            return true;
        }  
            
        
        return false;
    }

    
}
