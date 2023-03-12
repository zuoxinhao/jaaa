package work;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.xml.soap.Node;

import java.lang.*;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class researchConcurrent {
	
	//看的时候把源码打开，报错也无所谓，因为一些变量没有引进去。
	
	
	//1. 多线程的实现
	//线程的创建：实现runnable,callable接口，runnable重写run函数，callable重写call函数
	//然后调用的话，统一的调用是ExecutorService.execute()/submit()方法，而我们的线程池，实际上是继承于ExecutorService的
	//一般不会直接用ExecutorService来启动线程，都是用线程池来启动
	//*****多线程：实例对象仅一个，通过execute来反复调用开启多线程
	
	//callable的实现线程以及线程池的使用：
	/*
	public class MyCallable implements Callable<String>{    //实现callable接口并重写call方法，要有返回值
		public String call() throws Exception{
			Thread.sleep(1000);
			return Thread.currentThread().getName();      
		}	
	}
	
	public static class CallableDemo{
		private static final int CORE_POOL_SIZE=5;
		private static final int MAX_POOL_SIZE=10;
		private static final int QUEUE_CAPACITY=100;
		private static final long KEEP_ALIVE_TIME=1L;
		
		public static void main(String[] args) {
			ThreadPoolExecutor executor=new ThreadPoolExecutor(CORE_POOL_SIZE,MAX_POOL_SIZE,KEEP_ALIVE_TIME,TimeUnit.SECONDS,new ArrayBlockingQueue<>(QUEUE_CAPACITY),new ThreadPoolExecutor.CallerRunsPolicy());
			List<Future<String>> futureList=new ArrayList<>();
			Callable<String> callable=new MyCallable();                   //*****创建一个线程类的实例对象，线程类的实例对象仅需要一个
			for(int i=0;i<10;i++) {                                       //*****对象仅一个，但是线程池需要反复execute开启多线程
				Future<String> future=executor.submit(callable);          //线程池反复调用线程对象，会创建多线程
				futureList.add(future);
			}
		    for(Future<String> fut:futureList) {
				try {
					System.out.print(new Date(+"::"+fut.get()));
				}catch(InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
			executor.shutdown();
		}
	}
	*/
	
	
	//2. ThreadPoolExecutor的实现细节及源码
	//总结：corepoolsize核心线程数，是线程池一开始的并发线程数量；线程从0个开始，逐渐放入线程池，达到core的时候，就放入等待队列;
	//workQueue是等待队列，core满了，开始放入队列中;如果队列也满了，那就去扩充线程池,将线程池的大小从core扩充到maximum个。
	//core-----workQueue-----maximum
	
	//线程池原理的源码，ThreadPoolExecutor的execute方法源码：
	/*
	private final AtomicInteger ctl=new AtomicInteger(ctlOf(RUNNING,0));
	
	private static int workerCountOf(int c) {
		return c&CAPACITY;
	}
	
	private final BlockingQueue<Runnable> workQueue;
	
	public void execute(Runnable command) {
		if(command==null) {
			throw new NullPointerException();
		}
		int c=ctl.get();
		//第一步：数量小于core那就直接加入线程池
		if(workerCountOf(c)<corePoolSize) {
			if(addWorker(command,true)) {
				return;
			}
			c=ctl.get();
		}
		//第二步：等待队列没满，那就直接加入等待队列
		if(isRunning(c)&&workQueue.offer(command)) {
			int recheck=ctl.get();
			if(!isRunning(recheck)&&remove(command)) {
				reject(command);
			}else if(workCountOf(recheck)==0) {
				addWorker(null,false);
			}
		}
		//第三步：扩充完后，还不能放入，那就reject掉，可以放入那就直接放入maximum里面
		else if(!addWork(command,false)) {
			reject(command);
		}
	}
	*/
	
	
	//3. AQS公平锁非公平锁的源码
	//重点源码：
	
	//公平锁，先到先得，所以需要判断队列里是否存在祖先再去获取锁
	/*public final void acquire(int arg) {
		if(!hasQueuedPredecessors()&&compareAndSetState(0,acquires)) {
			setExclusiveOwnerThread(current);            //拿到锁
		}
	}*/
	
	//非公平锁：两次CAS抢占，一次是lock()调用后，另一次是acquire()
	/*final void lock() {
		if(compareAndSetState(0,1)) {
			setExclusiveOwnerThread(Thread.currentThread());
		}
	}
	
	final boolean nonfairTryAcquire(int acquires) {
		if(compareAndSetState(0,1)) {
			setExclusiveOwnerThread(Thread.currentThread());
		}
	}*/
	
	//4. countDownLatch的使用
	//countDownLatch就是设置一个count,每次执行countDown()函数count值会减1，减到0会唤醒await()
	//一直不到0那就在await处一直阻塞，程序没办法继续进行
	//至于cyclicBarrier基本一样的用法，但是它是自动减1（test里面放await,每次执行到await则减1），然后每count个执行await,不断循环的
	//cyclicBarrier可以用于复杂操作，比如三种操作都做完了才能去传数据等等
	public class CountDownLatchExample{
		private static final int threadCount=550;    //设置count数目
		
		public void main(String[] args) throws InterruptedException {
			ExecutorService threadPool=Executors.newFixedThreadPool(300);
			final CountDownLatch countDownLatch=new CountDownLatch(threadCount);
			for(int i=0;i<threadCount;i++) {
				final int threadnum=i;
				threadPool.execute(()->{
					try {
						test(threadnum);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}finally {
						countDownLatch.countDown();         //每次countDown则count减1
					}
				});
			}
			countDownLatch.await();
			threadPool.shutdown();
			System.out.println("finish");
		}
		
		public void test(int threadnum)throws InterruptedException {
			System.out.println("1");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
