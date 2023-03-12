package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;
import java.lang.reflect.Array;

public class codeTop14 {
	
	//*****很大的问题：栈，不能用取大小size,然后弹出m=size次实现栈的清空；？？很奇怪
	//必须用isEmpty来判断是否清空
	
	
	
	//93：IP地址的截取
	//回溯题：比较难想到：对于单个s从开头起，只能分为1，2，3个字符三种截取方式(因为最大的是255)，这是递归方向
	//然后判断递归的剪枝：如果剩下的k个字符小于"."个数，或者大于"3*."个数，那就剪枝；递归最多四层，四个".";
	//判断截取的字符是否在0~255之间，不在则剪枝,且除了个位数外不能以0开头；到达末尾就是结束，且层数为4则加入结果
	//**一般DP不会求解非最优化非boolean类型的返回值，所以想到回溯
	public List<String> restoreIpAddresses(String s){
		int len=s.length();
		List<String> res=new ArrayList<>();
		if(len<4||len>12) {
			return res;
		}
		Deque<String> path=new ArrayDeque<>();
		int splitTimes=0;
		dfs93(s,len,splitTimes,0,path,res);
		return res;
			}
	//判断数字是否符合
	public int judeIfRightNumber(String s,int left,int right) {
		int len=right-left+1;
		if(len>1&&s.charAt(left)=='0') {
			return -1;
		}
		int res=0;
		for(int i=left;i<=right;i++) {
			res=res*10+s.charAt(i)-'0';
		}
		if(res>255) {
			return -1;
		}
		return res;
	}
	
	public void dfs93(String s,int len,int split,int begin,Deque<String> path,List<String> res) {
		if(begin==len) {
			if(split==4) {
				res.add(String.join(".", path));
			}
			return ;
		}
		if(len-begin<(4-split)||len-begin>3*(4-split)) {
			return ;
		}
		for(int i=0;i<3;i++) {
			if(begin+i>=len) break;
			int judge=judeIfRightNumber(s,begin,begin+i);
			if(judge!=-1) {
				path.addLast(judge+"");
				dfs93(s,len,split+1,begin+i+1,path,res);
				path.removeLast();
			}
		}
	}
	
	//78：全部子集
	//***很巧妙：转化为选取不选取两次递归，每层都是两个递归
	//子集问题：选取不选取，所有结果：回溯。每一个数都要选取不选取，说明递归从数组左一直到右。
	//每次递归就下一个元素即可，所以递归出口就是到达末尾且最后一个选取过了(length)。深度==元素个数,下一次，然后加入结果集中。
	//对选取不选取：加入，递归或者不加入，递归
	List<Integer> tmp78=new LinkedList<>();
	List<List<Integer>> res78=new ArrayList<List<Integer>>();
	public List<List<Integer>> subsets(int[] nums){
		dfs78(0,nums);
		return res78;
	}
	
	public void dfs78(int len,int[] nums) {
		if(len==nums.length) {
			res78.add(new LinkedList<Integer>(tmp78));
			return ;
		}
		tmp78.add(nums[len]);  //选取了，递归
		dfs78(len+1,nums);
		tmp78.remove(tmp78.size()-1);//不选取，递归
		dfs78(len+1,nums);
	}
	
	//43:字符串相乘
	//竖式相乘：把乘数的每一位和被乘数相乘，然后补上需要扩大的0(注意错位相加，所以补0)，最后相加
	//**StringBuilder.append(数字)：相当于直接补上字符串。可以把数字直接append到StringBuilder里面
	//**StringBuilder中得到的其实是逆序，需要注意下，reverse一下
	//**相乘相加都是从末尾开始的，需要注意
	public String multiply(String num1, String num2) {
		if(num1.equals("0")||num2.equals("0")) {
			return "0";
		}
		String ans="0";
		int m=num2.length();
		int n=num1.length();
		for(int i=m-1;i>=0;i--) {
			int x=num2.charAt(i)-'0';
			int add=0;
			StringBuilder build=new StringBuilder();
			for(int k=m-i;k>1;k--) {
				build.append("0");
			}
			for(int j=n-1;j>=0;j--) {
				int y=num1.charAt(j)-'0';
				int multi=x*y+add;
				int res=multi%10;
				add=multi/10;
				build.append(res);
			}
			if(add!=0) {
				build.append(add);
			}
			ans=addStrings(ans,build.reverse().toString());
		}
		return ans;
	}
	
