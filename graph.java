package work;
import java.util.*;
import java.util.stream.Collectors;

import javax.xml.soap.Node;

import java.lang.*;
import java.lang.reflect.Array;


public class graph {
	
	
	//*****图的最基本的操作：
	//1. hashmap存节点关系，HashMap<Integer,List<Integer>>：键---本节点  值---邻接节点的集合  终端节点不在数组中有关系，所以不再哈希表中
	//2. hashset存是否访问
	//3. 二分图：三个标记，队列放入为0的节点；拓扑排序：队列放入入度为0的节点
	
	//两个点有点难，估计不会考察到的： 并查集的实现，判断连通，kruskal算法；dijiskal算法实现
	
	//6. 重点题：146LRU缓存：考察的重点在于选取数据结构：哈希表存 值+节点 用于定位节点元素
	//双向链表中头部是最近使用过的，尾部是最久未使用的。
	//7. 重点题：O(1)删除：list+hashmap, hashmap中存 值+下标  list 下标+值
	//删除时候：由于list删除复杂度是O(n),但是删除末尾是O(1),所以我们先用末尾元素覆盖index元素，然后再删除末尾即可实现O(1)
	//8. 字符串计数O(1)查询：哈希表存放，key字符,value节点；双向链表的节点，包含一个计数count,前后指针，以及计数为当前值的字符串集合，用Set
	//*****哈希表定位节点+双向链表排序     这种思考方法在很多数据结构题目中都出现了
	
	
	
	//785:二分图：
	//三类判断法+队列：0，1，2
	//核心思想：每次遍历都从未染色（0）的节点开始，将其染成红色(1)/绿色(2)，那么他的相邻节点为0的就必须染成绿色/红色
	//此时判断：相邻节点已经有色彩的是否二次染色，即和源节点同色。
	//然后对于这些相邻节点的相邻节点，那就得染成红色...如此循环下去
	//因为要一直向下循环找相邻节点，所以说需要用队列来存储
	//*****只有未染色的才需要放入队列，进行染色(染色需要和相邻的那个不同)，而已经染色过的直接判断就可以。
	//为什么？因为一开始都是无色的，只有放入队列的才能染色，所以碰到的染过色的都是进出过队列的，所以没必要再次进入队列找相邻。
	 public boolean isBipartite(int[][] graph) {
		 int[] color=new int[graph.length];
		 for(int i=0;i<color.length;i++) {
			 if(color[i]==0) {
				 Queue<Integer> q=new LinkedList<>();
				 q.offer(i);
				 color[i]=1;
				 while(!q.isEmpty()) {
					 int k=q.poll();
					 for(int index:graph[k]) {
						 if(color[index]==0) {      //*****主要一个注意点就是这里：只有未染过色的才能进入队列
							 q.offer(index);
							 color[index]=color[k]==1?2:1;
						 }else if(color[index]==color[k]) {
							 return false;
						 }
					 }
				 }
			 }
		 }
		 return true;
	    }
	 
	 //210：拓扑排序
	 //核心和平常做题是一样的：广度优先，先在队列中放入入度为0的节点，然后从队列中弹出，相应的节点入度--，然后入度为0的再放入，再弹出...
	 //所以需要存储的变量有：每个节点所指向的节点集合，每个节点的入度数量，结果顺序
	 public int[] findOrder(int numCourses, int[][] prerequisites) {
		 int[] res=new int[numCourses];
		 int index=0;
		 LinkedList<LinkedList<Integer>> inList=new LinkedList<LinkedList<Integer>>();
		 int[] inNumber=new int[numCourses];
		 for(int i=0;i<numCourses;i++) {
			 inList.add(new LinkedList<>());
		 }
		 for(int[] p:prerequisites) {
			 inList.get(p[1]).add(p[0]);              //*****注意：这里存的是每个节点所指向的节点的集合，这样子有节点出队的时候，就可以直接将对应指向节点的
			 inNumber[p[0]]++;                        //inNumber--,然后再判断即可。千万别弄反了。
		 }
		 Queue<Integer> q=new LinkedList<>();
		 for(int i=0;i<numCourses;i++) {
			 if(inNumber[i]==0) {
				 q.offer(i);
			 }
		 }
		 while(!q.isEmpty()) {
			 int k=q.poll();
			 res[index++]=k;
			 for(int v:inList.get(k)) {
				 inNumber[v]--;
				 if(inNumber[v]==0) {
					 q.offer(v);
				 }
			 }
		 }
		 if(index!=numCourses) return new int[0];
		 return res;
	    }
	 
