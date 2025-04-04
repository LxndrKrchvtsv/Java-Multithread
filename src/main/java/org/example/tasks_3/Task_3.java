package org.example.tasks_3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task_3 {
	private static final Logger LOGGER = LogManager.getLogger(org.example.tasks_3.Task_3.class.getName());
	public static void main(String[] args) throws InterruptedException {
		try {
			LOGGER.info("Starting task 3");
			for (int i = 0; i < 10; i++) {
				String[] messages = {
						"Thread " + i + " Message 1",
						"Thread " + i + " Message 2",
						"Thread " + i + " Message 3",
				};

				ThreadWithMessage threadWithMessage = new ThreadWithMessage(messages);
				threadWithMessage.start();
				threadWithMessage.join();
			}
		} catch (InterruptedException e) {
			LOGGER.error(Thread.currentThread().getName() + " Thread interrupted", e);
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
	}
}
