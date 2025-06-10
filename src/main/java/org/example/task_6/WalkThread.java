package org.example.task_6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WalkThread extends Thread {
	private static final Logger LOGGER = LogManager.getLogger(org.example.task_6.WalkThread.class.getName());

	public void run() {
		int counter = 0;

		try {
			long startTime = System.currentTimeMillis();

			while (System.currentTimeMillis() - startTime < 1){
				counter++;
			}
		} finally {
			LOGGER.info("WALK COUNTER {}", counter);
		}
	}
}