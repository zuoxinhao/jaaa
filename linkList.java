package work;
import java.util.*;
import java.util.stream.Collectors;

import org.w3c.dom.Node;

import java.lang.*;
import java.lang.reflect.Array;

public class linkList {
	
	//*****�������Ŀ���ѣ�ֻҪע����������11���㣬������ͷǳ�easy
	
	
	//�����ܽ᣺
	//1. A.next�ڵ�ʽ�Ҳ࣬��ʾ����A����һ��Ԫ�أ���Ԫ��
	//2. A.next�ڵ�ʽ��࣬��ʾ����������A�ĺ�һ��Ԫ��
	//3. A=B����ʾ��B����Ԫ�ظ�ֵ����A����
	//4. *****(������Ҫ)��ͷ���Ĳ�������(��Ҫ���������)��������Ŀ�У�������һ���ڵ�new ListNode(0��,�����ӵ�head,��Ϊ��ͷ���Ľڵ�
	//���������Ӹýڵ㿪ʼ���Ϳ��Ա��ⵥ����Ϊ�ջ��ߵ�����ֻ��һ��Ԫ�ص����ۣ�
	//��Ϊ����ڵ��൱��head��ǰ�ýڵ㣬������������Ǳ���Ҫǰ�ýڵ���������ô��ô���պ�������������
	//*****��Щʱ���¼������ڵ㣬�᷽�������������,����11�ص���
	//*****(����Ҫ)�漰���ڵ�ֵ�Ƚϵ�����ͱ����ͷ�ڵ��ˣ���Ϊ���˽ڵ�ֵû�취��ӣ����ô���
	//5. *****���ü��ɣ�������Ŀ������head,���ǲ�����headȥ�����ڵ㣬��Ϊ������Ҫһ������һֱ�ڿ�ͷ�����ڽ�������
	//��������һ��ListNode iter=head;�����iterȥִ������ı�����
	//*****�����֮����һ�����𲻶�next��������һ������һֱ�ڿ�ͷ���ں��������ͷ���
	//6. �����ཻ��ָ��ͬһ�ڵ㣬A==B������������ֵ���
	//7. *****���ü��ɣ�����ָ�����м�ڵ㣻*****�ȷ�ת�����ٴ���
	//8. ��ת������Ϊ������û�취��β����ǰ�����������漰�ⷽ�����Ŀ����Ҫ��ת��������
	//9. *****�������������������1---2---3  ��1������3ʱ�򣬳��ֵ�����ǣ�1---3������1---2���ˣ�2---3��Ȼ���ڣ��ǳ���Ҫ��ƽ��Ҳ���ùܣ�������Ŀ��������������ʣ�
	//���ڶ���������������һ���ڵ�ֻ�����ӵ�һ���ڵ㣨������ֻ��һ����������һ���ڵ���Խ��ն������������յ�߿����ж����
	//10. *****��������ֻ���ù鲢�����ÿ���ָ�����е㣬Ȼ��ݹ�����(��������ݹ�ֻ��Ҫ��¼����ͷ������Ҫ��¼l,r,mid)
	//���ϲ����ϲ��õ��ǣ��½�һ��ֵΪ0��ͷres��Ȼ�󰤸�����С�ڵ㣬����������res.next����
	//11. *****�ص��⣺������ת���������¼��������תǰ���Ǹ��ڵ�temp���Դ�Ϊ��׼���з�ת��ע�⣺��ͷ�������ÿ������漸�����ܿ�������
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
	
	//206:��ת����
	 public ListNode reverseList(ListNode head) {
		 if(head==null||head.next==null) return head;        //*****ҪС�ĳ�ʼ����Ϊ�գ�����ֻ��һ���ڵ�����
		 ListNode pre=head;
		 ListNode cur=head.next;                             //*****��ֱ���˵��ģ�pre=null,cur=head,�ڳ�ʼ�ڵ�ǰ��һ���սڵ�ͳһ�£���Ϊ��㡣
		 while(cur!=null) {
			 ListNode temp=cur.next;
			 cur.next=pre;
			 pre=cur;
			 cur=temp; 
		 }
		 head.next=null;                                     
		 return pre;
	    }
	 
