package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

public class researchDesignPatterns {
	
	//设计模式23种，提升内功，编程提升，源码提升
	
	
	//1. singleton单例模式详解：
	//(1) 饿汉式：1.构造器私有 2. 内部new出一个private static final的对象 3. 提供一个public static方法，让外部可以获取到这个实例
	//饿汉式非常安全实用，但是由于是静态且直接初始化的的，所以类加载时候就完成了实例化，有些时候我们不用这个类，但是也直接加载了实例化了。
	//(2) 懒汉式：按需初始化，但是有线程安全问题：可能第一个刚进入==null,还没new,另一个线程来了，还是==null,所以又new了一个
	//解决办法:直接用synchronized对整个方法加锁，但是加锁效率就会降低
	//**懒汉式怎么写：1. 不要final,一开始只需要声明 2. 将new对象的过程放入了getInatance中，如果是null就初始化
	//(3) 增加效率：减少同步代码块，把类锁放到判断之后synchronized，会出现问题：*****线程A判断==null进入但是没获取锁；线程B判断==null进入且获取锁，B运行完了后锁给了A
	//A直接进入并再一次new了，导致初始化两次。
	//(4) 双重检查+volatile：在synchronized里面再加一次判断，if(instance==null),双重判断，如果B初始化过，那就直接return;这是可行的(相当于第一次判断仅仅是节省时间)(需要加上volatile禁止重排序)
	//(5) 比较好的是实现：静态内部类：不会随着外部类加载而加载，所以可以用一个静态内部类，里面new一个本类，然后getInstance方法获取这个静态内部类的内部这个对象即可
	//(6) 完美写法：枚举单例，用enum，里面有个instance；外部用     类名.instance.hashcode()即可获取
	//*****为什么要加volatile:禁止指令重排序，因为new的过程分三步：创建内存空间，内存地址分配，初始化.
	//但是二三步可以进行指令重排序，导致对象先分配了内存地址，但是还没初始化，此时另一个线程调用了这个对象，那么就会得到还未初始化完成的对象，出错
	//手写单例模式：
	//饿汉式：
	/*public class singleton01{
		private singleton01() {};
		private static final singleton01 s1=new singleton01();
		public static singleton01 getInstance01() {
			return s1;
		}	
	}
	
	public class singleton02{
		private singleton02() {};
		private static volatile singleton02 s2;
		public static  singleton02 getInstance02() {
			if(s2==null) {
				synchronized(singleton02.class) {
					if(s2==null) {
						s2=new singleton02();
					}
				}
			}
			return s2;
		}
	}*/
	
	
	
	
	
	
	
	
	
	//2. 策略模式strategy详解：
	//(1) sort函数的一种实现：内部用的是泛型+实现Comparable接口+compareTo方法，泛型泛化所有的类型用于排序算法，compareTo方法定义了不同的类型如何比较，比如用Cat.weight比较
	//A.compareTo(B):A>B,返回1；A=B，返回0；A<B,返回-1。内部会有排序的具体规则属性；一般指的都是this对象和参数对象进行比较，this就是A，参数对象就是B
	//(2) *****另一种实现：comparator接口，sort参数有两个，一个是泛型数组，另一个是comparator接口，comparator里面有个方法compareTo需要使用时候主动实现
	//这样子在使用中，compareTo方法可以自己在使用时候手动实现，实现了扩展，而不是直接在类中指定(可以DogComparator，CatComparator都实现了Comparator接口，也可以直接lambda表达式(o1,o2)->{ })
	//函数式接口仅有一个方法，那么可以直接用lambda表达式来写
	//(3) 策略模式：用一个公共的接口，采取不同的策略来实现，这样以后如果需要扩展，那就继续实现接口的策略N即可
	
	
	//3. 工厂模式Factory详解:
	//(1) 简单工厂：就是用一个Factory类，里面有一些方法createCar,createPlane...把不同对象生成放到了统一的工厂里面
	//(2) *****抽象工厂：用于产品族，实体工厂A继承于抽象工厂(有产品生成的抽象方法，没有实现)，并实现了具体产品的生成方法，然后调用时候，就可以用抽象工厂f =new 实体A()
	//调用f.方法即可获取不同产品。换实体工厂B时候，只需要修改具体实现的方法和new 实体B()即可
	//(3) *****抽象类==接口，但是一般形容词用接口，名词用抽象类
	//(4) 简单工厂适用于产品直接扩展，加类加方法即可；抽象工厂适合于产品一族一族的扩展，也就是实体A工厂一族，B一族，但是单个产品不好扩展
	
	//4. 门面facade，调停者模式模式Mediator详解：
	//门面模式：对外，外部一个门面，屏蔽内部依赖关系，外部人直接访问门面即可
	//调停者：对内，内部一个中间者A，负责调节所有人的关系，所有人之间的交流都通过中间者A来进行，所有人都直接联系A而不是彼此联系
	//应用：消息中间件，所有人都和中间件打交道，而不是彼此直接交流
	
	//5. 装饰器Decorator模式详解：
	//(1) 想要对某一个对象进行装饰，添加一些额外的东西，如果用继承，那么挨个加很多，就会创建很多的类，很复杂
	//(2) 直接设计一个Decorator类，里面聚合了GameObject(内部类)(所有实体类)，然后写paint装饰方法(方法里面聚合了GameObject.paint+额外装饰decorate)
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
