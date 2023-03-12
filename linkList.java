package work;
import java.util.*;
import java.util.stream.Collectors;

import org.w3c.dom.Node;

import java.lang.*;
import java.lang.reflect.Array;

public class linkList {
	
	//*****链表的题目不难，只要注意好了下面的11个点，链表题就非常easy
	
	
	//链表总结：
	//1. A.next在等式右侧，表示的是A的下一个元素，是元素
	//2. A.next在等式左侧，表示加链，链接A的后一个元素
	//3. A=B，表示把B所在元素赋值给了A名称
	//4. *****(很少需要)加头结点的操作：在(需要遍历处理的)单链表题目中，先新增一个节点new ListNode(0）,并链接到head,作为最头部的节点
	//后续操作从该节点开始，就可以避免单链表为空或者单链表只有一个元素的讨论；
	//因为这个节点相当于head的前置节点，如果遍历中我们必须要前置节点来处理，那么这么做刚好满足了条件。
	//*****有些时候记录下这个节点，会方便后续遍历处理,比如11重点题
	//*****(很重要)涉及到节点值比较的问题就别添加头节点了，因为加了节点值没办法添加，不好处理
	//5. *****常用技巧：对于题目给定的head,我们不会用head去遍历节点，因为我们需要一个变量一直在开头，用于结果输出；
	//所以我们一般ListNode iter=head;用这个iter去执行链表的遍历。
	//*****简而言之就是一个负责不断next向后遍历，一个负责一直在开头用于后续结果从头输出
	//6. 链表相交：指向同一节点，A==B，而不仅仅是值相等
	//7. *****常用技巧：快慢指针求中间节点；*****先翻转链表再处理
	//8. 翻转链表：因为单链表没办法从尾部向前遍历，所以涉及这方面的题目都需要翻转链表来做
	//9. *****断链加链的内在情况：1---2---3  当1加链到3时候，出现的情况是：1---3新增，1---2断了，2---3依然存在（非常重要，平常也不用管，特殊题目可以利用这个性质）
	//对于断链加链，单链表一个节点只能链接到一个节点（出发边只能一个），但是一个节点可以接收多个到达的链（终点边可以有多个）
	//10. *****链表排序只能用归并：先用快慢指针找中点，然后递归左右(链表排序递归只需要记录链表头，不需要记录l,r,mid)
	//最后合并，合并用的是：新建一个值为0的头res，然后挨个加上小节点，最后输出的是res.next即可
	//11. *****重点题：两两翻转链表：巧妙：记录下两两翻转前的那个节点temp，以此为基准进行翻转。注意：开头两个不好看，后面几个才能看出规律
	//
	/*public ListNode swapPairs(ListNode head) {
		 ListNode res=new ListNode(0);
		 ListNode temp=res;
		 temp.next=head;
		 while(temp.next!=null&&temp.next.next!=null) {
			 ListNode node1=temp.next;
			 ListNode node2=temp.next.next;
			 temp.next=node2;
			 node1.next=node2.next;
			 node2.next=node1;
			 temp=node1;
		 }
		 return res.next;
	    }*/
	
	
	
	
	
	
	
	public class ListNode {
		      int val;
		      ListNode next;
		      ListNode() {}
		      ListNode(int val) { this.val = val; }
		      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
		  }
	
	//206:反转链表
	 public ListNode reverseList(ListNode head) {
		 if(head==null||head.next==null) return head;        //*****要小心初始链表为空，或者只有一个节点的情况
		 ListNode pre=head;
		 ListNode cur=head.next;                             //*****更直截了当的：pre=null,cur=head,在初始节点前加一个空节点统一下，更为简便。
		 while(cur!=null) {
			 ListNode temp=cur.next;
			 cur.next=pre;
			 pre=cur;
			 cur=temp; 
		 }
		 head.next=null;                                     
		 return pre;
	    }
	 
