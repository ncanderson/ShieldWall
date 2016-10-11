package nate.anderson;

public class ShieldWall {
            
    public static void main (String[] args) {
        
//    	create the display 
        Display mainDisplay = new Display();
         
//        create the two units
        ArmyUnit yourArmy = new ArmyUnit("Tears of Tyr", "*", 0);
        ArmyUnit foeArmy = new ArmyUnit("Odors of Odin", "@", 1);
        
//        armyUnitHolder to hold the two units
        ArmyUnit[] armyUnitHolder = { yourArmy, foeArmy };

//        make the game
        Game game = new Game(0, yourArmy, foeArmy, armyUnitHolder, mainDisplay);
        
//        gameStart method kicks off the loop that only ends when one army is defeated
        game.gameStart();
        
    }
}
 