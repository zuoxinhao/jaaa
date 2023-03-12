package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;


public class dynamicProgramming {
	
	//DP��ϰ�����ܺø�ϰ�����벻���ӣ�˼·������������������״̬ת�Ʒ��̼��ɡ�
	
	//DP�ܽ᣺
	//1. DP����Ҫ��һ�㣺����DP�������洢��������Ľ⣬�Ӷ��������⡣
	//2. dp���ģ�����ǰ��n-1��С����Ľ�������n�����⣬����ǰ������������Ľ��������һ������Ľ⡣
	//3. ����������������ģ������в���Ҫ�������Ȳ���������dp[i]����nums[i]Ϊ��β�ġ����������ı�������Ϊ��ʲôΪ��β
	//4. ������Ȳ��������Ż����⣬����DP���������Ż�����ġ��磺01�������0�������⣺ʹ�����ϣ����³�������DPȡmin����
    //����dp��������true/false֮��ģ����絥���и�
	//5. �ǣ����������֤������̫���ӣ�˼·����������α߳�=min(���ϣ�������������α߳�)+1
	//���̲�����ȫ��ת��Ϊ���ȸ���һ�κ�ͣ��ճ����������i������j�����ϸ���: dp[i]=min(i,dp[j]+i/j)
	//6. *****��������91�������̨�����⣬ͨ��ֻ�������һ����������ֻ���������ͣ�ע�������ֻʣ��λ��Ҫ���⴦��
	//7. *****�����и�139�������ĩβƥ�䷨����ĩβ�Ƿ�����и�Ϊ�ֵ��е��������ơ�dp[i]=dp[j]||dp[i](dp[i]��ʾ����иȡ��һ���ɹ�����)  
	//8. **ע��һ�£�LIS����������г�ֵ������dp[i]=1,��Ϊ��dp[i]����s[i]Ϊ��β�������г��ȣ��϶�������s[i],������1
	//**LCS�����������dp[i][j]��Ӧs[i-1] t[j-1]������մ�����Ϊ0
	//9. ȡmax---��ֵ���û�о�������Ҫ����ΪInteger.Min_Value/2 ȡmin---��ֵ��Ҫ����Ϊ���ֵ/2
	//10. *****�����������01����ȫ�������������ڳ�ֵ���⣺01����ȫ��ֵ����0���ɣ���������������ֻ�б������Ĳ�����Ч״̬
	//*****���ó�ʼ��������Ϊ0����0��������Ϊ0������ΪMin/2.���õ������ǣ��������±��������½���һ�δεĽ��ͱ�������ֻ�н�Ϊ0��
	//���������˿ռ䣬������ȷ�ı���·��������������ֵ��������0�ľ����ó�ֵΪMin/2.
	//11. �������⣺ѡȡ��ѡȡ�У���һ��Ҫ���ж���ʣ������s[i-1]�Ĵ�С��ϵ������Ѿ������ˣ��Ǿ�ֻ����������Ʒ�������˵�һ�����ж�
	//12. *****��Ҫע��㣺�����ģ�����Ҫ��DP��������XXXΪ��β�������С����Ȼ�ͻᵼ��������������ֵ�������磺���������ĺ�
	//������dp[i]����a[i]Ϊ��β�����������ĺͣ�dp[i-1]>0 dp[i]=dp[i-1]+a[i] dp[i-1]<0 dp[i]=a[i]
	//13. *****���ӹ�Ʊ����ĺ��ģ�״̬��DP����һ��ά�ȱ�ʾ״̬����Ʊ�Ļ���״̬�ǣ����У�����������״̬������---���ǵ�i���Ƿ���
	//������---���ǵ�i���Ƿ��������͵�i-1������⹫ʽ�������������ۼ������
	//14. **�������飬��β���ܼ�ã��������ǰ�0-n-2��1-n-1�ֳ��������ۼ��ɣ�ȡ�����Сֵ��
	//15. ��ڶ����У��������״̬��DP����dp[i][0]��nums[0]-nums[i]���������Ƶ�����max���ȣ�Ȼ��Ѱ��nums[i] nums[i-1]��С��ϵ����
	//16. *****�ص��⣺494�����������⣺������ѧ��ʽת��Ϊ������ѡȡ����(Ŀ���=(sum-target)/2)��ע�����ѡȡ����
	//���Ǳ������⣬�����������С��ֵ���������Ƿ�������������nums[i]<j��ѡ�����dp[i][j]=dp[i-1][j]+dp[i-1][j-nums[i]]
	//17. *****�ص���312������������ⲻ�ý⣬���Է���˼����ɼ�����Ȼ��ת��Ϊ��dp[i][j]=max(dp[i][k]+dp[k][j]+s[k]*s[i]*s[j])
	
	
	//*****dp���ֽ���˼·��
	//1. ��ͨ��һά��άdp:��dp��ʾ��Ȼ���ҳ���dp[i-1],dp[i-2]����dp[i+1]�ĵ��ƹ�ϵ;
	//����������ǰ���ĳ��j��dp[j]�����
	//2. �и���dp:���پ������ø�����dp��ϵ�������ض�����״̬�µ�dp������˵��ȫƽ����(ǰ���ĳ��j��Ϊ��ȫƽ������ֳ�ȥ��+1)���ַ�������
	//3. ���������ת������ѡȡ��ѡȡ����������ֵ���⣬��ת��Ϊ�˱������⣬��Ҫ��������Ԫ��ѡȡ��ѡȡ��ת��Ϊ01��������ȫ������������
	//4. ״̬��dp,��dp�������⣺����ʹ�õ�һһ��dp���飬���ǲ�ͬ״̬�ò�ͬ��dp��������ʾ�����Խ��ʹ��һ���ĵڶ�ά��[0][1],��Ҫ�漰���ǹ�Ʊ���⣬�ڶ���������
	
	
	//*****�ָ���dp:
	//��dp���鲻���������������λ��״̬ת�Ʒ��̣����磺dp[i-1],dp[i-2]���֣����������ض��ָ�������dp
	//����ʵ����˼�뻹����ǰi-1���Ƴ���i����ֻ��������ʹ������λ�ù�ϵ�����Ǹ�����Ŀ�����������ҹ�ϵ��
	  
