package agents;

public class SimpleAgent2 extends Agent implements Runnable {

	@Override
	public void run() {
		System.out.println("SA 2: " + this.uuid);
	}

}
