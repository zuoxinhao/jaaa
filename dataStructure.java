package work;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;
import java.lang.reflect.Array;

public class dataStructure {
	
	//���ݽṹ�ܽ᣺
	//���飬ջ�����У���ϣ��ǰ׺�ͣ���Ŀһ��Ƚ����û�й̶�˼·����Ҫ���Ӧ�䡣
	//1. ��ת���飺��෨�ҹ��ɣ�ע��ѭ������
	//2. ��ά����ļ�����צ�ֲ��ҷ����ֳ������֣�һ���ִ�һ����С�������Ϳ��Բ��������������Ͻǿ�ʼ����
	//3. ���ָ�����������Ĺ��ɣ���ǰ���������Ԫ��ֵ����Ŀǰ�±�ֵ���ֵʱ�򣬾Ϳ��Խ���һ�λ��֡�
	//4. *****�ص��⣺˫ջʵ�ֶ���
	//�и���Ҫע��㣺ѹ��ʱ��ֱ��ѹ��ջ1������ʱ������ջ2(ԭ˳��)������ջ2û���˲���Ҫ��ջ1��ȫ����ѹ���ٵ���
	//5. ��Сջ����һ������ջ�浱ǰ����Ԫ�ص���Сֵ���ɣ�Ȼ�󵯳�ʱ�����������==��Сջջ����Ҳ����(ѹ�������Сֵʱ�����⿼����ѹ�ڶ���)
	//6. *****����ջ����ÿ���¶�739��ά��ջ�ڵĵ���/�ݼ���������벻����ֱ�Ӽ��㡣O(n)ʱ��ʵ��Ѱ����һ������/��С��Ԫ��
	//7. ˫�˶�����ʵ����˫��LinkedList,��offerLast/First��������һ��˫�˶�������ά���������У����磺�����������ֵ
	//8. *****ͬ�ߵ����⣺��бʽ���ȶ�һ���㣬��ȥ��б�ʼ���
	//����ÿһ������һ��hashset����������б�ʲ���¼������ÿ��ȡmax�������ȡmax����
	//9. ŷ��·�����⣺���ù�ϣ�����ʼ�ڵ㣬Ȼ��Ե�һ���ڵ㿪ʼDFS���ҵ�����ͬ�ڵ�(���һ���ڵ㣬��Ϊ1)��Ȼ��ɾ���ýڵ㣻
	//����Ѱ�Ҷ�Ϊ1...������ݷ�������ģ��д�����Լ�һ�����while����
	/* public void dfs(String cur) {                                //������ȣ�Ѱ�ҵ�����ͬ�㣬Ȼ���������
     	while(map.containsKey(cur)&&map.get(cur).size()>0) {
     		String tmp=map.get(cur).poll();                      //���о����Ƚ��ȳ�������ǰ�濪ʼѰ������ͬ�㼴��
     		dfs(tmp);
     	}
     	res.add(cur);
     } */
	//10. ǰ׺�ͣ�ֵ�������£������������͵����---ʹ��ǰ׺�ͼ�����
	//11. ��ά����ת������ά---һά---��ά��x1=i*n1+j  i=x2/n2  j=x2%n2
	//12. *****�ص��⣺��������ʵ��ջ������ջʵ�ֶ����ص�����pop��������������ʵ��ջ�ص�����push����
	//���ľ���push����Ҫ�Ѿɵļ������2���µķ������1��Ȼ��Ѷ���2�ļ������1
    //�����·����Ԫ�أ���Ҫ����ȳ������Ա�����ڶ���1ͷ�����С����������ȰѶ���1��ԭ�ȵ�Ԫ�ط������2��Ȼ�����Ԫ�ط��ڶ���1
    //*****Ȼ���ٽ�����2�������1���ɡ���������Ԫ��ʼ�ջ������ǰ�档�൱�ھ�Ԫ�ض��μ������1
	//13. ��С������һ���������ӣ���ô�ø���������������������ӣ�����������������
	
	
	//*****����ջ�������ľ���ά��һ���������ߵݼ���ջ
	//��Ҫ��;��Ѱ����һ��������߸�С��Ԫ��
	//���磺�����ݼ�ջ��O(n)Ѱ����һ����֮���Ԫ��
	//�����Ԫ�ش���ջ����ջ����������¼
	//С��ջ����ֱ�ӷ���
	//��������ʵ�ּ�¼��ÿһ��Ԫ�غ����һ����֮���Ԫ��λ��/��С...
	
	//*****˫�˶��У�һ�����ڻ�������,ͨ��������ά�ֵ���/�ݼ�˳��
	//LinkedList:offerLast/First,pollLast/First
	
	
	//�����⣺
	//����Щ�ҹ��ɣ��ǳ��������Ŀ��
	
	//*****java�ж�̬���飬ջ�����е�ʵ�ּ������ϼ���
	//1. ���飺ArrayList,LinkedList,������.add,.get
	//2. ջ�����֣�Stack,LinkedList,������Ӧ���ǣ�push--addFirst() pop--removeFirst() peek--peekFirst()
	//ջһ�㶼�õ�һ��Stack
	//3. ���У�һ����LinkedList,������offer()β��,poll()ͷ��,peek(),Ҳ������offerLast(),pollFirst(),peekFirst()
	//����һ�㶼��add��remove
	
	//*****���ȶ��У����ľ����Դ����򣨵�����     �����ã��Դ����򣬿��Է���int[],node֮�࣬�ܷ���
	//O��1��ʱ�������ֵ/��Сֵ��O��logk�����룬O��n��ɾ��
	//PriorityQueue:add,remove
	
	
	
