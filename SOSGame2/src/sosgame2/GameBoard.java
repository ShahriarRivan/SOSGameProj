package sosgame2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class GameBoard extends JPanel 
{
	private final int BOARD_SIZE;
	public GameButton[][] buttons;
	private char[][] boardValues;
	private static Color currentColor = Color.RED;
	private static String gameMode = "general"; // default mode
	
	private int redScore = 0;
	private int blueScore = 0;
	private static boolean isSTurn = true;  // Start with "S" turn
	private static String currentLetter = "S"; // Default letter

	public GameBoard(int boardSize) {
	    resetGame(); // Reset the game variables
	    this.BOARD_SIZE = boardSize;
	    boardValues = new char[BOARD_SIZE][BOARD_SIZE];
	    setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
	    buttons = new GameButton[BOARD_SIZE][BOARD_SIZE];
	    initializeButtons();
	}

	
	public void resetGame() {
	    currentColor = Color.RED;
	    currentLetter = "S";
	    isSTurn = true;
	    redScore = 0;
	    blueScore = 0;
	}
	
	public void setRedScore(int score)
	{
		redScore=score;
	}
	
	public void setBlueScore(int score)
	{
		blueScore=score;
	}
	
	public void resetScores()
	{
		blueScore=0;
		redScore=0;
	}
	

	
	public static void setCurrentColor(Color color) 
	{
	    currentColor = color;
	}

	public static Color getCurrentColor() 
	{
	    return currentColor;
	}
	
	public static void setCurrentLetter(String letter) 
	{
	    currentLetter = letter;
	}

	public static String getCurrentLetter() 
	{
	    return currentLetter;
	}
	
	public static void setGameMode(String mode) 
	{
	    gameMode = mode;
	}

	
	public void displayEndOfGamePopup(JFrame frame) {
	    String winner;
	    if ("simple".equals(gameMode)) {
	        winner = redScore > 0 ? "Red" : "Blue";
	    } else {
	        winner = redScore > blueScore ? "Red" : "Blue";
	        if (redScore == blueScore) {
	            winner = "It's a tie!";
	        }
	    }
	    String message = "Scores:\nRed: " + redScore + "\nBlue: " + blueScore + "\nWinner: " + winner;

	    Object[] options = {"Start New Game"};

	    int choice = JOptionPane.showOptionDialog(frame, message, "Game Over",
	            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

	    if (choice == 0) { // Corresponds to "Start New Game"
	        int boardSize = SOSGame2.promptForBoardSize();
	        frame.getContentPane().removeAll();
	        frame.add(new GameBoard(boardSize), BorderLayout.CENTER);
	        frame.add(SOSGame2.initializeControlPanel(), BorderLayout.NORTH);
	        SOSGame2.initializeMenu(frame);
	        frame.pack();
	        frame.setLocationRelativeTo(null);
	    }
	}


	
	public boolean isBoardFull() 
	{
	    for (int i = 0; i < BOARD_SIZE; i++) 
	    {
	        for (int j = 0; j < BOARD_SIZE; j++) 
	        {
	            if ("".equals(buttons[i][j].getText())) 
	            {
	                return false;
	            }
	        }
	    }
	    return true;
	}

	

	public int getRedScore() 
	{
	    return redScore;
	}

	public int getBlueScore() 
	{
	    return blueScore;
	}
	



	private void initializeButtons() 
	{
	    for (int i = 0; i < BOARD_SIZE; i++) 
	    {
	        for (int j = 0; j < BOARD_SIZE; j++) 
	        {
	            GameButton btn = new GameButton(i, j);
	            btn.addActionListener(new ActionListener() 
	            {
	                
	            	@Override
	            	public void actionPerformed(ActionEvent e) 
	            	{
	            	    GameButton source = (GameButton) e.getSource();
	            	    if ("".equals(source.getText())) 
	            	    {
	            	        source.setText(GameBoard.getCurrentLetter());
	            	        source.setForeground(GameBoard.getCurrentColor());
	            	        
	            	       
	            	        checkForSOS(source.getRow(), source.getCol());
	            	        togglePlayer();
	            	        SOSGame2.updatePlayerSelection();


	            	        if (isBoardFull()) 
	            	        {
	            	            displayEndOfGamePopup((JFrame) SwingUtilities.getWindowAncestor(source));
	            	        }
	            	    }
	            	}


	            });
	            buttons[i][j] = btn;
	            add(btn);
	        }
	    }
	}
	
	public void checkForSOS(int row, int col) 
	{
	    // Potential sequences
	    String[] potentialMatches = {
	        getStringValue(row, col - 1) + getStringValue(row, col) + getStringValue(row, col + 1), // Horizontal
	        getStringValue(row - 1, col) + getStringValue(row, col) + getStringValue(row + 1, col), // Vertical
	        getStringValue(row - 1, col - 1) + getStringValue(row, col) + getStringValue(row + 1, col + 1), // Diagonal top-left to bottom-right
	        getStringValue(row - 1, col + 1) + getStringValue(row, col) + getStringValue(row + 1, col - 1), // Diagonal top-right to bottom-left
	        
	        getStringValue(row, col - 2) + getStringValue(row, col-1) + getStringValue(row, col), // Horizontal-left
	        getStringValue(row , col) + getStringValue(row, col+1) + getStringValue(row, col+2), // Horizontal-right
	        
	        getStringValue(row-2, col) + getStringValue(row-1, col) + getStringValue(row, col), // Vertical-top
	        getStringValue(row, col) + getStringValue(row+1, col) + getStringValue(row + 2, col), // Vertical-bottom
	        
	        getStringValue(row-2, col - 2) + getStringValue(row-1, col-1) + getStringValue(row, col), // D-T-L
	        getStringValue(row , col) + getStringValue(row+1, col+1) + getStringValue(row + 2, col+2), // D-B-R
	        
	        getStringValue(row+2, col-2) + getStringValue(row+1, col-1) + getStringValue(row, col), // D-B-L
	        getStringValue(row, col) + getStringValue(row-1, col+1) + getStringValue(row-2, col+2) // D-T-R
	    };
	    for (String sequence : potentialMatches) 
	    {
	        if ("SOS".equals(sequence)) 
	        {
	            if (getCurrentColor() == Color.RED) 
	            {
	                redScore++;
	            } 
	            else 
	            {
	                blueScore++;
	            }

	            if ("simple".equals(gameMode) && (redScore > 0 || blueScore > 0)) 
	            {
	                displayEndOfGamePopup((JFrame) SwingUtilities.getWindowAncestor(buttons[row][col]));
	                return; // Exit the method early since the game is over
	            }
	        }
	    }

	    
	    System.out.println("H   : "+potentialMatches[0]);
	    System.out.println("V   : "+potentialMatches[1]);
	    System.out.println("DL  : "+potentialMatches[2]);
	    System.out.println("DR  : "+potentialMatches[3]);

	    for (String sequence : potentialMatches) 
	    {
	        if ("SOS".equals(sequence)) 
	        {
	            if (getCurrentColor() == Color.RED) 
	            {
	                redScore++;
	            } else 
	            {
	                blueScore++;
	            }
	           
	            System.out.println("Red Score: " + redScore + ", Blue Score: " + blueScore);
	           
	        }
	    }
	}


	private String getStringValue(int i, int j) 
	{
	    if (i >= 0 && i < BOARD_SIZE && j >= 0 && j < BOARD_SIZE) 
	    {
	        return buttons[i][j].getText();
	    }
	    return "";
	}
	
	public void togglePlayer() 
	{
	    if (currentColor == Color.RED) 
	    {
	        currentColor = Color.BLUE;
	    } 
	    else 
	    {
	        currentColor = Color.RED;
	    }
	}


}
