package com.raines.raineslearn.async.chapter3.FutureTask;

import java.util.concurrent.*;

/**
 * 使用线程池submit执行FutureTask并获取执行结果
 */
public class AsyncFutureExample3 {

	public static String doSomethingA() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--- doSomethingA---");

		return "TaskAResult";
	}

	public static String doSomethingB() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--- doSomethingB---");
		return "TaskBResult";

	}

	// 0自定义线程池
	private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
	private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
			AVALIABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5),
			new ThreadPoolExecutor.CallerRunsPolicy());

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		long start = System.currentTimeMillis();

		// 1.开启异步单元执行任务A
		//调用了线程池的submit方法提交了一个任务到线程池，然后返回了一个futureTask对象
		Future<String> futureTask = POOL_EXECUTOR.submit(() -> {
			String result = null;
			try {
				result = doSomethingA();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		});

		// 2.执行任务B
		String taskBResult = doSomethingB();

		// 3.同步等待线程A运行结束
		String taskAResult = futureTask.get();
		// 4.打印两个任务执行结果
		System.out.println(taskAResult + " " + taskBResult);
		System.out.println(System.currentTimeMillis() - start);
	}
}
