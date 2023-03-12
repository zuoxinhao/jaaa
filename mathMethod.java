package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;


public class mathMethod {
	
	//数学问题总结：
	//1. 辗转相除法：(a,b)=(b,a%b)一直迭代直至除数为0；a和b的最大公因数等于b和a%b的最大公因数一样
	//2. 进制转化问题，别忘了负数，所以要先存符号。while(num!=0) 存商和余数 然后商给num
	//3. *****巧妙的数学题：统计n阶乘尾部0的个数：0--2*5，所以就是统计因子2和5的个数，2的个数必然远大于5，所以就是统计
	//因子5的个数。n/5 统计因子5出现一次的个数，n/25因子25出现一次，5出现两次的个数。又因为统计一次5时候带上了25之类的
	//数字，所以第二次加上n/25时候不需要×2了。所以是递归：return (n==0)?0:n/5+dfs(n/5); n/5+n/25+n/125...
	//4. (很重要)字符串/k进制数相加：加法原则，从末尾开始相加，记录本次值和进位值。**注意一下：字符‘9’想要获取值9，需要：‘9’-‘0’
	//5. *****随机打乱：执行n次，第i个位置，选择i+random.nextInt(n-i)与之交换,即[i,n)；所谓的复原，就是在打乱前copy一次这个数组
	//6. nextInt(n)函数：在[0,n]中随机选取一个数
	//7. 前缀和的作用：简化了空间复杂度，从total+1简化到了w.length
	//8. (了解一下)水塘抽样法：概率1/n随机抽样
	//对于遍历到第i个位置，在[0,i)区间随机抽样，如果是0，那就输出本位置的值(放入ans)；如果不是0，那就下一个
	//9. 进制思想的使用，但是和进制中0-25不同，1-26多了个26少了个0，所以需要额外处理26的k倍即可(余数为0的情况)
	//少了个0无所谓，主要是多了个26,需要我们单独处理余数为0的情况，余数为0转化为z，且除数需要--
	//10. 寻找众数的投票算法：不停的+1，-1，为0就换candidate
	//11. (最重要的)*****拒绝部分采样法：构造randK()函数
	//通用解法：拒绝部分并组合概率
	//随机采样的构造（都是小的构造大的）：将小的分两次（多次）拒绝一部分，取一部分，乘积求得等于抽样概率，然后映射第一个和第二个（多个）到需要的区间即可即可
	//所谓的拒绝一部分，就相当缩减了随机抽样，从rand7---变成了rand6,rand5,rand4...
	//如果概率无法用乘积的话，比如rand11,那就用两个rand7相乘然后选取11个等概率的即可(1-7,2-14,3-21...7-49,挨个算概率找)
	//重点题：rand7--rand10
	//重点代码：while(first==rand7()>2);   while(second==rand7()>5);
	/*public int rand7() {
		  return 0;
	  }
	  public int rand10() {
		  int first=0;
		  int second=0;
		  while((first=rand7())>2);                      //拒绝采样一部分，相当于变成了rand2
		  while((second=rand7())>5);                     //rand5    
		  if(first==1) {
			  return second;
		  }else {
			  return 5+second;
		  } 
	    }*/
	//12. 快乐数：不会无限扩大，就需要用hashset去判断是否会循环。重点就在于可能会循环
	
	
	
	
	//*****数学问题：
	//1. 进制问题的变型
	//2. 随机抽样的变型：拒绝抽样法
	//3. 常用的：前缀和，前缀乘积
	//4. 智力题
	
	
	//204:质数个数
	//1. 最简单的：对于2-n-1里面的每个数，判断是否有除了1和本身之外的其他因子
	//2. 埃斯托妮筛选：对于从2开始的每个数，他的k倍(<n)肯定都是和数，这样就可以用一个boolean数组来记录即可
	//两者的复杂度相差不大，但是第一种解法，第二层遍历必须是j*j<i,控制在n^(3/2)
	public int countPrimes(int n) {
		if(n<=2) return 0;
		int count=n-2;
		for(int i=2;i<n;i++) {
			for(int j=2;j*j<=i;j++) {
				if(i%j==0) {
					count--;
					break;
				}
			}
		}
		return count;
    }
	
	//504:7进制数转换
	//进制转换：余数就放入，商继续除，直至为0
	//负数处理：先变正数再求，最后添上负号即可
	//!!!余数自下向上放置，别忘了
	public String convertToBase7(int num) {
		if(num==0) return "0";
		StringBuilder res=new StringBuilder();
		boolean flag=false;
		if(num<0) {
			flag=true;
			num=-num;
		}
		while(num!=0) {
			int a=num/7;
			int b=num%7;
			res.append(b);
			num=a;
		}
		if(flag==true) {
			res.append('-');
		}
		return res.reverse().toString();
    }
	
