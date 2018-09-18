package automail;

import strategies.IMailPool;

//WeakRobot extends Robot class with a tube size of 4 and a weight limit of 2000 and it is not careful
public class WeakRobot extends Robot {
	
	private final int MAXIMUN_WEIGHT = 2000;
	private final int MAXIMUM_CAPACITY = 4;
	
	public WeakRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool);
		tube = new StorageTube(MAXIMUM_CAPACITY);
		changeToWeak();
	}

	@Override
	//check if the item is not heavy for the weak robot to carry
	public Boolean checkWeight(MailItem deliveryItem) {
		// TODO Auto-generated method stub
		if (deliveryItem.weight > MAXIMUN_WEIGHT) {
			return true;
		}
		return false;
	}
	
	


}

