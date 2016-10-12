package nate.anderson;

public class ArmyUnit {
    // unit stats to initialize
    private String name;
    private int morale;
    private int cohesion;
    private int armour;
    private int skill;
    private String fillString;
    private boolean charged;
    
    // boolean variable that establishes the relationship the two armies and which is player controlled
    private boolean yourArmy;
    
    // front rank is different for the two armies - important for calculating casualties
    // at instantiation this array is filled later such that idx[0] is the rank closest to the enemy army
    private int[] frontRank = new int[Config.getUnitRows()];
    
    // track how much space is behind each army so it displays properly
    private int armyAdvancePadding = 0; 
    
    // hold last command that unit was issued - mostly for the player although AI will have it (eventually)
    private String commandInput = "advance";
    
    // the arrays that will hold the unit
    private String[][] army = new String[Config.getUnitRows()][Config.getUnitColumns()];
    
    // position in the army unit array
    int arrayPosition;
    
    /*
    *	constructor and getters and setters
    */
    
    // army constructor
    public ArmyUnit(String armyName, String fillString, int arrayPosition) {
        this.name = armyName;
        this.fillString = fillString;
        this.arrayPosition = arrayPosition;
        
        morale = Config.getMorale();
        cohesion = Config.getCohesion();
        armour = Config.getArmour();
        skill = Config.getSkill();
        charged = false;
        
        this.arrayPosition = arrayPosition;
        if (arrayPosition == 0) {
        	yourArmy = true;
        }
        else {
        	yourArmy = false;
        }
        	
        frontRank = new int[Config.getUnitRows()];
        armyAdvancePadding = 0; 
        commandInput = "advance";
        army = new String[Config.getUnitRows()][Config.getUnitColumns()];
           
        if (yourArmy) {
            for (int i = 0; i < Config.getUnitRows(); i++) {
                frontRank[i] = Config.getUnitRows() - (i + 1);
            }
        }
        else if (!yourArmy) {
            for (int i = 0; i < Config.getUnitRows(); i++ ) {
                frontRank[i] = i;    
            }
        }
        else {
            System.out.println("Error in army initialization");
        }
    }
    
    // getters and setters
    public String getCommandInput() {
        return commandInput;
    }
    
    public void setCommandInput(String commandInput) {
        this.commandInput = commandInput;
    }

	public String getName() {
		return name;
	}

	public int getMorale() {
		return morale;
	}

	public void lowerMorale(int morale) {
		this.morale -= morale;
	}
	
	public void raiseMorale(int morale) {
		this.morale += morale;
	}

	public int getCohesion() {
		return cohesion;
	}

	public void lowerCohesion(int cohesion) {
		this.cohesion -= cohesion;
	}
	
	public void raiseCohesion(int cohesion) {
		this.cohesion += cohesion;
	}

	public int getArmour() {
		return armour;
	}

