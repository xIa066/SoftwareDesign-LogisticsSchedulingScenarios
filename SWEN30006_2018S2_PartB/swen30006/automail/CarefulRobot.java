package automail;

import strategies.IMailPool;

//Careful robot extends robot class with a tube size of 3 and it is a strong and careful robot with a different movement strategy
public class CarefulRobot extends Robot {
	
	//two states to simulate two steps
	private enum MoveState {Move, Stay}
	private MoveState currentState;
	
	
	private final int MAXIMUM_CAPACITY = 3;
	
	public CarefulRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool);
		tube = new StorageTube(MAXIMUM_CAPACITY);
		currentState = MoveState.Stay;
		changeToCareful();
	}
	
	@Override
	//first step:not move
	//second step:move
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
		}
	}		
}