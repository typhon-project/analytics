package ac.uk.york.typhon.analytics.casestudies.alphabank.simulator;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SplitStream;

import ac.uk.york.typhon.analytics.authorization.commons.enums.ExternalTopicType;
import ac.uk.york.typhon.analytics.casestudies.alphabank.authorizationfilters.FinancialEventAuthorization;
import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.messaging.StreamManager;
import ac.uk.york.typhon.query.generator.pojo.FinancialEvent;
import ac.uk.york.typhon.query.generator.source.impl.AlphaBankMariaDBImpl;

public class GeneratorUSEGeneratorPackage {

	public static void main(String[] args) throws Exception {

		AlphaBankMariaDBImpl ab = new AlphaBankMariaDBImpl();
		ab.generate();
		

	}

}