	//172：阶乘尾部0的个数
	//巧妙地数学问题：
	//阶乘尾部的0都来自于2*5所得，又因为2因子数量肯定远小于5因子数量，所以转化为了1-n中统计因子5的总个数
	//比如：n=130   数字5统计因子5一次，25统计因子5两次， 125统计因子5三次， 130统计因子5一次
	//结论：n/5=n1 得到一次因子5的数的个数   n1/5=n2 得到两次因子5的个数（因为在第一次n1中已经统计过一次了，所以这里再统计一次即可，直接+上） 
	 public int trailingZeroes(int n) {
		 return n==0?0:n/5+trailingZeroes(n/5);
	    }
	
	//415:字符串相加
	//一种：将每个字符串先转化为整数，相加，然后再用%得到一个个数，用StringBuilder链接即可
	//第二种：加法原则：末尾对齐相加，每次取出最后一位数，相加，得到本次数值以及进位和（add，即相加/10），那么下一个位数相加就需要加上进位和即可
	//解法二：
	 public String addStrings(String num1, String num2) {
		 int len1=num1.length()-1;
		 int len2=num2.length()-1;
		 int add=0;
		 StringBuilder ans=new StringBuilder();
		 while(len1>=0||len2>=0||add>0) {                         //！！！别忘了循环终止还有一个add>0,因为最后只有进位时候，也需要往前放置；用||避免了一个为空的情况，是否为空的处理在循环体中了
			 int x=len1>=0?num1.charAt(len1)-'0':0;               //!!!小心单个字符想要表示单个数的话，需要减去字符0才行
			 int y=len2>=0?num2.charAt(len2)-'0':0;
			 int res=x+y+add;
			 add=res/10;
			 ans.append(res%10);
			 len1--;
			 len2--;
		 }
		 return ans.reverse().toString();
		 }
	 
	 //326：判断3的幂次
	 //不停的除3，看余数是否是0；直至商为1，1%3=1 即当次余数不为0时，对应的除数应该是1
	 public boolean isPowerOfThree(int n) {
		 while(n!=0&&n%3==0) {
			 n/=3;
		 }
		 return n==1;
	    }
	 
	 //384：随机打乱数组
	 //随机打乱：洗牌算法，挺有意思的
	 //洗牌算法：从1-n中随机选取一个数，然后和最开始的位置进行交换，那么2-n位置中就是未打乱的，继续打乱交换第二个位置。。。循环n次即可打乱到最后一个位置
	 //第i个位置，选择i+random.nextInt(n-i)与之交换,即[i,n)
	 //所谓的复原，就是在打乱前copy一次这个数组
	 int[] nums;
	 int[] origin;
	 public void solution(int[] nums) {
		 this.nums=nums;
		 this.origin=new int[nums.length];
		 System.arraycopy(nums, 0, origin, 0, nums.length);
	 }
	 
	 public int[] reset() {
		 System.arraycopy(origin, 0, nums, 0, nums.length);
		 return nums;
	 }
	 
	 public int[] shuffle() {
		 Random random=new Random();                            //随机抽取，用Random类
		 for(int i=0;i<nums.length;i++) {
			 int j=i+random.nextInt(nums.length-i);             //random.nextInt(n):从0-n中随机抽取一个数
			 int temp=nums[i];
			 nums[i]=nums[j];
			 nums[j]=temp;
		 }
		 return nums;
	 }
	 
	 //528：权重随机
	 //给定一个权重数组，按照权重进行随机
	 //我的思路：[3,1]  那我重新建立一个数组，个数是total总和，3--对应把3放三次(应该是下标放三次)，1--对应把元素放一次，这样下来就可以得到按权重建立的新数组，然后随机抽样即可
	 int[] temp528;
	 int total;
	 public void Solution528(int[] w) {
		 for(int i=0;i<w.length;i++) {
			 total+=w[i];
		 }
		 temp528=new int[total];
		 int j=0;
		 for(int i=0;i<w.length;i++) {
			 int count=w[i];
			 for(int k=0;k<count;k++) {
				 temp528[j++]=i;
			 }	 
		 }
	    }
	    
	 public int pickIndex528() {
		 Random random=new Random();
		 int i=random.nextInt(total);
		 return temp528[i];
	    }
	 //超出空间限制：实际要求是最大额外空间为w.length大小的数组。我的是total+1的空间，不符合要求
	 
