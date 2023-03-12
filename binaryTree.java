package work;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;
import java.lang.reflect.Array;

public class binaryTree {
	
	//�������ܽ᣺д��û��ʲô�����ԣ���Ҫ�ǻ���ģ��+����+������Ŀ
	//1. ��ôд�������ĵݹ�:��д������(�ݹ���������)��Ȼ����д�߽�������
	//*****�ص㼼�ɣ������ֵݹ�����Ҫ�ķ���ֵ��������Ҫ��ķ���ֵ��̫һ��ʱ������Ҫ���븨������ȥ����ݹ飬
	//Ȼ����ȥ�������д���ݹ鷵��ֵ���ɡ�
	//2. ����ģ�壺�йظ߶ȵ���Ŀͬһģ��
	//���������(����ģ��)---ƽ��������ж�(������������-1��ʾ��ƽ��)---���ֱ��(ֱ��ת��Ϊ��(L+R+1)ȡ���ֵ)
	//3. ����ģ�壺·��֮��
	//437
	//4. ����ģ�壺�����/�Գ������ж�
	//�Ĳ�����ר���������������Ȼ��߶Գ�����
	//����Ҫ��Ԥ����������Ҫ�������������������ó������븨�����������б��������ȽϷ��㣩
    //Ȼ���Ĳ�����1.���Ҷ��գ�true;  2. ����һ���գ�false;  3. ��������ֵ���ȣ�false;  4. ������Ȼ��߶ԳƵ�Ҫ��������������ݹ�
	//5. ɾ����֣�˼·�ܼ򵥣���root��ʼ�ݹ����ҽڵ㣬�������ɾ�������У�����null���ҿ����Ƿ���������������������Ϊ�µ�����¼���������Ҫ�ж�root
	//�Ƿ񻹴��ڣ����������������
	//*****��Ҫ�����������еݹ�ÿ���ڵ㷵��root���൱�����ݹ飬������ݹ����ÿ���ڵ㣬����������ÿ�����Ĵ�����
	//ͬ572�������⣺������ݹ����ÿ���ڵ㣬Ȼ��������ȥ�жϵ����ڵ��Ƿ���������������return���� root||left||right
	//ͬ437·��֮��
	//6. ����ģ�壺�����������ָ�������
	//���ģ����ù�ϣ��������/��������ֵ���±�(���������ظ�ֵ)��Ȼ��ÿ��ȷ��һ���ڵ㣬�����������������ӽڵ�(�ݹ�)�����ظýڵ�
	//��������Ҫ���ģ����Ǹ���һ�������е�ֵ��һ�㶼�Ǹ�����λ�ã���Ӧ�ҵ���һ�������е�λ�ã���������������Ȼ��ݹ�������������
	//�ָ�ģ����Ҳ��Ҫ���븨���������н����ڵ㲢�ݹ���������
	//**����ģ��������ԭ������е�һ�֣�����ͷ==����ĩβ���޷������������������Կ���������ĵڶ����ڵ�"�������ĸ�"�ں����е�λ����ʵ��������������
	//7. �����������ķǵݹ�д����������Ϊ���������ң���ָ�ľ���pop������������ѹ��ջ�о������������ܵ�˳����
	//��pop��ʾ����Ȼ������ѹ�������ӽڵ㼴�ɡ�
	//8. �������������õ�����1��������������Կ���������������õ��������з���list�У�Ȼ����ȥ����(����list���õݹ���)
	//9. �ص���669�޼�������������ͨ��root.val��(L,R)�ķ�Χ��ȷ���ݹ���һ�࣬return root���ظýڵ㣬������ҹ���ʵ������
	//10. ��ת���������ͶԳ���/�������ͬ��ֻ��Ҫ�������ӽڵ㽻����Ȼ��ݹ������������ɣ�return rootʵ�ַ���
	//11. �������������õ�����2�����������������ݼ��������ڵݹ��а������˳�򣬡����������������ݣ���538�ۼ����м䴦������������ۼӲ������Ӧ�Ľڵ㡣
	//12. *****������������μ���ǰһ������ֵ���ڵݹ���(�������)����һ��pre=root.val,�ѱ����ֵ����pre�У�Ȼ���һ���ݹ����ʱ��
	//root.val����һ���ֵ��pre������һ�������ֵ��������ʵ���˼��䡣ע���¿�ͷ��Ҫ�������ǣ�����pre=-1��ʾ��ͷ
	//13. ����ģ�壺�����������
	//��������������ͬ�࣬�ݹ�ͬ��������ͬ�࣬����root�����õ���ֵ��ϵ�ж�ͬ�಻ͬ��
	//��ͨ���������������޵ݹ鷨�ж�ͬ�಻ͬ�ࣺ���޵ݹ���࣬�Ҳ࣬Ѱ���Ƿ����p,q;�������һ��Ϊ�գ�һ��Ϊp/q,˵����ͬһ�࣬��ʱ������Ⱦ���p/q;
	//������Ҷ����ڣ�˵��p,q��root�����࣬����������Ⱦ���root���ؼ��ɡ��ݹ���ֹ�������ݹ鵽p,q---return root���жϿ�/���գ�Ҳ�Ƿ���root����
	//14. �����������ת��ƽ����������������м�ڵ���Ϊ���ڵ㣬Ȼ��������м�ڵ�����Ϊ���������ĸ��ڵ�...
	//15. ����������������֮�ͺͶ�����������֮����һ���Ľⷨ����Ҫ��������������ƭ�ˣ����ù�ϣ������/�棬Ȼ�����������������
	//16. ɾ�������������е�һ���ڵ㣬�߼�������һ����һ���ǿգ��Ǿ�����/����������������գ���Ҫ���������е�ǰ��/����������
	//���磺��̣�����������С�ڵ�ֵ����������������Ҷ�ӽڵ㣬��-��-��-��...,�ҵ��������ɾ���Ľڵ㼴�ɣ�������̾��������һ���
	//Ȼ����̾ͻ�����ɾ�����(��дһ���ݹ�)��ɾ������������Ҷ��ǿգ����Ծ�ֱ��return
	
	
	
	
	//*****�������͵�������ʵ��һ�����𣺶���һ��ָ�룻������������������һ����
	
