package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

public class codeTop {
	/*
	
	//��19����Ŀ���ڽ�ָoffer�͹ȸ�180�У�ȫ����һ�鼴�ɡ�
	
	//19���ܽ᣺
	//1. �ַ��������и"\\.";�ַ���תΪ���֣�ֱ����Integer.parseInt(),������ȥǰ��0
	//2. ����ˮ����ס��ʽ���õ����ˮֵ=����������߸߶ȵĽ�Сֵ-�ýڵ��ֵ����ans[i]=min(maxleft,maxright)-height[i]
	//3. **���·���ͣ��ҵ�˼·��ÿ���ڵ㿪���ݹ飬Ȼ��ÿ�α��������������nums[]�У�Ȼ��ݹ���ھ���null��Ȼ����nums�����ֵ����
	//�ٷ���⣺�ڵ�������ֵ=root.val+max(���ҵ������ֵ)��·�������ֵ=root.val+���ӽڵ������ֵ+���ӽڵ������ֵ
	//4. ���Ч���ţ�"))",��ƥ���һ�������ţ���+2��DP����
	//5. *****�������ɣ��ǳ�����õݹ�д������
	//ʣ����������Ŀһ��ҪС�ڵ�����������Ŀ������ʱ����һ�����Եݹ������С��ʱ�򣬿��Լ�����/��
	//6. ��һ�����У��Ӻ���ǰ���ҵ�һ����С���ͽϴ�����������ʱ��ʹ������ֵ���ͬʱ���ú��������������Ϊ��С�Ĵ���
	//��С���ǴӺ���ǰ��һ������ģ��ϴ����Ӻ���ǰ��һ���Ƚ�С�����
	//7. *****����������Ϊû��βָ�룬�����Ƚ���벿������ת��Ȼ��Ϳ��԰��������ˡ�����ת�Ƿǳ������Ĳ���
	//8. ȱʧ�ĵ�һ��������whileѭ������ͣ�Ľ�������Ԫ��ֵ�����Ӧֵ���±�
	//ԭ�ع�ϣ�㷨������ԭ�������洢��Ӧ�±��Ԫ�أ��������ʱ���ɷ��ֲ�ƥ��Ԫ�ء�
	//9. K��һ�鷴ת������Ҫ���ڷ�ת����ν������ӺͿ�����һ�ֵ�׼���������˼·��ȷ����
 
	
	
	
	
	      //165.�汾�űȽ�
	      //���ģ��ַ����ָȻ��ת��int���ͱȽϼ���;java�зָ�ʱ����������������ʽ�����ǵ���\\.
	 public int compareVersion(String version1, String version2) {
		 String[] ver1=version1.split("\\.");
		 String[] ver2=version2.split("\\.");
		 for(int i=0;i<ver1.length||i<ver2.length;i++) {
			 int x=0;
			 int y=0;
			 if(i<ver1.length) {
				 x=Integer.parseInt(ver1[i]);
			 }
			 if(i<ver2.length) {
				 y=Integer.parseInt(ver2[i]);
			 }
			 if(x>y) return 1;
			 if(x<y) return -1;
		 }
		 return 0;
	    }
	    
	   
	  //42.����ˮ
	  //���ģ��õ����ˮֵ=���ҷֲ���߸߶ȵĽ�Сֵ-�ýڵ��ֵ����ans[i]=min(maxleft,maxright)-height[i]
	  //ע�⣺��������ҷֲ���Դ��ϱ��ڵ㣬�����պÿ��Ա�������ˮֵΪ���������
	  //�����ȴ洢������ҷֲ��maxleft,maxright,Ȼ����ȥ�ù�ʽ����
	  public int trap(int[] height) {
		  if(height==null||height.length==0) {
			  return 0;
		  }
		  int ans=0;
		  int len=height.length;
		  int[] maxleft=new int[len];
		  int[] maxright=new int[len];
		  maxleft[0]=height[0];
		  for(int i=1;i<len;i++) {
			  maxleft[i]=Math.max(maxleft[i-1], height[i]);
		  }
		  maxright[len-1]=height[len-1];
		  for(int j=len-2;j>=0;j--) {
			  maxright[j]=Math.max(maxright[j+1], height[j]);
		  }
		  for(int k=1;k<len-1;k++) {
			  ans+=Math.min(maxleft[k], maxright[k])-height[k];
		  }
		  return ans;	  
	  }
	  
	

	   //124���������е����·����
	   //���ģ�����ÿ���ڵ�������ֵ��maxGainӦ���������ӽڵ�ȡ����ֵ����ģ�Ȼ����ϱ��ڵ�ֵ
	   //��ν��·�����=���ڵ�+���ӽڵ�������ֵ+���ӽڵ�������ֵ��·��������������ӽڵ㹱��ֵΪ��ֵ�������ֱ������Ϊ0����Ҫ�ýڵ㣩
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
		  }
	 
	 int maxSum=Integer.MIN_VALUE;
	 public int maxPathSum(TreeNode root) {
            maxGain(root);
            return maxSum;
	    }
	
	 public int maxGain(TreeNode root) {
		 if(root==null) return 0;
		 int leftGain=Math.max(maxGain(root.left), 0);        //��������ֵ����ڵ�ݹ飬�ұ������ֵ���ҽڵ�ݹ�
		 int rightGain=Math.max(maxGain(root.right), 0);
		 int maxlength= root.val+leftGain+rightGain;
		 maxSum=Math.max(maxSum, maxlength);
		 return root.val+Math.max(rightGain,leftGain);
	 }
	 
	 //129.�������и��ڵ㵽Ҷ�ڵ���ɵ�����֮��
	 //���ģ�������ȣ����̣��ȴ����ڵ㣬Ҫ����*10�ӱ��ڵ�ֵ��Ȼ����ȥ�ݹ����ң�������ӣ���Ϊ���պ�
	  public int sumNumbers(TreeNode root) {
		  return dfs(root,0);
	    }
	  
	  public int dfs(TreeNode root,int presum) {
		  if(root==null)  return 0;
		  int sum=presum*10+root.val;
		  if(root.left==null&&root.right==null) {               //�����Ҷ�ڵ㣬��ֱ�ӷ��غ�
			  return sum;
		  }else {                                               //����Ƿ�Ҷ�ڵ㣬��ô���κͻ�Ҫ�������±������������Һ�Ӧ��������
			  return dfs(root.left,sum)+dfs(root.right,sum);
		  }//�ݹ����Σ���������10*�����Բ��ǶԵģ���Ȼ����Ҫ�ȼ�¼��nums����ȥ���
	  }


	    
	    //112.·����
	    //���ģ�������ȱ�������¼·���ͣ����Ƿ����target��
	    
	    //������룺sum����Ϊȫ�ֱ���ʱ���ᵼ��ÿ��·�����ӽ�ȥsum�����϶�����
	    int sum=0;//����
	    public boolean hasPathSum01(TreeNode root, int targetSum) {
	    	if(root==null)  return false;
	    	sum+=root.val;
	    	if(root.left==null&&root.right==null) {
	    		if(sum==targetSum) return true;
	    		else {
	    			return false;
	    		}
	    	}else {
	    		return hasPathSum(root.left,targetSum)||hasPathSum(root.right,targetSum);
	    	}

	    }
	    
	    //��д���룬Ҫ��ÿһ��sum��Ϊ�ֲ��������룬�������ܵݹ�����·����
	    //���ڲ����в����𽥴�����һ�εݹ�
	    public boolean hasPathSum(TreeNode root, int targetSum) {
	    	if(root==null)  return false;
	    	return dfs(root,targetSum,0);
	    }
	    
	    public boolean dfs(TreeNode root,int targetSum,int sum) {
	    	if(root==null)  return false;
	    	sum+=root.val;
	    	if(root.left==null&&root.right==null) {
	    		if(sum==targetSum) return true;
	    		else {
	    			return false;
	    		}//���ߣ�����sum��ֱ���ڵݹ����޸�ÿ�ε�targetSumΪtargetSum-root.val���ɣ������Ĵ�������
	    	}else {
	    		return dfs(root.left,targetSum,sum)||dfs(root.right,targetSum,sum);   //���ڲ����У������𽥼��벻ͬ��ĵݹ�
	    	}
	    }
	    //���ߣ�����sum��ֱ���ڵݹ����޸�ÿ�ε�targetSumΪtargetSum-root.val���ɣ������Ĵ�������
	   
	    
	    //32.���Ч����
	    //���ģ���̬�滮����dp[i]Ϊ���ַ�λ��i��β�����Ч���ŵĳ��ȡ�s[i]='('��dp[i]=0;dp[i]=')',��Ҫ���������
	    //1.��s[i-1]='(',��dp[i]=dp[i-2]+2
	    //2.��s[i-1]=')',����))�������������ô��һ�������ţ�ƥ�����Ч�����Ϊdp[i-1],��ʱ�������Ч�Ӵ���ǰ��,��s[i-dp[i-1]-1]λ�ã�����ǡ�(��
	    //��ô�����(���Ϳ��Ժ����һ��������ƥ�䣬+2��������������ǰ��Ĵ�������Ҳ��һ����Ч���ȣ���ʱdp[i]=dp[i-1]+2+dp[i-dp[i-1]-2]
	    //��ֵ��dp[0]=0,dp[1]=0
	    public int longestValidParentheses(String s) {
	    	if(s.length()==0||s.length()==1) return 0;
         int[] dp=new int[s.length()];
         int maxlen=0;
         dp[0]=0;
         for(int i=1;i<s.length();i++) {
         	if(s.charAt(i)=='(') {
         		dp[i]=0;
         	}else {
         		if(s.charAt(i-1)=='(') {
         			dp[i]=(i>=2?dp[i-2]:0)+2;
         		}else {
         			if(i-dp[i-1]>0&&s.charAt(i-dp[i-1]-1)=='(') {
         				dp[i]=dp[i-1]+2+(i-dp[i-1]-2>=0?dp[i-dp[i-1]-2]:0);
         			}
         		}
         	}
         	maxlen=Math.max(maxlen, dp[i]);
         }
         return maxlen;
	    }
	    
	    
	    
	    //98.�������������ж�
	    //�������������˳��������ҷ��ʣ���ôroot��������ʣ���Ҫ��¼ǰһ���ڵ㣬��ô��root.val>pre.val���������ֱ�ӷ���false
	    long pre=Long.MIN_VALUE;
	    public boolean isValidBST(TreeNode root) {
	    	if(root==null) return true;
	    	boolean l=isValidBST(root.left);
	    	if(root.val<=pre) return false;
	    	pre=root.val;
	    	boolean r=isValidBST(root.right);
	    	return l&&r;
	    }
	    
	   
	    
	    //22.��������
	    //���˽ⷨ��ֱ�ӽ��еݹ���ң�ʣ����������Ŀһ��ҪС�ڵ��������ŵ���Ŀ
	    List<String> res01=new ArrayList<>();                   //��¼ȫ����ֱ�ӷ����������
	    public List<String> generateParenthesis(int n) {
	    	if(n<=0) return res01;
	    	generate("",n,n);
	    	return res01;
	    }
	    
	    public void generate(String s,int left,int right) {
	    	if(left==0&&right==0) {
	    		res01.add(s);
	    		return ;
	    	}
	    	if(left==right) {
	    		generate(s+'(',left-1,right);
	    	}else {
	    		if(left<right) {
	    			if(left>0) {
	    				generate(s+'(',left-1,right);
	    			}
	    			generate(s+')',left,right-1);
	    		}
	    	}
	    }
	    
	    
	 //88.�ϲ�������������������һ��
	 public void merge(int[] nums1, int m, int[] nums2, int n) {
		 int i=0;
		 int j=0;
		 while(i<m&&j<n) {
			 if(nums1[i]>nums2[j]) {
				 for(int k=m-1;k>=i;k--) {
					 nums1[k+1]=nums1[k];
				 }
				 nums1[i]=nums2[j];
				 j++;
				 i++;
				 m++;
			 }else {
				 i++;
			 }
		 }
		 if(i==m) {
			 for(int p=j;p<n;p++) {
				 nums1[i]=nums2[p];
				 i++;
			 }
		 }
	    }
	 
	
	
	
	 //199.������������ͼ
	 //���ģ��������+ÿ�����һ���ڵ����
	
	 public List<Integer> rightSideView(TreeNode root) {
		 List<Integer> res=new ArrayList<>();
		 if(root==null) return res;
		 Queue<TreeNode> nodeQueue=new LinkedList<>();
		 nodeQueue.offer(root);
		 while(!nodeQueue.isEmpty()) {
			 int size=nodeQueue.size();                      //��¼ÿ���size
			 for(int i=0;i<size;i++) {
				 TreeNode tmp=nodeQueue.poll();
				 if(tmp.left!=null) {
					 nodeQueue.offer(tmp.left);
				 }
				 if(tmp.right!=null) {
					 nodeQueue.offer(tmp.right);
				 }
				 if(i==size-1) {
					 res.add(tmp.val);
				 }
			 }
		 }
		 return res;
	    }
	 
	
	 //31:��һ������
	 //���ģ��������ɸ����֣��������Ϊһ����������ν���Щ�����������У��Եõ���һ�����������
	 //�Ӻ���ǰ���ҵ�һ����С���ͽϴ�����������ʱ��ʹ������ֵ���ͬʱ���ú��������������Ϊ��С�Ĵ���
	 //ע�⣺��һ��Ҫ����ģ���Ѱ�ҵ��Ǹ���С����Ӧ��Խ�ұ�Խ�ã���Ϊ����������ֵ��󵫻�Ƚ�С�����Ե�һԭ��Ѱ�ҿ��ҵĽ�С����
	 //�������ұ߿�ʼ��Ѱ�ҵ���һ����i��i+1�����ӵ�˳��ԣ���ʱi������Ϊ��С����Ȼ��ϴ����ڣ�i+1,n���У����ҵ��ұȽ�С���󼴿ɣ�Ȼ�󽻻���������
	 public void nextPermutation(int[] nums) {
          int k=nums.length-2;
          while(k>=0&&nums[k+1]<=nums[k]) {
        	  k--;
          }
          int i=0;
          if(k>=0) {
        	  for( i=nums.length-1;i>=k+1;i--) {
        		  if(nums[i]>nums[k]) {
        			  swap(nums,i,k);
        			  break;                                       //�ҵ����ϵļ�������ѭ��
        		  }
        	  }
          }
          reverse(nums,k+1);                                       //����ע�⣬��ת���ǣ���֮ǰ��С����λ�ÿ�ʼ������������С���Ǹ���
	    }
	public void swap(int[] nums,int m,int n) {
		int tmp=nums[m];
		nums[m]=nums[n];
		nums[n]=tmp;
	}
	
	public void reverse(int[] nums,int start) {
		int right=nums.length-1;
		int left=start;
		while(left<right) {
			swap(nums,left,right);
			left++;
			right--;
		}
	}
	
	
	
	//143:��������
	//����1�����Ž⣺�۲��ҹ��ɣ����ź������ʵ�����ǣ���벿�ַ�ת��Ȼ���ٺ���벿�ְ���ƴ�Ӷ��ɣ��������ǿ������������1.�ҵ��м�ڵ� 2.
	//�Ұ벿�ַ�ת��3.��벿�֡��Ұ벿�ְ���ƴ��
	//����2���Լ���ģ������㣺�ù�ϣ���ڵ��˳���±꣬���߸����㣬���������飬��������Žڵ����ּ��ɣ������Ϳ��������±�ͽڵ�Ķ�Ӧ��ϵ��
	//Ȼ���ٽ���ƴ�Ӽ���

	public void reorderList(ListNode head) {
		if(head==null) return;                           //return�ձ�ʾ��ֵ���أ�void
		List<ListNode> list=new ArrayList<>();           //�ص㣺��������ʵ�ֽڵ�+�±꣬����Ҫ��ϣ��
		ListNode tmp=head;
		while(tmp!=null) {
			list.add(tmp);
			tmp=tmp.next;
		}
		int i=0;
		int j=list.size()-1;                            //����[]��length,List��size����
		while(i<j) {                                    //������[]��ȡ������list�õ���get��������ȡ
			list.get(i).next=list.get(j);
			i++;
			if(i==j) break;
			list.get(j).next=list.get(i);
			j--;
		}
        list.get(i).next=null;                          //�ǳ���Ҫ����Ϊ����м��ʱ��i���滹����ԭ�ȵ���һ������ʱ�����ѭ����
    }                                                   //������Ҫ���һ������i��next�Ͽ�������Ϊnull,��һ��ǳ���Ҫ
	
    
    //92.��ת����02
    //��¼�ĸ�λ�ã������꣬�����꣬��ǰ�����Һ�����Ȼ���������ת��Ȼ�����������
    public ListNode reverseBetween(ListNode head, int left, int right) {
    	ListNode start=new ListNode(0);
    	start.next=head;
    	ListNode tmp=start;
    	int i=1;
    	ListNode pre=null,last=null,leftNode=null,rightNode=null;
    	while(tmp!=null) {                                      //������������forѭ��ȷ���ĸ�λ�ã��򵥵�
    		if(i==left) {
    			pre=tmp;
    			leftNode=tmp.next;
    		}
    		if(i==right) {
    			rightNode=tmp.next;
    			last=tmp.next.next;
    			break;
    		}
    		tmp=tmp.next;
    		i++;
    	}
        pre.next=null;
        rightNode.next=null;
    	reverseList1(leftNode,rightNode);
        pre.next=rightNode;
        leftNode.next=last;
        return start.next;
    }
    
    public void reverseList1(ListNode head,ListNode tail) {
		 ListNode pre=null;                                      //��ת�����ʼpre��null�ȽϺ�һ�㣬������ֿ�ָ���쳣
		 ListNode cur=head;
		 while(pre!=tail) {
			 ListNode p=cur.next;
			 cur.next=pre;
			 pre=cur;
			 cur=p;
		 }
  }
    
    //69.x��ƽ������
    //ȡ�ɰ취������x=e^��1/2*lne�������ö�����ʵ��ƽ���������ڸ�����������ж�ans��ans+1�ĸ�������ȷ��
    //ʵ�ʿ��죺k^2<=x,kȡ���ֵʱ��������ʵ�ʾ���һ��0��x�����������ֲ��ҡ�
    public int mySqrt(int x) {
    	int left=0;
    	int right=x;
    	int res=-1;
    	while(left<=right) {
    		int mid=(left+right)/2;
    		if((long)mid*mid<=x) {
    			res=mid;
    			left=mid+1;
    		}else {
    			right=mid-1;
    		}
    	}
    	return res;
    }
    
    //41.ȱʧ�ĵ�һ��������
    //ʵ�ֿռ临�Ӷ�O��1�����㷨��ԭ�ع�ϣ�㷨������ԭ�������洢��Ӧ�±��Ԫ�أ��������ʱ���ɷ��ֲ�ƥ��Ԫ�ء�
    //���ģ��������飬�������е�ֵ���ŵ���Ӧ���±꣬����Ԫ��ֵ��
    //�ѵ㣺ע��forѭ����while��ÿ��i����Ҫ�ܶ�εĽ�����ֱ����λ��Ԫ����ȷ������Ԫ�ز��ڷ�Χ��Ϊֹ����������Ҫ��һ�㣬�мǲ�����if
    //�����λ�õļ�Ϊȱʧ�ġ�
    public int firstMissingPositive(int[] nums) {
             int len=nums.length;
             for(int i=0;i<len;i++) {
            	 while(nums[i]>0&&nums[i]<=len&&nums[nums[i]-1]!=nums[i]) {    //ѭ���ж�ֵ�Ƿ�������е�λ���ϣ��мǣ���λ�ò�����if
            		 swap(nums,nums[i]-1,i);                                   //������while��Ϊ��һֱѭ����ֱ����λ��Ԫ�ع̶����߲��ڷ�Χ��
            	 }
             }
             for(int j=0;j<len;j++) {
            	 if(nums[j]!=j+1)
            		 return j+1;
             }
             return len+1;

    }
    

		//15������֮��ת��Ϊ˫��֮�ͣ�����˫ָ����ٸ��Ӷ�.      ���-----˫ָ��----һ��ǰһ����
		//����˼�룺Ϊ�˼��ٸ��Ӷȣ�ʹ���ź���������˫ָ���㷨������һ��forѭ����һ���������ң�һ����������ʵ����O��N���㷨��
		//-a=b+c   -a��Ϊtarget�ͣ�����һ���ź�������飬һ����ǰ���һ���Ӻ���ǰ���������ߵĺͣ����������¼����С�ڣ�����ָ�����ƣ������ڣ�����ָ������
		public List<List<Integer>> threeSum(int[] nums) {
	             List<List<Integer>> res= new ArrayList<>();
	             if(nums==null||nums.length<=2)  return res;
	             Arrays.sort(nums);
	             for(int i=0;i<nums.length-2;i++) {
	            	 if(nums[i]>0) break;
	            	 if(i>0 && nums[i]==nums[i-1]) continue;
	            	 int target=-nums[i];
	            	 int left=i+1;
	            	 int right=nums.length-1;
	            	 while(left<right) {
	            		 if(nums[left]+nums[right]==target) {
	            			 res.add(new ArrayList<Integer>(Arrays.asList(nums[i],nums[left],nums[right])));
	            			 left++;
	            			 right--;
	            			 while(left<right&&nums[left]==nums[left-1]) left++;
	            			 while(left<right&&nums[right]==nums[right+1]) right--;	 
	            		 }else if(nums[left]+nums[right]<target) {
	            			 left++;
	            		 }else {
	            			 right--;
	            		 }
	            	 }	 
	             }
			     return res;
	    }
		
		//103.�������ľ�ݱ�����ʹ��˫�˶��п��Ը�����ʵ�֣�˫�˶��о��൱�ڼ�ʵ���˶�����ʵ����ջ����Ӧ���и��ӱ���
		//�����ڵ����һ�����У�����һ���������ҽ���ʣ�µĽ�����飬ͨ��˫�˶�����ʵ�֣�Ҫô�׽���Ҫô��β���������ȡ��������������
		//Queue�Ƕ��У�Deque��˫�˶���,����new LinkedList��ʵ����
		
		//�ۺ�˼·����һ����ͨ���д�Žڵ㣬�ڵ�һ���������Һ��ӷǿյĽ����������ǿ���֪�������еı���size��С
		//����ÿ������Ľڵ㣬����ö�Ӧ���Ǵ������ң����ĩβ���루��Ϊ˫�˶�����β�����Խ������ǻ����Ǵ�ͷ������ͷ���򣩣���֮��ͷ�塣
		//���߿�����һ����Ǽ�¼��ż�㣬ż����������ݡ�reverseһ�¼��ɡ�
		public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
	        List<List<Integer>> res= new LinkedList<>();
	        if(root==null)  return res;
	        Queue<TreeNode> nodeQueue=new LinkedList<TreeNode>();
	        nodeQueue.offer(root);               //���н�����offer,������poll
	        boolean left=true;
	        while(!nodeQueue.isEmpty()) {
	        	Deque<Integer> part=new LinkedList<>();
	        	int size=nodeQueue.size();
	        	for(int i=0;i<size;i++) {
	        		TreeNode s=nodeQueue.poll();
	        		if(left) {
	        			part.offerLast(s.val);
	        		}else {
	        			part.offerFirst(s.val);
	        		}
	        		if(s.left!=null) {
	        			nodeQueue.offer(s.left);
	        		}
	        		if(s.right!=null) {
	        			nodeQueue.offer(s.right);
	        		}
	        	}
	        	res.add(new LinkedList<Integer>(part));
	        	left=!left;
	        }
			 return res;	
	    }
		
		
		
		
		 //K��һ�鷴ת������ת����+ͷ�ڵ������+K���ж�һ������+��һ����ͷ��������һ����β�����ε�ͷ���´ε�ͷ
	
		 public ListNode reverseList(ListNode head,ListNode tail) {
				  ListNode pre=head;
				  ListNode cur=head.next;
				  while(pre!=tail) {
					  ListNode p=cur.next;
					  cur.next=pre;
	                  pre=cur;
	                  cur=p;
				  }
				  return cur;
		    }
		 public ListNode reverseKGroup(ListNode head, int k) {
	          ListNode start=new ListNode(0);
	          start.next=head;
	          ListNode prev=start;                   //��Ϊstart���ܶ����������������������prev����ÿ�η�ת���ͷ�����ڽ�����һ����β��
	          while(head!=null) {
	        	  ListNode tail=head;
	        	  for(int i=0;i<k-1;i++) {           //���ж�ÿK��һ�鷴ת
	        		  tail=tail.next;
	        		  if(tail==null)
	        			  return start.next;
	        	  }
	        	  ListNode tmp=tail.next;            //��Ϊ��ת�󣬻�Ҫ�����K����ͷ����������Ҫ��ʱ���������ͷ��
	        	  reverseList1(head,tail);
	        	  prev.next=tail;                    //�ϴ������ͷ���뱾�η�ת���tail
	        	  head.next=tmp;                     //���η�ת���head��������һ����ͷ��tmp
	        	  prev=head;                         //prev�̶�Ϊ����ͷ��������һ�ֲ���
	        	  head=tmp;      	                 //head��Ϊ��һ�ֵ�ͷ������ʼ��һ�ֵ�K����ת
	          }
	        	  
	        return start.next;	    	  
		    }
		 
		 

	//82:ɾ�������е��ظ��ڵ�                                                
	//*****ֻ����һ�εĽ����cur,cur.next,cur.next.next(cur���Լ������ͷ�ڵ㿪ʼ)
	//���cur.next.val==cur.next.next.val�Ǿ�����Ҫɾ���������ڵ㣬���Ǻ��滹���ܼ����ظ��������Ǿ��ȼ������val,���������ж�cur.next.next.next��ֵ�ǲ���val
	//���������ң�ֱ��ĳ���ڵ�ֵ������val,�����ǾͿ��Խ�cur���ӵ��ýڵ㣬��Ϊ����ɾ�����ظ��ڵ�
	public ListNode deleteDuplicates82(ListNode head) {
		ListNode top=new ListNode(0,head);
		if(head==null) return null;
		ListNode cur=top;
		while(cur.next!=null&&cur.next.next!=null) {
			if(cur.next.val==cur.next.next.val) {
				int x=cur.next.val;
				while(cur.next!=null&&cur.next.val==x) {
					cur.next=cur.next.next;
				}
			}else {
				cur=cur.next;
			}
		}
		return top.next;
	}
	
	//2����������֮��
	//˼·��ģ��ӷ����㣬����һ����λaddλ��Ȼ��������ڵ㲻���ˣ��Ǿ����½�0�ڵ������
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int add =0;
		ListNode res=new ListNode(0);
		ListNode l3=res;
		while(l1!=null||l2!=null) {
			if(l1==null) l1=new ListNode(0,null);
			if(l2==null) l2=new ListNode(0,null);
			int value=(l1.val+l2.val+add)%10;                  //*****С�ĵ㣺����add������value֮����£������valueǰ�Ļ����ᵼ�±��ε�value�����õ����²�add������
			add=(l1.val+l2.val+add)/10;
			l3.next=new ListNode(value);
			l1=l1.next;
			l2=l2.next;
			l3=l3.next;
		}
		if(add==1) l3.next=new ListNode(1);
		return res.next;
    }
	
	
	
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
