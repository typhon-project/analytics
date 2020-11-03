package ac.york.typhon.analytics.generation.plugin;

public class DefaultPOMAuth {

	public static String contents() {
		return "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\r\n" + 
				"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" + 
				"	xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\r\n" + 
				"	<modelVersion>4.0.0</modelVersion>\r\n" + 
				"	<groupId>typhon</groupId>\r\n" + 
				"	<artifactId>example</artifactId>\r\n" + 
				"	<version>0.0.1</version>\r\n" + 
				"	<name>example name</name>\r\n" + 
				"\r\n" + 
				"	<repositories>\r\n" + 
				"		<repository>\r\n" + 
				"			<id>internal</id>\r\n" + 
				"			<url>http://archiva.clmsuk.com:8090/repository/internal/</url>\r\n" + 
				"		</repository>\r\n" + 
				"	</repositories>\r\n" + 
				"\r\n" + 
				"	<dependencies>\r\n" + 
				"\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>typhon</groupId>\r\n" + 
				"			<artifactId>ac.york.typhon.analytics</artifactId>\r\n" + 
				"			<version>0.0.1-SNAPSHOT</version>\r\n" +
				"			<scope>provided</scope>\r\n" +
				"		</dependency>\r\n" + 
				"		\r\n" + 
				"	</dependencies>\r\n" + 
				"\r\n" + 
				"	<build>\r\n" + 
				"		<sourceDirectory>src</sourceDirectory>\r\n" + 
				"			<resources>\r\n" +
				"				<resource>\r\n" +
				"					<directory>resources</directory>\r\n" +
				"				</resource>\r\n" +
				"			</resources>\r\n" +
				"		<plugins>\r\n" + 
				"			<plugin>\r\n" + 
				"				<groupId>org.codehaus.mojo</groupId>\r\n" + 
				"				<artifactId>build-helper-maven-plugin</artifactId>\r\n" + 
				"				<executions>\r\n" + 
				"					<execution>\r\n" + 
				"						<phase>generate-sources</phase>\r\n" + 
				"						<goals>\r\n" + 
				"							<goal>add-source</goal>\r\n" + 
				"						</goals>\r\n" + 
				"						<configuration>\r\n" + 
				"							<sources>\r\n" + 
				"								<source>src-gen</source>\r\n" + 
				"							</sources>\r\n" + 
				"						</configuration>\r\n" + 
				"					</execution>\r\n" + 
				"				</executions>\r\n" + 
				"			</plugin>\r\n" + 
				"			<plugin>\r\n" + 
				"				<groupId>org.apache.maven.plugins</groupId>\r\n" + 
				"				<artifactId>maven-compiler-plugin</artifactId>\r\n" + 
				"				<version>3.3</version>\r\n" + 
				"				<configuration>\r\n" +
				"					<source>1.8</source>\r\n" + 
				"					<target>1.8</target>\r\n" +	
				"					<generatedSourcesDirectory>src-gen</generatedSourcesDirectory>\r\n" + 
				"				</configuration>\r\n" + 
				"			</plugin>\r\n" + 
				"			<plugin>\r\n" +
				"				<artifactId>maven-assembly-plugin</artifactId>\r\n" +
				"				<configuration>\r\n" +
				"					<archive>\r\n" +
				"						<manifest>\r\n" +
				"							<mainClass>PreEventAuthorizer</mainClass>\r\n" +
				"						</manifest>\r\n" +
				"					</archive>\r\n" +
				"					<descriptorRefs>\r\n" +
				"						<descriptorRef>jar-with-dependencies</descriptorRef>\r\n" +
				"					</descriptorRefs>\r\n" +
				"				</configuration>\r\n" +
				"				<executions>\r\n" +
				"					<execution>\r\n" +
				"						<id>make-assembly</id>\r\n" +
				"						<phase>package</phase>\r\n" +
				"						<goals>\r\n" +
				"							<goal>single</goal>\r\n" +
				"						</goals>\r\n" +
				"					</execution>\r\n" +
				"				</executions>\r\n" +
				"			</plugin>\r\n" +
				"		</plugins>\r\n" + 
				"	</build>\r\n" + 
				"\r\n" + 
				"</project>";
	}
	
}
