package work;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;
import java.lang.reflect.Array;

public class solveString {
	
	//�ַ����ܽ᣺
	//1. �ص��⣺�����Ӵ����������Ե�ǰ�ַ�/��λΪ�Գ�����������չ��count++��ֱ�����ǻ����Ӵ�/Խ��
	//������huiwen(nums,i,i)+huiwen(nums,i,i+1)
	//2. ����������������01��Ŀ��ͬ�������ֲ����Ӵ���������Ϊ�ǳ��������ֲ��������뵽�˰�ÿһ�θ�����¼����
	//���ڶ�ȡmin��Ӽ���
	//3. Character.isDigit(s[i])�жϵ����ַ��Ƿ�������
	//4. (��������)KMP�㷨
	//5. ������Ӵ���DP����dp[i][j]�Ǵ�i-j�ܷ񹹳ɻ��Ĵ���dp[i][j]=dp[i+1][j-1]&&(s[i]==s[j])
	//������Ŀ�������Ӵ������������ѡȡ������Ĵ����ȣ�������Ӵ�(�Ӵ������ű�������)
	
	
	       //242:�ַ���ͬ
	       //26��Ӣ����ĸ��ֱ������������¼����,�ǳ������ļ�¼��������26��С����������¼
	       public boolean isAnagram(String s, String t) {
		     char[] s1=s.toCharArray();
		     char[] t1=t.toCharArray();
		     if(s1.length!=t1.length) return false;
		     int[] res1=new int[26];
		     int[] res2=new int[26];
		     for(int i=0;i<s1.length;i++) {
			     res1[s1[i]-'a']++;
		     }
		     for(int j=0;j<t1.length;j++) {
			     res2[t1[j]-'a']++;
		    }
		     for(int i=0;i<s1.length;i++) {
			     if(res1[i]!=res2[i]) return false;
		     }
		     return true;
	         }
	
	     //205���ַ���ͬ��
	     //*****�ù�ϣ�����洢ӳ���ϵ
	     //��Ϊ��˫�䣬һһӳ�䣬����Ӧ��ά�����Ź�ϣ��һ����s---t��ӳ�䣬��һ����t----s��ӳ��
	     //����ÿ��λ�ã���ȥ����ϣ��ӳ�䣬����Ƿ���ڳ�ͻ�Լ��Ƿ��һ�γ�����Ҫ��¼
	       public boolean isIsomorphic(String s, String t) {
	    	   HashMap<Character,Character> hash1=new HashMap<>();
	    	   HashMap<Character,Character> hash2=new HashMap<>();
	    	   int len=s.length();
	    	   for(int i=0;i<len;i++) {
	    		   char s1=s.charAt(i);
	    		   char t1=t.charAt(i);
	    		   if(hash1.containsKey(s1)&&hash1.get(s1)!=t1) {
	    			   return false;
	    		   }
	    		   if(hash2.containsKey(t1)&&hash2.get(t1)!=s1) {
	    			   return false;
	    		   }
	    		   hash1.put(s1, t1);
	    		   hash2.put(t1, s1);
	    	   }
	    	   return true;
	       }
	       
	       //647�������Ӵ�������
	       //�������Ӵ�����������������ÿ���ַ�/ÿ����λΪ���ĵĶԳƴ�������
	       //*****���Ĵ����ص㣺ֻ���������ַ�Ϊ�ᣬ�������������ַ���Ŀ�Ϊ��
	       //������չ������ÿ���ַ�/ÿ����λ�������ַ��м�գ�Ϊ�Գ��ᣬ������������չ��ͳ�ƻ����Ӵ�������
	       public int countSubstrings(String s) {
	    	   int n=s.length();
	    	   int count=0;
	    	   for(int i=0;i<n;i++) {
	    		   count+=huiWen(s,i,i);                    //���ַ�Ϊ����չ����¼
	    		   count+=huiWen(s,i,i+1);                  //�Կ�λΪ����չ����¼
	    	   }
	    	   return count;   	  
	       }
	       