	//*****��ϣ��
	//HashMap:get(key),put(key,value),containsKey()...
	//��ϣ��Ĳ��룬ɾ�������Ҷ���O��1��
	//��ϣ���޸�valueֵ����put(key,hashmap.getOrDefault(key,default)...)
	//������ϣ����Ԫ�ط�������for(Map.Entry< , > entry: hashmap.entrySet){ entry.getKey  entry.getValue  }
	//����ֻ����key: hash.keySet()
	
	
	
	
	
	
	//48��˳ʱ����ת����������
	//*****��ת�ҹ�������Ҫ�ķ�������෨��ͨ�����۲죬�ǳ���ֱ��
	//���ѵ����ҹ��ɣ������5*5�����ң�3��4�Ĺ��ɲ�����
	//*****�ҹ�����ô�ң�����Ҫ�ķ������ǿ���࣬���͵�0����n��/�еļ�࣬������ת�������Ԫ�غ͵�0��n��/�еļ��
	//����5*5�ģ�a[i][j]-----a[j][n-i]-----a[n-i][n-j]-----a[n-j][i]
	//**С��ѭ���Ĵ��������i��ʾ������������һ��Ȧ��������չ��������0---n/2
	//jӦ���Ǵ�i---n-i,����n�Ѿ����±���
	public void rotate(int[][] matrix) {
		int n=matrix.length-1;
		int temp=0;
		for(int i=0;i<=n/2;i++) {
			for(int j=i;j<n-i;j++) {
				temp=matrix[j][n-i];
				matrix[j][n-i]=matrix[i][j];
				matrix[i][j]=matrix[n-j][i];
				matrix[n-j][i]=matrix[n-i][n-j];
				matrix[n-i][n-j]=temp;
			}
		}
    }
	
	//240�������ά���������
	//צ�ֲ��ҷ���ѡȡ�ض�λ�õ�Ԫ�أ�ʹ�ñ�֮С����һ���֣���֮�����һ���֣����͸��Ӷȣ�ÿ�ζ��ı�һ����/�У��ǵĸ��Ӷȴ�󽵵�
	//�����Ͻǿ�ʼ���ң�������ҵ�����֮С�������ң���֮���������ҡ������Ļ����Ӷ�ΪO��m+n����ÿ���ƶ�һ��/һ�У����Եõ��ø��Ӷȣ�
	 public boolean searchMatrix(int[][] matrix, int target) {
		 int n=matrix[0].length-1;
		 boolean res=false;
		 int i=0;
		 int j=n;
		 while(i<matrix.length&&j>=0) {
			 if(matrix[i][j]==target) {
				 res=true;
				 break;
			 }else if(matrix[i][j]>target) {
				 j--;
			 }else {
				 i++;
			 }
		 }
		 return res;   
	    }
	 
	 //769�����ָ�����������
	 //����ؽⷨ��������࣬Ҳ����ζӦ�����γ�һ��������ʱ���Ҫ�γɡ����ɣ���ǰ���������Ԫ��ֵ����Ŀǰ�±�ֵ���ֵʱ�򣬾Ϳ��Խ���һ�λ��֡�
	 //��Ϊ�ź����ʱ��Ԫ��ֵ���±�Ӧ���Ǹպö�Ӧ�ĵġ�0---0��1---1��2---2...
	 //���Ե����Ԫ��ֵ=����±�ʱ��Ҳ����ζ�����Ŀ��������Ӧ�ã�ͬʱ��ΪԪ�ظ���=�±����������С��Ҳ��ǡ�ö�Ӧ��
	 //�����ǰ���Ԫ��ֵ>����±�ֵ���Ǿ���Ҫ�������;�����ܳ���С�ڵ��������Ϊ0-n�������������������ֵ�����ܱ��±껹С
	 public int maxChunksToSorted(int[] arr) {
		 int res=0;
		 int n=arr.length;
		 int max=0;
		 for(int i=0;i<n;i++) {
			 max=Math.max(max, arr[i]);
			 if(max==i) {
				 res++;
			 }
		 }
		 return res;
	    }
	 
	 //232:����ջʵ�ֶ���
	 //Ψһ��Ҫע��ĵ㣺ջ2���ȷ������Ҫ�ȳ�(ջ2�Ѿ��ָ���ԭ˳������ջ2��Ҫ�ȳ���)����ջ1������ѹ��ʱ��ֻ�е�ջ2���ʱ��ջ1���ܼ�����ջ2����Ȼ�ᵼ�º���ȳ�������
	   Stack<Integer> stack1=new Stack<>();
	   Stack<Integer> stack2=new Stack<>();    
	    public void push232(int x) {
	    	stack1.push(x);
	    }
	    
	    public int pop232() {
	    	if(stack2.isEmpty()) {
	    		while(!stack1.isEmpty()) {
	    			int temp=stack1.pop();
	    			stack2.push(temp);
	    		}
	    	}
	    	return stack2.pop();
	    }
	    
	    public int peek232() {
	    	if(stack2.isEmpty()) {
	    		while(!stack1.isEmpty()) {
	    			int temp=stack1.pop();
	    			stack2.push(temp);
	    		}
	    	}
	    	return stack2.peek();
	    }
	    
	    public boolean empty232() {
	    	return stack1.isEmpty()&&stack2.isEmpty();
	    }
	    
