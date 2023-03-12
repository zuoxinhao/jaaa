package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

//���Գ��ָ��ʺܴ����Գ��ָ��ʺ�С
public class searchMethod {
	
	//������ȼ�practiceSearch,���ݷ��ܽ��practiceBackTracking
	//��������ܽ᣺
	//1. Queue�ķ�����add--remove, offer--poll.��������offerֻ�᷵��true��false,add�����׳��쳣
	//2. *****�ǳ������˼·�⣺934������룬��DFS��BFS���ϵ���Ŀ������DFS�ҵ���һ�����첢��¼�õ�����Χ�ĵ�һ��ˮ��
	//����һ��ˮ�����������У�Ȼ��ʼBFS��һ��һ������ĸ�������չ��ÿ����չһ�����+1���ɣ�ֱ���ҵ���һ������
    //3. ��С�߶�������������Ϊ1�Ľڵ㣬��ͣ����������(ÿ��ֻ������Ϊ1��)����������������С�߶ȡ�BFS
	
	
	
	//*****��������ص��ܽ᣺��ͳһת��ͼ���⣬��ת��Ϊģ��---����Ŀ��
	//���������������--------��ת��Ϊͼ----�ڵ�+��
	//�磺695��תΪͼ��m*n���ڵ㣬ÿ���ڵ���������ָ���������ң�547��n���ڵ㣬ÿ���ڵ����n���ߣ�����1һ����ָ���Լ�
	//������ȣ�������Ҫ����ÿ���ڵ㣬���������������ϱ�ǣ������ݹ飻
	//��������dfs�ݹ�ִ�У��ݹ�Ŀ�������ÿ���ߣ��������ıߡ�
	
	//�������������:��������˼ά�ұ��ҽڵ㣬���ϣ�һ������˼ά������������Ŀ
	
	
	//695:���������������ݷ�ģ��
	//**�������ΪʲôҪ��ջ������������޷��������½��еĽڵ�ʱ��ֱ�ӵ���top������ʵ�ֻ��ݵ����ڵ㣬���������ڽڵ�ѹ��ջ��
	//����������ʵ������˳��ÿ�μ�¼top�ڵ㣨�Ҳ����ظ���¼������ǣ����ɡ�
	//��������¼���п��ܿ����ݹ�ĵ㣬��������д�ݹ飬����ģ�壬�ݹ����п�����====����ջ��д��ѹ�����п��ܣ�������������ѹ�루ֱ��ջΪ�գ���
	public int maxAreaOfIsland(int[][] grid) {                                            //�����grid���Ե������
		if(grid.length==0||grid[0].length==0) return 0;
		int row=grid.length;
		int column=grid[0].length;
		int maxArea=0;
		for(int i=0;i<row;i++) {
			for(int j=0;j<column;j++) {
				if(grid[i][j]==1) {
					maxArea=Math.max(maxArea, dfs(grid,i,j));
				}
			}
		}
		return maxArea;
    }
	
