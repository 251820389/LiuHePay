package com.liuhepay.cuppayment.util;
import com.liuhepay.cuppayment.factory.PriorityThreadFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


// 线程池的实现方式
public class ThreadPool
{
	private static final int	POOL_SIZE		= Runtime.getRuntime()
			.availableProcessors() * 3 + 2;
	private final static int	MAX_POOL_SIZE	= 8;										// 设置线程池的最大线程数
	private final static int	KEEP_ALIVE_TIME	= 4;										// 设置线程的存活时间
	private final Executor		mExecutor;
	private static ThreadPool	mThreadPool;

	private ThreadPool()
	{
		// 创建线程池工厂
		ThreadFactory factory = new PriorityThreadFactory("thread-pool",
				android.os.Process.THREAD_PRIORITY_BACKGROUND);
		// 创建工作队列
		BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
		mExecutor = new ThreadPoolExecutor(POOL_SIZE,
				MAX_POOL_SIZE,
				KEEP_ALIVE_TIME,
				TimeUnit.SECONDS,
				workQueue,
				factory);
	}

	public static ThreadPool instance()
	{
		if (mThreadPool == null)
		{
			synchronized (ThreadPool.class)
			{
				if (mThreadPool == null)
				{
					mThreadPool = new ThreadPool();
				}
			}
		}
		return mThreadPool;
	}

	// 在线程池中执行线程
	public void submit(Runnable command)
	{
		mExecutor.execute(command);
	}
}