	//�и���dp:��Ҫ������ĿҪ�����Ҳ�ͬλ�õ�״̬ת�ƣ���ǰi-1����Ѱ������ķֳ�ȥ��Ȼ����ƣ�����������Сֵ
	  
	//*****��Ϊ���֣������и�ڳ�������������һ���֣�Ȼ��ʣ�µ���ô����ô�������ַ����и����һ��������λ�����֣�
	
	
	//70����¥������
	//���Ʒ��̣�ʵ���ϵ��Ʒ���Ҳ�Ƕ����һ�֣���Ϊ����Ҳ�Ǵ�ǰ���������Ľ�õ������k���Ľ�
	//��ͼ�õ��˿ռ�ѹ������Ϊÿ��Ԫ��ֻ��֮ǰ������Ԫ���йأ����Կ���ֱ��ֻ����ÿ�ε�ǰ����ֵ��������������ά�����ɣ�ÿ��ȡ��ֵ���������¡�
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
	
	//198:��������͵������
	//��ȷ˼·��
	//��dp[k]��С͵�����±�Ϊk����ʱ�ܻ�õ�������ǰ���������Ľ��ϣ���Ϊ���֣�
	//��k��û͵��dp[k]=dp[k-1]
	//͵�ˣ���ô��k-1���϶�����͵��dp[k]=dp[k-2]+nums[k]
	//����ȡmax��Ϊ���ε�k�ܻ�õ����ֵ
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
	  
	  //413���Ȳ�������ĸ���
	  //��ȷһ���㣬������ĸ���������������Ľ�ȡһ�Σ��Ž������飬����Ĳ��������顣������ĸ�����Ǳ���������
	  //��dp[i]Ϊ��nums[i]Ϊ��β�ĵȲ�������ĸ�����dp[i+1]��Ϊ���֣�
	  //1. nums[i+1]-nums[i]!=nums[i]-nums[i-1]:��Ϊ����������ȡ�����ģ�����������������㣬��ô���еĶ������㣬Ϊ0
	  //2. nums[i+1]-nums[i]==nums[i]-nums[i-1]==k:����������ɵȲ����У���+1��Ȼ����Ϊǰ�����е���nums[i]��β�ĵȲ����飬��Ȼ����nums[i-1],���ԵȲ�����Ĺ�����һ���ġ�����dp[i]
	  //�ڵ����鶼����ֱ������nums[i+1](��Ϊ����һ��)������dp[i+1]=dp[i]+1
	  //�������������ĵȲ��������������dp�������
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
	  
	  //64����С·����
	  //��dp[i][j]�ǵ���[i,j]λ�õ����·����.��Ϊ��λ�ã�ֻ������������������������ҵ���������ȡmin����
	  //dp[i][j]=min(dp[i-1][j],dp[i][j-1])+grid[i][j]
	  //��ֵ����Ϊ0.��Ϊ0����Ҫ����
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
	
