package org.example.task_4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Consumer extends Thread {
	private static final Logger LOGGER = LogManager.getLogger(org.example.task_4.Consumer.class.getName());

	Producer producer;
	public int K;
	public int M;

	public Consumer(Producer producer, int K, int M) {
		this.producer = producer;
		this.K = K;
		this.M = M;
	}

	@Override
	public void run() {
		LOGGER.info("Consumer thread started");
		try {
			while (K > 0) {
				LOGGER.info("Consumer thread going to sleep");
				while (!producer.getFlag()) {
					LOGGER.info("Consumer thread give control to Producer: " + !producer.getFlag());
					Thread.yield();
				}

				K -= M / 10;
				LOGGER.info("Timer: " + K);
				Thread.sleep(M / 10);
			}
		} catch (InterruptedException e) {
			LOGGER.error(Thread.currentThread().getName() + " Thread interrupted", e);
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
	}
}
