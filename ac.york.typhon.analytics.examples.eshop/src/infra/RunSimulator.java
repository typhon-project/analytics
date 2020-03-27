package infra;

import actions.Action;
import queryGenerators.InsertCommentGenerator;
import queryGenerators.InsertUserGenerator;

public class RunSimulator {
	
	public static final Boolean GENERATE_USERS = true;
	public static final int NUMBER_OF_USERS = 30;

	public static void main(String[] args) throws Exception {

		if(GENERATE_USERS) {
			InsertUserGenerator iug = new InsertUserGenerator();
			for (int i=0; i < NUMBER_OF_USERS; i++) {
				System.out.println(iug.generateQuery());
			}
		}
		
		// Create query generators
//		InsertCommentGenerator insertComment = new InsertCommentGenerator();
//		
//		Action a1 = new Action(insertComment.generateQuery());
//		a1.setName("Action");
//		a1.start();
	}

}
