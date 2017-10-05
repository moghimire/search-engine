package com.goglides.esservice.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileParser {

	static InputStream inputStream;

	public static void parseConfigFile(String fileName) throws IOException {
		try {
			Properties prop = new Properties();
			String propFileName = fileName;

			inputStream = ConfigFileParser.class.getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			// get the property value and print it out
			Configuration.HOST = prop.getProperty("host");
			Configuration.PORT = Integer.parseInt(prop.getProperty("port"));
			Configuration.CLUSTER_NAME = prop.getProperty("clusterName");
			Configuration.INDEX = prop.getProperty("index");
			Configuration.TYPE = prop.getProperty("type");
			Configuration.PAGE_SIZE = Integer.parseInt(prop.getProperty("page_size"));
			System.out.println("ELasticSearch Params");
			System.out.println("HOST: " + Configuration.HOST + "\nPORT: " + Configuration.PORT + "\nCLUSTERNAME: "
					+ Configuration.CLUSTER_NAME + "\nINDEX: " + Configuration.INDEX + "\nTYPE: " + Configuration.TYPE);
			System.out.println("PAGE_SIZE: " + Configuration.PAGE_SIZE);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
	}
}
