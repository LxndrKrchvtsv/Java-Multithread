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
			LOGGER.info("Try run thread {}", Thread.currentThread().getName());
			for (String message : messages) {
				TimeUnit.SECONDS.sleep(1);
				LOGGER.info("{} - message received for {} Thread", message, Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			LOGGER.error("{} Thread Talk thread interrupted", Thread.currentThread().getName(), e);
			Thread.currentThread().interrupt();
		}
	}
}
