package work;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;
import java.lang.reflect.Array;

public class solveString {
	
	//字符串总结：
	//1. 重点题：回文子串的数量：以当前字符/空位为对称轴向两边扩展，count++，直至不是回文子串/越界
	//所以是huiwen(nums,i,i)+huiwen(nums,i,i+1)
	//2. 巧妙：分组计数法，求01数目相同且连续分布的子串个数，因为是成组连续分布，所以想到了把每一段个数记录下来
	//相邻段取min相加即可
	//3. Character.isDigit(s[i])判断单个字符是否是数字
	//4. (保留待做)KMP算法
	//5. 最长回文子串：DP，设dp[i][j]是从i-j能否构成回文串，dp[i][j]=dp[i+1][j-1]&&(s[i]==s[j])
	//三个题目：回文子串的数量，随便选取的最长回文串长度，最长回文子串(子串代表着必须连续)
	
	
	       //242:字符相同
	       //26个英文字母，直接用数组来记录即可,非常常见的记录方法，用26大小的数组来记录
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
	
	     //205：字符串同构
	     //*****用哈希表来存储映射关系
	     //因为是双射，一一映射，所以应该维护两张哈希表，一个是s---t的映射，另一个是t----s的映射
	     //对于每个位置，都去检查哈希表映射，检查是否存在冲突以及是否第一次出现需要记录
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
	       
	       //647：回文子串的数量
	       //求解回文子串的数量，就是求以每个字符/每个空位为中心的对称串的数量
	       //*****回文串的特点：只能以中心字符为轴，或者以中心两字符间的空为轴
	       //中心拓展法：以每个字符/每个空位（两个字符中间空）为对称轴，向左向右逐步扩展，统计回文子串的数量
	       public int countSubstrings(String s) {
	    	   int n=s.length();
	    	   int count=0;
	    	   for(int i=0;i<n;i++) {
	    		   count+=huiWen(s,i,i);                    //以字符为轴扩展并记录
	    		   count+=huiWen(s,i,i+1);                  //以空位为轴扩展并记录
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
	       
	       //696：01数目相同子串计数
	       //名义上是easy，实际上非常巧妙；
	       //条件里还有个限定：子串中0，1只能是成组连续分布的，即00110011不合法的，因为0，1要分开成组且连续才行，有间隔是不行的
	       //要求子串中01数目相同且成组连续分布：
	       //分段计数法：利用count数组，记录下1，0每段的数目，然后相邻段取min即为数目相同的子串的数目（因为要成组分布，所以这个分段计数就容易想到了）
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
	       
	       //227：基本计算器2
	       //算数表达式的模拟求解：双栈法，一个存操作数，一个存操作符；只有当栈顶操作符优先级大于/等于当前操作符的时候，需要弹出并计算，然后压入操作数中。
           //这道题写起来很多细节要注意：
	       public static int calculate(String s) {
	    	   Stack<Integer> nums=new Stack<>();
	    	   Stack<Character> ops=new Stack<>();
	    	   s=s.replaceAll(" ", "");                            //*****这里replaceAll函数不是直接在源字符串上改变，所以需要将返回值赋给s，不然s不会改变
	    	   nums.push(0);                                       //一开始压入0，以免开头是负数，‘-’需要两个操作数才行，所以直接先压入0作为初始操作数
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
	    					   ops.pop();                              //*****这里一定要小心，peek只能取顶端，但是操作符没有弹出去，所以一定别忘了pop出去
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
	       
	       //28：字符串匹配（后续再去研究KMP的next数组）
	       //经典的KMP算法
	       //KMP最核心的其实就是：next()数组（部分匹配表）。代表着待匹配的字符串中前后缀相同的最长长度
	       //next[i]代表着第i-1(前一位)所在子串的最长匹配长度       
	       //自己以前对于KMP就没掌握牢靠，需要提升
	       //三宫的next数组解法和其他的解答相差太大了，晚点尽量以谷歌大佬的为准。三宫的那个解法有点难以理解
	       //KMP太难了，花了一个半小时还是没搞定，还是差一点才能理解透彻；KMP先过，其他的搞完了再去搞KMP
	       
	       
	       //409：最长回文串长度
	       //统计出现次数，最长长度=每个次数/2 *2 + 可能出现的只出现一次的（只能加一次1;也可能来自于前面二分留下的一个）
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
	       
	       //3：无重复字符的最长字串
	       //经典的滑动窗口，然后哈希表记录即可
	       //我的终止条件比答案的要好，速度快2ms
	       public int lengthOfLongestSubstring(String s) {
	    	   int n=s.length();
	    	   int left=0;
	    	   int right=-1;
	    	   int ans=0;
	    	   HashSet<Character> hash=new HashSet<>();
	    	   for(;right<n;left++) {                                //*****for循环终止，如果写的是left<n也可以，但会多做一些无意义的工作
	    		   if(left!=0) {
	    			   hash.remove(s.charAt(left-1));
	    		   }
	    		   while(right+1<n&&!hash.contains(s.charAt(right+1))) {
	    			   hash.add(s.charAt(++right));
	    		   }
	    		   ans=Math.max(ans, right-left+1);                 //即：滑动窗口，右端点到达末尾并且统计过了的时候，应该立即终止得到最后结果，所以要break
	    		   if(right==n-1) break;                            //*****for中写的是right<n,这里必须要加上right到末尾时候，直接跳出循环，不然会陷入死循环 
	    	   }
	    	   return ans;
	       }
	       
	       //772:（企业hard题，题量+1）基本计算器3
	       //比原来的2多了一些操作符：%,^,(,);
	       //(直接入栈，)则一直弹出计算，最后碰到(可以弹出并停止
	       //*****这里改编了题目，改成了任意k进制的运算，并且返回k进制字符串
	       public String calculate772(String s,int k) {
	    	   Stack<Integer> nums=new Stack<>();
	    	   Stack<Character> ops=new Stack<>();
	    	   s=s.replaceAll(" ", "");                            //*****这里replaceAll函数不是直接在源字符串上改变，所以需要将返回值赋给s，不然s不会改变
	    	   nums.push(0);                                       //一开始压入0，以免开头是负数，‘-’需要两个操作数才行，所以直接先压入0作为初始操作数
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
	    					   ops.pop();                              //*****这里一定要小心，peek只能取顶端，但是操作符没有弹出去，所以一定别忘了pop出去
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
	       
	       //5：最长回文子串求串
	       //经典的dp解法：dp[i][j]=dp[i+1][j-1]&&s[i]==s[j]
	       //初值：dp[i][i]=true  dp[i][j](j-i=1)=    s[i]==s[j]?
	       public String longestPalindrome(String s) {
	    	   int len=s.length();
	    	   if(len<2) return s;
	    	   boolean[][] dp=new boolean[len][len];
	    	   for(int i=0;i<len;i++) {
	    		   dp[i][i]=true;
	    	   }
	    	   int maxlen=1;                                          //*****小心点，这里必须是1，因为后续没有考虑到两个字符串情况“ab”,最低长度初始必为1
	    	   int begin=0;
	    	   char[] str=s.toCharArray();
	    	   for(int j=1;j<len;j++) {
	    		   for(int i=0;i<j;i++) {
	    			   if(str[i]!=str[j]) {
	    				   dp[i][j]=false;
	    			   }else {
	    				   if(j-i<3) {                                 //这边写j-i<3,是因为，aba,aa这两种，都是首尾相同则dp数组为true,实际上这里写j-i==1也可以
	    					   dp[i][j]=true;                          //相当于长度为3的一并简化了一下而已
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
