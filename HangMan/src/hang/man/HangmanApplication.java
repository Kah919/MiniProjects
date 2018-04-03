package hang.man;

import java.io.IOException;
import java.util.Scanner;

public class HangmanApplication {
	public static void main(String[] args) throws IOException {
		// How do we play the game
		Scanner sc = new Scanner(System.in);
		System.out.println("I want to play a game... \nI will pick a word and you will try to guess it. "
				+ "\nIf you guess wrong 6x, then I win. \nIf you can guess it before then, I will let you go. "
				+ "\nAre you ready? I hope so because I am.");
		System.out.println();
		System.out.println("I have picked my word. Below is a picture, and below "
				+ "that is your current guess, which starts off as nothing. \nEverytime you guess "
				+ "incorrectly, I will add a body part to the picture. \nWhen it is fully drawn you lose.");

		boolean doYouWantToPlay = true; // allows us to play multiple games
		while (doYouWantToPlay) {
			// Setting up the game
			System.out.println();
			System.out.println("Alright! Let's play!");
			Hangman game = new Hangman(); // sets up our streams
			System.out.println();

			do {
				// Draw the things
				System.out.println();
				System.out.println(game.drawPicture());
				System.out.println();
				System.out.println(game.getFormalCurrentGuess());
				//System.out.println(game.mysteryWord);
				
				// Get the guess
				System.out.println("Enter a character you think is in the word");
				char guess = (sc.next().toLowerCase()).charAt(0);
				System.out.println();
				
				
				// Check if the character is guessed already
//				while(game.isGuessedAlready(guess)) {
//					System.out.println("Try again! You've already guessed that character");
//					guess = (sc.nextLine().toLowerCase()).charAt(0);
//				}
//				
//				// Play the guess
//				if(game.playGuess(guess)) {
//					System.out.println("Great guess! That character is in the word!");
//				} else {
//					System.out.println("Nope.");
//				}	
//			} 
				try {
					while(game.isGuessedAlready(guess)) {
						System.out.println("Try again! You've already guessed that character");
						guess = (sc.nextLine().toLowerCase()).charAt(0);
					}
					
					// Play the guess
					if(game.playGuess(guess)) {
						System.out.println("Great guess! That character is in the word!");
					} else {
						System.out.println("Nope.");
					}	
				} 
				catch(IndexOutOfBoundsException e) {
					System.out.println("Index out of  bounds, don't use the same letters!");
				}
			}
			
			while (!game.gameOver());// while the game isn't over
			// Play again or no?
			System.out.println();
			System.out.println("Do you want to play another game? Enter 'Y' if you do.");
			Character response = (sc.next().toUpperCase()).charAt(0);
			doYouWantToPlay = (response == 'Y');
		}
		sc.close(); // to close scanner
	}

}