	 //21：合并增序链表
	 //*****链表的合并，和数组有点不同，就是需要一个新的链表头开启合并，这样子才能统一合并的过程
	 public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		 ListNode res=new ListNode(0);
		 ListNode node=res;
		 while(list1!=null&&list2!=null) {
			 if(list1.val<=list2.val) {
				 node.next=list1;
				 list1=list1.next;
			 }else {
				 node.next=list2;
				 list2=list2.next;
			 }
			 node=node.next;
		 }
		 if(list1==null) {                        //*****简便写法：既然list1结束了，那就说明list2的需要全部放入node,直接node.next=list2;
			 while(list2!=null) {                 //因为list2本身就是连接好的，所以就不需要再依次向后了，直接一句即可
				 node.next=list2;
				 list2=list2.next;
				 node=node.next;
			 }
		 }
		 if(list2==null) {
			 while(list1!=null) {
				 node.next=list1;
				 list1=list1.next;
				 node=node.next;
			 }
		 }
		 return res.next;
	    }
	
	 //24：两两交换链表节点
	 //非常巧妙的解法：保存两两交换前的那个节点，那么后面计算都有了一个基准，就很好算
	 //保存三个节点，第一个是temp表示开头名称（交换结点前的一个结点），node1,node2: temp-node1-node2-后续
	 //交换完之后就是：temp-node2-node1-后续  即可，然后将temp名称移到node1位置就可以去继续下一个两两交换
	 public ListNode swapPairs(ListNode head) {
		 ListNode res=new ListNode(0);
		 ListNode temp=res;
		 temp.next=head;
		 while(temp.next!=null&&temp.next.next!=null) {
			 ListNode node1=temp.next;
			 ListNode node2=temp.next.next;
			 temp.next=node2;
			 node1.next=node2.next;
			 node2.next=node1;
			 temp=node1;
		 }
		 return res.next;
	    }
	 
	 //160：相交链表
	 //求长度差，然后挨个向下判断是否相交
	 public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		 if(headA==null||headB==null) return null;
		 int len1=0;
		 int len2=0;
		 ListNode cur1=headA;
		 ListNode cur2=headB;
		 while(cur1!=null) {
			 len1++;
			 cur1=cur1.next;
		 }
		 while(cur2!=null) {
			 len2++;
			 cur2=cur2.next;
		 }
		 cur1=headA;
		 cur2=headB;
		 int k=len1-len2;
		 if(k>0) {
			 while(k!=0) {
				 k--;
				 cur1=cur1.next;
			 }
			 while(cur1!=null&&cur2!=null) {
				 if(cur1==cur2) {                         //*****链表相交：刚好同一个节点，不仅仅是值相等，而是完全指向同一个节点才对
					 return cur1;
				 }else {
					 cur1=cur1.next;
					 cur2=cur2.next;
				 }
			 }
		 }
		 if(k<0) {
			 while(k!=0) {
				 k++;
				 cur2=cur2.next;
			 }
			 while(cur1!=null&&cur2!=null) {
				 if(cur1==cur2) {
					 return cur1;
				 }else {
					 cur1=cur1.next;
					 cur2=cur2.next;
				 }
			 }
			 
		 }
	     return null;  
	    }
	 
	 //234：链表的回文判断
	 //**直接整体翻转，判断和原链表是否相同也可以，但是需要O(n)复杂度。所以我们考虑在原链表上翻转一半再比较即可。
	 //回文判断需要首尾比较，然后依次向中间扩展比较
	 //但是单链表中从尾部向前是不可能的，所以想到翻转链表
	 //先用快慢指针找到中间点，然后将后半部分翻转，最后比较即可
	 public boolean isPalindrome(ListNode head) {
		 if(head==null||head.next==null) return true;
		 ListNode slow=head;
		 ListNode fast=head;
		 while(fast.next!=null&&fast.next.next!=null) {
			 slow=slow.next;
			 fast=fast.next.next;	
		 }
		 slow.next=reverseList(slow.next);
         ListNode c1=head;
         ListNode c2=slow.next;
         while(c2!=null) {                                 //注意下：这里比较的终止条件是后半部分串结束,因为奇数节点时候，中间节点在前半串
        	 if(c1.val!=c2.val) return false;
        	 c1=c1.next;
        	 c2=c2.next;
         }
         return true;
	    }
	 
	 //83：删除链表中的重复元素
	 //很简单，记录前后两个元素并比较即可
	  public ListNode deleteDuplicates(ListNode head) {
		  if(head==null) return null;
		  ListNode pre=head;
		  ListNode cur=head.next;
		  while(cur!=null) {
			  if(cur.val==pre.val) {
				  ListNode temp=cur.next;
				  pre.next=cur.next;
				  cur=temp;
			  }else {
				  pre=cur;
				  cur=cur.next;
			  }
		  }
		  return head;  
	    }
	  
	  //328:奇偶节点
	  //利用性质9可以很容易的实现，1---2---3---4对于奇数节点1---3先加链，然后同时对偶数节点利用2---3还存在，可以直接添加2---4，这样互不影响
	  //最后再将奇数链末尾链接到2即可
	  public ListNode oddEvenList(ListNode head) {
		  if(head==null) return null;
		  ListNode odd=head;
		  ListNode temp=head.next;
		  ListNode even=head.next;
		  while(even!=null&&even.next!=null) {         //终止条件：自己试试看内部运行的方式，最后应该是even刚好落在了null,链也刚好消除，最后一个even链接到null
			  odd.next=even.next;
			  odd=odd.next;
			  even.next=odd.next;
			  even=even.next;
		  }
		  odd.next=temp;
		  return head;
	    }
	  
	  //19：删除倒数第n个节点
	  //取巧方法：先翻转再删除再翻转
	  //正常的就很明显的快慢指针：第一个先走n-1步，第二个再走
	  public ListNode removeNthFromEnd(ListNode head, int n) {
		  if(head.next==null&&n==1) return null;               //特殊情况，仅一个元素
		  ListNode fast=head;
		  ListNode slow=head;
		  ListNode pre=slow;
		  while(n-1!=0) {
			  n--;
			  fast=fast.next;
		  }
		  if(fast.next==null) return head.next;                //特殊情况，直接到末尾了，即删除倒数第len个元素（删头部）
		  while(fast.next!=null) {
			  fast=fast.next;
			  pre=slow;
			  slow=slow.next;
		  }
		  pre.next=slow.next;
		  return head;
	    }
	  
	  //148：链表排序
	  //归并排序：先找中点拆分，然后递归，最后合并
	  public ListNode sortList(ListNode head) {
		  if(head==null||head.next==null) return head;       //*****递归终止条件：空或者仅有一个元素，即可终止递归，开始合并过程
		  ListNode slow=head;
		  ListNode fast=head;
		  while(fast.next!=null&&fast.next.next!=null) {
			  fast=fast.next.next;
			  slow=slow.next;
		  }
		  ListNode temp=slow.next;
		  slow.next=null;
		  ListNode left=sortList(head);                      //找到中点并分治递归
		  ListNode right=sortList(temp);
		  ListNode start=new ListNode(0);
		  ListNode res=start;
		  while(left!=null&&right!=null) {                   //开始合并
			  if(left.val<=right.val) {
				  start.next=left;
				  left=left.next;
			  }else {
				  start.next=right;
				  right=right.next;
			  }
			  start=start.next;
		  }
		  start.next=left==null?right:left;
		  return res.next;
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
