package automail;

import strategies.IMailPool;

public class WeakRobot extends Robot {
	
	private final int MAXIMUN_WEIGHT = 2000;
	private final int MAXIMUM_CAPACITY = 4;
	
	public WeakRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool);
		tube = new StorageTube(MAXIMUM_CAPACITY);
		changeToWeak();
	}

	@Override
	public Boolean checkWeight(MailItem deliveryItem) {
		// TODO Auto-generated method stub
		if (deliveryItem.weight > MAXIMUN_WEIGHT) {
			return true;
		}
		return false;
	}
	
	


}

