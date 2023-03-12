package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;


public class dynamicProgramming {
	
	//DP复习起来很好复习，代码不复杂，思路很巧妙，就在于能推理出状态转移方程即可。
	
	//DP总结：
	//1. DP最重要的一点：利用DP数组来存储了子问题的解，从而简化了问题。
	//2. dp核心：利用前面n-1个小问题的解来求解第n个问题，利用前面所有子问题的解来求最后一个问题的解。
	//3. 子数组必须是连续的，子序列不需要连续。等差子数组设dp[i]是以nums[i]为结尾的。。。连续的必须设置为以什么为结尾
	//4. 深度优先不好求最优化问题，但是DP就是做最优化问题的。如：01矩阵最近0距离问题：使用左上，右下出发两次DP取min即可
    //部分dp可以用于true/false之类的，比如单词切割
	//5. 记，最大正方形证明起来太复杂：思路：最大正方形边长=min(左，上，左上最大正方形边长)+1
	//键盘操作：全部转化为了先复制一次后不停的粘贴，所以找i的因子j并不断复制: dp[i]=min(i,dp[j]+i/j)
	//6. *****解码问题91：类比于台阶问题，通过只考虑最后一步的情况来分化方案并求和；注意下最后只剩两位需要特殊处理
	//7. *****单词切割139：很巧妙，末尾匹配法，看末尾是否可以切割为字典中单词来递推。dp[i]=dp[j]||dp[i](dp[i]表示多个切割法取或，一个成功即可)  
	//8. **注意一下：LIS最长递增子序列初值，所有dp[i]=1,因为是dp[i]是以s[i]为结尾的子序列长度，肯定包括了s[i],所以是1
	//**LCS里面最好设置dp[i][j]对应s[i-1] t[j-1]，方便空串设置为0
	//9. 取max---初值如果没有具体意义要设置为Integer.Min_Value/2 取min---初值需要设置为最大值/2
	//10. *****满背包问题和01，完全背包的区别在于初值问题：01，完全初值都是0即可；但是满背包问题只有背包满的才是有效状态
	//*****设置初始背包容量为0的是0，背包不为0均设置为Min/2.设置的理由是，迭代导致背包容量下降，一次次的降低背包容量只有降为0后
	//才是用完了空间，才是正确的遍历路径。所以其他初值容量不是0的均设置初值为Min/2.
	//11. 背包问题：选取不选取中，第一步要先判断所剩容量和s[i-1]的大小关系，如果已经不够了，那就只能跳过该物品，别忘了第一部的判断
	//12. *****重要注意点：连续的，必须要设DP数组是以XXX为结尾的最大最小，不然就会导致跳过部分数组值出错。比如：最大子数组的和
	//必须设dp[i]是以a[i]为结尾的最大子数组的和：dp[i-1]>0 dp[i]=dp[i-1]+a[i] dp[i-1]<0 dp[i]=a[i]
	//13. *****复杂股票问题的核心：状态类DP，加一个维度表示状态：股票的基本状态是：持有，不持有两种状态。持有---考虑第i天是否购入
	//不持有---考虑第i天是否卖出。和第i-1天结合求解公式，这样分类讨论即可求解
	//14. **环形数组，首尾不能兼得，所以我们把0-n-2和1-n-1分成两类讨论即可，取最大最小值。
	//15. 最长摆动序列：很巧妙的状态类DP：设dp[i][0]是nums[0]-nums[i]中上升趋势的序列max长度，然后寻找nums[i] nums[i-1]大小关系即可
	//16. *****重点题：494正负搭配问题：先用数学公式转化为负数的选取问题(目标和=(sum-target)/2)，注意这个选取问题
	//不是背包问题，背包是最大最小价值，而本题是方案个数，所以nums[i]<j可选情况是dp[i][j]=dp[i-1][j]+dp[i-1][j-nums[i]]
	//17. *****重点题312：戳气球正向解不好解，所以反向思考变成加气球，然后转化为：dp[i][j]=max(dp[i][k]+dp[k][j]+s[k]*s[i]*s[j])
	
	
	//*****dp四种解题思路：
	//1. 普通的一维二维dp:设dp表示，然后找出和dp[i-1],dp[i-2]或者dp[i+1]的递推关系;
	//或者是利用前面的某个j，dp[j]求递推
	//2. 切割类dp:不再拘泥于用附近的dp关系，而是特定可能状态下的dp，比如说完全平方数(前面的某个j且为完全平方数则分出去，+1)，字符串问题
	//3. 背包问题的转化：把选取不选取，容量，价值问题，都转化为了背包问题，主要用于数组元素选取不选取，转化为01背包，完全背包，满背包
	//4. 状态类dp,多dp数组问题：不再使用单一一个dp数组，而是不同状态用不同的dp数组来表示，可以借鉴使用一个的第二维度[0][1],主要涉及的是股票问题，摆动序列问题
	
	
	//*****分割类dp:
	//当dp数组不再满足最近几个单位的状态转移方程，比如：dp[i-1],dp[i-2]这种，而是满足特定分割条件的dp
	//但其实核心思想还是用前i-1个推出第i个，只不过不再使用相邻位置关系，而是根据题目所给条件来找关系。
	  
	//切割类dp:需要按照题目要求来找不同位置的状态转移，从前i-1个中寻找满足的分出去，然后递推，并求解最大最小值
	  
	//*****分为两种：数字切割（挖出来满足条件的一部分，然后剩下的怎么样怎么样），字符串切割（按照一个个串的位置来分）
	
	
	//70：爬楼梯问题
	//递推方程，实际上递推方程也是动规的一种，因为递推也是从前面的子问题的解得到后面第k个的解
	//下图用到了空间压缩，因为每个元素只和之前的两个元素有关，所以可以直接只保存每次的前两个值，用两个变量来维护即可，每次取完值不断向后更新。
	public int climbStairs(int n) {
          if(n<=2) return n;
          int pre0=1;
          int pre1=2;
          int cur=0;
          for(int i=3;i<=n;i++) {
        	  cur=pre0+pre1;
        	  pre0=pre1;
        	  pre1=cur;
          }
          return cur;
    }
	
