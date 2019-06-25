package com.alphabank.typhon.dao;

import com.alphabank.typhon.entity.CustomerDetailsEntity;

public interface ICustomerDAO {

	CustomerDetailsEntity selectCustomerDetailsByCDICode(String id);

}
