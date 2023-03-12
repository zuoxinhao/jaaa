package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

public class twoPointers {
	
	//˫ָ�룬�򵥵����αȽϣ����ӵĽ��hashmap��¼����������λ�ã������жϸ��¡�
	
	//*****С�ܽ᣺
	//1. Math.sqrt()���ţ�Math.abs()�����ֵ
	//2. ���ע��һ�£�����������⣺int�ķ�Χ��-2^31-2^31-1,����ȡsqrt��ƽ�������ܻ����2^31,���������������long.
	//3. �ֵ��򣺴�����ĸ��ʼ�Ƚϣ���ĸС�ľ���С������a<b,��ĸ��ͬ�����ж���һ����ĸ��java�еļ��ֵ����б�s.compareTo(t)<0�����ֵ���С
	//JAVA��string������д����compareTo�����������ֵ�������<0�����ֵ���С
	//4. �ַ���Sɾ����ĸ�������һ���ַ���T��ֱ������ָ�����αȽϲ�ɾ�����ɣ����õ���˳��ͳ��ִ��������⡣
	//��Ϊ����������˳��Ƚϣ��Ѿ�ȷ����˳�����⣬��ʹ�����ظ��ˣ�Ҳû��Ҫ�����ˡ����αȽϾ���˳�����Բ����ٿ����ظ����⡣
	//5. hashmap.size(),hashmap.values()�õ����е�valueֵ��set���ϣ�Collections.min��������Сֵ����Ϊ�õ�����λ�ã�����
	//ֱ��ɾ����λ�����ַ����е��ַ�����ɾ����key��---���һ��hard��Ŀ
	//6. **������ϣ����Ԫ�ط�������for(Map.Entry< , > entry: hashmap.entrySet){ entry.getKey  entry.getValue  }
	//ʡȥ��iterator��������ֱ��ȡ��entrySet��ʹ�ü���
	
	
	//*****˫ָ����ⷽ���ܽ᣺
	//��Ŀһ�㼯��������������������ַ����ı�����ƥ�䣬����ǳ�easy
	//1. ����ͬ���ڲ�ͬ����/�ַ����ϣ�ֱ�Ӱ����жϼ���
	//2. �����෴����ͬһ���ϣ�����β�ж�
	//3. ����ͬ����ͬһ���ϣ�����������
	//4. ����˼ά����ĩβ��ʼ˫ָ��
	
	//*****˫ָ�������hard�⣺һ�㼯�����ַ����Ļ��������ϣ���ͨ����Ŀֱ�����������αȽϼ��ɣ�
	//���������ѵĲ���˫ָ�룬�������ʹ��hashmap�����⣬һ���Ϊ����
	//1. hashmap��keyΪ�ַ���valueΪ���ֵĴ������ô���������(�󲿷��ַ����������ڵ���Ŀ�����ü�¼����������)
	//2. hashmap��keyΪ�ַ���valueΪ���³��ֵ�λ�á���λ��������(���ټ�����������K�ַ�����ִ�)
	
