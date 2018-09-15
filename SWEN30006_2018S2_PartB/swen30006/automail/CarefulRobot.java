package automail;

import strategies.IMailPool;

public class CarefulRobot extends Robot {
	
	private final int MAXIMUM_CAPACITY = 3;
	
	public CarefulRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool);
		tube = new StorageTube(MAXIMUM_CAPACITY);
	}

	
}