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
        
        /*
        * create the game objects
        */
         
                
        // main game loop - after every turn checks to see if either side had lost
        while (true) {
        
            mainDisplay.displayBattleField();
            UserInterface.promptPlayer();
        
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
    
            if (countTroops(foeArmy) <= 0 || countTroops(youArmy) <= 0) {
                displayBattleField();
                return;
            }
        }
    }
}