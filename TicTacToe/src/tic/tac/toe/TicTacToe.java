package tic.tac.toe;

public class TicTacToe { // protected so only accessible within same package and subclasses in other packages
	protected char[] board; // x and o are chars
	protected char userMarker;
	protected char aiMarker;
	protected char winner;
	protected char currentMarker;
	
	public TicTacToe(char playerToken, char aiMarker) {
		this.userMarker = playerToken; // why are we setting userMarker to playerToken?
		this.aiMarker = aiMarker; 
		this.winner = '-';
		this.board = setBoard(); // had to make a setBoard method down here vvv
		this.currentMarker = userMarker;
	}
	public static char[] setBoard() { // this is gonna return a blank board and anyone can use it
		char[] board = new char[9];
		for(int i = 0; i < board.length; i++) {
			board[i] = '-';
		}
		return board;
	}
	public boolean playTurn(int spot) {
		boolean isValid = withinRange(spot) && !isSpotTaken(spot);
		if(isValid) {
			board[spot - 1] = currentMarker;
			currentMarker = (currentMarker == userMarker) ? aiMarker : userMarker;
			// in generic code '?' is a wild card and represents unknown type
		}
		return isValid;
	}
	// check if our spot is in range
	public boolean withinRange(int number) {
		return number > 0 && number < board.length + 1; // the +1 is so that the number has to be 1 to 9 between 0 and 10
	}
	// check if the spot is taken
	public boolean isSpotTaken(int number) {
		return board[number-1] != '-'; // we will get the thing at this number spot
		// we check to see if there is something occupied at the spot to see if it is equal to '-' if it is then its empty
	}
	public void printBoard() {
		// attempting to create
		// | - | - | -
		// ------------
		// | - | - | -
		// ------------
		// | - | - | -
		
		System.out.println();
		for(int i = 0; i < board.length; i++) {
			if(i % 3 == 0 && i != 0) {
				System.out.println();
				System.out.println("------------");
			}
			System.out.print(" | " + board[i]);
		}
		System.out.println();
	}
	public static void printIndexBoard() { // added static
		System.out.println();
		for(int i = 0; i < 9; i++) { // changed from board.length to 9
			if(i % 3 == 0 && i != 0) {
				System.out.println();
				System.out.println("------------");
			}
			System.out.print(" | " + (i + 1)); // because we started at 0 since the board starts at 0 but user sees 1
		}
		System.out.println();
	}
	public boolean isThereAWinner() {
		boolean diagonalsAndMiddle = (rightDi() || leftDi() || middleRow() || secondCol()) && board[4] != '-';
		boolean topAndFirst = (topRow() || firstCol()) && board[0] != '-';
		boolean bottomAndThird = (bottomRow() || thirdCol()) && board[8] != '-';
		if(diagonalsAndMiddle) {
			this.winner = board[4];
		}else if(topAndFirst) {
			this.winner = board[0]; 
		}else if(bottomAndThird) {
			this.winner = board[8];
		}
		return diagonalsAndMiddle || topAndFirst || bottomAndThird;
	}
	public boolean topRow() {
		return board[0] == board[1] && board[1] == board[2];
	}
	public boolean middleRow() {
		return board[3] == board[4] && board[4] == board[5];
	}
	public boolean bottomRow() {
		return board[6] == board[7] && board[7] == board[8];
	}
	public boolean firstCol() {
		return board[0] == board[3] && board[3] == board[6];
	}
	public boolean secondCol() {
		return board[1] == board[4] && board[4] == board[7];
	}
	public boolean thirdCol() {
		return board[2] == board[5] && board[5] == board[8];
	}
	public boolean rightDi() {
		return board[0] == board[4] && board[4] == board[8];
	}
	public boolean leftDi() {
		return board[2] == board[4] && board[4] == board[6];
	}
	public boolean isTheBoardFilled() {
		for(int i = 0; i < board.length; i++) {
			if(board[i] == '-') {
				return false;
			}
		}
		return true;
	}
	public String gameOver() {
		boolean didSomeoneWin = isThereAWinner();
		if(didSomeoneWin) {
			return "We have a winner! The winner is " + this.winner + "'s";
		}else if(isTheBoardFilled()) {
			return "draw: Game Over!";
		}else {
			return "notOver";
		}
	}
}