	 //1059：（VIP题，简单易懂）始点的所有路径到唯一终点
	 //回溯法判断
	 //判终点用hashmap，判环用hashset
	 //题目意思：在图中指定一个起点，从该起点出发，只能到达唯一的一个终点，而且图中不存在环结构
	 //判断唯一终点：HashMap存节点和他的指向节点(可能多个)，如果hashmap中没有该节点，那就说明没有后继节点，该节点属于最后的终端节点
	 //终端节点必定为des，如果不是，那就是false;
	 //判断环：用visited数组（这里用Set更加简便），如果已经存在了，就是false;然后递归邻居
	 public boolean leadsToDestination(int n,int[][] edges,int source,int destination) {
		 HashMap<Integer,List<Integer>> graph=new HashMap<>();
		 for(int[] edge:edges) {
			 graph.putIfAbsent(edge[0], new ArrayList<>());
			 graph.get(edge[0]).add(edge[1]);
		 }
		 return helper1059(graph,new HashSet<>(),source,destination);
	 }
	 
	 public boolean helper1059(Map<Integer,List<Integer>> graph,Set<Integer> visited,int cur,int end) {
		 if(!graph.containsKey(cur)) {   //hashmap不存在，说明没有后继节点，需要判断是否和题中终点相符合
			 return cur==end;
		 }
		 visited.add(cur);
		 for(int neighbor:graph.get(cur)) {
			 if(visited.contains(neighbor)||helper1059(graph,visited,neighbor,end)) {    //判断是否访问过，并递归邻接节点
				 return false;
			 }
		 }
		 visited.remove(cur);
		 return true;
	 }
	 
	 //1135：（VIP）最小代价生成树
	 //排序+并查集判断连通即可
	 //kruskal算法（加边）：每次加入权值最小的边，连通两个节点，且不会形成环，直到所有节点联通
	 //prim算法（加顶点和边），每次多连通一个节点
	 //用kruskal:先把所有边从小到大排序，然后不断加入最小的边，保证能够使得边两端的节点本来不连通，加入后连通（用并查集来求解）
	 //终止条件：边数=节点数-1（没有环，两个节点一条边）
	 List<Integer> p=new LinkedList<>();
	 public int find1135(int x) {
		 while(x!=p.get(x)) x=p.get(x);
		 return x;
	 }
	 
	 public int minimumCost(int N,List<List<Integer>> connections) {
		 int ans=0;
		 int point_num=0;
		 for(int i=0;i<=N;i++) {
			 p.add(i);
		 }
		 /*Collections.sort(connections, new Comparator<List<List<Integer>>>){
			 @Override
			 public int compare(List<List<Integer>> connections1,List<List<Integer>> connections2) {
				 return connections1.get(2)-connections2.get(2);
			 }
		 } 需要写一个排序，将所有边权值从小到大排序                */
		 for(List<Integer> conn:connections) {
			 int px=find1135(conn.get(0));            //并查集，寻找father
			 int py=find1135(conn.get(1));
			 if(px!=py) {                             //如果一开始不连通，那么就可以加入该边
				 p.set(px, py);                       //变连通
				 ans+=conn.get(2);
				 point_num++;
				 if(point_num==N-1) {
					 return ans;
				 }
			 }
		 }
		 return -1;
	 }
	 
