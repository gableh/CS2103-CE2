import java.util.Scanner;
/**
 * 
 * @author Gable Heng
 *This program is used to store the information entered by the user into a text file.The text file can either be specified
 *by the user or a default one,textbuddy.txt will be used if no filename is specified.This program will
 *ignore any commands that are invalid.The commands given are case insensitive.
 *
 *Valid Commands are display,add,delete,clear,sort,search and exit.
 *
 */
public class TextBuddy {
	private static final String MESSAGE_COMMAND = "Command: ";
	private static final String MESSAGE_ERROR = "An unknown error has occured";
	public static Controller controller;
	public static Scanner sc;
	public static String command;

	/**
	 * Creates the controller when called and uses it to take input
	 * Program closes when controller stops running.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			createController(args);
			createScanner();
			while (controller.getIsRunning()) {
				displayInputMessage();
				getInput();
				controller.executeCommand(command);
			}
		} catch (Exception e) {
			displayError();
		}
	}

	private static void displayInputMessage() {
		System.out.print(MESSAGE_COMMAND);
	}

	private static void displayError() {
		System.out.println(MESSAGE_ERROR);
	}

	private static void getInput() {
		command = sc.nextLine();
	}

	private static void createScanner() {
		sc = new Scanner(System.in);
	}

	private static void createController(String[] args) {
		controller = new Controller(args);
	}

}
