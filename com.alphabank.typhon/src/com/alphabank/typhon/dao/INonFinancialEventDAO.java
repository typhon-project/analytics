package com.alphabank.typhon.dao;

import com.alphabank.typhon.entity.NonFinancialEventEntity;

public interface INonFinancialEventDAO {



	NonFinancialEventEntity selectNonFinancialEventByAccountNumber(
			String accountNumber);

}
