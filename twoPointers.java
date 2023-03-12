package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

public class twoPointers {
	
	//双指针，简单的依次比较，复杂的结合hashmap记录次数，最新位置，进行判断更新。
	
	//*****小总结：
	//1. Math.sqrt()根号，Math.abs()求绝对值
	//2. 最好注意一下：考虑溢出问题：int的范围是-2^31-2^31-1,但是取sqrt再平方，可能会大于2^31,导致溢出，所以用long.
	//3. 字典序：从首字母开始比较，字母小的就是小，比如a<b,字母相同继续判断下一个字母。java中的简单字典序判别：s.compareTo(t)<0就是字典序小
	//JAVA中string类型重写过了compareTo函数，就是字典序排序，<0就是字典序小
	//4. 字符串S删除字母后等于另一个字符串T，直接两个指针依次比较并删除即可，不用担心顺序和出现次数的问题。
	//因为两个串依次顺序比较，已经确定了顺序问题，即使后面重复了，也没必要计入了。依次比较就是顺序，所以不用再考虑重复问题。
	//5. hashmap.size(),hashmap.values()得到所有的value值的set集合，Collections.min求数组最小值。因为得到的是位置，所以
	//直接删除该位置在字符串中的字符，即删掉了key。---最后一个hard题目
	//6. **遍历哈希表中元素方法二：for(Map.Entry< , > entry: hashmap.entrySet){ entry.getKey  entry.getValue  }
	//省去了iterator迭代器，直接取出entrySet来使用即可
	
	
	//*****双指针出题方向总结：
	//题目一般集中在两个：数组遍历，字符串的遍历，匹配，简单题非常easy
	//1. 方向同，在不同数组/字符串上，直接挨个判断即可
	//2. 方向相反，在同一串上，即首尾判断
	//3. 方向同，在同一串上，即滑动窗口
	//4. 逆向思维，从末尾开始双指针
	
	//*****双指针的难题hard题：一般集中在字符串的滑动窗口上（普通的题目直接两个串依次比较即可）
	//这种题最难的不是双指针，而是如何使用hashmap来解题，一般分为两种
	//1. hashmap，key为字符，value为出现的次数。用次数来解题(大部分字符串滑动窗口的题目都是用记录次数来解题)
	//2. hashmap，key为字符，value为最新出现的位置。用位置来解题(很少见，除了至多K字符的最长字串)
	
	//*****环形链表知识点：
	//二次相遇法(第一次步长为2和1，第二次步长都是1，并且第二次新指针从头开始运动，slow指针接着走)
	//*****计算核心在于设出三个距离，二次相遇即为环口
	//1. 有环必然相遇：假设slow刚进入环的时候两个指针相距为m,那么接下来运动，2t-t=m即可相遇
	//2. 如何判断环的入口：假设开始点到环入口距离为a,第一次环内相遇，距离入口顺时针距离为b,逆时针距离为c，
	//那么可以得到：(a+c)*2=a+c+k(b+c)  推出a=(k-1)(b+c)+b说明距离a等于了环内k-1圈再加上顺时针到环口的距离
	//所以我们将新的节点从出发点开始，步长为1，那么a时间后，slow走了k-1圈+b到了入口处,新节点也刚好到入口，相遇点即为环口
	
	
	//*****最小覆盖字串总结：比较重点的题目
	//1. 因为允许重复字符，所以需要两个HashMap,tmap,smap,存字符和出现次数
	//2. 算法核心流程：滑动窗口，start在头，end从-1开始加入元素，每次加入都计入smap,并且check()T是否已经满足S
	//如果满足了，那就需要判断是否是满足且当前最小的串，需要start右移，start++继续check()能否满足且更小。满足就
	//继续缩小区间，不满足则扩大end继续寻求新的满足区间。
	//3. check()函数判断满足：因为我们需要满足tmap的所有key和value,所以需要读取整个键值对。这时候就需要迭代器Iterator
	//*****通过hashmap.entrySet().iterator()可以获取迭代器，hashmap的entrySet()函数的iterator()函数获取迭代器
	//迭代器默认从空开始，他的下一个就是第一个key,value元素；迭代器
	//末尾就是最后一个元素，所以判断是否迭代到尾部，用iterator.hasNext().初始iter.next()就是第一个entry
	//迭代器封装了键值对，最好强转一下，通过(Map.Entry)iter.next()
	//之后就可以用entry.getKey() entry.getValue()获取当次的键和值，最好都强转一下转为需要的特定类型
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
	
	
	
