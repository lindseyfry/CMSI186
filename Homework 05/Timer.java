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
 public class Timer{
   private static final double SECONDS_IN_A_MINUTE = 60.0;
   private static final double SECONDS_IN_AN_HOUR = 3600.0;
   private static double timeSlice;
   private static double totalSeconds;
   private static double hour;
   private static double minute;
   private static double second;

   public Timer(double createTimeSlice ){
     timeSlice = createTimeSlice;
     totalSeconds = 0;
   }

   //This method calculates the next tick from the time increment
   public double tick(){
     totalSeconds += timeSlice;

     hour = Math.floor(totalSeconds/SECONDS_IN_AN_HOUR);
     minute = Math.floor(totalSeconds - (SECONDS_IN_AN_HOUR * hour));
     second = totalSeconds - (SECONDS_IN_AN_HOUR * hour) - (SECONDS_IN_A_MINUTE * minute);

     return totalSeconds;
   }

    //This method gets the total number of seconds
    public double getTotalSeconds(){
      return totalSeconds;
    }

    //This method gets the time Slice
    public double getTimeSlice(){
      return this.timeSlice;
    }

    //This method returns a string
    public String toString(){
      String hourMinute = "00";
      String secondString = "00";

      DecimalFormat hourMinuteFormat = new DecimalFormat(hourMinute);
      DecimalFormat secondStringFormat = new DecimalFormat(secondString);

      return hourMinuteFormat.format(hour) + ":" +  hourMinuteFormat.format(minute) + ":" +secondStringFormat.format(second);
    }

    //main method to test methods
    public static void main( String args[] ) {
     Timer t = new Timer( 1800 );

     //TEST tick()
     System.out.println( "TESTING tick()" );

     System.out.println( " time slice = 1800 seconds");
     for ( int i = 0; i < 10; i++ ) {
       try { System.out.println( "    Time: " + t.toString() ); }
       catch (Exception e) { System.out.println( e ); }
       t.tick();
     }

     System.out.println( "\n time slice = 10 seconds");
     t.timeSlice = 10;
     for ( int i = 0; i < 10; i++ ) {
       try { System.out.println( "    Time: " + t.toString() ); }
       catch (Exception e) { System.out.println( e ); }
       t.tick();
     }

     System.out.println( "\n time slice = 12.3 seconds");
     t.timeSlice = 12.3;
     for ( int i = 0; i < 10; i++ ) {
       try { System.out.println( "    Time: " + t.toString() ); }
       catch (Exception e) { System.out.println( e ); }
       t.tick();
     }

     System.out.println( "\n time slice = .456 seconds");
     t.timeSlice = .456;
     for ( int i = 0; i < 10; i++ ) {
       try { System.out.println( "    Time: " + t.toString() ); }
       catch (Exception e) { System.out.println( e ); }
       t.tick();
     }

     System.out.println( "\n time slice = -3 seconds");
     t.timeSlice = -3;
     for ( int i = 0; i < 10; i++ ) {
       try { System.out.println( "    Time: " + t.toString() ); }
       catch (Exception e) { System.out.println( e ); }
       t.tick();
     }

     //TEST getTotalSeconds()
     System.out.println( "\nTESTING getTotalSeconds()" );
     System.out.println( "   Time slice is incrementing by 10 each time." );
     for ( int i = 0; i < 25; i++ ) {
       t.timeSlice += 10;
       try { System.out.println( "    Time: " + t.toString() + " Total Seconds: " + t.getTotalSeconds() ); }
       catch (Exception e) { System.out.println( e ); }
       t.tick();
     }

     //TEST getTimeSlice()
     System.out.println( "\nTESTING getTimeSlice()" );
     t.timeSlice = 9;
     try { System.out.println( "  Current time slice: " + t.getTimeSlice() ); }
     catch (Exception e) { System.out.println( e ); }
   }
 }