	 //标准解答：前缀和法：思想和上面我的是一样，就是想象成一段很长的数组，每段部分代表着随机抽样到哪个下标
	 //所以我们寻找每一段的公式：利用前缀和pre[i]表示遍历到w[i]结束所对应的和
	 //找规律可知：下标i对应的段的范围是：pre[i]-w[i]+1 <= i <=pre[i]
	 //简化了空间复杂度
	 //!!!!!注意，这个划分，是从1开始的，1-n区间划分为多少段，随机数也必须在1-n中选择
	 int[] pre;
	 int[] w1;
	 public void Solution5282(int[] w) {
		 this.w1=w;
		 pre=new int[w.length];
		 for(int i=0;i<w.length;i++) {
			 total+=w[i];
		 }
		 pre[0]=w[0];
		 for(int i=1;i<w.length;i++) {
			 pre[i]=pre[i-1]+w[i];
		 }
	 }
	 
	 public int pickIndex5282() {
		 int i=(int)(Math.random()*total)+1;                        //!!!!!Math.random范围是[0,1),乘以total是[0,total)加个1就是[1,total+1)
		 for(int j=0;j<pre.length;j++) {
			 if(i>=pre[j]-w1[j]+1&&i<=pre[j]) {
				 return j;
			 }
		 }
		 return 0;
	 }
	 
	 //382:单链表随机抽样
	 //第一种：直接复制数组中随机抽样，空间复杂度O（n）
	 //第二种：空间复杂度O（1），水塘抽样法：基于概率计算的方法
	 
	 //*****水塘抽样：遍历到第i位置时，保证随机抽样到i的方法是：第i个被选择，且i+1，i+2...均不被选择。
	 //*****：用于不知道总体集合大小，但是仍然可以以1/n的概率随机抽样出某一行
	 //*****证明用下面的公式即可
	 
	 //对于遍历到第i个位置，在[0,i)区间随机抽样，如果是0，那就输出本位置的值；如果不是0，那就输出ans(初始为0)
	 //那么输出i节点值的概率P=P(第i节点抽样到0)*P(第i+1节点抽样不是0)*P(第i+2节点抽样不是0)*P(第i+3节点抽样不是0)...*P(第n节点抽样不是0)
	 //P=1/i*(1-1/(i+1))*(1-1/(i+2))...*(1-1/n)=1/n就是随机抽样的概率。
	  public class ListNode {
		      int val;
		      ListNode next;
		      ListNode() {}
		      ListNode(int val) { this.val = val; }
		      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
		  }
	  ListNode head;
	  Random random;
	  public void Solution382(ListNode head) {
		  this.head=head;
		  random=new Random();
	  }
	
	  public int getRandom() {
		  int i=1;
		  int ans=0;
		  for(ListNode h=head;h!=null;h=h.next) {
			  if(random.nextInt(i)==0) {
				  ans=h.val;
			  }
			  i++;
		  }
		  return ans;
	  }
	  
	  //168：excel列表名称
	  //进制思想的使用，但是和进制中0-n-1不同，这里多了个n少了个0，所以需要额外处理n的k倍即可
	  //少了个0无所谓，主要是多了个n,需要我们单独处理余数为0的情况，商为0转化为z，且除数需要--
	  public String convertToTitle(int num) {
			if(num==0) return "0";
			StringBuilder res=new StringBuilder();
			boolean flag=false;
			if(num<0) {
				flag=true;
				num=-num;
			}
			while(num!=0) {
				int a=num/26;
				int b=num%26;
				if(b==0) {                             //!!!为0特殊情况，变成'Z'且要借位-1
					res.append('Z');           
					a--;
				}else {
				res.append((char)('A'+b-1));           //整型要强转才能变成char
				}
				num=a;
			}
			if(flag==true) {
				res.append('-');
			}
			return res.reverse().toString();
	    }
	
	  //67:二进制字符相加
	  //求和及进位，从10进制-2进制，只有除法，求余的除数变了而已
	  public String addBinary(String num1, String num2) {
			 int len1=num1.length()-1;
			 int len2=num2.length()-1;
			 int add=0;
			 StringBuilder ans=new StringBuilder();
			 while(len1>=0||len2>=0||add>0) {                         //！！！别忘了循环终止还有一个add>0,因为最后只有进位时候，也需要往前放置；用||避免了一个为空的情况，是否为空的处理在循环体中了
				 int x=len1>=0?num1.charAt(len1)-'0':0;               //!!!小心单个字符想要表示单个数的话，需要减去字符0才行
				 int y=len2>=0?num2.charAt(len2)-'0':0;
				 int res=x+y+add;
				 add=res/2;
				 ans.append(res%2);
				 len1--;
				 len2--;
			 }
			 return ans.reverse().toString();
			 }
	  
