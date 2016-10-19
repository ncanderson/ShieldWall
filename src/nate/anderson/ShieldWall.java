package nate.anderson;

import java.util.Scanner;

import sun.net.www.content.text.plain;

public class ShieldWall {
    
	private static final String PROMPT = "=|===> "; 
	
	private static final String MAIN_MENU_HEADING = "Main Menu";
	private static final String MAIN_ONE_PLAYER_GAME = "1 Player Game";
	private static final String MAIN_TWO_PLAYER_GAME = "2 Player Game";
	private static final String MAIN_OPTIONS = "Options";
	private static final String MAIN_MANUAL = "Manual";
	private static final String MAIN_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_HEADING,
														MAIN_ONE_PLAYER_GAME,
														MAIN_TWO_PLAYER_GAME,
														MAIN_OPTIONS,
														MAIN_MANUAL,
														MAIN_EXIT };
	
	private static final String MENU_RETURN_TO_MAIN = "Return to Main Menu";
	
	private static final String OPTIONS_MENU_HEADING = "Select a stat to change";
	private static String UNIT_ROWS = "Unit Rows: " + Config.getUnitRows();
	private static String UNIT_COLUMNS = "Unit Columns: " + Config.getUnitColumns();
	private static String ARMY_SPACING = "Army Spacing: " + Config.getArmySpacing();
	private static String MORALE = "Army Morale: " + Config.getMorale();
	private static String COHESION = "Army Cohesion: " + Config.getCohesion();
	private static String ARMOUR = "Armour of Warriors: " + Config.getArmour();
	private static String SKILL = "Battle Skill of Warriors: " + Config.getSkill();
	private static String[] OPTIONS_MENU_OPTIONS = { OPTIONS_MENU_HEADING, UNIT_ROWS, UNIT_COLUMNS, ARMY_SPACING, MORALE, COHESION, ARMOUR, SKILL, MENU_RETURN_TO_MAIN };
	
	private static final String LEADER_MENU_HEADING = "Would you like to create a leader or build one randomly?";
	private static final String LEADER_RANDOM_LEADER = "Random Leader";
	private static final String LEADER_BUILD_LEADER = "Build Your Own Leader";
	private static final String[] LEADER_MENU_OPTIONS = { LEADER_MENU_HEADING, LEADER_RANDOM_LEADER, LEADER_BUILD_LEADER };
	
	private Menu menu;
	public ArmyUnit[] armyUnitHolder = new ArmyUnit[2];
	
    public static void main (String[] args) {
    	ShieldWall game = new ShieldWall();
    	game.run();
    }
    
    public ShieldWall() {
		this.menu = new Menu(System.in, System.out);
    }
    
    private void run() {
    	displayApplicationBanner();
    	Scanner scanner = new Scanner(System.in);
    	
    	while(true) {
    		String choice = null;
    		printHeading(MAIN_MENU_OPTIONS);
    		choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
    		if (choice.equals(MAIN_ONE_PLAYER_GAME)) {
    			startOnePlayerGame();
    		}
    		else if (choice.equals(MAIN_TWO_PLAYER_GAME)) {
    			startTwoPlayerGame();
    		}
    		else if (choice.equals(MAIN_OPTIONS)) {
    			openOptions();
    		}
    		else if (choice.equals(MAIN_EXIT)) {
    			System.exit(0);
    		}
    	}
    }
    
    private void startOnePlayerGame() {
    	printHeading(LEADER_MENU_OPTIONS);
    	String menuChoice = (String)menu.getChoiceFromOptions(LEADER_MENU_OPTIONS);
    	
    	if (menuChoice.equals(LEADER_RANDOM_LEADER)) {
    		onePlayerGameStart();
    	}
    	else if (menuChoice.equals(LEADER_BUILD_LEADER)) {
    		System.out.println("feature not implemented yet");
    	}
    }
    
    private void startTwoPlayerGame() {
    	printHeading(LEADER_MENU_OPTIONS);
    	String menuChoice = (String)menu.getChoiceFromOptions(LEADER_MENU_OPTIONS);
    	
    	if (menuChoice.equals(LEADER_RANDOM_LEADER)) {
    		playerTwoGameInit();
    	}
    	else if (menuChoice.equals(LEADER_BUILD_LEADER)) {
    		System.out.println("feature not implemented yet");
    	}
    }
    
    private void onePlayerGameStart() {
    	System.out.println("not currently implemented");
    }
    
    private void playerTwoGameInit() {
    	System.out.println("Player 1, what is the name of your army?");
    	System.out.println();
    	String playerOneArmyName = menu.getStringInput();
    	System.out.println("Player 2, what is the name of your army?");
    	System.out.println();
    	String playerTwoArmyName = menu.getStringInput();
    	ArmyUnit playerOne= new ArmyUnit(playerOneArmyName, "*", 0);
    	ArmyUnit playerTwo = new ArmyUnit(playerTwoArmyName, "@", 1);
    	playerOne.fillUnit();
    	playerTwo.fillUnit();
    	armyUnitHolder[0] = playerOne;
    	armyUnitHolder[1] = playerTwo;
    }
    
  
