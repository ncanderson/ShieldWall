package nate.anderson;

public class ShieldWall {
            
    public static void main (String[] args) {
                 
//        create the two units
        ArmyUnit yourArmy = new ArmyUnit("Tears of Tyr", "*", 0);
        ArmyUnit foeArmy = new ArmyUnit("Odors of Odin", "@", 1);
        
        yourArmy.fillUnit();
        foeArmy.fillUnit();
        
//        armyUnitHolder to hold the two units
        ArmyUnit[] armyUnitHolder = { yourArmy, foeArmy };

//    	create the display 
        Display mainDisplay = new Display();
        
//        make the game
        Game game = new Game(0, yourArmy, foeArmy, armyUnitHolder, mainDisplay);
        
//        gameStart method kicks off the loop that only ends when one army is defeated
//        initializes game with advance as the the 'last command'
        game.gameStart();
        
    }
}
 