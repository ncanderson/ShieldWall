package nate.anderson;

public class Display {
    
    // initial spacing between armies is defined in the Config class
    private int armySpacing;
    
    // new display takes no arguments
    public Display() {
    	this.armySpacing = Config.getArmySpacing();
    }
    
    public int getArmySpacing() {
        return armySpacing;
    }

    public void setArmyStartSpacing(int spacingChange) {
        // -= because it made more sense to me when calling in other methods
        this.armySpacing -= spacingChange;
    }
    
    public void displayBattleField(ArmyUnit[] armyUnitHolder, String commandInput) {
        
        // main battle display - print out two armies
        System.out.println();
        System.out.println("YOUR FORCES");
        System.out.println("soldiers: " + armyUnitHolder[0].countTroops());
        System.out.println("organization: " + armyUnitHolder[0].getCohesion());
        System.out.println("morale: " + armyUnitHolder[0].getMorale());
        System.out.println("------------------------------------------------------------");
        System.out.println("  YOUR LAST COMMAND: " + commandInput);
        
        // prints out lines between your HUD and your army, based on how far you've advanced
        for (int spacing = 0; spacing < armyUnitHolder[0].getArmyAdvancePadding(); spacing++) {
            System.out.println();
        }
        
        // display your army
        armyUnitHolder[0].renderArmy();
        
        // prints out lines between the armies based on how far you have advanced
        for (int spacing = 0; spacing < Config.getArmySpacing(); spacing++) {
            System.out.println();
        }
        
        //display foe army
        armyUnitHolder[1].renderArmy();
        
        // prints out lines between foe HUD and foe army, based on how far they've advanced
        for (int spacing = 0; spacing < armyUnitHolder[1].getArmyAdvancePadding(); spacing++) {
            System.out.println();
        }
        
        // foe's HUD
        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.println("ENEMY FORCES");
        System.out.println("soldiers: " + armyUnitHolder[1].countTroops());
        System.out.println("organization: " + armyUnitHolder[1].getCohesion());
        System.out.println("morale: " + armyUnitHolder[1].getMorale());
        
        System.out.println();
    }
}