import java.util.Properties;
import java.io.FileReader;
import java.io.IOException;

public class PropertiesHandler {

	String fileName;

	public PropertiesHandler(String fileName) {
		setFileName(fileName);
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public Properties setProperties() {
		Properties p = new Properties();
		FileReader reader;

		try {
			reader = new FileReader(fileName);
			p.load(reader);
		} catch (IOException e) {
			System.err.println("File not found!");
		}
		return p;
	}

	public void editProperty(Properties p, String propertyKey, String oldString, String newString) {
		p.replace(propertyKey, p.getProperty(propertyKey).replace(oldString, newString));
	}
}
