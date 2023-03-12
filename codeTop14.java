package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;
import java.lang.reflect.Array;

public class codeTop14 {
	
	//*****�ܴ�����⣺ջ��������ȡ��Сsize,Ȼ�󵯳�m=size��ʵ��ջ����գ����������
	//������isEmpty���ж��Ƿ����
	
	
	
	//93��IP��ַ�Ľ�ȡ
	//�����⣺�Ƚ����뵽�����ڵ���s�ӿ�ͷ��ֻ�ܷ�Ϊ1��2��3���ַ����ֽ�ȡ��ʽ(��Ϊ������255)�����ǵݹ鷽��
	//Ȼ���жϵݹ�ļ�֦�����ʣ�µ�k���ַ�С��"."���������ߴ���"3*."�������Ǿͼ�֦���ݹ�����Ĳ㣬�ĸ�".";
	//�жϽ�ȡ���ַ��Ƿ���0~255֮�䣬�������֦,�ҳ��˸�λ���ⲻ����0��ͷ������ĩβ���ǽ������Ҳ���Ϊ4�������
	//**һ��DP�����������Ż���boolean���͵ķ���ֵ�������뵽����
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
	//�ж������Ƿ����
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
	
	//78��ȫ���Ӽ�
	//***�����ת��Ϊѡȡ��ѡȡ���εݹ飬ÿ�㶼�������ݹ�
	//�Ӽ����⣺ѡȡ��ѡȡ�����н�������ݡ�ÿһ������Ҫѡȡ��ѡȡ��˵���ݹ��������һֱ���ҡ�
	//ÿ�εݹ����һ��Ԫ�ؼ��ɣ����Եݹ���ھ��ǵ���ĩβ�����һ��ѡȡ����(length)�����==Ԫ�ظ���,��һ�Σ�Ȼ����������С�
	//��ѡȡ��ѡȡ�����룬�ݹ���߲����룬�ݹ�
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
		tmp78.add(nums[len]);  //ѡȡ�ˣ��ݹ�
		dfs78(len+1,nums);
		tmp78.remove(tmp78.size()-1);//��ѡȡ���ݹ�
		dfs78(len+1,nums);
	}
	
	//43:�ַ������
	//��ʽ��ˣ��ѳ�����ÿһλ�ͱ�������ˣ�Ȼ������Ҫ�����0(ע���λ��ӣ����Բ�0)��������
	//**StringBuilder.append(����)���൱��ֱ�Ӳ����ַ��������԰�����ֱ��append��StringBuilder����
	//**StringBuilder�еõ�����ʵ��������Ҫע���£�reverseһ��
	//**�����Ӷ��Ǵ�ĩβ��ʼ�ģ���Ҫע��
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
	
	//39.���֮��
	//���ģ�����·��ֵ��target������С����С��0������������С��������ֹͣ���εݹ�
	//**���ظ�ѡȡ�Ĵ��룺ÿ�εݹ���Ȼ�ӵ�ǰֵ��ʼ����ʾ�����ظ�ѡȡ��
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
			//ÿ�εݹ���Ȼ��i����beginλ��
			dfs39(candidates,i,len,target-candidates[i],path,res);
			path.removeLast();
		}
	}
	
	//718����ظ�������
	//DP�� dp[i][j]=dp[i-1][j-1]+1  (s[i-1]==t[j-1])  !=ʱ��dp[i][j]=0   ��Ϊ�ǽ�β�������飬���������У����Բ���ֱ��Ϊ0
	//Ȼ��ȡmax���ɣ���ֵ��dp[0][j]=0 dp[i][0]=0   
	//*****�������Ҳ��s[i-1] ��Ϊs[0]��Ӧ��ֵ�ģ�����dp[0]�������Ϊnums1Ϊ�յ�ʱ�򣬼�dp[i]��Ӧs[i-1]
	//*****�������ó�ֵ����ֵ����������ַ���Ϊ�յ�����������ͼ��˳�ֵ����
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
	 
	 //394���ַ�������
	 //��������while��flag����ʾ�Ƿ����[],�����߼��ܸ���
	 //���Ľⷨ����ջ��ʵ�����Ž����߼��������ַ���ջ�������ţ���ĸ��ջ�������ţ��Ǿ���Ҫ������ֱ�������ţ�
	 //��ʱ����������reverse�Ұ�������ջ�����ֵķ�����Ȼ������ѹ��ջ�У���������Ԫ��...
	 //��ֹ���������һ���ַ����������в�reverse
	 //**ע�����ֿ��ܴ���10������Ҫ�ж��Ƿ��Ƕ�λ��
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
				 //��λ�����
				 while(!stack.isEmpty()&&Character.isDigit(stack.peek())) {
					 //**����һ��ҪС�ģ�++count,�����count+1,����ı���һ�ε�countֵ������countһֱ��0+1������
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
	 
	 //14�������ǰ׺
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
	 
	 //283:�ƶ�0
	 //˫ָ��û����
	 //*****Ҫά��ԭ˳������Ӧ����˫ָ��ͬ�򣬴����ң��Ѳ���0�Ľ�������ߣ���ά����֮ǰ��Ԫ�غ���
	 //**ֱ�Ӱ�0�����ұ�û�취ά��ԭ˳�����Ժ�������ðѲ�Ϊ0�Ļ�����߲��У��ҿ�������һ��ָ��ά��˳��
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
	 
	 //162:Ѱ�ҷ�ֵ
	 //�ǳ�����Ľⷨ������Ҫ���Ǳ߽�����nums[-1]=nums[n]=���������
	 //������ȡ�м�ڵ��ʱ��Ѱ��һ���������µķ�����ô����������ϣ���������Ǹ��������һ���������󽵣����ڷ�ֵ
	 //��������·������������һֱ���µ�����������ڷ�ֵ�����Զ��ֲ���ֻ��Ҫ���������򼴿�
	 public int findPeakElement(int[] nums) {
		 int l=0;
		 int r=nums.length-1;
		 //**����������Ŀ��������֤��Ҫ����Ϊl<r,����return l����������������Ľ⣬���ᳬʱ
		 //<=Ȼ�󷵻�midҲ�ԣ����ǻᳬʱ����������Ĳ���������ʱ
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
	 
	 //62����ͬ·��
	 //dp[i][j]=dp[i-1][j]+dp[i][j-1] �򵥵�dp���⣬������������ϻ�������ͼ���
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
	 
	 //662�������������
	 //**ע��㣺������null�ڵ�������ǲ���ȡ�ģ��ᱨ�쳣������null�ڵ㲻�ɷ������
     //���ģ���Ϊnull����ֱ�ӷ�����У���������ʹ�ñ����ʵ�ּ�¼�����ӽڵ�2i,���ӽڵ�2i+1.(ע�������Ŵ�1��ʼ)
     //��ΪnullҲ�����˱������������ı�ž���2i,2i+1��
	 //һ�����д�ڵ㣬һ�����д��š�
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
	 
	 //152���˻����������
	 //���뵽�ĵ�һ�㣺dp[i]=max(dp[i-1]*nums[i],nums[i]),nums[i]��ע����{0,1,0,1,1}���ִ�0�����
	 //���ǿ��ǵ��п��ܸ��������������nums[i]Ϊ�������Ǿ���Ҫǰi-1����Сֵ��˲��ܵõ����˻�
	 //����������Ҫά��һ����С�˻���Ȼ��max[i]=max(max[i-1]*nums[i],min[i-1]*nums[i],nums[i])
	 //���ģ���Ҫά�����˻�����Ҫά����С�˻������⸺�������������
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
	 
	 //179�������
	 //���ģ��޷�ֱ��ȷ����������������������𡣰��������ָ��Ϊab,ba�Ľ������򼴿�
	 public String largestNumber(int[] nums) {
		 int n=nums.length;
		 String[] str=new String[n];
		 for(int i=0;i<n;i++) str[i]=""+nums[i]; //**intת���ַ�����ֱ���ÿ��ַ�ƴ�Ӽ���
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
	 
	 //468:��֤IP
	 //�򵥵��з����⣺**ע��.�õ���ת��\\.,��ֱ���ü���
	 //**ע���ж��ַ��Ƿ�Ϊ���֣���Character.isDigit;�ַ���ת��Ϊint,��Integer.parseInt()
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
	 
	 //912:����������
	 //�����򣺺��ĺ�����heapify(arr,n,i)ά���󶥶�˳�򣬵�ǰλ����i,Ȼ�����϶��µ����󶥶ѣ��Ѹ��ڵ���������ӽڵ������
	 //�ķŵ����ڵ㣬����ں��ӽڵ��򽻻���Ȼ��ݹ�����������λ��   O(logn)��û������ݹ����
	 //���ѣ������һ���к��ӽ���(n/2-1)��ʼ(��n�ڵ�ĸ��ڵ㿪ʼ)�������������¶��ϵ���˳�� O(n)
	 //���򣺽�arr[0]�Ѷ�Ԫ�غ�ĩβԪ�ؽ�����ĩβԪ�ؾ������ֵ�����ˣ�Ȼ��Խ�����ȥ��0λ�ý���heapify���� O(nlogn)
	 
	 //���ģ�ά�ֶ���˳��
	 public void heapify(int[] arr,int n,int i) {
		 int largest=i; //ά�����ڵ���ӽڵ����ֵ���±꣬��ʼ������Ϊi
		 int lson=i*2+1;//�±��0��ʼʱ������Ϊ2i+1,�Һ���Ϊ2i+2
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
		 //1.���ѣ������һ���ڵ�ĸ��ڵ㿪ʼ
		 for(int i=n/2-1;i>=0;i--) {
			 heapify(arr,n,i);
		 }
		 
		 //2.����ĩβԪ�ؿ�ʼ����ͷԪ�أ��̶�λ��
		 for(int j=n-1;j>0;j--) {
			 int temp=arr[0];
			 arr[0]=arr[j];
			 arr[j]=temp;
			 heapify(arr,j,0);  //**ע������Ѵ�С�����˱仯�����һ��Ԫ��ȷ��λ���ˣ����Դ�СΪj����n
		 }
	 }
	 
	 
	 
	 
	 
	 
	 
	 
}

	    
	 
	 
	 
	 
	 
	 
	 
	 public static void main(String[] args) {
		 String s="100[leetcode]";
		 String t=decodeString(s);
		 System.out.print(t);
	 }
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
