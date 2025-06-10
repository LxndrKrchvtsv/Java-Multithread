package org.example.app_logistics_base.truck;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TruckFileReader {
	private static final Logger LOGGER = LogManager.getLogger(TruckFileReader.class.getName());

	public static List<Truck> readTrucksConfig(String fileName) throws IOException {
		List<Truck> trucks = new ArrayList<>();

		try (InputStream is = TruckFileReader.class.getClassLoader().getResourceAsStream(fileName);
			 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			LOGGER.info("Reading trucks...");
			String line;

			while ((line = br.readLine()) != null) {
				String[] parts = line.split(" ");

				int id = Integer.parseInt(parts[0].trim());
				TruckOperationType operation = TruckOperationType.valueOf(parts[1].trim().toUpperCase());
				boolean perishable = Boolean.parseBoolean(parts[2].trim());
				long processingTime = Long.parseLong(parts[3].trim());

				trucks.add(new Truck(id, perishable, operation, processingTime));
			}
		} catch (IOException e) {
			LOGGER.error("Error while reading trucks config file: {}", fileName, e);
			throw new IOException(e.getMessage());
		}
		return trucks;
	}
}