	    //155����Сջ
	    //�ø���ջ�����ÿһ������Сֵ
	    //Ҫ���ص�����С��Ԫ�أ������min����¼ÿ��push����Сֵ���������һpop�ˣ���Сֵû���Ҳ���ǰһ���ˡ�
	    //����Ҫ�ø���ջ������ջ���һ�����������Сֵ��ÿ�θ���ʱ���µ���Сֵ��ֱ��ѹ�뼴�ɣ���ôǰһ����Сֵ�ᱣ����ջ�ڣ�
	    //������pop������Сֵ����ô֮ǰ����Сֵ����minջ�е���һ��Ԫ�أ�ֱ��˫pop,������������
	    Stack<Integer> stack155=new Stack<>();
	    Stack<Integer> minStack=new Stack<>();
	    public void push(int val) {
	    	if(minStack.isEmpty()||val<=minStack.peek()) {
	    		minStack.push(val);
	    	}
	    	stack155.push(val);
	    }
	    
	    public void pop() {
	    	if(!minStack.isEmpty()&&stack155.peek().equals(minStack.peek())) {      //*****ע�⣺��ΪStack�õ��ǰ�װ�࣬����-128-127��Χ�ڣ�����ֱ����==��������equals�����ж����
	    		minStack.pop();                                                     //stack155.peek()==minStack.peek()  ����
	    	}
	    	stack155.pop();
	    }
	    
	    public int top() {
	        return stack155.peek();

	    }
	    
	    public int getMin() {
	    	return minStack.peek();
	    }
	    
	    //20����Ч����
	    //�ܼ򵥣�������ѹ��ջ�������źͶ���ƥ�䣬�������ƥ�����ջ��һֱƥ�䵽���ջ�Ƿ�Ϊ�ռ���
	    public boolean isValid(String s) {
	    	char[] c=s.toCharArray();
	    	int i=0;
	    	Stack<Character> stack=new Stack<>();
	    	while(i<c.length) {
	    		if(c[i]=='('||c[i]=='{'||c[i]=='[') {
	    			stack.push(c[i]);
	    		}
	    		if(c[i]==')') {
	    			if(!stack.isEmpty()&&stack.peek()=='(') {
	    				stack.pop();
	    			}else {
	    				return false;
	    			}
	    		}
	    		if(c[i]==']') {
	    			if(!stack.isEmpty()&&stack.peek()=='[') {
	    				stack.pop();
	    			}else {
	    				return false;
	    			}
	    		}
	    		if(c[i]=='}') {
	    			if(!stack.isEmpty()&&stack.peek()=='{') {
	    				stack.pop();
	    			}else {
	    				return false;
	    			}
	    		}
	    		i++;
	    	}
	    	if(stack.isEmpty()) {
	    		return true;
	    	}else {
	    		return false;
	    	}    	
	    }
	    
	    //739:ÿ���¶�
	    //*****����ջ����ջ��ֻά�ֵ���/�ݼ���˳�򣬲�ƥ���ֱ�ӵ��������ַ����ͽ�������ջ��.���ַ������Ժܺõ�ʵ�ְ�����벢�ұȽ�ջ�������������Ƚ�ջ��...
	    //�ô���ÿ��Ԫ�ض�Ҫ�ͺ���Ԫ�رȽϣ�����Ҫ�����һ�δ�/С��Ԫ��λ�ã�
	    //���⣺ѹ�������±꣬��������ֱ�Ӽ�������
	    public int[] dailyTemperatures(int[] temperatures) {
	    	Stack<Integer> stack=new Stack<>();
	    	int[] ans=new int[temperatures.length];
	    	for(int i=0;i<temperatures.length;i++) {
	    		while(!stack.isEmpty()&&temperatures[i]>temperatures[stack.peek()]) {
	    			int temp=stack.pop();                        //*****ÿ�μ���������¶�>ջ���¶�ʱ����Ҫ��ջ����������¼ans��Ȼ������Ƚ�ջ�����������Ҫ����pop��������peek
	    			ans[temp]=i-temp;
	    		}
	    		stack.push(i);
	    	}
	    	return ans;
	    }
	    
	    //23:(hard,��⼴��)�ϲ���������
	    //�����ģ���ǰ�õ������ϲ����������������ȼ����нⷨ�������Ǳ�ɺ�����ϲ�һ���ģ�����������Ŀ�ͷ��min���������ȼ������У�Ȼ��ÿ��ȡ��min����Ϊ���ȼ����п���ֱ�����򣩣�����ϲ���������м���
	    //����������ȼ����У�Ϊʲô���ܺ�����һ���ϲ��أ���ΪҪȡ����ͷ����ڵ㣬������Ҫ���������������ǲ��ò����ġ�
	    public class ListNode {
	    	      int val;
	    	      ListNode next;
	    	      ListNode() {}
	    	      ListNode(int val) { this.val = val; }
	    	      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	    	  }
	    class Status implements Comparable<Status>{                          //��ֵ�ͽڵ��װ��Ŀ���ǣ����ں���ֱ��ȡ���ڵ㣬�������
	    	int val;
	    	ListNode ptr;
	    	Status(int val,ListNode ptr){
	    		this.val=val;
	    		this.ptr=ptr;
	    	}
	    	public int compareTo(Status status2) {
	    		return this.val-status2.val;
	    	}
	    }
	    
	    PriorityQueue<Status> queue=new PriorityQueue<Status>();             //���ȼ����У���Ҫ��дcompareTo������Ĭ��������ɵ����ֵ
	    public ListNode mergeKLists(ListNode[] lists) {
	    	for(ListNode node:lists) {
	    		if(node!=null) {
	    			queue.offer(new Status(node.val,node));
	    		}
	    	}
	    	ListNode head=new ListNode(0);
	    	ListNode tail=head;
	    	while(!queue.isEmpty()) {
	    		Status f=queue.poll();
	    		tail.next=f.ptr;
	    		tail=tail.next;
	    		if(f.ptr.next!=null) {
	    			queue.offer(new Status(f.ptr.next.val,f.ptr.next));
	    		}
	    	}
	    	return head.next;
	    }
	    
