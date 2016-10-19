package nate.anderson;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {

	private String PROMPT = "=|===> ";
	private String REVERSE_PROMPT = " <===|=";
	private PrintWriter out;
	private Scanner in;
	
	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}
	
	public int getIntegerInput() {
		System.out.println();
    	System.out.println("Enter New Value");
    	System.out.print(PROMPT);
		return in.nextInt();
	}
	
	public String getStringInput() {
		System.out.println();
		System.out.println("Enter your answer");
		System.out.print(PROMPT);
		return in.nextLine();
	}
		
	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while(choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}
	
	public Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption <= options.length) {
				choice = options[selectedOption];
			}
		}
		catch (NumberFormatException e) {
			// error message will be displayed
		}
		if (choice == null) {
			out.println("\n" + PROMPT + userInput + REVERSE_PROMPT);
			out.println("is not a valid input");
		}
		return choice;
	}
	
	private void displayMenuOptions(Object[] options) {
		out.println();
		for(int i = 1; i < options.length; i++) {
			out.println(i + ") " + options[i]);
		}
		out.println();
		out.print(PROMPT);
		out.flush();
	}
	

}
	
	
	
	
	
	
	
