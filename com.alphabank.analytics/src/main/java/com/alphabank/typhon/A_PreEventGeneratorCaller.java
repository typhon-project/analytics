package com.alphabank.typhon;

import ac.york.typhon.generator.generators.IGenerator;

import com.alphabank.typhon.generator.AlphaBankDatabaseGeneratorImpl;

public class A_PreEventGeneratorCaller {

	public static void main(String[] args) throws Exception {

//		AlphaBankGenerator.main(null);

		IGenerator generator = new AlphaBankDatabaseGeneratorImpl();
		generator.generate();
	}

}
