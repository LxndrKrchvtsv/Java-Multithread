package org.example.app_logistics_base.logistic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.app_logistics_base.truck.Truck;

import java.io.IOException;

public class LogisticBaseManager {
	private static final Logger LOGGER = LogManager.getLogger(LogisticBaseManager.class.getName());

	private static class Holder {
		static final LogisticBaseManager INSTANCE;

		static {
			try {
				LOGGER.info("Initializing LogisticBaseManager");
				INSTANCE = new LogisticBaseManager();
			} catch (IOException e) {
				LOGGER.error("Failed to initialize LogisticBaseManager: ", e);
				throw new RuntimeException(e);
			}
		}
	}

	private final LogisticBase logisticBase;
	private final int numbersOfTerminalsOnBase = LogisticFileReader.readLogisticConfig("logistic_base.txt");

	private LogisticBaseManager() throws IOException {
		logisticBase = new LogisticBase(numbersOfTerminalsOnBase);
	}

	public static LogisticBaseManager getInstance() {
		return Holder.INSTANCE;
	}

	public void processTruck(Truck truck) throws InterruptedException {
		logisticBase.processTruck(truck);
	}
}
