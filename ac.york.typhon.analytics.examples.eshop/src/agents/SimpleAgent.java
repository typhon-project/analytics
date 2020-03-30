package agents;

import java.util.Random;

import utils.Utilities;

public class SimpleAgent extends Agent implements Runnable  {

	@Override
	public void run() {
		// Number of products navigated
		int  numOfProds = Utilities.getRandomNumberInRange(5,10);
		System.out.println("SA 1: " +  this.uuid);
	}

}