/*    	

//    	create the display 
        Display mainDisplay = new Display();
        
//        make the game
        Game game = new Game(0, yourArmy, foeArmy, armyUnitHolder, mainDisplay);
        
//        gameStart method kicks off the loop that only ends when one army is defeated
//        initializes game with advance as the the 'last command'
        game.gameStart();
        
    }
*/
    
	private void printHeading(String[] optionsMenu) {
		System.out.println("\n" + optionsMenu[0]);
		for(int i = 0; i < optionsMenu[0].length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	private String getUserInput(String prompt) {
		System.out.print(prompt + PROMPT);
		return new Scanner(System.in).nextLine();
	}
	
	private void openOptions() {	
    	String menuChoice = null;
    	int newStatValue = 0;
    	int indexToChange;
    	
    	while (true) {
	    	printHeading(OPTIONS_MENU_OPTIONS);
	    	menuChoice = (String)menu.getChoiceFromOptions(OPTIONS_MENU_OPTIONS);
	    	
	    	if (menuChoice.equals(UNIT_ROWS)) {
	    		newStatValue = Integer.parseInt(getUserInput("What is the new value? "));
	    		Config.setUnitRows(newStatValue);
	    		UNIT_ROWS = "Unit Rows: " + Config.getUnitRows();
	    		OPTIONS_MENU_OPTIONS[1] = UNIT_ROWS;
	    	}
	    	else if (menuChoice.equals(UNIT_COLUMNS)) {
	    		newStatValue = Integer.parseInt(getUserInput("What is the new value? "));
	    		Config.setUnitColumns(newStatValue);
	    		UNIT_COLUMNS = "Unit Columns: " + Config.getUnitColumns(); 
	    		OPTIONS_MENU_OPTIONS[2] = UNIT_COLUMNS;
	    	}
	    	else if (menuChoice.equals(ARMY_SPACING)) {
	    		newStatValue = Integer.parseInt(getUserInput("What is the new value? "));
	    		Config.setArmySpacing(newStatValue);
	    		ARMY_SPACING = "Army Spacing: " + Config.getArmySpacing();
	    		OPTIONS_MENU_OPTIONS[3] = ARMY_SPACING;
	    	}
	    	else if (menuChoice.equals(MORALE)) {
	    		newStatValue = Integer.parseInt(getUserInput("What is the new value? "));
	    		Config.setMorale(newStatValue);
	    		MORALE = "Army Morale: " + Config.getMorale();
	    		OPTIONS_MENU_OPTIONS[4] = MORALE;
	    	}
	    	else if (menuChoice.equals(COHESION)) {
	    		newStatValue = Integer.parseInt(getUserInput("What is the new value? "));
	    		Config.setCohesion(newStatValue);
	    		COHESION = "Army Cohesion: " + Config.getCohesion();
	    		OPTIONS_MENU_OPTIONS[5] = COHESION;
	    	}
	    	else if (menuChoice.equals(ARMOUR)) {
	    		newStatValue = Integer.parseInt(getUserInput("What is the new value? "));
	    		Config.setArmour(newStatValue);
	    		ARMOUR = "Armour of Warriors: " + Config.getArmour();
	    		OPTIONS_MENU_OPTIONS[6] = ARMOUR;
	    	}
	    	else if (menuChoice.equals(SKILL)) {
	    		newStatValue = Integer.parseInt(getUserInput("What is the new value? "));
	    		Config.setSkill(newStatValue);
	    		SKILL = "Battle Skill of Warriors: " + Config.getSkill();
	    		OPTIONS_MENU_OPTIONS[7] = SKILL;
	    	}
	    	else if (menuChoice.equals(MENU_RETURN_TO_MAIN)) {
	    		return;
	    	}
    	}	
    }
	
	private void displayApplicationBanner() {
		System.out.println();
    	System.out.println(" _______          _________ _______  _        ______            _______  _        _       ");
    	System.out.println("(  ____ \\ |\\     /|\\__   __/(  ____ \\( \\      (  __  \\ |\\     /|(  ___  )( \\      ( \\      ");
    	System.out.println("| (    \\/ | )   ( |   ) (   | (    \\/| (      | (  \\  )| )   ( || (   ) || (      | (      ");
    	System.out.println("| (_____  | (___) |   | |   | (__    | |      | |   ) || | _ | || (___) || |      | |      ");
    	System.out.println("(_____  ) |  ___  |   | |   |  __)   | |      | |   | || |( )| ||  ___  || |      | |      ");
    	System.out.println("      ) | | (   ) |   | |   | (      | |      | |   ) || || || || (   ) || |      | |      ");      
    	System.out.println("/\\____) | | )   ( |___) (___| (____/\\| (____/\\| (__/  )| () () || )   ( || (____/\\| (____/\\");
    	System.out.println("\\_______) |/     \\|\\_______/(_______/(_______/(______/ (_______)|/     \\|(_______/(_______/");
    	System.out.println();
    	System.out.println("                      Take up your spear and join the shieldwall!");
    	System.out.println();
    	System.out.println();
	}
}
 