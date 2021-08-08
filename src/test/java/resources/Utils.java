package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification reqspec;

	public RequestSpecification requestSpecification() throws IOException {

		if (reqspec == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logDetails.txt"));
			reqspec = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(getGlobalValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();

			return reqspec;
		}
		return reqspec;
	}

	public static String getGlobalValue(String key) throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\prem.rathore\\eclipse-workspace\\LibraryAPIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);

	}

	public String getJsonValue(String string, String key) {
		String resp = string.toString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();

	}

}