	public int dfs(int[][] grid,int i,int j) {                                              //ģ�壺
		if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0) return 0;            //�߽������ͱ����Ϊ�ݹ����        
		grid[i][j]=0;                                                                       //״̬��ǣ��޸ı��
		int area=1;
		area+=dfs(grid,i-1,j)+dfs(grid,i+1,j)+dfs(grid,i,j-1)+dfs(grid,i,j+1);              //�������еݹ���һ���Ŀ����ԣ�����forҲ�ɲ���
		return area;
	}
	//**�ص����ѣ�����������areaÿ�����ö���1������Ϊÿ�εݹ鶼ֻ�ǵ�����+1��Ȼ��ֵ������dfs����ֵ���棬�ݹ鵽���ʱ��area==dfs����ֵ���ܴ��ֵ��Ȼ��ݹ�
	//������һ�㣬����ֵ��
	
	//*****�ص��ص��⣬�͵����������ֿ������ڽӾ���洢����ͨ��������
	//547��ʡ�ݷֲ���ת��ͳһ��ͼ����:n�ڵ㣬ÿ��n����
	 public int findCircleNum(int[][] isConnected) {
		 if(isConnected.length==0) return 0;
		 boolean[] visited=new boolean[isConnected.length];                                   //״̬��ǣ�����ֱ����ԭ���鲻����
		 int count=0;
		 for(int i=0;i<isConnected.length;i++) {
			 visited[i]=false;
		 }
		 for(int n=0;n<isConnected.length;n++) {                                              //�ڵ㿪���ݹ�
			 if(visited[n]!=true) {
				 dfs(isConnected,n,visited);
				 count++;
			 }	 
		 }
		 return count;
	    }
	 
	 public void dfs(int[][] isConnected,int n,boolean[] visited) {
		 if(visited[n]==true) return;                                                         //�߽�����
		 visited[n]=true;                                                                     //�޸ı��
		 for(int k=0;k<isConnected.length;k++) {                                              //�ݹ�����ԣ���
			 if(visited[k]==false&&isConnected[n][k]==1) {                                    //�ݹ�����
				 dfs(isConnected,k,visited);
			 }
		 }
	 }
	 
	 //417��̫ƽ������
	 //����ⷨ�����ж�����ֵ��
	 //ע��ݹ���ܳ�������ѭ��ջ�����A--B��B---A�������Ҫ���
	 //���ֵļ���bug��
	 //����ѭ��������Ϊû�б�����飻
	 //����Խ�磬����ñ߽�������         --------------------��Ҫ�ǵݹ�ǰ�и�if�жϻᵼ���������Ȼ�������
	 //�жϳ������Ҫ�����жϣ���Ȼ�ᱻ��һ�εĸ���ֵ��
	 public static List<List<Integer>> pacificAtlantic(int[][] heights) {
		 List<List<Integer>> result=new ArrayList<>();
		 boolean[][] visitedPac=new boolean[heights.length][heights[0].length]; 
		 boolean[][] visitedAla=new boolean[heights.length][heights[0].length];
		 for(int i=0;i<heights.length;i++) {
			 for(int j=0;j<heights[0].length;j++) {
				 visitedPac[i][j]=false;                                                       //�����ж��ܷ����̫ƽ�󣬴�����
				 visitedAla[i][j]=false;
				 }
			 }
			 
		 for(int i=0;i<heights.length;i++) {
			 for(int j=0;j<heights[0].length;j++) {
				 boolean[][] visited01=new boolean[heights.length][heights[0].length];
				 for(int m=0;m<heights.length;m++) {
					 for(int n=0;n<heights[0].length;n++) {                                    
						visited01[m][n]=false;                                                 //������飺ÿ�ο���dfs���Ǻ������Ľڵ㣺Ϊ�˱������2-2��2-2
						 }                                                                     //���ڸ߶���ͬ��������ѭ�����ݹ����
					 }
				 boolean[][] visited02=new boolean[heights.length][heights[0].length];
				 for(int m=0;m<heights.length;m++) {
					 for(int n=0;n<heights[0].length;n++) {
						visited02[m][n]=false;
						 }
					 }
				 dfsPac(heights,i,j,visitedPac,visited01);                                     
				 dfsAla(heights,i,j,visitedAla,visited02);
				 if(visitedPac[i][j]==true&&visitedAla[i][j]==true) {                         //һ��˫dfs�������жϣ���Ȼ�ᱻ��һ�εĸ���
					 List<Integer> list=new ArrayList<>();
					 list.add(i);
					 list.add(j);
					 result.add(list);
				 }
			 }
		 }
		 return result;
	    }
	 
	 public static boolean dfsPac(int[][] heights,int i,int j,boolean[][] visitedPac,boolean[][] visited01) {
		 if(i<=0||j<=0) {                                                                                       //����������ϣ���������̫ƽ��
			 visitedPac[i][j]=true;
			 return true;
		 };
		 if(i>=heights.length||j>=heights[0].length) return false;
		 visited01[i][j]=true;
		 if(heights[i-1][j]<=heights[i][j]&&visited01[i-1][j]==false) visitedPac[i][j]=visitedPac[i][j]||dfsPac(heights,i-1,j,visitedPac,visited01);
		 if(i+1<heights.length) {
			 if(heights[i+1][j]<=heights[i][j]&&visited01[i+1][j]==false) visitedPac[i][j]=visitedPac[i][j]||dfsPac(heights,i+1,j,visitedPac,visited01);
		 }
		 if(heights[i][j-1]<=heights[i][j]&&visited01[i][j-1]==false) visitedPac[i][j]=visitedPac[i][j]||dfsPac(heights,i,j-1,visitedPac,visited01);
		 if(j+1<heights[0].length) {
			 if(heights[i][j+1]<=heights[i][j]&&visited01[i][j+1]==false) visitedPac[i][j]=visitedPac[i][j]||dfsPac(heights,i,j+1,visitedPac,visited01);
		 }
		 
		 return visitedPac[i][j];
	 }
	 
	 public static boolean dfsAla(int[][] heights,int i,int j,boolean[][] visitedAla,boolean[][] visited02) {
		 if(i>=heights.length-1||j>=heights[0].length-1) {                                                     //�����һ����£����������
			 visitedAla[i][j]=true;
			 return true;
		 }
		 if(i<0||j<0) return false;
		 visited02[i][j]=true;
		 if(i>0) { 
			if(heights[i-1][j]<=heights[i][j]&&visited02[i-1][j]==false) visitedAla[i][j]=visitedAla[i][j]||dfsAla(heights,i-1,j,visitedAla,visited02);
			}
		 if(heights[i+1][j]<=heights[i][j]&&visited02[i+1][j]==false) visitedAla[i][j]=visitedAla[i][j]||dfsAla(heights,i+1,j,visitedAla,visited02);
		 if(j>0) {
			 if(heights[i][j-1]<=heights[i][j]&&visited02[i][j-1]==false) visitedAla[i][j]=visitedAla[i][j]||dfsAla(heights,i,j-1,visitedAla,visited02);
		 }
		 if(heights[i][j+1]<=heights[i][j]&&visited02[i][j+1]==false) visitedAla[i][j]=visitedAla[i][j]||dfsAla(heights,i,j+1,visitedAla,visited02);
		 return visitedAla[i][j];
	 }
	 
	 //�򵥺ܶ�Ľⷨ������˼ά���Ӻ������ڲ���
	 //����ⷨ������ֱ�ӣ���Ϊģ����ˮ�ᵼ���ظ�������̫�����ˣ���������ʹ�õ������ⷨ���ӱ߽絽�ɵ���Ľڵ�
	 //�൱�ڣ��ڵ��Ǿ�����ı߽ڵ㣬�߾����������Ҳ�Խ�磬�Ҵ��ڵ���ԭֵ����
	 //*****int[][] direction= {{-1,0},{1,0},{0,-1},{0,1}};����ȫ�ֱ�����ʾ��������dfs��һ��for���ɡ����������һ��
	 public int[][] direction= {{-1,0},{1,0},{0,-1},{0,1}};
	 public int m;
	 public int n;
	 int[][] heights;
	 public List<List<Integer>> pacificAtlantic01(int[][] heights){
		 this.heights=heights;
		 this.m=heights.length;
		 this.n=heights[0].length;
		 List<List<Integer>> result=new ArrayList<>();
		 boolean[][] visitedPac=new boolean[heights.length][heights[0].length]; 
		 boolean[][] visitedAla=new boolean[heights.length][heights[0].length];
		 for(int i=0;i<heights[0].length;i++) {
			 dfs(0,i,visitedPac);
		 }
		 for(int i=1;i<heights.length;i++) {
			 dfs(i,0,visitedPac);
		 }
		 for(int j=0;j<heights.length;j++) {
			 dfs(j,heights[0].length-1,visitedAla);
		 }
		 for(int j=0;j<heights[0].length-1;j++) {
			 dfs(heights.length-1,j,visitedAla);
		 }
		 for(int i=0;i<heights.length;i++) {
			 for(int j=0;j<heights[0].length;j++) {
				 if(visitedPac[i][j]==true&&visitedAla[i][j]==true) {                         
					 List<Integer> list=new ArrayList<>();
					 list.add(i);
					 list.add(j);
					 result.add(list);
				 }
			 }
		 }
		 return result;
	 }
	 
	 public void dfs(int i,int j,boolean[][] visited) {
		 if(visited[i][j]) return;                                            //visited[i][j]��ӳ䵱�˱������
		 visited[i][j]=true;
		 for(int k=0;k<4;k++) {
			 int i1=i+direction[k][0];
			 int j1=j+direction[k][1];
			 if(i1>=0&&i1<m&&j1>=0&&j1<n&&heights[i1][j1]>=heights[i][j]) {
				 dfs(i1,j1,visited);
			 }
			 }
	 }
	 
	 //*****���ݷ�������������⣺������+ģ��(������һ��ģ�壬�����߰˳ɾ͹�����)
	 //�Ȼ�����������֪depth(�ݹ�������ݹ���ڵĲ���,һ��Ϊlen)��path(һ������ӵõ������飬��·��)��used(������飬���ڷ�ֹ�ظ���ӣ�������Ϊfalse)
	 //ģ�壺������������һЩ������ֱ�ӿ���backtracking;�������ݹ飺
	 //������ģ�壺
	 //1. �ݹ���ڣ���depthΪ������������len����path�������մ���(��0�㿪ʼ��len-1,��һ�㿪ʼ��len)
	 //2. �������п��ܣ���used��ֹ�ظ���������ѭ���п�����ӣ�����path������������77����״̬�޸ģ�������backtracking�ݹ�
	 //3. ״̬�ָ���used���飬path���飬����Ҫ���ݵ�֮ǰ��״̬
	 
	 //*****���ݺ�������ȵ����ͬ��״̬�ָ������ޣ�������Ȳ���Ҫ״̬�ָ���
	 
	 //*****������ϵ���һ�֣��ַ�����ȡ�⣬���ս�ȡλ��������ڣ�������ԡ�
	 

	 //46��ȫ����
	 //���Ҫ����������д����77���У�ȥ������path���������ɣ��ǳ���
	 public static  List<List<Integer>> permute(int[] nums) {
		 List<List<Integer>> result=new ArrayList<>();
         backtracking(nums,0,result);
         return result;
	    }
	 
	 public static  void backtracking(int[] nums,int level,List<List<Integer>> result) {
		 if(level==nums.length-1) {
             List<Integer> list=Arrays.stream(nums).boxed().collect(Collectors.toList());            //ѧϰһ�£���int[] ת��ΪList<Integer>, ��Arrays.stream+װ��
             result.add(list);
			 return;
		 }
		 for(int i=level;i<nums.length;i++) {
			 swap(nums,i,level);
			 backtracking(nums,level+1,result);
			 swap(nums,i,level);
		 }
	 }

	public static  void swap(int[] nums,int i, int j) {
		// TODO Auto-generated method stub
		int p=nums[i];
		nums[i]=nums[j];
		nums[j]=p;
	}
	
	//77:������⣺���û��ݷ���������depth,path,used�����
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res=new ArrayList<>();
		if(n==0) return res;
		int[] nums=new int[n];
		for(int i=0;i<n;i++) {
			nums[i]=i+1;
		}
		boolean[] used=new boolean[n];
		List<Integer> path=new ArrayList<>();
		backtracking01(nums,k,0,path,used,res,n);
		return res;
    }
	
	public void backtracking01(int[] nums,int k,int depth,List<Integer> path,boolean[] used,List<List<Integer>> res,int n) {
		if(depth==k) {
			res.add(new ArrayList<>(path));                                                
			return;
		}
		for(int i=0;i<n;i++) {
			if(used[i]) {
				continue;
			}                                                                              //���ݷ�ģ�壺
			if(path.size()==0||nums[i]>path.get(path.size()-1)) {                          //��Ϊֻ�ܳ���12��������21���������Ʊ���������ܼ���path��
				path.add(nums[i]);                                                         //�޸�״̬������·��
				used[i]=true;
				backtracking01(nums,k,depth+1,path,used,res,n);                            //�ݹ�
				used[i]=false;                                           
				path.remove(path.size()-1);                                                //����״̬������·��
			}
		}
	}
	
	//79������������ͬ������ȣ��Ƚϼ򵥵���Ŀ
	public boolean find=false;                                       //*****д�������ǳ�Ա�������Ϳ���ֱ�ӽ���ֲ��������棬�õ����ս��
	public boolean exist(char[][] board, String word) {
		int m=board.length;
		int n=board[0].length;
		//boolean find=false;                                       *****д���ڲ����ͺ�intһ��ֻ�ǻ����������ͣ�ֻ��ֵ���ݣ������dfs79����ֻ������ֵfalse��Ȼ��GG
		boolean[][] visited=new boolean[m][n];                      
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				dfs79(board,i,j,word,0,visited);
			}
		}
		return find;
    }
	
	public void dfs79(char[][] board,int i,int j,String word,int pos,boolean[][] visited) {
		if(i<0||i>=board.length||j<0||j>=board[0].length) return;
		if(visited[i][j]||find||board[i][j]!=word.charAt(pos)) return;
		if(pos==word.length()-1) {
			find=true;
			return;
		}
		visited[i][j]=true;
		dfs79(board,i+1,j,word,pos+1,visited);
		dfs79(board,i-1,j,word,pos+1,visited);
		dfs79(board,i,j+1,word,pos+1,visited);
		dfs79(board,i,j-1,word,pos+1,visited);
		visited[i][j]=false;                                        //״̬�ָ���Ҳ����ֱ���������������ؽ�visited���飬��Ϊÿ��dfs�����޸����飬����Ҫô�ָ���Ҫô�ؽ���
	}
	
	//51����hard��̫���ˣ�N�ʺ����⣺����������չӦ�ã����͵Ļ���ģ����
	//ʵ���ϵ�Ӧ���Ǳ�����顣ֻ��������һ���Ա�Ǿ���/�����������Ǳ�ǣ���/��б�Խ�/��б�Խ��Ƿ��лʺ�;
	//��б����+��=��ֵ����б����-��=��ֵ������������ɼ�������������
	public List<List<String>> solveNQueens(int n){
		List<List<String>> solutions=new ArrayList<List<String>>();
		int[] queens=new int[n];
		Arrays.fill(queens, -1);
		Set<Integer> columns=new HashSet<>();
		Set<Integer> diagnosals1=new HashSet<>();
		Set<Integer> diagnosals2=new HashSet<>();
		backtracking51(solutions,queens,n,0,columns,diagnosals1,diagnosals2);
		return solutions;
	}
	
	public void backtracking51(List<List<String>> solutions,int[] queens,int n,int row,Set<Integer> columns,Set<Integer> diagnosals1,Set<Integer> diagnosals2) {
		if(row==n) {                                                            //���͵Ļ���ģ�壺���ڣ�����n
			List<String> board=generateBoard(queens,n);
			solutions.add(board);
		}else { 
			for(int i=0;i<n;i++) {                                              //�������п��ܵݹ�
				if(columns.contains(i)) {                                       //�ж������������
					continue;
				}
				int d1=row-i;
				if(diagnosals1.contains(d1)){
					continue;
				}
				int d2=row+i;
				if(diagnosals2.contains(d2)) {
					continue;
				}
				queens[row]=i;                                                 //�޸�״̬����ǣ�����
				columns.add(i);
				diagnosals1.add(d1);
				diagnosals2.add(d2);
				backtracking51(solutions,queens,n,row+1,columns,diagnosals1,diagnosals2);   //����
				queens[row]=-1;                                                //�ָ�״̬����ǣ�ȥ��
				columns.remove(i);
				diagnosals1.remove(d1);
				diagnosals2.remove(d2);
			}
		}
	}
	
	public List<String> generateBoard(int[] queens,int n){
		List<String> board=new ArrayList<>();
		for(int i=0;i<n;i++) {
			char[] row=new char[n];
			Arrays.fill(row, '.');
			row[queens[i]]='Q';
			board.add(new String(row));
		}
		return board;
	}
	
	
	//*****������ȣ��Ƚϼ򵥣�˵���˾���һ�����д洢����ͣ�Ľ��ӳ��ӣ���α�����
	
	
	//*****queue��ʹ�÷�������LinkedList������add��ӣ�poll����
	
	//934��������̾���
	//���ⷨ�����ã�ֱ������������ȵĽⷨ������boolean����ǣ�dfsһ�Σ��ҵ���һ�����죻Ȼ������һ�Σ��ҵ��ڶ������죻�����ҵ�������м�¼�µ㼯�ϣ�����������㼯�ϵľ��룬|x1-x2|+|y1-y2|-1ȡmin��Ϊ��
	//dfs+bfs:��������Ҹ������������
	//dfs�ҵ���һ�����죬�����ö��м�¼������ΧΪ0�ĸ��ӣ�����¼�뵺�����һ��ˮ��Ȼ����Щˮ����������ĸ�������չ�����������1���Ǿ��������˵��죻û�������Ǿͽ���������ˮ������У�������չ��
	//����Ĺ�����ȣ�����ˮ�ĸ�����һ��������չ���ö���ʵ�֡�
	public int shortestBridge(int[][] grid) {
		int m=grid.length;
		int n=grid[0].length;
		int result=0;
		boolean flag=false;
		Queue<int[]> queue=new LinkedList<>();
		for(int i=0;i<m;i++) {
			if(flag) break;                                   //��boolean��ǣ�ֻ����һ��dfs
			for(int j=0;j<n;j++) {
				if(grid[i][j]==1) {
				dfs934(grid,m,n,i,j,queue);
				flag=true;
				break;
				}
			}
		}
		
		int x,y;
		while(!queue.isEmpty()) {                                //ȷ����ѭ����ÿ����չ���жϣ����в�Ϊ�ղ��ܼ���
			result++;
			int s=queue.size();
			for(int k=0;k<s;k++) {
				int[] sample=queue.poll();
				for(int kk=0;kk<4;kk++) {
					x=sample[0]+direction[kk][0];
					y=sample[1]+direction[kk][1];
					if(x>=0&&x<m&&y>=0&&y<n) {
						if(grid[x][y]==1) return result;         //Ϊ1������ˮ��ֱ�ӷ���
						if(grid[x][y]==2) continue;              //Ϊ2.������Ϊ�˱����ظ�����ˮ�����Լ��ټ�������ֱ��continue��ȥ
						grid[x][y]=2;                            //������������Ϊ2������Ϊ�˱����ظ���������
						queue.add(new int[] {x,y});
					}
				}
			}
		}
		return 0;

    }
	
	public void dfs934(int[][] grid,int m,int n,int i,int j,Queue<int[]> queue) {
		if(i<0||i>=m||j<0||j>=n||grid[i][j]==2) return;
		if(grid[i][j]==0) {                                     //��¼�ܱߵ�ˮ
			queue.add(new int[] {i,j});
			return;
		}
		grid[i][j]=2;
		dfs934(grid,m,n,i+1,j,queue);
		dfs934(grid,m,n,i-1,j,queue);
		dfs934(grid,m,n,i,j+1,queue);
		dfs934(grid,m,n,i,j-1,queue);
	}
	
	//126����hard,˼·�����������ʽ���
    //����˼·��BFS�������������ӿ�ʼ���ʽ���һ��λ���ַ��仯��������һ�㣬Ȼ������һ��...ֱ���ҵ���ֹ����
	//��Ҫ�ؼ�������ôȷ����һ��ĵ��ʣ���һ�����һ���Ӧ�ĵ���ֻ���һ���ַ����ڵ��ʱ�����
	//�������ǵĴ����ǣ�������ĵ��ʣ������ַ��滻Ϊa-z���滻�������ڵ��ʱ����棬˵���������¼�����һ�㣬������������ֱ��ȥ��
	//ע��һ�£�һ��·�����浥�ʲ����ظ����֣�����ÿ��BFS��Ҫ��������ʾ�õ��ʱ������ˡ�
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> ans=new ArrayList<>();
		if(!wordList.contains(endWord)) {
			return ans;
		}
		bfs126(beginWord,endWord,wordList,ans);
		return ans;
	}
	
	public void bfs126(String beginWord, String endWord, List<String> wordList,List<List<String>> ans) {
		Queue<List<String>> queue=new LinkedList<>();
		List<String> path=new ArrayList<>();
		path.add(beginWord);
		queue.offer(path);
		boolean isFound=false;
		Set<String> dict=new HashSet<>(wordList);
		Set<String> visited=new HashSet<>();
		visited.add(beginWord);
		while(!queue.isEmpty()) {
			int size=queue.size();
			Set<String> subVisited=new HashSet<>();
			for(int j=0;j<size;j++) {
				List<String> p=queue.poll();
				String temp=p.get(p.size()-1);
				ArrayList<String> neighbors=getNeighbors(temp,dict);
				for(String neighbor:neighbors) {
					if(!visited.contains(neighbor)) {
						if(neighbor.equals(endWord)) {
							isFound=true;
							p.add(neighbor);
							ans.add(new ArrayList<String>(p));
							p.remove(p.size()-1);                            //�ָ�״̬��Ϊ�˽���������
						}
						p.add(neighbor);
						queue.offer(new ArrayList<String>(p));
						p.remove(p.size()-1);
						subVisited.add(neighbor);
					}
				}
			}
			visited.addAll(subVisited);
			if(isFound) {
				break;
			}
		}
		
	}
	
	public ArrayList<String> getNeighbors(String node,Set<String> dict){              //ʵ�����ڲ�ֻ��һ���ַ�
		ArrayList<String> res=new ArrayList<>();
		char[] chs=node.toCharArray();
		for(char ch='a';ch<='z';ch++) {
			for(int i=0;i<chs.length;i++) {
				if(chs[i]==ch) {
					continue;
				}
				char oldCh=chs[i];
				chs[i]=ch;
				if(dict.contains(String.valueOf(chs))) {
					res.add(String.valueOf(chs));
				}
				chs[i]=oldCh;
			}
		}
		return res;
	}
	
	//��ϰ�⣺
	
	//130������Χ������
	//����������ȵ�ģ�壬��������ࣨ�����ࣩ����ǳ�easy,һ���
	//������ȣ��ͺ�������������һ���ģ�����˼ά��������Ŀ����ʾ��Ϣ˵�ģ��������ߵ�O����������dfs�������O�����޸�ΪN����ʾ���ø�ΪX�ģ������X��return;
	//���հ�ʣ�µ�O��ΪX,��N��ΪO����Ϊ���մ𰸡��ڵ�:������ıߣ��߾����ĸ�����
	//**˼·2��ʵ�����ǵ������⣬�ҵ����죬�ҵ��첻���ٱ߽磬����������flag1��¼�Ƿ��ٱ߽磬flag2��¼�Ƿ���ʹ���Ҳ����ʵ��
	public void solve(char[][] board) {
		int m=board.length;
		int n=board[0].length;
		for(int i=0;i<m;i++) {
			if(board[i][0]=='O') {
				dfs130(board,i,0);
			}
		}
		for(int j=1;j<n;j++) {
			if(board[m-1][j]=='O') {
				dfs130(board,m-1,j);
			}
		}
		for(int j=1;j<n;j++) {
			if(board[0][j]=='O') {
				dfs130(board,0,j);
			}
		}
		for(int i=1;i<m-1;i++) {
			if(board[i][n-1]=='O') {
				dfs130(board,i,n-1);
			}
		}
		for(int row=0;row<m;row++) {
			for(int column=0;column<n;column++) {
				if(board[row][column]=='O') {
					board[row][column]='X';
				}
			}
		}
		for(int row1=0;row1<m;row1++) {
			for(int column1=0;column1<n;column1++) {
				if(board[row1][column1]=='N') {
					board[row1][column1]='O';
				}
			}
		}
    }
	
	public void dfs130(char[][] board,int i,int j) {
		if(i<0||i>=board.length||j<0||j>=board[0].length||board[i][j]=='N'||board[i][j]=='X') return;
		board[i][j]='N';
		dfs130(board,i+1,j);
		dfs130(board,i-1,j);
		dfs130(board,i,j+1);
		dfs130(board,i,j-1);
	}
	
	//257��������������·��
	//*****������Ȳ��ӻ��ݵĻ�������Ҫÿ�ζ������½���stringbuilder����ֹ�ظ��������
	//�ӻ��ݣ�Ҫע����ǻ��ݵĵ����ģ�-----ɾ���ռ�����ַ�������Ҫע�⣬��Ҷ�ӽڵ㴦�������append���룬�Ǿ͵��ٻָ�
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
	  
	  public List<String> binaryTreePaths(TreeNode root) {
		  StringBuilder cur=new StringBuilder();
		  List<String> res=new ArrayList<String>();
		  dfs257(root,cur,res);
		  return res;
	    }
	
	  public void dfs257(TreeNode root,StringBuilder cur,List<String> res) {
		  if(root==null) return;
		  if(root.left==null&&root.right==null) {
			  //res.add(cur.append(root.val).toString());                                  �����������append�޸�cur�Ļ����Ǻ�����뻹ԭ����delete����ɾ������
			  res.add(cur.toString()+root.val);
			  return;
		  }
		  int pos=cur.length();
		  cur.append(root.val).append("->");
		 // dfs257(root.left,new StringBuilder(cur),res);                                 ����ʱ��ֱ���½��������������Ͳ������cur�ظ���ʹ�ã����³���                  
		 // dfs257(root.right,new StringBuilder(cur),res);
		  dfs257(root.left,cur,res);
		  dfs257(root.right,cur,res);
		  cur.delete(pos, cur.length());                                                 //��ԭ��ɾ���±��pos��ʼ��cur.length���ַ�
	  }
	
	  //47��ȫ���У����ظ��ַ�
	  //ȥ���ظ�path�ķ�����ÿһ��λ�ã������ֵ��ֻ�ܳ���һ�Σ������ظ����롣����˵:nums=1,1,2;��һ��λ��[]�����Ƿ��˵�һ��1����ô��λ�þͲ��ܷŵڶ���1����Ϊ����[1],[1]�ظ��ᵼ�����path�ظ�
	  //����ȥ��ʵ�֣���Ϊdepth��ʾ�ľ��Ǵ�0��ʼ��ÿ��λ�ã���k����Ƿ����k��λ�õ�ֵ������ֱ����backtracking�������һ��hashset����¼�Ѿ��ڸ�λ�÷Ź���ֵ������ظ��ˣ�ֱ��continue
	  public List<List<Integer>> permuteUnique(int[] nums) {
			List<List<Integer>> res=new ArrayList<>();
			int n=nums.length;
			boolean[] used=new boolean[n];
			List<Integer> path=new ArrayList<>();
			backtracking01(nums,0,path,used,res,n);
			return res;
	    }
		
		public void backtracking01(int[] nums,int depth,List<Integer> path,boolean[] used,List<List<Integer>> res,int n) {
			if(depth==n) {
				res.add(new ArrayList<>(path));                                                
				return;
			}
			Set<Integer> hasVisited=new HashSet<>();                               //hashset���ڵ�ǰλ�õ�ȥ��
			for(int i=0;i<n;i++) {
				if(used[i]) {
					continue;
				}                                                                             
				if(hasVisited.contains(nums[i])) {                                 //hashset���ڵ�ǰλ�õ�ȥ��
					continue;
				}
				hasVisited.add(nums[i]);
				path.add(nums[i]);                                                         					
				used[i]=true;
				backtracking01(nums,depth+1,path,used,res,n);                            
				used[i]=false;                                           
				path.remove(path.size()-1);                                                
			}
		}
		
		//40�������ͣ�
		//��������Ŀ��˼·���ı��˵ݹ���ڣ����ҵݹ����һ��������¼��ǰ��ֵ
		//������ͬ��
		//�ݹ���ڴӲ���������˺����
		//����hashsetȥ��
		//ÿ�μ�¼��ֵ�����Ҹ�һ���±���rs�����һֱ��sum�ᵼ���ظ�����
		public List<List<Integer>> combinationSum2(int[] candidates, int target) {
				List<List<Integer>> res=new ArrayList<>();
			    Arrays.sort(candidates);                                                                  //������ת��Ϊ�������
				boolean[] used=new boolean[candidates.length];
				List<Integer> path=new ArrayList<>();
				backtracking40(candidates,target,path,used,res,0);
				return res;
		}
			
			public void backtracking40(int[] candidates,int target,List<Integer> path,boolean[] used,List<List<Integer>> res,int sum) {
				if(sum==target) {                                                                         //�ݹ���ڱ���˺͵�
					res.add(new ArrayList<>(path));                                                
					return;
				}
				Set<Integer> hasVisited=new HashSet<>();                                                 
				for(int i=0;i<candidates.length;i++) {
					if(used[i]) {
						continue;
					}      
					if(path.size()==0||candidates[i]>=path.get(path.size()-1)) {
						if(hasVisited.contains(candidates[i])) {                                             
							continue;
						}
						hasVisited.add(candidates[i]);
						int rs=sum+candidates[i];
						if(rs<=target) {                                                                 //<=,����ҲҪ������һ��ݹ���ܳ�ȥ�����Ա����˵��ں�
							path.add(candidates[i]);                                                         
							used[i]=true;
							backtracking40(candidates,target,path,used,res,rs);                            
							used[i]=false;                                           
							path.remove(path.size()-1);      
						}else {
							break;
						}
					}
					
               }
			}
			
			//37����hard��̫���ˣ���������
			//��n�ʺ�������һ��˼�룬�����У��У��Ź�������ͬ��Ҳ������������飬��϶�������������
			//���������n�ʺ�������Ҫ�����ŵĴ洢�ã�Ȼ�����pos��������д����д����������Ϊfalse
			boolean[][] line=new boolean[9][9];                                     //[][][],���һ��[]��������ֵ�ֵ-1
			boolean[][] column=new boolean[9][9];
			boolean[][][] block=new boolean[3][3][9];
			boolean valid=false;
			List<int[]> spaces=new ArrayList<>();
			public void solveSudoku(char[][] board) {
				for(int i=0;i<9;i++) {
					for(int j=0;j<9;j++){
						if(board[i][j]=='.') {                                     //Ϊ�գ���¼���������ں�����д
							spaces.add(new int[] {i,j});
						}else {
							int digit=board[i][j]-'0'-1;                           //��¼��ֵ
							line[i][digit]=true;
							column[j][digit]=true;
							block[i/3][j/3][digit]=true;
						}
					}
				}
				sudoku(board,0);
			}
			
			public void sudoku(char[][] board,int pos) {
				if(pos==spaces.size()) {
					valid=true;
					return;
				}
				int[] point=spaces.get(pos);
				int i=point[0];
				int j=point[1];
				for(int digit=0;digit<9&&!valid;digit++) {                                     //0-8ͨ��+1���ο����ܷ�����
					if(!line[i][digit]&&!column[j][digit]&&!block[i/3][j/3][digit]) {
						board[i][j]=(char)(digit+'0'+1);
						line[i][digit]=column[j][digit]=block[i/3][j/3][digit]=true;
						sudoku(board,pos+1);
						line[i][digit]=column[j][digit]=block[i/3][j/3][digit]=false;
					}
				}
			}
			
			//310����С�߶������ҵĽⷨĥ���˻���Ӧ�ã���Ȼ��ʱ����������ȷ�Ļ��ݽⷨ��
			//�����нڵ㿪��dfs��ÿ��dfs��¼�±��������û�нڵ�ɱ�������·�����ϣ�����Щ·��������ȡmax��Ϊ���ڵ�����
			//��������нڵ����ȼ�������С���Ǹ�����Ϊ�𰸡�
			//���ǽڵ㣬���ǿ����öԳ��������洢����������ǽڵ�ͷǳ�easy�ˡ�
		    //path��һ��·����res�����е�·����
			public List<Integer> findMinHeightTrees01(int n, int[][] edges) {
                 int[][] edge=new int[n][n];
                 for(int k=0;k<edges.length;k++) {                              //����ͼ��ת��Ϊ�Գƾ��������
                	 int i=edges[k][0];
                	 int j=edges[k][1];
                	 edge[j][i]=edge[i][j]=1;
                 }
                 int[] height=new int[n];
                 for(int m=0;m<n;m++) {                                         //��ÿ���ڵ㶼dfs
                	 boolean[] used=new boolean[n];                             //������飬����һ�α����ı�ǣ���ֹ�ظ�
                	 List<Integer> path=new ArrayList<>();                      //path�浥�α�����û�нڵ�ɱ�����·��
                	 List<List<Integer>> res=new ArrayList<>();                 //resΪ·������
                	 path.add(m);                                               //���α������ȼ��뱾�ڵ㲢���ñ��ڵ�Ϊtrue
                	 used[m]=true;
                	 dfs310(edge,m,path,res,used);                              //����dfs,��Ҫ��Ϊ�˵õ�res
                	 int len=res.size();
                	 for(int k1=0;k1<len;k1++) {                                //��res�����·��
                		 height[m]=Math.max(height[m], res.get(k1).size());
                	 }
                 }
                 List<Integer> result=new ArrayList<>();
                 int minHeight=height[0];
                 for(int i1=0;i1<n;i1++) {                                      //�����нڵ��·������Сֵ
                	 if(height[i1]<minHeight) {
                		 result.clear();
                		 result.add(i1);
                		 minHeight=height[i1];
                	 }else if(height[i1]==minHeight) {
                		 result.add(i1);
                	 }
                 }
                 return result;   
			    }
			
			public void dfs310(int[][] edge,int m,List<Integer> path,List<List<Integer>> res,boolean[] used) {
				for(int j1=0;j1<edge.length;j1++) {                              //�������п���
					if(j1==edge.length-1&&(edge[m][j1]==0||used[j1]==true)) {    //������б��������һ�У���ȻΪ0���������һ���ڵ�Ϊtrue����û��·�����������ˣ����Լ���res
						res.add(new ArrayList<Integer>(path));
						return;
					}
					if(edge[m][j1]==1&&used[j1]==false) {                        //�����1���бߣ���δ�����ʹ�������Եݹ�
						path.add(j1);                                            //����path���޸�״̬
						used[j1]=true;
						dfs310(edge,j1,path,res,used);                           //�ݹ�
						used[j1]=false;                                          //״̬�ָ���ʹ��path��used��������
						path.remove(path.size()-1);
					}
				}
			}
			
			//����ⷨ��BFS
			//ͼ���м�Ľڵ㣬���Ǹ߶�min�Ľڵ�
			//�������Ǵ�����Χ����Ϊ1��Ҷ�ӽڵ㿪ʼ�����������������������Ǹ߶�min�����м�Ľڵ�
			//��Ҫ��¼�ľ��ǶȺ����ڽڵ�
			public List<Integer> findMinHeightTrees(int n, int[][] edges){
				List<Integer> res=new ArrayList<>();
				if(n==1) {
					res.add(0);
					return res;
				}
				int[] degree=new int[n];                                             //��ÿ���ڵ�Ķȣ������ҵ���Ϊ1�ģ�ÿ�ζ�����;�±���ǽڵ�
				List<List<Integer>> map=new ArrayList<>();                           //map������ÿ���ڵ�����ڽڵ㣬���ڹ�ϵ
				for(int i=0;i<n;i++) {
					map.add(new ArrayList<>());
				}
				for(int[] edge:edges) {
					degree[edge[0]]++;
					degree[edge[1]]++;
					map.get(edge[0]).add(edge[1]);
					map.get(edge[1]).add(edge[0]);
				}
				Queue<Integer> queue=new LinkedList<>();                             //BFS���У���1���ӣ�Ȼ�����ڽڵ��-1��������˶�Ϊ1�������
				for(int i=0;i<n;i++) {
					if(degree[i]==1) {
						queue.offer(i);
					}
				}
				while(!queue.isEmpty()) {
					res=new ArrayList<>();                                           //ÿ��һ���µ�res��res��ľ���Ҷ�ӽڵ㣬���һ�δ��Ҷ�ӽڵ���Ǵ�
					int size=queue.size();
					for(int i=0;i<size;i++) {
						int cur=queue.poll();
						res.add(cur);        
						List<Integer> neighbors=map.get(cur);
						for(int neighbor:neighbors) {
							degree[neighbor]--;
							if(degree[neighbor]==1) {
								queue.offer(neighbor);
							}
						}
					}
				}
				return res;	
			}
			
			
			

	public static void main(String[] args) {
		int[][] edges= {{1,0}};
		System.out.print(2);
	}
	 

	
	
	
	
	
	
	
	
	
	
	
	
	

}

