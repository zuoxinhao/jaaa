package work;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;
import java.lang.reflect.Array;

public class dataStructure {
	
	//数据结构总结：
	//数组，栈，队列，哈希表，前缀和，题目一般比较巧妙，没有固定思路，需要随机应变。
	//1. 旋转数组：间距法找规律，注意循环层数
	//2. 二维数组的检索：爪字查找法，分成两部分，一部分大一部分小，这样就可以不断搜索，从右上角开始查找
	//3. 最大分割子数组个数的规律：当前数组中最大元素值等于目前下标值最大值时候，就可以进行一次划分。
	//4. *****重点题：双栈实现队列
	//有个重要注意点：压入时候直接压入栈1，出的时候，优先栈2(原顺序)弹出，栈2没有了才需要将栈1“全部”压入再弹出
	//5. 最小栈：用一个辅助栈存当前所有元素的最小值即可，然后弹出时候如果弹出的==最小栈栈顶则也弹出(压入相等最小值时候特殊考虑下压第二次)
	//6. *****单调栈法：每日温度739：维持栈内的递增/递减，满足加入不满足直接计算。O(n)时间实现寻找下一个更大/更小的元素
	//7. 双端队列其实就是双向LinkedList,用offerLast/First来操作，一般双端队列用于维护单调队列，比如：滑动窗口最大值
	//8. *****同线点问题：点斜式：先定一个点，再去定斜率即可
	//对于每一个点用一个hashset存和其他点的斜率并记录次数，每次取max，最后再取max即可
	//9. 欧拉路径问题：利用哈希表存起始节点，然后对第一个节点开始DFS，找到死胡同节点(最后一个节点，度为1)，然后删除该节点；
	//继续寻找度为1...这个回溯法不好用模板写，所以记一下这个while代码
	/* public void dfs(String cur) {                                //深度优先，寻找到死胡同点，然后加入链表
     	while(map.containsKey(cur)&&map.get(cur).size()>0) {
     		String tmp=map.get(cur).poll();                      //队列就是先进先出，从最前面开始寻找死胡同点即可
     		dfs(tmp);
     	}
     	res.add(cur);
     } */
	//10. 前缀和：值得利用下，求解数组区间和的情况---使用前缀和简化运算
	//11. 二维数组转化：二维---一维---二维：x1=i*n1+j  i=x2/n2  j=x2%n2
	//12. *****重点题：两个队列实现栈。两个栈实现队列重点在于pop操作，两个队列实现栈重点在于push操作
	//核心就是push操作要把旧的加入队列2，新的放入队列1，然后把队列2的加入队列1
    //对于新放入的元素，需要后进先出，所以必须放在队列1头部才行。所以我们先把队列1中原先的元素放入队列2，然后把新元素放于队列1
    //*****然后再将队列2加入队列1即可。这样子新元素始终会放在最前面。相当于旧元素二次加入队列1
	//13. 最小丑数：一个丑数出队，那么用该数乘以其他丑数即可入队，这样无限扩大数组
	
	
	//*****单调栈法：核心就是维护一个递增或者递减的栈
	//主要用途：寻找下一个更大或者更小的元素
	//比如：单调递减栈：O(n)寻找下一个比之大的元素
	//如果新元素大于栈顶：栈顶弹出并记录
	//小于栈顶，直接放入
	//这样就能实现记录了每一个元素后面第一个比之大的元素位置/大小...
	
	//*****双端队列：一般用于滑动窗口,通过弹出来维持递增/递减顺序
	//LinkedList:offerLast/First,pollLast/First
	
	
	//数组题：
	//都是些找规律，非常巧妙的题目。
	
	//*****java中动态数组，栈，队列的实现及方法合集：
	//1. 数组：ArrayList,LinkedList,可以用.add,.get
	//2. 栈：两种，Stack,LinkedList,方法对应的是：push--addFirst() pop--removeFirst() peek--peekFirst()
	//栈一般都用第一种Stack
	//3. 队列：一般用LinkedList,方法：offer()尾插,poll()头出,peek(),也可以用offerLast(),pollFirst(),peekFirst()
	//队列一般都用add和remove
	
	//*****优先队列：核心就是自带排序（递增）     很有用，自带排序，可以放入int[],node之类，很方便
	//O（1）时间找最大值/最小值，O（logk）插入，O（n）删除
	//PriorityQueue:add,remove
	
	
	
