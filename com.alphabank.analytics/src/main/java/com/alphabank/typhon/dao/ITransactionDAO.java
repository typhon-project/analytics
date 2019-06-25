package com.alphabank.typhon.dao;

import com.alphabank.typhon.entity.TransactionEntity;

public interface ITransactionDAO {

	TransactionEntity selectTransactionById(String transactionId);

}
