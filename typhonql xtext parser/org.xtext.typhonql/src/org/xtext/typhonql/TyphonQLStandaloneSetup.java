/*
 * generated by Xtext 2.20.0
 */
package org.xtext.typhonql;


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class TyphonQLStandaloneSetup extends TyphonQLStandaloneSetupGenerated {

	public static void doSetup() {
		new TyphonQLStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}