	//*****哈希表：
	//HashMap:get(key),put(key,value),containsKey()...
	//哈希表的插入，删除，查找都是O（1）
	//哈希表修改value值，用put(key,hashmap.getOrDefault(key,default)...)
	//遍历哈希表中元素方法二：for(Map.Entry< , > entry: hashmap.entrySet){ entry.getKey  entry.getValue  }
	//可以只遍历key: hash.keySet()
	
	
	
	
	
	
	//48：顺时针旋转正方形数组
	//*****旋转找规律最重要的方法：间距法。通过间距观察，非常简单直接
	//最难的是找规律：最好用5*5的来找，3，4的规律不明显
	//*****找规律怎么找：最重要的方法就是看间距，看和第0，第n行/列的间距，经过旋转变成了新元素和第0，n行/列的间距
	//比如5*5的，a[i][j]-----a[j][n-i]-----a[n-i][n-j]-----a[n-j][i]
	//**小心循环的次数：外层i表示层数，从外面一个圈层向内拓展，所以是0---n/2
	//j应该是从i---n-i,这里n已经是下标了
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
	
	//240：有序二维矩阵的搜索
	//爪字查找法：选取特定位置的元素，使得比之小的搜一部分，比之大的搜一部分，降低复杂度，每次都改变一次行/列，是的复杂度大大降低
	//从右上角开始查找，相等则找到，比之小则向左找，比之大则向下找。这样的话复杂度为O（m+n）（每次移动一行/一列，所以得到该复杂度）
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
	 
	 //769：最大分割子数组数量
	 //巧妙地解法：数量最多，也就意味应该能形成一个子数组时候就要形成。规律：当前数组中最大元素值等于目前下标值最大值时候，就可以进行一次划分。
	 //因为排好序的时候，元素值和下标应该是刚好对应的的。0---0，1---1，2---2...
	 //所以当最大元素值=最大下标时，也就意味着最大的可以排序对应好，同时因为元素个数=下标个数，所以小的也能恰好对应；
	 //如果当前最大元素值>最大下标值，那就需要继续向后;不可能出现小于的情况，因为0-n的数，所以数组中最大值不可能比下标还小
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
	 
	 //232:两个栈实现队列
	 //唯一需要注意的点：栈2是先放入的需要先出(栈2已经恢复了原顺序，所以栈2的要先出完)，（栈1后面再压入时）只有当栈2清空时，栈1才能继续入栈2，不然会导致后进先出，出错。
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
	    
	    //155：最小栈
	    //用辅助栈来存放每一步的最小值
	    //要返回的是最小的元素，如果用min来记录每次push的最小值，会出现万一pop了，最小值没了找不到前一个了。
	    //所以要用辅助栈：辅助栈存放一步步存入的最小值，每次更新时（新的最小值）直接压入即可，那么前一个最小值会保存在栈内；
	    //当我们pop到了最小值，那么之前的最小值就是min栈中的下一个元素，直接双pop,继续满足条件
	    Stack<Integer> stack155=new Stack<>();
	    Stack<Integer> minStack=new Stack<>();
	    public void push(int val) {
	    	if(minStack.isEmpty()||val<=minStack.peek()) {
	    		minStack.push(val);
	    	}
	    	stack155.push(val);
	    }
	    
	    public void pop() {
	    	if(!minStack.isEmpty()&&stack155.peek().equals(minStack.peek())) {      //*****注意：因为Stack用的是包装类，超出-128-127范围内，不能直接用==，必须用equals方法判断相等
	    		minStack.pop();                                                     //stack155.peek()==minStack.peek()  错误
	    	}
	    	stack155.pop();
	    }
	    
	    public int top() {
	        return stack155.peek();

	    }
	    
	    public int getMin() {
	    	return minStack.peek();
	    }
	    
	    //20：有效括号
	    //很简单，左括号压入栈，右括号和顶部匹配，如果可以匹配则出栈；一直匹配到最后看栈是否为空即可
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
	    
