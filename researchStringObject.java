package work;
import java.util.*;
import java.util.stream.Collectors;

import javax.xml.soap.Node;

import java.lang.*;
import java.lang.reflect.Array;

public class researchStringObject {
	
	
	
	
	//1. Integer,Character,Boolean包装类的缓存机制(源码及实现)
	//包装类用缓存机制（数组）来实现高性能，Integer类缓存[-128,127],Character缓存[0,127],Boolean类型缓存true,false.
	//cache数组注意下，下标-low开始，在valueOf方法里面
	//*****因为有缓存机制，所以[-128,127]之间的数字进行Integer初始化时候，得到的是同一对象;超出范围的就不是同一对象
	//*****使用new Integer 不会使用缓存数组，而是直接创建一个Integer对象
	//*****Integer类型的equals方法，可以直接判断值，而不是判断是否是同一对象；所以Integer类型的值判断，都用equals，而不用==（==超出范围无法进行值判断，转为了对象判断）
	//所以包装类的判断一般都用equals，而不用==
	public static boolean testInteger1() {
		Integer i=1;
		Integer j=1;
		return i==j;              //true
	}
	
	public static boolean testInteger2() {
		Integer i=128;
		Integer j=128;
		return i==j;              //false
	}
	
	public static boolean testInteger3() {
		Integer i=23;
		Integer j=new Integer(23);
		return i==j;              //false
	}
	
	public static boolean testInteger4() {
		Integer i=232;
		Integer j=232;
		return i.equals(j);       //true，equals方法只判断值是否相同，不分对象
	}
	
	/*（源码）public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];     //为什么是减号？因为cache缓存数组里面定义的就是-low.
        return new Integer(i);
    }
    private static class IntegerCache {
    static final int low = -128;
    static final int high = 0;    //实际上没有初始值0，后加的，不然报错
    static {
        int high = 127;
    }
    static int[] cache=new int[high-low+1]; 
    }*/
	
	public static void main(String[] args) {
		System.out.print(testInteger4());
	}
	
	
    //2. Object的一些源码方法
	//最重要的就是两个：hashcode(),equals()
	//object的初始equals是比较两个对象地址是否相同，String类型重写过了equals方法，判断字符串值是否相同；Integer页重写过了equals，变成判断值是否相同
	//*****==：对于基本数据类型没有地址，只能判断值；对于引用类型，判断地址
	//*****equals没重写的话，判断的也是地址；基本数据类型没有equals方法
	//*****总结：说白了，==和equals都是判断地址是否相同；基本数据类型没有地址只能判断值；
	//Integer，String重写过equals，所以判断值；对于Integer,由于缓存机制，[-128,127]判断==，很容易指向同一份对象。
	
	/*（源码）public boolean equals(Object obj) {
	     return (this == obj);
	}
	*/
	
	
	//3. String的源码
	//String类型，由于用了private导致其没有暴露给外部修改；用了final，不能被继承，所以子类也无法修改；所以String类型写好值就不能改变
	//用+来连接，实际上是生成了新的对象，内部实现是通过StringBuilder的append方法，然后再toString生成新对象
	//所以一般用StringBuilder,StringBuffer(线程安全)
	
	
	/*（源码）public final class String implements java.io.Serializable, Comparable<String>, CharSequence {
	    private final char value[];
	}
	*/
	
	
	//4. String类型字符串常量池的分析：
	//*****常量池分析：
	//*****String s="..."  会进行两步操作：1. 堆中创建对象s       2. 将s的引用放到字符串常量池中；（如果常量池中本就有该字符，那就直接在堆中返回该字符引用即可）
	//String s=new String("...") 只会进行一步操作：堆中创建新对象s，但不会放到常量池中
	//intern():如果该字符不在常量池中，则放入常量池；如果在了，直接返回引用（按照值来返回引用）
	String s1="JAVA";
	String s2=s1.intern();                 //因为s1的初始化已经放入常量池了，所以s2得到的是s1的引用
	String s3=new String("JAVA");          //新建一个对象，但不放入常量池；   s1!=s3 因为是不同的对象，且没有引用关系，所以不等
	String s4=s3.intern();                 //s4还是JAVA的常量池引用，所以s4还是s1的引用
	String s5="JAVA";                      //s5那就是新建对象，然后返回的是引用s1
	//结果：s1==s2    s3!=s4      s4==s1    s5==s1
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