	//*****����������Ҫ�������д�ݹ飺
	//д�ݹ�����Ҫ��һ���㣺��д��������д�߽��������Ȱ�������+�ݹ�д�ˣ�Ȼ�����������ʵ���Ǳ߽����������Է��������ȥ���Ǽ���һ�����á�
	
	//*****����������������Ϊһ��������������������Ϊ�������У�������Ϊ�ݼ����У�Ȼ����Ŀ�ȼ�Ϊ��δ���������м���
	
	//*****�����������и�ԭ��������
	//���ģ����ù�ϣ��������/��������ֵ���±꣬Ȼ��ÿ��ȷ��һ���ڵ㣬�����������������ӽڵ㣬���ظýڵ�
	//��������Ҫ���ģ����Ǹ���һ�������е�ֵ��һ�㶼�Ǹ�����λ�ã���Ӧ�ҵ���һ�������е�λ�ã���������������Ȼ��ݹ�������������
	
	//*****������������⣺�������������õ���ֵ��ϵ�����ٵݹ��ж�����ͨ���������õ������޵ݹ鵽null/p/q��Ȼ�󼴿��ж����ȣ��ȵݹ���жϣ����Ժ��������
	
	
	
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
	 
	 //104:��������������
	 //��ϰ���ֶ������ĵݹ�˼·
	 public int maxDepth(TreeNode root) {
		 if(root==null) return 0;                        //Ҫ��һ���ݹ���ڣ�һ����Ϊnull,����Ϊ����Ԫ����Щ���������Ϊ����
		 int left=maxDepth(root.left);                   //�ݹ�����
		 int right=maxDepth(root.right);
		 return Math.max(left, right)+1;                 //���ر�������Ҫ�Ľ��
	    }
	 
