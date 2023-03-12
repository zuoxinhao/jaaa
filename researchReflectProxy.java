package work;

import java.util.*;
import java.util.stream.Collectors;

import javax.xml.soap.Node;

import java.lang.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;



public class researchReflectProxy {
	
	public class TargetObject{
		
		private String name;
		
		public void publicMethod(String s) {
			System.out.print(s);
		}
		
		private void privateMehod() {
			System.out.print(name);
		}
		
		
	}
	
	
	//1. 深入了解反射代码
	//反射目的：通过实例来获取类，进而获取更多类的属性，方法，实际上就是运行时动态获取类及其内部内容
	//获取类有特定的四种方式，得到类后获取属性方法也有特定的函数方式
	//获取类的四种方式：
	//(1)知道类名,.class
	Class class1=TargetObject.class;
	//(2)知道类的所在全路径Class.forName()
	//Class class2=Class.forName("work.TargetObject");   //""放路径
	
	//(3)*****通过实例来获取类，getClass()
	TargetObject o=new TargetObject();
	Class class3=o.getClass();                            //获取的类TargetObject现在被命名为了class3类
	
	//(4)类加载器传入路径
	//Class class4=ClassLoader.getSystemClassLoader().loadClass("work.TargetObject")
	
	//得到类后，获取类的属性，方法,属性方法在反射中直接封装成了类的形式
	//首先创建类实例：newInstance()
	//获取方法属性：所有方法getDeclaredMethods()；单个方法和参数getDeclaredMethod("名字"，参数类型)；属性getDeclaredField("属性名")
	//触发方法：方法.invoke(实例，参数)
	//修改属性：属性.set(实例，属性值)
	//修改安全检查：（针对private）setAccessible(true)
	
	/*实现代码
	Class targetClass=TargetObject.class;                              类叫做targetClass
	TargetObject o=(TargetObject)targetClass.newInstance();            实例对象是o
	Method[] m=targetClass.getDeclaredMethods();                       *****所有类中方法封装成了Method类，属性封装成了Field类，通过类来调用
	Method m1=targetClass.getDeclaredMethod("publicMethod",String.class);
	m1.invoke(o,"good");
	Field f=targetClass.getDeclaredFiled("name");
	f.setAccessible(true);
	f.set(o,"xinhao");
	*/
	
	//2. 深入理解代理模式
	//三种：静态代理：很简单，就是定义接口，实现类，代理类，之后将实现类注入代理类的构造函数中，这样就实现了代理类不仅可以调用实现类方法，还可以自己加操作
	//JDK动态代理：定义接口，实现类，与静态不同的是，动态代理实现了代理的封装，使得代理类统一化了，不再需要为每一个实现类创建一个代理类
	//CGLIB动态代理：不再需要接口，可以直接为实现类创建代理
	//JDK动态代理实现代码：
	//*****总：由工厂类生成代理类对象，然后send()会自动触发InvocationHandler的invoke方法，里面就会放入原实现方法以及额外操作。
	
	//(1)定义接口及其实现类
	public interface SendEmail{
		String send(String message);
	}
	
	public class email implements SendEmail{
		public String send(String message) {
			return message;
		}
	}
	//(2)*****自定义InvocationHandler,重写invoke方法，invoke方法中会调用实现类的方法，而且可以自己加一些逻辑
	public class DebugInvocationHandler implements InvocationHandler{
		private final Object target;
		
		public DebugInvocationHandler(Object target) {
			this.target=target;
		}
		
		public Object invoke(Object proxy,Method method,Object[] args) throws InvocationTargetException,IllegalAccessException{
			System.out.print(method.getName());           //额外逻辑
			Object result=method.invoke(target, args);    //调用实现类方法
			return result;
		}
	}
	//(3)创建代理类的工厂类，用newProxyInstance生成代理类对象
	/*public class JDKProxyFactory{
		public static Object getProxy(Object target) {         //这里由于static问题会报错一点东西，问题不大不用管
			return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new DebugInvocationHandler(target));
		}
	}*/
	
	//(4)实际使用,调用代理类工厂的getProxy函数生成代理类对象，然后会转到InvocationHandler
	/*SendEmail s=(SendEmail)JDKProxyFactory.getProxy(new email());
	s.send("JAVA");*/
	
	
	//CGLIB动态代理实现代码：
	//(1)定义一个操作类
	public class AliSmsService{
		public String send(String message) {
			System.out.print(message);
			return message;
		}
	}
	
	//(2)自定义MethodInterceptor,重写intercept方法，效果和InvocationHandler的invoke方法一样
	//CGLIB需要手动添加依赖才可以使用
	/*public class DebugMethodInterceptor implements MethodInterceptor{
		public Object intercept(Object o,Method method,Object[] args,MethodProxy methodProxy) throws Throwable{
			System.out.print(method.getName());
			Object object=methodProxy.invokeSuper(o,args);
			return object;
		}
	}*/
	
	//(3)通过CGLIB的工厂类创建代理类，只是这里有个不同，*****CGLIB里面是通过Enhancer类来创建代理类，而不是newProxyInstance方法
	/*public class CglibProxyFactory{
		public static Object getProxy(Class<?> calzz) {
			Enhancer enhancer=new Enhancer();
			enhancer.setClassLoader(clazz.getClassLoader());
			enhancer.setSuperClass(clazz);
			return enhancer.create();
		}
	}*/
	
	//(4)实际调用
	/*AliSmsService a=(AliSmsService)CglibProxyFactory.getProxy(AliSmsService.class);
	a.send("JAVA");*/
	
	
	//3. 对比三种代理方式
	//                         静态代理                  JDK动态代理              CGLIB动态代理
	//   JVM                   .class文件                 字节码                       字节码
	//   修改                                 麻烦，每个一个         一个代理类                一个代理类，封装好的
	//   接口                                                                  必须要有接口                不需要
	//   自定义类                              注入类           InvocationHandler      MethodInterceptor
	//   方法                                                                     invoke            intercept
	//   代理类工厂生成对象                                 Proxy.newProxyInstance   Enhancer类来生成
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
