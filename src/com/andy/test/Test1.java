package com.andy.test;

import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test1 {

	private static final ThreadPoolExecutor n_es = new ThreadPoolExecutor(100, 1000, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000));
	private static final ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
	private static final BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();

	public static void main(String[] args) throws InterruptedException {
		int num = 100000;
		a(num);
		b(num);
		n_es.shutdownNow();
	}

	private static void a(int num) throws InterruptedException {
		for (int i = 0; i < num; i++) {
			map.put(i, i);
		}
		CountDownLatch countDownLatch = new CountDownLatch(map.size());
		new Thread(() -> {
			for (Entry<Integer, Integer> entry : map.entrySet()) {
				n_es.execute(() -> {
					// System.out.println(entry.getKey());
					countDownLatch.countDown();
					queue.add(entry.getKey());
				});
			}
		}).start();
		countDownLatch.await();
		System.out.println(queue.size());
	}

	private static void b(int num) throws InterruptedException {
		/*for (int i = 0; i < num; i++) {
			queue.add(i);
		}*/
		CountDownLatch countDownLatch = new CountDownLatch(queue.size());
		new Thread(() -> {
			while (true) {
				Integer i = queue.poll();
				while (i != null) {
					countDownLatch.countDown();
					int a = i;
					n_es.execute(() -> {
						map.put(a, a);
					});
					i = queue.poll();
				}
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		countDownLatch.await();
		System.out.println(map.size());
	}

}
