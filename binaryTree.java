package work;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;
import java.lang.reflect.Array;

public class binaryTree {
	
	//二叉树总结：写法没有什么规律性，主要是基本模板+记忆+特殊题目
	//1. 怎么写二叉树的递归:先写主程序(递归左右子树)，然后再写边界条件。
	//*****重点技巧：当发现递归中需要的返回值和主函数要求的返回值不太一样时，就需要引入辅助函数去处理递归，
	//然后再去主函数中处理递归返回值即可。
	//2. 基本模板：有关高度的题目同一模板
	//二叉树深度(基础模板)---平衡二叉树判断(加限制条件，-1表示不平衡)---最大直径(直径转化为了(L+R+1)取最大值)
	//3. 基本模板：路径之和
	//437
	//4. 基本模板：相等树/对称树的判断
	//四步法：专门用于求解树的相等或者对称问题
	//很重要：预处理：首先需要将主函数的左右子树拿出来放入辅助函数进行判别（这样求解比较方便）
    //然后四步法：1.左右都空，true;  2. 左右一个空，false;  3. 左右子树值不等，false;  4. 根据相等或者对称的要求进行左右子树递归
	//5. 删点成林：思路很简单：从root开始递归左右节点，如果是在删除集合中，则置null并且看他是否存在左右子树，存在则成为新的树记录下来。最后要判断root
	//是否还存在，存在则加入结果集。
	//*****需要辅助函数进行递归每个节点返回root，相当于两递归，主程序递归访问每个节点，辅助函数做每个结点的处理工作
	//同572子树问题：主程序递归访问每个节点，然后辅助函数去判断单个节点是否成立，最后主程序return的是 root||left||right
	//同437路径之和
	//6. 基本模板：先序中序后序恢复二叉树
	//核心：利用哈希表存放中序/后序序列值与下标(序列中无重复值)，然后每次确定一个节点，建立并加入其左右子节点(递归)，返回该节点
	//我们所需要做的，就是根据一个序列中的值（一般都是根所在位置）对应找到另一个序列中的位置，划分左右子树，然后递归左右子树即可
	//恢复模板中也需要引入辅助函数进行建立节点并递归左右子树
	//**特殊的：先序后序复原树结果中的一种：先序开头==后序末尾，无法划分左右子树；所以考虑用先序的第二个节点"左子树的根"在后序中的位置来实现左右子树划分
	//7. 先序中序后序的非递归写法：以先序为例：根左右：根指的就是pop操作，左右在压入栈中就是右左，所以总的顺序是
	//先pop表示根，然后依次压入右左子节点即可。
	//8. 二叉搜索树必用的性质1：中序递增，所以可以先用中序遍历得到中序序列放入list中，然后再去操作(放入list不用递归了)
	//9. 重点题669修剪二叉搜索树：通过root.val和(L,R)的范围来确定递归哪一侧，return root返回该节点，左归左，右归右实现连接
	//10. 翻转二叉树：和对称树/相等树不同：只需要把左右子节点交换，然后递归左右子树即可，return root实现返回
	//11. 二叉搜索树必用的性质2：中序递增，反中序递减，可以在递归中按照这个顺序，“根”用来处理数据，如538累加树中间处理根。把数据累加并放入对应的节点。
	//12. *****二叉搜索树如何记忆前一个序列值：在递归中(根的最后)，加一句pre=root.val,把本层的值放入pre中，然后后一个递归过来时候
	//root.val是这一层的值，pre就是上一层的序列值，这样就实现了记忆。注意下开头需要单独考虑，设置pre=-1表示开头
	//13. 基本模板：最近公共祖先
	//二叉搜索树：在同侧，递归同侧树；不同侧，就是root。利用的是值关系判断同侧不同侧
	//普通二叉树：利用无限递归法判断同侧不同侧：无限递归左侧，右侧，寻找是否存在p,q;如果左右一个为空，一个为p/q,说明在同一侧，此时最近祖先就是p/q;
	//如果左右都存在，说明p,q在root的两侧，所以最近祖先就是root返回即可。递归终止条件：递归到p,q---return root；判断空/不空，也是返回root即可
	//14. 巧妙：有序链表转化平衡二叉搜索树：找中间节点作为根节点，然后两侧的中间节点再作为左右子树的根节点...
	//15. 二叉搜索树的两数之和和二叉树的两数之和是一样的解法：不要被搜索树的条件骗了：先用哈希表来找/存，然后遍历左右子树即可
	//16. 删除二叉搜索树中的一个节点，逻辑：左右一个空一个非空：那就用左/右替代；两个都不空，需要用中序序列的前驱/后继来替代，
	//比如：后继，右子树的最小节点值，即右子树的最左叶子节点，右-左-左-左...,找到后替代被删除的节点即可：替代过程就是左换左，右换右
	//然后过程就换成了删除后继(多写一个递归)，删除后继由于左右都是空，所以就直接return
	
	
	
	
	//*****二叉树和单链表其实就一个区别：多了一个指针；（出发边是两条或者一条）
	