	    //739:每日温度
	    //*****单调栈法：栈中只维持递增/递减的顺序，不匹配的直接弹出，这种方法就叫做单调栈法.这种方法可以很好的实现按序放入并且比较栈顶，弹出继续比较栈顶...
	    //用处：每个元素都要和后面元素比较，且需要求出第一次大/小的元素位置；
	    //本题：压入日期下标，这样就能直接计算天数
	    public int[] dailyTemperatures(int[] temperatures) {
	    	Stack<Integer> stack=new Stack<>();
	    	int[] ans=new int[temperatures.length];
	    	for(int i=0;i<temperatures.length;i++) {
	    		while(!stack.isEmpty()&&temperatures[i]>temperatures[stack.peek()]) {
	    			int temp=stack.pop();                        //*****每次即将放入的温度>栈顶温度时，需要将栈顶弹出并记录ans，然后继续比较栈顶。这里必须要弹出pop，而不是peek
	    			ans[temp]=i-temp;
	    		}
	    		stack.push(i);
	    	}
	    	return ans;
	    }
	    
	    //23:(hard,理解即可)合并增序链表
	    //做过的（以前用的两两合并法），这里用优先级队列解法：（就是变成和数组合并一样的）把所有链表的开头（min）放入优先级队列中，然后每次取出min（因为优先级队列可以直接排序），放入合并后的链表中即可
	    //如果不用优先级队列，为什么不能和数组一样合并呢？因为要取出开头链表节点，而且需要排序，排序用链表是不好操作的。
	    public class ListNode {
	    	      int val;
	    	      ListNode next;
	    	      ListNode() {}
	    	      ListNode(int val) { this.val = val; }
	    	      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	    	  }
	    class Status implements Comparable<Status>{                          //将值和节点封装的目的是：便于后面直接取出节点，加入答案中
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
	    
	    PriorityQueue<Status> queue=new PriorityQueue<Status>();             //优先级队列，需要重写compareTo函数，默认是升序可得最大值
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
	    
	    //218：（hard,理解）轮廓线问题
	    //这道题目非常难以理解：主要看分割矩形解法：记录的点是“上坡”“下坡（滑坡）”
	    //先将所有bs中的左右端点区分放入数组中，然后排序，之后开始分类分析
	    //如果是左端点：首先要将高度加入优先队列，如果此时高度>之前的最高高度，发生了“上坡”，那么就需要记录此端点
	    //如果是右端点：代表着一个高度的边结束了，先要在队列中移除本高度，然后对于此时的队列中的最大值，如果相比于之前的最大值发生了变化。说明此处发生了“滑坡”
	    //那么该右端点就需要记录；否则没有发生滑坡。不需要记录
	    //本题用优先队列的原因：优先队列可以实现O（1）取最大值peek;O(logn)添加，O（n）删除
	    //根据所有的端点分割成一个个矩形，然后需要记录的是矩形上边的左端点，且跳过由上一个矩形延伸而来的边
	    public List<List<Integer>> getSkyline(int[][] bs){
	    	List<List<Integer>> ans=new ArrayList<>();
	    	List<int[]> ps=new ArrayList<>();
	    	for(int[] b:bs) {                      //区分分类
	    		int l=b[0];
	    		int r=b[1];
	    		int h=b[2];
	    		ps.add(new int[] {l,-h});
	    		ps.add(new int[] {r,h});
	    	}
	    	Collections.sort(ps,(a,b)->{
	    		if(a[0]!=b[0]) return a[0]-b[0];   //按端点大小排序
	    		return a[1]-b[1];                  //横坐标同，按照高度排序，且左端点优先
	    	});
	    	PriorityQueue<Integer> q=new PriorityQueue<>((a,b)->b-a);
	    	int prev=0;
	    	q.add(prev);
	    	for(int[] p:ps) {
	    		int point=p[0];
	    		int height=p[1];
	    		if(height<0) {
	    			q.add(-height);                //左端点加入高度，右端点结束高度
	    		}else {
	    			q.remove(height);
	    		}
	    		int cur=q.peek();                  //看此时的最高高度与之前的是否发生变化
	    		if(cur!=prev) {                    //上坡/下坡，高度必然变化，变化则记录
	    			List<Integer> list=new ArrayList<>();
	    			list.add(point);
	    			list.add(cur);
	    			ans.add(list);
	    			prev=cur;
	    		}
	    	}
	    	return ans;
	    }
	   
