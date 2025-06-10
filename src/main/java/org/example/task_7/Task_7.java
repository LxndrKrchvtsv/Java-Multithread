package org.example.task_7;

public class Task_7 {
	public static void main(String[] args) {
		Thread yieldThread = new Thread(new YieldThread());
		Thread talkThread = new Thread(new TalkThread());

		yieldThread.start();
		talkThread.start();
	}
}
