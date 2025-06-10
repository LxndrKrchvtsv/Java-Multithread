package org.example.task_6;

public class Task_6 {
	public static void main(String[] args) {
		Thread walkMin = new Thread(new WalkThread(), "Min");
		Thread talkMax = new Thread(new TalkThread(), "Max");

		walkMin.setPriority(Thread.MIN_PRIORITY);
		talkMax.setPriority(Thread.MAX_PRIORITY);

		talkMax.start();
		walkMin.start();
	}
}
