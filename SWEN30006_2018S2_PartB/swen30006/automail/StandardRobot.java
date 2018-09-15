package automail;

import strategies.IMailPool;

public class StandardRobot extends Robot {
	
	private final int MAXIMUM_CAPACITY = 4;
	
	public StandardRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool);
		tube = new StorageTube(MAXIMUM_CAPACITY);
	}


	
}