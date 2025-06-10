package org.example.app_logistics_base.truck;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.app_logistics_base.logistic.LogisticBaseManager;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Truck implements Callable<Void>, Comparable<Truck> {
	private static final Logger LOGGER = LogManager.getLogger(Truck.class.getName());

	private final int id;
	private final long processingTime;
	private final boolean perishable;
	private final TruckOperationType operation;

	public Truck(int id, boolean perishable, TruckOperationType operation, long processingTime) {
		this.id = id;
		this.processingTime = processingTime;
		this.perishable = perishable;
		this.operation = operation;
	}

	public boolean isPerishable() {
		return perishable;
	}

	public void process() throws InterruptedException {
		try {
			LOGGER.info("Starting processing: {} of {}, Perishable: {}, Operation: {}", operation, id, perishable, operation);

			TimeUnit.SECONDS.sleep(processingTime);

			LOGGER.info("Finishing processing: {} of {}, Perishable: {}, Operation: {}", operation, id, perishable, operation);
		} catch (InterruptedException e) {
			LOGGER.info("Truck processing interrupted {}: ", e);
			throw new InterruptedException();
		}

	}

	@Override
	public Void call() throws InterruptedException {
		try {
			LogisticBaseManager.getInstance().processTruck(this);
		} catch (InterruptedException e) {
			LOGGER.error("Call method: Processing Truck was interrupted", e);
			throw new InterruptedException();
		}

		return null;
	}

	@Override
	public int compareTo(Truck other) {
		if (this.perishable && !other.isPerishable()) return -1;
		if (!this.perishable && other.isPerishable()) return 1;

		return 0;
	}
}
