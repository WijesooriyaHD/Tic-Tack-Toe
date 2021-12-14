


			/*..............      E/17/407    ...............*/

import java.awt.FlowLayout; 
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;    // import some inbuild classes
import java.util.Scanner;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//creating the class Game
public class Game extends JFrame implements ActionListener {

	private JPanel area; //creating a panel (working area)
	private JButton[][] buttonLocation;  // creating an array of buttons
	private char[][] symbol; // creating an array of characters to store player symbol
	private boolean turn_p1; // variable to check the turn of player1

	
	// TickTactToe method 
	// generating the gaming area
	public Game() {

		super("Tick-Tact-Toe (E/17/407)");  
		// modifying the window
		this.setLayout(new BorderLayout());  
		this.setSize(600,600);
		
		area = new JPanel(new GridLayout(3, 3)); //creating a new panel
		area.setVisible(true);  // set the panel visible
		buttonLocation = new JButton[3][3];  // creating an array of buttons
		symbol = new char[3][3];  // creating an array of characters
		for (int row = 0;  row < 3;  row++) {
			for (int column = 0; column  < 3; column ++) {
				buttonLocation[row][column] = new JButton(" "); // new button
				buttonLocation[row][column].setEnabled(true);// enable button
				buttonLocation[row][column].addActionListener(this);
				buttonLocation[row][column].setActionCommand(row + " " + column);
				buttonLocation[row][column].setVisible(true); // set visible
				area.add(buttonLocation[row][column]); // add button to the panel
				symbol[row][column] = '?';  //assign '?' as the  initial symbol
			}
		}
		
		this.add(area, BorderLayout.CENTER);
		turn_p1 = true; //assign true to player1's turn
	}

	
	
	// method to create the window which prints results
	
	public void resultWindow(int playerWon) {
		
		String msg; // string type variable to store the message 
		
		if(playerWon==0) { // check whether the game is draw or not
			
			msg="Tie."; // assign tie to msg
			
		}else if(playerWon==1) { // check whether the player 1 won or not
			
			// prompt a msg when player 1 won the game
			msg="Player 1 won the game."; 
			
		}else {  
			
			// prompt a msg when player 2 won the game
			msg="Player 2 won the game.";
		}
		
		// creating the result window
		JFrame frame = new JFrame("Results"); // creating the frame  
	    JPanel panel = new JPanel();  // creating the working area
	    panel.setLayout(new FlowLayout());  
	    JLabel label = new JLabel(msg);  // creating a label
	    panel.add(label);  // add label to the panel(working area)
	    frame.add(panel);  // add panel to the frame 
	    frame.setSize(300, 100);   // set the frame size
	    frame.setLocationRelativeTo(null);  // setting the frame location
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // how to close the window
	    frame.setVisible(true); // set the window visible
		
	}
	
	
	// method to disable all buttons when a player wins
	public void disableAll() {
		
		//loop through the button array
		for (int row = 0; row < 3; row++) {// loop through the rows  
			for (int col = 0; col < 3; col++) { // loop through the rows
				buttonLocation[row][col].setEnabled(false); //enable buttons 
			}
		}
		
	}
	
	
	// method to check the winner ( return true if a player wins)
	private boolean winner(char playerSymbol, char[][] symbol){
		
		// checking for a diagonal winner
		
		if (symbol[0][0] == playerSymbol && symbol[1][1] == playerSymbol && symbol[2][2] == playerSymbol) {
			return true; // return true if someone wins
		}

		if (symbol[0][2] == playerSymbol && symbol[1][1] == playerSymbol && symbol[2][0] == playerSymbol) {
			return true; // return true if someone wins
		}
		
		//checking for a row winner
	
		for (int row = 0; row < 3; row++) { 
		
			int count = 0;
			for (int column = 0; column < 3; column++) {
			
				if (symbol[row][column] == playerSymbol)
					count++;
				else
					count = 0;
			}
			// check whether the player symbol appears 3 times or not 
			if (count == 3) {
				return true; // return true if someone wins
			}
		}

		// checking for a column winner
		for (int column = 0; column < 3; column++) {
		
			int count = 0;
			for (int row = 0; row < 3; row++) {
			
				if (symbol[row][column] == playerSymbol) {
					count++;
				}else {
					count = 0;
				}
			}
			// check whether the player symbol appears 3 times or not
			if (count == 3) {
				return true;// return true if someone wins
			}
		}
		
		return false;// return false if someone doesn't win
	}

	
	// method to detect a draw match
	private boolean tie(char[][] symbol) {
	
		// loop through rows
		for (int row = 0; row < 3; row++) {
		// loop through columns
			for (int column = 0; column < 3; column++) {
			// checking for '?' symbol
				if (symbol[row][column] == '?') {
					return false;// if we found a symbol '?' it is not a tie
				}
			}
		}
//if there is no '?' symbol in the symbol array then it is a tie
		// all the symbols are '1' or '2'
		return true;
	}

	//Override the actionPerformed() method
	public void actionPerformed(ActionEvent e) {
	

		String s = e.getActionCommand(); //Returns the command string associated with this action e
		if (s.length() == 3) {

			Scanner input = new Scanner(s); // get input from the user
			String rowString = input.next();
			String colString = input.next();

			int row = Integer.parseInt(rowString);
			int col = Integer.parseInt(colString);

			JButton button = buttonLocation[row][col]; // access to  a button

			if (button.isEnabled()) {  // check whether the button is enable or not
				
				if (turn_p1) { // check whether if the turn is player 1's or not
			
					button.setText("1");  // display 1 on the button
					symbol[row][col] = '1'; // assign 1 to the array element
					button.setEnabled(false); // disable the button after it is selected by the user
					turn_p1 = false; // turn_p1 = false means that it is palyer 2's turn
					
				} else { //check whether if the turn is player 2's or not
				
					button.setText("2");  // display 2 on the button
					symbol[row][col] = '2'; // assign 2 to the array element
					button.setEnabled(false); // disable the button after it is selected by the user
					turn_p1 = true; // turn_p1 = true means that it is palyer 1's turn
				}
			}
			
			
			
			if (tie(symbol)) {  // checking for a draw 
			
				input.close();  // stop getting inputs
				resultWindow(0);  // prompt the result in a separate window
			}
			
			
			if (winner('2', symbol)) {
				
				input.close(); // stop getting inputs
				disableAll(); // disable buttons 
				resultWindow(2); // prompt the result in a separate window
				
			}
			
			if (winner('1', symbol)) {
			
				input.close();// stop getting inputs
				disableAll();// disable buttons 
				resultWindow(1);// prompt the result in a separate window
				
			}

			
		}
		
	}
	

// main method	
	public static void main(String args[]) {
	
		// creating a new game window
		Game game = new Game();
		game.setVisible(true); // enable window
	}
	
}