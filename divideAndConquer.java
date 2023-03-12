package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;


public class divideAndConquer {
	
	//*****分治总结：
	//1. master定理：T(n)=aT(n/b)+f(n)   f(n)和n^(logba)比较大小，谁大取谁，相等再乘logn
	//2. **加括号的结果：dp[l][r]=dp[l][k-1]  nums[k](操作符且分开)  dp[k+1][r]

	
	//一般来讲，可以用分治法递归求解的，那就可以用动态规划自底向上求解。分治主要是一种思想，可以用于解决大问题，二分之类
	//*****一般不直接用分治来解题，正常下都是二分和动态规划来解题。
	
	//*****分治算不上一个独立的算法题，基本就是分治=二分+dp,所以单纯的分治很少见。经常使用的还是二分，还有dp来求解
	
	//分治是自顶向下，dp是自底向上，所以一般分治都直接用dp更好写也更好理解。
	
	//241：加括号的运算结果
	//核心也就是左右分dp数组，中间用操作符连接
	//单纯用分治递归很难写，还要记忆数组。用dp也很难，但是稍微好理解一点
	//首先将数字，操作符加入到一个列表里面，然后设dp[l][r]表示从l到r所有可能的运算结果
	//dp[l][r]= 从某一个运算符切开，左侧结果op右侧结果  加入本次结果
	//dp[l][r]=dp[l][k-1] ops[k](运算符) dp[k+1][r]
	//初值，dp[i][i]=单个数（ops[i]不为运算符时候）
	static final int addition=-1;
	static final int sub=-2;
	static final int multi=-3;
	 public List<Integer> diffWaysToCompute(String expression) {
		 List<Integer> ops=new ArrayList<>();
		 for(int i=0;i<expression.length();) {                               //将字符转化为了整型list
			 if(!Character.isDigit(expression.charAt(i))) {                   
				 if(expression.charAt(i)=='+') {
					 ops.add(addition);
				 }
				 if(expression.charAt(i)=='-') {
					 ops.add(sub);
				 }
				 if(expression.charAt(i)=='*') {
					 ops.add(multi);
				 }
				 i++;
			 }else {
				 int t=0;
				 while(i<expression.length()&&Character.isDigit(expression.charAt(i))) {            //数字转化，以免45，26这种大于9的数
					 t=t*10+expression.charAt(i)-'0';
					 i++;
				 }
				 ops.add(t);
			 }
		 }
		 List<Integer>[][] dp=new List[ops.size()][ops.size()];
		 for(int i=0;i<ops.size();i++) {
			 for(int j=0;j<ops.size();j++) {
				 dp[i][j]=new ArrayList<Integer>();                               //list要初始化
			 }
		 }
		 for(int i=0;i<ops.size();i+=2) {
			 dp[i][i].add(ops.get(i));                                            //初值
		 }
		 for(int i=3;i<=ops.size();i++) {                                          //i是长度。长度为1就是初值，为2没有意义，所以从3开始
			 for(int j=0;j+i<=ops.size();j+=2) {                                  //j是起始下标，间隔为2，以免出现运算符开头
				 int l=j;
				 int r=i+j-1;
				 for(int k=j+1;k<r;k+=2) {                                        //k代表的就是本次的运算符的位置，也是间隔为2
					 List<Integer> left=dp[l][k-1];
					 List<Integer> right=dp[k+1][r];
					 for(int nums1:left) {
						 for(int nums2:right) {
							 if(ops.get(k)==addition) {
								 dp[l][r].add(nums1+nums2);
							 }
							 if(ops.get(k)==sub) {
								 dp[l][r].add(nums1-nums2);
							 }
							 if(ops.get(k)==multi) {
								 dp[l][r].add(nums1*nums2);
							 }
						 }
					 }
				 }
			 }
		 }
	  return dp[0][ops.size()-1];	 
	    }
	 
	 //932：漂亮数组
	 //用到了分治的思想，比较难的二分
	 //其实是个数学问题：对于2*a[k]=a[i]+a[j] 恒不成立：右侧，a[i]奇数，a[j]偶数即可
	 //如果a[i],a[j],a[k]是漂亮数组，那么ka[i]+b,ka[j]+b,ka[k]+b也是漂亮数组	
	 //所以我们想到，将数组拆分成两部分，左侧奇数，右侧偶数，且左右两侧都是漂亮数组，那么合起来也必然是漂亮数组
	 //接下来就是如果划分成都是漂亮数组的奇偶数集合：
	 //用的就是k倍性质：奇数-----2*奇数-1，得到的还是奇数，且性质不变
	 //偶数-----2*偶数，得到的还是偶数，且性质不变     通过这种变换，我们就将N扩展到了2N个数（实际上2N-1同样也扩展到了）
	 //用递归来写，实际上是自顶向下
	 //自底向上：1----1，2------1，3，2-----1，3，5，2，4
	 Map<Integer,int[]> memo;                                      //hashmap用来存n和对应的数组
	 public int[] beautifulArray(int n) {
		 memo=new HashMap();
		 return f932(n);
	    }
	 
	 public int[] f932(int n) {
		 if(memo.containsKey(n)) {                                 //从n--n/2---n/4---一步步递归返回
			 return memo.get(n);
		 }
		 int[] ans=new int[n];                                     
		 if(n==1) {
			 ans[0]=1;
		 }else {
			 int t=0;
			 for(int x:f932((n+1)/2)) {                           //奇数漂亮数组递归
				 ans[t++]=2*x-1;                                  //扩展
			 }
			 for(int y:f932(n/2)) {                               //偶数漂亮数组递归
				 ans[t++]=2*y;                                    //扩展
			 }
		 }
		 memo.put(n, ans);                                       
		 return ans;	 
	 }
	 
	 //312：（hard,配不上hard）戳气球（逆向思维DP）
	 //这道题算不上hard题，就是一个逆向思维，后面的dp转移方程比较好想。
	 //逆向思维：每次戳破一个气球，会导致原本不相邻的变成相邻的，不好处理数组；所以逆向思维，直接清空数组，变成每次添加一个气球并分成左右两侧来求，并求coins值+左右侧取最大值
	 //设dp[i][j]表示区间（i,j）的数组进行添加气球后所能得到的最大值
	 //dp[i][j]=max(dp[i][k]+dp[k][j]+nums[i]*nums[j]*nums[k]) k是nums（i,j）中的任意一个数
	 //初始化，将nums扩展，首尾加个1，用于求乘积；初值都是0
	 public int maxCoins(int[] nums) {
		 int m=nums.length;
		 int[][] dp=new int[m+2][m+2];
		 int[] val=new int[m+2];
		 val[0]=val[m+1]=1;
		 for(int i=1;i<m+1;i++) {
			 val[i]=nums[i-1];
		 }
		 for(int i=m-1;i>=0;i--) {              //要保证（i,j）当中存在k可以取，所以i最大值选择的是m-1,这样会至少有一个m存在可取
			 for(int j=i+2;j<=m+1;j++) {        //j是右端点，最大取m+1，j=i+2保持可取至少一个
				 for(int k=i+1;k<j;k++) {
					 int sum=val[i]*val[k]*val[j];
					 sum+=dp[i][k]+dp[k][j];
					 dp[i][j]=Math.max(dp[i][j], sum);
				 }
			 }
		 }
		 return dp[0][m+1];
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