	 //110��ƽ����������ж�
	 //�͸߶Ⱥ����ƣ�Դ���ڸ߶��жϣ���Ҫ����߶ȵĻ����ϼ�һЩ����
	 //���ø߶ȷ���-1����ʾ��ƽ������
	 //����������߶Ȳ����1�ˣ��Ǿͷ���-1��������֮ǰ�ݹ�Ĺ����У����������ĳ�����߶�Ϊ-1����ô�Ѿ���ƽ���ˣ�֮�����Ҫֱ�Ӹ�ֵΪ-1
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
	 
	 //543�����ֱ��
		    int ans;
		    public int diameterOfBinaryTree(TreeNode root) {
		        ans = 1;
		        depth(root);
		        return ans - 1;
		    }
		    public int depth(TreeNode node) {
		        if (node == null) {
		            return 0; // ���ʵ��սڵ��ˣ�����0
		        }
		        int L = depth(node.left); // �����Ϊ�������������
		        int R = depth(node.right); // �Ҷ���Ϊ�������������
		        ans = Math.max(ans, L+R+1); // ����d_node��L+R+1 ������ans
		        return Math.max(L, R) + 1; // ���ظýڵ�Ϊ�������������
		    }
		    
	 
	 //437��·��֮��3
	 //�������Ƹ��ڵ��Ҷ�ӽڵ�Ϊ��ʼ��ֹ��
	 //����˼�룺����Ӧ��ͳ�Ƶ�����ÿ���ڵ�Ϊ��ʼ�ڵ㣬���±������Եõ�·���͵�·����Ŀ
	 //Ȼ���ÿ���ڵ�������Ŀ��Ӽ���
	 //����ÿ���ڵ�������·����Ŀ�Ĺ��̣������˱��ڵ㣬������������Ҫtarget-node.val��Ϊ�µĺ͵ݹ�(zע������ģ���ǰ�ڵ�ֵ���Ǻ�ֵ���Ǿ�Ҫ����Ŀֱ��++)
	 //����ÿ���ڵ���Ŀ��ӣ�ֱ�����õݹ�����������Ȼ����Ӽ���
	 public int pathSum(TreeNode root, int targetSum) {
		 if(root==null) return 0; 
		 int ret=rootSum(root,targetSum);                             //ÿ���ڵ�������·����Ŀ
		 ret+=pathSum(root.left,targetSum);                           //�ݹ�ÿ���ڵ����Ŀ�����
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
	 
	 //101���Գ������ж�
	 //*****�Ĳ�����ר���������������Ȼ��߶Գ�����
	 //*****����Ҫ��Ԥ����������Ҫ�������������������ó������븨�����������б��������ȽϷ��㣩
     //Ȼ���Ĳ�����1.���Ҷ��գ�true;  2. ����һ���գ�false;  3. ��������ֵ���ȣ�false;  4. ������Ȼ��߶ԳƵ�Ҫ��������������ݹ�
	 public boolean isSymmetric(TreeNode root) {
		 if(root==null) return true;
		 return helper101(root.left,root.right);
	    }
	 
	 public boolean helper101(TreeNode left,TreeNode right) {
		 if(left==null&&right==null) return true;
		 if(left==null||right==null) return false;
		 if(left.val!=right.val) return false;                //*****����ֱ��false,������ȣ�����Ҫ���������жϲ��У����Բ���ֵ
		 return helper101(left.left,right.right)&&helper101(left.right,right.left);
	 }
	 
	 //1110:ɾ�����
	 //ɾ�������ֽڵ㣬Ȼ����list��¼һ����ɭ��
	 //����˼�룺����������������ÿһ����Ҫɾ���Ľڵ㣺�������������������ô���������ͱ�����µ�������Ҫ����list;Ȼ�󱾽ڵ���Ҫ��Ϊnull��ʾɾ��
	 //����������Ҫ����һ������ģ���Ҫ�ж����ʼ���ǿ����Ƿ񻹴��ڣ����������Ҫ��������list.(��Ϊ��������ֻ�ܰ�����������������������ԭ�ȵ��ǿ���û�м���)
	 //�ù�ϣ��洢��ɾ����㣬���Է����ж���Щ��Ҫɾ��
	 public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
	        LinkedList<TreeNode> res=new LinkedList<>();
	        HashSet<Integer> hash=new HashSet<>();
	        for(int de:to_delete) {
	        	hash.add(de);
	        }
	        root=helper1110(root,hash,res);
	        if(root!=null) res.add(root);                        //���⴦���ʼ���ǿ������ж��Ƿ���ڣ��������¼
	        return res;
	    }
	 
