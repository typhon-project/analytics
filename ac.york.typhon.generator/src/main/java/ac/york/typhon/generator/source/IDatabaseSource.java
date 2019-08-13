package ac.york.typhon.generator.source;

import java.sql.ResultSet;

public interface IDatabaseSource {

	ResultSet executeStatement(String query);

	void closeConnection();

}