	//*****二叉树最重要的是如何写递归：
	//写递归最重要的一个点：先写主程序，再写边界条件。先把主程序+递归写了，然后特殊情况其实就是边界条件，可以放在最后再去考虑即可一举两得。
	
	//*****二叉搜索树：想象为一个有序序列来处理，中序为递增序列，反中序为递减序列，然后题目等价为如何处理这个序列即可
	
	//*****根据两个序列复原二叉树：
	//核心：利用哈希表存放中序/后序序列值与下标，然后每次确定一个节点，建立并加入其左右子节点，返回该节点
	//我们所需要做的，就是根据一个序列中的值（一般都是根所在位置）对应找到另一个序列中的位置，划分左右子树，然后递归左右子树即可
	
	//*****最近公共祖先题：二叉搜索树利用的是值关系，快速递归判定；普通二叉树利用的是无限递归到null/p/q，然后即可判定祖先（先递归后判断，所以后序遍历）
	
	
	
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
	 
	 //104:二叉树的最大深度
	 //练习这种二叉树的递归思路
	 public int maxDepth(TreeNode root) {
		 if(root==null) return 0;                        //要有一个递归出口：一般是为null,或者为单个元素这些特殊情况作为出口
		 int left=maxDepth(root.left);                   //递归两侧
		 int right=maxDepth(root.right);
		 return Math.max(left, right)+1;                 //返回本次所需要的结果
	    }
	 
	 //110：平衡二叉树的判断
	 //和高度很类似，源自于高度判断，需要在求高度的基础上加一些限制
	 //利用高度返回-1来表示不平衡的情况
	 //如果左右树高度差大于1了，那就返回-1，而且在之前递归的过程中，如果出现了某个树高度为-1，那么已经不平衡了，之后的需要直接赋值为-1
	 public boolean isBalanced(TreeNode root) {
		 return helper110(root)!=-1;
	    }
	 
	 public int helper110(TreeNode root) {
		 if(root==null) return 0;
		 int left=helper110(root.left);                   
		 int right=helper110(root.right);
		 if(Math.abs((int)(left-right))>1||left==-1||right==-1) {
			 return -1;
		 }
		 return Math.max(left, right)+1;                 
	 }
	 
	 //543：最大直径
		    int ans;
		    public int diameterOfBinaryTree(TreeNode root) {
		        ans = 1;
		        depth(root);
		        return ans - 1;
		    }
		    public int depth(TreeNode node) {
		        if (node == null) {
		            return 0; // 访问到空节点了，返回0
		        }
		        int L = depth(node.left); // 左儿子为根的子树的深度
		        int R = depth(node.right); // 右儿子为根的子树的深度
		        ans = Math.max(ans, L+R+1); // 计算d_node即L+R+1 并更新ans
		        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
		    }
		    
	 
	 //437：路径之和3
	 //不用限制根节点和叶子节点为起始终止点
	 //核心思想：所以应该统计的是以每个节点为起始节点，向下遍历可以得到路径和的路径数目
	 //然后把每个节点的情况数目相加即可
	 //对于每个节点向下求路径数目的过程：包含了本节点，则左右子树需要target-node.val作为新的和递归(z注意特殊的，当前节点值就是和值，那就要把数目直接++)
	 //对于每个节点数目相加：直接利用递归左右子树，然后相加即可
	 public int pathSum(TreeNode root, int targetSum) {
		 if(root==null) return 0; 
		 int ret=rootSum(root,targetSum);                             //每个节点向下求路径数目
		 ret+=pathSum(root.left,targetSum);                           //递归每个节点的数目并相加
		 ret+=pathSum(root.right,targetSum);
		 return ret;
	 }
	 
