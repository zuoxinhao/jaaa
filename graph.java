package work;
import java.util.*;
import java.util.stream.Collectors;

import javax.xml.soap.Node;

import java.lang.*;
import java.lang.reflect.Array;


public class graph {
	
	
	//*****ͼ��������Ĳ�����
	//1. hashmap��ڵ��ϵ��HashMap<Integer,List<Integer>>����---���ڵ�  ֵ---�ڽӽڵ�ļ���  �ն˽ڵ㲻���������й�ϵ�����Բ��ٹ�ϣ����
	//2. hashset���Ƿ����
	//3. ����ͼ��������ǣ����з���Ϊ0�Ľڵ㣻�������򣺶��з������Ϊ0�Ľڵ�
	
	//�������е��ѣ����Ʋ��ῼ�쵽�ģ� ���鼯��ʵ�֣��ж���ͨ��kruskal�㷨��dijiskal�㷨ʵ��
	
	//6. �ص��⣺146LRU���棺������ص�����ѡȡ���ݽṹ����ϣ��� ֵ+�ڵ� ���ڶ�λ�ڵ�Ԫ��
	//˫��������ͷ�������ʹ�ù��ģ�β�������δʹ�õġ�
	//7. �ص��⣺O(1)ɾ����list+hashmap, hashmap�д� ֵ+�±�  list �±�+ֵ
	//ɾ��ʱ������listɾ�����Ӷ���O(n),����ɾ��ĩβ��O(1),������������ĩβԪ�ظ���indexԪ�أ�Ȼ����ɾ��ĩβ����ʵ��O(1)
	//8. �ַ�������O(1)��ѯ����ϣ���ţ�key�ַ�,value�ڵ㣻˫������Ľڵ㣬����һ������count,ǰ��ָ�룬�Լ�����Ϊ��ǰֵ���ַ������ϣ���Set
	//*****��ϣ��λ�ڵ�+˫����������     ����˼�������ںܶ����ݽṹ��Ŀ�ж�������
	
	
	