	public void setArmour(int armour) {
		this.armour = armour;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public boolean hasCharged() {
		return charged;
	}

	public void setCharged(boolean charged) {
		this.charged = charged;
	}

	public String[][] getArmy() {
		return army;
	}

	public void setArmy(String[][] army) {
		this.army = army;
	}
    
    public int getArmyAdvancePadding() {
        return armyAdvancePadding;
    }
     
    public void setArmyAdvancePadding(int armyAdvancePadding) {
        this.armyAdvancePadding = armyAdvancePadding;
    } 

    
    /*
    *
    *  rendering/display/display-helper methods, called as parts of the other methods
    *  
    */
    
    // takes array position and returns opponent
    public int opponent(int activePlayer) {
        if (arrayPosition == 0) {
            return 1;
        }
        else {
            return 0;
        }
    } 
    
    // loop through the 2D arrays, apply the fill character in each slot
    public void fillUnit() {  
        for (int i = 0; i < army.length; i++) {
            for (int j = 0; j < army[i].length; j++) {
                army[i][j] = fillString;
            }
        }
    }

    // loop through your unit and print out contents
    // move to display?
    public void renderArmy() {         
        for (int i = 0; i < army.length; i++) {
            for (int j = 0; j < army[i].length; j++) {
                System.out.print(army[i][j]);
            }
            System.out.println();
        }   
    }
    
    // count troop amount to display in HUD
    // this can may be eliminated - replace with countPerRank and sum the array
    public int countTroops() {
        int armyCount = 0;
        for (int i = 0; i < (this.army).length; i++) {
            for (int j = 0; j < (this.army[i]).length; j++) {
                if (this.army[i][j] != " ") {
                    armyCount++;
                }
            }
        }
        return armyCount;
    }
    
    // count troops per rank so you pull that value later for throwing and melee
    public int[] countPerRank() {
        int[] rankArray = new int[Config.getUnitRows()];
        
        for (int i = 0; i < (this.army).length; i++) {
            for (int j = 0; j < (this.army[i]).length; j++) {
                int rankCount = 0;
                if (this.army[i][j] != " ") {
                    rankCount++;
                }
                rankArray[i] = rankCount;
            }
        }
        return rankArray;
    }
    
    /*
    *
    *  move orders to the units
    * 
    */
    
    public void advanceOrder(ArmyUnit[] armyUnitHolder, int activePlayer) {
        if (Config.getArmySpacing() <= 0) {
            System.out.println("You are already in melee range! Pick another option!");
        }
        else {
            Config.setArmySpacing(1);
            armyAdvancePadding++;
        }
        
        // charged state can't persist past the next command given
        charged = false;
    }
    
    public void chargeOrder(ArmyUnit[] armyUnitHolder, int activePlayer) {
        if (Config.getArmySpacing() <= 1) {
            System.out.println("You are too close to charge! Pick another command!");
        }
        else {
            Config.setArmySpacing(2);
            armyAdvancePadding += 2;
            charged = true;
        }
    }
    
    public void retreatOrder(ArmyUnit[] armyUnitHolder, int activePlayer) {
        if (Config.getArmySpacing() == 10) {
            System.out.println("You may not retreat any further! Pick another command!");
        }
        // retreat while in melee reduces cohesion and morale
        else if (Config.getArmySpacing() == 0) {
            Config.setArmySpacing(1);
            armyAdvancePadding--;
            cohesion -= 10;
            morale -= 10;
        }
        else {
            Config.setArmySpacing(1);
            armyAdvancePadding--;
            // smaller cohesion reduction while not in melee
            cohesion -= 5;
        }
        
        // charged state can't persist past the next command given
        charged = false;
    }
    
    // restore morale and cohesion, with a max of 100
    public void restOrder(ArmyUnit[] armyUnitHolder, int activePlayer) {
        morale += 5;
        cohesion += 5;
        if (morale > 100) {
            morale = 100;
        }
        if (cohesion > 100) {
            cohesion = 100;
        }
        
        // charged state can't persist past the next command given
        charged = false;
    }
    
    /*
    *
    *  combat orders to the units
    * 
    */
    
    // follow same outline as throwspears
    public void fightOrder(ArmyUnit[] armyUnitHolder, int activePlayer) {
        int kills = 0;
        int[] rankCount;
            
        if (Config.getArmySpacing() != 0) {
            System.out.println("You are too far away, close the distance first!");
        }
        else {
            // declare attacker
            ArmyUnit attacker = armyUnitHolder[activePlayer];
            ArmyUnit defender = armyUnitHolder[opponent(activePlayer)];
            
            // counts up troops in each rank to use for melee attacks
            rankCount = armyUnitHolder[activePlayer].countPerRank();
            
            // all in front rank, half in second rank, quarter in third rank get an attack
            int meleeAttacks = rankCount[0] + (int)(rankCount[1] / 2) + (int)(rankCount[3] / 4);
            
            // double attacks in first fight after attack
            if (charged) {
                meleeAttacks *= 2;
                charged = false;
            }
            
            if (this.yourArmy) {
                defender = armyUnitHolder[1];
                if ((Dice.roll() + armyUnitHolder[0].skill) >= armyUnitHolder[1].armour) {
                    kills++;
                }
            }
            else {
                defender = armyUnitHolder[0];
                if ((Dice.roll() + armyUnitHolder[1].skill) >= armyUnitHolder[0].armour) {
                    kills++;
                }
            }
            
            // apply casualties
            takeCasualties(defender, kills);
            
            // reducing moral and cohesion        
            lowerMorale(kills);
            lowerCohesion(kills);
        }
    }
    
    
    // currently both sides throwing is in this one method. later separate it out so there can be an AI
    public void throwOrder(ArmyUnit[] armyUnitHolder, int activePlayer) {
        // value to pass to the casualties method later
        int kills = 0;
        double spearThrows = 0;
        
        if (Config.getArmySpacing() <= 0) {
            System.out.println("You are too close to throw spears! Pick another command");
        }
        else if (Config.getArmySpacing() > 5) {
            System.out.println("You are too far away to throw spears! Pick another command");
        }
        else {
            // throws is 25% of troops remaining * cohesion (lower cohesion reduces throws)
            spearThrows = (this.countTroops() * (this.cohesion / 100.0)) / 10;
            
            // defender determines which army is passed into the take casualties method
            ArmyUnit defender;

            // loop through each spearThrow, roll to hit, add to kills
            // D20 based hit mechanics
            if (this.yourArmy) {
                defender = armyUnitHolder[1];
                if ((Dice.roll() + armyUnitHolder[0].skill) >= armyUnitHolder[1].armour) {
                    kills++;
                }
            }
            else {
                defender = armyUnitHolder[0];
                if ((Dice.roll() + armyUnitHolder[1].skill) >= armyUnitHolder[0].armour) {
                    kills++;
                }
            }
            
            // apply casualties to defender
            takeCasualties(defender, kills);
            
            // reduce defender morale and cohesion at a lesser rate than melee
            defender.lowerMorale((int)(kills * 0.1));
            defender.lowerCohesion((int)(kills * 0.1));
        }
    }
    
    // take casualties from the appropriate rank, based on which army it is  
    public static void takeCasualties(ArmyUnit defender, int kills) {
        int killCounter = 0;
        int position = 0;
        // takes the last element of the frontRank instance variable (the back rank of the unit)
        int rankCounter = 0;
        int rank = defender.frontRank[rankCounter];
        
        while (killCounter < kills) {
            if ((defender.countTroops() <= 0)) {
                if (defender.yourArmy) {
                    System.out.println("You have been vanquished!");
                }
                else {
                    System.out.println("You have vanquished your foe!");
                }
                return;
            }
            
            if ((defender.army[rank][position]).equals(" ")) {
                position++;
                if (position == ((defender.army[rank]).length)) {
                    rankCounter++;
                    position = 0;
                }    
            }
            else {
                defender.army[rank][position] = " ";
                killCounter++;
                position++;
                if (position == ((defender.army[rank]).length)) {
                    rankCounter++;
                    position = 0;
                }
            }
        }
    }    
}

    
