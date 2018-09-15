package automail;

import strategies.IMailPool;

public class CarefulRobot extends Robot {
	
	private enum MoveState {Move, Stay}
	private MoveState currentState;
	
	
	private final int MAXIMUM_CAPACITY = 3;
	
	public CarefulRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool);
		tube = new StorageTube(MAXIMUM_CAPACITY);
		currentState = MoveState.Stay;
	}
	
	@Override
	public void moveTowards(int destination) {
		int current_floor = getCurrentFloor();
		switch (currentState){
			case Move:
				if (current_floor < destination){
					current_floor++;
					setCurrentFloor(current_floor);
				}
				else {
					current_floor--;
					setCurrentFloor(current_floor);
				}
				currentState = MoveState.Stay;
				break;
			case Stay:
				currentState = MoveState.Move;
				break;
			default:
				currentState = MoveState.Move;
				break;
		}
	}		
}