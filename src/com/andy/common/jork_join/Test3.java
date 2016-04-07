package com.andy.common.jork_join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Test3 extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	final int n;

	Test3(int n) {
		this.n = n;
	}

	private int compute(int small) {
		final int[] results = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 };
		int result = results[small];
		System.out.println(result);
		return result;
	}

	public Integer compute() {
		if (n <= 10) {
			return compute(n);
		}
		Test3 f1 = new Test3(n - 1);
		Test3 f2 = new Test3(n - 2);
		f1.fork();
		return f2.compute() + f1.join();
	}

	public static void main(String... args) throws InterruptedException, ExecutionException {
		ForkJoinTask<Integer> fjt = new Test3(10);
		ForkJoinPool fjpool = new ForkJoinPool();
		fjpool.execute(fjt);
		Integer resultInt = fjt.get();
		System.out.println(resultInt);
	}

}
