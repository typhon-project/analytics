package ac.york.typhon.generator.generators.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import ac.york.typhon.analytics.commons.AppConfiguration;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.streaming.TopicPublisher;
import ac.york.typhon.generator.commons.AlphaConstants;
import ac.york.typhon.generator.generators.IGenerator;
import ac.york.typhon.generator.source.IDatabaseSource;
import ac.york.typhon.generator.source.impl.DatabaseSourceImpl;

public abstract class DatabaseGeneratorImpl implements IGenerator {
	protected IDatabaseSource source;
	protected String statementString;

	public DatabaseGeneratorImpl() {
		loadConfiguration();
	}

	public abstract PreEvent populatePreEvent(ResultSet resultSet);

	private void loadConfiguration() {
		String url = AppConfiguration.getString(AlphaConstants.Database.URL);
		String username = AppConfiguration
				.getString(AlphaConstants.Database.USERNAME);
		String password = AppConfiguration
				.getString(AlphaConstants.Database.PASSWORD);
		source = new DatabaseSourceImpl(url, username, password);
	}

	@Override
	public void generate() {

		try {

			ResultSet resultSet = source.executeStatement(statementString);

			while (resultSet.next()) {
				Event event = populatePreEvent(resultSet);
				TopicPublisher.publish(AnalyticTopicType.PRE, event);
			}

			source.closeConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
