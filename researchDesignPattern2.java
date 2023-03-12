package work;

public class researchDesignPattern2 {
		
		
	//1. 责任链模式ChainOfResponsibility模式详解:
	//(1) 例如：message-----filter1-----filter2-----filter3-----DB后台
	//信息经过过滤链挨个处理最后处理完放到数据库中(链-----arraylist)
	//(2) 定义一个Filter接口，然后每个过滤器实现Filter,然后用一个chainFilter类(也实现Filter)中放置arraylist用于加入各个filter，并执行doFilter调用各个方法。
	//(3) 链上如果有一个出故障，就不再执行---添加返回值即可断链
	//(4) Java中servlet类中的FilterChain，需要先处理request在处理response,且request是1.2，response是2.1顺序相反
	//*****非常厉害的实现：第三个参数FilterChain(在FilterChain加入位置参数，表示执行到第几个Filter),在每个Filter的实现类中先处理Request，然后调用FilterChain(总的所有的Filter)的doFilter，这个方法会判断并自动执行当前Filter
	//的下一个Filter的处理函数；然后下一个Filter里面也是先处理Request,然后传递到下一个...直到最后没有后一个了，那就执行上一个的Response处理，然后再传递上一个...
	//核心思想：通过加入总的ChainFilter实现了Request下一个的传递，Response上一个的传递，这样就会延续链的顺序性;*****递归写法，把对response处理放到doFilter的递归调用之后即可
	
	
	//2. 观察者Observer模式详解：
	//(1) 观察者需要等待被观察者的状态来实现监控触发，所以直接将观察者类写到被观察者之中(在源类中加入监听者类对象)，而不是用while()来等待
	//同时对于触发时候的一些状态，我们直接封装成了event类，给observer传参使用，source是源对象是被观察者
	//(2) event里面会有个getSource来获取源本身，需要时候取出来使用
	
	//3. composite组合模式详解：
	//树状结构专用模式，相当于文件的树状逻辑结构存储，分为叶子节点(文件)，非叶子节点(目录)
	
	//4. Flyweight享元模式详解：
	//享元：共享元数据：对于一些小对象，经常使用的，没必要创建很多个，我们可以用一个池子，里面放入小对象，比如：ABCDFE,然后需要的话就去池子里面取即可
	//和线程池是一个思想，不想new出很多个，那就预先定义好，然后再去取；同理String类的字符串池，也都是从字符串常量池里面去取
	
	//5. 代理模式Proxy详解：
	//(1) 静态代理模式：简单而言就是代理人能帮助另一个人处理事务(原事务)，然后可以放入自己的方法(新事务)，原事务+新事务就是代理
	//代理类和被代理类同时实现了相同的接口，然后在代理类里面放入一个接口类对象并重写方法加入自己的处理，这样可以将被代理类传入代理类进行运行
	//(2) jdk动态代理：不需要明确的指定代理类，直接Proxy.newProxyInstance(classLodader,Interface[],InvocationHandler)生成类的统一代理类且实现了接口
	//具体的调用就是javaguide里面例子写的。补充一个：Proxy代理的生成来自于-----asm二进制字节码，通过genCode函数来生成Proxy
	//(3) *****注意：cglib需要最后运行指定代理的哪个方法，但是JDK动态代理不用，直接接口数组里面放的就是被代理的方法。
	//(4) *****AOP面向切面编程，其实就是对一些方法A,B,C的调用时候，加一个整个切面作为代理类，代理方法为D,E,F(会指定好是哪些代理类)，运行这些方法A,B,C时候
	//需要运行这些代理类方法D,E,F。配置的话，那就是<aop:config>...</aop:config> ...里面会指定切谁，切谁的哪个方法，切的方法是什么。也可以@Aspect注解实现
	//说白了就是将被代理类的运行切掉，直接去运行代理类的方法
	//(5) 代理的底层实现：ASM字节码文件，通过ClassWriter(写入代理类),ClassVisitor(中间加操作，通过汇编代码来加入before,after),ClassReader(读取源类)
	
	//6. 迭代器iterator模式详解：
	//(1) 通用的遍历方式：用iterator拿到下一个元素来遍历：写一个Iterator接口，然后所有的数据结构都去实现该接口：重写两个方法方法：hasNext()有下一个元素与否，next()下一个
	//iterator的new出来是从空开始，调用next才能获取第一个元素
	//(2) ArrayIterator,LinkedIterator实现Iterator，然后ArrayList,LinkedList里面有这两个迭代类，然后就可以直接使用
	
	//7. visitor访问者模式详解：
	//按照访问者的身份(访问的东西结构是固定的)进行不同的处理，这时候就需要统一的接口visitor，不同的visitor实现该接口，做出不同的处理。
	//应用：AST语法分析树处理节点(固定)不同的三种处理法
	
	//8. Builder模式：
	//构建复杂对象，那就buildpart1,buildpart2,buildpart3...build,简单而言就是分部分构造，最后build即可
	//实现：在类中写一个静态内部类builder,然后由builder去分部分构造类，用到的指定好，不需要的就不用写了，避免了很多变量的直接构造函数
	//可以用.式编程，builder.buildWall().builderWeapon().buildOcean().build()
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
