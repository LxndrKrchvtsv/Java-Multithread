package org.example.task_4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Producer extends Thread {
	private static final Logger LOGGER = LogManager.getLogger(org.example.task_4.Producer.class.getName());

	private final int M;
	private Boolean flag = false;
	private Boolean isRun = true;

	public Producer(int M) {
		this.M = M;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setIsRun(Boolean isRun) {
		this.isRun = isRun;
	}

	@Override
	public void run() {
		LOGGER.info("Producer thread started");
		try {
			while (isRun) {
				LOGGER.info("Producer thread try to sleep for M");
				Thread.sleep(M);

				flag = !flag;

				Thread.yield();
			}
		} catch (InterruptedException e) {
			LOGGER.error("{} Thread interrupted", Thread.currentThread().getName(), e);
			Thread.currentThread().interrupt();
		}
	}
}
