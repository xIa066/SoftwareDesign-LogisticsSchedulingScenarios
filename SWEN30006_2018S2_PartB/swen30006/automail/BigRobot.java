package automail;

import strategies.IMailPool;

public class BigRobot extends Robot {
	
	private final int MAXIMUM_CAPACITY = 6;
	
	public BigRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool);
		tube = new StorageTube(MAXIMUM_CAPACITY);
	}


	
}