package work;

public class researchSpring {
	
	
	//有关Spring源码的深入分析
	//*****PostProcessor:扩展器，应我们的需求，做一些中间处理，交给下一阶段
	
	//1. IOC容器
	//(1) IOC容器里面放的是Bean,Bean的存储用的是Map三级缓存，K-V(K是BeanName,V是该对象实例)
	//下面是一个完整的IOC流程：2，3，4，5需要看图进行回忆理解
	//(2) (看视频中的图)客户端-----IOC容器-----容器里面有预定义Bean信息BeanDefinition(一般来自于XML文件)：所谓控制反转，实际上IOC容器是从预定义好的Bean中获取，然后返回给客户端应用
	//(3) (图)来自于XML，yaml,json的文件配置信息，需要导入到预定义Bean信息中，用的是抽象的接口BeanDefinitionReader
	//(4) 读入预定义Bean信息后，需要的是用定义信息经过反射在IOC容器中进行实例化Bean(开辟堆空间，设默认值)，初始化Bean(init方法)，完整的Bean对象给到客户端
	//(5) Bean预定义信息通过BeanFactoryPostProcessor(扩展器)处理定义信息，怎么处理都可以，处理完了给BeanFactory去用反射实例化Bean;
	//(6) Environment接口：将系统环境变量env和属性properties加载进去，供后续使用
	//(7) *****AbstractAppllicationContext中的refresh函数，是IOC最重要的函数，包含了一系列的Bean流程:
	//先创建BeanFactory;读取配置文件信息(基本的信息);BeanFactoryPostProcessor进行信息的扩展处理；准备好监听器，用于监听Bean的实例化；实例化Bean;populateBean()填充类的属性值；
	//beforePostProcessor;执行init-method;afterPostProcessor;使用context.getBean()获取初始化后的Bean
	//(8) BeanFactory通过反射获取类，类的构造器，然后通过构造器ctor.newInstance()实例化Bean
	//(9) 属性填充用到的方法：populateBean()
	
	
	//2. BeanFactory和FactoryBean的区别
	//FactoryBean:生产特殊的对象，比如：日期Bean,运算符转换Bean...或者自定义创建的简单对象逻辑，不需要经过标准的实例化初始化   三个方法：isSingleton(),getObject(),getObjectType()
	//核心方法：getObject() 里面可以放自己的简单逻辑，甚至new也可以
	//BeanFactory用于spring来生产普通的Bean
	
	//3. 循环依赖
	//问题概述：A类中有B为内部类，B中有A为内部类，互相引用
	//解决办法：三级缓存类DefaultSingletonBeanRegistry类，singletonObjects一级(ConcurrentHashMap)，earlySingletonObjects二级(HashMap)，singletonFactories三级(HashMap)
	//本来的流程：先对A进行实例化，填充属性时候发现需要B对象，又去实例化B，但是B实例化又需要A...产生了循环依赖
	//解决流程：三级缓存的运用
	//*****每次获取Bean都是从一级缓存先获取值，如果获取不到再去流程中
	//*****先将A实例化但未初始化，A的匿名内部类加入三级缓存，B的实例化但未初始化，B的匿名内部类加入三级缓存；然后通过匿名内部类处理普通/代理对象，
	//将A加入二级缓存，B不用；B可以直接放入一级缓存，最后两个A,B都加到一级缓存，用于getBean
	//*****核心思想：将对象的创建分状态存储，这样就不会获取到中间态（仅实例化未初始化）的对象
	//*****一级缓存：存储完整状态(实例化+初始化)的对象   二级缓存：存储实例化但是未初始化的对象  三级缓存：存储对象的匿名内部类
	//一级二级缓存就可以解决循环依赖
	//但是三级缓存存在的意义：避免有些对象是AOP代理对象的对象，这样会导致获取的是代理对象，而不是实际对象;方法是：getearlyBeanReference()
	//*****核心中的核心：将实例化但未初始化的A，直接注入了B的初始化中，引用是同一个地址，这样就可以得到成品B，后续得到成品A。
	
	
	//4. 自动装配
	//通过注解就能实现某些功能@...
	//表面解释：通过@SpringBootApplication里面的@EnableAutoConfiguration开启自动装配，最后通过SpringFactoriesLoader加载自动装配实现自动装配，需要预先添加好依赖项，引入spring-boot-starter-xxx
	//深层的解释：在BeanFactoriesPostProcessor下面有个子类ConfigurationPostProcessor实现了starter接口，专门用于注解的方法实现。即工厂扩展类下面有个配置扩展类
	//重写了关于所有注解的方法，实现了自动装配。
	
	//5. Aware接口
	//用于对容器里面对象的getter,setter方法的同一调用，重写aware接口就可以重写getter,setter方法
	//*****在beforePostProcessor之前，会有个小步骤，就是设置容器对象属性，通过invokeAwareMethod()来实现.
	//populateBean填充的是自定义的属性，而aware设置的是系统容器对象属性，比如：ApplicationContext,BeanFactory的属性设置
	
	//6. AOP细节：
	//*****AOP面向切面编程，其实就是对一些方法A,B,C的调用时候，加一个整个切面作为代理类，代理方法为D,E,F(会指定好是哪些代理类)，运行这些方法A,B,C时候
	//需要运行这些代理类方法D,E,F。配置的话，那就是<aop:config>...</aop:config> ...里面会指定切谁，切谁的哪个方法，切的方法是什么。也可以@Aspect注解实现
	//说白了就是将被代理类的运行切掉，直接去运行代理类的方法
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