	    //218����hard,��⣩����������
	    //�����Ŀ�ǳ�������⣺��Ҫ���ָ���νⷨ����¼�ĵ��ǡ����¡������£����£���
	    //�Ƚ�����bs�е����Ҷ˵����ַ��������У�Ȼ������֮��ʼ�������
	    //�������˵㣺����Ҫ���߶ȼ������ȶ��У������ʱ�߶�>֮ǰ����߸߶ȣ������ˡ����¡�����ô����Ҫ��¼�˶˵�
	    //������Ҷ˵㣺������һ���߶ȵı߽����ˣ���Ҫ�ڶ������Ƴ����߶ȣ�Ȼ����ڴ�ʱ�Ķ����е����ֵ����������֮ǰ�����ֵ�����˱仯��˵���˴������ˡ����¡�
	    //��ô���Ҷ˵����Ҫ��¼������û�з������¡�����Ҫ��¼
	    //���������ȶ��е�ԭ�����ȶ��п���ʵ��O��1��ȡ���ֵpeek;O(logn)��ӣ�O��n��ɾ��
	    //�������еĶ˵�ָ��һ�������Σ�Ȼ����Ҫ��¼���Ǿ����ϱߵ���˵㣬����������һ��������������ı�
	    public List<List<Integer>> getSkyline(int[][] bs){
	    	List<List<Integer>> ans=new ArrayList<>();
	    	List<int[]> ps=new ArrayList<>();
	    	for(int[] b:bs) {                      //���ַ���
	    		int l=b[0];
	    		int r=b[1];
	    		int h=b[2];
	    		ps.add(new int[] {l,-h});
	    		ps.add(new int[] {r,h});
	    	}
	    	Collections.sort(ps,(a,b)->{
	    		if(a[0]!=b[0]) return a[0]-b[0];   //���˵��С����
	    		return a[1]-b[1];                  //������ͬ�����ո߶���������˵�����
	    	});
	    	PriorityQueue<Integer> q=new PriorityQueue<>((a,b)->b-a);
	    	int prev=0;
	    	q.add(prev);
	    	for(int[] p:ps) {
	    		int point=p[0];
	    		int height=p[1];
	    		if(height<0) {
	    			q.add(-height);                //��˵����߶ȣ��Ҷ˵�����߶�
	    		}else {
	    			q.remove(height);
	    		}
	    		int cur=q.peek();                  //����ʱ����߸߶���֮ǰ���Ƿ����仯
	    		if(cur!=prev) {                    //����/���£��߶ȱ�Ȼ�仯���仯���¼
	    			List<Integer> list=new ArrayList<>();
	    			list.add(point);
	    			list.add(cur);
	    			ans.add(list);
	    			prev=cur;
	    		}
	    	}
	    	return ans;
	    }
	   
	    //239����hard����⣩�������ڵ����ֵ
	    //����˫�˶��У�java�о���LinkedList��:������ά��һ���ݼ�˳��
	    //���ڻ����¼ӵ�Ԫ�أ������β��Ԫ�������ǰ��Ĵ󣬣���ΪҪ�����ֵ���Ǿ���ζ��С�Ŀ϶��ᱻ�������ˣ����԰�С��ֱ�ӵ���ȥ
	    //���ڻ���ȥ����Ԫ�أ�����������ֵ��ɾ���ǾͲ�Ӱ�죻���ɾ����ǡ�������ֵ���ڶ��ף���ô����Ҫ�Ѷ��׵������������ֵ�����µĶ��׼���
	    public int[] maxSlidingWindow(int[] nums,int k) {
	    	if(nums==null||nums.length<2) return nums;
	    	LinkedList<Integer> queue=new LinkedList();                      //java�е�˫�˶�����ʵ����˫������
	    	int[] result=new int[nums.length-k+1];
	    	for(int i=0;i<nums.length;i++) {
	    		while(!queue.isEmpty()&&nums[queue.peekLast()]<=nums[i]) {   //�����Ԫ�أ������ǰ��Ĵ��Ǿ͵���ǰ���Ԫ��
	    			queue.pollLast();
	    		}
	    		queue.addLast(i);
	    		if(queue.peek()<=i-k) {                                      //�ж�ɾ�����Ƿ��ǿ�ͷ
	    			queue.poll();
	    		}
	    		if(i+1>=k) {                                                 //ֻ�б��������±�ﵽ���ڳ���ʱ���ſ�ʼ��¼���
	    			result[i+1-k]=nums[queue.peek()];
	    		}
	    	}	    	
	    	return result;
	    }
	    
	    //1������֮��
	    public int[] twoSum(int[] nums, int target) {
	    	HashMap<Integer,Integer> hashmap=new HashMap<>();
	    	for(int i=0;i<nums.length;i++) {
	    		if(hashmap.containsKey(target-nums[i])) {
	    			return new int[] {hashmap.get(target-nums[i]),i};
	    		}
	    		hashmap.put(nums[i], i);
	    	}
	    	return new int[0];
	    }
	    
