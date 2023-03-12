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
	
	
	//1. ArrayList���ݻ���Դ���������һ������Դ�룩
	//�ܽ�,�ܼ򵥣�(1)��ʼ�޲ι�����������0,ArrayList��һ��Ĭ��������10���������ֻ���ڵ�һ��Ԫ��add��ȥʱ��ͨ��ensureCapacityInternal�����Ż���Ч������10��
	//(2)Ȼ��Ԫ�ظ�������10���ᴥ�����ݻ���grow���������ݻ��Ƶĺ�����������Ϊ1.5����A+A>>1��
	//(3)grow���������Ϊ1.5�����������Ǿ�ֱ�ӱ������Ҫ��minCapacity.*****����Ԫ���ǰ����ӽ�ȥ�ģ�minCapacity�������ӣ�����һ�㶼��������1.5��
	//(4)Ȼ������������:�Ƚ�������newCapacity��Integer.MAX_VALUE-8(�������),�������������������������Ǿ͸�����Ҫ����minCapacity��Integer.MAX_VALUE-8��С�Ƚ�
	//��Ҫ�������Ǿ�����ΪInteger.MAX_VALUE;�������������ΪInteger.MAX_VALUE-8
	//**ÿ������Ϊ1.5�������ܳ������ֵ�ˣ�����Ҫ�Ƚ�newCapacity���������
	
	//�ص�Դ�������
	//���ܽ᣺add-----ensureCapacityInternal-----ȡ���ֵ��ʼ10��ensureExplicitCapacity-----�Ƿ���Ҫgrow-----
	public class researchArrayList<E>{
		private static final int DEFAULT_CAPACITY=10;
		
		int size;                         //ר�����ڼ�¼Ԫ�ظ���
		
		transient Object[] elementData;   //ArrayList�ײ�洢��ʽ��Object����
		
		public boolean add(E e) {         //һ���add������ʼ������
			ensureCapacityInternal(size+1);
			elementData[size++]=e;
			return true;
		}
		
		private void ensureCapacityInternal(int minCapacity) {  //minCapacity��������Ҫ����������Ԫ���¸���
			minCapacity=Math.max(DEFAULT_CAPACITY, minCapacity);//minCapacity����Ϊ��10�͵�ǰ���������ֵ
			ensureExplicitCapacity(minCapacity);
		}
		
		private void ensureExplicitCapacity(int minCapacity) {
			int modCount=0;
			modCount++;
			
			if(minCapacity-elementData.length>0) {  //�ж����������Ƿ�������鵱ǰ�洢������������ھ���Ҫ����
				grow(minCapacity);
			}
		}
		
		private static final int MAX_ARRAY_SIZE=Integer.MAX_VALUE-8;
		
		//*****���ģ�
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
 
		//���ﲻ�ǣ�����һ��hugeCapacity
		private int hugeCapacity(int minCapacity) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		/*private int hugeCapacity(int minCapacity) {
			return (minCapacity>MAX_ARRAY_SIZE)?Integer.MAX_VALUE:MAX_ARRAY_SZIE;
		}*/
		
	}
	
	
	//2. HashMapԴ�뼰ʵ�ֽṹ����
	//�ܽ᣺(1)HashMap��Ĭ��������16(1<<4)���洢�ṹ��node<K,V>����+����/�����������ÿ��put(key,value),�����putVal()����������������У����Ƚ���ɢ�в���
	//(2)ɢ�в�����[(hashcode()^(h>>>16))](��˵)-----�õ�hashֵ��Ȼ��**hash&(n-1)  (n�������������)  �õ�ɢ�е�������λ��
	//(3)�������λ������Ԫ�أ��ǾͱȽ�key�Ƿ���ͬ����ͬ�Ǿ͸��ǣ���ͬ�ͼ���������ߺ����
	//(4)������жϣ����ж��Ƿ��Ǻ�����ڵ㣬�������ֱ�Ӽ��룻������β�巨��������Ȼ����������Ƿ���ڵ���9�����ڵ���9ִ��treeifyBin()
	//(5)treeifyBin()�����ж����������Ƿ���ڵ���64�����ڵ���64����Ҫ������ת��Ϊ�������С�������resize�������ݣ�����ɢ�У��ع���
	//��������������ִ��resize:���ݳ�����������<<1��,��������hash������Ԫ�ط���λ�á�
	//**ΪʲôҪ������ת��Ϊ����������������O(n),�����O(logn)��������С�ڵ���8��ʱ�������ԣ����Ǵ���8��ͷǳ�������
	//�ܶ���֮��Ϊ�˼ӿ����Ч�ʡ�
	
	//�ص�Դ�������
	
	/*public class researchHashMap<K,V>{
		static final int DEFAULT_INITIAL_CAPACITY=1<<4;
		
		static final int TREEIFY_THRESHOLD=8;                   //�������������ֵ���ó���8��Ϊʲô������
		                                                        //*****��Ϊ���������-1��ʼ���������Ժ���121�е��ж��Ǵ��ڵ�����ֵ-1�����Ǵ��ڵ���9�����ſ�ʼ��
		static final int UNTREEIFY_THRESHOLD=6;                 
		
		static final int MIN_TREEIFY_CAPACITY=64;
		
		transient Node<K,V>[] table;
		
		public V put(K key,V value) {
			return putVal(hash(key),key,value,false,true);
		}
		
		final V putVal(int hash,K key,V value,boolean onlyIfAbsent,boolean evict) {
			//ֻд�ص�
			if((p=tab[i=(n-1)&hash])==null) {                   //���ȶ�λ������λ�ã�����λ��ûֵ��ֱ�ӷ���
				tab[i]=newNode(hash,key,value,null);
			}else {
				if(p.hash==hash&&((k=p.key)==key)||(key!=null&&key.equals(k))) {  //��ֵ�ǾͱȽ�key�Ƿ���ͬ����ͬ�򸲸�
					e=p;
				}else if(p instanceof TreeNode) {                                 //��ͬ��Ҫ���룬���ж��Ƿ��Ǻ�����ڵ㣬����ֱ�Ӳ�������
					e=putTreeVal(this,tab,hash,key,value);
				}else {
					for(int binCount=0;;++binCount) {                             //���Ǿ�β�巨��������
						if((e=p.next)==null) {
							p.next=newNode(hash,key,value,null);
							if(binCount>=TREEIFY_SHRESHOLD-1) {                   //������֮����Ҫ�ж��Ƿ�Ҫ��������ִ��treeifyBin����
								treeifyBin(tab,hash);
							}
							break;
						}
					}
				}
			}
		}
	}*/
	
	
	//3. ConcurrentHashMap��Դ�뼰ʵ��
	//JAVA7������segment���鸺�������ReentrantLock����ÿ�ν�����һ���̲߳��������飩��Ȼ��ÿ��segment�����HashEntry�����൱��һ��HashMap���������������
	//JAVA8��ֱ���õ�Node����+����/�������ʵ�ֵģ�����ȷ��Node����λ��֮�����������Ҫ����key,�õ���CAS���룻
	//Ȼ������ͻ����Ҫ��������ڵ�������ڵ㣬�õ���synchronized�����ѷ���Ĳ���ȫ��ס���ɡ�
	
	//�ⲿ�־���Դ��̫���ˣ�ֻ���˿��ص�ġ�����λ�á�������Ҫ������Դ���Ķ���

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
