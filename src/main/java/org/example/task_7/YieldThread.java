package org.example.task_7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class YieldThread extends Thread {
	private static final Logger LOGGER = LogManager.getLogger(org.example.task_7.YieldThread.class.getName());

	@Override
	public void run() {
		int counter = 0;

		try {
			long startTime = System.currentTimeMillis();

			while (System.currentTimeMillis() - startTime < 1){
				counter++;
				Thread.yield();
			}
		} finally {
			LOGGER.info("TALK COUNTER {}", counter);
		}
	}
}
