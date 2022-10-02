import java.text.NumberFormat;
import java.util.Scanner;

/**
 * A program for solving a Tower of Hanoi. User inputs a valid number of rings,
 * and the console displays the total number of minimum moves that solve the
 * tower.
 * 
 * @author aarongoin
 */
public class Hanoi {
	private static int count = 1; // number of moves it takes to solve the tower
	private static boolean quit = false;
	private static String numberMaybe = null;
	private static String countFormat = null;

	/**
	 * Creates an instance of the method that solves the tower, displays the minimum
	 * number of moves to solve it, and gives confirmation of completion of the
	 * program.
	 * 
	 * @param args the user input when asked to enter number of rings
	 */
	public static void main(String[] args) {

		tower();

		if (quit) {
			return;
		}

		count--; // to prevent adding to count after last move
		countFormat = NumberFormat.getIntegerInstance().format(count);
		System.out.print("\nMinimum number of moves for " + numberMaybe + " rings: " + countFormat + '.');
		System.out.print("\nTower Solved. The End.");
	}

	/**
	 * Confirms that user input represents a valid number of rings. Informs user if
	 * input isn't valid, and then asks for a different input. Will not stop until a
	 * valid input is entered or 'quit' is entered. If input is valid, each move in
	 * the minimum number of moves needed to solve the tower will be displayed in
	 * console through the move() method.
	 */
	private static void tower() {
		Scanner scan = new Scanner(System.in);
		try {
			System.out.print("Enter the number of rings in your tower: ");

			numberMaybe = scan.nextLine();

			System.out.println("\n");

			/*
			 * Will execute if rings are integer values between -∞ and 0.
			 */
			for (char c : numberMaybe.toCharArray()) {
				if (!Character.isDigit(c) && !Character.isLetter(c)) {
					badRingNum(scan);
				}
			}
			move(Integer.parseInt(numberMaybe), "First Peg", "Last Peg", "Middle Peg");

			scan.close();

		} catch (NumberFormatException e) {
			e.toString();
		}
	}

	private static void badRingNum(Scanner scan) {
		System.out.println(numberMaybe + " is not a valid number of rings.");
		System.out.print("Try again? Enter 'y' for yes or 'n' for no: ");

		String tryAgain = scan.nextLine();

		if (tryAgain.equalsIgnoreCase("n") || tryAgain.equalsIgnoreCase("no")) {
			scan.close();
			System.out.print("\n\nTower wasn't solved. Sorry you didn't have fun.");
			quit = true;
			return; // exits method if user enters quit

		} else if (tryAgain.equalsIgnoreCase("y") || tryAgain.equalsIgnoreCase("yes")) {
			System.out.println("\n");
			tower();

		} else {
			System.out.println("\n");
			System.out.println("You didn't follow instructions, so I'm choosing for you.");
			System.out.println("You're trying again.");
			tower();
		}
	}

	/**
	 * Lists all required movements that complete the Tower of Hanoi. All rings will
	 * start on the start peg, and will end up on the end peg. The start peg will be
	 * the first peg on the left, and the end peg will be the last peg on the right.
	 * 
	 * @param rings the number of rings in your tower
	 * @param start the starting peg where are rings are located before any
	 *              movements
	 * @param end   the peg where all rings will be located after a valid solution
	 * @param temp  the peg in the middle of the start and end peg
	 */
	public static void move(int rings, String start, String end, String temp) {
		if (rings == 0) {
			System.out.println("There are no rings, so no moves can be made.");
			return;
		}
		
		String countFormat = null; // to print numbers in columns

		if (rings == 1) {
			countFormat = NumberFormat.getIntegerInstance().format(count);
			count++;
			System.out.println("Move Number " + countFormat + ": Move ring from " + start + " to " + end);

		} else {
			move(rings - 1, start, temp, end);
			countFormat = NumberFormat.getIntegerInstance().format(count);
			count++;
			System.out.println("Move Number " + countFormat + ": Move ring from " + start + " to " + end);
			move(rings - 1, temp, end, start);
		}
	}
}
