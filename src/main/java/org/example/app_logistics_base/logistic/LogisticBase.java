package org.example.app_logistics_base.logistic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.app_logistics_base.truck.Truck;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase {
	private static final Logger LOGGER = LogManager.getLogger(LogisticBase.class.getName());

	private int terminalsNumber;
	private final Lock lock = new ReentrantLock();
	private final Condition terminalAvailable = lock.newCondition();
	private final PriorityQueue<Truck> trucksQueue = new PriorityQueue<>();

	public LogisticBase(int numbersOfTerminalsOnBase) {
		this.terminalsNumber = numbersOfTerminalsOnBase;
	}

	public void processTruck(Truck truck) throws InterruptedException {
		lock.lock();
		try {
			LOGGER.info("Adding truck to the Priority Queue: {}", truck);
			trucksQueue.add(truck);

			while (terminalsNumber == 0 || trucksQueue.peek() != truck) {
				LOGGER.info("Waiting for terminal: {}", truck);
				terminalAvailable.await();
			}

			LOGGER.info("Removing truck from the Priority Queue: {}", truck);
			LOGGER.info("Taking a terminal...");

			terminalsNumber--;
			trucksQueue.remove(truck);
		} catch (InterruptedException e) {
			LOGGER.error("Processing queue interrupted", e);
			throw new InterruptedException();
		} finally {
			LOGGER.info("Releasing lock after entering terminal: {}", truck);
			lock.unlock();
		}

		try {
			LOGGER.info("Running operation processing truck: {}", truck);
			truck.process();
		} finally {
			lock.lock();
			try {
				LOGGER.info("Processing truck finished: {}", truck);
				LOGGER.info("Releasing terminal...");

				terminalsNumber++;
				terminalAvailable.signalAll();
			} catch (Exception e) {
				LOGGER.error("Releasing terminal interrupted", e);
				throw new InterruptedException();
			} finally {
				LOGGER.info("Releasing lock after processing truck: {}", truck);
				lock.unlock();
			}
		}
	}
}