	 //21���ϲ���������
	 //*****����ĺϲ����������е㲻ͬ��������Ҫһ���µ�����ͷ�����ϲ��������Ӳ���ͳһ�ϲ��Ĺ���
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
		 if(list1==null) {                        //*****���д������Ȼlist1�����ˣ��Ǿ�˵��list2����Ҫȫ������node,ֱ��node.next=list2;
			 while(list2!=null) {                 //��Ϊlist2����������Ӻõģ����ԾͲ���Ҫ����������ˣ�ֱ��һ�伴��
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
	
	 //24��������������ڵ�
	 //�ǳ�����Ľⷨ��������������ǰ���Ǹ��ڵ㣬��ô������㶼����һ����׼���ͺܺ���
	 //���������ڵ㣬��һ����temp��ʾ��ͷ���ƣ��������ǰ��һ����㣩��node1,node2: temp-node1-node2-����
	 //������֮����ǣ�temp-node2-node1-����  ���ɣ�Ȼ��temp�����Ƶ�node1λ�þͿ���ȥ������һ����������
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
	 
	 //160���ཻ����
	 //�󳤶ȲȻ�󰤸������ж��Ƿ��ཻ
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
				 if(cur1==cur2) {                         //*****�����ཻ���պ�ͬһ���ڵ㣬��������ֵ��ȣ�������ȫָ��ͬһ���ڵ�Ŷ�
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
	 
	 //234������Ļ����ж�
	 //**ֱ�����巭ת���жϺ�ԭ�����Ƿ���ͬҲ���ԣ�������ҪO(n)���Ӷȡ��������ǿ�����ԭ�����Ϸ�תһ���ٱȽϼ��ɡ�
	 //�����ж���Ҫ��β�Ƚϣ�Ȼ���������м���չ�Ƚ�
	 //���ǵ������д�β����ǰ�ǲ����ܵģ������뵽��ת����
	 //���ÿ���ָ���ҵ��м�㣬Ȼ�󽫺�벿�ַ�ת�����Ƚϼ���
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
         while(c2!=null) {                                 //ע���£�����Ƚϵ���ֹ�����Ǻ�벿�ִ�����,��Ϊ�����ڵ�ʱ���м�ڵ���ǰ�봮
        	 if(c1.val!=c2.val) return false;
        	 c1=c1.next;
        	 c2=c2.next;
         }
         return true;
	    }
	 
	 //83��ɾ�������е��ظ�Ԫ��
	 //�ܼ򵥣���¼ǰ������Ԫ�ز��Ƚϼ���
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
	  
	  //328:��ż�ڵ�
	  //��������9���Ժ����׵�ʵ�֣�1---2---3---4���������ڵ�1---3�ȼ�����Ȼ��ͬʱ��ż���ڵ�����2---3�����ڣ�����ֱ�����2---4����������Ӱ��
	  //����ٽ�������ĩβ���ӵ�2����
	  public ListNode oddEvenList(ListNode head) {
		  if(head==null) return null;
		  ListNode odd=head;
		  ListNode temp=head.next;
		  ListNode even=head.next;
		  while(even!=null&&even.next!=null) {         //��ֹ�������Լ����Կ��ڲ����еķ�ʽ�����Ӧ����even�պ�������null,��Ҳ�պ����������һ��even���ӵ�null
			  odd.next=even.next;
			  odd=odd.next;
			  even.next=odd.next;
			  even=even.next;
		  }
		  odd.next=temp;
		  return head;
	    }
	  
	  //19��ɾ��������n���ڵ�
	  //ȡ�ɷ������ȷ�ת��ɾ���ٷ�ת
	  //�����ľͺ����ԵĿ���ָ�룺��һ������n-1�����ڶ�������
	  public ListNode removeNthFromEnd(ListNode head, int n) {
		  if(head.next==null&&n==1) return null;               //�����������һ��Ԫ��
		  ListNode fast=head;
		  ListNode slow=head;
		  ListNode pre=slow;
		  while(n-1!=0) {
			  n--;
			  fast=fast.next;
		  }
		  if(fast.next==null) return head.next;                //���������ֱ�ӵ�ĩβ�ˣ���ɾ��������len��Ԫ�أ�ɾͷ����
		  while(fast.next!=null) {
			  fast=fast.next;
			  pre=slow;
			  slow=slow.next;
		  }
		  pre.next=slow.next;
		  return head;
	    }
	  
	  //148����������
	  //�鲢���������е��֣�Ȼ��ݹ飬���ϲ�
	  public ListNode sortList(ListNode head) {
		  if(head==null||head.next==null) return head;       //*****�ݹ���ֹ�������ջ��߽���һ��Ԫ�أ�������ֹ�ݹ飬��ʼ�ϲ�����
		  ListNode slow=head;
		  ListNode fast=head;
		  while(fast.next!=null&&fast.next.next!=null) {
			  fast=fast.next.next;
			  slow=slow.next;
		  }
		  ListNode temp=slow.next;
		  slow.next=null;
		  ListNode left=sortList(head);                      //�ҵ��е㲢���εݹ�
		  ListNode right=sortList(temp);
		  ListNode start=new ListNode(0);
		  ListNode res=start;
		  while(left!=null&&right!=null) {                   //��ʼ�ϲ�
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