	    //128����hard,ԭ��easy�����������
	    //��һ�ֽⷨ������ʱ��72����70����˼·�ܼ򵥣��������������ϣ���У�Ȼ��ӹ�ϣ������ȡһ��Ԫ�أ�Ȼ��ɾ����������ɾ�����Ľ��ڵ�������+1��-1
	    //Ȼ��������£�+2��-2...ֱ��û�н��ڣ�����ɾ���ˣ���¼���ȡ�Ȼ�����ѡȡһ��Ԫ�أ�ɾ���������Ľ���...
	    public int longestConsecutive1(int[] nums) {
	    	HashSet<Integer> hash=new HashSet<>();
	    	for(int i:nums) {
	    		hash.add(i);
	    	}
	    	int ans=0;
	    	while(!hash.isEmpty()) {
	    		int k=hash.iterator().next();      //��ϣ��.iterator().next()��ʾȡ��ϣ���еĵ�һ��Ԫ��
	    		hash.remove(k);
	    		int next=k+1;
	    		int prev=k-1;
	    		while(hash.contains(next)) {       //ɾ�����ڵ�Ԫ�أ�һֱɾ��
	    			hash.remove(next++);
	    		}
	    		while(hash.contains(prev)) {
	    			hash.remove(prev--);
	    		}
	    		ans=Math.max(ans, next-prev-1);
	    	}
	    	return ans;
	    }
	    //�����ⷨ��ʵ���Ӷ�Ӧ����һ���ģ�����O��n����������һ����ֱ������ѡȡȻ�������ڼ��ɣ�һ����ֻѡȡ��С��Ȼ��++��չ��
	    
	    //�ڶ��ֽⷨ�������Ӷ�Ҳ��O��n����,����ֻ��Ҫ�������ӣ�+1�������ܸ��Ӷ���΢��һ�㣻Ҳ�����ϸ��O��n��
	    //�ȷ����ϣ���У�����������ÿ��Ԫ�أ�����С��x��ʼѡȡ����x-1�ǲ����ڱ��еģ�Ȼ������+1���󳤶ȣ���¼����
	    public int longestConsecutive(int[] nums) {
	    	HashSet<Integer> hash=new HashSet<>();
	    	for(int i:nums) {
	    		hash.add(i);
	    	}
	    	int ans=0;
	    	for(int num:nums) {
	    		if(!hash.contains(num-1)) {
	    			int current=num;
	    			int len=1;
	    			while(hash.contains(current+1)) {
	    				len++;
	    				current++;
	    			}
	    			ans=Math.max(ans, len);
	    		}	
	    	}
	    	return ans;
	    }
	    
	    //149����hard,ԭ��easy��ͬһֱ���ϵĵ���������
	    //�õ��ǵ�бʽ��ȷ����һ���㣬��ôͬһб�ʵ������㶼����ͬһֱ����
	    //����ÿ���㣬����һ����ϣ�����洢�������ĵ��б�ʣ����б��ͬ����ʾ��ͬһֱ���ϣ�value++���ɣ����ѡȡ���ֵ
	    //���⴦��Ҫע��б�ʲ����ڵ����
	    public int maxPoints(int[][] points) {
	    	int len=points.length;
	    	int max=0;
	    	for(int i=0;i<len;i++) {
	    		HashMap<Double,Integer> hashmap=new HashMap<>();
	    		int unexist=0;
	    		for(int j=0;j<len;j++) {
	    			if(points[i][0]==points[j][0]) {
	    				unexist++;                                          //б�ʲ����ڵ�ֱ�ߣ�������¼
	    			}else {
	    				int dy=points[j][1]-points[i][1];
	    				int dx=points[j][0]-points[i][0];
	    				double k=(double)dy/(double)dx;                     //ע�⣺int/int���õ��Ŀ϶����������ᵼ�½��������������������Ҫ������int��ǿתΪdouble����
	    				hashmap.put(k, hashmap.getOrDefault(k, 1)+1);       //��ϣ����valueֵ���£���put�����Ҫ���ó�ֵ����getOrDefault.
	    			}                                                       //ע����������Ϊ1��ֵ����Ϊ���㱾�εĵ�Ҳ�ڸ�б����
	    		}
	    		max=Math.max(max, unexist);
	    		for(Map.Entry<Double, Integer> entry:hashmap.entrySet()) {  //ÿ�ζ�������ϣ���õ����ֵ����
	    			int value=entry.getValue();
	    			max=Math.max(max, value);
	    		}
	    	}
	    	return max;
	    }
	    
	    //332����hard,��⼴�ɣ���Ʊ·������
	    //����Ȿ������ŷ��·�����⣺��ĳ�����㿪ʼ���������б�һ���ҽ���һ�Σ��ܹ����������ж����·��
	    //�����Ϻܼ򵥣���ϣ�����ֹ�ڵ�+����ŵ�������ͬ��+ջ�������
	    //�ⷨ��DFS����Ϊ���һ������������ͬ����Ϊ1�����ٴ�����һ��dst�������������ҵ��������ͬ��ȥ��������ͬ�㣬��һ����Ȼ����������ͬ��...
	    //����ͬ����������������У���Ȼ�ͻ���ǰ��ֹ�������ҵ�����ͬ�����Ҫ����ջ���Ƚ��������������reverse��
        Map<String,PriorityQueue<String>> map=new HashMap<String,PriorityQueue<String>>();  
        List<String> res=new LinkedList<String>();
        public List<String> findItinerary(List<List<String>> tickets){
        	for(List<String> ticket: tickets) {
        		String src=ticket.get(0);
        		String dst=ticket.get(1);
        		if(!map.containsKey(src)) {                          //HashMap������ǿ�ʼ�ص㣬ֵ�ǵ����Ķ��м��ϣ����ֵ����źã�
        			map.put(src, new PriorityQueue<String>());
        		}
        		map.get(src).offer(dst);
        	}
        	dfs("JFK");                                             
        	Collections.reverse(res);                                //����ת��������ͬ��ŵ�������
        	return res;
        }
        
        public void dfs(String cur) {                                //������ȣ�Ѱ�ҵ�����ͬ�㣬Ȼ���������
        	while(map.containsKey(cur)&&map.get(cur).size()>0) {
        		String tmp=map.get(cur).poll();                      //���о����Ƚ��ȳ�������ǰ�濪ʼѰ������ͬ�㼴��
        		dfs(tmp);
        	}
        	res.add(cur);
        }
        
