package tic.tac.toe;

import java.util.Scanner;

public class TicTacToeApplication {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// boolean to allow continuous games
		boolean doYouWantToPlay = true;
		// setting up tokens and AI
		while (doYouWantToPlay) {
			System.out.println("Welcome to Tic Tac Toe! You can't defeat me!\n "
					+ "Now choose which character your want to be and what character I will be");
			System.out.println();
			System.out.println("Enter a single character that will resemble you on the board:");
			char playerToken = sc.next().charAt(0); // will only take the first char of whatever is typed ("Kah" = "K")
			System.out.println("Enter a single character that will resemble your opponent on the board");
			System.out.println();
			char opponentToken = sc.next().charAt(0);
			TicTacToe game = new TicTacToe(playerToken, opponentToken); // going to create a new instance
			AI ai = new AI();
				
			// Set up game
			System.out.println();
			System.out.println("Let the game begin!");
			System.out.println("Enter a number and your token will be put into its place.\nThe numbers go from 1-9 left to right. Good luck!");
			TicTacToe.printIndexBoard(); // changed printIndexBoard method to static
			System.out.println();
			
			//Let's Play!
			while(game.gameOver().equals("notOver")) { // this game will keep going until there is no more room or someone wins
				if(game.currentMarker == game.userMarker) { // users turn
					System.out.println("It's your turn! Enter a spot for your token");
					int spot = sc.nextInt();
					while(!game.playTurn(spot)) { // while cannot play the turn
						System.out.println("Try again " + spot + " this spoke is already taken or out of range"); // invalid spot please print something else 
						spot = sc.nextInt(); // resetting spot to allow to allow while loop to break or keep going depending on user input 
					}
					System.out.println("You picked " + spot + "!");
				}else { // if our current marker isn't the user marker then it is the AI marker 
					// AI turn 
					System.out.println("It's my turn!"); // computer saying its my turn
					// Pick spot 
					int aiSpot = ai.pickSpot(game); // everything in our AI class will be run
					game.playTurn(aiSpot); // will know its aiMarker
					System.out.println("I picked " + aiSpot + "!");
				}
				// new board after turn played
				System.out.println();
				game.printBoard();
			}
			System.out.println(game.gameOver());
			System.out.println();
			// set up a new game depending on the response
			System.out.println("Continue? Enter Y to continue.");
			char response = sc.next().charAt(0);
			doYouWantToPlay = (response == 'Y');
			System.out.println();
			System.out.println();
		}
	}

}