	    //239：（hard，理解）滑动窗口的最大值
	    //利用双端队列（java中就是LinkedList）:队列中维持一个递减顺序
	    //窗口滑动新加的元素：加入队尾的元素如果比前面的大，（因为要求最大值）那就意味着小的肯定会被先舍弃了，所以把小于直接弹出去
	    //窗口滑动去掉的元素：如果不是最大值被删，那就不影响；如果删除的恰好是最大值，在队首，那么就需要把队首弹出，本轮最大值就是新的队首即可
	    public int[] maxSlidingWindow(int[] nums,int k) {
	    	if(nums==null||nums.length<2) return nums;
	    	LinkedList<Integer> queue=new LinkedList();                      //java中的双端队列其实就是双向链表
	    	int[] result=new int[nums.length-k+1];
	    	for(int i=0;i<nums.length;i++) {
	    		while(!queue.isEmpty()&&nums[queue.peekLast()]<=nums[i]) {   //加入的元素，如果比前面的大，那就弹出前面的元素
	    			queue.pollLast();
	    		}
	    		queue.addLast(i);
	    		if(queue.peek()<=i-k) {                                      //判断删除的是否是开头
	    			queue.poll();
	    		}
	    		if(i+1>=k) {                                                 //只有遍历到的下标达到窗口长度时，才开始记录结果
	    			result[i+1-k]=nums[queue.peek()];
	    		}
	    	}	    	
	    	return result;
	    }
	    
	    //1：两数之和
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
	    
	    //128：（hard,原理easy）最长连续序列
	    //第一种解法：（超时，72过了70个）思路很简单：将所有数放入哈希表中，然后从哈希表中先取一个元素，然后删除它，并且删除他的紧邻的两个，+1，-1
	    //然后继续向下，+2，-2...直至没有紧邻，不能删除了，记录长度。然后继续选取一个元素，删除他和他的紧邻...
	    public int longestConsecutive1(int[] nums) {
	    	HashSet<Integer> hash=new HashSet<>();
	    	for(int i:nums) {
	    		hash.add(i);
	    	}
	    	int ans=0;
	    	while(!hash.isEmpty()) {
	    		int k=hash.iterator().next();      //哈希表.iterator().next()表示取哈希表中的第一个元素
	    		hash.remove(k);
	    		int next=k+1;
	    		int prev=k-1;
	    		while(hash.contains(next)) {       //删除紧邻的元素，一直删除
	    			hash.remove(next++);
	    		}
	    		while(hash.contains(prev)) {
	    			hash.remove(prev--);
	    		}
	    		ans=Math.max(ans, next-prev-1);
	    	}
	    	return ans;
	    }
	    //两个解法其实复杂度应该是一样的，都是O（n），本质上一个是直接任意选取然后找相邻即可；一个是只选取最小的然后++扩展。
	    
	    //第二种解法：（复杂度也是O（n））,但是只需要单向的添加（+1），可能复杂度稍微好一点；也不是严格的O（n）
	    //先放入哈希表中，对于数组中每个元素，从最小的x开始选取，即x-1是不存在表中的，然后依次+1扩大长度，记录即可
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
	    
	    //149：（hard,原理easy）同一直线上的点数最大个数
	    //用的是点斜式：确定好一个点，那么同一斜率的其他点都会在同一直线上
	    //对于每个点，都用一个哈希表来存储和其他的点的斜率，如果斜率同，表示在同一直线上，value++即可，最后选取最大值
	    //特殊处理，要注意斜率不存在的情况
	    public int maxPoints(int[][] points) {
	    	int len=points.length;
	    	int max=0;
	    	for(int i=0;i<len;i++) {
	    		HashMap<Double,Integer> hashmap=new HashMap<>();
	    		int unexist=0;
	    		for(int j=0;j<len;j++) {
	    			if(points[i][0]==points[j][0]) {
	    				unexist++;                                          //斜率不存在的直线，单独记录
	    			}else {
	    				int dy=points[j][1]-points[i][1];
	    				int dx=points[j][0]-points[i][0];
	    				double k=(double)dy/(double)dx;                     //注意：int/int最后得到的肯定是整数，会导致结果出错，所以我们这里需要把两个int都强转为double计算
	    				hashmap.put(k, hashmap.getOrDefault(k, 1)+1);       //哈希表中value值更新，用put，如果要设置初值，用getOrDefault.
	    			}                                                       //注意这里设置为1初值：因为计算本次的点也在该斜率中
	    		}
	    		max=Math.max(max, unexist);
	    		for(Map.Entry<Double, Integer> entry:hashmap.entrySet()) {  //每次都遍历哈希表，得到最大值更新
	    			int value=entry.getValue();
	    			max=Math.max(max, value);
	    		}
	    	}
	    	return max;
	    }
	    
