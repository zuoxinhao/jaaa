package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;


public class divideAndConquer {
	
	//*****�����ܽ᣺
	//1. master����T(n)=aT(n/b)+f(n)   f(n)��n^(logba)�Ƚϴ�С��˭��ȡ˭������ٳ�logn
	//2. **�����ŵĽ����dp[l][r]=dp[l][k-1]  nums[k](�������ҷֿ�)  dp[k+1][r]

	
	//һ�������������÷��η��ݹ����ģ��ǾͿ����ö�̬�滮�Ե�������⡣������Ҫ��һ��˼�룬�������ڽ�������⣬����֮��
	//*****һ�㲻ֱ���÷��������⣬�����¶��Ƕ��ֺͶ�̬�滮�����⡣
	
	//*****�����㲻��һ���������㷨�⣬�������Ƿ���=����+dp,���Ե����ķ��κ��ټ�������ʹ�õĻ��Ƕ��֣�����dp�����
	
	//�������Զ����£�dp���Ե����ϣ�����һ����ζ�ֱ����dp����дҲ������⡣
	
	//241�������ŵ�������
	//����Ҳ�������ҷ�dp���飬�м��ò���������
	//�����÷��εݹ����д����Ҫ�������顣��dpҲ���ѣ�������΢�����һ��
	//���Ƚ����֣����������뵽һ���б����棬Ȼ����dp[l][r]��ʾ��l��r���п��ܵ�������
	//dp[l][r]= ��ĳһ��������п��������op�Ҳ���  ���뱾�ν��
	//dp[l][r]=dp[l][k-1] ops[k](�����) dp[k+1][r]
	//��ֵ��dp[i][i]=��������ops[i]��Ϊ�����ʱ��
	static final int addition=-1;
	static final int sub=-2;
	static final int multi=-3;
	 public List<Integer> diffWaysToCompute(String expression) {
		 List<Integer> ops=new ArrayList<>();
		 for(int i=0;i<expression.length();) {                               //���ַ�ת��Ϊ������list
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
				 while(i<expression.length()&&Character.isDigit(expression.charAt(i))) {            //����ת��������45��26���ִ���9����
					 t=t*10+expression.charAt(i)-'0';
					 i++;
				 }
				 ops.add(t);
			 }
		 }
		 List<Integer>[][] dp=new List[ops.size()][ops.size()];
		 for(int i=0;i<ops.size();i++) {
			 for(int j=0;j<ops.size();j++) {
				 dp[i][j]=new ArrayList<Integer>();                               //listҪ��ʼ��
			 }
		 }
		 for(int i=0;i<ops.size();i+=2) {
			 dp[i][i].add(ops.get(i));                                            //��ֵ
		 }
		 for(int i=3;i<=ops.size();i++) {                                          //i�ǳ��ȡ�����Ϊ1���ǳ�ֵ��Ϊ2û�����壬���Դ�3��ʼ
			 for(int j=0;j+i<=ops.size();j+=2) {                                  //j����ʼ�±꣬���Ϊ2����������������ͷ
				 int l=j;
				 int r=i+j-1;
				 for(int k=j+1;k<r;k+=2) {                                        //k����ľ��Ǳ��ε��������λ�ã�Ҳ�Ǽ��Ϊ2
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
	 
	 //932��Ư������
	 //�õ��˷��ε�˼�룬�Ƚ��ѵĶ���
	 //��ʵ�Ǹ���ѧ���⣺����2*a[k]=a[i]+a[j] �㲻�������Ҳ࣬a[i]������a[j]ż������
	 //���a[i],a[j],a[k]��Ư�����飬��ôka[i]+b,ka[j]+b,ka[k]+bҲ��Ư������	
	 //���������뵽���������ֳ������֣�����������Ҳ�ż�������������඼��Ư�����飬��ô������Ҳ��Ȼ��Ư������
	 //����������������ֳɶ���Ư���������ż�����ϣ�
	 //�õľ���k�����ʣ�����-----2*����-1���õ��Ļ��������������ʲ���
	 //ż��-----2*ż�����õ��Ļ���ż���������ʲ���     ͨ�����ֱ任�����Ǿͽ�N��չ����2N������ʵ����2N-1ͬ��Ҳ��չ���ˣ�
	 //�õݹ���д��ʵ�������Զ�����
	 //�Ե����ϣ�1----1��2------1��3��2-----1��3��5��2��4
	 Map<Integer,int[]> memo;                                      //hashmap������n�Ͷ�Ӧ������
	 public int[] beautifulArray(int n) {
		 memo=new HashMap();
		 return f932(n);
	    }
	 
	 public int[] f932(int n) {
		 if(memo.containsKey(n)) {                                 //��n--n/2---n/4---һ�����ݹ鷵��
			 return memo.get(n);
		 }
		 int[] ans=new int[n];                                     
		 if(n==1) {
			 ans[0]=1;
		 }else {
			 int t=0;
			 for(int x:f932((n+1)/2)) {                           //����Ư������ݹ�
				 ans[t++]=2*x-1;                                  //��չ
			 }
			 for(int y:f932(n/2)) {                               //ż��Ư������ݹ�
				 ans[t++]=2*y;                                    //��չ
			 }
		 }
		 memo.put(n, ans);                                       
		 return ans;	 
	 }
	 
	 //312����hard,�䲻��hard������������˼άDP��
	 //������㲻��hard�⣬����һ������˼ά�������dpת�Ʒ��̱ȽϺ��롣
	 //����˼ά��ÿ�δ���һ�����򣬻ᵼ��ԭ�������ڵı�����ڵģ����ô������飻��������˼ά��ֱ��������飬���ÿ�����һ�����򲢷ֳ������������󣬲���coinsֵ+���Ҳ�ȡ���ֵ
	 //��dp[i][j]��ʾ���䣨i,j����������������������ܵõ������ֵ
	 //dp[i][j]=max(dp[i][k]+dp[k][j]+nums[i]*nums[j]*nums[k]) k��nums��i,j���е�����һ����
	 //��ʼ������nums��չ����β�Ӹ�1��������˻�����ֵ����0
	 public int maxCoins(int[] nums) {
		 int m=nums.length;
		 int[][] dp=new int[m+2][m+2];
		 int[] val=new int[m+2];
		 val[0]=val[m+1]=1;
		 for(int i=1;i<m+1;i++) {
			 val[i]=nums[i-1];
		 }
		 for(int i=m-1;i>=0;i--) {              //Ҫ��֤��i,j�����д���k����ȡ������i���ֵѡ�����m-1,������������һ��m���ڿ�ȡ
			 for(int j=i+2;j<=m+1;j++) {        //j���Ҷ˵㣬���ȡm+1��j=i+2���ֿ�ȡ����һ��
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
