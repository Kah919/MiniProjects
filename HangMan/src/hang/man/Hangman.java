package hang.man;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException; // input output 
import java.util.ArrayList;
import java.util.Random;

public class Hangman {
	String mysteryWord;
	StringBuilder currentGuess; // lets you change characters inside of the string (strings are immutable)
	ArrayList<Character> previousGuesses = new ArrayList<>();

	int maxTries = 6;
	int currentTry = 0;

	ArrayList<String> dictionary = new ArrayList<>();
	private static FileReader fileReader;
	private static BufferedReader bufferedFileReader; // same idea as string builder

	public Hangman() throws IOException { // throwing IOException because file might not be there
		initializeStreams();
		mysteryWord = pickWord();
		currentGuess = intializeCurrentGuess(); // makes a guess for us
	}

	public void initializeStreams() throws IOException { // because our constructor has it too
		try {
			File inFile = new File("dictionary.txt"); // I put the dictionary.txt into the hangman folder and imported
			fileReader = new FileReader(inFile); // lets us read the file
			bufferedFileReader = new BufferedReader(fileReader); // lets us parse through
			String currentLine = bufferedFileReader.readLine(); // gets the first line of our document
			while (currentLine != null) { // meaning we aren't at the end of the document
				dictionary.add(currentLine);
				currentLine = bufferedFileReader.readLine(); // lets while loop get out of it
			}
			bufferedFileReader.close(); // always good practice to close these bufferedFileReader
			fileReader.close();
		} catch (IOException e) {
			System.out.println("Could not init streams");
		}
	}

	public String pickWord() {
		Random rand = new Random();
		int wordIndex = Math.abs(rand.nextInt()) % dictionary.size();
		return dictionary.get(wordIndex);
	}

	public StringBuilder intializeCurrentGuess() {
		StringBuilder current = new StringBuilder();
		for (int i = 0; i <= (mysteryWord.length() * 2) - 2; i++) { // we did * 2 because we wanted it to be twice as long for
																// the spaces
			if (i % 2 == 0) {
				current.append("_");
			} else {
				current.append(" ");
			}
		}
		return current;
	}

	public String getFormalCurrentGuess() {
		return "Current Guess: " + currentGuess.toString();
	}

	public boolean gameOver() {
		if (didWeWin()) {
			System.out.println();
			System.out.println("You got lucky. Let's see you do it again.");
			return true;
		} else if (didWeLose()) {
			System.out.println();
			System.out.println("I thought so. The word was " + mysteryWord + ".");
			return true;
		}
		return false;
	}
	public boolean didWeLose() {
		return currentTry >= maxTries;
	}
	public boolean didWeWin() {
		String guess = getCondensedCurrentGuess();
		return guess.equals(mysteryWord);
	}
	public String getCondensedCurrentGuess() {
		String guess = currentGuess.toString();
		return guess.replace(" ", "");
	}
	public boolean isGuessedAlready(char guess) { // just to check if our guess is in our previous guesses
		return previousGuesses.contains(guess); // everytime we guess something it'll be added to our previousGuesses
	}

	public boolean playGuess(char guess) {
		boolean isItAGoodGuess = false;
		previousGuesses.add(guess); // moved it up here
		for (int i = 0; i < mysteryWord.length(); i++) { // for each letter insider the mystery word we will see if it
															// is equal to our guess
			if (mysteryWord.charAt(i) == guess) { // if our guess matches
				currentGuess.setCharAt(i * 2, guess); // i * 2 because of the spaces and we set our guess to the
														// location
				isItAGoodGuess = true;
				// previousGuesses.add(guess); moved up
			}
		}
		if (!isItAGoodGuess) { // isItAGoodGuess was set to default false
			currentTry++; // if we guess it right nothing happens but if we guess wrong our currentTry
							// goes up
		}
		return isItAGoodGuess;
	}

	// " - - - - -\n"+
	// "| |\n"+
	// "| O\n" +
	// "| / | \\ \n"+
	// "| |\n" +
	// "| / \\ \n" +
	// "|\n" +
	// "|\n";
	public String drawPicture() {
		switch (currentTry) {
		case 0:
			return noPersonDraw();
		case 1:
			return addHeadDraw();
		case 2:
			return addBodyDraw();
		case 3:
			return addOneArmDraw();
		case 4:
			return addSecondArmDraw();
		case 5:
			return addFirstLeg();
		default:
			return fullPersonDraw();
		}

	}

	private String noPersonDraw() {

		return " - - - - -\n" + "|        |\n" + "|        \n" + "|       \n" + "|        \n" + "|       \n" + "|\n"
				+ "|\n";
	}

	private String addHeadDraw() {

		return " - - - - -\n" + "|        |\n" + "|        O\n" + "|      \n" + "|        \n" + "|       \n" + "|\n"
				+ "|\n";
	}

	private String addBodyDraw() {

		return " - - - - -\n" + "|        |\n" + "|        O\n" + "|        | \n" + "|        \n" + "|       \n" + "|\n"
				+ "|\n";
	}

	private String addOneArmDraw() {

		return " - - - - -\n" + "|        |\n" + "|        O\n" + "|        | \\ \n" + "|        \n" + "|       \n"
				+ "|\n" + "|\n";
	}

	private String addSecondArmDraw() {

		return " - - - - -\n" + "|        |\n" + "|        O\n" + "|      / | \\ \n" + "|        \n" + "|       \n"
				+ "|\n" + "|\n";
	}

	private String addFirstLeg() {

		return " - - - - -\n" + "|        |\n" + "|        O\n" + "|      / | \\ \n" + "|        |\n" + "|       /n"
				+ "|\n" + "|\n";
	}

	private String fullPersonDraw() {

		return " - - - - -\n" + "|        |\n" + "|        O\n" + "|      / | \\ \n" + "|        |\n"
				+ "|       / \\ \n" + "|\n" + "|\n";

	}
}
