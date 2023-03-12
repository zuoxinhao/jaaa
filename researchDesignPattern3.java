package work;

public class researchDesignPattern3 {
	
	
	//1. Adapter适配器模式详解：
	//(1) 相当于电压转接头，把A-----适配器-----B，A通过适配器才能适配B。
	//(2) InputStream(字节)连接BufferedReader(按行)，需要通过中间适配器Adapter来连接；SQL server里面接口是ODBC，Java用的是JDBC,两个接口不一样，中间也需要适配器bridge
	//(3) windowAdpater，keyAdapter这些带Adapter结尾的类，不是适配器，这是为了方便编程，不用重写所有的方法了，用抽象类实现接口，那么接下来的重写就不再需要重写所有方法
	
	//2. Bridge桥接模式详解：
	//(1) 分离抽象和具体：将抽象，具体分开来各自发展继承，抽象总父类里面聚合具体的总父类，用于两者的结合
	//(2) 例如：gift抽象有warmGift,coldGift,giftImpl具体类有car,book,flower，在gift抽象里面聚合一个giftImpl对象；调用时候就是new warmGift(new book())实现了结合
	
	//3. Command命令模式详解：
	//Command命令模式一般都是接口，有两个方法：doit(),undo(),执行和回退命令
	
	//4. Prototype原型模式详解：
	//(1) clone：如果我们希望得到和一个对象属性差不多的对象，那就直接调用clone方法
	//(2) Java的Object自带了clone，其他类需要去实现cloneable接口，重写clone方法；Object的clone是将对象属性全部拷贝，然后放到另一块内存空间
	//*****引用类型属性，指向的是同一个引用对象，该对象不会被拷贝。这就是浅拷贝，Object自带的浅拷贝
	//*****String类型也是引用类型，但是String类型用的是常量池，原先的String变了，那么直接去新的对象了，和拷贝出来的互不影响。所以String不需要重写深拷贝；但StringBuilder需要重写深拷贝
	//(3) 深拷贝浅拷贝：浅拷贝就是引用对象不会被拷贝，指向同一个对象；深拷贝就是引用对象也复制一份，和原先互不影响。深拷贝就需要自己去实现
	
	//5. Memento备忘录模式详解：
	//(1) 用于记录状态：记录快照，存盘；可以用一个单独的类专门用来记录事务的状态，便于后面恢复之前的状态
	//(2) Command+Memento就可以实现事务Transcation的回滚，就是记录状态并undo
	
	//6. TemplateMethod模板方法模式详解：
	//父类作为模板，有一些可以重写的方法，子类重写这些方法，就是使用了模板
	
	//7. state状态模式详解：
	//(1) 根据状态决定行为：如果根据状态来决定行为的发生(switch 状态 case   行为)，就可以抽象出来state模式
	//方式：将state抽象出来，聚合到类中，然后我们只需要在state里面进行不同状态的方法实现即可
	//(2) 例如：TCP有open,close方法，但是需要根据TCPState进行不同的函数处理，所以抽象出TCPState,下面有三个子类TCPCON.TCPDONE,TCPCLOSE,然后每个子类再去实现两个方法
	//而在TCP类里面直接聚合TCPState即可
	//(3) 例如：线程的状态变化，需要根据状态和输入进行状态迁移，所以可以用到state模式
	
	//8. Intepreter解释器模式详解：
	//最后一种，但是基本没有用，相当于解释一门语言，python解释器,底层开发才用到。
	
	//9. 总分类总原则：
	//(1) 设计模式分类：见图
	//(2) 核心：高内聚低耦合(少用继承)，聚合进去，抽象出来，可扩展
	//*****所有设计模式基本都源自于：多态，多态实现了这种抽象化，扩展化
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