	//198:不能连续偷窃问题
	//正确思路：
	//设dp[k]是小偷到达下标为k房屋时能获得的最大金额，与前面的子问题的解结合，分为两种：
	//第k个没偷：dp[k]=dp[k-1]
	//偷了，那么第k-1个肯定不能偷：dp[k]=dp[k-2]+nums[k]
	//两者取max即为本次到k能获得的最大值
	  public int rob(int[] nums) {
		    if(nums.length==1) return nums[0];
		    int[] dp=new int[nums.length];
		    dp[0]=nums[0];
		    dp[1]=Math.max(nums[0], nums[1]);
		    for(int i=2;i<nums.length;i++) {
		    	dp[i]=Math.max(dp[i-1], dp[i-2]+nums[i]);
		    }
            return dp[nums.length-1];
	    }
	  
	  //413：等差子数组的个数
	  //明确一个点，子数组的概念：在数组中连续的截取一段，才叫子数组，间隔的不算子数组。子数组的概念就是必须连续。
	  //设dp[i]为以nums[i]为结尾的等差子数组的个数，dp[i+1]分为两种：
	  //1. nums[i+1]-nums[i]!=nums[i]-nums[i-1]:因为子数组必须截取连续的，最近的三个都不满足，那么所有的都不满足，为0
	  //2. nums[i+1]-nums[i]==nums[i]-nums[i-1]==k:最近的三个成等差数列，先+1；然后因为前面所有的以nums[i]结尾的等差数组，必然包括nums[i-1],所以等差数组的公差是一样的。所以dp[i]
	  //内的数组都可以直接连上nums[i+1](因为公差一样)，所以dp[i+1]=dp[i]+1
	  //最后求整个数组的等差子数组个数：把dp数组求和
	  public int numberOfArithmeticSlices(int[] nums) {
              int[] dp=new int[nums.length];
              if(nums.length==0||nums.length==1) return 0;
              int res=0;
              dp[0]=dp[1]=0;
              for(int i=2;i<nums.length;i++) {
            	  if(nums[i]-nums[i-1]==nums[i-1]-nums[i-2]) {
            		  dp[i]=dp[i-1]+1;
            	  }
              }
              for(int j=0;j<nums.length;j++) {
            	  res+=dp[j];
              }
              return res;
	    }
	  
	  //64：最小路径和
	  //设dp[i][j]是到达[i,j]位置的最短路径和.因为该位置，只能由上面下来，或者左边向右得来，所以取min即可
	  //dp[i][j]=min(dp[i-1][j],dp[i][j-1])+grid[i][j]
	  //初值：行为0.列为0都需要单列
	  public int minPathSum(int[][] grid) {
		  int[][] dp=new int[grid.length][grid[0].length];
		  dp[0][0]=grid[0][0];
		  for(int j=1;j<grid[0].length;j++) {
			  dp[0][j]=dp[0][j-1]+grid[0][j];
		  }
		  for(int i=1;i<grid.length;i++) {
			  dp[i][0]=dp[i-1][0]+grid[i][0];
		  }
		  for(int m=1;m<grid.length;m++) {
			  for(int n=1;n<grid[0].length;n++) {
				  dp[m][n]=Math.min(dp[m-1][n], dp[m][n-1])+grid[m][n];		  
			  }
		  }
		  return dp[grid.length-1][grid[0].length-1];
	    }
	
	  //空间压缩：很巧妙，就是一个当前表示的思想：遍历到dp[i][j]想要求值时候，min里面的dp[i][j-1]就是本层i已经求过的的dp[j-1].
	  //然后dp[i-1][j]是上一层i-1时候的dp[j]，因为本层dp[i][j]还没更新，即i层的dp[j]还没更新。那么刚好min中改为dp[j]就是代表的上一层的dp[j],所以压缩成功
	  
	  //542:01矩阵最近0问题
	  //第一反应用回溯法，能做但是比较复杂，甚至回溯很难求最近的0，四个方向需要向外扩展，应该用广度优先。可以做。回溯法不好求最优化问题，但是广度优先可以
	  //设计最优化的问题，一般还是得用动态规划
	  //设dp[i][j]是第[i][j]位置到最近0的距离，我们假设只能向左上行走，即和上一题一样
	  //1. mat[i][j]==0: dp[i][j]=0
	  //2. mat[i][j]!=0: 只能从左或者上而来，dp[i][j]=min(dp[i-1][j],dp[i][j-1])+1
	  //然后如果只能右下，又可以更新一次；然后左下；然后右上；把四个方向都更新完了，就是所有方向都包含了，更新到最后即为答案
	  //简化：只要左上，右下两个方向即可。具体的证明就是粗略的，大概了解，两个方向即可证明最终答案。两次更新即可得到答案按
	  public int[][] updateMatrix(int[][] mat) {
		  int m=mat.length;
		  int n=mat[0].length;
		  int[][] dp=new int[m][n];
		  for(int i=0;i<m;i++) {
			  Arrays.fill(dp[i], Integer.MAX_VALUE/2);              //!!!必须赋初值为较大值，不然后面Math.min里面，会导致初值为0，每次取min都是0占优势，出错
		  }
		  for(int i=0;i<m;i++) {
			  for(int j=0;j<n;j++) {
				  if(mat[i][j]==0) {
					  dp[i][j]=0;
				  }
			  }
		  }
		  for(int i=0;i<m;i++) {
			  for(int j=0;j<n;j++) {
				  if(i-1>=0) {
					  dp[i][j]=Math.min(dp[i][j], dp[i-1][j]+1);    //为了解决边界问题，分两次取min，这样子就可以简化边界问题；同时别忘了，每次min都要带上当前值取min,这样才是更新
				  }                                                 //！！同时解决了mat[i][j]=0时候，可以直接得到0的情况。因为dp[i][j]=0,肯定是优势方，所以这里不用再特意判断mat[i][j]
				  if(j-1>=0) {                                      //是否为0
					  dp[i][j]=Math.min(dp[i][j], dp[i][j-1]+1);
				  }
			  }
		  }
		  for(int i=m-1;i>=0;i--) {
			  for(int j=n-1;j>=0;j--) {
				  if(i+1<m) {
					  dp[i][j]=Math.min(dp[i][j], dp[i+1][j]+1);    //min带上当前值，是为了更新值。
				  }
				  if(j+1<n) {
					  dp[i][j]=Math.min(dp[i][j], dp[i][j+1]+1);
				  }
			  }
		  }
		  return dp;
	    }
	  