        //303�����������
        //��i-j�±귶Χ�ڵ�����Ԫ�غͣ�ǰ׺�͡�ͨ��ǰ׺�͵�Ԥ����ʹ��ֱ�Ӽ���ʱ������O��1��ʵ��
        int[] sum303;
        public void NumArray(int[] nums) {
        	int n=nums.length;
        	sum303=new int[n+1];
        	for(int i=1;i<=n;i++) {
        		sum303[i]=sum303[i-1]+nums[i-1];
        	}
        }
        
        public int sumRange(int left, int right) {
               return sum303[right+1]-sum303[left];
        }
        
        //304����άǰ׺��
        //���ε�Ԫ�غͣ���ʵ����ǰ׺�͵Ķ�ά��չ�����ֽл���ͼ��ͨ����[0][0]�ľ���Ԫ�غͣ�S=��-��1-��2+С ���ɵõ���
        //*****�����ͼ����ͨ��S=��1+��2-��+����Ԫ��   ����⣬ͨ����ʽ����ֱ�ӡ����Ӷ�ΪO��n^2��,������ù�ʽ�������⣬O��n^4��,���Ӷȹ���
        int[][] res304;
        public void NumMatrix(int[][] matrix) {
        	int m=matrix.length;
        	int n=matrix[0].length;
        	res304=new int[m+1][n+1];
        	for(int i=0;i<m;i++) {
        		for(int j=0;j<n;j++) {
        			res304[i+1][j+1]=res304[i][j+1]+res304[i+1][j]-res304[i][j]+matrix[i][j];   //*****res304[i+1][j+1]��Ӧ������matrix[i][j]Ϊĩ�˵�ľ���Ԫ�غ�
        		}                                                                               //*****��ȥ��i=0,j=0���������������Ԫ�غ͵�����
        	}                                                                                   //*****�����ͼ����ͨ����1+��2-��+����Ԫ������⣬����ֱ��

        }
        
        public int sumRegion(int row1, int col1, int row2, int col2) {
        	return res304[row2+1][col2+1]-res304[row2+1][col1]-res304[row1][col2+1]+res304[row1][col1];
        }
        
        //560��������������������
        //����˼·��ǰ׺��+˫��ѭ����O��n^2��
        //�Ż���ǰ׺��+��ϣ��ֱ�Ӳ���
        //��ϣ������ǰ׺�͵�ֵ��ֵ�ǳ��ֵĴ���
        //����������ͣ�k= sum560[j]-sum560[i-1]  �����˺���ǰ׺��-ǰ��ǰ׺��
	    //sum560[i-1]=sum560[j]-k    Ҳ����˵��������j��ǰ׺��ʱ��ֻ��Ҫ����i<j��sum560[i-1]�������ض�ֵ��ǰ׺�ͳ��ֵĴ�������
        //*****�ǳ�����߼���߸��£�����ϣ��洢��jʱ�򣬹�ϣ������Ķ���i<j��ǰ׺�ͣ����Կ���ֱ��ȡ�á�
        public int subarraySum(int[] nums, int k) {
        	HashMap<Integer,Integer> hash=new HashMap<>();
        	int sum=0;
        	int count=0;
        	hash.put(0, 1);                                       //*****ǧ�������ˣ����i=0.ֱ��j��ǰ׺��Ҳ�����������������Ա���Ҫ��һ����0Ԫ�ص�ǰ׺�ͳ��ִ���Ϊ1
        	for(int num:nums) {
        		sum+=num;
        		if(hash.containsKey(sum-k)) {
        			count+=hash.get(sum-k);
        		}
        		hash.put(sum, hash.getOrDefault(sum, 0)+1);
        	}
        	return count;
        }
        
        //566����������
        //��ά����-----һά����-----��ά����
        //����Ҫ����ռ��������ֱ��ͨ����άһά����任�����,��ͨ�ù����򻯸��Ӷ�
        //*****ͨ�ù�ʽ���Ӷ�ά��һά��x=i*n1+j��Ԫ����һά�ռ����������x��ӳ�䵽m�У�n�еĶ�ά�����е����꣺i=x/n2,j=x%n2
        public int[][] matrixReshape(int[][] mat, int r, int c) {
        	int m=mat.length;
        	int n=mat[0].length;
        	if(m*n!=r*c) {
        		return mat;
        	}
        	int[][] res=new int[r][c];
        	for(int x=0;x<m*n;x++) {
        		res[x/c][x%c]=mat[x/n][x%n];
        	}
            return res;
        }
        
        //225:��������ʵ��ջ
        //����ջʵ��һ�����кܼ򵥣����ξ������ɡ�
        //��������ʵ��һ��ջ�е�����ģ����ľ��ǰ���Ԫ��һ��Ҫ���ڶ���
        //�����·����Ԫ�أ���Ҫ����ȳ������Ա�����ڶ���1ͷ�����С����������ȰѶ���1��ԭ�ȵ�Ԫ�ط������2��Ȼ�����Ԫ�ط��ڶ���1
        //Ȼ���ٽ�����2�������1���ɡ���������Ԫ��ʼ�ջ������ǰ�档
        class MyStack {
            Queue<Integer> queue1=new LinkedList<>();
            Queue<Integer> queue2=new LinkedList<>();
            public MyStack() {

            }
            
            public void push(int x) {
            	while(!queue1.isEmpty()) {
            		queue2.offer(queue1.poll());
            	}
            	queue1.offer(x);
            	while(!queue2.isEmpty()) {
            		queue1.offer(queue2.poll());
            	} 
            }
            
            public int pop() {
            	return queue1.poll();
            }
            
