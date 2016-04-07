package com.andy.test;

import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;

public class Main {
	// public static final List<Integer> lists = new ArrayList<Integer>();
	public static final ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
	public static final ConcurrentLinkedQueue<Integer> lists = new ConcurrentLinkedQueue<Integer>();
	public static final HashSet<Integer> sets = new HashSet<Integer>();

	private static final ThreadPoolExecutor n_es = new ThreadPoolExecutor(5, 1000, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5000));

	public static void main(String[] args) throws Exception {
		int num = 5000;
		for (int i = 0; i < num; i++) {
			queue.add(i);
		}
		int time = 2;
		int size = 50;
		for (int j = 1; j <= time; j++) {
			CountDownLatch countDownLatch = new CountDownLatch(size);
			for (int k = 0; k < size; k++) {
				new Thread(new Andy(countDownLatch)).start();
			}
			countDownLatch.await();
		}
		extracted();
	}

	private static void extracted() throws InterruptedException {
		for (int i = 5; i >= 1; i--) {
			System.out.println(i);
			Thread.sleep(1000);
		}
		System.out.println(sets.size());
		System.out.println(JSONObject.toJSONString(sets));
		System.out.println(sets.size());
		for (int i = 0; i < sets.size(); i++) {
			if (!sets.contains(i)) {
				System.out.print(i + "\t");
			}
			/*
			 * if (i != lists.poll()) { System.out.print(i + "\t"); }
			 */
		}
		System.out.println(sets.size());
		// lists.clear();
	}
}

class Andy implements Runnable {
	final CountDownLatch countDownLatch;

	public Andy(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		while (true) {
			Integer i = Main.queue.poll();
			countDownLatch.countDown();
			while (i != null) {
				synchronized (Main.sets) {
					if (Main.sets.contains(i)) {
						System.out.println("有重复数据");
					} else {
						Main.sets.add(i);
					}
				}
				i = Main.queue.poll();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
