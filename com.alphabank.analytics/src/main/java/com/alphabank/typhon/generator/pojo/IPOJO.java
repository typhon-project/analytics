package com.alphabank.typhon.generator.pojo;

import java.sql.ResultSet;

public interface IPOJO {

	void populate(ResultSet resultSet);

	String toInsert();

}