	//*****��������֪ʶ�㣺
	//����������(��һ�β���Ϊ2��1���ڶ��β�������1�����ҵڶ�����ָ���ͷ��ʼ�˶���slowָ�������)
	//*****���������������������룬����������Ϊ����
	//1. �л���Ȼ����������slow�ս��뻷��ʱ������ָ�����Ϊm,��ô�������˶���2t-t=m��������
	//2. ����жϻ�����ڣ����迪ʼ�㵽����ھ���Ϊa,��һ�λ����������������˳ʱ�����Ϊb,��ʱ�����Ϊc��
	//��ô���Եõ���(a+c)*2=a+c+k(b+c)  �Ƴ�a=(k-1)(b+c)+b˵������a�����˻���k-1Ȧ�ټ���˳ʱ�뵽���ڵľ���
	//�������ǽ��µĽڵ�ӳ����㿪ʼ������Ϊ1����ôaʱ���slow����k-1Ȧ+b������ڴ�,�½ڵ�Ҳ�պõ���ڣ������㼴Ϊ����
	
	
	//*****��С�����ִ��ܽ᣺�Ƚ��ص����Ŀ
	//1. ��Ϊ�����ظ��ַ���������Ҫ����HashMap,tmap,smap,���ַ��ͳ��ִ���
	//2. �㷨�������̣��������ڣ�start��ͷ��end��-1��ʼ����Ԫ�أ�ÿ�μ��붼����smap,����check()T�Ƿ��Ѿ�����S
	//��������ˣ��Ǿ���Ҫ�ж��Ƿ��������ҵ�ǰ��С�Ĵ�����Ҫstart���ƣ�start++����check()�ܷ������Ҹ�С�������
	//������С���䣬������������end����Ѱ���µ��������䡣
	//3. check()�����ж����㣺��Ϊ������Ҫ����tmap������key��value,������Ҫ��ȡ������ֵ�ԡ���ʱ�����Ҫ������Iterator
	//*****ͨ��hashmap.entrySet().iterator()���Ի�ȡ��������hashmap��entrySet()������iterator()������ȡ������
	//������Ĭ�ϴӿտ�ʼ��������һ�����ǵ�һ��key,valueԪ�أ�������
	//ĩβ�������һ��Ԫ�أ������ж��Ƿ������β������iterator.hasNext().��ʼiter.next()���ǵ�һ��entry
	//��������װ�˼�ֵ�ԣ����ǿתһ�£�ͨ��(Map.Entry)iter.next()
	//֮��Ϳ�����entry.getKey() entry.getValue()��ȡ���εļ���ֵ����ö�ǿתһ��תΪ��Ҫ���ض�����
	/*
	  public boolean check() {                                              
		   Iterator iter=tmap.entrySet().iterator();                           
		   while(iter.hasNext()) {
			   Map.Entry entry=(Map.Entry) iter.next();                        
			   Character key=(Character)entry.getKey();
			   Integer val=(Integer)entry.getValue();
			   if(smap.getOrDefault(key, 0)<val) {
				   return false;
			   }
		   }
		   return true;
	   }
	 */
	
	
	
	//167���������������֮��2
	//��֮ǰ��û̫�������Ѿ��ź����ˣ�����
	//ֻ��Ҫע��һ���������⣬��Ŀ�������1��ʼ��¼��ʵ����int[]��0��ʼ������ȡ���±��ֱ��+1�������մ�
	  public int[] twoSum(int[] numbers, int target) {
		  int i=0;
		  int j=numbers.length-1;
		  int[] res=new int[2];
		  while(i<j) {
			  if(numbers[i]+numbers[j]==target) {
				  res[0]=i+1;                                    //�±�+1.��ɴ�1��ʼ����
				  res[1]=j+1;
				  return res;
			  }else if(numbers[i]+numbers[j]>target) {
				  j--;
			  }else {
				  i++;
			  }
		  }
		  return res;
	    }
	  
	  //*****��ָ�룬�Ӻ���ǰ��˫ָ�룬����˼ά
	  
	  //88���鲢��������
	  //��Ŀд�����ܼ򵥣��Ƚ�����˼������O��1���ռ���ʵ�֡���Ϊnums1�Ѿ�Ԥ�����˿ռ��nums2Ԫ�أ�����ֱ���÷�ָ�룬�Ӻ���ǰ����Ĵ���nums1�����
	  public void merge(int[] nums1, int m, int[] nums2, int n) {
		  int k=m+n-1;
		  int i=m-1;
		  int j=n-1;
		  while(i>=0&&j>=0) {
			  if(nums1[i]>=nums2[j]) {
				  nums1[k]=nums1[i];
				  i--;
				  k--;
			  }else {
				  nums1[k]=nums2[j];
				  j--;
				  k--;
			  }
		  }
		  while(i>=0) {
			  nums1[k--]=nums1[i--];
		  }
		  while(j>=0) {
			  nums1[k--]=nums2[j--];
		  }
	    }
	  
