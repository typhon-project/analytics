package ac.york.typhon.generator.helper;

public interface GeneratorConstants {

	public interface FileNames {
		String NON_FINANCIAL_EVENTS = "non_fnc_events.csv";
		String FINANCIAL_TRANSATIONS = "transactions.csv";
		String SQL_LOG_FILE_NAME = "sky.csv";
		String TWICKY_CSV_CAUSING_ISSUE = "sample_csv_causing_problem.csv";
		String TWICKY_CSV_CAUSING_ISSUE_COMPLETE_FILE = "F:\\general_log.CSV";

	}

	public interface RemoteResourceCredentials {
		String HOST = "remote.host";
		String USERNAME = "remote.username";
		String PASSWORD = "remote.password";
		String PORT = "remote.port";
		String FILE_PATH = "remote.file";

	}

	// public interface RemoteResourceHeader {
	// int EVENT_TIME_FIELD_INDEX = 0;
	// int USER_HOST_FIELD_INDEX = 1;
	// int THREAD_ID_FIELD_INDEX = 2;
	// int SERVER_ID_FIELD_INDEX = 3;
	// int COMMAND_TYPE_FIELD_INDEX = 4;
	// int ARGUMENT_FIELD_INDEX = 5;
	// }

	public interface RemoteResourceHeader {
		String EVENT_TIME = "EVENT_TIME";
		String USER_HOST = "USER_HOST";
		String THREAD_ID = "THREAD_ID";
		String SERVER_ID = "SERVER_ID";
		String COMMAND_TYPE = "COMMAND_TYPE";
		String ARGUMENT = "ARGUMENT";

		public static int getFieldsCount() {
	
			return RemoteResourceHeader.class.getDeclaredFields().length;

		}

	}

}
