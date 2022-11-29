package com.epam.rd.autotasks;

import java.util.*;
import java.util.logging.Logger;


public class UnionFactory implements ThreadUnion {
	
	private static final String WORKER = "-worker-";
	private static final Logger logger = Logger.getLogger(UnionFactory.class.getName());
	private final List<FinishedThreadResult> resultsList = Collections.synchronizedList(new ArrayList<>());
	private final List<Thread> threadList = Collections.synchronizedList(new ArrayList<>());
	private final String name;
	private int totalSize;
	private boolean isFactoryShutDown = false;
	
	
	public UnionFactory(String name) {
		this.name = name;
		this.totalSize = 0;
	}
	
	@Override
	public int totalSize() {
		return totalSize;
	}
	
	@Override
	public int activeSize() {
		return (int) threadList.stream().filter(Thread::isAlive).count();
	}
	
	@Override
	public void shutdown() {
		for (Thread t : threadList) {
			t.interrupt();
		}
		isFactoryShutDown = true;
	}
	
	@Override
	public boolean isShutdown() {
		return isFactoryShutDown;
	}
	
	@Override
	public void awaitTermination() {
		for (Thread t : threadList) {
			try {
				t.join();
			} catch (InterruptedException e) {
				logger.severe(e.getMessage());
				Thread.currentThread().interrupt();
			}
		}
	}
	
	@Override
	public boolean isFinished() {
		return isFactoryShutDown && activeSize() == 0;
	}
	
	
	@Override
	public List<FinishedThreadResult> results() {
		return Collections.unmodifiableList(resultsList);
	}
	
	@Override
	public Thread newThread(Runnable runInit) {
		Thread tempThread;
		
		if (isFactoryShutDown) {
			throw new IllegalStateException();
		} else {
			tempThread = createThread(runInit);
			tempThread.setUncaughtExceptionHandler((t, e) -> {
				logger.severe(e.getMessage());
				resultsList.add(new FinishedThreadResult(t.getName(), e));
			});
			threadList.add(tempThread);
		}
		
		return tempThread;
	}
	
	private synchronized Thread createThread(Runnable r) {
		
		return new Thread(r, this.name + WORKER + totalSize++) {
			@Override
			public void run() {
				super.run();
				resultsList.add(new FinishedThreadResult(this.getName()));
			}
		};
	}
}
