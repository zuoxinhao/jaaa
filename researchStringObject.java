package work;
import java.util.*;
import java.util.stream.Collectors;

import javax.xml.soap.Node;

import java.lang.*;
import java.lang.reflect.Array;

public class researchStringObject {
	
	
	
	
	//1. Integer,Character,Boolean��װ��Ļ������(Դ�뼰ʵ��)
	//��װ���û�����ƣ����飩��ʵ�ָ����ܣ�Integer�໺��[-128,127],Character����[0,127],Boolean���ͻ���true,false.
	//cache����ע���£��±�-low��ʼ����valueOf��������
	//*****��Ϊ�л�����ƣ�����[-128,127]֮������ֽ���Integer��ʼ��ʱ�򣬵õ�����ͬһ����;������Χ�ľͲ���ͬһ����
	//*****ʹ��new Integer ����ʹ�û������飬����ֱ�Ӵ���һ��Integer����
	//*****Integer���͵�equals����������ֱ���ж�ֵ���������ж��Ƿ���ͬһ��������Integer���͵�ֵ�жϣ�����equals��������==��==������Χ�޷�����ֵ�жϣ�תΪ�˶����жϣ�
	//���԰�װ����ж�һ�㶼��equals��������==
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
		return i.equals(j);       //true��equals����ֻ�ж�ֵ�Ƿ���ͬ�����ֶ���
	}
	
	/*��Դ�룩public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];     //Ϊʲô�Ǽ��ţ���Ϊcache�����������涨��ľ���-low.
        return new Integer(i);
    }
    private static class IntegerCache {
    static final int low = -128;
    static final int high = 0;    //ʵ����û�г�ʼֵ0����ӵģ���Ȼ����
    static {
        int high = 127;
    }
    static int[] cache=new int[high-low+1]; 
    }*/
	
	public static void main(String[] args) {
		System.out.print(testInteger4());
	}
	
	
    //2. Object��һЩԴ�뷽��
	//����Ҫ�ľ���������hashcode(),equals()
	//object�ĳ�ʼequals�ǱȽ����������ַ�Ƿ���ͬ��String������д����equals�������ж��ַ���ֵ�Ƿ���ͬ��Integerҳ��д����equals������ж�ֵ�Ƿ���ͬ
	//*****==�����ڻ�����������û�е�ַ��ֻ���ж�ֵ�������������ͣ��жϵ�ַ
	//*****equalsû��д�Ļ����жϵ�Ҳ�ǵ�ַ��������������û��equals����
	//*****�ܽ᣺˵���ˣ�==��equals�����жϵ�ַ�Ƿ���ͬ��������������û�е�ַֻ���ж�ֵ��
	//Integer��String��д��equals�������ж�ֵ������Integer,���ڻ�����ƣ�[-128,127]�ж�==��������ָ��ͬһ�ݶ���
	
	/*��Դ�룩public boolean equals(Object obj) {
	     return (this == obj);
	}
	*/
	
	
	//3. String��Դ��
	//String���ͣ���������private������û�б�¶���ⲿ�޸ģ�����final�����ܱ��̳У���������Ҳ�޷��޸ģ�����String����д��ֵ�Ͳ��ܸı�
	//��+�����ӣ�ʵ�������������µĶ����ڲ�ʵ����ͨ��StringBuilder��append������Ȼ����toString�����¶���
	//����һ����StringBuilder,StringBuffer(�̰߳�ȫ)
	
	
	/*��Դ�룩public final class String implements java.io.Serializable, Comparable<String>, CharSequence {
	    private final char value[];
	}
	*/
	
	
	//4. String�����ַ��������صķ�����
	//*****�����ط�����
	//*****String s="..."  ���������������1. ���д�������s       2. ��s�����÷ŵ��ַ����������У�������������б����и��ַ����Ǿ�ֱ���ڶ��з��ظ��ַ����ü��ɣ�
	//String s=new String("...") ֻ�����һ�����������д����¶���s��������ŵ���������
	//intern():������ַ����ڳ������У�����볣���أ�������ˣ�ֱ�ӷ������ã�����ֵ���������ã�
	String s1="JAVA";
	String s2=s1.intern();                 //��Ϊs1�ĳ�ʼ���Ѿ����볣�����ˣ�����s2�õ�����s1������
	String s3=new String("JAVA");          //�½�һ�����󣬵������볣���أ�   s1!=s3 ��Ϊ�ǲ�ͬ�Ķ�����û�����ù�ϵ�����Բ���
	String s4=s3.intern();                 //s4����JAVA�ĳ��������ã�����s4����s1������
	String s5="JAVA";                      //s5�Ǿ����½�����Ȼ�󷵻ص�������s1
	//�����s1==s2    s3!=s4      s4==s1    s5==s1
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
