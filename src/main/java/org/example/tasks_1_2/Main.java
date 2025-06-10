package org.example.tasks_1_2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Main {
	private static final Logger LOGGER = LogManager.getLogger(TalkThread.class.getName());

	public static void main(String[] args) throws InterruptedException {
		TalkThread childT = new TalkThread();

		childT.start();
		try {
			childT.join();
		} catch (InterruptedException e) {
			LOGGER.error("Child Talk thread interrupted while is parent Talk thread waiting", e);
		}

		for (int i = 0; i < 10; i++) {
			try {
				LOGGER.info("Parent Thread Talk: {} - {}", i, Thread.currentThread().getName());
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				LOGGER.error("Parent Talk thread interrupted", e);
				Thread.currentThread().interrupt();
			}
		}
	}
}