	  //238：除本身外的所有乘积
	  //提示了用前缀乘积和后缀乘积：所以很简单，求0-i-1前缀乘积，和i+1-n后缀乘积，两者乘积即可
	  public int[] productExceptSelf(int[] nums) {
		  int[] preMulti=new int[nums.length];
		  int[] nextMulti=new int[nums.length];
		  int[] answer=new int[nums.length];
		  preMulti[0]=nums[0];
		  for(int i=1;i<nums.length;i++) {
			  preMulti[i]=preMulti[i-1]*nums[i];
		  }
		  nextMulti[nums.length-1]=nums[nums.length-1];
		  for(int i=nums.length-2;i>=0;i--) {
			  nextMulti[i]=nextMulti[i+1]*nums[i];
		  }
		  answer[0]=nextMulti[1];
		  answer[nums.length-1]=preMulti[nums.length-2];
		  for(int i=1;i<nums.length-1;i++) {
			  answer[i]=preMulti[i-1]*nextMulti[i+1];
		  }
          return answer;
	    }
	  
	  //462：变成相等元素的步数
	  //转化为了中位数问题，原理：假设target在最小值-最大值之间，我们选取这个数，那么最大值最小值移动到target的步数和是max-target+target-min=max-min是固定值
	  //所以直接删掉max,min，继续选取target，同理继续删除...直至只有两个元素/一个元素：偶数，中位数两个二选一均可，奇数，就是中位数
	  public int minMoves2(int[] nums) {
		  Arrays.sort(nums);
		  int m=nums.length;
		  int res=0;
		  for(int i=0;i<nums.length;i++) {
			  res+=Math.abs(nums[i]-nums[m/2]);
		  }
		  return res;
	    }
	  
	  //169:寻找众数
	  //第一种：用哈希表存元素和出现的次数，O（n）也挺好的，花点空间而已
	  //第二种：用投票算法：一个candidate存候选元素，一个count存次数，出现+1，不是-1，为0那就换掉candidate
	  public int majorityElement(int[] nums) {
		  Integer candidate=null;
		  int count=0;
		  for(int i=0;i<nums.length;i++) {
			  if(count==0) {
				  candidate=nums[i];
			  }
			  if(nums[i]==candidate) {
				  count++;
			  }else {
				  count--;
			  }
		  }
		  return candidate;
	    }
	  
	  //470：随机采样的构造（拒绝部分采样法）
	  
	  //*****通用解法：
	  //*****随机采样的构造（都是小的构造大的）：将小的分两次（多次）拒绝一部分，取一部分，乘积求得等于抽样概率，然后映射第一个和第二个（多个）到需要的区间即可即可
	  //*****所谓的拒绝一部分，就相当缩减了随机抽样，从rand7---变成了rand6,rand5,rand4...
	  //*****如果概率无法用乘积的话，比如rand11,那就用两个rand相乘然后选取11个等概率的即可
	  
	  //从rand7变到rand10:实际上随机抽样的原理是-----等概率，即每个元素抽中概率为1/10即可
	  //把rand7中拒绝掉3-7，只剩1-2，概率是1/2，然后再来一个rand7，拒绝掉6-7，只剩1-5，概率是1/5，然后两个rand概率相乘，每个元素的概率就是1/10
	  //接下来就是映射：1-2，1-5要映射到1-10，那就如果满足第一个是1，然后1-5直接映射；满足第一个是2，映射到5+第二个，就是6=10.映射完毕
	  public int rand7() {
		  return 0;
	  }
	  public int rand10() {
		  int first=0;
		  int second=0;
		  while((first=rand7())>2);                      //拒绝采样一部分，相当于变成了rand2
		  while((second=rand7())>5);                     //rand5    
		  if(first==1) {
			  return second;
		  }else {
			  return 5+second;
		  } 
	    }
	  
	  //202：快乐数
	  //难点就在于：判断循环，最好用哈希表，不是链表没必要用floyd判圈法,直接用hashset来判断是否已经存在即可
	  //会不会无限扩大？不会，999-243，9999-324，9999...---对应的只会比当前值小，也就是说数字经过各位数平方，不会到无穷大，到了一定程度只会减少到1或者陷入循环
	  public boolean isHappy(int n) {
		  HashSet<Integer> hash=new HashSet<>();
		  while(n!=1&&!hash.contains(n)) {
			  hash.add(n);
			  n=squareNum(n);
		  }
		  return n==1;
	    }
	  
	  public int squareNum(int n) {
		  int res=0;
		  while(n!=0) {
			  int i=n%10;
			  res+=i*i;
			  n/=10;
		  }
		  return res;
	  }
	
	  
	
	
	
	
	
	

}

