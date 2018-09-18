package automail;

import strategies.IMailPool;

//BigRobot class extends Robot class with its own tube size of 6 and it is a strong and not careful robot
public class BigRobot extends Robot {
	
	private final int MAXIMUM_CAPACITY = 6;
	
	public BigRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool);
		tube = new StorageTube(MAXIMUM_CAPACITY);
	}


	
}