	public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }
	
	//39.组合之和
	//核心：加入路径值后，target不断缩小，缩小到0加入结果集，缩小到负数，停止当次递归
	//**可重复选取的代码：每次递归依然从当前值开始，表示可以重复选取。
	public List<List<Integer>> combinationSum(int[] candidates, int target){
		int len=candidates.length;
		List<List<Integer>> res=new ArrayList<List<Integer>>();
		if(len==0) return res;
		Deque<Integer> path=new ArrayDeque<>();
		dfs39(candidates,0,len,target,path,res);
		return res;
	}
	
	public void dfs39(int[] candidates,int begin,int len,int target,Deque<Integer> path,List<List<Integer>> res) {
		if(target<0) return ;
		if(target==0) {
			res.add(new ArrayList<>(path));
			return ;
		}
		for(int i=begin;i<len;i++) {
			path.add(candidates[i]);
			//每次递归依然是i，即begin位置
			dfs39(candidates,i,len,target-candidates[i],path,res);
			path.removeLast();
		}
	}
	
	//718：最长重复子数组
	//DP： dp[i][j]=dp[i-1][j-1]+1  (s[i-1]==t[j-1])  !=时候：dp[i][j]=0   因为是结尾的子数组，不是子序列，所以不等直接为0
	//然后取max即可，初值，dp[0][j]=0 dp[i][0]=0   
	//*****这里最好也是s[i-1] 因为s[0]对应有值的，所以dp[0]最好设置为nums1为空的时候，即dp[i]对应s[i-1]
	//*****方便设置初值，初值最好设置在字符串为空的情况，这样就简化了初值处理
	 public int findLength(int[] nums1, int[] nums2) {
		 int m=nums1.length;
		 int n=nums2.length;
		 int[][] dp=new int[m+1][n+1];
		 int res=0;
		 for(int i=1;i<m+1;i++) {
			 for(int j=1;j<n+1;j++) {
				 if(nums1[i-1]==nums2[j-1]) {
					 dp[i][j]=dp[i-1][j-1]+1;
				 }
				 res=Math.max(res, dp[i][j]);
			 }
		 }
		 return res;
	    }
	 
	 //394：字符串解码
	 //本来想用while和flag来表示是否存在[],但是逻辑很复杂
	 //核心解法：用栈来实现括号解码逻辑。数字字符进栈；左括号，字母进栈；右括号，那就需要弹出，直到左括号，
	 //此时将弹出序列reverse且把它复制栈顶数字的份数，然后重新压入栈中，继续放入元素...
	 //终止：放入最后一个字符，弹出所有并reverse
	 //**注意数字可能大于10，所以要判断是否是多位数
	 public static String decodeString(String s) {
		 int len=s.length();
		 int i=0;
		 Stack<Character> stack=new Stack<>();
		 
		 StringBuilder ans=new StringBuilder();
		 while(i<len) {
			 char c=s.charAt(i);
			 if(s.charAt(i)>='0'&&s.charAt(i)<='9') {
				 stack.push(c);
			 }else if(c=='['||(c>='a'&&c<='z')) {
				 stack.push(c);
			 }else if(c==']') {
				 StringBuilder res=new StringBuilder();
				 while(stack.peek()!='[') {
					 res.append(stack.pop());
				 }
				 stack.pop();
				 res.reverse();
				 char digit=stack.pop();
				 int d=digit-'0';
				 int count=0;
				 //多位数求解
				 while(!stack.isEmpty()&&Character.isDigit(stack.peek())) {
					 //**这里一定要小心，++count,如果是count+1,不会改变下一次的count值，导致count一直是0+1，出错
					 d=(int) ((stack.pop()-'0')*Math.pow(10, ++count)+d);
				 }
				 StringBuilder res1=new StringBuilder();
				 for(int k=1;k<=d;k++) {
					 res1.append(res);
				 }
				 
				 int j=0;
				 while(j<res1.length()) {
					 stack.push(res1.charAt(j));
					 j++;
				 }
			 }
			 i++;
		 }
		 while(!stack.isEmpty()) {
			 ans.append(stack.pop());
		 }
		 return ans.reverse().toString();
	 }
	 
	 //14：最长公共前缀
	 public String longestCommonPrefix(String[] strs) {
		 StringBuilder res=new StringBuilder();
		 int i=0;
	     while(true) {
	    	 if(i>=strs[0].length()) break;
	    	 char c=strs[0].charAt(i);
	    	 int j=0;
	    	 boolean flag=true;
	    	 while(j<strs.length) {
	    		 if(i>=strs[j].length()) {
	    			 flag=false;
	    			 break;
	    		 }
	    		 if(strs[j].charAt(i)!=c) {
	    			 flag=false;
	    			 break;
	    		 }
	    		 j++;
	    	 }
	    	 if(flag==true) {
	    		 res.append(c);
	    		 i++;
	    	 }else {
	    		 break;
	    	 }
	     }
	     return res.toString();
	 }
	 
	 //283:移动0
	 //双指针没问题
	 //*****要维持原顺序，所以应当是双指针同向，从左到右，把不是0的交换到左边，且维持在之前的元素后面
	 //**直接把0换到右边没办法维持原顺序，所以很巧妙的用把不为0的换到左边才行，且可以用另一个指针维持顺序
	 public void moveZeroes(int[] nums) {
		 int j=0;
		 for(int i=0;i<nums.length;i++) {
			 if(nums[i]!=0) {
				 int temp=nums[i];
				 nums[j]=nums[i];
				 nums[i]=temp;
				 j++;
			 }
			 
		 }
		 
		 
	 }
	 
	 //162:寻找峰值
	 //非常巧妙的解法：最重要的是边界条件nums[-1]=nums[n]=负的无穷大
	 //当我们取中间节点的时候，寻找一个可以上坡的方向，那么在这个方向上，由于最后是负无穷，所以一定是先升后降，存在峰值
	 //如果往下坡方向遍历，可能一直下坡到负无穷，不存在峰值。所以二分查找只需要查找升序方向即可
	 public int findPeakElement(int[] nums) {
		 int l=0;
		 int r=nums.length-1;
		 //**很奇怪这道题目，经过验证需要设置为l<r,而且return l才是最后满足条件的解，不会超时
		 //<=然后返回mid也对，但是会超时，对于特殊的测试用例超时
		 while(l<r) {
			 int mid=(l+r)/2;
			 if(nums[mid]>nums[mid+1]) {
				 r=mid;
			 }else {
				 l=mid+1;
			 }
		 }
		 return l;
	    }
	 
	 //62：不同路径
	 //dp[i][j]=dp[i-1][j]+dp[i][j-1] 简单的dp问题，最后结果来自于上或者左，求和即可
	 public int uniquePaths(int m, int n) {
		 int[][] dp=new int[m][n];
		 for(int i=0;i<m;i++) dp[i][0]=1;
		 for(int j=0;j<n;j++) dp[0][j]=1;
		 for(int i=1;i<m;i++) {
			 for(int j=1;j<n;j++) {
				 dp[i][j]=dp[i-1][j]+dp[i][j-1];
			 }
		 }
		 return dp[m][n];
	    }
	 
	 public class TreeNode {
		     int val;
		    TreeNode left;
		     TreeNode right;
		     TreeNode() {}
		     TreeNode(int val) { this.val = val; }
		     TreeNode(int val, TreeNode left, TreeNode right) {
		         this.val = val;
		         this.left = left;
		         this.right = right;
		     }
	 
	 //662：二叉树最大宽度
	 //**注意点：二叉树null节点的左右是不可取的，会报异常，而且null节点不可放入队列
     //核心：因为null不能直接放入队列，所以我们使用编号来实现记录。左子节点2i,右子节点2i+1.(注意这里标号从1开始)
     //因为null也算在了标号里，满二叉树的标号就是2i,2i+1。
	 //一个队列存节点，一个队列存标号。
	 public int widthOfBinaryTree(TreeNode root) {
          Deque<TreeNode> q=new LinkedList<>();
          Deque<Integer> num=new LinkedList<>();
          q.add(root);
          num.add(1);
          int maxlen=0;
          while(!q.isEmpty()) {
        	  int size=q.size();
        	  int len=num.peekLast()-num.peekFirst()+1;
        	  maxlen=maxlen>len?maxlen:len;
        	  for(int i=0;i<size;i++) {
        		  TreeNode temp=q.peek();
        		  Integer temp1=num.peek();
        		  if(temp.left!=null) {
        			  q.add(temp.left);
        			  num.add(temp1*2);
        		  }
        		  if(temp.right!=null) {
        			  q.add(temp.right);
        			  num.add(temp1*2+1);
        		  }
        		  
        		  q.remove();
        		  num.remove();
        	  }
          }
          return maxlen;
	    }
	 
	 //152：乘积最大子数组
	 //简单想到的第一层：dp[i]=max(dp[i-1]*nums[i],nums[i]),nums[i]关注的是{0,1,0,1,1}这种带0的情况
	 //但是考虑到有可能负负得正的情况，nums[i]为负数，那就需要前i-1的最小值相乘才能得到最大乘积
	 //所以我们需要维护一个最小乘积，然后max[i]=max(max[i-1]*nums[i],min[i-1]*nums[i],nums[i])
	 //核心：既要维护最大乘积，又要维护最小乘积，以免负负得正的情况。
	 public int maxProduct(int[] nums) {
		int[] max=new int[nums.length];
		int[] min=new int[nums.length];
		max[0]=nums[0];
		min[0]=nums[0];
		int res=nums[0];
		for(int i=1;i<nums.length;i++) {
			max[i]=Math.max(max[i-1]*nums[i],Math.max(min[i-1]*nums[i], nums[i]));
			min[i]=Math.min(min[i-1]*nums[i], Math.min(max[i-1]*nums[i], nums[i]));
		}
		for(int i=1;i<max.length;i++) {
			res=res>max[i]?res:max[i];
		}
		return res;
	 }
	 
	 //179：最大数
	 //核心：无法直接确定，巧妙地用排序规则来解答。把排序规则指定为ab,ba的降序排序即可
	 public String largestNumber(int[] nums) {
		 int n=nums.length;
		 String[] str=new String[n];
		 for(int i=0;i<n;i++) str[i]=""+nums[i]; //**int转化字符串，直接用空字符拼接即可
		 Arrays.sort(str,(a,b)->{
			 String sa=a+b;
			 String sb=b+a;
			 return sb.compareTo(sa);
			 });
	     StringBuilder res=new StringBuilder();
	     for(int i=0;i<str.length;i++) res.append(str[i]);
	     if(res.charAt(0)=='0') return "0";
	     return res.toString();
	 }
	 
	 //468:验证IP
	 //简单的切分问题：**注意.用的是转义\\.,：直接用即可
	 //**注意判断字符是否为数字，用Character.isDigit;字符串转化为int,用Integer.parseInt()
	 public String validIPAddress(String queryIP) {
		 return queryIP.contains(":")?query6(queryIP):query4(queryIP);
	 }
	 
	 public String query4(String queryIP) {
		 if(queryIP.startsWith(".")||queryIP.endsWith(".")||queryIP.contains("..")) return "Neither";
		 String[] str=queryIP.split("\\.");
		 if(str.length!=4) return "Neither";
		 for(int i=0;i<str.length;i++) {
			 String s=str[i];
			 char[] ch=s.toCharArray();
			 if(ch.length>3) return "Neither";
			 int count=0;
			 for(int j=0;j<ch.length;j++) {
				 if(!Character.isDigit(ch[j])) {
					 return "Neither";
				 }else {
					 count=count*10+ch[j]-'0';
				 }
				 if(j==ch.length-1) {
					 if(count>255||(ch[0]=='0'&&ch.length!=1)) {
						 return "Neither";
					 }
				 }
			 }
		 }
		 return "IPv4";
	 }
	 
	 public String query6(String queryIP) {
		 if(queryIP.startsWith(":")||queryIP.endsWith(":")||queryIP.contains("::")) return "Neither";
		 String[] str=queryIP.split(":");
		 if(str.length!=8) return "Neither";
		 for(int i=0;i<str.length;i++) {
			 char[] ch=str[i].toCharArray();
			 if(ch.length>4||ch.length==0) return "Neither";
			 for(int j=0;j<ch.length;j++) {
				 if(!((Character.isDigit(ch[j]))||(ch[j]>='a'&&ch[j]<='f')||(ch[j]>='A'&&ch[j]<='F'))){
					 return "Neither";
				 }
			 }
		 }
		 return "IPv6";
	 }
	 
	 //912:堆排序数组
	 //堆排序：核心函数是heapify(arr,n,i)维护大顶堆顺序，当前位置是i,然后自上而下调整大顶堆，把父节点和两个孩子节点中最大
	 //的放到父节点，如果在孩子节点则交换。然后递归调整交换后的位置   O(logn)；没交换则递归结束
	 //建堆：从最后一个有孩子结点的(n/2-1)开始(即n节点的父节点开始)，从右向左，自下而上调整顺序。 O(n)
	 //排序：将arr[0]堆顶元素和末尾元素交换，末尾元素就是最大值不动了，然后对交换过去的0位置进行heapify即可 O(nlogn)
	 
	 //核心：维持堆内顺序
	 public void heapify(int[] arr,int n,int i) {
		 int largest=i; //维护父节点和子节点最大值的下标，初始是设置为i
		 int lson=i*2+1;//下标从0开始时，左孩子为2i+1,右孩子为2i+2
		 int rson=i*2+2;
		 if(lson<n&&arr[lson]>arr[largest]) largest=lson;
		 if(rson<n&&arr[rson]>arr[largest]) largest=rson;
		 if(largest!=i) {
			 int temp=arr[i];
			 arr[i]=arr[largest];
			 arr[largest]=temp;
			 heapify(arr,n,largest);
		 }
	 }
	 
	 public void heapSort(int[] arr,int n) {
		 //1.建堆：从最后一个节点的父节点开始
		 for(int i=n/2-1;i>=0;i--) {
			 heapify(arr,n,i);
		 }
		 
		 //2.排序：末尾元素开始交换头元素，固定位置
		 for(int j=n-1;j>0;j--) {
			 int temp=arr[0];
			 arr[0]=arr[j];
			 arr[j]=temp;
			 heapify(arr,j,0);  //**注意这里：堆大小发生了变化，最后一个元素确定位置了，所以大小为j不是n
		 }
	 }
	 
	 
	 
	 
	 
	 
	 
	 
}

	    
	 
	 
	 
	 
	 
	 
	 
	 public static void main(String[] args) {
		 String s="100[leetcode]";
		 String t=decodeString(s);
		 System.out.print(t);
	 }
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
