 /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  Lindsey Fry
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;
public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
   private static final double MINUTE_IN_AN_HOUR = 60.0;
   private static final double HOURS_ON_A_CLOCK = 12.0;
   private static final double SECONDS_IN_AN_HOUR = 3600.0;
   private static final double SECONDS_IN_A_MINUTE = 60.0;
   private double currentDegrees;
   private double defaultDegrees;
   //private String time;
   private double timeSlice = 0;
   // private double angle;
   private int hour = 0;
   private int minute = 0;
   private int second = 0;
   private double totalSeconds = 0;
   double angleHour = 0;
   double angleMinute = 0;
   //private double angleSecond;
   double middleAngle = 0;



  /**
   *  Constructor goes here
   */
   public Clock(double angle, double timeSlice) {
     defaultDegrees = angle;
     currentDegrees = angle;
     timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
    this.timeSlice = timeSlice;
     if(angle > MAXIMUM_DEGREE_VALUE || angle < 0){
       System.exit((int)INVALID_ARGUMENT_VALUE);
     }
     if(timeSlice <= 0 || timeSlice > 1800.0){
       System.out.println("Invalid Time Slice Value. Please enter a number between 0 and 1800.");
       System.exit((int)INVALID_ARGUMENT_VALUE);
     }

   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
     totalSeconds += timeSlice;
     updateDegrees();
     hour = (int)Math.floor(totalSeconds / 3600);
     minute = (int)Math.floor((totalSeconds % 3600) /60);
     second = (int)totalSeconds - ((minute * 60) + (hour * 3600));
     // hour = (int)(totalSeconds/SECONDS_IN_AN_HOUR);
     // minute = (int)((totalSeconds - hour)/SECONDS_IN_A_MINUTE);
     // //minute = (int)((totalSeconds/SECONDS_IN_AN_HOUR)/SECONDS_IN_A_MINUTE);
     // //second = (int)(totalSeconds % 60);
     // second = (int)(((totalSeconds/SECONDS_IN_AN_HOUR)/SECONDS_IN_A_MINUTE)%60);
     return totalSeconds;
   }

   public void updateDegrees(){
     currentDegrees += defaultDegrees;
     // double newAngle = defaultAngle + currentAngle;
     if(currentDegrees > MAXIMUM_DEGREE_VALUE){
       currentDegrees -= MAXIMUM_DEGREE_VALUE;
     }
     //return newAngle;
   }


  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      //hour = Math.floor(totalSeconds/3600); //total HOURS
      angleHour = (totalSeconds * HOUR_HAND_DEGREES_PER_SECOND);
      while(angleHour > MAXIMUM_DEGREE_VALUE){
        angleHour -= MAXIMUM_DEGREE_VALUE;
      }
      return angleHour;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
    //minute = totalSeconds % 3600;
    angleMinute = (totalSeconds * MINUTE_HAND_DEGREES_PER_SECOND);
    while(angleMinute > MAXIMUM_DEGREE_VALUE){
      angleMinute -= MAXIMUM_DEGREE_VALUE;
    }
      return angleMinute;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
     middleAngle = Math.abs(getHourHandAngle() - getMinuteHandAngle())% 360;
     if(middleAngle > 180){
       middleAngle -= 360;
       //middleAngle = 360 - middleAngle;
     }
     return middleAngle;

   }
//add a method to check if my current angle is equal to the angle my user is looking for

   public double getDegree(){
     return currentDegrees;
   }

   /**
    *  Method to fetch the total number of seconds
    *   we can use this to tell when 12 hours have elapsed
    *  @return double-precision value the total seconds private variable
    */
   public double getTotalSeconds() {
      return totalSeconds;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {

      String hourMinute = "00";
      String secondString = "00.0";
      DecimalFormat hourMinuteFormat = new DecimalFormat(hourMinute);
      DecimalFormat secondStringFormat = new DecimalFormat(secondString);

      String time = hourMinuteFormat.format(hour) + ":" + hourMinuteFormat.format(minute) + ":" + secondStringFormat.format(second);
      return time;
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {
     Clock clock = new Clock(90, 30);
     // for(int i = 0; i <121; i++){
     clock.tick();
     // }
      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      System.out.println( "    New clock created: " + clock.toString() );
      System.out.println("Tester: " + clock.getHourHandAngle());
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      //try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      //catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
   }
}
