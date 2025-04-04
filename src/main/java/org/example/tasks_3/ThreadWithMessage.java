package org.example.tasks_3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class ThreadWithMessage extends Thread {
	private static final Logger LOGGER = LogManager.getLogger(org.example.tasks_3.ThreadWithMessage.class.getName());
	private final String[] messages;

	public ThreadWithMessage(String[] messages) {
		this.messages = messages;
	}

	public void run() {
		try {
			LOGGER.info("Try run thread " + Thread.currentThread().getName());
			for (String message : messages) {
				TimeUnit.SECONDS.sleep(1);
				LOGGER.info(message + " - message recieved for " + Thread.currentThread().getName() + " Thread");
			}
		} catch (InterruptedException e) {
			LOGGER.error(Thread.currentThread().getName() + " Thread Talk thread interrupted", e);
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
	}
}