	    //332：（hard,理解即可）订票路径问题
	    //这道题本质上是欧拉路径问题：从某个顶点开始，经过所有边一次且仅有一次，能够遍历过所有顶点的路径
	    //本质上很简单：哈希表存起止节点+深度优点找死胡同点+栈反序输出
	    //解法：DFS：因为最后一个顶点是死胡同，度为1（不再存在下一个dst），所以优先找到这个死胡同，去掉该死胡同点，下一个必然还是找死胡同点...
	    //死胡同点必须最后遍历到才行，不然就会提前终止。所以找到死胡同点后，需要放入栈，先进后出（或者链表reverse）
        Map<String,PriorityQueue<String>> map=new HashMap<String,PriorityQueue<String>>();  
        List<String> res=new LinkedList<String>();
        public List<String> findItinerary(List<List<String>> tickets){
        	for(List<String> ticket: tickets) {
        		String src=ticket.get(0);
        		String dst=ticket.get(1);
        		if(!map.containsKey(src)) {                          //HashMap里面键是开始地点，值是到达点的队列集合（按字典序排好）
        			map.put(src, new PriorityQueue<String>());
        		}
        		map.get(src).offer(dst);
        	}
        	dfs("JFK");                                             
        	Collections.reverse(res);                                //链表反转，将死胡同点放到最后遍历
        	return res;
        }
        
        public void dfs(String cur) {                                //深度优先，寻找到死胡同点，然后加入链表
        	while(map.containsKey(cur)&&map.get(cur).size()>0) {
        		String tmp=map.get(cur).poll();                      //队列就是先进先出，从最前面开始寻找死胡同点即可
        		dfs(tmp);
        	}
        	res.add(cur);
        }
        
        //303：区域数组和
        //求i-j下标范围内的数组元素和：前缀和。通过前缀和的预处理，使得直接计算时候算是O（1）实现
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
        
        //304：二维前缀和
        //矩形的元素和：其实就是前缀和的二维扩展，名字叫积分图，通过和[0][0]的矩形元素和，S=大-侧1-侧2+小 即可得到答案
        //*****求积分图，是通过S=侧1+侧2-重+单个元素   来求解，通过公式更简单直接。复杂度为O（n^2）,如果不用公式，暴力解，O（n^4）,复杂度过高
        int[][] res304;
        public void NumMatrix(int[][] matrix) {
        	int m=matrix.length;
        	int n=matrix[0].length;
        	res304=new int[m+1][n+1];
        	for(int i=0;i<m;i++) {
        		for(int j=0;j<n;j++) {
        			res304[i+1][j+1]=res304[i][j+1]+res304[i+1][j]-res304[i][j]+matrix[i][j];   //*****res304[i+1][j+1]对应的是以matrix[i][j]为末端点的矩形元素和
        		}                                                                               //*****免去了i=0,j=0这两种特殊情况的元素和的讨论
        	}                                                                                   //*****求积分图，是通过侧1+侧2-重+单个元素来求解，更简单直接

        }
        
        public int sumRegion(int row1, int col1, int row2, int col2) {
        	return res304[row2+1][col2+1]-res304[row2+1][col1]-res304[row1][col2+1]+res304[row1][col1];
        }
        
        //560：连续子数组和区间个数
        //暴力思路：前缀和+双层循环：O（n^2）
        //优化：前缀和+哈希表直接查找
        //哈希表：键是前缀和的值，值是出现的次数
        //连续子数组和：k= sum560[j]-sum560[i-1]  等于了后面前缀和-前面前缀和
	    //sum560[i-1]=sum560[j]-k    也就是说当遍历到j的前缀和时，只需要考虑i<j且sum560[i-1]等于了特定值的前缀和出现的次数即可
        //*****非常巧妙：边计算边更新：当哈希表存储到j时候，哈希表里面的都是i<j的前缀和，所以可以直接取用。
        public int subarraySum(int[] nums, int k) {
        	HashMap<Integer,Integer> hash=new HashMap<>();
        	int sum=0;
        	int count=0;
        	hash.put(0, 1);                                       //*****千万不能忘了，如果i=0.直接j的前缀和也可能满足条件，所以必须要加一个，0元素的前缀和出现次数为1
        	for(int num:nums) {
        		sum+=num;
        		if(hash.containsKey(sum-k)) {
        			count+=hash.get(sum-k);
        		}
        		hash.put(sum, hash.getOrDefault(sum, 0)+1);
        	}
        	return count;
        }
        
