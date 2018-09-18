package strategies;

import java.util.LinkedList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.function.Consumer;

import automail.MailItem;
import automail.PriorityMailItem;
import automail.Robot;
import automail.StorageTube;
import exceptions.TubeFullException;
import exceptions.FragileItemBrokenException;

public class MyMailPool implements IMailPool {
	private class Item {
		int priority;
		int destination;
		boolean heavy;
		boolean fragile;
		MailItem mailItem;
		// Use stable sort to keep arrival time relative positions
		
		public Item(MailItem mailItem) {
			priority = (mailItem instanceof PriorityMailItem) ? ((PriorityMailItem) mailItem).getPriorityLevel() : 1;
			heavy = mailItem.getWeight() >= 2000;
			destination = mailItem.getDestFloor();
			fragile = mailItem.getFragile();
			this.mailItem = mailItem;
		}
	}
	
	public class ItemComparator implements Comparator<Item> {
		@Override
		public int compare(Item i1, Item i2) {
			int order = 0;
			if (i1.priority < i2.priority) {
				order = 1;
			} else if (i1.priority > i2.priority) {
				order = -1;
			} else if (i1.destination < i2.destination) {
				order = 1;
			} else if (i1.destination > i2.destination) {
				order = -1;
			}
			return order;
		}
	}
	
	private LinkedList<Item> fragilePool;
	private LinkedList<Item> nonFragilePool;
	private LinkedList<Robot> robots;
	private int lightCount;

	public MyMailPool(){
		// Start empty
		fragilePool = new LinkedList<Item>();
		nonFragilePool = new LinkedList<Item>();
		lightCount = 0;
		robots = new LinkedList<Robot>();
	}

	// add item to pool separately
	public void addToPool(MailItem mailItem) {
		Item item = new Item(mailItem);
		if (item.fragile) {
			fragilePool.add(item);
			fragilePool.sort(new ItemComparator());
		} else {
			nonFragilePool.add(item);
			if (!item.heavy) lightCount++;
			nonFragilePool.sort(new ItemComparator());
		}
	}
	
	@Override
	public void step() throws FragileItemBrokenException {
		for (Robot robot: (Iterable<Robot>) robots::iterator) { fillStorageTube(robot); }
	}
	
	private void fillStorageTube(Robot robot) throws FragileItemBrokenException {
		StorageTube tube = robot.getTube();
		StorageTube temp = new StorageTube(tube.getCapacity());
		try { // Get as many items as available or as fit
			// for careful robots just add a fragile item if there are some fragile items
			if (robot.isCareful() && !fragilePool.isEmpty()) {
				Item item = fragilePool.remove();
				temp.addItem(item.mailItem);
			} // for robots which don't have weight limits add items til tube is full
			else if (robot.isStrong()) {
				while(temp.getSize() < tube.getCapacity() && !nonFragilePool.isEmpty() ) {
					Item item = nonFragilePool.remove();
					if (!item.heavy) lightCount--;
					temp.addItem(item.mailItem);
				}
			} else {
				ListIterator<Item> i = nonFragilePool.listIterator();
				while(temp.getSize() < tube.getCapacity() && lightCount > 0) {
					Item item = i.next();
					if (!item.heavy) {
						temp.addItem(item.mailItem);
						i.remove();
						lightCount--;
					}
				}
			}
			if (temp.getSize() > 0) {
				while (!temp.isEmpty()) tube.addItem(temp.pop());
				robot.dispatch();
			}
		}
		catch(TubeFullException e){
			e.printStackTrace();
		}
	}

	@Override
	public void registerWaiting(Robot robot) { // assumes won't be there
		if (robot.isCareful() && fragilePool.size() > 0) {
			robots.add(robot); 
		} else if (robot.isStrong() ) {
			robots.add(robot);
		} else {
			robots.addLast(robot); // weak robot last as want more efficient delivery with highest priorities
		}
	}

	@Override
	public void deregisterWaiting(Robot robot) {
		robots.remove(robot);
	}

}
