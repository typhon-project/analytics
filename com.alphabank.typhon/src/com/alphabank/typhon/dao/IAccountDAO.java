package com.alphabank.typhon.dao;

import com.alphabank.typhon.entity.AccountEntity;

public interface IAccountDAO {

	AccountEntity retrieveAccountByAccountNumber(String accountNumber);



}