	//167：有序数组的两数之和2
	//和之前的没太大区别，已经排好序了，更简单
	//只需要注意一个计数问题，题目中数组从1开始记录，实际上int[]从0开始，所以取得下标后直接+1即是最终答案
	  public int[] twoSum(int[] numbers, int target) {
		  int i=0;
		  int j=numbers.length-1;
		  int[] res=new int[2];
		  while(i<j) {
			  if(numbers[i]+numbers[j]==target) {
				  res[0]=i+1;                                    //下标+1.完成从1开始计数
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
	  
	  //*****反指针，从后向前的双指针，逆向思维
	  
	  //88：归并有序数组
	  //题目写起来很简单，比较有意思的是用O（1）空间来实现。因为nums1已经预留好了空间存nums2元素，所以直接用反指针，从后向前，大的存入nums1的最后
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
	  
	  //142：环形链表题
	  //floyd判圈法：重点在于理解和计算这个算法：最主要的就是一个计算过程
	  //1. fast步长为2，slow步长为1，有环肯定相遇，因为在环中：2t-t=@x(@是德尔塔)t=@x即相遇，在环中，初始距离@x不是问题，一定会相遇;没环的话，fast一定先到null
	  //2. 环中相遇时，slow的路程是a+b,fast的路程是a+n(b+c)+b(a是到环入口的距离，b是相遇点离环口距离，c是环长-b)
	  //因为fast的路程是slow的二倍，所以推出a=(n-1)(b+c)+c,物理意义：代表环前路程=n-1圈的环，加上距离c
	  //3. 让slow继续运动，并且从起始点开始一个新的指针pointer,那么pointer走过a到达入口处，slow走过的就是c+(n-1)圈，刚好也是入口处，即可得到入口是相遇点
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
	        while(fast.next!=null&&fast.next.next!=null) {                          //第一次相遇，判断相遇点和有无环
	        	slow=slow.next;
	        	fast=fast.next.next;
	        	if(slow==fast) break;
	        }                                                                       //千万别忘了，后两个为null也是无环，容易忘掉这个判断。
	        if(fast.next==null||fast.next.next==null) return null;                  //注意下：是||，fast后一个为null或者后两个为null是没有环的表现
	        while(pointer!=slow) {                                                  //pointer从头开始，第二次相遇
	        	slow=slow.next;
	        	pointer=pointer.next;
	        }
	        return slow;
	    }
	   
	   //78：（hard）最小覆盖子串(T中字符允许重复，且S中重复个数必然达到T中个数)
	   //双指针滑动原理不难，主要是两个哈希表判断是否符合条件不是很熟悉。
	   //核心在于两个哈希表存t,和s中滑动窗口的字符，及出现次数。滑动窗口right++，每次滑都更新smap,并判断窗口是否已经满足t,满足的话，缩小left，使得既满足t又能让
	   //窗口长度min；如果不满足，则right继续++；
	   //难点：判断滑动窗口的字符及频度能否满足t的字符及频度，需要用到关于hashmap迭代器
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
        	   tmap.put(t.charAt(i), tmap.getOrDefault(t.charAt(i), 0)+1);      //map键值一起放用put函数，getOrDefault函数挺实用的，要么取值，要么初始化为初始值
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
           return resl==-1?"":s.substring(resl,resr);                          //substring截取，左闭右开，包含左，不包含右
	    }
	  
	   //实际上叫entrySet迭代器
	   public boolean check78() {                                              //用迭代器来遍历hashmap,通过entry获得键值对，然后get键和值
		   Iterator iter=tmap.entrySet().iterator();                           //首先用entrySet.iterator获取迭代器，初始为空
		   while(iter.hasNext()) {
			   Map.Entry entry=(Map.Entry) iter.next();                        //iter.next获取下一个，entry.getKey获取键
			   Character key=(Character)entry.getKey();
			   Integer val=(Integer)entry.getValue();
			   if(smap.getOrDefault(key, 0)<val) {
				   return false;
			   }
		   }
		   return true;
	   }
	   
	   //633：平方数之和
	   //两数之和的变种，实际上和它思想一样，双指针对着遍历
	   //注意两个点：
	   //考虑溢出问题：int的范围是-2^31-2^31-1,但是取sqrt再平方，可能会大于2^31,导致溢出，所以用long
	   //j的范围不用从c开始，直接从sqrt(c)开始
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
	   
	   //680：回文串判断
	   //最简单粗暴的：先判断不删除能否构成回文串；删除一个(for遍历)能否构成回文串。这里删除一个，用substring取子串拼接方法来实现
	   //巧妙点的：直接判断前后是否等，如果不等，有一次删除末尾或者开头的机会，然后继续判断首尾是否相等。不等直接false。等true.
	   //相当于有一次试错的机会，可以删除开头或者末尾，然后继续判断即可。代码两个函数差不多基本
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
	   
	   //524：判别子序列问题
	   //两个指针指在了不同串上，实际上比较easy;主要是不太懂字典序，按字母大小比较，从头到尾，相等则下一个
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
                			   if(dic.length()>res.length()||(dic.length()==res.length()&&dic.compareTo(res)<0)) {  //java中字典序判别，直接用compareTo
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
	   
	   //340：（hard,理解,但本题不难其实）至多包含K个不同字符的最长子串
	   //难点：用hashmap来存一个滑动窗口内不同字符，且记录该字符最新出现的位置。因为如果hashmap中字符数>k,我们需要删除一个旧的字符，根据滑动窗口
	   //我们肯定删除离的最远的那个，即最近最久未出现的字符。
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
			   if(map.size()>k) {                            //hashmap.size得到大小
				   int min=Collections.min(map.values());    //用Collections的min方法，得到最小value
				   map.remove(s.charAt(min));
				   left=min+1;
			   }
			   ans=Math.max(ans, right-left+1);
		   }
		   return ans;
	   }
	
	   
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
