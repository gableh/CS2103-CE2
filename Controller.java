import java.io.*;
import java.util.Scanner;
public class Controller {
	private static final String MESSAGE_NOFILE = "Since no input file has been specified,textbuddy.txt has been created for you";
	private static boolean isRunning;
	private static String fileName;
	private static final String MESSAGE_WELCOME ="Welcome to TextBuddy. %1$s is ready for use.";
	public Controller(String[] args){
		isRunning = true;
		setFileName(args);
		displayWelcome();
	}
	public void executeCommand(String command){
		Storage storage = new Storage(fileName);
		
	}
	public boolean getIsRunning(){
		return isRunning;
	}
	private void displayWelcome() {
		System.out.println(String.format(MESSAGE_WELCOME, fileName));
	}
	
	private void setFileName(String[] args) {
		if (args[0] == null) {
			displayMessage(MESSAGE_NOFILE);
			fileName = "textbuddy.txt";
		}else{
			fileName = args[0];
		}
	}

	private void displayMessage(String message) {
		System.out.println(message);
	}
	public String getFileName(){
		return fileName;
	}
}