	  //142������������
	  //floyd��Ȧ�����ص��������ͼ�������㷨������Ҫ�ľ���һ���������
	  //1. fast����Ϊ2��slow����Ϊ1���л��϶���������Ϊ�ڻ��У�2t-t=@x(@�ǵ¶���)t=@x���������ڻ��У���ʼ����@x�������⣬һ��������;û���Ļ���fastһ���ȵ�null
	  //2. ��������ʱ��slow��·����a+b,fast��·����a+n(b+c)+b(a�ǵ�����ڵľ��룬b���������뻷�ھ��룬c�ǻ���-b)
	  //��Ϊfast��·����slow�Ķ����������Ƴ�a=(n-1)(b+c)+c,�������壺����ǰ·��=n-1Ȧ�Ļ������Ͼ���c
	  //3. ��slow�����˶������Ҵ���ʼ�㿪ʼһ���µ�ָ��pointer,��ôpointer�߹�a������ڴ���slow�߹��ľ���c+(n-1)Ȧ���պ�Ҳ����ڴ������ɵõ������������
	   class ListNode {
		       int val;
		       ListNode next;
		       ListNode(int x) {
		           val = x;
		           next = null;
		       }
		   }
	   public ListNode detectCycle(ListNode head) {
		    if(head==null) return null;
	        ListNode fast=head;
	        ListNode slow=head;
	        ListNode pointer=head;
	        while(fast.next!=null&&fast.next.next!=null) {                          //��һ���������ж�����������޻�
	        	slow=slow.next;
	        	fast=fast.next.next;
	        	if(slow==fast) break;
	        }                                                                       //ǧ������ˣ�������ΪnullҲ���޻���������������жϡ�
	        if(fast.next==null||fast.next.next==null) return null;                  //ע���£���||��fast��һ��Ϊnull���ߺ�����Ϊnull��û�л��ı���
	        while(pointer!=slow) {                                                  //pointer��ͷ��ʼ���ڶ�������
	        	slow=slow.next;
	        	pointer=pointer.next;
	        }
	        return slow;
	    }
	   
	   //78����hard����С�����Ӵ�(T���ַ������ظ�����S���ظ�������Ȼ�ﵽT�и���)
	   //˫ָ�뻬��ԭ���ѣ���Ҫ��������ϣ���ж��Ƿ�����������Ǻ���Ϥ��
	   //��������������ϣ���t,��s�л������ڵ��ַ��������ִ�������������right++��ÿ�λ�������smap,���жϴ����Ƿ��Ѿ�����t,����Ļ�����Сleft��ʹ�ü�����t������
	   //���ڳ���min����������㣬��right����++��
	   //�ѵ㣺�жϻ������ڵ��ַ���Ƶ���ܷ�����t���ַ���Ƶ�ȣ���Ҫ�õ�����hashmap������
	   Map<Character,Integer>  tmap=new HashMap<>();
       Map<Character,Integer>  smap=new HashMap<>();
	   public String minWindow(String s, String t) {        
           int r=-1;                
           int l=0;
           int resl=-1;
           int resr=-1;
           int minlen=Integer.MAX_VALUE;
           for(int i=0;i<t.length();i++) {
        	   //hashmap.getOrDefault(key,default value)
        	   tmap.put(t.charAt(i), tmap.getOrDefault(t.charAt(i), 0)+1);      //map��ֵһ�����put������getOrDefault����ͦʵ�õģ�Ҫôȡֵ��Ҫô��ʼ��Ϊ��ʼֵ
           }
           while(r<s.length()) {
        	   r++;
        	   if(r<s.length())
        	   smap.put(s.charAt(r),smap.getOrDefault(s.charAt(r), 0)+1);
        	   while(check78()&&l<=r) {
        		   if(r-l+1<minlen) {
        			   resl=l;
        			   resr=r+1;
        			   minlen=r-l+1;
        		   }
        		   smap.put(s.charAt(l), smap.getOrDefault(s.charAt(l), 0)-1);
        		   l++; 
        	   }
        	   
           }
           return resl==-1?"":s.substring(resl,resr);                          //substring��ȡ������ҿ��������󣬲�������
	    }
	  
	   //ʵ���Ͻ�entrySet������
	   public boolean check78() {                                              //�õ�����������hashmap,ͨ��entry��ü�ֵ�ԣ�Ȼ��get����ֵ
		   Iterator iter=tmap.entrySet().iterator();                           //������entrySet.iterator��ȡ����������ʼΪ��
		   while(iter.hasNext()) {
			   Map.Entry entry=(Map.Entry) iter.next();                        //iter.next��ȡ��һ����entry.getKey��ȡ��
			   Character key=(Character)entry.getKey();
			   Integer val=(Integer)entry.getValue();
			   if(smap.getOrDefault(key, 0)<val) {
				   return false;
			   }
		   }
		   return true;
	   }
	   
