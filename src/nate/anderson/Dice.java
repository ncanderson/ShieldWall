package nate.anderson;

public class Dice {
    // can't instantiate dice
    private Dice() {
        ;
    }
    
    // obtain a random number 1 - 20
    public static int roll() {
        return (1 + (int)(Math.random() * 20));
    }
    
}