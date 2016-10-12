package nate.anderson;

public class Game {
   
	/*
	 *	instance variables for game objects needed
	 */
	
//  toggle for active player, to determine who is targeting who
    private int activePlayer = 0;
    
//  the two armies
    private ArmyUnit yourArmy;
    private ArmyUnit foeArmy;
    
//    container to hold the two armies so we can toggle between them
    public ArmyUnit[] armyUnitHolder = new ArmyUnit[2];
    
//    main display for rendering game objects    
    private Display mainDisplay;
    
    /*
     *	game constructor
     */
    
    public Game(int activePlayer, ArmyUnit yourArmy, ArmyUnit foeArmy, ArmyUnit[] armyUnitHolder, Display mainDisplay) {
    	this.activePlayer = activePlayer;
    	this.yourArmy = yourArmy;
    	this.foeArmy = foeArmy;
    	this.armyUnitHolder = armyUnitHolder; 
    	this.mainDisplay = mainDisplay;
    }
    
    /*
    *	getters and setters
    */
        
    public int getActivePlayer() {
        return this.activePlayer;
    }
    
    public ArmyUnit opponent(int activePlayer) {
        if (this.activePlayer == 0) {
            return armyUnitHolder[1];
        }
        else {
            return armyUnitHolder[0];
        }
    } 
    
    public void setActivePlayer() {
    	if (activePlayer == 0) {
    		activePlayer = 1;
    	}
    	else {
    		activePlayer = 0;
    	}
    }
   
    public ArmyUnit getYourArmy() {
		return yourArmy;
	}

	public void setYourArmy(ArmyUnit yourArmy) {
		this.yourArmy = yourArmy;
	}

	public ArmyUnit getFoeArmy() {
		return foeArmy;
	}

	public void setFoeArmy(ArmyUnit foeArmy) {
		this.foeArmy = foeArmy;
	}

	public ArmyUnit[] getArmyUnitHolder() {
		return armyUnitHolder;
	}

	public void setArmyUnitHolder(ArmyUnit[] armyUnitHolder) {
		this.armyUnitHolder = armyUnitHolder;
	}

	public Display getMainDisplay() {
		return mainDisplay;
	}

	public void setMainDisplay(Display mainDisplay) {
		this.mainDisplay = mainDisplay;
	}

	/*
	 *	Start the game
	 */
	
    public void gameStart() {
                
        // main game loop - after every turn checks to see if either side had lost
        while (true) {
        	String lastCommand = "advance";
        	
            mainDisplay.displayBattleField();
            
            System.out.println("Enter your commmand below");
            System.out.println(">>> ");
            String userInput = System.console().readLine();
            
            if (userInput.equals("")) {
                userInput = lastCommand;
            }
            else {
                lastCommand = userInput;
            }
        
            switch (userInput) {
                case "advance": armyUnitHolder[activePlayer].advanceOrder();
                break;
                case "charge": armyUnitHolder[activePlayer].chargeOrder();
                break;
                case "throw spears": armyUnitHolder[activePlayer].throwOrder();
                break;
                case "retreat": armyUnitHolder[activePlayer].retreatOrder();
                break;
                case "fight": armyUnitHolder[activePlayer].fightOrder();
                break;
                case "rest": armyUnitHolder[activePlayer].restOrder();
                break;
                default: System.out.println("Not a valid command, try again");
                break;
            }
            
            if (mainDisplay.getArmySpacing() <= 0) {
                System.out.println("Enter one of the following commands");
                System.out.println("- fight           - retreat");
            }
            else if (mainDisplay.getArmySpacing() <= 5 && mainDisplay.getArmySpacing() > 0) {
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
    
            if (armyUnitHolder[0].countTroops() <= 0 || armyUnitHolder[1].countTroops() <= 0) {
            	mainDisplay.displayBattleField();
                return;
            }
        }
    }
}