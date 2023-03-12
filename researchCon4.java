package work;

public class researchCon4 {
	
	
	//1. Phaser分阶段的线程：
	//(1) onAdvance()：当所有线程都到达某个栅栏了就运行该方法；arriveAndAwaitAdvance():让线程等待所有其他线程一起到达栅栏；arriveAndDeregister():取消其他线程
	//仅保留本次线程去往下一个栅栏。
	
	//2. ReadWriteLock读写锁：
	//ReentrantReadWriteLock.readLock读锁:共享锁,同时进行，执行很快；ReentrantReadWrtieLock.writeLock写锁：排他锁，必须挨个等待，执行较慢
	//多个共享锁可以共存，但是排他锁不行，只能一个
	
	//3. Semaphore的使用：
	//首先new指定信号量，然后调用acquire()阻塞方法，信号量-1，release()唤醒，信号量+1。信号量只有大于0才能获取并执行
	//semaphore表示的是同时能有几个线程一起执行；有公平非公平信号量之分
	
	//4. Exchanger的使用：
	//exchange(s)方法:交换两个线程中的s变量的值
	
	//5. LockSupport的使用：
	//park(),unpark()方法，Con2中学过
	//*****且unpark可以在park之前使用，表示提前放行，线程中park就没有用了。
	
	//6. notify注意点：
	//notify唤醒，但是不释放锁，wait才会阻塞并让出锁给另一个线程，所以很容易导致出问题；
	//所以一般我们用LockSupport来实现线程间的通信，同步等等。
	
	
    //*****7. 阅读源码的方法：
	//代码跑起来读，然后才能设置断点，逐渐向下点进去
	//代码主要就是数据结构和设计模式
	//一般不读静态方法
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
