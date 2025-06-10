package org.example.tasks_1_2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class TalkThread extends Thread{
	private static final Logger LOGGER = LogManager.getLogger(TalkThread.class.getName());
	public TalkThread() {
	}

	@Override
	public void run() {
		try {
			LOGGER.info("Talk thread started");
			for (int i = 0; i < 10; i++) {
				LOGGER.info("Child {} {}", Thread.currentThread().getName(), i);
				TimeUnit.MILLISECONDS.sleep(500);			}
		} catch (InterruptedException e) {
			LOGGER.error("Child Talk thread interrupted", e);
			Thread.currentThread().interrupt();
		}
	}
}
