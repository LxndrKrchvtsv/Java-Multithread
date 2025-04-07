package org.example.app_logistics_base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.app_logistics_base.truck.Truck;
import org.example.app_logistics_base.truck.TruckFileReader;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

	public static void main(String[] args) {
		try {
			List<Truck> trucks = TruckFileReader.readTrucksConfig("trucks.txt");
			ExecutorService executor = Executors.newFixedThreadPool(trucks.size());

			for (Truck truck : trucks) {
				executor.submit(truck);
			}

			executor.shutdown();
			executor.awaitTermination(1, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