            public int top() {
                return queue1.peek();
            }
            
            public boolean empty() {
                return queue1.isEmpty();
            }
        }
        
        //503:��һ�������Ԫ��
        //�����739ÿ���¶�����ͬһ���������O��n��ʱ�� Ѱ����������һ�������Ԫ������λ��
        //����ջ����ջ��ֻά�ֵݼ�˳��ֻҪ�Ǵ��ڵĵ�һ�γ��ֵģ�������м�¼��
        //ѭ������Ľ���취���������γ��ȣ�������ֵ���ջ�в���Ԫ��û�취������һ�α����У�����û�б�֮���Ԫ�أ������Ա���������2*n-2.numsԪ��ȡֵ����Ҫ%n����
        public int[] nextGreaterElements(int[] nums) {
        	Stack<Integer> stack=new Stack<>();                                //ջ�з��±꣬ʵ�ʱȽ���Ԫ��ֵ
	    	int[] ans=new int[nums.length];
	    	Arrays.fill(ans, -1);
	    	int n=nums.length;
	    	for(int i=0;i<n*2-1;i++) {
	    		while(!stack.isEmpty()&&nums[i%n]>nums[stack.peek()]) {        //����ջ������������¼
	    			int temp=stack.pop();                        
	    			ans[temp]=nums[i%n];
	    		}
	    		stack.push(i%n);                                               //ջ��û�д��ڵ��ˣ���ֱ�ӷ���
	    	}
	    	return ans;
        }
        
        //217:�ظ�Ԫ��
        public boolean containsDuplicate(int[] nums) {
        	HashSet<Integer> hash=new HashSet<>();
        	for(int i=0;i<nums.length;i++) {
        		if(hash.contains(nums[i])) {
        			return true;
        		}
        		hash.add(nums[i]);
        	}
        	return false;
        }
        
        //697������Ķ�
        //*****��ϣ��ļ�ֵ���Է����飬ʹ���������Ӽ�෽��,�����ù�ϣ���ֵ���洢����ǳ�ʡ��
        //��ֱ�ӽⷨ����α��������ù�ϣ��洢Ԫ�ؼ����ִ�������¼�������ִ�����Ȼ�������ϣ���ҵ���������Ӧ��Ԫ���ǣ��������������Ĳ�ֹһ��Ԫ�أ���
        //Ȼ����ȥ�����������Ƕ�Ӧ����ʼλ�ã�ĩλ�ã������С������ɡ�
        //���Ӽ��Ľⷨ�����������٣��ù�ϣ��洢Ԫ�أ��Լ�Ԫ�ض�Ӧ�Ĵ�������ʼλ�ã�ĩλ�ã����ǵ���Ԫ�أ�ֵ��һ�����飩��Ȼ�������ϣ��ȡ�������ģ����Ҵ������ʱ��ȡ���min
        public int findShortestSubArray(int[] nums) {
        	int n=nums.length;
        	HashMap<Integer,int[]> hash=new HashMap<>();
        	for(int i=0;i<n;i++) {
        		if(hash.containsKey(nums[i])) {
        			hash.get(nums[i])[0]++;                        //���ﲻ��Ҫ��put,ֱ�ӽ������޸ģ���Ϊ��������Զ���¼ֵ
        			hash.get(nums[i])[2]=i;
        		}else {
        			hash.put(nums[i], new int[] {1,i,i});
        		}
        	}
        	int maxCount=0;
        	int res=0;
        	for(Map.Entry<Integer, int[]> entry:hash.entrySet()) {
        		int[] arr=entry.getValue();
        		if(arr[0]>maxCount) {
        			maxCount=arr[0];
        			res=arr[2]-arr[1]+1;
        		}else if(arr[0]==maxCount) {
        			res=Math.min(res, arr[2]-arr[1]+1);
        		}
        	}
        	return res;
        }
        
        //594�����г������
        //1. ����+�������ڣ�O��nlogn�������ź����Ǿ�ֻ��Ҫ��x,x+1�Ĵ�����Ԫ�ظ������ɣ�begin,end,�����ֵ����1����begin���ƣ���ֵС�ڵ���1�����¼��end���ơ�
	    //2. ����ù�ϣ��ֱ����ȡ��O��n������¼ÿ��Ԫ�ؼ�����ֵĴ�������������Ҫ�ľ���ͳ��x��x+1�Ĵ����͡�
        //Ϊʲô����Ҫx,x-1?��Ϊ���Ƕ�ÿ��Ԫ�ض���x��x+1�ĺͣ�ʵ���ϾͰ����˿��ܳ��ֵ�x,x-1�������Ҳ�ͻ�����x-1,x�Ĵ����͡�����ֻͳ��+1���ɡ�
        public int findLHS(int[] nums) {
        	int res=0;
        	HashMap<Integer,Integer> hash=new HashMap<>();
        	for(int num:nums) {
        		hash.put(num, hash.getOrDefault(num, 0)+1);
        	}
        	for(int key:hash.keySet()) {
        		if(hash.containsKey(key+1)) {
        			res=Math.max(res, hash.get(key)+hash.get(key+1));
        		}
        	}
        	return res;
        }
        
