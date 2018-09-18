package strategies;


import java.util.List;

import automail.IMailDelivery;
import automail.Robot;
import automail.Simulation.RobotType;
import automail.*;



public class Automail {
	      
    private Robot[] robot;
    public IMailPool mailPool;
    
    //get robot by index
    public Robot getRobotByIndex(int index) {
		return robot[index];
	}	
    
    //add param robotTypes, it reads from property and use it to set the simulation robots
    public Automail(IMailPool mailPool, IMailDelivery delivery, List<automail.Simulation.RobotType> robotTypes) {
    	// Swap between simple provided strategies and your strategies here
    	    	
    	/** Initialize the MailPool */
    	
    	this.mailPool = mailPool;
    
    	
    	/** Initialize robots */
    	//use different constructors for different types of robots
      	robot = new Robot[robotTypes.size()];
    	int count = 0;
    	for(RobotType robotType : robotTypes) {
    		switch (robotType) {
			case Weak:
				robot[count++] = new WeakRobot(delivery, mailPool);
				break;
			case Big:
				robot[count++] = new BigRobot(delivery, mailPool);
				break;
			case Careful:
				robot[count++] = new CarefulRobot(delivery, mailPool);
				break;
			case Standard:
				robot[count++] = new StandardRobot(delivery, mailPool);
				break;
			}
    	}
    }
    
}
