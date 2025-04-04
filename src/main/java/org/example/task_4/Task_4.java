package org.example.task_4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task_4 {
	private static final Logger LOGGER = LogManager.getLogger(org.example.task_4.Task_4.class.getName());

	public static void main(String[] args) throws InterruptedException {
		LOGGER.info("Starting task 4");
		int M = 1000;
		int K = 2000;

		Producer producer = new Producer(M);
		Consumer consumer = new Consumer(producer, K, M);

		producer.start();
		consumer.start();

		consumer.join();
		producer.setIsRun(false);

		LOGGER.info("THREAD STATUS: " + producer.isInterrupted());
		LOGGER.info("THREAD STATE: " + producer.getState());

		// Interrupted Exception
		producer.interrupt();
	}
}
