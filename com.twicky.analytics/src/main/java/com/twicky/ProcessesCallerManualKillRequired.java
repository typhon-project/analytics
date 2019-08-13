package com.twicky;

import java.io.File;
import java.io.IOException;

import com.twicky.authorization.PreEventAuthorizer;

import ac.york.typhon.generator.PostEventsGenerator;
import ac.york.typhon.generator.PreEventsGenerator;

public class ProcessesCallerManualKillRequired {

	public static void exec(Class klass) throws IOException,
			InterruptedException {
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator
				+ "java";
		String classpath = System.getProperty("java.class.path");
		String className = klass.getName();

		ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath,
				className);

		Process process = builder.inheritIO().start();
	
//		process.wait();// waitFor();
//		return process.exitValue();
	}

	public static void main(String[] args) throws Exception {

		
		ProcessesCallerManualKillRequired.exec(PreEventsGenerator.class);
		ProcessesCallerManualKillRequired.exec(PreEventAuthorizer.class);
		ProcessesCallerManualKillRequired.exec(PostEventsGenerator.class);
		ProcessesCallerManualKillRequired.exec(FollowersActivity.class);
		

//		try {
//
//			// create a new process
//			System.out.println("Creating Process");
//
//			ProcessBuilder builder = new ProcessBuilder("notepad.exe");
//			Process pro = builder.start();
//
//			// wait 10 seconds
//			System.out.println("Waiting");
//			Thread.sleep(10000);
//
//			// kill the process
//			pro.destroy();
//			System.out.println("Process destroyed");
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}

	}

}
