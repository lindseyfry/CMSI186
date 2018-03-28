/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  Lindsey Fry
 *  Date written  :  2017-03-15
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim class
 *                   for Homework 5, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-03-15  Lindsey Fry   Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 import java.text.DecimalFormat;
 public class Ball{
   private final double MINIMUM_SPEED = 0.083;
   private double speed;
   private double xPos;
   private double yPos;
   private double xSpeed;
   private double ySpeed;
   private double timeSlice;

   public Ball(double xStart, double yStart, double xSpeedStart, double ySpeedStart, double timeSliceInput){
     this.speed = Math.sqrt((xSpeed * xSpeed) + (ySpeed * ySpeed));
     this.xPos = xStart;
     this.yPos = yStart;
     this.xSpeed = xSpeedStart;
     this.ySpeed = ySpeedStart;
     this.timeSlice = timeSliceInput;

   }
  //This method decreases the ball's speed by 1% per second and update the position of the Ball
   public void newBallPos(){

     xPos += xSpeed * timeSlice;
     yPos += ySpeed * timeSlice;
     xSpeed *= (Math.pow(0.99, timeSlice));
     ySpeed *= (Math.pow(0.99, timeSlice));
     speed = Math.sqrt((xSpeed * xSpeed) + (ySpeed * ySpeed));
   }

   //This method returns the ball's speed
   // public double getSpeed(){
   //   return speed;
   // }

   //this method returns the ball's yPosition
   public double getYPosition(){
     return yPos;
   }

   //this method returns the ball's xPosition
   public double getXPosition(){
     return xPos;
   }


   //This method returns if the speed of the ball is less than 1 inch per second
   public boolean continuesToMove(){
     if(Math.abs(speed) < MINIMUM_SPEED){
       return false;
     }
     return true;
   }

   //This method returns the string of the current speed
   public String toString(){
     String newSpeed = "00.00";
     String newPosition = "00.00";

     DecimalFormat speedFormat = new DecimalFormat (newSpeed);
     DecimalFormat positionFormat = new DecimalFormat (newPosition);

     if ( this.continuesToMove() ) {
       return "Velocity: " + speedFormat.format( xSpeed ) + " , " + speedFormat.format( ySpeed ) + "; Position: " + positionFormat.format( xPos ) + " , " + positionFormat.format( yPos ) ;
     }
     else {
       return "Position: " + positionFormat.format( xPos ) + " , " + positionFormat.format( yPos ) + "The ball is not moving!";
     }
   }

   //This is the main method used to test and run the code
   public static void main(String args[]){
     Ball b = new Ball(10, 10, 1, 1, 1);

     System.out.println( "TESTING newBallPos()" );

      b.timeSlice = 10;

      for ( int i = 0; i < 10; i++ ) {
        try { System.out.println( "   " + b.toString() ); }
        catch (Exception e) { System.out.println( e ); }
        b.newBallPos();
      }

      //TEST getXPosition()
      System.out.println( "\nTESTING getXPosition()" );

      b = new Ball( 40 , 40 , -10 , -10 , 1 );
      for ( int i = 0; i < 5; i++ ) {
        try { System.out.println( "   " + "x-position: " + b.getXPosition() ); }
        catch (Exception e) { System.out.println( e ); }
        b.newBallPos();
      }

      //TEST getYPosition()
      System.out.println( "\nTESTING getYPosition()" );

      b = new Ball( 0 , 0 , 10 , 10 , 1 );
      for ( int i = 0; i < 5; i++ ) {
        try { System.out.println( "   " + "y-position: " + b.getYPosition() ); }
        catch (Exception e) { System.out.println( e ); }
        b.newBallPos();
      }


      //TESTING continuesToMove()
      System.out.println( "\nTESTING continuesToMove()" );
      b = new Ball( 5 , 15 , 11 , 12 , 20 );
      for ( int i = 0; i < 40; i++ ) {
        try { System.out.println( "   " + "is moving: " + b.continuesToMove() + "   " + b.toString() ); }
        catch (Exception e) { System.out.println( e ); }
        b.newBallPos();
      }

   }
 }
