package work;

public class researchTomcat {
	
	
	//1. Tomcat架构：
	//(1) NIO: 打开ServerSocketChannel里面的端口，然后accept客户端连接SocketChannel，把请求放入线程池处理。每个线程处理一个请求
	//每个线程处理请求时候，如果有IO操作(读写操作),线程会和操作系统请求，读数据时候没有数据，写数据不可写时候，BIO就会阻塞住，无法继续进行
	//设置非阻塞NIO：Tomcat里面设置acceptSocketChannel.configureBlocking(false)，操作系统会帮忙设置标志位，表示是否完成，不行就返回标志
	//(2) *****非阻塞模式NIO：线程-----选择器-----操作系统
	//线程通过注册选择器，指明自己是写事件还是读事件；然后选择器去查询是否有准备好事件的通道selector.select(100)，接收操作系统返还的标志位，并回给线程池
	//(3) 连接，IO,业务分离：selector单线程(大部分情况下是2线程)做专门的IO处理判断，线程池用于业务处理，单线程接收连接
	
	//2. AIO：
	//(1) 异步IO：异步核心：多了CallBack回调，直接分发给不同的handlers,每个handler里面有回调函数，这样就可以异步执行，每个都能回调
	//事件来了，先去线程池，然后有个线程专门去selector询问，后面会扫描到对应的SocketChannel，然后回调执行线程池中线程
	
	//3. 分布式事务详解：
	//(1) 多个服务，多个服务器，多个数据库的场景：比如支付服务和订单服务，不在同一服务器上，所以这里就需要保持分布式数据一致性。一个请求调用了多个服务或者多个库，都是分布式事务
	//(2) 数据不一致的原因：库存服务-1成功，但是订单服务响应订单insert order但是失败了，导致订单没有增加，但是库存-1了，所以数据不一致
	//(3) 解决办法：彩排演练：先来次预演，预演中库存服务和订单服务有一个出错了，那就给中间件(收集成功失败信息)发送信息，通知两方均不执行；如果都对了，中间件给他们发消息，两方均执行
	//即：引入协调者，收集演练信息，然后发送通知让双方一起进行或者一起不进行
	//(4) 两阶段提交实现：第一阶段投票(准备)阶段，协调者向每个服务发送询问是否准备好提交，并接收服务发送来的答案，服务要先执行事务(BEGAIN TRANCSACTION,执行事务)，预备提交；第二阶段提交阶段，如果第一阶段服务均同意提交，
	//那么这个阶段就会正式提交，commit，并释放资源；如果第一阶段有服务失败了，那就一起回滚事务
	//*****核心：第一阶段是演习询问,探口风，第二阶段正式通知和提交
	//两阶段提交问题：协调者单点故障，阻塞资源(*****第一阶段执行事务一直拿着资源不动，等待第二阶段的提交还是回滚选项)；数据不一致(第二阶段出错，让commit但是信息丢失了)
	//(5) 中间件实现：阿里的seata，用注解@GlobalTransaction，表示全局事务，解决分布式事务
	//seata解决了资源阻塞的问题：直接释放资源，每个服务依赖于一个undolog表专门用于回滚的记录，所以性能很高
	//三阶段提交协议：can commit,pre commit,do commit,相当于在两阶段之前加了一个can阶段，就是服务衡量下能不能执行，如果不能就不要执行了，不会pre时候浪费资源
	//(6) 三阶段新加入的机制：超时机制，如果第二阶段协调者等待回复了很久，那就直接abort commit;第三阶段参与者等不到协调者的消息，那就直接提交
	//(7) TCC:Try(对应第一阶段，询问，尝试)   Confirm Cancel(第二阶段，提交/回滚)
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