        //566：数组重塑
        //二维数组-----一维数组-----二维数组
        //不需要额外空间的做法：直接通过二维一维坐标变换来解决,用通用公共简化复杂度
        //*****通用公式：从二维到一维：x=i*n1+j；元素在一维空间里面的坐标x，映射到m行，n列的二维数组中的坐标：i=x/n2,j=x%n2
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
        
        //225:两个队列实现栈
        //两个栈实现一个队列很简单，依次经过即可。
        //两个队列实现一个栈有点巧妙的：核心就是把新元素一定要放在队首
        //对于新放入的元素，需要后进先出，所以必须放在队列1头部才行。所以我们先把队列1中原先的元素放入队列2，然后把新元素放于队列1
        //然后再将队列2加入队列1即可。这样子新元素始终会放在最前面。
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
        
        //503:下一个更大的元素
        //本题和739每日温度属于同一个题基本：O（n）时间 寻找数组中下一个更大的元素所在位置
        //单调栈法：栈中只维持递减顺序，只要是大于的第一次出现的，都会进行记录。
        //循环数组的解决办法：遍历两次长度，以免出现单调栈中部分元素没办法消掉（一次遍历中，后续没有比之大的元素），所以遍历长度是2*n-2.nums元素取值，需要%n即可
        public int[] nextGreaterElements(int[] nums) {
        	Stack<Integer> stack=new Stack<>();                                //栈中放下标，实际比较用元素值
	    	int[] ans=new int[nums.length];
	    	Arrays.fill(ans, -1);
	    	int n=nums.length;
	    	for(int i=0;i<n*2-1;i++) {
	    		while(!stack.isEmpty()&&nums[i%n]>nums[stack.peek()]) {        //大于栈顶，弹出并记录
	    			int temp=stack.pop();                        
	    			ans[temp]=nums[i%n];
	    		}
	    		stack.push(i%n);                                               //栈顶没有大于的了，则直接放入
	    	}
	    	return ans;
        }
        
        //217:重复元素
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
        
        //697：数组的度
        //*****哈希表的键值可以放数组，使用起来更加简洁方便,本题用哈希表的值来存储数组非常省事
        //简单直接解法，多次遍历：先用哈希表存储元素及出现次数，记录下最大出现次数；然后遍历哈希表，找到最大次数对应的元素们（即出现最多次数的不止一个元素）；
        //然后再去数组中找它们对应的起始位置，末位置，最后最小间隔即可。
        //更加简洁的解法，遍历次数少：用哈希表存储元素，以及元素对应的次数，起始位置，末位置（键是单个元素，值是一个数组）；然后遍历哈希表，取次数最大的，并且次数最大时候取间隔min
        public int findShortestSubArray(int[] nums) {
        	int n=nums.length;
        	HashMap<Integer,int[]> hash=new HashMap<>();
        	for(int i=0;i<n;i++) {
        		if(hash.containsKey(nums[i])) {
        			hash.get(nums[i])[0]++;                        //这里不需要用put,直接进行了修改，因为是数组会自动记录值
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
        
        //594：最长和谐子序列
        //1. 排序+滑动窗口，O（nlogn）：先排好序，那就只需要找x,x+1的窗口内元素个数即可；begin,end,如果差值大于1，则begin后移；差值小于等于1，则记录并end后移。
	    //2. 巧妙，用哈希表直接求取，O（n）：记录每个元素及其出现的次数，我们所需要的就是统计x和x+1的次数和。
        //为什么不需要x,x-1?因为我们对每个元素都求x，x+1的和，实际上就包含了可能出现的x,x-1的情况，也就换成了x-1,x的次数和。所以只统计+1即可。
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
        
        //287：寻找重复数字
        //本题限制了只能用O（1）空间，所以比较难想到,非常巧妙的转化环形链表
        //转化环形链表法：
        //环形链表题自己很熟悉：快慢指针floyd判圈，然后一个从头开始一个原地开始，第一次相遇即可
        //核心：通过下标对应元素值，然后将元素值作为新的下标，来求取下一个元素值，例如：0---1   下一个就是nums[1]=4  下一个nums[4]=0 圈了
        //因为有重复数字，这样就会形成环路，转化为了环形链表
        //即下一个元素：.next-------nums[slow]   .next.next------nums[nums[fast]]
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
        
        //313：超级丑数
        //如果考虑DP求解，真的超级难，看不懂
        //便捷解法：优先队列+哈希表
        //核心：丑数排序的核心就是，一个最小丑数出队，那么用它乘以所有初始丑数的乘积入队，然后再找最小值即可...
        //哈希表思路是对的，但是会超时，所以本题用了很巧妙的解法判断重复：
        //if(x%prime==0）break;           ？？？为什么可以做到
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
        				//if(x%prime==0）break;                      标准解答用这句来去重，但是我没明白为什么这句可以实现？？？
        			}
        		}
        	}
        	return -1;
        }
        
