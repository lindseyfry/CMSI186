/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  Provides a class describing the HighRoll game
 *  @author       :  Lindsey Fry
 *  Date          :  2017-02-06
 *  Description   :  Creates a High Roll Game
 *  Notes         :  Has a menu for any concerns
 *
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.util.Scanner;

public class HighRoll{
  public static void main ( String[] args){
    //initialize variables
    int currentScore = 0;
    int highScore = 0;
    int sides = 0;
    int count = 0;
    boolean quitProgram = false;
    DiceSet ds;

    System.out.println("\n ****** Main Menu ****** \n");
    //Create a Scanner
    Scanner scan = new Scanner(System.in);
    //Create a new dice set with user input
    ds = new DiceSet(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    if(args.length ==0){
       throw new IllegalArgumentException("Please enter a number <number of dice> <number of sides on each dice>");
    }

    //Create the games
    while(!quitProgram){
      int newInput = 0;

      //create the Main Menu
      System.out.println("Please press one of the following numbers on your keyboard to initiate an action");
      System.out.println("(1) Roll all of the dice");
      System.out.println("(2) Roll one die");
      System.out.println("(3) Calculate the score for the set");
      System.out.println("(4) Save this score as the High Score");
      System.out.println("(5) Display the High Score");
      System.out.println("(6) Quit");

      //display the dice set and ask for input
      System.out.println("the current dice set is " + ds.toString() + "\n Enter the number of the action you want to perform \n");

      try {
        newInput = scan.nextInt();
      }
      catch( Exception e){
        scan.next();
      }

      System.out.println("\n");

      if(newInput == 1){
        ds.roll();
      }else if(newInput == 2){
        System.out.println("Enter the index of the die you want to roll beginning at 0");
        int dieIndex = scan.nextInt();
        int rolledValue = ds.rollIndividual(dieIndex);
        System.out.println("\n the dice at index " + dieIndex + " rolled a " + rolledValue + "\n");
      }else if(newInput == 3){
        currentScore = ds.sum();
        System.out.println("\n Your current score is: " + currentScore + "\n");
      }else if(newInput == 4){
        highScore = currentScore;
      }else if(newInput == 5){
        System.out.println("\nYour high score is " + highScore + "\n");
      }else if(newInput == 6){
        quitProgram = true;
      }//else{
        //System.out.println("Please enter a number between 1 and 6");
      //}

    }
    System.out.println("Your final high score is " + highScore + "!");
  }
}
