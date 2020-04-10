package agents;

import utils.Utilities;

import java.util.Random;

import infra.RunSimulator;

public class Agent {
	
	String uuid;

	public Agent() {
		Random r = new Random();
		System.out.println("All users: " + RunSimulator.allUsers.size());
		int uuidPosition = r.nextInt(RunSimulator.allUsers.size());
		this.uuid = RunSimulator.allUsers.get(uuidPosition);
		RunSimulator.allUsers.remove(uuidPosition);
	}
	
	public void randomSleep(int lowerBound, int upperBound) throws InterruptedException {
		int sleepTime = Utilities.getRandomNumberInRange(lowerBound, upperBound);
		Thread.sleep(sleepTime);
	}

}
