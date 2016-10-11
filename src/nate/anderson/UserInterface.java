package nate.anderson;
public class UserInterface {
   
	
    // displays player options based on which commands are available
    public static void optionsMenu() {
        if (game.getMainDisplay().getArmySpacing <= 0) {
            System.out.println("Enter one of the following commands");
            System.out.println("- fight           - retreat");
        }
        else if (mainDisplay.getArmySpacing <= 5 && mainDisplay.getArmySpacing > 0) {
            System.out.println("Enter one of the following commands");
            System.out.println("- advance         - charge");
            System.out.println("- throw spears    - retreat");
            System.out.println("- rest");
        }
        else {
            System.out.println("Enter one of the following commands");
            System.out.println("- advance         - charge");
            System.out.println("- rest           - retreat");
        }
        
    }

    public static void promptPlayer() {
        // prompt user for input
        System.out.println("Enter your commmand below");
        userInput = System.console().readLine();
    
        if (userInput.equals("")) {
            userInput = lastInput;
        }
        else {
            lastInput = userInput;
        }
    }
}