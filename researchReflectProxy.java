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
	
	
	//1. �����˽ⷴ�����
	//����Ŀ�ģ�ͨ��ʵ������ȡ�࣬������ȡ����������ԣ�������ʵ���Ͼ�������ʱ��̬��ȡ�༰���ڲ�����
	//��ȡ�����ض������ַ�ʽ���õ�����ȡ���Է���Ҳ���ض��ĺ�����ʽ
	//��ȡ������ַ�ʽ��
	//(1)֪������,.class
	Class class1=TargetObject.class;
	//(2)֪���������ȫ·��Class.forName()
	//Class class2=Class.forName("work.TargetObject");   //""��·��
	
	//(3)*****ͨ��ʵ������ȡ�࣬getClass()
	TargetObject o=new TargetObject();
	Class class3=o.getClass();                            //��ȡ����TargetObject���ڱ�����Ϊ��class3��
	
	//(4)�����������·��
	//Class class4=ClassLoader.getSystemClassLoader().loadClass("work.TargetObject")
	
	//�õ���󣬻�ȡ������ԣ�����,���Է����ڷ�����ֱ�ӷ�װ���������ʽ
	//���ȴ�����ʵ����newInstance()
	//��ȡ�������ԣ����з���getDeclaredMethods()�����������Ͳ���getDeclaredMethod("����"����������)������getDeclaredField("������")
	//��������������.invoke(ʵ��������)
	//�޸����ԣ�����.set(ʵ��������ֵ)
	//�޸İ�ȫ��飺�����private��setAccessible(true)
	
	/*ʵ�ִ���
	Class targetClass=TargetObject.class;                              �����targetClass
	TargetObject o=(TargetObject)targetClass.newInstance();            ʵ��������o
	Method[] m=targetClass.getDeclaredMethods();                       *****�������з�����װ����Method�࣬���Է�װ����Field�࣬ͨ����������
	Method m1=targetClass.getDeclaredMethod("publicMethod",String.class);
	m1.invoke(o,"good");
	Field f=targetClass.getDeclaredFiled("name");
	f.setAccessible(true);
	f.set(o,"xinhao");
	*/
	
	//2. ����������ģʽ
	//���֣���̬�����ܼ򵥣����Ƕ���ӿڣ�ʵ���࣬�����֮࣬��ʵ����ע�������Ĺ��캯���У�������ʵ���˴����಻�����Ե���ʵ���෽�����������Լ��Ӳ���
	//JDK��̬��������ӿڣ�ʵ���࣬�뾲̬��ͬ���ǣ���̬����ʵ���˴���ķ�װ��ʹ�ô�����ͳһ���ˣ�������ҪΪÿһ��ʵ���ഴ��һ��������
	//CGLIB��̬����������Ҫ�ӿڣ�����ֱ��Ϊʵ���ഴ������
	//JDK��̬����ʵ�ִ��룺
	//*****�ܣ��ɹ��������ɴ��������Ȼ��send()���Զ�����InvocationHandler��invoke����������ͻ����ԭʵ�ַ����Լ����������
	
	//(1)����ӿڼ���ʵ����
	public interface SendEmail{
		String send(String message);
	}
	
	public class email implements SendEmail{
		public String send(String message) {
			return message;
		}
	}
	//(2)*****�Զ���InvocationHandler,��дinvoke������invoke�����л����ʵ����ķ��������ҿ����Լ���һЩ�߼�
	public class DebugInvocationHandler implements InvocationHandler{
		private final Object target;
		
		public DebugInvocationHandler(Object target) {
			this.target=target;
		}
		
		public Object invoke(Object proxy,Method method,Object[] args) throws InvocationTargetException,IllegalAccessException{
			System.out.print(method.getName());           //�����߼�
			Object result=method.invoke(target, args);    //����ʵ���෽��
			return result;
		}
	}
	//(3)����������Ĺ����࣬��newProxyInstance���ɴ��������
	/*public class JDKProxyFactory{
		public static Object getProxy(Object target) {         //��������static����ᱨ��һ�㶫�������ⲻ���ù�
			return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new DebugInvocationHandler(target));
		}
	}*/
	
	//(4)ʵ��ʹ��,���ô����๤����getProxy�������ɴ��������Ȼ���ת��InvocationHandler
	/*SendEmail s=(SendEmail)JDKProxyFactory.getProxy(new email());
	s.send("JAVA");*/
	
	
	//CGLIB��̬����ʵ�ִ��룺
	//(1)����һ��������
	public class AliSmsService{
		public String send(String message) {
			System.out.print(message);
			return message;
		}
	}
	
	//(2)�Զ���MethodInterceptor,��дintercept������Ч����InvocationHandler��invoke����һ��
	//CGLIB��Ҫ�ֶ���������ſ���ʹ��
	/*public class DebugMethodInterceptor implements MethodInterceptor{
		public Object intercept(Object o,Method method,Object[] args,MethodProxy methodProxy) throws Throwable{
			System.out.print(method.getName());
			Object object=methodProxy.invokeSuper(o,args);
			return object;
		}
	}*/
	
	//(3)ͨ��CGLIB�Ĺ����ഴ�������ֻ࣬�������и���ͬ��*****CGLIB������ͨ��Enhancer�������������࣬������newProxyInstance����
	/*public class CglibProxyFactory{
		public static Object getProxy(Class<?> calzz) {
			Enhancer enhancer=new Enhancer();
			enhancer.setClassLoader(clazz.getClassLoader());
			enhancer.setSuperClass(clazz);
			return enhancer.create();
		}
	}*/
	
	//(4)ʵ�ʵ���
	/*AliSmsService a=(AliSmsService)CglibProxyFactory.getProxy(AliSmsService.class);
	a.send("JAVA");*/
	
	
	//3. �Ա����ִ���ʽ
	//                         ��̬����                  JDK��̬����              CGLIB��̬����
	//   JVM                   .class�ļ�                 �ֽ���                       �ֽ���
	//   �޸�                                 �鷳��ÿ��һ��         һ��������                һ�������࣬��װ�õ�
	//   �ӿ�                                                                  ����Ҫ�нӿ�                ����Ҫ
	//   �Զ�����                              ע����           InvocationHandler      MethodInterceptor
	//   ����                                                                     invoke            intercept
	//   �����๤�����ɶ���                                 Proxy.newProxyInstance   Enhancer��������
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
