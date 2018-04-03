package maze;

import java.util.Scanner;

public class MazeRunner {
	private static void intro(Maze myMap) {
		System.out.println("Welcome to my maze. You have 100 turns to get out.\n You are currently here");
		myMap.printMap();
	}

	private static String userMove(Scanner sc) {
		System.out.println("Where would you like to move? (R, L, U, D): ");
		String userInput = sc.next().toUpperCase();
		if (userInput.equals("R") || userInput.equals("L") || userInput.equals("U") || userInput.equals("D")) {
			return userInput; // if what the user types is equal to condition then return what is entered
		} else {
			System.out.println("You need to pick (R, L, U, D):");
			return userMove(sc); // using recursion here so the user will input the correct commands
		}

	}

	private static int counter = 0; // this is marking our turns

	private static void movePosition(Scanner sc, Maze myMap) {
		canIMove(userMove(sc), myMap, sc);
		counter++;

	}

	private static void canIMove(String userMove, Maze myMap, Scanner sc) {
		boolean canIMove;
		if (myMap.isThereAPit(userMove)) { // takes the user move and returns whether there is a pit ahead of us
			System.out.println("There is a pit. Would you like to take a leap of faith? (Y) (N)");
			if (sc.next().toUpperCase().equals("Y")) {
				myMap.jumpOverPit(userMove);
			} else { // if the user chooses not to jump over the pit
				System.out.println("You will have to find another way to pass this");
				movePosition(sc, myMap);
			}
		}
		switch (userMove) { // if there are no pits then the switch statement starts
		case "R":
			canIMove = myMap.canIMoveRight();
			if (canIMove) {
				myMap.moveRight();
				myMap.printMap();
			} else {
				System.out.println("Thats a wall.");
				myMap.printMap();
				movePosition(sc, myMap);
			}
			break;

		case "L":
			canIMove = myMap.canIMoveLeft();
			if (canIMove) {
				myMap.moveLeft();
				myMap.printMap();
			} else {
				System.out.println("Thats a wall.");
				myMap.printMap();
				movePosition(sc, myMap);
			}
			break;

		case "U":
			canIMove = myMap.canIMoveUp();
			if (canIMove) {
				myMap.moveUp();
				myMap.printMap();
			} else {
				System.out.println("Thats a wall.");
				myMap.printMap();
				movePosition(sc, myMap);
			}
			break;

		case "D":
			canIMove = myMap.canIMoveDown();
			if (canIMove) {
				myMap.moveDown();
				myMap.printMap();
			} else {
				System.out.println("Thats a wall.");
				myMap.printMap();
				movePosition(sc, myMap);
			}
			break;

		default:
			System.out.println("Error");
			break;
		}
	}

	private static void moveMessage(int moves) { // lets the player know how many turns they got left
		if (moves == 50) {
			System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
		} else if (moves == 75) {
			System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.\n");
		} else if (moves == 90) {
			System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
		} else if (moves == 100) {
			System.out.println("You died");
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		Maze myMap = new Maze();
		Scanner sc = new Scanner(System.in);
		intro(myMap);
		while (!myMap.didIWin()) { // this will return true if you win else false
			movePosition(sc, myMap);
			moveMessage(counter);
		}
		System.out.println("Well done, you got out in " + counter + " turns.");
	}

}
