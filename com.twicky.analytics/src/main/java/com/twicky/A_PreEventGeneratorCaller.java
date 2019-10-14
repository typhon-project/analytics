package com.twicky;

import ac.york.typhon.generator.generators.IGenerator;
import ac.york.typhon.generator.source.IFileSource;

import com.twicky.generator.TwickyCSVGeneratorImpl;

public class A_PreEventGeneratorCaller {

	public static void main(String[] args) throws Exception {
		// PreEventsGenerator.main(null);

		IGenerator generator = new TwickyCSVGeneratorImpl(
				IFileSource.Host.LOCAL);
		generator.generate();

	}

}
