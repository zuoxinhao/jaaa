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

public class researchArrayListHashMap {
	
	
	//1. ArrayList扩容机制源码分析（第一份完整源码）
	//总结,很简单：(1)初始无参构造器容量是0,ArrayList有一个默认容量是10，这个容量只有在第一个元素add进去时候通过ensureCapacityInternal方法才会生效，到达10，
	//(2)然后元素个数超过10，会触发扩容机制grow方法，扩容机制的核心在于扩容为1.5倍（A+A>>1）
	//(3)grow中如果扩容为1.5倍还不够，那就直接变成所需要的minCapacity.*****但是元素是挨个加进去的，minCapacity线性增加，所以一般都是逐渐扩容1.5倍
	//(4)然后扩容上限是:比较新容量newCapacity和Integer.MAX_VALUE-8(最大容量),如果新容量大于了最大容量，那就根据需要容量minCapacity和Integer.MAX_VALUE-8大小比较
	//需要容量大，那就扩容为Integer.MAX_VALUE;最大容量大，扩容为Integer.MAX_VALUE-8
	//**每次扩容为1.5倍，可能超过最大值了，所以要比较newCapacity和最大容量
	
	//重点源码分析：
	//简单总结：add-----ensureCapacityInternal-----取最大值初始10，ensureExplicitCapacity-----是否需要grow-----
	public class researchArrayList<E>{
		private static final int DEFAULT_CAPACITY=10;
		
		int size;                         //专门用于记录元素个数
		
		transient Object[] elementData;   //ArrayList底层存储方式是Object数组
		
		public boolean add(E e) {         //一般从add函数开始分析：
			ensureCapacityInternal(size+1);
			elementData[size++]=e;
			return true;
		}
		
		private void ensureCapacityInternal(int minCapacity) {  //minCapacity就是所需要的容量，即元素新个数
			minCapacity=Math.max(DEFAULT_CAPACITY, minCapacity);//minCapacity设置为了10和当前容量的最大值
			ensureExplicitCapacity(minCapacity);
		}
		
		private void ensureExplicitCapacity(int minCapacity) {
			int modCount=0;
			modCount++;
			
			if(minCapacity-elementData.length>0) {  //判断所需容量是否大于数组当前存储容量，如果大于就需要扩容
				grow(minCapacity);
			}
		}
		
		private static final int MAX_ARRAY_SIZE=Integer.MAX_VALUE-8;
		
		//*****核心：
		private void grow(int minCapacity) {
			int oldCapacity=elementData.length;
			int newCapacity=oldCapacity+oldCapacity>>1;
			if(newCapacity-minCapacity<0) {
				newCapacity=minCapacity;
			}
			if(newCapacity-MAX_ARRAY_SIZE>0) {
				newCapacity=hugeCapacity(minCapacity);
			}
			elementData=Arrays.copyOf(elementData, newCapacity);	
		}
 
		//这里不是，看下一个hugeCapacity
		private int hugeCapacity(int minCapacity) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		/*private int hugeCapacity(int minCapacity) {
			return (minCapacity>MAX_ARRAY_SIZE)?Integer.MAX_VALUE:MAX_ARRAY_SZIE;
		}*/
		
	}
	
	
	//2. HashMap源码及实现结构分析
	//总结：(1)HashMap的默认容量是16(1<<4)，存储结构是node<K,V>数组+链表/红黑树。对于每次put(key,value),会调用putVal()函数，在这个函数中，会先进行散列操作
	//(2)散列操作：[(hashcode()^(h>>>16))](不说)-----得到hash值，然后**hash&(n-1)  (n就是数组的容量)  得到散列到的数组位置
	//(3)如果数组位置上有元素，那就比较key是否相同，相同那就覆盖，不同就加入链表或者红黑树
	//(4)加入的判断：先判断是否是红黑树节点，如果是则直接加入；不是则尾插法加入链表，然后看链表个数是否大于等于9，大于等于9执行treeifyBin()
	//(5)treeifyBin()：先判断数组容量是否大于等于64，大于等于64则需要将链表转化为红黑树；小于则进行resize数组扩容，重新散列（重构）
	//数组容量不够，执行resize:扩容成两倍容量（<<1）,并且重新hash，复制元素分配位置。
	//**为什么要把链表转化为红黑树，链表查找是O(n),红黑树O(logn)，当个数小于等于8的时候差别不明显，但是大于8后就非常明显了
	//总而言之，为了加快查找效率。
	
	//重点源码分析：
	
	/*public class researchHashMap<K,V>{
		static final int DEFAULT_INITIAL_CAPACITY=1<<4;
		
		static final int TREEIFY_THRESHOLD=8;                   //链表变红黑树的阈值设置成了8，为什么？？？
		                                                        //*****因为链表个数从-1开始计数，所以后面121行的判断是大于等于阈值-1，就是大于等于9个，才开始变
		static final int UNTREEIFY_THRESHOLD=6;                 
		
		static final int MIN_TREEIFY_CAPACITY=64;
		
		transient Node<K,V>[] table;
		
		public V put(K key,V value) {
			return putVal(hash(key),key,value,false,true);
		}
		
		final V putVal(int hash,K key,V value,boolean onlyIfAbsent,boolean evict) {
			//只写重点
			if((p=tab[i=(n-1)&hash])==null) {                   //首先定位到数组位置，数组位置没值则直接放入
				tab[i]=newNode(hash,key,value,null);
			}else {
				if(p.hash==hash&&((k=p.key)==key)||(key!=null&&key.equals(k))) {  //有值那就比较key是否相同，相同则覆盖
					e=p;
				}else if(p instanceof TreeNode) {                                 //不同需要插入，先判断是否是红黑树节点，是则直接插入红黑树
					e=putTreeVal(this,tab,hash,key,value);
				}else {
					for(int binCount=0;;++binCount) {                             //不是就尾插法插入链表
						if((e=p.next)==null) {
							p.next=newNode(hash,key,value,null);
							if(binCount>=TREEIFY_SHRESHOLD-1) {                   //插入完之后需要判断是否要变红黑树，执行treeifyBin函数
								treeifyBin(tab,hash);
							}
							break;
						}
					}
				}
			}
		}
	}*/
	
	
	//3. ConcurrentHashMap的源码及实现
	//JAVA7里面是segment数组负责加锁（ReentrantLock锁，每次仅允许一个线程操作该数组），然后每个segment里面放HashEntry，就相当于一个HashMap，有数组和链表构成
	//JAVA8是直接用的Node数组+链表/红黑树来实现的，首先确定Node数组位置之后，如果空则需要放入key,用的是CAS放入；
	//然后发生冲突，需要放入链表节点或者树节点，用的是synchronized锁，把放入的操作全锁住即可。
	
	//这部分具体源码太多了，只看了看重点的“锁的位置”，不需要完整的源码阅读。

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
