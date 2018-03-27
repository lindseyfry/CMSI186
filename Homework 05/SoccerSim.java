/**   Ball.java
     Timer.java
     - handle the tick value
     - find the time right now
     - toString
     SoccerSim.java
     - At a minimum
     Vf = Vo - ((Vo *.01) *timeSlice)
*/
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
 import java.util.HashSet;
 import java.util.ArrayList;
 import java.util.Iterator;

 public class SoccerSim{
   public static void main (String args[]){
     //double RADIUS_IN_INCHES = 4.5;
     double TIME_SLICE = 1;
     double X_CENTER = 1000;
     double Y_CENTER = 1000;
     double DIAMETER= 0.75;

     Timer timer = new Timer(0);
     Ball pole = new Ball(0, 0, 0, 0, 0);
     Ball ball = new Ball(0, 0, 0, 0, 0);

     //creates an array for the balls and the pole
     ArrayList<Ball> ballList = new ArrayList<Ball>();

     //initialize timer, pole and array after checking the arguments
     if(args.length % 4 == 1 && args.length >= 4){
       timer = new Timer(TIME_SLICE);
       pole = new Ball((double)Math.floor((Math.random() * X_CENTER) + 1), (double)Math.floor((Math.random() * Y_CENTER)+1), 0, 0, TIME_SLICE);
       for( int i = 0; i < args.length; i += 4){
         try{
           ball = new Ball(Double.parseDouble(args[i]), Double.parseDouble(args[i + 1]), Double.parseDouble(args[i + 2]), Double.parseDouble(args[i + 3]), TIME_SLICE);
           ballList.add(ball);
         }
         catch(NumberFormatException e){
           System.out.println("The arguments must be doubles");
           System.exit(1);
         }
        }
       }
       else if(args.length % 4 == 1 && args.length >= 4){
         try{
           pole = new Ball( (double)Math.floor( ( Math.random() * X_CENTER ) + 1  ) , (double)Math.floor( ( Math.random() * Y_CENTER) + 1 ) , 0 , 0 , Double.parseDouble( args[ args.length - 1 ] ) );
         }
         catch(NumberFormatException e){
           System.out.println( "Enter doubles" );
           System.exit( 1 );
         }
         timer = new Timer(Double.parseDouble(args[args.length - 1]));

         for(int j = 0; j < args.length - 1; j += 4){
           try{
             ball = new Ball(Double.parseDouble(args[j]), Double.parseDouble(args[j + 1]), Double.parseDouble(args[j + 2]), Double.parseDouble(args[j + 3]), Double.parseDouble(args[j - 1]));
             ballList.add(ball);
           }
           catch(NumberFormatException e){
             System.out.println("Please enter a double");
             System.exit(1);
           }
          }
         }
         else{
           System.out.println("Please enter Speeds and Positions for Ball X and Y and the Time increment");
           System.exit(1);
         }

         //New array of the elements within the boundary
         ArrayList<Ball> inBound = new ArrayList<Ball>();
         for(Ball b : ballList){
           inBound.add(b);
         }
         inBound.add(pole);

       System.out.println("INITIAL REPORT");
       System.out.println( "   " + "Time Slice = " + timer.getTimeSlice() );
       System.out.println( "   " + "Pole: X-Position = " + pole.getXPosition() + " Y-Position = " + pole.getYPosition() + "\n" );

       HashSet<Ball> roll = new HashSet<Ball>();

       // run simulation and check for collisions
       while ( roll.size() < ballList.size() ) {

         System.out.println( "\nREPORT AT " + timer.toString() );
         for ( int k = 0; k < ballList.size(); k++ ) {
           // change print if ball is off field
           if ( Math.abs( ballList.get( k ).getXPosition() ) > X_CENTER || Math.abs( ballList.get( k ).getYPosition() ) > Y_CENTER ) {
             System.out.println( "   " + "Ball " + k + ": " + "   " + "   " + "   " + "<out of bounds>" );
           }
           else {
             System.out.println( "   " + "Ball " + k + ": " + ballList.get( k ).toString() );
           }
         }

         // check for collisions
         for ( int l = 0; l < inBound.size()-1; l++ ) {
           for ( int m = l + 1; m < inBound.size(); m++ ) {
             if ( Math.sqrt( Math.pow( (inBound.get( l ).getXPosition() - inBound.get( m ).getXPosition()) , 2 ) + Math.pow( (inBound.get( l ).getYPosition() - inBound.get( m ).getYPosition()) , 2 ) ) <= DIAMETER ) {
               String end = ( ballList.indexOf( inBound.get( m ) ) == -1 ) ? "Pole" : Integer.toString( ballList.indexOf( inBound.get( m ) ) );
               System.out.println( "Ball collision " + ballList.indexOf( inBound.get( l ) ) + " & " + end );
               System.exit(0);
             }
           }
         }

         //check if balls are still in motion
         for ( Ball b : ballList ) {
           if ( !b.continuesToMove() ) {
             roll.add( b );
           }
           else {
             b.newBallPos();
           }
         }

         //update balls that are in bounds
         for ( Iterator<Ball> newIt = inBound.iterator(); newIt.hasNext(); ) {
           Ball bb = newIt.next();
           if ( Math.abs( bb.getXPosition() ) > X_CENTER || Math.abs( bb.getYPosition() ) > Y_CENTER ) {
             newIt.remove();
             roll.add( bb );
           }
         }

         timer.tick();
       }

       System.out.println( "\nNO COLLISION POSSIBLE" );
     }
   }
