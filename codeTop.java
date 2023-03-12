package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

public class codeTop {
	/*
	
	//这19道题目不在剑指offer和谷歌180中，全都过一遍即可。
	
	//19题总结：
	//1. 字符串按点切割，"\\.";字符串转为数字，直接用Integer.parseInt(),可以消去前导0
	//2. 接雨水：记住公式：该点的雨水值=左右两侧最高高度的较小值-该节点的值。即ans[i]=min(maxleft,maxright)-height[i]
	//3. **最大路径和：我的思路：每个节点开启递归，然后每次遍历都将结果加入nums[]中，然后递归出口就是null，然后求nums中最大值即可
	//官方求解：节点的最大贡献值=root.val+max(左右的最大贡献值)，路径和最大值=root.val+左子节点最大贡献值+右子节点最大贡献值
	//4. 最长有效括号："))",先匹配第一个右括号，再+2再DP即可
	//5. *****括号生成：非常巧妙，用递归写法加入
	//剩余左括号数目一定要小于等于右括号数目。等于时候，下一个可以递归加入左；小于时候，可以加入左/右
	//6. 下一个排列：从后向前，找到一个较小数和较大数互换，此时会使得整体值变大，同时，让后面的升序排序，则为最小的大数
	//较小数是从后向前第一个降序的，较大数从后向前第一个比较小数大的
	//7. *****重排链表：因为没有尾指针，所以先将后半部分链表反转，然后就可以挨个链接了。链表反转是非常常见的操作
	//8. 缺失的第一个正数：while循环，不停的交换当次元素值到达对应值的下标
	//原地哈希算法：即用原数组来存储对应下标的元素，这样检查时即可发现不匹配元素。
	//9. K个一组反转链表：主要在于反转后如何进行链接和开启下一轮的准备；看解答，思路明确即可
 
	
	
	
	
	      //165.版本号比较
	      //核心：字符串分割，然后转化int类型比较即可;java中分割时候，括号内是正则表达式，我们得用\\.
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
	    
	   
	  //42.接雨水
	  //核心：该点的雨水值=左右分侧最高高度的较小值-该节点的值。即ans[i]=min(maxleft,maxright)-height[i]
	  //注意：这里的左右分侧可以带上本节点，这样刚好可以避免了雨水值为负数的情况
	  //我们先存储这个左右分侧的maxleft,maxright,然后再去用公式即可
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
	  
	

	   //124：二叉树中的最大路径和
	   //核心：计算每个节点的最大贡献值。maxGain应该是左右子节点取贡献值更大的，然后加上本节点值
	   //所谓的路径最大=本节点+左子节点的最大贡献值+右子节点的最大贡献值（路径，如果出现了子节点贡献值为负值，则可以直接设置为0，不要该节点）
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
		 int leftGain=Math.max(maxGain(root.left), 0);        //左边最大贡献值又左节点递归，右边最大贡献值由右节点递归
		 int rightGain=Math.max(maxGain(root.right), 0);
		 int maxlength= root.val+leftGain+rightGain;
		 maxSum=Math.max(maxSum, maxlength);
		 return root.val+Math.max(rightGain,leftGain);
	 }
	 
	 //129.二叉树中根节点到叶节点组成的数字之和
	 //核心：深度优先，流程：先处理本节点，要将和*10加本节点值，然后再去递归左右，左右相加，即为最终和
	  public int sumNumbers(TreeNode root) {
		  return dfs(root,0);
	    }
	  
	  public int dfs(TreeNode root,int presum) {
		  if(root==null)  return 0;
		  int sum=presum*10+root.val;
		  if(root.left==null&&root.right==null) {               //如果是叶节点，则直接返回和
			  return sum;
		  }else {                                               //如果是非叶节点，那么本次和还要继续向下遍历，并且左右和应当加起来
			  return dfs(root.left,sum)+dfs(root.right,sum);
		  }//递归两次，加了两次10*，所以才是对的；不然就需要先记录入nums中再去相加
	  }


	    
	    //112.路径和
	    //核心：深度优先遍历，记录路径和，看是否等于target。
	    
	    //错误代码：sum设置为全局变量时，会导致每条路径都加进去sum，最后肯定出错
	    int sum=0;//错误
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
	    
	    //改写代码，要将每一个sum作为局部变量加入，这样才能递归增加路径和
	    //放在参数中才能逐渐带入下一次递归
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
	    		}//或者，不用sum，直接在递归中修改每次的targetSum为targetSum-root.val即可，这样的代码更简便
	    	}else {
	    		return dfs(root.left,targetSum,sum)||dfs(root.right,targetSum,sum);   //放在参数中，才能逐渐加入不同层的递归
	    	}
	    }
	    //或者，不用sum，直接在递归中修改每次的targetSum为targetSum-root.val即可，这样的代码更简便
	   
	    
	    //32.最长有效括号
	    //核心：动态规划，设dp[i]为以字符位置i结尾的最长有效括号的长度。s[i]='('则dp[i]=0;dp[i]=')',则要分情况讨论
	    //1.若s[i-1]='(',则dp[i]=dp[i-2]+2
	    //2.若s[i-1]=')',即‘))’这种情况，那么第一个右括号，匹配的有效最长长度为dp[i-1],此时在这个有效子串的前面,即s[i-dp[i-1]-1]位置，如果是‘(’
	    //那么这个‘(’就可以和最后一个右括号匹配，+2；而且在左括号前面的串，可能也有一个有效长度，此时dp[i]=dp[i-1]+2+dp[i-dp[i-1]-2]
	    //初值：dp[0]=0,dp[1]=0
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
	    
	    
	    
	    //98.二叉搜索树的判定
	    //利用中序遍历，顺序是左根右访问，那么root按这个访问，需要记录前一个节点，那么本root.val>pre.val则过，否则直接返回false
	    long pre=Long.MIN_VALUE;
	    public boolean isValidBST(TreeNode root) {
	    	if(root==null) return true;
	    	boolean l=isValidBST(root.left);
	    	if(root.val<=pre) return false;
	    	pre=root.val;
	    	boolean r=isValidBST(root.right);
	    	return l&&r;
	    }
	    
	   
	    
	    //22.括号生成
	    //惊人解法，直接进行递归查找：剩余左括号数目一定要小于等于右括号的数目
	    List<String> res01=new ArrayList<>();                   //记录全部，直接放在外面可以
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
	    
	    
	 //88.合并两个排序数组至其中一个
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
	 
	
	
	
	 //199.二叉树的右视图
	 //核心：层序遍历+每层最后一个节点输出
	
	 public List<Integer> rightSideView(TreeNode root) {
		 List<Integer> res=new ArrayList<>();
		 if(root==null) return res;
		 Queue<TreeNode> nodeQueue=new LinkedList<>();
		 nodeQueue.offer(root);
		 while(!nodeQueue.isEmpty()) {
			 int size=nodeQueue.size();                      //记录每层的size
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
	 
	
	 //31:下一个排列
	 //核心：给定若干个数字，将其组合为一个整数。如何将这些数字重新排列，以得到下一个更大的整数
	 //从后向前，找到一个较小数和较大数互换，此时会使得整体值变大，同时，让后面的升序排序，则为最小的大数
	 //注意：第一需要满足的，是寻找的那个较小数，应该越右边越好，因为这样交换后，值变大但会比较小。所以第一原则，寻找靠右的较小数。
	 //即，从右边开始，寻找到第一个（i，i+1）增加的顺序对，此时i就锁定为较小数；然后较大数在（i+1,n）中，靠右的且比较小数大即可；然后交换；变升序
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
        			  break;                                       //找到符合的即可跳出循环
        		  }
        	  }
          }
          reverse(nums,k+1);                                       //这里注意，反转的是，从之前较小数的位置开始，这样才是最小的那个数
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
	
	
	
	//143:重排链表
	//方法1：最优解：观察找规律，重排后的链表实际上是，后半部分反转，然后再和左半部分挨个拼接而成，所以我们可以三步解决：1.找到中间节点 2.
	//右半部分反转，3.左半部分。右半部分挨个拼接
	//方法2：自己想的，基础点：用哈希表存节点和顺序下标，或者更简便点，用链表数组，数组里面放节点这种即可，这样就可以锁定下标和节点的对应关系了
	//然后再进行拼接即可

	public void reorderList(ListNode head) {
		if(head==null) return;                           //return空表示无值返回，void
		List<ListNode> list=new ArrayList<>();           //重点：链表数组实现节点+下标，不需要哈希表
		ListNode tmp=head;
		while(tmp!=null) {
			list.add(tmp);
			tmp=tmp.next;
		}
		int i=0;
		int j=list.size()-1;                            //数组[]用length,List用size函数
		while(i<j) {                                    //数组用[]存取，但是list用的是get函数来存取
			list.get(i).next=list.get(j);
			i++;
			if(i==j) break;
			list.get(j).next=list.get(i);
			j--;
		}
        list.get(i).next=null;                          //非常重要：因为最后到中间的时候，i后面还接着原先的下一个，此时会出现循环链
    }                                                   //所以需要最后一步，将i的next断开，设置为null,这一点非常重要
	
    
    //92.反转链表02
    //记录四个位置，左坐标，右坐标，左前驱，右后驱，然后断链，反转，然后加链，即可
    public ListNode reverseBetween(ListNode head, int left, int right) {
    	ListNode start=new ListNode(0);
    	start.next=head;
    	ListNode tmp=start;
    	int i=1;
    	ListNode pre=null,last=null,leftNode=null,rightNode=null;
    	while(tmp!=null) {                                      //或者先用两个for循环确定四个位置，简单点
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
		 ListNode pre=null;                                      //反转链表，最开始pre用null比较好一点，以免出现空指针异常
		 ListNode cur=head;
		 while(pre!=tail) {
			 ListNode p=cur.next;
			 cur.next=pre;
			 pre=cur;
			 cur=p;
		 }
  }
    
    //69.x的平方根：
    //取巧办法：根号x=e^（1/2*lne）这样用对数来实现平方根，由于浮点数误差，最后判断ans与ans+1哪个才是正确答案
    //实际考察：k^2<=x,k取最大值时候的情况。实际就是一个0到x的有条件二分查找。
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
    
    //41.缺失的第一个正数：
    //实现空间复杂度O（1）的算法：原地哈希算法：即用原数组来存储对应下标的元素，这样检查时即可发现不匹配元素。
    //核心：遍历数组，将数组中的值，放到对应的下标，交换元素值，
    //难点：注意for循环中while，每次i都需要很多次的交换，直至本位置元素正确，或者元素不在范围内为止，这是最重要的一点，切记不能用if
    //最后不在位置的即为缺失的。
    public int firstMissingPositive(int[] nums) {
             int len=nums.length;
             for(int i=0;i<len;i++) {
            	 while(nums[i]>0&&nums[i]<=len&&nums[nums[i]-1]!=nums[i]) {    //循环判断值是否在其该有的位置上，切记，本位置不能用if
            		 swap(nums,nums[i]-1,i);                                   //这里用while是为了一直循环，直到本位置元素固定或者不在范围内
            	 }
             }
             for(int j=0;j<len;j++) {
            	 if(nums[j]!=j+1)
            		 return j+1;
             }
             return len+1;

    }
    

		//15：三数之和转化为双数之和，再用双指针减少复杂度.      求和-----双指针----一个前一个后
		//核心思想：为了减少复杂度，使用排好序的数组的双指针算法，简化了一层for循环，一个从左向右，一个从右向左，实现了O（N）算法。
		//-a=b+c   -a作为target和，对于一个排好序的数组，一个从前向后，一个从后向前，计算两者的和，若等于则记录，若小于，则左指针右移，若大于，则右指针左移
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
		
		//103.二叉树的锯齿遍历：使用双端队列可以更简便的实现，双端队列就相当于即实现了队列又实现了栈，在应用中更加便利
		//将树节点存入一个队列，用于一个出，左右进；剩下的结果数组，通过双端队列来实现，要么首进，要么，尾进，进完后取出，加入结果数组
		//Queue是队列，Deque是双端队列,均用new LinkedList来实例化
		
		//综合思路：用一个普通队列存放节点，节点一个出，左右孩子非空的进，并且我们可以知道队列中的本次size大小
		//对于每层出来的节点，如果该对应的是从左向右，则从末尾插入（因为双端队列首尾都可以进，但是基本是从头出，从头排序）；反之，头插。
		//或者可以用一个标记记录奇偶层，偶层出来的数据。reverse一下即可。
		public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
	        List<List<Integer>> res= new LinkedList<>();
	        if(root==null)  return res;
	        Queue<TreeNode> nodeQueue=new LinkedList<TreeNode>();
	        nodeQueue.offer(root);               //队列进入用offer,出来用poll
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
		
		
		
		
		 //K个一组反转链表：反转链表+头节点留输出+K个判断一个集合+上一个的头，接入下一个的尾，本次的头接下次的头
	
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
	          ListNode prev=start;                   //因为start不能动，留给后序输出，所以用prev来存每次反转后的头，用于接入下一个的尾部
	          while(head!=null) {
	        	  ListNode tail=head;
	        	  for(int i=0;i<k-1;i++) {           //简单判定每K个一组反转
	        		  tail=tail.next;
	        		  if(tail==null)
	        			  return start.next;
	        	  }
	        	  ListNode tmp=tail.next;            //因为反转后，还要接入后K个的头部。所以需要临时变量存这个头部
	        	  reverseList1(head,tail);
	        	  prev.next=tail;                    //上次留存的头接入本次反转后的tail
	        	  head.next=tmp;                     //本次反转后的head，接入下一个的头部tmp
	        	  prev=head;                         //prev固定为本次头，方便下一轮操作
	        	  head=tmp;      	                 //head变为新一轮的头部，开始下一轮的K个反转
	          }
	        	  
	        return start.next;	    	  
		    }
		 
		 

	//82:删除链表中的重复节点                                                
	//*****只遍历一次的解答：用cur,cur.next,cur.next.next(cur从自己加入的头节点开始)
	//如果cur.next.val==cur.next.next.val那就是需要删除这两个节点，但是后面还可能继续重复，那我们就先记下这个val,接着向下判断cur.next.next.next的值是不是val
	//不断向下找，直到某个节点值不再是val,那我们就可以将cur链接到该节点，即为整体删除了重复节点
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
	
	//2：链表两数之和
	//思路：模拟加法运算，设置一个进位add位，然后有链表节点不够了，那就用新建0节点代替下
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int add =0;
		ListNode res=new ListNode(0);
		ListNode l3=res;
		while(l1!=null||l2!=null) {
			if(l1==null) l1=new ListNode(0,null);
			if(l2==null) l2=new ListNode(0,null);
			int value=(l1.val+l2.val+add)%10;                  //*****小心点：这里add必须在value之后更新，如果在value前的话，会导致本次的value计算用的是下层add，出错
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