        //870：优势洗牌
        //思路很明确：贪心，对两个数组排序，然后对nums2中从最小的开始，找nums1中比之大的最小的那个元素即可
        //关键是需要输出的是重建后的nums1,所以比较麻烦。需要排序+记录下标----考虑用优先队列中存数组来存nums2的值和下标，然后依次取出和nums1排完序的比较
        public int[] advantageCount(int[] nums1, int[] nums2) {
        	int n=nums2.length;
        	PriorityQueue<int[]> q=new PriorityQueue<>((o1,o2)->o1[0]-o2[0]);     //*****优先队列重写比较器，按照数组的第一个元素进行升序排序
        	for(int i=0;i<n;i++) {
        		q.add(new int[] {nums2[i],i});
        	}
        	Arrays.sort(nums1); 
        	int[] res=new int[n];
        	Arrays.fill(res, -1);                                                 //结果数组初值都赋成-1，这样子后面对应位置没有改变的元素就一直是-1
        	int begin=0;
        	List<Integer> list=new ArrayList<>();                                 //用一个列表来存放没有用的nums1元素（没有用指的是对应位置比nums2中元素值小，导致不需要直接放入对应位置）
        	while(!q.isEmpty()) {                                                 
        		if(begin==n-1) break;                                             //比较结束的条件
        		if(q.peek()[0]<nums1[begin]) {                                    //如果nums2中位置元素小于nums1中元素，则将nums1放入对应位置的结果答案中                
        			res[q.peek()[1]]=nums1[begin];
        			begin++;
        			q.poll();
        		}else {
        			list.add(nums1[begin]);                                       
        			begin++;	
        		}     		
        	}
        	int j=0;
        	for(int i=0;i<n;i++) {                                                 //把没用到的nums1中元素，直接依次插入结果即可
        		if(res[i]==-1) {
        			res[i]=list.get(j++);
        		}
        	}
        	return res;
        }
        
        //307：前缀和的数据结构表示
        //本题用前缀和，复杂度超了，这里介绍的是实现前缀和的一种数据结构----树状数组----超纲了
        //树状数组据说很难，暂时不理解吧，留着，以后碰到了在详细理解（超纲，再碰到再理解）
        //*****暂时不理解，直接记忆模板
        class NumArray{
        	//树状数组必写的模板，三个函数
        	int[] tree;                                          //树状数组模板
        	int lowbit(int x) {
        		return x&-x;
        	}
        	
        	int query(int x) {                                   //前缀和模板
        		int ans=0;
        		for(int i=x;i>0;i-=lowbit(i)) ans+=tree[i];
        		return ans;
        	}
        	
        	void add(int x,int u) {                              //更新值模板
        		for(int i=x;i<=n;i+=lowbit(i)) tree[i]+=u;
        	}
        	
        	
        	int[] nums;
        	int n;
        	public NumArray(int[] _nums) {
        		nums=_nums;
        		n=nums.length;
        		tree=new int[n+1];
        		
        		//树状数组初始化模板
        		for(int i=0;i<n;i++) add(i+1,nums[i]);
        	}
        	
        	public void update(int i,int val) {                 //更新增加值
        		add(i+1,val-nums[i]);
        		nums[i]=val;
        	}
        	
        	public int sumRange(int l,int r) {
        		return query(r+1)-query(l);
        	}
        	
        }
        
     
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