	 public int rootSum(TreeNode root, int targetSum) {               
		 if(root==null) return 0;
		 int count=0;
		 if(root.val==targetSum) count++;
		 count+=rootSum(root.left,targetSum-root.val);
		 count+=rootSum(root.right,targetSum-root.val);
		 return count;
	 }
	 
	 //101：对称树的判断
	 //*****四步法：专门用于求解树的相等或者对称问题
	 //*****很重要：预处理：首先需要将主函数的左右子树拿出来放入辅助函数进行判别（这样求解比较方便）
     //然后四步法：1.左右都空，true;  2. 左右一个空，false;  3. 左右子树值不等，false;  4. 根据相等或者对称的要求进行左右子树递归
	 public boolean isSymmetric(TreeNode root) {
		 if(root==null) return true;
		 return helper101(root.left,root.right);
	    }
	 
	 public boolean helper101(TreeNode left,TreeNode right) {
		 if(left==null&&right==null) return true;
		 if(left==null||right==null) return false;
		 if(left.val!=right.val) return false;                //*****不等直接false,但是相等，还需要继续向下判断才行，所以不置值
		 return helper101(left.left,right.right)&&helper101(left.right,right.left);
	 }
	 
	 //1110:删点成林
	 //删除掉部分节点，然后用list记录一整个森林
	 //核心思想：（辅助函数）对于每一个需要删除的节点：如果存在左右子树，那么左右子树就变成了新的树，需要加入list;然后本节点需要置为null表示删除
	 //主函数：需要处理一个特殊的，需要判断最初始的那棵树是否还存在，如果存在则要把他加入list.(因为辅助函数只能把左右子树加入结果集，但是原先的那棵树没有加入)
	 //用哈希表存储待删除结点，可以方便判断哪些需要删除
	 public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
	        LinkedList<TreeNode> res=new LinkedList<>();
	        HashSet<Integer> hash=new HashSet<>();
	        for(int de:to_delete) {
	        	hash.add(de);
	        }
	        root=helper1110(root,hash,res);
	        if(root!=null) res.add(root);                        //特殊处理最开始的那棵树，判断是否存在，存在则记录
	        return res;
	    }
	 
	 public TreeNode helper1110(TreeNode root,HashSet<Integer> hash,LinkedList<TreeNode> res) {
		 if(root==null) return root;
		 root.left=helper1110(root.left,hash,res);
		 root.right=helper1110(root.right,hash,res);
		 if(hash.contains(root.val)) {
			 if(root.left!=null) res.add(root.left);            //判断待删除结点的左右子树是否存在，存在则记录
			 if(root.right!=null) res.add(root.right);
			 root=null;
		 }
		 return root;
	 }
	 
	 //637：二叉树每层的平均值
	 //层序遍历，用队列存储元素
	 //*****对于层序遍历题：只需要一个队列记录节点即可，对于每层的节点，可以在while内，先记录下当前层的数量，然后用for进行处理一层即可，然后就可以轻松开始下一层。
	 public List<Double> averageOfLevels(TreeNode root) {
		 if(root==null) return null;
		 List<Double> res=new LinkedList<>();
		 Queue<TreeNode> q=new LinkedList<>();
		 q.offer(root);
		 while(!q.isEmpty()) {
			 int count=q.size();                           //*****先记录当前层数量，便于后续处理
			 double sum=0;
			 for(int i=0;i<count;i++) {
				 TreeNode t=q.poll();
				 if(t.left!=null) q.offer(t.left);
				 if(t.right!=null) q.offer(t.right);
				 sum+=t.val;
			 }
			 res.add(sum/count);
		 }
        return res;
	    }
	 
	 //105：中序先序复原树结构
	 //原理上很简单明了，但是写递归就比较难
	 //*****核心思想：每次都是确定一个根节点放入树结构，然后递归左右子树作为根的左右节点，最后返回的是这个根即可。
	 //注：想要递归左右子树就需要确定先序中序里面哪一段是他的左子树，哪一段是右子树，才能够去递归
	 //所以确定递归的内容：先序，开始点，结束点；中序，开始点，结束点。
	 //为了找到先序的根在中序中的位置，便于确定左右子树的递归点，用哈希表存储中序序列即可。
	 HashMap<Integer,Integer> hash=new HashMap<>();
	 public TreeNode buildTree(int[] preorder, int[] inorder) {
		 for(int i=0;i<inorder.length;i++) {
			 hash.put(inorder[i], i);
		 }
		 return helper105(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
	 }
	 
	 public TreeNode helper105(int[] preorder,int preleft,int preright,int[] inorder,int inleft,int inright) {
		 if(preleft>preright) return null;        //*****递归终止条件先不写，等到递归写出来了，就知道了；因为递归时候，每次先序中左节点位置要+1.所以终止条件就是
		 int root=preorder[preleft];              //左节点>右节点，等于时候是恰好一个元素的情况
		 TreeNode r=new TreeNode(root);
		 int k=hash.get(root);
		 int leftsize=k-inleft;
		 r.left=helper105(preorder,preleft+1,preleft+leftsize,inorder,inleft,inleft+leftsize-1);    //确定左右子树在先序中序序列中所在的位置，便于递归
		 r.right=helper105(preorder,preleft+leftsize+1,preright,inorder,inleft+leftsize+1,inright);
		 return r;
	 }
	
	 //144：先序遍历的非递归写法
	 //核心：用栈来维护，每次弹出一个节点（先出根），并且先放入他的右节点，再放入左节点
	 //因为：栈中，放右左，出来就是左右，这样就维持了先序序列根左右
	 public List<Integer> preorderTraversal(TreeNode root) {
		 if(root==null) return new LinkedList<Integer>();
		 Stack<TreeNode> stack=new Stack<>();
		 List<Integer> list=new LinkedList<>();
		 stack.push(root);
		 while(!stack.isEmpty()) {
			 TreeNode t=stack.pop();                //先出根
			 list.add(t.val); 
			 if(t.right!=null) {                    //然后先压右，再压左，这样出来就是根左右
				 stack.push(t.right);
			 }
			 if(t.left!=null) {
				 stack.push(t.left);
			 }
		 }
		 return list;
	    }
	 
	 //99：二叉搜索树两交换节点的恢复
	 //*****二叉搜索树必用的性质：中序递增，所以一般都是先用中序遍历得到中序序列，然后再去操作
	 //核心：中序遍历后，如果出现两个元素a[i]>a[i+1]，a[j]>a[j+1],那就说明需要交换a[i]和a[j+1](自己试试就知道了)；如果只有一个元素a[k]>a[k+1],那就需要交换a[k],a[k+1]
	 //主要区别在于：邻位互换只会出现一次元素乱序，隔位交换就会出现两次元素乱序
	 //找到需要交换的元素后，将对应的节点变化即可（用先序遍历就行）
	 public void recoverTree(TreeNode root) {
		 List<Integer> inorder=new LinkedList<>();
		 inorder99(root,inorder);
		 int[] twoInt=new int[2];
		 twoInt=findTwoSwap(inorder);
		 recover(root,inorder.get(twoInt[0]),inorder.get(twoInt[1]));          //*****小心点，findTwoSwap里面得到的只是下标，这里最后需要的是值，所以要用get函数
	    }
	 
	 public void inorder99(TreeNode root,List<Integer> inorder) {
		 if(root==null) return ;
		 inorder99(root.left,inorder);
		 inorder.add(root.val);
		 inorder99(root.right,inorder);
	 }
	 
	 public static int[] findTwoSwap(List<Integer> inorder) {
		 int[] r=new int[2];
		 int count=2;
		 for(int i=0;i<inorder.size()-1;i++) {
			 if(inorder.get(i)>inorder.get(i+1)) {
				 if(count==2) {
					 r[0]=i;
					 r[1]=i+1;
				 }else if(count==1) {
					 r[1]=i+1;
					 break;
				 }
				 count--;
			 }
		 }
		 return r;
	 }
	 
	 public void recover(TreeNode root,int x,int y) {
		 if(root==null) return ;
		 if(root.val==x||root.val==y) {
			 root.val=root.val==x?y:x;
		 }
		 recover(root.left,x,y);
		 recover(root.right,x,y);
	 }
	 
	 //669：修剪二叉搜索树
	 //很巧妙：修剪二叉树：只递归符合的节点
	 //核心：根小于low，只递归右侧；根大于high,只递归左侧；根属于有效范围，则直接继续向下递归判断后面的是否符合即可，左归左，右归右，连接起来
	 public TreeNode trimBST(TreeNode root, int low, int high) {
		 if(root==null) return null;
		 if(root.val<low) {
			 return trimBST(root.right,low,high);
		 }else if(root.val>high) {
			 return trimBST(root.left,low,high);
		 }else {
			 root.left=trimBST(root.left,low,high);
			 root.right=trimBST(root.right,low,high);
		 }
         return root;
	    }
	 
	 //208：字典树的实现
	 //核心：每个字典树维护一个节点数组children[26]表示接下来的   26个小写英文字母指向     是null/存在的节点；再维护一个表示单词结束（叶子节点）的标志，boolean
	 //null就代表的该指向没有节点，那就是断开的树；有节点（当然这里节点没有任何内容，但不是null），那就表示存在这个字母指向
	 //insert: 依次向后判断children数组指向是否存在，存在直接向后一个，不存在则需要建立指向
	 //search前缀：依次向后判断是否存在，不存在则false;一直存在，返回最后的那个前缀节点
	 //搜索单词：不仅要前缀存在，且最后要到末尾。
	 class Trie {
		 private Trie[] children;
		 private boolean end;

		    public Trie() {
		    	children=new Trie[26];
		    	end=false;

		    }
		    
		    public void insert(String word) {
		    	Trie node =this;
		    	for(int i=0;i<word.length();i++) {
		    		char c=word.charAt(i);
		    		int index=c-'a';
		    		if(node.children[index]==null) {
		    			node.children[index]=new Trie();
		    		}
		    		node=node.children[index];		
		    	}
		    	node.end=true; 
		    }
		    
		    public boolean search(String word) {
		    	Trie node=prefix208(word);
		    	return node!=null&&node.end==true;
		    }
		    
		    public boolean startsWith(String prefix) {
                return prefix208(prefix)!=null;
		    }
		    
		    public Trie prefix208(String prefix) {
		    	Trie node=this;
		    	for(int i=0;i<prefix.length();i++) {
		    		char c=prefix.charAt(i);
		    		int index=c-'a';
		    		if(node.children[index]==null) {
		    			return null;
		    		}
		    		node=node.children[index];		
		    	}
		    	return node;
		    }
		}
	 
	 //226:翻转二叉树
	 //不用想的过于复杂：对称树的判断是需要左右子树放入辅助函数，然后判断左左，左右等等
	 //但是本题翻转：*****当我们交换两个子节点时，其后面的节点也会跟着过去的（非常重要），相当于是左右两颗子树整体交换，而不是单纯的两个节点交换，这点一定要注意
	 //然后我们再继续翻转子节点即可，不用想单纯的交换值，太复杂了。
	 public TreeNode invertTree(TreeNode root) {
		 if(root==null) return null;
		 TreeNode temp=root.left;             //交换左右子节点
		 root.left=root.right;
		 root.right=temp;
		 invertTree(root.left);               //继续向后翻转
		 invertTree(root.right);
		 return root;
	    }
	 
	 //617：合并两个二叉树
	 //核心：先写主程序：对应位置的val值相加，然后递归左右子树，return节点；再去考虑终止条件：如果左右其中一个为空，那就不能直接加val值
	 //这种情况，因为不需要加入为空的节点的值，所以直接就是返回非空节点即可
	 //*****写递归的思想：先考虑主递归程序怎么写，然后再去写终止条件，终止条件就是特殊情况，最后写即可，方便带入特殊情况
	 public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
		 if(root1==null) return root2;
		 if(root2==null) return root1;
		 TreeNode node=new TreeNode(root1.val+root2.val);
		 node.left=mergeTrees(root1.left,root2.left);
		 node.right=mergeTrees(root1.right,root2.right);
		 return node;
	    }
	 
	 //572：判断子树（包含所有后代）
	 //很简单：对于主程序，首先判断以root为根的能否和sub相等，如果不等，那就继续递归用左右子树为根，和sub比较即可。辅函数就是判断两个树（题中条件：包含所有后代）是否相等即可
	 public boolean isSubtree(TreeNode root, TreeNode subRoot) {
		 if(root==null) return false;                              //这道题有个限制条件就是，如果出现root为null的情况，必然判false;所以直接加一个条件就可以了
		 return isEqual(root,subRoot)||isSubtree(root.left,subRoot)||isSubtree(root.right,subRoot);//如果左边为空，递归时候自动判false;
	    }
	 
	 public boolean isEqual(TreeNode root,TreeNode subRoot) {
		 if(root==null&&subRoot==null) return true;
		 if(root==null&&subRoot!=null) return false;
		 if(root!=null&&subRoot==null) return false;
		 if(root.val!=subRoot.val) return false;
		 return isEqual(root.left,subRoot.left)&&isEqual(root.right,subRoot.right);
	 }
	 
	 //404：左叶子节点之和
	 //很简单，确定好需要加入的左叶子节点的条件，然后递归下一个左右节点即可，并且和需要继续加入。
	 //*****sum实际上存的是当次需要加入的值，不是总和值；递归完后，才是总和值
	 public int sumOfLeftLeaves(TreeNode root) {
		 int sum=0;                             //*****非常重要：这里sum放在内部，每次都需要置成0，为什么？因为return是一步步返回的，上一步的已经记录下来了，并且return中是步步加，所以下一步
		 if(root==null) return 0;               //的递归只需要记录当前是否加入即可，所以要步步加，步步0开始
		 //*****递归终止条件：对于空节点，当次的和值，只需要返回0即可，0加入之前的结果，等于没影响，所以这里必须返回0
		 if(root.left!=null&&root.left.left==null&&root.left.right==null) {
			 sum+=root.left.val;
		 }
		 return sum+sumOfLeftLeaves(root.left)+sumOfLeftLeaves(root.right);
	    }
	 
	 //513：二叉树左下角的值
	 //核心：层序遍历，本来是一层层的先放左再放右，最后一个是右下角；现在改成左下角，就是先放右，再放左进队列即可。
	 public int findBottomLeftValue(TreeNode root) {
		 Queue<TreeNode> q=new LinkedList<>();
		 q.offer(root);
		 int res=0;
		 while(!q.isEmpty()) {
			 int size=q.size();
			 for(int i=0;i<size;i++) {
				 TreeNode t=q.poll();
				 if(t.right!=null) q.offer(t.right);
				 if(t.left!=null) q.offer(t.left);
				 res=t.val;
			 } 
		 }
		 return res;
	    }
	 
	 //538:二叉搜索树转化为累加树
	 //*****二叉搜索树：想象为一个单调序列来处理，中序为递增序列，反中序为递减序列，然后题目等价为如何处理这个序列即可
	 //核心：反中序是递减序列，先遍历大，再遍历小，我们要求的是当前值和大于他的所有的值之和，也就是说需要维护一个sum确定当前遍历到的和值即可
	 int sum538=0;
	 public TreeNode convertBST(TreeNode root) {
		 if(root==null) return null;
		 convertBST(root.right);
		 sum538+=root.val;
		 root.val=sum538;
		 convertBST(root.left);
		 return root;
	    }
	 
	 //235:二叉搜索树的最近公共祖先
	 //核心：如果p,q位于root两端，那么root就是祖先；p,q位于同侧，那就是要么在root左，要么在root右侧，确定下，然后递归即可
	 //终止条件：如果直接到了p或者q，说明剩下另一个肯定在这个的后面出现，那就直接返回该值即可
	 public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		 if(p.val==root.val||q.val==root.val) return root;
		 if((p.val>root.val&&q.val<root.val)||(p.val<root.val&&q.val>root.val)) {
			 return root;
		 }else if(p.val<root.val&&q.val<root.val) {
			 return lowestCommonAncestor(root.left,p,q);
		 }else {
			 return lowestCommonAncestor(root.right,p,q);
		 }    
	    }
	 
	 //530：二叉搜索树的最小差
	 //又是二叉搜索树，直接想象为一个有序序列来处理，也就是求有序序列的最小差，那就是相邻差的最小值。
	 //因为中序递增，所以用后一个值减去前一个值，即为差值，并记录最小的即可
	 int pre=-1;                              //*****注意下：最开始第一个树中元素不需要减pre，没有差值，所以这里设置pre初值为-1，便于分辨是否是开头。
	 int min530=Integer.MAX_VALUE;
	 public int getMinimumDifference(TreeNode root) {
		 if(root==null) return min530;
		 getMinimumDifference(root.left);
		 if(pre==-1) {
			 pre=root.val;
		 }else {
		 min530=Math.min(min530, root.val-pre);
		 pre=root.val;
		 }
		 getMinimumDifference(root.right);
         return min530;
	    }
	 
	 //889:先序后序复原树
	 //虽然没办法唯一确定，但是可以复原答案之一即可
	 //核心：先自己算一遍这个复原过程：应该是，寻找先序当前节点的后一个节点在后序中的位置（因为第一个节点必然是在后序的最后一个节点，所以找第二个节点确定左右子树分割）
	 //先序中左子树的根在后序中就是左子树的最后一个节点index,那么就可以划分左右子树了。
	 //*****（为什么可以递归？）因为我们拿出的是先序中的节点，所以递归后左右子节点是可以定下的，因为先序就是根-左-右，拿出左右子树的根就是原来root的左右子节点
	 HashMap<Integer,Integer> hash889=new HashMap<>();
	 public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
		 for(int i=0;i<postorder.length;i++) {
			 hash889.put(postorder[i], i);
		 }
         return helper889(preorder,postorder,0,preorder.length-1,0,postorder.length-1);
	    }
	 
	 public TreeNode helper889(int[] preorder, int[] postorder,int preleft,int preright,int postleft,int postright) {
		 if(preleft>preright) return null;
		 int root=preorder[preleft];
		 TreeNode t=new TreeNode(root);
		 if(preleft==preright) return t;
		 int index=hash889.get(preorder[preleft+1]);
		 t.left=helper889(preorder,postorder,preleft+1,index+preleft+1-postleft,postleft,index);
		 t.right=helper889(preorder,postorder,index+preleft+1-postleft+1,preright,index+1,postright-1);
		 return t;
	 }	 
	 
	 //106：中序后序复原树
	 //核心：和前两个一样，寻找节点在另一个序列中的位置，分割左右子树递归即可
	  HashMap<Integer,Integer> hash106=new HashMap<>();
	  public TreeNode buildTree106(int[] inorder, int[] postorder) {
		  for(int i=0;i<inorder.length;i++) {
				 hash106.put(inorder[i], i);
			 }
	         return helper106(inorder,postorder,0,inorder.length-1,0,postorder.length-1);
	    }
	  
	  public TreeNode helper106(int[] inorder, int[] postorder,int inleft,int inright,int postleft,int postright) {
			 if(postleft>postright) return null;
			 int root=postorder[postright];
			 TreeNode t=new TreeNode(root);
			 int index=hash106.get(root);
			 t.left=helper106(inorder,postorder,inleft,index-1,postleft,postleft+index-inleft-1);
			 t.right=helper106(inorder,postorder,index+1,inright,postleft+index-inleft,postright-1);
			 return t;
		 }	 
	  
	  //94：中序遍历
	  List<Integer> res94=new LinkedList<>();
	  public List<Integer> inorderTraversal(TreeNode root) {
		  if(root==null) return res94;
		  inorderTraversal(root.left);
		  res94.add(root.val);
          inorderTraversal(root.right);
          return res94;
	    }
	  
	  //145:后序遍历
	  List<Integer> res145=new LinkedList<>();
	  public List<Integer> postorderTraversal(TreeNode root) {
		  if(root==null) return res145;
		  postorderTraversal(root.left);
		  postorderTraversal(root.right);
		  res145.add(root.val);
		  return res145;
	    }
	  
	  //236:普通二叉树的最近公共祖先
	  //二叉搜索树有值关系，可以直接用值比较来快速确定递归
	  //*****普通二叉树最近公共祖先：利用无限递归，到null
	  //核心：如果p,q在root的两侧，那么递归两侧肯定会碰到p,q,此时最近公共祖先就是root;如果p,q在一侧，那么另一侧进行无限递归，必然返回null,
	  //此时只需要本侧第一个递归到的p/q，就是他俩的祖先。之后再去确定终止条件（因为先递归后判断，所以是后序遍历）
	  public TreeNode lowestCommonAncestor236(TreeNode root, TreeNode p, TreeNode q) {
		  if(root==null||root==p||root==q) return root;
		  TreeNode left=lowestCommonAncestor(root.left,p,q);         //*****无限递归法：实际上就是不停递归，到特定值null/p/q才返回，非常的简单直接
		  TreeNode right=lowestCommonAncestor(root.right,p,q);
		  if(left==null) return right;
		  if(right==null) return left;
		  return root;
	    }
	  
	  //109：有序链表转化为平衡二叉搜索树
	  //核心：尽可能的平衡-----找中间节点作为根节点，那么左右两侧节点数目尽量相等，每次都找中间节点，就会尽可能的平衡
	  //找中间节点作为根节点，左右两侧序列的中间节点再作为根的左右子节点，依次递归，即可得到尽量平衡的二叉搜索树
	  public class ListNode {
		       int val;
		       ListNode next;
		       ListNode() {}
		       ListNode(int val) { this.val = val; }
		       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
		   }
	  public TreeNode sortedListToBST(ListNode head) {
		  return buildBST(head,null);                    //*****巧妙：对于链表划分为左右两侧，需要起始，末尾两个参数，所以这里一开始头是链表头，末尾是null，就是最后一个结点的后一个
	    }                                                //因为多加一个null,不影响中间节点，而且可以让后面的处理更加简单（直接找链表尾部，需要O（n）,所以这里用null简单多了）
	  
	  public TreeNode buildBST(ListNode head,ListNode tail) {
		  if(head==tail) return null;
		  ListNode l=findMiddle(head,tail);
		  TreeNode t=new TreeNode(l.val);
		  t.left=buildBST(head,l);
		  t.right=buildBST(l.next,tail);
		  return t;
	  }
	  
	  public ListNode findMiddle(ListNode left,ListNode right) {
		  ListNode slow=left;
		  ListNode fast=left;
		  while(fast.next!=right&&fast.next.next!=right) {   //*****小心点，这里末尾不是null,而是right，while终止条件要注意下
			  fast=fast.next.next;
			  slow=slow.next;
		  }
		  return slow;
	  }
	  
	  //897：搜索树转化为递增搜索树
	  //*****稍微注意下：直接用res会导致后面没办法返回初始开始的节点了，所以需要一个引用index来负责增加树，res单纯指向开头即可，用于结果返回
	  TreeNode res=new TreeNode(0);
	  TreeNode index=res;                             //建立引用，负责增树
	  public TreeNode increasingBST(TreeNode root) {
		  if(root==null) return res.right;
		  increasingBST(root.left);
		  TreeNode t=new TreeNode(root.val);
		  index.right=t;
		  index=index.right;
		  increasingBST(root.right);
          return res.right;
	    }
	  
	  //653：二叉搜索树的两数之和
	  //*****别想太复杂了，实际上和数组是一样的，都需要一个哈希表来存储元素，检索元素，只不过二叉树和数组遍历过程有区别而已。
	  //这道题，还是和数组一样的用哈希表存储，只不过需要遍历元素而已。数组是直接正序遍历，二叉树需要先序，中序，后序选一个即可。
	  HashSet<Integer> hash653=new HashSet<Integer>();
	  public boolean findTarget(TreeNode root, int k) {
		  if(root==null) return false;
		  if(hash653.contains(k-root.val)) {
			  return true;
		  }else {
			  hash653.add(root.val);
			  }
		  return findTarget(root.left,k)||findTarget(root.right,k);		  
	    }
	  
	  //450：删除二叉搜索树中的一个节点
	  //二叉搜索树的删除：主要在于替换原则上
	  //如果只有左子树或者右子树：那就直接替换
	  //如果都有：用二叉搜索树的后继或者前驱来替换----比如后继，就是右子树中的最小值，也就是右子树中最左端的节点来替换根节点的值
	  //替换节点值，还要删除该节点（继续重复删除的过程）
	  //*****这道题递归的实现过程非常复杂：
	  //1. 没遍历到待删除节点时，左递归归左，右递归归右，返回根节点
	  //2. 到了待删除结点，需要开始判断过程，判断左右是否为空
	  //3. 左右都非空，找到需要用于替换的节点，再次执行递归，之后需要加上删除过程-----就是将该节点的左给到根左，右给到根右
	  public TreeNode deleteNode(TreeNode root, int key) {
		  if(root==null) return null;
		  if(root.val>key) {
			  root.left=deleteNode(root.left,key);
			  return root;
		  }
		  if(root.val<key) {
			  root.right=deleteNode(root.right,key);
			  return root;
		  }
		  if(root.val==key) {
			  if(root.left==null&&root.right==null) return null;
			  if(root.left==null) return root.right;
			  if(root.right==null) return root.left;
			  TreeNode t=root.right;
			  while(t.left!=null) {
				  t=t.left;
			  }
			  root.right=deleteNode(root.right,t.val);
			  t.right=root.right;                        //*****这就是删除过程和替换过程一体的，将用于替换的节点直接去替换根节点即可。
			  t.left=root.left;
			  return t;
		  }
		  return root;
	  }
	  
	  
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
