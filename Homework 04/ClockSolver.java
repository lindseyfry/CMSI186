/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
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

public class ClockSolver {

  private static final double MAX_NUM_OF_TOTAL_SECONDS = 43200.0;
  private final static double EPSILON_VALUE = .1;
  private static double angle = 0;
  private static double timeSlice = 0;
  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   // public ClockSolver(double d, double ts) {
   //    clock = new Clock(d,ts);
   //    d = degrees;
   //    ts = timeSlice;
   //
   //    //clock.handleInitialArguments(degrees, timeSlice);
   // }


  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */

   public void handleInitialArguments(String args[]){
        //Clock clock = new Clock();
        System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
        if( 0 == args.length ) {
           System.out.println( "   Sorry you must enter at least one argument\n" +
                               "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                               "   Please try again..........." );
           System.exit( 1 );
        }
        if( 1 == args.length){
          timeSlice = 1800.00;
          angle = Double.parseDouble(args[0]);
        }
        if(2 == args.length){
          timeSlice = Double.parseDouble(args[1]);
          angle = Double.parseDouble(args[0]);
        }
    }

        //
        // try{
        //   angle = clock.validateAngleArg( args[0] );
        // }catch (Exception e){
        //   System.out.println("Argument must be between 0 and 360.");
        // }
        //
        // try{
        //   timeSlice = clock.validateAngleArg( args[1] );
        // }catch (Exception e){
        //   timeSlice = clock.validateAngleArg("Argument must be between 0 and 180");
        // }timeSlice = 60.0;
        //
        // if(angle > 180){
        //   angle = angle - 180;
        // }
        // else{
        //   angle = angle;
        // }
    public static void main( String args[] ) {
      ClockSolver cse = new ClockSolver();
      //ClockEmpty clock    = new ClockEmpty();
      double[] timeValues = new double[3];
      cse.handleInitialArguments( args );
      Clock clock = new Clock(cse.angle, cse.timeSlice);
      while( clock.getTotalSeconds() <= MAX_NUM_OF_TOTAL_SECONDS) {
         if(cse.angle - clock.getHandAngle() <= EPSILON_VALUE){
           System.out.println(" " + clock.toString());
         }
         clock.tick();
      }
      System.exit( 0 );
   }
}
