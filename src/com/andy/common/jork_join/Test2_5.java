package com.andy.common.jork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class Test2_5 extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	
	private static final int THRESHOLD = 50;//·§Öµ
	private int start;
	private int end;
	
	public Test2_5(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		boolean can = (end-start) <= THRESHOLD;
		if(can){
			for(int i = start;i<=end;i++)
				sum += i;
		}else{
			int middle = (start+end)/2;
			Test2_5 left = new Test2_5(start, middle);
			Test2_5 right = new Test2_5(end, middle);
			left.fork();
			right.fork();
			sum = left.join()+right.join();
		}
		return sum;
	}
	
	public static void main(String[] args) throws Exception{
		ForkJoinPool forkJoin = new ForkJoinPool();
		Test2_5 task = new Test2_5(1, 100);
		Future<Integer> result = forkJoin.submit(task);
		System.out.println(result.get());
	}

}