	 //882：(hard,理解即可)可到达的节点数
	 //dijiskal单源最短路径题：https://zhuanlan.zhihu.com/p/338414118 讲解了如何利用结果集（跳转集）/未更新集，算法流程easy,主要是代码，这篇知乎的代码可以借鉴
	 //核心：单源最短路径+对每个节点到达后可以向前继续遍历的节点数，Max-disx:就表示到了x后，可以继续向后的最大节点数，对每个可到达节点都求一次，相加即可。
	 //其实没必要这么复杂的求解：就是几个关键步骤：
	 //1. 通过dijiskal求出maxMoves可到达的单源集合以及其最短路径distance[x]
	 //2. 对于每个可到达的节点，通过maxMoves-distance[x],可以得到到达x后，还可以遍历的小节点数
	 //3. 为了防止出现到达后一整个小节点都覆盖了，所以需要min(边长，maxMoves-x+maxMoves-y),因为双向的，所以每条边x,y都需要一次求小节点。
	 public int reachableNodes(int[][] edges,int maxMoves,int n) {
		 int len=edges.length;
		 //存图节点
		 Map<Integer,List<Integer>> graph=new HashMap<>(len);
		 //存每条边，编号和长度
		 Map<Integer,Integer> edgeMap=new HashMap<>(len);
		 final int INF=10001;
		 for(int[] edge:edges) {
			 int x=edge[0];
			 int y=edge[1];
			 List<Integer> list=graph.getOrDefault(x, new ArrayList<>());
			 list.add(y);
			 graph.put(x, list);
			 list=graph.getOrDefault(y, new ArrayList<>());
			 list.add(x);
			 graph.put(y, list);
			 int v=edge[2];
			 edgeMap.put(getIndex(x,y), v+1);	 
		 }
		 //dijiskal求单源到每个的最短路径，这里用的是优先队列，自己可以用知乎那个来求解，比较简单易懂，但是复杂度高点
		 //*****有空可以试试用知乎上简单数组的方法实现dijiska单源最短路径求distance数组
		 int[] distance=new int[len];
		 Arrays.fill(distance, INF);
		 distance[0]=0;
		 boolean[] isVisited=new boolean[len];
		 PriorityQueue<Node> queue=new PriorityQueue<>(Comparator.comparingInt(o->o.curDistance));
		 queue.offer(new Node(0,0));
		 int res=0;
		 while(!queue.isEmpty()) {
			 Node node=queue.poll();
			 if(isVisited[node.point]) {
				 continue;
			 }
			 isVisited[node.point]=true;
			 res++;
			 distance[node.point]=node.curDistance;
			 List<Integer> list=graph.get(node.point);
			 if(list==null||list.size()==0) {
				 continue;
			 }
			 for(int nextPoint:list) {
				 int v=edgeMap.get(getIndex(node.point,nextPoint));
				 if(v==INF||isVisited[nextPoint]) {
					 continue;
				 }
				 int d=node.curDistance+v;
				 if(d<=maxMoves) {
					 queue.offer(new Node(nextPoint,d));
				 }
			 }
	 }
		 for(int[] edge:edges) {
			 int x=edge[0];
			 int y=edge[1];
			 int v=edge[2];
			 int remain=0;
			 if(isVisited[x]) {
				 remain+=maxMoves-distance[x];
			 }
			 if(isVisited[y]) {
				 remain+=maxMoves-distance[y];
			 }
			 res+=Math.min(v, remain);
		 }
		 return res;
	 }
		 
		 public Integer getIndex(int x,int y) {
			 return Math.min(x, y)*3001+Math.max(x, y);
		 }
		 
		 public class Node{
			 int point;
			 int curDistance;
			 public Node(int point,int curDistance) {
				 this.point=point;
				 this.curDistance=curDistance; 
			 }
		 }
		 
		 //684：冗余的一条边
		 //并查集：如果一条边的两个节点本来就是连通的，那么本次的该边，就是冗余边。
		 //*****并查集的实现：判断是否连通
		 //1. 初始化，每个节点的父节点是自身，只需要一个parent数组即可
		 //2. 对于每次加入的边，先判断两个节点是否连通，连通则不需要此边，不连通则union一下
		 //3. 判断连通与否：就是判断他们的find函数最高层父节点是否相同，相同则连通
		 //4. find函数：寻找一个节点的最高层父节点，即最后那个父节点是自身的节点
		 //5. union函数：连通两个节点，就是把他们的最高层父节点连通一下，把一个的parent[ 父1  ]= 父2
		 public int[] findRedundantConnection(int[][] edges) {
			 int n=edges.length;
			 int[] parent=new int[n+1];        //本题的节点标号，从1开始到n
			 for(int i=1;i<n+1;i++) {
				parent[i]=i;
			 }
			 for(int[] edge:edges) {
				 int x=edge[0];
				 int y=edge[1];
				 if(find684(parent,x)!=find684(parent,y)) {
					 union684(parent,x,y);
				 }else {
					 return edge;
				 }
			 }
			 return new int[0];
		    }
		 
		 public int find684(int[] parent,int index) {
			 while(parent[index]!=index) {
				 index=parent[index];                  //*****根本不需要递归，就是个很简单的向上寻找遍历，递归太复杂了
			 }
			 return parent[index];
		 }
		 
		 public void union684(int[] parent,int x,int y) {
			 parent[find684(parent,x)]=find684(parent,y);   //把最高层父节点连通一下即可
		 }
		 