	 public TreeNode helper1110(TreeNode root,HashSet<Integer> hash,LinkedList<TreeNode> res) {
		 if(root==null) return root;
		 root.left=helper1110(root.left,hash,res);
		 root.right=helper1110(root.right,hash,res);
		 if(hash.contains(root.val)) {
			 if(root.left!=null) res.add(root.left);            //�жϴ�ɾ���������������Ƿ���ڣ��������¼
			 if(root.right!=null) res.add(root.right);
			 root=null;
		 }
		 return root;
	 }
	 
	 //637��������ÿ���ƽ��ֵ
	 //����������ö��д洢Ԫ��
	 //*****���ڲ�������⣺ֻ��Ҫһ�����м�¼�ڵ㼴�ɣ�����ÿ��Ľڵ㣬������while�ڣ��ȼ�¼�µ�ǰ���������Ȼ����for���д���һ�㼴�ɣ�Ȼ��Ϳ������ɿ�ʼ��һ�㡣
	 public List<Double> averageOfLevels(TreeNode root) {
		 if(root==null) return null;
		 List<Double> res=new LinkedList<>();
		 Queue<TreeNode> q=new LinkedList<>();
		 q.offer(root);
		 while(!q.isEmpty()) {
			 int count=q.size();                           //*****�ȼ�¼��ǰ�����������ں�������
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
	 
	 //105����������ԭ���ṹ
	 //ԭ���Ϻܼ����ˣ�����д�ݹ�ͱȽ���
	 //*****����˼�룺ÿ�ζ���ȷ��һ�����ڵ�������ṹ��Ȼ��ݹ�����������Ϊ�������ҽڵ㣬��󷵻ص�����������ɡ�
	 //ע����Ҫ�ݹ�������������Ҫȷ����������������һ������������������һ���������������ܹ�ȥ�ݹ�
	 //����ȷ���ݹ�����ݣ����򣬿�ʼ�㣬�����㣻���򣬿�ʼ�㣬�����㡣
	 //Ϊ���ҵ�����ĸ��������е�λ�ã�����ȷ�����������ĵݹ�㣬�ù�ϣ��洢�������м��ɡ�
	 HashMap<Integer,Integer> hash=new HashMap<>();
	 public TreeNode buildTree(int[] preorder, int[] inorder) {
		 for(int i=0;i<inorder.length;i++) {
			 hash.put(inorder[i], i);
		 }
		 return helper105(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
	 }
	 
	 public TreeNode helper105(int[] preorder,int preleft,int preright,int[] inorder,int inleft,int inright) {
		 if(preleft>preright) return null;        //*****�ݹ���ֹ�����Ȳ�д���ȵ��ݹ�д�����ˣ���֪���ˣ���Ϊ�ݹ�ʱ��ÿ����������ڵ�λ��Ҫ+1.������ֹ��������
		 int root=preorder[preleft];              //��ڵ�>�ҽڵ㣬����ʱ����ǡ��һ��Ԫ�ص����
		 TreeNode r=new TreeNode(root);
		 int k=hash.get(root);
		 int leftsize=k-inleft;
		 r.left=helper105(preorder,preleft+1,preleft+leftsize,inorder,inleft,inleft+leftsize-1);    //ȷ�����������������������������ڵ�λ�ã����ڵݹ�
		 r.right=helper105(preorder,preleft+leftsize+1,preright,inorder,inleft+leftsize+1,inright);
		 return r;
	 }
	
	 //144����������ķǵݹ�д��
	 //���ģ���ջ��ά����ÿ�ε���һ���ڵ㣨�ȳ������������ȷ��������ҽڵ㣬�ٷ�����ڵ�
	 //��Ϊ��ջ�У������󣬳����������ң�������ά�����������и�����
	 public List<Integer> preorderTraversal(TreeNode root) {
		 if(root==null) return new LinkedList<Integer>();
		 Stack<TreeNode> stack=new Stack<>();
		 List<Integer> list=new LinkedList<>();
		 stack.push(root);
		 while(!stack.isEmpty()) {
			 TreeNode t=stack.pop();                //�ȳ���
			 list.add(t.val); 
			 if(t.right!=null) {                    //Ȼ����ѹ�ң���ѹ�������������Ǹ�����
				 stack.push(t.right);
			 }
			 if(t.left!=null) {
				 stack.push(t.left);
			 }
		 }
		 return list;
	    }
	 
	 //99�������������������ڵ�Ļָ�
	 //*****�������������õ����ʣ��������������һ�㶼��������������õ��������У�Ȼ����ȥ����
	 //���ģ���������������������Ԫ��a[i]>a[i+1]��a[j]>a[j+1],�Ǿ�˵����Ҫ����a[i]��a[j+1](�Լ����Ծ�֪����)�����ֻ��һ��Ԫ��a[k]>a[k+1],�Ǿ���Ҫ����a[k],a[k+1]
	 //��Ҫ�������ڣ���λ����ֻ�����һ��Ԫ�����򣬸�λ�����ͻ��������Ԫ������
	 //�ҵ���Ҫ������Ԫ�غ󣬽���Ӧ�Ľڵ�仯���ɣ�������������У�
	 public void recoverTree(TreeNode root) {
		 List<Integer> inorder=new LinkedList<>();
		 inorder99(root,inorder);
		 int[] twoInt=new int[2];
		 twoInt=findTwoSwap(inorder);
		 recover(root,inorder.get(twoInt[0]),inorder.get(twoInt[1]));          //*****С�ĵ㣬findTwoSwap����õ���ֻ���±꣬���������Ҫ����ֵ������Ҫ��get����
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
	 
	 //669���޼�����������
	 //������޼���������ֻ�ݹ���ϵĽڵ�
	 //���ģ���С��low��ֻ�ݹ��Ҳࣻ������high,ֻ�ݹ���ࣻ��������Ч��Χ����ֱ�Ӽ������µݹ��жϺ�����Ƿ���ϼ��ɣ�������ҹ��ң���������
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
	 
	 //208���ֵ�����ʵ��
	 //���ģ�ÿ���ֵ���ά��һ���ڵ�����children[26]��ʾ��������   26��СдӢ����ĸָ��     ��null/���ڵĽڵ㣻��ά��һ����ʾ���ʽ�����Ҷ�ӽڵ㣩�ı�־��boolean
	 //null�ʹ���ĸ�ָ��û�нڵ㣬�Ǿ��ǶϿ��������нڵ㣨��Ȼ����ڵ�û���κ����ݣ�������null�����Ǿͱ�ʾ���������ĸָ��
	 //insert: ��������ж�children����ָ���Ƿ���ڣ�����ֱ�����һ��������������Ҫ����ָ��
	 //searchǰ׺����������ж��Ƿ���ڣ���������false;һֱ���ڣ����������Ǹ�ǰ׺�ڵ�
	 //�������ʣ�����Ҫǰ׺���ڣ������Ҫ��ĩβ��
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
	 
	 //226:��ת������
	 //������Ĺ��ڸ��ӣ��Գ������ж�����Ҫ�����������븨��������Ȼ���ж��������ҵȵ�
	 //���Ǳ��ⷭת��*****�����ǽ��������ӽڵ�ʱ�������Ľڵ�Ҳ����Ź�ȥ�ģ��ǳ���Ҫ�����൱�������������������彻���������ǵ����������ڵ㽻�������һ��Ҫע��
	 //Ȼ�������ټ�����ת�ӽڵ㼴�ɣ������뵥���Ľ���ֵ��̫�����ˡ�
	 public TreeNode invertTree(TreeNode root) {
		 if(root==null) return null;
		 TreeNode temp=root.left;             //���������ӽڵ�
		 root.left=root.right;
		 root.right=temp;
		 invertTree(root.left);               //�������ת
		 invertTree(root.right);
		 return root;
	    }
	 
	 //617���ϲ�����������
	 //���ģ���д�����򣺶�Ӧλ�õ�valֵ��ӣ�Ȼ��ݹ�����������return�ڵ㣻��ȥ������ֹ�����������������һ��Ϊ�գ��ǾͲ���ֱ�Ӽ�valֵ
	 //�����������Ϊ����Ҫ����Ϊ�յĽڵ��ֵ������ֱ�Ӿ��Ƿ��طǿսڵ㼴��
	 //*****д�ݹ��˼�룺�ȿ������ݹ������ôд��Ȼ����ȥд��ֹ��������ֹ��������������������д���ɣ���������������
	 public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
		 if(root1==null) return root2;
		 if(root2==null) return root1;
		 TreeNode node=new TreeNode(root1.val+root2.val);
		 node.left=mergeTrees(root1.left,root2.left);
		 node.right=mergeTrees(root1.right,root2.right);
		 return node;
	    }
	 
	 //572���ж��������������к����
	 //�ܼ򵥣����������������ж���rootΪ�����ܷ��sub��ȣ�������ȣ��Ǿͼ����ݹ�����������Ϊ������sub�Ƚϼ��ɡ������������ж��������������������������к�����Ƿ���ȼ���
	 public boolean isSubtree(TreeNode root, TreeNode subRoot) {
		 if(root==null) return false;                              //������и������������ǣ��������rootΪnull���������Ȼ��false;����ֱ�Ӽ�һ�������Ϳ�����
		 return isEqual(root,subRoot)||isSubtree(root.left,subRoot)||isSubtree(root.right,subRoot);//������Ϊ�գ��ݹ�ʱ���Զ���false;
	    }
	 
	 public boolean isEqual(TreeNode root,TreeNode subRoot) {
		 if(root==null&&subRoot==null) return true;
		 if(root==null&&subRoot!=null) return false;
		 if(root!=null&&subRoot==null) return false;
		 if(root.val!=subRoot.val) return false;
		 return isEqual(root.left,subRoot.left)&&isEqual(root.right,subRoot.right);
	 }
	 
	 //404����Ҷ�ӽڵ�֮��
	 //�ܼ򵥣�ȷ������Ҫ�������Ҷ�ӽڵ��������Ȼ��ݹ���һ�����ҽڵ㼴�ɣ����Һ���Ҫ�������롣
	 //*****sumʵ���ϴ���ǵ�����Ҫ�����ֵ�������ܺ�ֵ���ݹ���󣬲����ܺ�ֵ
	 public int sumOfLeftLeaves(TreeNode root) {
		 int sum=0;                             //*****�ǳ���Ҫ������sum�����ڲ���ÿ�ζ���Ҫ�ó�0��Ϊʲô����Ϊreturn��һ�������صģ���һ�����Ѿ���¼�����ˣ�����return���ǲ����ӣ�������һ��
		 if(root==null) return 0;               //�ĵݹ�ֻ��Ҫ��¼��ǰ�Ƿ���뼴�ɣ�����Ҫ�����ӣ�����0��ʼ
		 //*****�ݹ���ֹ���������ڿսڵ㣬���εĺ�ֵ��ֻ��Ҫ����0���ɣ�0����֮ǰ�Ľ��������ûӰ�죬����������뷵��0
		 if(root.left!=null&&root.left.left==null&&root.left.right==null) {
			 sum+=root.left.val;
		 }
		 return sum+sumOfLeftLeaves(root.left)+sumOfLeftLeaves(root.right);
	    }
	 
	 //513�����������½ǵ�ֵ
	 //���ģ����������������һ�����ȷ����ٷ��ң����һ�������½ǣ����ڸĳ����½ǣ������ȷ��ң��ٷ�������м��ɡ�
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
	 
	 //538:����������ת��Ϊ�ۼ���
	 //*****����������������Ϊһ��������������������Ϊ�������У�������Ϊ�ݼ����У�Ȼ����Ŀ�ȼ�Ϊ��δ���������м���
	 //���ģ��������ǵݼ����У��ȱ������ٱ���С������Ҫ����ǵ�ǰֵ�ʹ����������е�ֵ֮�ͣ�Ҳ����˵��Ҫά��һ��sumȷ����ǰ�������ĺ�ֵ����
	 int sum538=0;
	 public TreeNode convertBST(TreeNode root) {
		 if(root==null) return null;
		 convertBST(root.right);
		 sum538+=root.val;
		 root.val=sum538;
		 convertBST(root.left);
		 return root;
	    }
	 
	 //235:�����������������������
	 //���ģ����p,qλ��root���ˣ���ôroot�������ȣ�p,qλ��ͬ�࣬�Ǿ���Ҫô��root��Ҫô��root�Ҳ࣬ȷ���£�Ȼ��ݹ鼴��
	 //��ֹ���������ֱ�ӵ���p����q��˵��ʣ����һ���϶�������ĺ�����֣��Ǿ�ֱ�ӷ��ظ�ֵ����
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
	 
	 //530����������������С��
	 //���Ƕ�����������ֱ������Ϊһ����������������Ҳ�������������е���С��Ǿ������ڲ����Сֵ��
	 //��Ϊ��������������ú�һ��ֵ��ȥǰһ��ֵ����Ϊ��ֵ������¼��С�ļ���
	 int pre=-1;                              //*****ע���£��ʼ��һ������Ԫ�ز���Ҫ��pre��û�в�ֵ��������������pre��ֵΪ-1�����ڷֱ��Ƿ��ǿ�ͷ��
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
	 
	 //889:�������ԭ��
	 //��Ȼû�취Ψһȷ�������ǿ��Ը�ԭ��֮һ����
	 //���ģ����Լ���һ�������ԭ���̣�Ӧ���ǣ�Ѱ������ǰ�ڵ�ĺ�һ���ڵ��ں����е�λ�ã���Ϊ��һ���ڵ��Ȼ���ں�������һ���ڵ㣬�����ҵڶ����ڵ�ȷ�����������ָ
	 //�������������ĸ��ں����о��������������һ���ڵ�index,��ô�Ϳ��Ի������������ˡ�
	 //*****��Ϊʲô���Եݹ飿����Ϊ�����ó����������еĽڵ㣬���Եݹ�������ӽڵ��ǿ��Զ��µģ���Ϊ������Ǹ�-��-�ң��ó����������ĸ�����ԭ��root�������ӽڵ�
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
	 
	 //106���������ԭ��
	 //���ģ���ǰ����һ����Ѱ�ҽڵ�����һ�������е�λ�ã��ָ����������ݹ鼴��
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
	  
	  //94���������
	  List<Integer> res94=new LinkedList<>();
	  public List<Integer> inorderTraversal(TreeNode root) {
		  if(root==null) return res94;
		  inorderTraversal(root.left);
		  res94.add(root.val);
          inorderTraversal(root.right);
          return res94;
	    }
	  
	  //145:�������
	  List<Integer> res145=new LinkedList<>();
	  public List<Integer> postorderTraversal(TreeNode root) {
		  if(root==null) return res145;
		  postorderTraversal(root.left);
		  postorderTraversal(root.right);
		  res145.add(root.val);
		  return res145;
	    }
	  
	  //236:��ͨ�������������������
	  //������������ֵ��ϵ������ֱ����ֵ�Ƚ�������ȷ���ݹ�
	  //*****��ͨ����������������ȣ��������޵ݹ飬��null
	  //���ģ����p,q��root�����࣬��ô�ݹ�����϶�������p,q,��ʱ����������Ⱦ���root;���p,q��һ�࣬��ô��һ��������޵ݹ飬��Ȼ����null,
	  //��ʱֻ��Ҫ�����һ���ݹ鵽��p/q���������������ȡ�֮����ȥȷ����ֹ��������Ϊ�ȵݹ���жϣ������Ǻ��������
	  public TreeNode lowestCommonAncestor236(TreeNode root, TreeNode p, TreeNode q) {
		  if(root==null||root==p||root==q) return root;
		  TreeNode left=lowestCommonAncestor(root.left,p,q);         //*****���޵ݹ鷨��ʵ���Ͼ��ǲ�ͣ�ݹ飬���ض�ֵnull/p/q�ŷ��أ��ǳ��ļ�ֱ��
		  TreeNode right=lowestCommonAncestor(root.right,p,q);
		  if(left==null) return right;
		  if(right==null) return left;
		  return root;
	    }
	  
	  //109����������ת��Ϊƽ�����������
	  //���ģ������ܵ�ƽ��-----���м�ڵ���Ϊ���ڵ㣬��ô��������ڵ���Ŀ������ȣ�ÿ�ζ����м�ڵ㣬�ͻᾡ���ܵ�ƽ��
	  //���м�ڵ���Ϊ���ڵ㣬�����������е��м�ڵ�����Ϊ���������ӽڵ㣬���εݹ飬���ɵõ�����ƽ��Ķ���������
	  public class ListNode {
		       int val;
		       ListNode next;
		       ListNode() {}
		       ListNode(int val) { this.val = val; }
		       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
		   }
	  public TreeNode sortedListToBST(ListNode head) {
		  return buildBST(head,null);                    //*****�������������Ϊ�������࣬��Ҫ��ʼ��ĩβ������������������һ��ʼͷ������ͷ��ĩβ��null���������һ�����ĺ�һ��
	    }                                                //��Ϊ���һ��null,��Ӱ���м�ڵ㣬���ҿ����ú���Ĵ�����Ӽ򵥣�ֱ��������β������ҪO��n��,����������null�򵥶��ˣ�
	  
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
		  while(fast.next!=right&&fast.next.next!=right) {   //*****С�ĵ㣬����ĩβ����null,����right��while��ֹ����Ҫע����
			  fast=fast.next.next;
			  slow=slow.next;
		  }
		  return slow;
	  }
	  
	  //897��������ת��Ϊ����������
	  //*****��΢ע���£�ֱ����res�ᵼ�º���û�취���س�ʼ��ʼ�Ľڵ��ˣ�������Ҫһ������index��������������res����ָ��ͷ���ɣ����ڽ������
	  TreeNode res=new TreeNode(0);
	  TreeNode index=res;                             //�������ã���������
	  public TreeNode increasingBST(TreeNode root) {
		  if(root==null) return res.right;
		  increasingBST(root.left);
		  TreeNode t=new TreeNode(root.val);
		  index.right=t;
		  index=index.right;
		  increasingBST(root.right);
          return res.right;
	    }
	  
	  //653������������������֮��
	  //*****����̫�����ˣ�ʵ���Ϻ�������һ���ģ�����Ҫһ����ϣ�����洢Ԫ�أ�����Ԫ�أ�ֻ�������������������������������ѡ�
	  //����⣬���Ǻ�����һ�����ù�ϣ��洢��ֻ������Ҫ����Ԫ�ض��ѡ�������ֱ�������������������Ҫ�������򣬺���ѡһ�����ɡ�
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
	  
	  //450��ɾ�������������е�һ���ڵ�
	  //������������ɾ������Ҫ�����滻ԭ����
	  //���ֻ���������������������Ǿ�ֱ���滻
	  //������У��ö����������ĺ�̻���ǰ�����滻----�����̣������������е���Сֵ��Ҳ����������������˵Ľڵ����滻���ڵ��ֵ
	  //�滻�ڵ�ֵ����Ҫɾ���ýڵ㣨�����ظ�ɾ���Ĺ��̣�
	  //*****�����ݹ��ʵ�ֹ��̷ǳ����ӣ�
	  //1. û��������ɾ���ڵ�ʱ����ݹ�����ҵݹ���ң����ظ��ڵ�
	  //2. ���˴�ɾ����㣬��Ҫ��ʼ�жϹ��̣��ж������Ƿ�Ϊ��
	  //3. ���Ҷ��ǿգ��ҵ���Ҫ�����滻�Ľڵ㣬�ٴ�ִ�еݹ飬֮����Ҫ����ɾ������-----���ǽ��ýڵ������������Ҹ�������
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
			  t.right=root.right;                        //*****�����ɾ�����̺��滻����һ��ģ��������滻�Ľڵ�ֱ��ȥ�滻���ڵ㼴�ɡ�
			  t.left=root.left;
			  return t;
		  }
		  return root;
	  }
	  
	  
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
