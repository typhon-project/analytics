package ac.york.typhon.generator.source.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import ac.york.typhon.generator.source.IFileSource;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class RemoteFileSourceImpl  extends FileSourceImpl implements IFileSource{

	private String user;
	private String host;
	private String password;
	private int port;
	private String file;

	private ChannelSftp sftpChannel;
	private Session session;

	public RemoteFileSourceImpl(String host, int port, String user,
			String password, String file) {

		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
		this.file = file;

	}

	@Override
	public Reader getReader() {
		BufferedReader bufferedStreamReader = null;

		try {
			JSch jsch = new JSch();

			session = jsch.getSession(user, host, port);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(password);

			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();
			sftpChannel = (ChannelSftp) channel;

			InputStream stream = sftpChannel.get(file);

			InputStreamReader streamReader = new InputStreamReader(stream);
			bufferedStreamReader = new BufferedReader(streamReader);

		} catch (JSchException | SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bufferedStreamReader;

	}

	@Override
	public void closeStream() {

		sftpChannel.exit();
		session.disconnect();

	}

	// private InputStream openStream() throws JSchException, SftpException,
	// IOException {
	//
	// JSch jsch = new JSch();
	// Session session = null;
	//
	// session = jsch.getSession(user, host, port);
	// session.setConfig("StrictHostKeyChecking", "no");
	// session.setPassword(password);
	// session.connect();
	//
	// Channel channel = session.openChannel("sftp");
	// channel.connect();
	// sftpChannel = (ChannelSftp) channel;
	//
	// InputStream stream = sftpChannel.get(file);
	//
	// // Path path = Paths.get(url.toURI());
	// Reader reader = new InputStreamReader(stream);
	// String id = null;
	//
	// Iterable<CSVRecord> records = CSVFormat.ORACLE.withHeader(
	// RemoteResourceHeader.EVENT_TIME,
	// RemoteResourceHeader.USER_HOST, RemoteResourceHeader.THREAD_ID,
	// RemoteResourceHeader.SERVER_ID,
	// RemoteResourceHeader.COMMAND_TYPE,
	// RemoteResourceHeader.ARGUMENT)
	//
	// .parse(reader);
	//
	// System.out.println(RemoteResourceHeader.getFieldsCount());
	//
	// for (CSVRecord record : records) {
	// // System.out.println(record);
	// Map map = record.toMap();
	// // System.out.println(map.size());
	// if (map.size() != RemoteResourceHeader.getFieldsCount()) {
	// continue;
	// }
	//
	// id = Utils.generateRandomId();
	// // Map<String, String > recordMap = record.toMap();
	//
	// String query = record.get(RemoteResourceHeader.ARGUMENT);
	// String timestamp = record.get(RemoteResourceHeader.EVENT_TIME);
	// String user = record.get(RemoteResourceHeader.USER_HOST);
	// // System.out.println(query);
	// Event preEvent = new PreEvent(id, query, user,
	// Utils.generateTimeStamp(), "dbUser");
	// System.out.println(preEvent);
	// // TopicPublisher.publish(AnalyticTopicType.PRE, preEvent);
	// int x = 6;
	//
	// }
	//
	// return stream;
	//
	// }

	// public void generateOLD() throws Exception {
	// try {
	//
	// // load events from the CSV files
	// Iterable<CSVRecord> records = retrieveRecordsIterator(this.csvFileName);
	// StatementFactory.setTableName(this.csvFileName.split("\\.")[0]);
	// String id = null;
	//
	// for (CSVRecord record : records) {
	//
	// String query = StatementFactory.initializeDMLString(record);
	//
	// if (StringUtils.isNotBlank(query)) {
	//
	// id = Utils.generateRandomId();
	//
	// Thread.sleep(RandomUtils.nextLong(0, 1000));
	//
	// Event preEvent = new PreEvent(id, query, "user",
	// Utils.generateTimeStamp(), "dbUser");
	//
	// TopicPublisher.publish(AnalyticTopicType.PRE, preEvent);
	//
	// Thread.sleep(RandomUtils.nextLong(0, 1000));
	//
	// }
	//
	// }
	// } catch (FileNotFoundException e1) {
	//
	// e1.printStackTrace();
	// }
	//
	// }

	// @Override
	// public void generate() {
	//
	// InputStream stream;
	// try {
	// stream = openStream();
	//
	// try {
	// BufferedReader br = new BufferedReader(new InputStreamReader(
	// stream));
	// String line;
	// while ((line = br.readLine()) != null) {
	// System.out.println(line);
	// }
	//
	// } catch (IOException io) {
	// System.out
	// .println("Exception occurred during reading file from SFTP server due to "
	// + io.getMessage());
	// io.getMessage();
	//
	// } catch (Exception e) {
	// System.out
	// .println("Exception occurred during reading file from SFTP server due to "
	// + e.getMessage());
	// e.getMessage();
	//
	// }
	//
	// closeStream();
	// } catch (JSchException | SftpException | IOException ex) {
	//
	// ex.printStackTrace();
	// }
	//
	// }

	// public static void main(String[] args) throws FileNotFoundException,
	// IOException, JSchException, SftpException {
	//
	// JSch jsch = new JSch();
	// Session session = null;
	// try {
	// session = jsch.getSession(user, host, port);
	// session.setConfig("StrictHostKeyChecking", "no");
	// session.setPassword(password);
	// session.connect();
	//
	// Channel channel = session.openChannel("sftp");
	// channel.connect();
	// ChannelSftp sftpChannel = (ChannelSftp) channel;
	//
	// InputStream stream = sftpChannel.get(file);
	// try {
	// BufferedReader br = new BufferedReader(new InputStreamReader(
	// stream));
	// String line;
	// while ((line = br.readLine()) != null) {
	// System.out.println(line);
	// }
	//
	// } catch (IOException io) {
	// System.out
	// .println("Exception occurred during reading file from SFTP server due to "
	// + io.getMessage());
	// io.getMessage();
	//
	// } catch (Exception e) {
	// System.out
	// .println("Exception occurred during reading file from SFTP server due to "
	// + e.getMessage());
	// e.getMessage();
	//
	// }
	//
	// sftpChannel.exit();
	// session.disconnect();
	// } catch (JSchException e) {
	// e.printStackTrace();
	// } catch (SftpException e) {
	// e.printStackTrace();
	// }
	//
	// }
}