	  //�ռ�ѹ�������������һ����ǰ��ʾ��˼�룺������dp[i][j]��Ҫ��ֵʱ��min�����dp[i][j-1]���Ǳ���i�Ѿ�����ĵ�dp[j-1].
	  //Ȼ��dp[i-1][j]����һ��i-1ʱ���dp[j]����Ϊ����dp[i][j]��û���£���i���dp[j]��û���¡���ô�պ�min�и�Ϊdp[j]���Ǵ������һ���dp[j],����ѹ���ɹ�
	  
	  //542:01�������0����
	  //��һ��Ӧ�û��ݷ����������ǱȽϸ��ӣ��������ݺ����������0���ĸ�������Ҫ������չ��Ӧ���ù�����ȡ������������ݷ����������Ż����⣬���ǹ�����ȿ���
	  //������Ż������⣬һ�㻹�ǵ��ö�̬�滮
	  //��dp[i][j]�ǵ�[i][j]λ�õ����0�ľ��룬���Ǽ���ֻ�����������ߣ�������һ��һ��
	  //1. mat[i][j]==0: dp[i][j]=0
	  //2. mat[i][j]!=0: ֻ�ܴ�������϶�����dp[i][j]=min(dp[i-1][j],dp[i][j-1])+1
	  //Ȼ�����ֻ�����£��ֿ��Ը���һ�Σ�Ȼ�����£�Ȼ�����ϣ����ĸ����򶼸������ˣ��������з��򶼰����ˣ����µ����Ϊ��
	  //�򻯣�ֻҪ���ϣ������������򼴿ɡ������֤�����Ǵ��Եģ�����˽⣬�������򼴿�֤�����մ𰸡����θ��¼��ɵõ��𰸰�
	  public int[][] updateMatrix(int[][] mat) {
		  int m=mat.length;
		  int n=mat[0].length;
		  int[][] dp=new int[m][n];
		  for(int i=0;i<m;i++) {
			  Arrays.fill(dp[i], Integer.MAX_VALUE/2);              //!!!���븳��ֵΪ�ϴ�ֵ����Ȼ����Math.min���棬�ᵼ�³�ֵΪ0��ÿ��ȡmin����0ռ���ƣ�����
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
					  dp[i][j]=Math.min(dp[i][j], dp[i-1][j]+1);    //Ϊ�˽���߽����⣬������ȡmin�������ӾͿ��Լ򻯱߽����⣻ͬʱ�����ˣ�ÿ��min��Ҫ���ϵ�ǰֵȡmin,�������Ǹ���
				  }                                                 //����ͬʱ�����mat[i][j]=0ʱ�򣬿���ֱ�ӵõ�0���������Ϊdp[i][j]=0,�϶������Ʒ����������ﲻ���������ж�mat[i][j]
				  if(j-1>=0) {                                      //�Ƿ�Ϊ0
					  dp[i][j]=Math.min(dp[i][j], dp[i][j-1]+1);
				  }
			  }
		  }
		  for(int i=m-1;i>=0;i--) {
			  for(int j=n-1;j>=0;j--) {
				  if(i+1<m) {
					  dp[i][j]=Math.min(dp[i][j], dp[i+1][j]+1);    //min���ϵ�ǰֵ����Ϊ�˸���ֵ��
				  }
				  if(j+1<n) {
					  dp[i][j]=Math.min(dp[i][j], dp[i][j+1]+1);
				  }
			  }
		  }
		  return dp;
	    }
	  
	  //221�����������
	  //��dp[i][j]Ϊ��matrix[i][j]Ϊ���½ǵ���������εı߳�������ע���Ǳ߳����������
	  //1. matrix[i][j]==0: dp[i][j]=0
	  //2. matrix[i][j]==1: ��ʱ��������Σ�Ӧ�����󷽣��Ϸ������Ϸ���������α߳�����Сֵ�����ϱ�Ԫ�صĳ��ȣ���+1
	  //dp[i][j]=min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])+1
	  //�������Ҫ֤�����ر��ӣ�����Ͳ�׸����
	  //��ֵ��i=0�ı߽��ϣ�Ϊ1�����Ϊ1��j=0�ı߽��ϣ�Ϊ1�����Ϊ1.
	  public int maximalSquare(char[][] matrix) {                     //С�ģ�������char���͵Ķ�ά���飬����int
		  int m=matrix.length;
		  int n=matrix[0].length;
		  int[][] dp=new int[m][n];
		  int max=0;
		  for(int i=0;i<m;i++) {
			  if(matrix[i][0]=='1') {
				  dp[i][0]=1;
				  max=1;                                              //��1��Ҫ����max,��������ܱ���1�������ڲ���1���������
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
	  

	  //279����ȫƽ����
	  //��dp[i]��ʾi���ٲ�ֵ�ƽ�����ĸ���
	  //i�����ȷֳ�1��4��9��16...Ȼ�������ʣ�µ�i-1,i-4,i-9,i-16...��dp����ֵ+1���ɣ�����Ҫȡminֵ
	  //dp[i]=min(dp[i-1],dp[i-4],dp[i-9],dp[i-16]...)+1
	  //�����ĺ��ľ��ǣ�����һ����ת��Ϊǰi-1��Χ�ڵģ�Ȼ����dp
	  public int numSquares(int n) {
		  int[] dp=new int[n+1];
		  dp[0]=0;
		  for(int k=1;k<=n;k++) {
			  dp[k]=Integer.MAX_VALUE;                           //��ʼֵҪ��ֵ���ֵ��Ϊ�˺������dp[i]�ڲ������������ֵ��ʼ��Ϊ0��������minһ������0������
		  }
		  for(int i=1;i<=n;i++) {
			  for(int j=1;j*j<=i;j++) {                          //С�ģ���<=i,����n,��ǰ���Ӧ�������i
				  dp[i]=Math.min(dp[i], dp[i-j*j]+1);
			  }
		  }
          return dp[n];
	    }
	  
	  //91����������
	  //�����и���ɣ����Ǻ����ڵĹ�ϵ
	  //��dp[i]����s[i]��β���ַ����Ľ�����������dp[i-1],dp[i-2]...�Ĺ�ϵ
	  //1. ���һ���������룬��s[i]!=0: ���һ����ĸ���������뼴�ɣ���ôǰi-1���Ľ����ֱ�Ӹ������һ����ĸ���ɣ�����dp[i]=dp[i-1]
	  //2. �������һ����룬s[i-1]!=0&&�����������ɵ���С�ڵ���26���������һ����룬��ǰ��i-2�������ֱ꣬�Ӹ������ģ�dp[i]=dp[i-2]
	  //һ��ֻ�������ֿ��ܣ���Ӽ��ɵõ�dp[i]
	  //���⣺�ַ���0��ͷ�Ļ����Ǿ�û�취���룬��Ϊ��ͷ��0û�������𰸾���0
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
			  if(i-2==-1&&s.charAt(i-1)!='0'&&((s.charAt(i-1)-'0')*10+(s.charAt(i)-'0'))<=26) {   //���ǵ���12�����������������������ɽ��룬����ǰ��û���ˣ�����Ҫֱ��+1����
				  dp[i]+=1;
			  }
		  }
		  return dp[m-1];
	    }
	  
	  //139�������и�����
	  //*****dp�������������Ż������ҿ�������true/false������
	  //��dp[i]��ʾ����s[i-1]Ϊ��β�ĵ����Ƿ���Գɹ��и��β��ֱ�ӿ�ʼ�и�
	  //1. β���������ֵ��и�����и���ֵ䣬�õ�jλ���и��dp[i]=dp[j]||dp[i]    ��||dp[i]��ԭ���ǿ���ǰһ���ֵ��е��и����true,�ǾͿ���ֱ��return true�ˣ�����Ҫ��||dp[i]
	  //2. β�������и�Ǿ���false,�������˳�ʼ��false��
	  
	  //*****java���ж������ַ����ڵ�Ԫ���Ƿ���ͬ���õ���equals������������==��==ֻ���ж��Ƿ�ָ��ͬһ���ַ�����
	  //���ڰ�װ���ͣ��ַ�����java�ڲ���д����equals��������==���ǱȽ��Ƿ�ָ��ͬһ���󣬶����ǱȽ����ݣ��ж�����ֻ����equals���������ڻ����������ͣ�==���ǱȽ�ֵ
	  //�����������ͣ�������ֶ���дequals���Ǿ���equals��==һ����ֻ���ж��Ƿ�ָ��ͬһ������д��equals�����ж�����
	  //�򵥶��ԣ�ֻ����д��equals�Ĳ����ж����ݣ��ַ�������װ��java�ڲ��Ѿ���д���ˣ������������;���==�ж�ֵ��ֱ��==�ľ����жϵ�ַ
	  public boolean wordBreak(String s, List<String> wordDict) {
		  int m=s.length();
		  boolean[] dp=new boolean[m+1];
		  dp[0]=true;
		  for(int i=1;i<=m;i++) {
			  for(int k=0;k<wordDict.size();k++) {
				  int len=wordDict.get(k).length();
				  if(i>=len&&s.substring(i-len, i).equals(wordDict.get(k))) {           //ֻ����equals��һ��ҪС�İ�
					  dp[i]=dp[i]||dp[i-len];
				  }
			  }
		  }
		  return dp[m];
	    }
	  
	  //*****���������⣺
	  //�����к�������������������������Ԫ�أ������п��Բ�������
	  //���������⣬��������������䳣��������Ҫ���ǳ�ֵ���⣬Ҫע��һ��
	  
	  //300��LIS�����������
	  //��dp[i]����nums[i]Ϊ��β������������еĳ���
	  //dp[i]=max{dp[j]+1},����nums[j]<nums[i]&&j<i
	  //!!!��ʼ�������е�dp����ֵ����Ҫ��Ϊ1����Ϊ���ֻ��dp[0]=1�Ļ�����Ϊ����Ĳ�һ������nums[0]���ᵼ������dp[0],��ô�Ϳ�����������ı���dp[1]=0,Ӱ���˺���ļ���
	  //����LIS�ĳ�ʼ������Ҫ���е����鶼�ó�ֵ1.
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
	  
	  //1143��LCS�����������
	  //��dp[i][j]��ʾtext1[i]��text2[j]��β���ַ��������������
	  //1. text1[i]==text2[j]: dp[i][j]=dp[i-1][j-1]+1
	  //2. text1[i]!=text2[j]: dp[i][j]=max(dp[i-1][j],dp[i][j-1])   ������⣺ĩβֵ���ȣ���Ȼ��һ����Ҫ��������
	  //!!!����д������ֵ�Ƚ��鷳��������øĳ�text1[i-1],text2[j-1]��β���մ�ֱ�Ӹ�ֵΪ0����Ϊ�Զ���ʼ������0�����Բ�����ȥ���⸳ֵ��
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
	  
	  
	  
	  //���������⣺һ�㶼�ǴӾ�������ת��Ϊ�������⣺ѡȡ��ѡȡ���⣬�������⣬��ֵ����
	  //�ĵ���������������������01��������ȫ���������������j��������˼���ֿռ�ѹ�������õľ��Ǳ���˳����dp[i][j]ʱ��ȷ��dp[i-1][j-w]����dp[i][j-w]һ��Ҫ����dp[i][j]������ֵ
	  //*****ȷ����dp[j-w]�Ǳ���Ļ����ϲ�ģ�������Ҫ����ֵ������Ҫȷ������һ���ֵ
	  
	  //*****�й��Ӽ������⣬�������ת��Ϊ01�������⣺Ԫ��ѡȡ��ѡȡ���⣬��������
	  //*****����������Ӽ����Ӽ���Ԫ�أ�ѡȡ����-----01��������
	  
	  //*****���������⣺����ͨ�����Ĳ�ͬ���ڳ�ֵ
	  //��ͨ������ֵ����0
	  //���������⣺ֻ������Ϊ0��״̬������Ч״̬������Ϊ0������������Ϊ0�ľ�����Ч״̬������ΪMIN����������min������ΪMAX/2��
	  //*****�Ƶ������dp[i][j]װ��������ôִ��max( , )ʱ�򣬼�ʹ��ȡ����һ�ݣ������dp[i-1][j-w],��Ȼ�ǲ�����װ���ģ�����˵�����ڲ���������MIN�����Դ𰸻���MIN
	  
	  //*****��ֵ��0��Ӧnums[-1]ʱ����ʵ���Ǵ���numsΪ�մ�ʱ������
	  
	  
	  
	  
	  
	  
	  //416���ָ�Ⱥ��Ӽ�
	  //������ת��Ϊ01�������⣺��ѡȡ��ѡȡ����,��������
	  //��������Ҫ��ת��˼·������Ϊ�Ⱥ������Ӽ���Ҳ����Ѱ��һ���Ӽ��ĺ��������һ�뼴�ɡ������������������Բ���sumΪ����
	  //��dp[i][j]��ʾnums[0]-nums[i]����ȡ�Ӽ���ʹ�ú��ܷ񵽴�j
	  //1. j<nums[i]: nums[i]�϶����ܷ����Ӽ����൱�ڱ����������ˣ���dp[i][j]=dp[i-1][j]
	  //2. j>=nums[i]: nums[i]�ɷſɲ��ţ�dp[i][j]=dp[i-1][j]||dp[i-1][j-nums[i-1]]
	  //��ֵ��j==0,�Ǿ���dp[i][0]=true;ֱ�Ӿ���Ŀ��ֵ����0��Ϊtrue��
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
	  
	  //474��һ��������
	  //ת��Ϊ01�������⣺ѡȡ��ѡȡ����������m,n
	  //��dp[i][j][k]��ʾѡȡstrs[0]-strs[i-1],��0,1����С��j,k������Ӽ�����
	  //1. strs[i-1]��0,1���������� dp[i][j][k]=dp[i-1][j][k]
	  //2. С�ڵ��������� dp[i][j][k]=max(dp[i-1][j-0��][k-1��]+1,dp[i-1][j][k])
	  //��ֵ���ͱ�������һ��������0.dp[i][0][0]=0,Ϊ0����ʼ������0.
	  public int findMaxForm(String[] strs, int m, int n) {
		  int l=strs.length;
		  int[][][] dp=new int[l+1][m+1][n+1];
		  for(int i=0;i<l+1;i++) {
			  for(int j=0;j<m+1;j++) {
				  for(int k=0;k<n+1;k++) {
					  if(i-1>=0&&(count474(strs[i-1])[0]>j||count474(strs[i-1])[1]>k)) {              //ע���£�dp�й涨��strs[i-1]����һ������д����
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
	  
	  //322��Ӳ������
	  //��ȫ����������������
	  //��dp[i][j]��ʾcoins[0]-coins[i-1]�л�ȡj������װ������СӲ����
	  //1. ����װ��dp[i][j]=dp[i-1][j]
	  //2. ��װ��dp[i][j]=min(dp[i-1][j],dp[i][j-coins[i-1]]+1)
	  //��ʼֵ����Ч״̬������Ϊ0ʱ��Ϊ0����Ч״̬��������Ϊ0������ΪMAX/2
	  public int coinChange(int[] coins, int amount) {
		  int m=coins.length;
		  int[][] dp=new int[m+1][amount+1];
		  for(int i=0;i<m+1;i++) {
			  for(int j=0;j<amount+1;j++) {
				  dp[i][j]=Integer.MAX_VALUE/2;                       //!!!!!�ǳ���Ҫ����2��ԭ������Ϊ�����м�1��Ϊ�˷�ֹ���������ֱ�������ֵ��ֻ��/2����
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
	  
	  
	  //72���༭����
	  //̫�����ˣ���ʽ������д��
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
	  
	  //650:���̲���
	  //״̬ת�Ʒ��̱Ƚ����뵽���õ���������ϵ
	  //������⣺ÿ��Ҫôֱ��ճ����Ҫô�ȸ�����ճ����ʵ������һ������ġ���Ϊ������������ճ����ʵ���Ͼ���ԭ����k���١�2�����Ե�i�������һ���ǵ�j���ı�����ϵ��
	  //ֱ��ճ����k,2k,3k,4k......
	  //����ճ����k,2k,3k(����ճ��),6k,9k......ֻ���൱�ڼӿ����ٶȶ��ѣ���ʵ����k�ı���
	  //��dp[i]��ʾ����i��A�����ٲ�������
	  //�ҵ�i%j==0,�ҵ�i��������Ȼ���ȵ���j��Ȼ����и���,ճ����ճ����ճ������������������i
	  //dp[i]=min(dp[j]+i/j-1+1)=min(dp[j]+i/j)
	  //��ֵ��dp[1]=0,dp[0]�����壬�����ģ�Ϊ��ѭ���е�min���£�Ҫ���ó�ֵΪ���ֵ(i,����һ����Ȼ��ͣճ��)
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
	  
	  //10����hard,��⣩����ƥ��
	  //��β����ʼƥ�䷨,��ʵ����״̬ת�Ʒ���Ҳ���ѣ�һ������β���Ƴ������ɡ�
	  //��dp[i][j]��ʾs��ǰi���ַ���p��ǰj�����Ƿ�ƥ��
	  //1.  p��ĩβ����ĸ���Ǿ�ֱ�����߿����Ƿ�ƥ�䣬ƥ����dp[i-1][j-1],��ƥ�����false
	  //2.  p��ĩβ�ǡ�.��,����ƥ�䣬����dp[i-1][j-1]
	  //3.  p��ĩβ�ǡ�*��,��Ϊ���֣�s[i]��p[j-1]��ͬ��������ƥ��һ�ε���Σ��Ǿ���ѡ��ƥ��/��ƥ�䣬Ϊdp[i-1][j]��dp[i][j-2]
	  //����ͬ���Ǿ�û��ѡ�񣬿϶�ƥ��0�Σ�Ϊdp[i][j-2]
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
	  
	  //*****��Ʊ�����⣺
	  //1. �򵥵ģ���ͨ�������������ĺ����⣬���ǿ���ֱ��ת������������⼴��
	  
	  //2. ���ӵģ�k�ʽ������⣬�䶳�����⣬����������
	  //*****��״̬��������⣺
	  //��dp[i][0],dp[i][1],dp[i][2]Ϊ�й�Ʊ���޹�Ʊ���䶳��...��ͬ״̬�ı�ʾ
	  //Ȼ�����õ�i���״̬���͵�i-1���״̬�����Ƶ����Ϳ��Եõ�״̬ת�Ʒ��̡�
	  
	  
	  
	  
	 
	  //121����Ʊ����(���������ĺ�)
	  //��������Ȼ��ת��Ϊ�������������ĺ�
	  //dp[i]Ϊ��a[i]Ϊ��β�����������ĺ�,dp[i-1]С��0����dp[i]=a[i];dp[i-1]>=0,dp[i]=dp[i-1]+a[i]
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
	  
	  //188Ҳ������dp[i][0][k],dp[i][1][k]����⣬�൱��������ά������ʾ���Ͳ��÷ֳ�����������
	  
	  //188:��hard,��⣩��Ʊ����k������
	  //����������˶�ά��������⣬���ҷ�Ϊ�˳��У������й�Ʊ��������
	  //��buy[i][j]��i���У�������j�ʽ��������г��й�Ʊ���������sell[i][j]��price[0]-price[i]�У�������j�ʽ���������û�й�Ʊ���������
	  //����buy[i][j]:���еĹ�Ʊ�Ƿ��ǵ�i������ģ�
	  //1. �ǣ�buy[i][j]=sell[i-1][j]-prices[i]
	  //2. ���ǣ�buy[i][j]=buy[i-1][j]
	  //����sell[i][j]û�й�Ʊ���Ƿ��ǵ�i�������ģ�
	  //1. �ǣ�sell[i][j]=buy[i-1][j-1]+prices[i]
	  //2. ���ǣ�sell[i][j]=sell[i-1][j]
	  //����������������ȡ���ֵ����ֵ��buy[0][0]=-prices[0],sell[0][0]=0,buy[0][j],sell[0][j]���ǲ��Ϸ�״̬������ΪMIN
	  public int maxProfit188(int k,int[] prices) {
		  int n=prices.length;
		  if(n==0) return 0;
		  k=Math.min(k, n/2);
		  int[][] buy=new int[n][k+1];
		  int[][] sell=new int[n][k+1];
		  buy[0][0]=-prices[0];
		  sell[0][0]=0;
		  for(int i=1;i<=k;i++) {
			  buy[0][i]=sell[0][i]=Integer.MIN_VALUE/2;      //��ΪҪȡmax
		  }
		  for(int i=1;i<n;i++) {
			  buy[i][0]=Math.max(buy[i-1][0], sell[i-1][0]-prices[i]);                    //�൱�ڰ�j=0�ù�����
			  for(int j=1;j<=k;j++) {
				  buy[i][j]=Math.max(buy[i-1][j], sell[i-1][j]-prices[i]);
				  sell[i][j]=Math.max(sell[i-1][j], buy[i-1][j-1]+prices[i]);
			  }
		  }
		  return Arrays.stream(sell[n-1]).max().getAsInt();
	  }
	  
	  //309����Ʊ���󣨰����䶳�ڣ�
	  //״̬�Զ����ⷨ
	  //��������Ŀһ����Ҳ�ǰѳ��й�Ʊ�������й�Ʊ��Ϊ���������ͬʱ���⻹���䶳�ڣ�Ҳ��״̬
	  //���ԣ�dp[i][0]:��ʾ��i����������ҳ��й�Ʊ��dp[i][1]��ʾ��i���������û�й�Ʊ�Ҵ����䶳�ڣ�dp[i][2]��ʾ�������û�й�Ʊ�Ҳ����䶳��
	  //1. dp[i][0]: �Ƿ��ǵ�i�������ģ�=max(dp[i-1][0],dp[i-1][2]-prices[i])
	  //2. dp[i][1]: �����䶳�ڣ�ֻ���ǵ�i-1�������ˣ�=dp[i-1][0]+prices[i]
	  //3. dp[i][2]: �����䶳�ڣ���i-1��϶��������˹�Ʊ��i-1��������䶳�ڻ��߷��䶳�ڣ�=max(dp[i-1][1],dp[i-1][2])
	  //��ֵ��dp[0][0]=-prices[0],dp[0][1]=0,dp[0][2]=0
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
	  
	  //714:��Ʊ�������۳��������ѣ�
	  //��������Ŀһ������dp[i][0]��ʾ��������������й�Ʊ��dp[i][1]��ʾ�������������û�й�Ʊ��dp�����i��prices����һ������0��ʼ��
	  //�ֳɣ���i���Ƿ����룬��i���Ƿ�����
	  //dp[i][0]=max(dp[i-1][0],dp[i-1][1]-prices[i])
	  //dp[i][1]=max(dp[i-1][1],dp[i-1][0]+prices[i]-fee)      ������ע����prices[i],��Ϊ�ǵ�i-1�������й�Ʊ����i�����������������+prices[i]  ������ļ۸���֮ǰ�Ѿ�����ˣ�����ֱ����������������
	  //��ֵ��dp[0][0]=-prices[0] dp[0][1]=0
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
	  
	  //��ϰ�⣺
	  
	  //213��С͵����2
	  //�����������⣺��β����һ���Ǿͷֳ����֣�0-n-2Ϊ��һ�֣�1-n-1Ϊ�ڶ��֣�����ȡmax����
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
	  
	  //53:���������ĺ�
	  //dp[i]�ǰ�����nums[i]�����������ĺ�
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
	  
	  //343��������ֳ˻����
	  //��֮ǰ����ȫƽ������ֺ��񣬾����и���dp
	  //��dp[i]��ʾi���в�ֺ����ܴﵽ�ĳ˻����ֵ�������֣������ٲ�ֳ���������
	  //1. j�Ȳ�ֳ���j,�Һ������Ȼ���Բ�֣�dp[i]=j*dp[i-j]
	  //2. j��ֳ��������Ǻ��治�ٲ�֣���Ϊ������⣺dp[i]=j*(i-j)          !!!һ��ʼ�����������
	  //ȡmax���ɣ���ֵdp[0]=0,dp[1]=0
	  public int integerBreak(int n) {
		  int[] dp=new int[n+1];
		  dp[0]=dp[1]=0;
		  for(int i=2;i<n+1;i++) {
			  for(int j=1;j<i;j++) {
				  dp[i]=Math.max(dp[i], Math.max(j*(i-j), dp[i-j]*j));         //��Ϊ���ε�dp[i]��Ҫj��1---i-1��ȡ���˲���ȷ�����ֵ������Ҫ����һ��max�����ⲿ
				 
			  }
		  }
		  return dp[n];
	    }
	  
	  //583���༭�����ɾ��
	  //�༭����ļ򻯰棬ֻ��ɾ���������滻���߲���
	  //ʵ����ɾ��==���룬����Aɾ����Bɾdp[i-1][j]+1��dp[i][j-1]+1���滻������һ��dp[i-1][j-1]+1
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
	   
	  //646:�������
	  //���ⲻ����dp����⣬��Ϊdp��⸴�Ӷȿ϶����ߣ�ֱ��ת��Ϊ���ص�����������
	  public int findLongestChain(int[][] pairs) {
		  Arrays.sort(pairs,(a,b)->(a[1]-b[1]));                          //!!!ѧϰһ�£�������ֵ���������������������һ�������Ƕ�ά���飬�ڶ�������дcompartor:a��b�����У�a[1],b[1]������
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
	  
	  //376:��ڶ�����
	  //*****��ʵ�����ӣ��ؼ�����Ҫ������dp�������ۣ��͹�Ʊ��������һ�࣬dp�����״̬����----------------��Ʊ�����⣬���dp�����״̬����
	  //��dp[i][0]��ʾ��nums[i]������Ԫ�س��������Ƶ�����ȣ�dp[i][1]��ʾ��nums[i]�����Ԫ�س��½����Ƶ������
	  //�������ۣ�dp[i][0]
	  //1. nums[i]<=nums[i-1]: ��ô����Ԫ�ؿ϶�����ѡȡ��dp[i][0]=dp[i-1][0]
	  //2. nums[i]>nums[i-1]:����ѡȡ���볤��+1,ʣ�����ĵ�nums[i-1]�����½��������У�Ҳ����ֱ�Ӳ�ѡȡ��dp[i][0]=max(dp[i-1][0],dp[i-1][1]+1)
	  //ͬ��õ�dp[i][1]: >=:dp[i][1]=dp[i-1][1]  <:dp[i][1]=max(dp[i-1][1],dp[i-1][0]+1)
	  //��ֵ��dp[0][0]=dp[0][1]=1
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
	  
	  //494:����Ԫ�ص���������Ŀ���
	  //�ص�����ת���Ĺ��̣��߼���ѧ����-----ת������Ǻ����Ե�ѡȡ��ѡȡ����
	  //������Ԫ�صĺ�Ϊsum,����ѡȡ����Ԫ��Ϊ��������Щ���ĺͣ����Ӹ���ʱ��Ϊnegat,��ô����Ԫ�صĺ;���sum-negat
	  //��ΪĿ���Ϊtarget=sum-negat+(-negat)=sum-2negat  �Ƴ�����negat=(sum-target)/2
	  //��������ת��Ϊ��ѡȡ����Ԫ��Ϊ����Ԫ�أ������ǵĺ���negat
	  //��dp[i][j]��nums��ǰiԪ�أ�Ŀ���Ϊj�ķ�����
	  //1. nums[i-1]>j:�϶�����ѡnums[i-1],dp[i][j]=dp[i-1][j]
	  //2. nums[i-1]<=j:��ѡ�ɲ�ѡ�����ַ���Ҫ������,dp[i][j]=dp[i-1][j-nums[i-1]]+dp[i-1][j]
	  //��ֵ��dp[0][0]=1.dp[0][j]=0
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