	       public int huiWen(String s,int left,int right) {
	    	   int count=0;
	    	   while(left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)) {
	    		   count++;
	    		   left--;
	    		   right++;
	    	   }
	    	   return count;
	       }
	       
	       //696��01��Ŀ��ͬ�Ӵ�����
	       //��������easy��ʵ���Ϸǳ����
	       //�����ﻹ�и��޶����Ӵ���0��1ֻ���ǳ��������ֲ��ģ���00110011���Ϸ��ģ���Ϊ0��1Ҫ�ֿ��������������У��м���ǲ��е�
	       //Ҫ���Ӵ���01��Ŀ��ͬ�ҳ��������ֲ���
	       //�ֶμ�����������count���飬��¼��1��0ÿ�ε���Ŀ��Ȼ�����ڶ�ȡmin��Ϊ��Ŀ��ͬ���Ӵ�����Ŀ����ΪҪ����ֲ�����������ֶμ����������뵽�ˣ�
	       public int countBinarySubstrings(String s) {
	    	   List<Integer> count=new ArrayList<>();
	    	   int i=0;
	    	   int ans=0;
	    	   while(i<s.length()) {
	    		   char c=s.charAt(i);
	    		   int counts=0;
	    		   while(i<s.length()&&c==s.charAt(i)) {
	    			   counts++;
	    			   i++;   
	    		   }
	    		   count.add(counts);
	    	   }
	    	   for(int j=1;j<count.size();j++) {
	    		   ans+=Math.min(count.get(j), count.get(j-1));
	    	   }
	    	   return ans;
	       }
	       
	       //227������������2
	       //�������ʽ��ģ����⣺˫ջ����һ�����������һ�����������ֻ�е�ջ�����������ȼ�����/���ڵ�ǰ��������ʱ����Ҫ���������㣬Ȼ��ѹ��������С�
           //�����д�����ܶ�ϸ��Ҫע�⣺
	       public static int calculate(String s) {
	    	   Stack<Integer> nums=new Stack<>();
	    	   Stack<Character> ops=new Stack<>();
	    	   s=s.replaceAll(" ", "");                            //*****����replaceAll��������ֱ����Դ�ַ����ϸı䣬������Ҫ������ֵ����s����Ȼs����ı�
	    	   nums.push(0);                                       //һ��ʼѹ��0�����⿪ͷ�Ǹ�������-����Ҫ�������������У�����ֱ����ѹ��0��Ϊ��ʼ������
	    	   HashMap<Character,Integer> priority=new HashMap<>();
	    	   priority.put('-', 1);
	    	   priority.put('+', 1);
	    	   priority.put('*', 2);
	    	   priority.put('/', 2);
	    	   char[] s1=s.toCharArray();
	    	   int n=s.length();
	    	   int i=0;
	    	   while(i<n) {
	    		   if(Character.isDigit(s1[i])) {
	    			   int number=0;
	    			   while(i<n&&Character.isDigit(s1[i])) {
	    				   number=number*10+(s1[i]-'0');
	    				   i++;
	    			   }
	    			   nums.push(number);
	    		   }else {
	    			   while(!ops.isEmpty()) {
	    				   char c=ops.peek();
	    				   if(priority.get(c)>=priority.get(s1[i])) {
	    					   calculate1(nums,c);
	    					   ops.pop();                              //*****����һ��ҪС�ģ�peekֻ��ȡ���ˣ����ǲ�����û�е���ȥ������һ��������pop��ȥ
	    				   }else {
	    					   break;
	    				   }
	    			   }
	    			   ops.push(s1[i]);
	    			   i++;
	    		   }
	    	   }
	    	   while(!ops.isEmpty()) {
	    		   calculate1(nums,ops.pop());
	    	   }
	    	   return nums.peek();
	       }
	       
	       public static void calculate1(Stack<Integer> nums,char c) {
	    	   if(nums.size()<2) return;
	    	   int x=nums.pop();
	    	   int y=nums.pop();
	    	   int res=0;
	    	   if(c=='-') {
	    		   res=y-x; 
	    	   }else if(c=='+') {
	    		   res=y+x;
	    	   }else if(c=='*') {
	    		   res=y*x;
	    	   }else if(c=='/') {
	    		   res=y/x;
	    	   }
	    	   nums.push(res);
	       }
	       
	       //28���ַ���ƥ�䣨������ȥ�о�KMP��next���飩
	       //�����KMP�㷨
	       //KMP����ĵ���ʵ���ǣ�next()���飨����ƥ����������Ŵ�ƥ����ַ�����ǰ��׺��ͬ�������
	       //next[i]�����ŵ�i-1(ǰһλ)�����Ӵ����ƥ�䳤��       
	       //�Լ���ǰ����KMP��û�����ο�����Ҫ����
	       //������next����ⷨ�������Ľ�����̫���ˣ���㾡���Թȸ���е�Ϊ׼���������Ǹ��ⷨ�е��������
	       //KMP̫���ˣ�����һ����Сʱ����û�㶨�����ǲ�һ��������͸����KMP�ȹ��������ĸ�������ȥ��KMP
	       
	       
	       //409������Ĵ�����
	       //ͳ�Ƴ��ִ����������=ÿ������/2 *2 + ���ܳ��ֵ�ֻ����һ�εģ�ֻ�ܼ�һ��1;Ҳ����������ǰ��������µ�һ����
	       public int longestPalindrome409(String s) {
	    	   int[] count=new int[58];
	    	   for(int i=0;i<s.length();i++) {
	    		   char c=s.charAt(i);
	    		   count[c-'A']++;
	    	   }
	    	   int ans=0;
	    	   boolean flag=true;
	    	   for(int i=0;i<count.length;i++) {
	    		   ans+=count[i]/2*2;
	    		   if(flag==true&&count[i]%2==1) {
	    			   flag=false;
	    			   ans++;
	    		   }
	    	   }
	    	   return ans;
	       }
	       
	       //3�����ظ��ַ�����ִ�
	       //����Ļ������ڣ�Ȼ���ϣ���¼����
	       //�ҵ���ֹ�����ȴ𰸵�Ҫ�ã��ٶȿ�2ms
	       public int lengthOfLongestSubstring(String s) {
	    	   int n=s.length();
	    	   int left=0;
	    	   int right=-1;
	    	   int ans=0;
	    	   HashSet<Character> hash=new HashSet<>();
	    	   for(;right<n;left++) {                                //*****forѭ����ֹ�����д����left<nҲ���ԣ��������һЩ������Ĺ���
	    		   if(left!=0) {
	    			   hash.remove(s.charAt(left-1));
	    		   }
	    		   while(right+1<n&&!hash.contains(s.charAt(right+1))) {
	    			   hash.add(s.charAt(++right));
	    		   }
	    		   ans=Math.max(ans, right-left+1);                 //�����������ڣ��Ҷ˵㵽��ĩβ����ͳ�ƹ��˵�ʱ��Ӧ��������ֹ�õ������������Ҫbreak
	    		   if(right==n-1) break;                            //*****for��д����right<n,�������Ҫ����right��ĩβʱ��ֱ������ѭ������Ȼ��������ѭ�� 
	    	   }
	    	   return ans;
	       }
	       
	       //772:����ҵhard�⣬����+1������������3
	       //��ԭ����2����һЩ��������%,^,(,);
	       //(ֱ����ջ��)��һֱ�������㣬�������(���Ե�����ֹͣ
	       //*****����ı�����Ŀ���ĳ�������k���Ƶ����㣬���ҷ���k�����ַ���
	       public String calculate772(String s,int k) {
	    	   Stack<Integer> nums=new Stack<>();
	    	   Stack<Character> ops=new Stack<>();
	    	   s=s.replaceAll(" ", "");                            //*****����replaceAll��������ֱ����Դ�ַ����ϸı䣬������Ҫ������ֵ����s����Ȼs����ı�
	    	   nums.push(0);                                       //һ��ʼѹ��0�����⿪ͷ�Ǹ�������-����Ҫ�������������У�����ֱ����ѹ��0��Ϊ��ʼ������
	    	   HashMap<Character,Integer> priority=new HashMap<>();
	    	   priority.put('-', 1);
	    	   priority.put('+', 1);
	    	   priority.put('*', 2);
	    	   priority.put('/', 2);
	    	   priority.put('%', 2);
	    	   priority.put('^', 3);
	    	   char[] s1=s.toCharArray();
	    	   int n=s.length();
	    	   int i=0;
	    	   while(i<n) {
	    		   if(s1[i]=='(') {
	    			   ops.push(s1[i]);
	    		   }else if(s1[i]==')') {
	    			   while(!ops.isEmpty()) {
	    				   if(ops.peek()=='(') {
	    					   ops.pop();
	    					   break;
	    				   }else {
	    					   calculate7721(nums,ops.pop());
	    				   }
	    			   }
	    		   }else if(Character.isDigit(s1[i])||s1[i]=='a'||s1[i]=='b'||s1[i]=='c'||s1[i]=='d'||s1[i]=='e'||s1[i]=='f') {
	    			   int number=0;
	    			   while(i<n&&(Character.isDigit(s1[i])||s1[i]=='a'||s1[i]=='b'||s1[i]=='c'||s1[i]=='d'||s1[i]=='e'||s1[i]=='f')) {
	    				   if(Character.isDigit(s1[i])) {
	    					   number=number*k+(s1[i]-'0');
		    				   i++;
	    				   }
	    				   if(s1[i]=='a'||s1[i]=='b'||s1[i]=='c'||s1[i]=='d'||s1[i]=='e'||s1[i]=='f') {
	    					   number=number*k+(s1[i]-'a'+10);
		    				   i++;
	    				   }
	    			   }
	    			   nums.push(number);
	    		   }else {
	    			   while(!ops.isEmpty()) {
	    				   char c=ops.peek();
	    				   if(priority.get(c)>=priority.get(s1[i])) {
	    					   calculate7721(nums,c);
	    					   ops.pop();                              //*****����һ��ҪС�ģ�peekֻ��ȡ���ˣ����ǲ�����û�е���ȥ������һ��������pop��ȥ
	    				   }else {
	    					   break;
	    				   }
	    			   }
	    			   ops.push(s1[i]);
	    			   i++;
	    		   }
	    	   }
	    	   while(!ops.isEmpty()) {
	    		   calculate7721(nums,ops.pop());
	    	   }
	    	   
	    	   return convertToBase(nums.peek(),k);
	       }
	       
	       public void calculate7721(Stack<Integer> nums,char c) {
	    	   if(nums.size()<2) return;
	    	   int x=nums.pop();
	    	   int y=nums.pop();
	    	   int res=0;
	    	   if(c=='-') {
	    		   res=y-x; 
	    	   }else if(c=='+') {
	    		   res=y+x;
	    	   }else if(c=='*') {
	    		   res=y*x;
	    	   }else if(c=='/') {
	    		   res=y/x;
	    	   }else if(c=='%') {
	    		   res=y%x;
	    	   }else if(c=='^') {
	    		   res=(int)Math.pow(y, x);
	    	   }
	    	   nums.push(res);
	       }
	       
	       public String convertToBase(int num,int k) {
	           if(num==0) return "0";
	   		StringBuilder res=new StringBuilder();
	   		boolean flag=false;
	   		if(num<0) {
	   			flag=true;
	   			num=-num;
	   		}
	   		while(num!=0) {
	   			int a=num/k;
	   			int b=num%k;
	   			if(b>=0&&b<=9) {
		   			res.append(b);
	   			}
	   			if(b==10) {
	   				res.append("a");
	   			}
	   			if(b==11) {
	   				res.append("b");
	   			}
	   			if(b==12) {
	   				res.append("c");
	   			}
	   			if(b==13) {
	   				res.append("d");
	   			}
	   			if(b==14) {
	   				res.append("e");
	   			}
	   			if(b==15) {
	   				res.append("f");
	   			}

	   			num=a;
	   		}
	   		if(flag==true) {
	   			res.append('-');
	   		}
	   		return res.reverse().toString();
	       }
	       
	       //5��������Ӵ���
	       //�����dp�ⷨ��dp[i][j]=dp[i+1][j-1]&&s[i]==s[j]
	       //��ֵ��dp[i][i]=true  dp[i][j](j-i=1)=    s[i]==s[j]?
	       public String longestPalindrome(String s) {
	    	   int len=s.length();
	    	   if(len<2) return s;
	    	   boolean[][] dp=new boolean[len][len];
	    	   for(int i=0;i<len;i++) {
	    		   dp[i][i]=true;
	    	   }
	    	   int maxlen=1;                                          //*****С�ĵ㣬���������1����Ϊ����û�п��ǵ������ַ��������ab��,��ͳ��ȳ�ʼ��Ϊ1
	    	   int begin=0;
	    	   char[] str=s.toCharArray();
	    	   for(int j=1;j<len;j++) {
	    		   for(int i=0;i<j;i++) {
	    			   if(str[i]!=str[j]) {
	    				   dp[i][j]=false;
	    			   }else {
	    				   if(j-i<3) {                                 //���дj-i<3,����Ϊ��aba,aa�����֣�������β��ͬ��dp����Ϊtrue,ʵ��������дj-i==1Ҳ����
	    					   dp[i][j]=true;                          //�൱�ڳ���Ϊ3��һ������һ�¶���
	    				   }else {
	    					   dp[i][j]=dp[i+1][j-1];
	    				   }
	    			   }
	    			   if(dp[i][j]&&j-i+1>maxlen) {
	    				   maxlen=j-i+1;
	    				   begin=i;	   
	    			   }   
	    		   }
	    	   }
               return s.substring(begin, begin+maxlen);
	       }
	       
	       //
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       public static void main(String[] args) {
	    	   String s="af00+ff";
	    	   System.out.print(s);
	       }
	       
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
