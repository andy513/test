package com.andy.common.jork_join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Test2 extends RecursiveTask<Long> {

	private static final long serialVersionUID = 1L;

	static final int SEQUENTIAL_THRESHOLD = 5000;

	int low;
	int high;
	int[] array;

	public Test2(int[] arr, int lo, int hi) {
		array = arr;
		low = lo;
		high = hi;
	}

	protected Long compute() {
		if (high - low <= SEQUENTIAL_THRESHOLD) {
			long sum = 0;
			for (int i = low; i < high; ++i)
				sum += array[i];
			return sum;
		} else {
			int mid = low + (high - low) / 2;
			Test2 left = new Test2(array, low, mid);
			Test2 right = new Test2(array, mid, high);
			left.fork();
			long rightAns = right.compute();
			long leftAns = left.join();
			return leftAns + rightAns;
		}
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int size = Integer.MAX_VALUE/15;
		int[] a = new int[size];
		long result = 0;
		for (int i = 0; i < size; i++) {
			a[i] = i;
			result += i;
		}
		System.out.println(result);
		ForkJoinTask<Long> fjt = new Test2(a, 0, size);
		ForkJoinPool fjpool = new ForkJoinPool();
		fjpool.execute(fjt);
		Long resultInt = fjt.get();
		System.out.println(resultInt);
	}
}
