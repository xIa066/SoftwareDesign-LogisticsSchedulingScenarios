package strategies;


import java.util.List;

import automail.IMailDelivery;
import automail.Robot;
import automail.Simulation.RobotType;
import automail.*;



public class Automail {
	      
    public Robot[] robot;
    public IMailPool mailPool;
    
    public Automail(IMailPool mailPool, IMailDelivery delivery, List<automail.Simulation.RobotType> robotTypes) {
    	// Swap between simple provided strategies and your strategies here
    	    	
    	/** Initialize the MailPool */
    	
    	this.mailPool = mailPool;
    
    	
    	/** Initialize robots */
    	System.out.println(robotTypes);
    	System.out.println(robotTypes.size());
    	
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
    		//robot[count++] = new Robot(delivery, mailPool, robotType.toString());
    	}

    	
//    	robot[0] = new Robot(delivery, mailPool, weak);
//    	robot[1] = new Robot(delivery, mailPool, strong);
//    	robot[2] = new Robot(delivery, mailPool, strong);
    }
    
}
