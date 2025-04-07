package org.example.app_logistics_base.logistic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.app_logistics_base.truck.TruckFileReader;

import java.io.*;

public class LogisticFileReader {
	private static final Logger LOGGER = LogManager.getLogger(LogisticFileReader.class.getName());

	public static int readLogisticConfig(String fileName) throws IOException {
		int terminalsAvailable = 0;

		try (InputStream is = TruckFileReader.class.getClassLoader().getResourceAsStream(fileName);
			 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			LOGGER.info("Reading logistic config file: " + fileName);
			String line;

			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(" ");

				terminalsAvailable = Integer.parseInt(tokens[0]);
			}
		} catch (IOException e) {
			LOGGER.error("Error while reading logistic config file: " + fileName, e);
			throw new IOException(e.getMessage());
		}

		return terminalsAvailable;
	}
}