	   //633��ƽ����֮��
	   //����֮�͵ı��֣�ʵ���Ϻ���˼��һ����˫ָ����ű���
	   //ע�������㣺
	   //����������⣺int�ķ�Χ��-2^31-2^31-1,����ȡsqrt��ƽ�������ܻ����2^31,���������������long
	   //j�ķ�Χ���ô�c��ʼ��ֱ�Ӵ�sqrt(c)��ʼ
	   public boolean judgeSquareSum(int c) {
           long i=0;
           long j=(long)Math.sqrt(c);
           while(i<=j) {
        	   if(i*i+j*j==c) {
        		   return true;
        	   }else if(i*i+j*j>c) {
        		   j--;
        	   }else {
        		   i++;
        	   }
           }
           return false;
	    }
	   
	   //680�����Ĵ��ж�
	   //��򵥴ֱ��ģ����жϲ�ɾ���ܷ񹹳ɻ��Ĵ���ɾ��һ��(for����)�ܷ񹹳ɻ��Ĵ�������ɾ��һ������substringȡ�Ӵ�ƴ�ӷ�����ʵ��
	   //�����ģ�ֱ���ж�ǰ���Ƿ�ȣ�������ȣ���һ��ɾ��ĩβ���߿�ͷ�Ļ��ᣬȻ������ж���β�Ƿ���ȡ�����ֱ��false����true.
	   //�൱����һ���Դ�Ļ��ᣬ����ɾ����ͷ����ĩβ��Ȼ������жϼ��ɡ�������������������
	   public boolean validPalindrome(String s) {
		   int i=0;
		   int j=s.length()-1;
		   while(i<=j) {
			   if(s.charAt(i)==s.charAt(j)) {
				   i++;
				   j--;
			   }else {
				   return validDeleteOne(s,i,j-1)||validDeleteOne(s,i+1,j);
			   }
		   }
		   return true;
	    }
	     
	   public boolean validDeleteOne(String s,int head,int tail) {
		   while(head<=tail) {
			   if(s.charAt(head)!=s.charAt(tail)) {
				   return false;
			   }
			   head++;
			   tail--;
		   }
		   return true;
	   }
	   
	   //524���б�����������
	   //����ָ��ָ���˲�ͬ���ϣ�ʵ���ϱȽ�easy;��Ҫ�ǲ�̫���ֵ��򣬰���ĸ��С�Ƚϣ���ͷ��β���������һ��
	   public String findLongestWord(String s, List<String> dictionary) {
               int i=0;
               int j=0;
               String res="";
               String dic;
               for(int k=0;k<dictionary.size();k++) {
            	   dic=dictionary.get(k);
            	   i=0;
            	   j=0;
            	   while(i<s.length()&&j<dic.length()) {
                	   if(s.charAt(i)==dic.charAt(j)) {
                		   i++;
                		   j++;
                		   if(j==dic.length()) {
                			   if(dic.length()>res.length()||(dic.length()==res.length()&&dic.compareTo(res)<0)) {  //java���ֵ����б�ֱ����compareTo
                				   res=dic;
                				   }
                			   }
                	   }else {
                		   i++;
                	   }
                   }
               }
              return res;
	    }
	   
	   //340����hard,���,�����ⲻ����ʵ���������K����ͬ�ַ�����Ӵ�
	   //�ѵ㣺��hashmap����һ�����������ڲ�ͬ�ַ����Ҽ�¼���ַ����³��ֵ�λ�á���Ϊ���hashmap���ַ���>k,������Ҫɾ��һ���ɵ��ַ������ݻ�������
	   //���ǿ϶�ɾ�������Զ���Ǹ�����������δ���ֵ��ַ���
	   public int lengthOfLongestSubstringKDistinct(String s, int k) {
		   int len=s.length();
		   if(len*k==0) return 0;
		   int left=0;
		   int right=0;
		   int ans=0;
		   Map<Character,Integer> map=new HashMap<>();
		   while(right<len) {
			   map.put(s.charAt(right),right);
			   right++;
			   if(map.size()>k) {                            //hashmap.size�õ���С
				   int min=Collections.min(map.values());    //��Collections��min�������õ���Сvalue
				   map.remove(s.charAt(min));
				   left=min+1;
			   }
			   ans=Math.max(ans, right-left+1);
		   }
		   return ans;
	   }
	
	   
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
