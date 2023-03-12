package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

public class researchCon2 {
	
	//1. 屏障：禁止重排序
	//内存屏障：CPU级别指令，lfence之类
	//JVM屏障：volatile：JVM层面实现指令：LoadLoadBarrier读读之间加屏障，StoreLoadBarrier写读之间加屏障，不能换顺序......
	//所以对于volatile修饰的变量，内存，会在其前后加入很多的JVM屏障指令，保证其不能指令重排。
	//HotSpot里面对于volatile的源码实现是用Barrier来屏障，然后HotSpot自身的写起来的源码（C++写的HotSpot）是用Lock(汇编指令，物理层面的总线锁)来实现
	
	
	//2. 哲学家死锁问题：
	//sychronized左右筷子问题，两把锁，每个人都优先右锁导致死锁了。
	//*****左撇子算法：非常巧妙：
	//代码层面的解决办法：
	//第一种是对整个筷子加锁，只要有一个人来了，就直接把整个锁住，等他吃完再释放；不好，一次只能一个人且锁住全部了
	//第二种是错位加锁法：指定一个人（index0）先拿左锁再拿右锁，其他人(index12345...)先拿右锁再拿左锁，这样子会出现一个人(index1/index0)可以拿到左右两个锁然后释放，这样就不会死锁。
	//第三种效率更高的：奇数左撇子，偶数右撇子，这样子可以有多个同时进餐，效率更高了。
	
	//3. 交替执行
	//两线程交替输出123456，abcdef:  1a2b3c4d5e6f
	//*****第一种方法：LockSupport：locksuport可以先唤醒unpark另一个线程，然后把自己park阻塞，等待另一个来唤醒，这样子两个线程就能交替唤醒阻塞。
	//第二种方法：两个线程共用synchronized锁，然后一线程先执行打印再去释放锁（notify）叫醒另一个线程，阻塞(wait)自己；另一个也是先打印，再释放锁，阻塞自己。
	//*****第三种方法：lock的condition队列，不同类别的线程放入不同队列，交替唤醒
	//LockSupport相当于是公用的锁，就不用再去synchronized释放阻塞之类。
	//*****notify()只能叫醒所有等待线程中的一个，没办法指定某一个特定线程，但是locksupport可以做到，lock也可以，lock有condition队列，可以将不同线程放入不同队列来唤醒
	
	
	//4. 分布式事务
	//如果三个线程完成一个火锅任务，一个买菜，一个买肉，一个烧水，如果其中之一操作失败，则需要集体停止，并回滚到原状态：
	//这就是分布式事务处理：
	//核心思想：*****需要一个leader来统筹处理分布式事务，如果有人出错，那就先通知leader,然后leader告诉所有人停止，然后回滚事务。
	//用到的首先需要一个父类leader统筹所有事务，然后标志位标记失败还是成功，然后三个子类需要有回滚事务的函数重写。
	//*****completablefuture专门用于处理分布式事务，也就是completablefuture可以异步开启多个任务，然后相互通知，然后再去根据通知来处理每个任务。
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