	//785:����ͼ��
	//�����жϷ�+���У�0��1��2
	//����˼�룺ÿ�α�������δȾɫ��0���Ľڵ㿪ʼ������Ⱦ�ɺ�ɫ(1)/��ɫ(2)����ô�������ڽڵ�Ϊ0�ľͱ���Ⱦ����ɫ/��ɫ
	//��ʱ�жϣ����ڽڵ��Ѿ���ɫ�ʵ��Ƿ����Ⱦɫ������Դ�ڵ�ͬɫ��
	//Ȼ�������Щ���ڽڵ�����ڽڵ㣬�Ǿ͵�Ⱦ�ɺ�ɫ...���ѭ����ȥ
	//��ΪҪһֱ����ѭ�������ڽڵ㣬����˵��Ҫ�ö������洢
	//*****ֻ��δȾɫ�Ĳ���Ҫ������У�����Ⱦɫ(Ⱦɫ��Ҫ�����ڵ��Ǹ���ͬ)�����Ѿ�Ⱦɫ����ֱ���жϾͿ��ԡ�
	//Ϊʲô����Ϊһ��ʼ������ɫ�ģ�ֻ�з�����еĲ���Ⱦɫ������������Ⱦ��ɫ�Ķ��ǽ��������еģ�����û��Ҫ�ٴν�����������ڡ�
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
						 if(color[index]==0) {      //*****��Ҫһ��ע���������ֻ��δȾ��ɫ�Ĳ��ܽ������
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
	 
	 //210����������
	 //���ĺ�ƽ��������һ���ģ�������ȣ����ڶ����з������Ϊ0�Ľڵ㣬Ȼ��Ӷ����е�������Ӧ�Ľڵ����--��Ȼ�����Ϊ0���ٷ��룬�ٵ���...
	 //������Ҫ�洢�ı����У�ÿ���ڵ���ָ��Ľڵ㼯�ϣ�ÿ���ڵ��������������˳��
	 public int[] findOrder(int numCourses, int[][] prerequisites) {
		 int[] res=new int[numCourses];
		 int index=0;
		 LinkedList<LinkedList<Integer>> inList=new LinkedList<LinkedList<Integer>>();
		 int[] inNumber=new int[numCourses];
		 for(int i=0;i<numCourses;i++) {
			 inList.add(new LinkedList<>());
		 }
		 for(int[] p:prerequisites) {
			 inList.get(p[1]).add(p[0]);              //*****ע�⣺��������ÿ���ڵ���ָ��Ľڵ�ļ��ϣ��������нڵ���ӵ�ʱ�򣬾Ϳ���ֱ�ӽ���Ӧָ��ڵ��
			 inNumber[p[0]]++;                        //inNumber--,Ȼ�����жϼ��ɡ�ǧ���Ū���ˡ�
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
	 
	 //1059����VIP�⣬���׶���ʼ�������·����Ψһ�յ�
	 //���ݷ��ж�
	 //���յ���hashmap���л���hashset
	 //��Ŀ��˼����ͼ��ָ��һ����㣬�Ӹ���������ֻ�ܵ���Ψһ��һ���յ㣬����ͼ�в����ڻ��ṹ
	 //�ж�Ψһ�յ㣺HashMap��ڵ������ָ��ڵ�(���ܶ��)�����hashmap��û�иýڵ㣬�Ǿ�˵��û�к�̽ڵ㣬�ýڵ����������ն˽ڵ�
	 //�ն˽ڵ�ض�Ϊdes��������ǣ��Ǿ���false;
	 //�жϻ�����visited���飨������Set���Ӽ�㣩������Ѿ������ˣ�����false;Ȼ��ݹ��ھ�
	 public boolean leadsToDestination(int n,int[][] edges,int source,int destination) {
		 HashMap<Integer,List<Integer>> graph=new HashMap<>();
		 for(int[] edge:edges) {
			 graph.putIfAbsent(edge[0], new ArrayList<>());
			 graph.get(edge[0]).add(edge[1]);
		 }
		 return helper1059(graph,new HashSet<>(),source,destination);
	 }
	 
	 public boolean helper1059(Map<Integer,List<Integer>> graph,Set<Integer> visited,int cur,int end) {
		 if(!graph.containsKey(cur)) {   //hashmap�����ڣ�˵��û�к�̽ڵ㣬��Ҫ�ж��Ƿ�������յ������
			 return cur==end;
		 }
		 visited.add(cur);
		 for(int neighbor:graph.get(cur)) {
			 if(visited.contains(neighbor)||helper1059(graph,visited,neighbor,end)) {    //�ж��Ƿ���ʹ������ݹ��ڽӽڵ�
				 return false;
			 }
		 }
		 visited.remove(cur);
		 return true;
	 }
	 
	 //1135����VIP����С����������
	 //����+���鼯�ж���ͨ����
	 //kruskal�㷨���ӱߣ���ÿ�μ���Ȩֵ��С�ıߣ���ͨ�����ڵ㣬�Ҳ����γɻ���ֱ�����нڵ���ͨ
	 //prim�㷨���Ӷ���ͱߣ���ÿ�ζ���ͨһ���ڵ�
	 //��kruskal:�Ȱ����бߴ�С��������Ȼ�󲻶ϼ�����С�ıߣ���֤�ܹ�ʹ�ñ����˵Ľڵ㱾������ͨ���������ͨ���ò��鼯����⣩
	 //��ֹ����������=�ڵ���-1��û�л��������ڵ�һ���ߣ�
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
		 } ��Ҫдһ�����򣬽����б�Ȩֵ��С��������                */
		 for(List<Integer> conn:connections) {
			 int px=find1135(conn.get(0));            //���鼯��Ѱ��father
			 int py=find1135(conn.get(1));
			 if(px!=py) {                             //���һ��ʼ����ͨ����ô�Ϳ��Լ���ñ�
				 p.set(px, py);                       //����ͨ
				 ans+=conn.get(2);
				 point_num++;
				 if(point_num==N-1) {
					 return ans;
				 }
			 }
		 }
		 return -1;
	 }
	 
	 //882��(hard,��⼴��)�ɵ���Ľڵ���
	 //dijiskal��Դ���·���⣺https://zhuanlan.zhihu.com/p/338414118 ������������ý��������ת����/δ���¼����㷨����easy,��Ҫ�Ǵ��룬��ƪ֪���Ĵ�����Խ��
	 //���ģ���Դ���·��+��ÿ���ڵ㵽��������ǰ���������Ľڵ�����Max-disx:�ͱ�ʾ����x�󣬿��Լ����������ڵ�������ÿ���ɵ���ڵ㶼��һ�Σ���Ӽ��ɡ�
	 //��ʵû��Ҫ��ô���ӵ���⣺���Ǽ����ؼ����裺
	 //1. ͨ��dijiskal���maxMoves�ɵ���ĵ�Դ�����Լ������·��distance[x]
	 //2. ����ÿ���ɵ���Ľڵ㣬ͨ��maxMoves-distance[x],���Եõ�����x�󣬻����Ա�����С�ڵ���
	 //3. Ϊ�˷�ֹ���ֵ����һ����С�ڵ㶼�����ˣ�������Ҫmin(�߳���maxMoves-x+maxMoves-y),��Ϊ˫��ģ�����ÿ����x,y����Ҫһ����С�ڵ㡣
	 public int reachableNodes(int[][] edges,int maxMoves,int n) {
		 int len=edges.length;
		 //��ͼ�ڵ�
		 Map<Integer,List<Integer>> graph=new HashMap<>(len);
		 //��ÿ���ߣ���źͳ���
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
		 //dijiskal��Դ��ÿ�������·���������õ������ȶ��У��Լ�������֪���Ǹ�����⣬�Ƚϼ��׶������Ǹ��Ӷȸߵ�
		 //*****�пտ���������֪���ϼ�����ķ���ʵ��dijiska��Դ���·����distance����
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
		 
		 //684�������һ����
		 //���鼯�����һ���ߵ������ڵ㱾��������ͨ�ģ���ô���εĸñߣ���������ߡ�
		 //*****���鼯��ʵ�֣��ж��Ƿ���ͨ
		 //1. ��ʼ����ÿ���ڵ�ĸ��ڵ�������ֻ��Ҫһ��parent���鼴��
		 //2. ����ÿ�μ���ıߣ����ж������ڵ��Ƿ���ͨ����ͨ����Ҫ�˱ߣ�����ͨ��unionһ��
		 //3. �ж���ͨ��񣺾����ж����ǵ�find������߲㸸�ڵ��Ƿ���ͬ����ͬ����ͨ
		 //4. find������Ѱ��һ���ڵ����߲㸸�ڵ㣬������Ǹ����ڵ�������Ľڵ�
		 //5. union��������ͨ�����ڵ㣬���ǰ����ǵ���߲㸸�ڵ���ͨһ�£���һ����parent[ ��1  ]= ��2
		 public int[] findRedundantConnection(int[][] edges) {
			 int n=edges.length;
			 int[] parent=new int[n+1];        //����Ľڵ��ţ���1��ʼ��n
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
				 index=parent[index];                  //*****��������Ҫ�ݹ飬���Ǹ��ܼ򵥵�����Ѱ�ұ������ݹ�̫������
			 }
			 return parent[index];
		 }
		 
		 public void union684(int[] parent,int x,int y) {
			 parent[find684(parent,x)]=find684(parent,y);   //����߲㸸�ڵ���ͨһ�¼���
		 }
		 
		 //146��LRU����ʵ��
		 //˫�˶��в����ã���Ϊ����û�취��λԪ��
		 //����⿴�Ƹ��ӣ�ʵ���ϲ��ѣ�ֻҪ֪������Ҫ�õ���ϣ���˫������ʣ�µĶ���Щ�����߼�����������������ѣ����ѡ�
		 //���ģ���ϣ��϶���Ҫ��*****Ϊ��ʵ��������δʹ�ã�����Ҫһ��˫������ͷ�������ʹ�õģ�β�������δʹ�õ�
		 //�ù�ϣ�������key�Ͷ�Ӧ�Ľڵ㣬����������ٶ�λ
		 //*****˫��������Ҫ�ֶ�ʵ�֣������ͷָ�룬βָ�룬�����ͷ��������ͷ��βɾ
		 //�����������λ��֮����Ҫɾ���ڵ㣬Ȼ��ŵ�ͷ����ɾ���ڵ㣬β���ÿ�
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
		 
		 //380�����룬ɾ�������Ԫ�ؾ�ΪO��1�������ݽṹ
		 //����ɾ��O��1��---��ϣ�����Ԫ��O��1��---�������list
		 //���Կ����뵽����ʹ�ù�ϣ���list���ϣ���ϣ��������Ӧ����ֵ�Լ�ֵ��list������±�
		 //*****ע��һ��ɾ���ض�Ԫ�أ���Ϊlist�������ɾ���ض��±��Ԫ�أ�ʱ�临�Ӷ���O��n������ҪѰ���±�Ĺ��̣�,����ɾ��ĩβ��O��1����
		 //���������õķ����ǣ���index�±��ӦԪ�ػ���ĩβԪ�أ�Ȼ��ֱ�ӽ�ĩβɾ������ʵ��O��1��ɾ��
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
		 
		 //432:(hard,��⼴��)�ַ�����������ѯO��1��
		 //��ϣ����ʵ�ּ���O��1�������ǲ�ѯO��1��ʵ�ֲ��ˣ������õ���˫��������ʵ�֣���LRU������
		 //�������ʵҲ����(�������ܴ�С����߼��ܶ࣬����ûʲô�ѵ��㷨)�����ǹ�ϣ��λ+˫������ά��˳���ѯ
		 //��ϣ���ţ�key�ַ�,value�ڵ㣻˫������Ľڵ㣬����һ������count,ǰ��ָ�룬�Լ�����Ϊ��ǰֵ���ַ������ϣ���Set
		 //���Ӽ������ȿ���ϣ������û�У�����У���λ���ڵ㣬��ΪҪ+1���Ǿ���ζ�ű����ַ���Ҫ�ӵ�ǰ�ڵ��ƶ���count+1�Ľڵ㴦��
		 //����Ҫ�ж�count+1�ڵ��Ƿ���ڣ������Ǿ�ֱ�ӷ����Ӧ��set�������ھ���Ҫ�ڱ��νڵ��������һ��count+1�ڵ����
		 //�����ϣ���б������ڣ�Ҳ����count��0��1���Ǿ�ֱ���ڿ�ͷ����һ��Ϊ1�ļ����ڵ�
		 //���ټ��������������ƣ������ж�count-1�Ƿ���ڣ�����ֱ�ӷ��룬��������Ҫ�½�������
		 //�����Сֵ���Ǿ�������Ŀ�ͷ�ͽ�β
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
		 
		 //716����VIP�����ջ
		 //�ⷨ1������Сջһ������˫ջ����ά��һ����ǰ��Ӧֵ�����ջ�����Ӷ����ΪO��n�������˸��������ֵ��
		 //�ⷨ2��TreeMap+˫������̫�����ˣ����ﲻд��
		 //˫ջ����ά��һ����ͨջ��һ��ֻ�浱ǰԪ�ض�Ӧ�����ֵ��ջ�����Ҫɾ�����ֵ���Ǿ��ȰѲ������ֵ���ó�����Ȼ��ɾ�����ֵ���ٷŻ�ȥ������������̣�
		 //155��Сջ��û�е�����Сֵ�Ĳ�����������O��1��.
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
		 
		 //��������+5����Ϊ�����VIP��û�취д��ȥ
		 
		 //�������ݿ����Ŀ��һ��287����Ŀ
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