		 //146：LRU缓存实现
		 //双端队列不好用，因为队列没办法定位元素
		 //这道题看似复杂，实际上不难，只要知道了需要用到哈希表和双向链表，剩下的都是些基本逻辑和链表基本操作而已，不难。
		 //核心：哈希表肯定需要，*****为了实现最近最久未使用，还需要一个双向链表，头部是最近使用的，尾部是最久未使用的
		 //用哈希表里面存key和对应的节点，这样方便快速定位
		 //*****双向链表需要手动实现，最好用头指针，尾指针，这样就方便遍历，头插尾删
		 //链表操作：定位到之后，需要删除节点，然后放到头部；删除节点，尾部置空
		 class LRUCache {
			 public class TwoLinkedNode{
				 TwoLinkedNode prev;
				 TwoLinkedNode next;
				 int key;
				 int value;
				 public TwoLinkedNode() {}
				 public TwoLinkedNode(int key,int value) {
					 this.key=key;
					 this.value=value;
				 }	 
			 }
			 
			 HashMap<Integer,TwoLinkedNode> hash=new HashMap<>();
			 int size;
			 int capacity;
			 TwoLinkedNode head,tail;
			   
			    public LRUCache(int capacity) {
			    	this.size=0;
			    	this.capacity=capacity;
			    	head=new TwoLinkedNode();
			    	tail=new TwoLinkedNode();
			    	head.next=tail;
			    	tail.prev=head;
			    }
			    
			    public int get(int key) {
			    	TwoLinkedNode node=hash.get(key);
			    	if(node==null) return -1;
			    	moveToHead(node);
			    	return node.value;
			    }
			    
			    public void put(int key, int value) {
			    	TwoLinkedNode node=hash.get(key);
			    	if(node==null) {
			    		TwoLinkedNode t=new TwoLinkedNode(key,value);
			    		hash.put(key, t);
			    		addToHead(t);
			    		size++;
			    		if(size>capacity) {
			    			TwoLinkedNode n=removeTail();
			    			hash.remove(n.key);
			    			--size;
			    		}
			    	}else {
			    		node.value=value;
			    		moveToHead(node);
			    	}
			    }
			    
			    public void addToHead(TwoLinkedNode node) {
			    	node.next=head.next;
			    	node.prev=head;
			    	head.next.prev=node;
			    	head.next=node;
			    }
			    
			    public void removeNode(TwoLinkedNode node) {
			    	node.prev.next=node.next;
			    	node.next.prev=node.prev;
			    }
			    
			    public void moveToHead(TwoLinkedNode node) {
			    	removeNode(node);
			    	addToHead(node);
			    }
			    
			    public TwoLinkedNode removeTail() {
			    	TwoLinkedNode res=tail.prev;
			    	removeNode(res);
			    	return res;
			    } 
			}
		 
		 //380：插入，删除，随机元素均为O（1）的数据结构
		 //插入删除O（1）---哈希表，随机元素O（1）---数组或者list
		 //所以可以想到本题使用哈希表和list相结合，哈希表里面存的应该是值以及值在list里面的下标
		 //*****注意一下删除特定元素：因为list里面如果删除特定下标的元素，时间复杂度是O（n）（需要寻找下标的过程）,但是删除末尾是O（1）、
		 //所以我们用的方法是，将index下标对应元素换成末尾元素，然后直接将末尾删除即可实现O（1）删除
		 class RandomizedSet {
			 HashMap<Integer,Integer> hash;
			 List<Integer> list;
			 Random random;

			    public RandomizedSet() {
			    	hash=new HashMap<>();
			    	list=new ArrayList<>();
			    	random=new Random();
			    }
			    
			    public boolean insert(int val) {
			    	if(hash.containsKey(val)) {
			    		return false;
			    	}
			    	int size=list.size();
			    	list.add(val);
			    	hash.put(val, size);
			    	return true;
			    }
			    
			    public boolean remove(int val) {
			    	if(!hash.containsKey(val)) {
			    		return false;
			    	}
			    	int index=hash.get(val);
			    	int last=list.get(list.size()-1);
			    	list.set(index, last);
			    	list.remove(list.size()-1);
			    	hash.put(last,index);
			    	hash.remove(val);
			    	return true;
			    }
			    
			    public int getRandom() {
			    	int next=random.nextInt(list.size());
			    	return list.get(next);
			    }
			}
		 
