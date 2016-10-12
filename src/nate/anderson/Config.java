package nate.anderson;

public class Config{
    
    // sets size of the two units
    private static int unitRows = 5;
    private static int unitColumns = 5;
    private static int armySpacing = 5;
    private static int morale = 100;
    private static int cohesion = 100;
    private static int armour = 10;
    private static int skill = 10;
    
    public static int getMorale() {
		return morale;
	}

	public void setMorale(int morale) {
		Config.morale = morale;
	}

	public static int getCohesion() {
		return cohesion;
	}

	public static void setCohesion(int cohesion) {
		Config.cohesion = cohesion;
	}

	public static int getArmour() {
		return armour;
	}

	public static void setArmour(int armour) {
		Config.armour = armour;
	}

	public static int getSkill() {
		return skill;
	}

	public static void setSkill(int skill) {
		Config.skill = skill;
	}
    
    // class cannot be intantiated
    private Config() {
        
    }
    
    public static int getUnitRows() {
        return unitRows;
    }
    
    public static void setUnitRows(int numUnitRows) {
        unitRows = numUnitRows;
    }
    
    public static int getUnitColumns() {
        return unitColumns;
    }
    
    public static void setUnitColumns(int numUnitColumns) {
        unitColumns = numUnitColumns;
    }
    
    public static int getArmySpacing() {
        return armySpacing;
    }
    
    public static void setArmySpacing(int numArmySpacing) {
        armySpacing -= numArmySpacing;
    }
    
}