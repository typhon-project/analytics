package com.alphabank.typhon.dataaccess;

import ac.uk.york.typhon.analytics.commons.datatypes.commands.DMLCommand;

public interface IAccountActivityAccess {

	boolean isDormantAccount(String accountNumber);
	
	void closeConnection();

	 String retrieveAccountNumberFromDML(DMLCommand dmlCommand);


}