        //287��Ѱ���ظ�����
        //����������ֻ����O��1���ռ䣬���ԱȽ����뵽,�ǳ������ת����������
        //ת������������
        //�����������Լ�����Ϥ������ָ��floyd��Ȧ��Ȼ��һ����ͷ��ʼһ��ԭ�ؿ�ʼ����һ����������
        //���ģ�ͨ���±��ӦԪ��ֵ��Ȼ��Ԫ��ֵ��Ϊ�µ��±꣬����ȡ��һ��Ԫ��ֵ�����磺0---1   ��һ������nums[1]=4  ��һ��nums[4]=0 Ȧ��
        //��Ϊ���ظ����֣������ͻ��γɻ�·��ת��Ϊ�˻�������
        //����һ��Ԫ�أ�.next-------nums[slow]   .next.next------nums[nums[fast]]
        public int findDuplicate(int[] nums) {
        	int slow=0;
        	int fast=0;
        	do {
        		slow=nums[slow];
        		fast=nums[nums[fast]];
        	}while(fast!=slow);
        	slow=0;
        	while(fast!=slow) {
        		slow=nums[slow];
        		fast=nums[fast];
        	}
        	return slow;
        }
        
        //313����������
        //�������DP��⣬��ĳ����ѣ�������
        //��ݽⷨ�����ȶ���+��ϣ��
        //���ģ���������ĺ��ľ��ǣ�һ����С�������ӣ���ô�����������г�ʼ�����ĳ˻���ӣ�Ȼ��������Сֵ����...
        //��ϣ��˼·�ǶԵģ����ǻᳬʱ�����Ա������˺�����Ľⷨ�ж��ظ���
        //if(x%prime==0��break;           ������Ϊʲô��������
        public int nthSuperUglyNumber(int n, int[] primes) {
        	PriorityQueue<Integer> queue=new PriorityQueue<>();
        	queue.add(1);
        	HashSet<Integer> hash=new HashSet<>();
        	hash.add(1);
        	while(n-->0) {
        		int x=queue.poll();
        		if(n==0) return x;
        		for(int prime:primes) {
        			if(x*prime<Integer.MAX_VALUE&&!hash.contains(x*prime)) {
        				queue.add(x*prime);
        				hash.add(x*prime);
        				//if(x%prime==0��break;                      ��׼����������ȥ�أ�������û����Ϊʲô������ʵ�֣�����
        			}
        		}
        	}
        	return -1;
        }
        
        //870������ϴ��
        //˼·����ȷ��̰�ģ���������������Ȼ���nums2�д���С�Ŀ�ʼ����nums1�б�֮�����С���Ǹ�Ԫ�ؼ���
        //�ؼ�����Ҫ��������ؽ����nums1,���ԱȽ��鷳����Ҫ����+��¼�±�----���������ȶ����д���������nums2��ֵ���±꣬Ȼ������ȡ����nums1������ıȽ�
        public int[] advantageCount(int[] nums1, int[] nums2) {
        	int n=nums2.length;
        	PriorityQueue<int[]> q=new PriorityQueue<>((o1,o2)->o1[0]-o2[0]);     //*****���ȶ�����д�Ƚ�������������ĵ�һ��Ԫ�ؽ�����������
        	for(int i=0;i<n;i++) {
        		q.add(new int[] {nums2[i],i});
        	}
        	Arrays.sort(nums1); 
        	int[] res=new int[n];
        	Arrays.fill(res, -1);                                                 //��������ֵ������-1�������Ӻ����Ӧλ��û�иı��Ԫ�ؾ�һֱ��-1
        	int begin=0;
        	List<Integer> list=new ArrayList<>();                                 //��һ���б������û���õ�nums1Ԫ�أ�û����ָ���Ƕ�Ӧλ�ñ�nums2��Ԫ��ֵС�����²���Ҫֱ�ӷ����Ӧλ�ã�
        	while(!q.isEmpty()) {                                                 
        		if(begin==n-1) break;                                             //�ȽϽ���������
        		if(q.peek()[0]<nums1[begin]) {                                    //���nums2��λ��Ԫ��С��nums1��Ԫ�أ���nums1�����Ӧλ�õĽ������                
        			res[q.peek()[1]]=nums1[begin];
        			begin++;
        			q.poll();
        		}else {
        			list.add(nums1[begin]);                                       
        			begin++;	
        		}     		
        	}
        	int j=0;
        	for(int i=0;i<n;i++) {                                                 //��û�õ���nums1��Ԫ�أ�ֱ�����β���������
        		if(res[i]==-1) {
        			res[i]=list.get(j++);
        		}
        	}
        	return res;
        }
        
        //307��ǰ׺�͵����ݽṹ��ʾ
        //������ǰ׺�ͣ����Ӷȳ��ˣ�������ܵ���ʵ��ǰ׺�͵�һ�����ݽṹ----��״����----������
        //��״�����˵���ѣ���ʱ�����ɣ����ţ��Ժ�����������ϸ��⣨���٣�����������⣩
        //*****��ʱ����⣬ֱ�Ӽ���ģ��
        class NumArray{
        	//��״�����д��ģ�壬��������
        	int[] tree;                                          //��״����ģ��
        	int lowbit(int x) {
        		return x&-x;
        	}
        	
        	int query(int x) {                                   //ǰ׺��ģ��
        		int ans=0;
        		for(int i=x;i>0;i-=lowbit(i)) ans+=tree[i];
        		return ans;
        	}
        	
        	void add(int x,int u) {                              //����ֵģ��
        		for(int i=x;i<=n;i+=lowbit(i)) tree[i]+=u;
        	}
        	
        	
        	int[] nums;
        	int n;
        	public NumArray(int[] _nums) {
        		nums=_nums;
        		n=nums.length;
        		tree=new int[n+1];
        		
        		//��״�����ʼ��ģ��
        		for(int i=0;i<n;i++) add(i+1,nums[i]);
        	}
        	
        	public void update(int i,int val) {                 //��������ֵ
        		add(i+1,val-nums[i]);
        		nums[i]=val;
        	}
        	
        	public int sumRange(int l,int r) {
        		return query(r+1)-query(l);
        	}
        	
        }
        
     
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