	  //221：最大正方形
	  //设dp[i][j]为以matrix[i][j]为右下角的最大正方形的边长！！！注意是边长，不是面积
	  //1. matrix[i][j]==0: dp[i][j]=0
	  //2. matrix[i][j]==1: 此时最大正方形，应该是左方，上方，左上方最大正方形边长的最小值，加上本元素的长度，即+1
	  //dp[i][j]=min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])+1
	  //这道题需要证明，特别复杂，这里就不赘述了
	  //初值：i=0的边界上，为1的面积为1；j=0的边界上，为1的面积为1.
	  public int maximalSquare(char[][] matrix) {                     //小心，这题是char类型的二维数组，不是int
		  int m=matrix.length;
		  int n=matrix[0].length;
		  int[][] dp=new int[m][n];
		  int max=0;
		  for(int i=0;i<m;i++) {
			  if(matrix[i][0]=='1') {
				  dp[i][0]=1;
				  max=1;                                              //有1，要更改max,以免出现周边有1，但是内部无1的特殊情况
			  }else {
				  dp[i][0]=0;
			  }
		  }
		  for(int j=0;j<n;j++) {
			  if(matrix[0][j]=='1') {
				  dp[0][j]=1;
				  max=1;
			  }else {
				  dp[0][j]=0;
			  }
		  }
		  for(int i=1;i<m;i++) {
			  for(int j=1;j<n;j++) {
				  if(matrix[i][j]=='0') {
					  dp[i][j]=0;
				  }else {
					  dp[i][j]=Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1])+1;
					  max=Math.max(max, dp[i][j]);
			  }
		  }		 
	    }
		  return max*max;	
	  }
	  

	  //279：完全平方数
	  //设dp[i]表示i最少拆分的平方数的个数
	  //i可以先分出1，4，9，16...然后就是求剩下的i-1,i-4,i-9,i-16...的dp数组值+1即可，并且要取min值
	  //dp[i]=min(dp[i-1],dp[i-4],dp[i-9],dp[i-16]...)+1
	  //这道题的核心就是，先推一步，转化为前i-1范围内的，然后用dp
	  public int numSquares(int n) {
		  int[] dp=new int[n+1];
		  dp[0]=0;
		  for(int k=1;k<=n;k++) {
			  dp[k]=Integer.MAX_VALUE;                           //初始值要赋值最大值，为了后面更新dp[i]内部不会出错，不赋值初始化为0，后面求min一定会是0，出错。
		  }
		  for(int i=1;i<=n;i++) {
			  for(int j=1;j*j<=i;j++) {                          //小心，是<=i,不是n,当前层对应的求的是i
				  dp[i]=Math.min(dp[i], dp[i-j*j]+1);
			  }
		  }
          return dp[n];
	    }
	  
	  //91：解码问题
	  //不算切割类吧，就是和相邻的关系
	  //设dp[i]是以s[i]结尾的字符串的解码数，找与dp[i-1],dp[i-2]...的关系
	  //1. 最后一个单独解码，且s[i]!=0: 最后一个字母，单独编码即可，那么前i-1个的解码后，直接附上最后一个字母即可，所以dp[i]=dp[i-1]
	  //2. 最后两个一起解码，s[i-1]!=0&&最后两数字组成的数小于等于26：最后两个一起编码，即前面i-2个解码完，直接附上最后的，dp[i]=dp[i-2]
	  //一共只有这两种可能，相加即可得到dp[i]
	  //特殊：字符以0开头的话，那就没办法解码，因为开头的0没法处理，答案就是0
	  public int numDecodings(String s) {
		  int m=s.length();
		  int[] dp=new int[m];
		  if(s.charAt(0)=='0') return 0;
		  dp[0]=1;
		  for(int i=1;i<m;i++) {
			  if(s.charAt(i)!='0') {
				  dp[i]+=dp[i-1];
			  }
			  if(i-2>=0&&s.charAt(i-1)!='0'&&((s.charAt(i-1)-'0')*10+(s.charAt(i)-'0'))<=26) {
				  dp[i]+=dp[i-2];
			  }
			  if(i-2==-1&&s.charAt(i-1)!='0'&&((s.charAt(i-1)-'0')*10+(s.charAt(i)-'0'))<=26) {   //考虑到“12”这种特殊情况，最后两个成解码，但是前面没有了，就需要直接+1即可
				  dp[i]+=1;
			  }
		  }
		  return dp[m-1];
	    }
	  
	  //139：单词切割问题
	  //*****dp不仅能用于最优化，而且可以用于true/false的问题
	  //设dp[i]表示以是s[i-1]为结尾的单词是否可以成功切割。从尾部直接开始切割
	  //1. 尾部可以用字典切割，遍历切割的字典，得到j位置切割，则dp[i]=dp[j]||dp[i]    用||dp[i]的原因是可能前一个字典中的切割出了true,那就可以直接return true了，所以要用||dp[i]
	  //2. 尾部不能切割，那就是false,包含在了初始化false中
	  
	  //*****java中判断两个字符串内的元素是否相同，用的是equals方法，不能用==，==只能判断是否指向同一个字符串。
	  //对于包装类型，字符串（java内部重写过了equals方法），==都是比较是否指向同一对象，而不是比较内容，判断内容只能用equals方法；对于基本数据类型，==就是比较值
	  //对于引用类型，如果不手动重写equals，那就是equals和==一样，只能判断是否指向同一对象；重写过equals才能判断内容
	  //简单而言：只有重写过equals的才能判断内容，字符串，包装类java内部已经重写过了；基本数据类型就用==判断值；直接==的就是判断地址
	  public boolean wordBreak(String s, List<String> wordDict) {
		  int m=s.length();
		  boolean[] dp=new boolean[m+1];
		  dp[0]=true;
		  for(int i=1;i<=m;i++) {
			  for(int k=0;k<wordDict.size();k++) {
				  int len=wordDict.get(k).length();
				  if(i>=len&&s.substring(i-len, i).equals(wordDict.get(k))) {           //只能用equals，一定要小心啊
					  dp[i]=dp[i]||dp[i-len];
				  }
			  }
		  }
		  return dp[m];
	    }
	  
	  //*****子序列问题：
	  //子序列和子数组的区别：子数组必须连续元素，子序列可以不连续。
	  //子序列问题，里面的这两个极其常见，最主要就是初值问题，要注意一下
	  
	  //300：LIS最长递增子序列
	  //设dp[i]是以nums[i]为结尾的最长递增子序列的长度
	  //dp[i]=max{dp[j]+1},其中nums[j]<nums[i]&&j<i
	  //!!!初始化，所有的dp数组值都需要置为1。因为如果只置dp[0]=1的话，因为后面的不一定大于nums[0]，会导致跳过dp[0],那么就可能造成其他的比如dp[1]=0,影响了后面的计算
	  //所以LIS的初始化，需要所有的数组都置初值1.
	  public int lengthOfLIS(int[] nums) {
		  if(nums.length==1) return 1;
		  int[] dp=new int[nums.length];
		  int max=0;
		  for(int i=0;i<nums.length;i++) {
			  dp[i]=1;
		  }
		  for(int i=1;i<nums.length;i++) {
			  for(int j=0;j<i;j++) {
				  if(nums[j]<nums[i]) {
					  dp[i]=Math.max(dp[i], dp[j]+1);
				  }
			  }
			  max=Math.max(max, dp[i]);
		  }
		  return max;
	    }
	  
	  //1143：LCS最长公共子序列
	  //设dp[i][j]表示text1[i]和text2[j]结尾的字符串的最长公共长度
	  //1. text1[i]==text2[j]: dp[i][j]=dp[i-1][j-1]+1
	  //2. text1[i]!=text2[j]: dp[i][j]=max(dp[i-1][j],dp[i][j-1])   核心理解：末尾值不等，必然有一个需要舍弃掉。
	  //!!!上面写法，初值比较麻烦。所以最好改成text1[i-1],text2[j-1]结尾，空串直接赋值为0；因为自动初始化就是0，所以不用再去刻意赋值了
	  public int longestCommonSubsequence(String text1, String text2) {
		  int m=text1.length();
		  int n=text2.length();
		  int[][] dp=new int[m+1][n+1];
		  for(int i=1;i<m+1;i++) {
			  for(int j=1;j<n+1;j++) {
				  if(text1.charAt(i-1)==text2.charAt(j-1)) {
					  dp[i][j]=dp[i-1][j-1]+1;
				  }else {
					  dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
				  }
			  }
		  }
		  return dp[m][n];           
	    }
	  
	  
	  
	  //背包类问题：一般都是从具体问题转化为背包问题：选取不选取问题，容量问题，价值问题
	  //文档里面关于正向逆向遍历，01背包和完全背包两个方向遍历j，很有意思这种空间压缩。利用的就是遍历顺序，求到dp[i][j]时，确定dp[i-1][j-w]或者dp[i][j-w]一定要早于dp[i][j]赋好了值
	  //*****确定好dp[j-w]是本层的还是上层的，不仅仅要赋好值，而且要确定是哪一层的值
	  
	  //*****有关子集的问题，大多数都转化为01背包问题：元素选取不选取问题，容量问题
	  //*****最大特征：子集（子集中元素）选取问题-----01背包问题
	  
	  //*****满背包问题：和普通背包的不同在于初值
	  //普通背包初值都是0
	  //满背包问题：只有容量为0的状态才是有效状态，设置为0；其他容量不为0的就是无效状态，设置为MIN。（部分求min的设置为MAX/2）
	  //*****推导：如果dp[i][j]装不满，那么执行max( , )时候，即使先取出了一份，变成了dp[i-1][j-w],依然是不可能装满的，所以说（）内部两个都是MIN，所以答案还是MIN
	  
	  //*****初值，0对应nums[-1]时，其实就是代表nums为空串时候的情况
	  
	  
	  
	  
	  
	  
	  //416：分割等和子集
	  //！！！转化为01背包问题：即选取不选取问题,容量问题
	  //！！！重要的转化思路：划分为等和两个子集，也就是寻找一个子集的和是总体的一半即可。而且是正整数，所以不能sum为奇数
	  //设dp[i][j]表示nums[0]-nums[i]中提取子集，使得和能否到达j
	  //1. j<nums[i]: nums[i]肯定不能放入子集（相当于背包超容量了），dp[i][j]=dp[i-1][j]
	  //2. j>=nums[i]: nums[i]可放可不放，dp[i][j]=dp[i-1][j]||dp[i-1][j-nums[i-1]]
	  //初值：j==0,那就是dp[i][0]=true;直接就是目标值减到0即为true。
	  public boolean canPartition(int[] nums) {
		  int m=nums.length;
		  int sum=0;
		  for(int i=0;i<m;i++) {
			  sum+=nums[i];
		  }
		  if(sum%2==1) return false;
		  int target=sum/2;
		  boolean[][] dp=new boolean[m][target+1];
		  for(int i=0;i<m;i++) {
			  dp[i][0]=true;
		  }
		  for(int i=0;i<m;i++) {
			  for(int j=0;j<=target;j++) {
				  if(j<nums[i]&&i-1>=0) {
					  dp[i][j]=dp[i-1][j];
				  }
				  if(j>=nums[i]&&i-1>=0) {
					  dp[i][j]=dp[i-1][j]||dp[i-1][j-nums[i]];
				  }
			  }
		  }
		  return dp[m-1][target];
	    }
	  
	  //474：一和零问题
	  //转化为01背包问题：选取不选取，容量就是m,n
	  //设dp[i][j][k]表示选取strs[0]-strs[i-1],且0,1容量小于j,k的最大子集长度
	  //1. strs[i-1]中0,1大于容量： dp[i][j][k]=dp[i-1][j][k]
	  //2. 小于等于容量： dp[i][j][k]=max(dp[i-1][j-0数][k-1数]+1,dp[i-1][j][k])
	  //初值，和背包问题一样，就是0.dp[i][0][0]=0,为0，初始化就是0.
	  public int findMaxForm(String[] strs, int m, int n) {
		  int l=strs.length;
		  int[][][] dp=new int[l+1][m+1][n+1];
		  for(int i=0;i<l+1;i++) {
			  for(int j=0;j<m+1;j++) {
				  for(int k=0;k<n+1;k++) {
					  if(i-1>=0&&(count474(strs[i-1])[0]>j||count474(strs[i-1])[1]>k)) {              //注意下，dp中规定是strs[i-1]这里一定不能写错了
						  dp[i][j][k]=dp[i-1][j][k];
					  }
					  if(i-1>=0&&(count474(strs[i-1])[0]<=j&&count474(strs[i-1])[1]<=k)) {
						  dp[i][j][k]=Math.max(dp[i-1][j][k], dp[i-1][j-count474(strs[i-1])[0]][k-count474(strs[i-1])[1]]+1);
					  }
				  }
			  }
		  }
		  return dp[l][m][n];
	    }
	  
	  public int[] count474(String s) {
		  int[] count=new int[2];
		  for(int i=0;i<s.length();i++) {
			  if(s.charAt(i)=='0') count[0]++;
			  if(s.charAt(i)=='1') count[1]++;
		  }
		  return count;
	  }
	  
	  //322：硬币问题
	  //完全背包问题且满背包
	  //设dp[i][j]表示coins[0]-coins[i-1]中获取j容量且装满的最小硬币数
	  //1. 不好装：dp[i][j]=dp[i-1][j]
	  //2. 好装：dp[i][j]=min(dp[i-1][j],dp[i][j-coins[i-1]]+1)
	  //初始值，有效状态，容量为0时，为0；无效状态，容量不为0，设置为MAX/2
	  public int coinChange(int[] coins, int amount) {
		  int m=coins.length;
		  int[][] dp=new int[m+1][amount+1];
		  for(int i=0;i<m+1;i++) {
			  for(int j=0;j<amount+1;j++) {
				  dp[i][j]=Integer.MAX_VALUE/2;                       //!!!!!非常重要：除2的原因，是因为后面有加1，为了防止溢出，不能直接用最大值，只能/2再用
			  }
		  }
		  for(int i=0;i<m+1;i++) {
			  dp[i][0]=0;
		  }
		  for(int i=0;i<m+1;i++) {
			  for(int j=0;j<amount+1;j++) {
				  if(i-1>=0&&coins[i-1]>j) {
					  dp[i][j]=dp[i-1][j];
				  }
				  if(i-1>=0&&coins[i-1]<=j) {
					  dp[i][j]=Math.min(dp[i-1][j], dp[i][j-coins[i-1]]+1);
				  }
			  }
		  }
		  return dp[m][amount]>=Integer.MAX_VALUE/2?-1:dp[m][amount];               
	    }
	  
	  
	  //72：编辑距离
	  //太常见了，公式都不用写了
	  public int minDistance72(String word1, String word2) {
		  int m=word1.length();
		  int n=word2.length();
		  int[][] dp=new int[m+1][n+1];
		  for(int i=0;i<m+1;i++) {
			  dp[i][0]=i;
		  }
		  for(int j=0;j<n+1;j++) {
			  dp[0][j]=j;
		  }
		  for(int i=1;i<m+1;i++) {
			  for(int j=1;j<n+1;j++) {
				  if(word1.charAt(i-1)==word2.charAt(j-1)) {
					  dp[i][j]=dp[i-1][j-1];
				  }else {
					  dp[i][j]=Math.min(Math.min(dp[i-1][j-1], dp[i][j-1]), dp[i-1][j])+1;
				  }
			  }
		  }
		  return dp[m][n];
	    }
	  
	  //650:键盘操作
	  //状态转移方程比较难想到，用的是整除关系
	  //核心理解：每次要么直接粘贴，要么先复制再粘贴。实际上是一个道理的。因为先整个复制再粘贴，实际上就是原来的k倍再×2，所以第i个能完成一定是第j个的倍数关系。
	  //直接粘贴：k,2k,3k,4k......
	  //复制粘贴：k,2k,3k(复制粘贴),6k,9k......只是相当于加快了速度而已，其实还是k的倍数
	  //设dp[i]表示到达i个A的最少操作次数
	  //找到i%j==0,找到i的因数，然后先到达j，然后进行复制,粘贴，粘贴，粘贴。。。操作，到达i
	  //dp[i]=min(dp[j]+i/j-1+1)=min(dp[j]+i/j)
	  //初值，dp[1]=0,dp[0]无意义，其他的，为了循环中的min更新，要设置初值为最大值(i,复制一个，然后不停粘贴)
	  public int minSteps(int n) {
		  int[] dp=new int[n+1];
		  dp[1]=0;
		  for(int i=2;i<n+1;i++) {
			  dp[i]=i;
			  for(int j=2;j<i;j++) {
				  if(i%j==0) {
					  dp[i]=Math.min(dp[i], dp[j]+i/j);
				  }
			  }
		  }
		  return dp[n];
	    }
	  
	  //10：（hard,理解）正则匹配
	  //从尾部开始匹配法,其实看了状态转移方程也不难，一步步从尾部推出来即可。
	  //设dp[i][j]表示s的前i个字符和p的前j个字是否匹配
	  //1.  p的末尾是字母，那就直接两者看看是否匹配，匹配则dp[i-1][j-1],不匹配就是false
	  //2.  p的末尾是‘.’,任意匹配，就是dp[i-1][j-1]
	  //3.  p的末尾是‘*’,分为两种，s[i]与p[j-1]相同，即可以匹配一次到多次，那就有选择匹配/不匹配，为dp[i-1][j]或dp[i][j-2]
	  //若不同，那就没有选择，肯定匹配0次，为dp[i][j-2]
	  public boolean isMatch(String s, String p) {
		  int m=s.length();
		  int n=p.length();
		  boolean[][] f=new boolean[m+1][n+1];
		  f[0][0]=true;
		  for(int i=0;i<m+1;i++) {
			  for(int j=1;j<n+1;j++) {
				  if(p.charAt(j-1)=='*') {
					  f[i][j]=f[i][j-2];
					  if(matches(s,p,i,j-1)) {
						  f[i][j]=f[i][j]||f[i-1][j];
					  }
				  }else {
					  if(matches(s,p,i,j)) {
						  f[i][j]=f[i-1][j-1];
					  }
				  }
			  }
		  }
		  return f[m][n];
	  }
	  
	  public boolean matches(String s,String p,int i,int j) {
		  if(i==0) {
			  return false;
		  }
		  if(p.charAt(j-1)=='.') {
			  return true;
		  }
		  return s.charAt(i-1)==p.charAt(j-1);
	  }
	  
	  //*****股票类问题：
	  //1. 简单的：普通利润，最大子数组的和问题，都是可以直接转成利润数组求解即可
	  
	  //2. 复杂的：k笔交易问题，冷冻期问题，手续费问题
	  //*****用状态机法来求解：
	  //设dp[i][0],dp[i][1],dp[i][2]为有股票，无股票，冷冻期...不同状态的表示
	  //然后利用第i天的状态，和第i-1天的状态进行推导，就可以得到状态转移方程。
	  
	  
	  
	  
	 
	  //121：股票利润(最大子数组的和)
	  //先求利润，然后转化为了求最大子数组的和
	  //dp[i]为以a[i]为结尾的最大子数组的和,dp[i-1]小于0，则dp[i]=a[i];dp[i-1]>=0,dp[i]=dp[i-1]+a[i]
	  public int maxProfit121(int[] prices) {
          int m=prices.length;
          int[] profit=new int[m-1];
          if(m==1) return 0;
          for(int i=0;i<m-1;i++) {
        	  profit[i]=prices[i+1]-prices[i];
          }
          int[] dp=new int[m-1];
          dp[0]=profit[0];
          int maxProfit=dp[0];
          for(int i=1;i<m-1;i++) {
        	  if(dp[i-1]<0) {
        		  dp[i]=profit[i];
        		  maxProfit=Math.max(maxProfit, dp[i]);
        	  }else {
        		  dp[i]=dp[i-1]+profit[i];
        		  maxProfit=Math.max(maxProfit, dp[i]);
        	  }
          }
          return maxProfit>=0?maxProfit:0;    
	    }
	  
	  //188也可以用dp[i][0][k],dp[i][1][k]来求解，相当于增加了维度来表示，就不用分成两个数组了
	  
	  //188:（hard,理解）股票利润，k次买卖
	  //很巧妙，利用了二维数组来求解，而且分为了持有，不持有股票两种数组
	  //设buy[i][j]是i天中，进行了j笔交易且手中持有股票的最大利润；sell[i][j]是price[0]-price[i]中，进行了j笔交易且手中没有股票的最大利润
	  //对于buy[i][j]:手中的股票是否是第i天买入的：
	  //1. 是，buy[i][j]=sell[i-1][j]-prices[i]
	  //2. 不是，buy[i][j]=buy[i-1][j]
	  //对于sell[i][j]没有股票，是否是第i天卖掉的：
	  //1. 是，sell[i][j]=buy[i-1][j-1]+prices[i]
	  //2. 不是，sell[i][j]=sell[i-1][j]
	  //对于上面两个，均取最大值。初值，buy[0][0]=-prices[0],sell[0][0]=0,buy[0][j],sell[0][j]都是不合法状态，设置为MIN
	  public int maxProfit188(int k,int[] prices) {
		  int n=prices.length;
		  if(n==0) return 0;
		  k=Math.min(k, n/2);
		  int[][] buy=new int[n][k+1];
		  int[][] sell=new int[n][k+1];
		  buy[0][0]=-prices[0];
		  sell[0][0]=0;
		  for(int i=1;i<=k;i++) {
			  buy[0][i]=sell[0][i]=Integer.MIN_VALUE/2;      //因为要取max
		  }
		  for(int i=1;i<n;i++) {
			  buy[i][0]=Math.max(buy[i-1][0], sell[i-1][0]-prices[i]);                    //相当于把j=0拿过来了
			  for(int j=1;j<=k;j++) {
				  buy[i][j]=Math.max(buy[i-1][j], sell[i-1][j]-prices[i]);
				  sell[i][j]=Math.max(sell[i-1][j], buy[i-1][j-1]+prices[i]);
			  }
		  }
		  return Arrays.stream(sell[n-1]).max().getAsInt();
	  }
	  
	  //309：股票利润（包含冷冻期）
	  //状态自动机解法
	  //和上面题目一样，也是把持有股票，不持有股票分为两种情况；同时本题还有冷冻期，也是状态
	  //所以：dp[i][0]:表示第i天最大利润且持有股票；dp[i][1]表示第i天最大利润，没有股票且处于冷冻期；dp[i][2]表示最大利润，没有股票且不是冷冻期
	  //1. dp[i][0]: 是否是第i天才买入的：=max(dp[i-1][0],dp[i-1][2]-prices[i])
	  //2. dp[i][1]: 处于冷冻期，只能是第i-1天卖出了，=dp[i-1][0]+prices[i]
	  //3. dp[i][2]: 不是冷冻期，第i-1天肯定不是卖了股票，i-1天可能是冷冻期或者非冷冻期：=max(dp[i-1][1],dp[i-1][2])
	  //初值：dp[0][0]=-prices[0],dp[0][1]=0,dp[0][2]=0
	  public int maxProfit(int[] prices) {
           int m=prices.length;
           int[][] dp=new int[m][3];
           dp[0][0]=-prices[0];
           for(int i=1;i<m;i++) {
        	dp[i][0]=Math.max(dp[i-1][0], dp[i-1][2]-prices[i]);
        	dp[i][1]=dp[i-1][0]+prices[i];
        	dp[i][2]=Math.max(dp[i-1][1], dp[i-1][2]);
           }
           return Math.max(dp[m-1][1], dp[m-1][2]); 
	    }
	  
	  //714:股票利润（收售出的手续费）
	  //和上面题目一样，设dp[i][0]表示最大利润且手中有股票，dp[i][1]表示最大利润且手中没有股票（dp里面的i和prices里面一样，从0开始）
	  //分成，第i天是否买入，第i天是否卖出
	  //dp[i][0]=max(dp[i-1][0],dp[i-1][1]-prices[i])
	  //dp[i][1]=max(dp[i-1][1],dp[i-1][0]+prices[i]-fee)      ！！！注意是prices[i],因为是第i-1天手中有股票，第i天才卖，所以利润是+prices[i]  （买入的价格在之前已经算过了，所以直接卖出的算作利润）
	  //初值：dp[0][0]=-prices[0] dp[0][1]=0
	  public int maxProfit(int[] prices, int fee) {
          int m=prices.length;
          int[][] dp=new int[m][2];
          dp[0][0]=-prices[0];
          for(int i=1;i<m;i++) {
        	  dp[i][0]=Math.max(dp[i-1][0], dp[i-1][1]-prices[i]);
        	  dp[i][1]=Math.max(dp[i-1][1], dp[i-1][0]+prices[i]-fee);
          }
          return dp[m-1][1];
	    }
	  
	  //练习题：
	  
	  //213：小偷问题2
	  //环形数组问题：首尾不能一起，那就分成两种，0-n-2为第一种，1-n-1为第二种，两种取max即可
	  public int rob213(int[] nums) {
		    if(nums.length==1) return nums[0];
		    if(nums.length==2) return Math.max(nums[0], nums[1]);
		    int[] dp1=new int[nums.length-1];
		    int[] dp2=new int[nums.length-1];
		    dp1[0]=nums[0];
		    dp1[1]=Math.max(nums[0], nums[1]);
		    dp2[0]=nums[1];
		    dp2[1]=Math.max(nums[1], nums[2]);
		    for(int i=2;i<nums.length-1;i++) {
		    	dp1[i]=Math.max(dp1[i-1], dp1[i-2]+nums[i]);
		    	dp2[i]=Math.max(dp2[i-1], dp2[i-2]+nums[i+1]);
		    }
          return Math.max(dp1[nums.length-2], dp2[nums.length-2]);
	    }
	  
	  //53:最大子数组的和
	  //dp[i]是包括了nums[i]的最大子数组的和
	  //1. dp[i-1]>=0:dp[i]=dp[i-1]+nums[i]
	  //2. dp[i-1]<0:dp[i]=nums[i]
	  public int maxSubArray(int[] nums) {
           int m=nums.length;
           int[] dp=new int[m];
           int max=nums[0];
           dp[0]=nums[0];
           for(int i=1;i<m;i++) {
        	   if(dp[i-1]>=0) {
        		   dp[i]=dp[i-1]+nums[i];
        		   max=Math.max(max, dp[i]);
        	   }else {
        		   dp[i]=nums[i];
        		   max=Math.max(max, dp[i]);
        	   }
           }
           return max;
	    }
	  
	  //343：整数拆分乘积最大
	  //和之前的完全平方数拆分很像，就是切割类dp
	  //设dp[i]表示i进行拆分后，所能达到的乘积最大值（必须拆分），至少拆分成两个数：
	  //1. j先拆分出来j,且后面的依然可以拆分：dp[i]=j*dp[i-j]
	  //2. j拆分出来，但是后面不再拆分，作为整体求解：dp[i]=j*(i-j)          !!!一开始忘了这种情况
	  //取max即可，初值dp[0]=0,dp[1]=0
	  public int integerBreak(int n) {
		  int[] dp=new int[n+1];
		  dp[0]=dp[1]=0;
		  for(int i=2;i<n+1;i++) {
			  for(int j=1;j<i;j++) {
				  dp[i]=Math.max(dp[i], Math.max(j*(i-j), dp[i-j]*j));         //因为本次的dp[i]是要j从1---i-1都取遍了才能确定最大值，所以要再用一个max放在外部
				 
			  }
		  }
		  return dp[n];
	    }
	  
	  //583：编辑距离仅删除
	  //编辑距离的简化版，只能删除，不能替换或者插入
	  //实际上删除==插入，可以A删或者B删dp[i-1][j]+1和dp[i][j-1]+1，替换是另外一种dp[i-1][j-1]+1
	  public int minDistance(String word1, String word2) {
		  int m=word1.length();
		  int n=word2.length();
		  int[][] dp=new int[m+1][n+1];
		  for(int i=0;i<m+1;i++) {
			  dp[i][0]=i;
		  }
		  for(int j=0;j<n+1;j++) {
			  dp[0][j]=j;
		  }
		  for(int i=1;i<m+1;i++) {
			  for(int j=1;j<n+1;j++) {
				  if(word1.charAt(i-1)==word2.charAt(j-1)) {
					  dp[i][j]=dp[i-1][j-1];
				  }else {
					  dp[i][j]=Math.min(dp[i][j-1], dp[i-1][j])+1;
				  }
			  }
		  }
		  return dp[m][n];
	    }
	   
	  //646:最长数对链
	  //这题不能用dp来求解，因为dp求解复杂度肯定更高，直接转化为不重叠子区间问题
	  public int findLongestChain(int[][] pairs) {
		  Arrays.sort(pairs,(a,b)->(a[1]-b[1]));                          //!!!学习一下，按照列值进行升序排序的做法：第一个参数是二维数组，第二个参数写compartor:a，b代表行，a[1],b[1]代表列
		  if(pairs.length==1) return 1;
		  int res=1;
		  int curRight=pairs[0][1];
		  for(int i=1;i<pairs.length;i++) {
			  if(pairs[i][0]>curRight) {
				  res++;
				  curRight=pairs[i][1];
			  }
		  }
		  return res;
	    }
	  
	  //376:最长摆动序列
	  //*****其实不复杂：关键在于要分两种dp数组讨论，和股票问题属于一类，dp数组分状态讨论----------------股票类问题，多个dp数组分状态讨论
	  //设dp[i][0]表示到nums[i]且最后的元素呈上升趋势的最长长度，dp[i][1]表示到nums[i]且最后元素呈下降趋势的最长长度
	  //分类讨论：dp[i][0]
	  //1. nums[i]<=nums[i-1]: 那么最后的元素肯定不能选取，dp[i][0]=dp[i-1][0]
	  //2. nums[i]>nums[i-1]:可以选取加入长度+1,剩下来的到nums[i-1]就是下降趋势序列，也可以直接不选取，dp[i][0]=max(dp[i-1][0],dp[i-1][1]+1)
	  //同理得到dp[i][1]: >=:dp[i][1]=dp[i-1][1]  <:dp[i][1]=max(dp[i-1][1],dp[i-1][0]+1)
	  //初值：dp[0][0]=dp[0][1]=1
	  public int wiggleMaxLength(int[] nums) {
		  int m=nums.length;
		  int[][] dp=new int[m][2];
		  dp[0][0]=dp[0][1]=1;
		  for(int i=1;i<nums.length;i++) {
			  if(nums[i]<=nums[i-1]) {
				  dp[i][0]=dp[i-1][0];
			  }
			  if(nums[i]>nums[i-1]) {
				  dp[i][0]=Math.max(dp[i-1][0], dp[i-1][1]+1);
			  }
			  if(nums[i]>=nums[i-1]) {
				  dp[i][1]=dp[i-1][1];
			  }
			  if(nums[i]<nums[i-1]) {
				  dp[i][1]=Math.max(dp[i-1][1], dp[i-1][0]+1);
			  }
		  }
		  return Math.max(dp[m-1][0], dp[m-1][1]);
	    }
	  
	  //494:所有元素的正负搭配目标和
	  //重点在于转化的过程，逻辑数学问题-----转化完就是很明显的选取不选取问题
	  //设所有元素的和为sum,其中选取若干元素为负数，这些数的和（不加负号时候）为negat,那么正数元素的和就是sum-negat
	  //因为目标和为target=sum-negat+(-negat)=sum-2negat  推出来：negat=(sum-target)/2
	  //所以问题转化为了选取部分元素为负数元素，且他们的和是negat
	  //设dp[i][j]是nums的前i元素，目标和为j的方案数
	  //1. nums[i-1]>j:肯定不能选nums[i-1],dp[i][j]=dp[i-1][j]
	  //2. nums[i-1]<=j:可选可不选，两种方案要加起来,dp[i][j]=dp[i-1][j-nums[i-1]]+dp[i-1][j]
	  //初值：dp[0][0]=1.dp[0][j]=0
	  public int findTargetSumWays(int[] nums, int target) {
		  int m=nums.length;
		  int sum=0;
		  for(int i=0;i<m;i++) {
			  sum+=nums[i];
		  }
		  int diff=sum-target;
		  if(diff<0||diff%2==1) {
			  return 0;
		  }
		  int negat=diff/2;
		  int[][] dp=new int[m+1][negat+1];
		  dp[0][0]=1;
		  for(int i=1;i<m+1;i++) {
			  for(int j=0;j<negat+1;j++) {
				  if(nums[i-1]>j) {
					  dp[i][j]=dp[i-1][j];
				  }else {
					  dp[i][j]=dp[i-1][j]+dp[i-1][j-nums[i-1]];
				  }
			  }
		  }
		  return dp[m][negat];

	    }	  
	  

	  public static void main(String[] args) {
		  String s="leetcode";
		  List<String> wordDict=new ArrayList<>();
		  wordDict.add("leet");
		  wordDict.add("code");
		  System.out.print(1);
	  }

	
	
	
	
	
	
	
	
	
	
	

}

