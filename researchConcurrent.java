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
	
	//����ʱ���Դ��򿪣�����Ҳ����ν����ΪһЩ����û������ȥ��
	
	
	//1. ���̵߳�ʵ��
	//�̵߳Ĵ�����ʵ��runnable,callable�ӿڣ�runnable��дrun������callable��дcall����
	//Ȼ����õĻ���ͳһ�ĵ�����ExecutorService.execute()/submit()�����������ǵ��̳߳أ�ʵ�����Ǽ̳���ExecutorService��
	//һ�㲻��ֱ����ExecutorService�������̣߳��������̳߳�������
	//*****���̣߳�ʵ�������һ����ͨ��execute���������ÿ������߳�
	
	//callable��ʵ���߳��Լ��̳߳ص�ʹ�ã�
	/*
	public class MyCallable implements Callable<String>{    //ʵ��callable�ӿڲ���дcall������Ҫ�з���ֵ
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
			Callable<String> callable=new MyCallable();                   //*****����һ���߳����ʵ�������߳����ʵ���������Ҫһ��
			for(int i=0;i<10;i++) {                                       //*****�����һ���������̳߳���Ҫ����execute�������߳�
				Future<String> future=executor.submit(callable);          //�̳߳ط��������̶߳��󣬻ᴴ�����߳�
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
	
	
	//2. ThreadPoolExecutor��ʵ��ϸ�ڼ�Դ��
	//�ܽ᣺corepoolsize�����߳��������̳߳�һ��ʼ�Ĳ����߳��������̴߳�0����ʼ���𽥷����̳߳أ��ﵽcore��ʱ�򣬾ͷ���ȴ�����;
	//workQueue�ǵȴ����У�core���ˣ���ʼ���������;�������Ҳ���ˣ��Ǿ�ȥ�����̳߳�,���̳߳صĴ�С��core���䵽maximum����
	//core-----workQueue-----maximum
	
	//�̳߳�ԭ���Դ�룬ThreadPoolExecutor��execute����Դ�룺
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
		//��һ��������С��core�Ǿ�ֱ�Ӽ����̳߳�
		if(workerCountOf(c)<corePoolSize) {
			if(addWorker(command,true)) {
				return;
			}
			c=ctl.get();
		}
		//�ڶ������ȴ�����û�����Ǿ�ֱ�Ӽ���ȴ�����
		if(isRunning(c)&&workQueue.offer(command)) {
			int recheck=ctl.get();
			if(!isRunning(recheck)&&remove(command)) {
				reject(command);
			}else if(workCountOf(recheck)==0) {
				addWorker(null,false);
			}
		}
		//��������������󣬻����ܷ��룬�Ǿ�reject�������Է����Ǿ�ֱ�ӷ���maximum����
		else if(!addWork(command,false)) {
			reject(command);
		}
	}
	*/
	
	
	//3. AQS��ƽ���ǹ�ƽ����Դ��
	//�ص�Դ�룺
	
	//��ƽ�����ȵ��ȵã�������Ҫ�ж϶������Ƿ����������ȥ��ȡ��
	/*public final void acquire(int arg) {
		if(!hasQueuedPredecessors()&&compareAndSetState(0,acquires)) {
			setExclusiveOwnerThread(current);            //�õ���
		}
	}*/
	
	//�ǹ�ƽ��������CAS��ռ��һ����lock()���ú���һ����acquire()
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
	
	//4. countDownLatch��ʹ��
	//countDownLatch��������һ��count,ÿ��ִ��countDown()����countֵ���1������0�ỽ��await()
	//һֱ����0�Ǿ���await��һֱ����������û�취��������
	//����cyclicBarrier����һ�����÷������������Զ���1��test�����await,ÿ��ִ�е�await���1����Ȼ��ÿcount��ִ��await,����ѭ����
	//cyclicBarrier�������ڸ��Ӳ������������ֲ����������˲���ȥ�����ݵȵ�
	public class CountDownLatchExample{
		private static final int threadCount=550;    //����count��Ŀ
		
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
						countDownLatch.countDown();         //ÿ��countDown��count��1
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
