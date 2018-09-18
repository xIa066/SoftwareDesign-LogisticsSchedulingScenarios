package automail;

import strategies.IMailPool;

//StandardRobot extends robot class with a tube size of 4, it is strong and not careful
public class StandardRobot extends Robot {
	
	private final int MAXIMUM_CAPACITY = 4;
	
	public StandardRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool);
		tube = new StorageTube(MAXIMUM_CAPACITY);
	}


	
}