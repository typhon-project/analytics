package ac.york.typhon.generator.source.impl;

import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import ac.york.typhon.generator.source.IFileSource;

public abstract class FileSourceImpl implements IFileSource {

	@Override
	public
	Iterable<CSVRecord> getRecordsIterator(CSVFormat csvFormat) {

		Iterable<CSVRecord> records = null;
		try {
			Reader reader = this.getReader();
			records = csvFormat.parse(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return records;

	}

	//
	// public Iterable<CSVRecord> retrieveRecordsIterator(String fileName)
	// throws Exception {
	//
	// URL url = Resources.getResource(fileName);
	//
	// // URL url = ClassLoaderUtil.getResource(
	// // GeneratorConstants.CSV.FILE_NAME, PreEventsGenerator.class);
	//
	// Path path = Paths.get(url.toURI());
	//
	// Reader reader = new FileReader(path.toString());
	// Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader()
	// .parse(reader);
	//
	// return records;
	// }

	/**
	 * instantiate a reader from the resource folder within the classpath using
	 * the filename
	 * 
	 * @param fileName
	 * @return
	 */
	// public Reader getResourceReaderWithFileName(String fileName) {
	// Reader reader = null;
	// try {
	//
	// URL url = Resources.getResource(fileName);
	//
	// Path path = Paths.get(url.toURI());
	//
	// reader = new FileReader(path.toString());
	//
	// } catch (FileNotFoundException | URISyntaxException e) {
	//
	// e.printStackTrace();
	// }
	//
	// return reader;
	// }

	/**
	 * instantiate a reader outside the classpath using the absolute path
	 * 
	 * @param absoluteFilePath
	 * @return
	 */
	// public Reader getResourceReaderWithAbsolutePath(String absoluteFilePath)
	// {
	//
	// Reader reader = null;
	// try {
	// reader = new FileReader(absoluteFilePath);
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return reader;
	// }

	/**
	 * instantiate a reader from a remote server
	 * 
	 * @return
	 */
	// public Reader getReader() {
	//
	// JSch jsch = new JSch();
	// Session session = null;
	// ChannelSftp sftpChannel;
	//
	//
	// // session = jsch.getSession(user, host, port);
	// // session.setConfig("StrictHostKeyChecking", "no");
	// // session.setPassword(password);
	// // session.connect();
	// //
	// // Channel channel = session.openChannel("sftp");
	// // channel.connect();
	// // sftpChannel = (ChannelSftp) channel;
	//
	// // InputStream stream = sftpChannel.get(file);
	//
	// // Path path = Paths.get(url.toURI());
	// // Reader reader = new InputStreamReader(stream);
	//
	// return null;
	// }

}