		 //432:(hard,理解即可)字符串计数，查询O（1）
		 //哈希表能实现计数O（1），但是查询O（1）实现不了，这里用的是双向链表来实现，和LRU很类似
		 //这道题其实也不难(工作量很大，小点的逻辑很多，但是没什么难的算法)，就是哈希表定位+双向链表维持顺序查询
		 //哈希表存放，key字符,value节点；双向链表的节点，包含一个计数count,前后指针，以及计数为当前值的字符串集合，用Set
		 //增加计数：先看哈希表中有没有，如果有，定位到节点，因为要+1，那就意味着本次字符串要从当前节点移动到count+1的节点处，
		 //所以要判断count+1节点是否存在，存在那就直接放入对应的set；不存在就需要在本次节点后面新增一个count+1节点插入
		 //如果哈希表中本身不存在，也就是count从0到1，那就直接在开头新增一个为1的计数节点
		 //减少计数：和上面类似，就是判断count-1是否存在，存在直接放入，不存在需要新建并插入
		 //最大最小值，那就是链表的开头和结尾
		 class AllOne{
			 class Node{
				 int count;
				 HashSet<String> set=new HashSet<>();
				 Node prev,next;
				 Node(int c){
					 count=c;
				 }
			 }
			 
			 Node head,tail;
			 HashMap<String,Node> map=new HashMap<>();
			 public AllOne() {
				 head=new Node(-1000);
				 tail=new Node(-1000);
				 head.next=tail;
				 tail.prev=head;
			 }
			 
			 public void clear(Node node) {
				 if(node.set.size()==0) {
					 node.prev.next=node.next;
					 node.next.prev=node.prev;
				 }
			 }
			 
			 public void inc(String key) {
				 if(map.containsKey(key)) {
					 Node node=map.get(key);
					 node.set.remove(key);
					 int cnt=node.count;
					 Node n=null;
					 if(node.next.count==cnt+1) {
						 n=node.next;
					 }else {
						 n=new Node(cnt+1);
						 n.next=node.next;
						 n.prev=node;
						 node.next.prev=n;
						 node.next=n;
					 }
					 n.set.add(key);
					 map.put(key, n);
					 clear(node);
				 }else {
					 Node node=null;
					 if(head.next.count==1) {
						 node=head.next;
					 }else {
						 node=new Node(1);
						 node.next=head.next;
						 node.prev=head;
						 head.next.prev=node;
						 head.next=node;
					 }
					 node.set.add(key);
					 map.put(key, node);
				 }
			 }
			 
			 public void dec(String key) {
				 Node node=map.get(key);
				 node.set.remove(key);
				 int cnt=node.count;
				 if(cnt==1) {
					 map.remove(key);
				 }else {
					 Node n=null;
					 if(node.prev.count==cnt-1) {
						 n=node.prev;
					 }else {
						 n=new Node(cnt-1);
						 n.next=node;
						 n.prev=node.prev;
						 node.prev.next=n;
						 node.prev=n;				 
					 }
					 n.set.add(key);
					 map.put(key, n);
				 }
				 clear(node);
			 }
			 
			 public String getMaxKey() {
				 Node node=tail.prev;
				 for(String str:node.set) return str;
				 return "";
			 }
			 
			 public String getMinKey() {
				 Node node=head.next;
				 for(String str:node.set) return str;
				 return "";
			 } 
		 }
		 
		 //716：（VIP）最大栈
		 //解法1：和最小栈一样，用双栈法，维护一个当前对应值的最大栈，复杂度最高为O（n）（多了个弹出最大值）
		 //解法2：TreeMap+双向链表，太复杂了，这里不写了
		 //双栈法：维护一个普通栈，一个只存当前元素对应的最大值的栈。如果要删除最大值，那就先把不是最大值的拿出来，然后删除最大值，再放回去（多了这个过程）
		 //155最小栈，没有弹出最小值的操作，所以是O（1）.
		 class MaxStack{
			 Stack<Integer> stack;
			 Stack<Integer> maxStack;
			 
			 public MaxStack() {
				 stack=new Stack();
				 maxStack=new Stack();
			 }
			 
			 public void push(int x) {
				 int max=maxStack.isEmpty()?x:maxStack.peek();
				 maxStack.push(max>x?max:x);
				 stack.push(x);
			 }
			 
			 public int top() {
				 return stack.peek();
			 }
			 
			 public int pop() {
				 maxStack.pop();
				 return stack.pop();
			 }
			 
			 public int peekMax() {
				 return maxStack.peek();
			 }
			 
			 public int popMax() {
				 int max=peekMax();
				 Stack<Integer> buffer=new Stack();
				 while(top()!=max) buffer.push(pop());
				 pop();
				 while(!buffer.isEmpty()) push(buffer.pop());
				 return max;
			 } 
		 }
		 
		 //力扣题数+5，因为有五道VIP题没办法写进去
		 
		 //不连数据库的题目，一共287道题目
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
