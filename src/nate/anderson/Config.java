package nate.anderson;

public class Config{
    
    // sets size of the two units
    private static int unitRows = 7;
    private static int unitColumns = 150;
    private static int armySpacing = 10;
    private int morale = 100;
    private int cohesion = 100;
    private int armour;
    private int skill;
    
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