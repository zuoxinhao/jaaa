package work;

public class researchCon3 {
	
	//1. thread细节知识：
	//(1) 线程的启动start方法，相当于在主线程里面再开启一个线程，该线程和main方法同时运行，竞争CPU；如果直接调用线程的run方法，则是表示顺序执行，先执行线程中东西
	//后执行主线程剩下的内容。
	//(2) 线程的创建：首先是继承Thread类的，直接new Thread就可以start;另外两种是实现callable，runnable接口的，需要new Thread(new Runnable()).start
	//(3) 几个有意思的方法：t1中调用t2.join(),表示将t2插入t1运行过程,先执行完t2,再执行t1;yield()方法，表示把该线程放回等待队列，重新一起竞争CPU。
	//(4) new出来是就绪状态，blocked状态是等待锁，wait状态是等待wait()或者join(),sleep(),park()...
	
	//2. synchronized细节：
	//(1) 在普通方法里面，synchronized(this),就是锁这个类的对象；静态方法直接synchronized放在方法修饰符(public)之后，表示对T.class对类加锁
	//(2) *****一个synchronized方法和另一个不是synchronized的方法都start,那么这两个程序会交替执行；所谓的加锁指的仅仅是没有获取锁的该线程无法进入，不影响其他方法
	//(3) 可重入锁：两个方法(m1,m2)可以用同一把锁，然后在m1中可以调用m2（嵌套）直接拿到就执行，m2可以调用m1；父子类锁的是this，表示同一个锁。
	//(4) 异常没有处理，会直接释放锁，导致问题
	//(5) *****底层实现：synchronized(Object)先在Object的头部markword两个字段记录线程ID（偏向锁）,如果是该线程到来直接进入即可(比如细节2中的情况，如果syn的线程来了，则直接运行)；
	//然后如果有另一个线程来争用这把锁，则升级为自旋锁，自旋锁的意思是第二个线程不停的查询等待锁的释放，释放了他就能进入;
	//如果自旋10次后仍没有释放，升级为重量级锁，就是OS锁；执行时间短，数量少，用自旋锁；长，多用OS锁
	//(6) 锁对象：属性变化没问题，如果变成别的对象，那就出问题了，所以一般需要final对象来当锁。
	//(7) syn m1,syn m2,然后如果m1,m2同时start，这两个竞争同一把锁this,那么只能一个运行完再进行另一个。
	
	//3. volatile细节：
	//可见性和禁止重排序
	//(1) 线程之间变量副本变化容易不可见：即一个线程A中变量I副本变化了，不会立即反映到另一个线程B中变量I副本，造成同一变量两个副本值不一致。volatile就是将可见性实现
	//(2) 懒汉单例模式，双重检查，判断两次INSTANCE==NULL(为了防止进入锁两次，两次初始化)；
	//要加volatile,禁止new初始化时候的指令重排序问题(第一个线程还没将变量赋值，就被外面的一个线程使用了)
	//(3) *****new的指令：先申请内存初始化a=0,然后赋值a=8,最后指向内存；如果不用volatile，(2)中就会出现另一个线程直接使用了a=0,而不是a=8。
	//(4) 保证了可见性，但是不能保证原子性，就是光看见了，但是一旦多并发，还是会出现问题，导致+的不对。同样的1，在不同线程同时运行，都是变成2，结果就错了。
	//(5) *****volatile修饰引用类型，其内部属性不具备可见性
	
	//4. CAS深入理解：
	//Atomic类主要用的就是CAS
	//(1) Atomic类的最后源码来自于unsafe类的weakCompareAndSetObject方法,1.8中是compareAndSwapObject，这个方法是CPU原语，不可分割。不需要实际的锁，但是和锁效果一样
	//(2) CAS内容：cas(V,E,NEW)    if(V==E) V=NEW otherwise try again or fail,如果是期望值则更改为新值，不然就重试或者失败；CPU原语支持，相当于对这一整个操作加锁了，不可分开。
	//(3) ABA问题：CAS中期望值是1，然后有个线程，先1-2，后2-1，还是1，CAS照旧运行；解决办法：那就加个version版本号，CAS检查比较版本号即可。
	//(4) unsafe类用处了解：直接操作内存，分配内存allocateMemory,freeMemory,相当于C++中的指针
	//(5) 常用incrementAndGet(i) 相当于线程安全的i++,效果相当于加锁的i++,且效率更高
	//(6) 补充：秒杀系统，LongAdder效率一般比AtomicLong效率更高，LongAdder用的是分段锁CAS，然后sum一起。
	//(7) unsafe的底层汇编指令：cmpxchgl (省略e)
	
	//5. ReentrantLock可重入锁深入理解：
	//(1) ReentrantLock：可以替代synchronized(),但是需要手动lock,unlock,
	//(2) 一些不同的方法：tryLock(time, )在时间内尝试获取锁，不行的话再去其他处理；lockInterruptibly(),可以对interrupt()方法做出响应，让该线程不再等待锁，直接进入代码；
	//(3) *****ReentrantLock底层是CAS，不是sync;ReentrantLock有公平非公平两种锁
	//(4) lock底层实现锁：锁总线/锁缓存行
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
