package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

//机试出现概率很大，面试出现概率很小
public class searchMethod {
	
	//深度优先见practiceSearch,回溯法总结见practiceBackTracking
	//广度优先总结：
	//1. Queue的方法：add--remove, offer--poll.两者区别：offer只会返回true和false,add队满抛出异常
	//2. *****非常巧妙的思路题：934岛屿距离，把DFS和BFS相结合的题目，先用DFS找到第一个岛屿并记录该岛屿周围的第一层水，
	//将第一层水的坐标放入队列，然后开始BFS，一层一层的向四个方向扩展，每可扩展一层距离+1即可，直至找到另一个岛屿
    //3. 最小高度树：从最外层度为1的节点，不停的向内收缩(每次只收缩度为1的)，收缩到最后就是最小高度。BFS
	
	
	
	//*****深度优先重点总结：先统一转化图问题，后转化为模板---见题目中
	//遍历搜索结点问题--------先转化为图----节点+边
	//如：695，转为图，m*n个节点，每个节点有四条边指向上下左右；547：n个节点，每个节点最多n条边，最少1一条边指向自己
	//深度优先：主函数要遍历每个节点，（加上条件，加上标记）开启递归；
	//辅函数，dfs递归执行，递归的可能性是每条边，即上述的边。
	
	//深度优先三道题:两个正向思维找边找节点，如上；一个逆向思维，倒过来简化题目
	
	
	//695:岛屿最大面积：回溯法模板
	//**深度优先为什么要用栈？方便遍历到无法继续向下进行的节点时，直接弹出top，即可实现回溯到父节点，继续找相邻节点压入栈。
	//深度优先如何实现搜索顺序？每次记录top节点（且不可重复记录，做标记）即可。
	//主函数记录所有可能开启递归的点，辅函数内写递归，套用模板，递归所有可能性====利用栈来写，压入所有可能，弹出顶部，再压入（直至栈为空）。
	public int maxAreaOfIsland(int[][] grid) {                                            //本题的grid可以当标记用
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
	
	public int dfs(int[][] grid,int i,int j) {                                              //模板：
		if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0) return 0;            //边界条件和标记作为递归出口        
		grid[i][j]=0;                                                                       //状态标记，修改标记
		int area=1;
		area+=dfs(grid,i-1,j)+dfs(grid,i+1,j)+dfs(grid,i,j-1)+dfs(grid,i,j+1);              //遍历所有递归下一步的可能性，可用for也可不用
		return area;
	}
	//**重点提醒：辅函数里面area每次设置都是1，是因为每次递归都只是单纯的+1，然后值存在了dfs返回值里面，递归到最深处时，area==dfs返回值，很大的值，然后递归
	//返回上一层，逐层加值。
	
	//*****重点重点题，和岛屿数量区分开来，邻接矩阵存储找连通分量的题
	//547：省份分布：转化统一的图问题:n节点，每个n条边
	 public int findCircleNum(int[][] isConnected) {
		 if(isConnected.length==0) return 0;
		 boolean[] visited=new boolean[isConnected.length];                                   //状态标记，本题直接用原数组不好用
		 int count=0;
		 for(int i=0;i<isConnected.length;i++) {
			 visited[i]=false;
		 }
		 for(int n=0;n<isConnected.length;n++) {                                              //节点开启递归
			 if(visited[n]!=true) {
				 dfs(isConnected,n,visited);
				 count++;
			 }	 
		 }
		 return count;
	    }
	 
	 public void dfs(int[][] isConnected,int n,boolean[] visited) {
		 if(visited[n]==true) return;                                                         //边界条件
		 visited[n]=true;                                                                     //修改标记
		 for(int k=0;k<isConnected.length;k++) {                                              //递归可能性，边
			 if(visited[k]==false&&isConnected[n][k]==1) {                                    //递归条件
				 dfs(isConnected,k,visited);
			 }
		 }
	 }
	 
	 //417：太平洋问题
	 //正向解法：很有锻炼价值；
	 //注意递归可能出现无限循环栈溢出：A--B，B---A。溢出：要标记
	 //出现的几个bug：
	 //无限循环，是因为没有标记数组；
	 //数组越界，定义好边界条件；         --------------------主要是递归前有个if判断会导致溢出，不然不用这个
	 //判断成立与否，要立刻判断，不然会被下一次的覆盖值。
	 public static List<List<Integer>> pacificAtlantic(int[][] heights) {
		 List<List<Integer>> result=new ArrayList<>();
		 boolean[][] visitedPac=new boolean[heights.length][heights[0].length]; 
		 boolean[][] visitedAla=new boolean[heights.length][heights[0].length];
		 for(int i=0;i<heights.length;i++) {
			 for(int j=0;j<heights[0].length;j++) {
				 visitedPac[i][j]=false;                                                       //用于判断能否进入太平洋，大西洋
				 visitedAla[i][j]=false;
				 }
			 }
			 
		 for(int i=0;i<heights.length;i++) {
			 for(int j=0;j<heights[0].length;j++) {
				 boolean[][] visited01=new boolean[heights.length][heights[0].length];
				 for(int m=0;m<heights.length;m++) {
					 for(int n=0;n<heights[0].length;n++) {                                    
						visited01[m][n]=false;                                                 //标记数组：每次开启dfs后标记好流过的节点：为了避免出现2-2，2-2
						 }                                                                     //相邻高度相同导致无限循环，递归溢出
					 }
				 boolean[][] visited02=new boolean[heights.length][heights[0].length];
				 for(int m=0;m<heights.length;m++) {
					 for(int n=0;n<heights[0].length;n++) {
						visited02[m][n]=false;
						 }
					 }
				 dfsPac(heights,i,j,visitedPac,visited01);                                     
				 dfsAla(heights,i,j,visitedAla,visited02);
				 if(visitedPac[i][j]==true&&visitedAla[i][j]==true) {                         //一次双dfs，立刻判断，不然会被下一次的覆盖
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
		 if(i<=0||j<=0) {                                                                                       //到达左或者上，即可流入太平洋
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
		 if(i>=heights.length-1||j>=heights[0].length-1) {                                                     //到达右或者下，流入大西洋
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
	 
	 //简单很多的解法：逆向思维，从海洋往内部进
	 //反向解法：更简单直接，因为模拟雨水会导致重复遍历，太复杂了，所以我们使用倒过来解法，从边界到可到达的节点
	 //相当于：节点是矩阵的四边节点，边就是上下左右不越界，且大于等于原值即可
	 //*****int[][] direction= {{-1,0},{1,0},{0,-1},{0,1}};利用全局变量表示方向，所以dfs中一个for即可。这样更简洁一点
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
		 if(visited[i][j]) return;                                            //visited[i][j]间接充当了标记数组
		 visited[i][j]=true;
		 for(int k=0;k<4;k++) {
			 int i1=i+direction[k][0];
			 int j1=j+direction[k][1];
			 if(i1>=0&&i1<m&&j1>=0&&j1<n&&heights[i1][j1]>=heights[i][j]) {
				 dfs(i1,j1,visited);
			 }
			 }
	 }
	 
	 //*****回溯法：排列组合问题：生成树+模板(掌握这一种模板，掌握七八成就够用了)
	 //先画生成树：可知depth(递归层数，递归出口的层数,一般为len)，path(一步步添加得到的数组，即路径)，used(标记数组，用于防止重复添加，声明即为false)
	 //模板：主函数声明完一些变量，直接开启backtracking;辅函数递归：
	 //辅函数模板：
	 //1. 递归出口：即depth为生成树层数，len；将path加入最终答案中(第0层开始到len-1,第一层开始到len)
	 //2. 遍历所有可能，用used防止重复遍历；在循环中可以添加，加入path的条件，如题77（即状态修改），开启backtracking递归
	 //3. 状态恢复，used数组，path数组，都需要回溯到之前的状态
	 
	 //*****回溯和深度优先的最大不同：状态恢复的有无，深度优先不需要状态恢复。
	 
	 //*****排列组合的另一种：字符串截取题，按照截取位置来算出口，算可能性。
	 

	 //46：全排列
	 //如果要用生成树来写：在77题中，去掉加入path的条件即可，非常简单
	 public static  List<List<Integer>> permute(int[] nums) {
		 List<List<Integer>> result=new ArrayList<>();
         backtracking(nums,0,result);
         return result;
	    }
	 
	 public static  void backtracking(int[] nums,int level,List<List<Integer>> result) {
		 if(level==nums.length-1) {
             List<Integer> list=Arrays.stream(nums).boxed().collect(Collectors.toList());            //学习一下：从int[] 转化为List<Integer>, 用Arrays.stream+装箱
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
	
	//77:组合问题：利用回溯法生成树的depth,path,used来求解
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
			}                                                                              //回溯法模板：
			if(path.size()==0||nums[i]>path.get(path.size()-1)) {                          //因为只能出现12，不能有21，所以限制必须递增才能加入path中
				path.add(nums[i]);                                                         //修改状态，加入路径
				used[i]=true;
				backtracking01(nums,k,depth+1,path,used,res,n);                            //递归
				used[i]=false;                                           
				path.remove(path.size()-1);                                                //回溯状态，消除路径
			}
		}
	}
	
	//79：单词搜索：同深度优先，比较简单的题目
	public boolean find=false;                                       //*****写在外面是成员变量，就可以直接进入局部函数里面，得到最终结果
	public boolean exist(char[][] board, String word) {
		int m=board.length;
		int n=board[0].length;
		//boolean find=false;                                       *****写在内部，就和int一样只是基本数据类型，只有值传递，最后导致dfs79里面只传入了值false，然后GG
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
		visited[i][j]=false;                                        //状态恢复，也可以直接在主函数里面重建visited数组，因为每次dfs都会修改数组，所以要么恢复。要么重建。
	}
	
	//51：（hard，太难了）N皇后问题：标记数组的扩展应用，典型的回溯模板题
	//实际上的应用是标记数组。只不过不是一次性标记经过/不经过，而是标记，列/左斜对角/右斜对角是否有皇后;
	//左斜：行+列=定值；右斜：行-列=定值；利用这个规律检查三个标记数组
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
		if(row==n) {                                                            //典型的回溯模板：出口，层数n
			List<String> board=generateBoard(queens,n);
			solutions.add(board);
		}else { 
			for(int i=0;i<n;i++) {                                              //遍历所有可能递归
				if(columns.contains(i)) {                                       //判断三个标记数组
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
				queens[row]=i;                                                 //修改状态，标记，加入
				columns.add(i);
				diagnosals1.add(d1);
				diagnosals2.add(d2);
				backtracking51(solutions,queens,n,row+1,columns,diagnosals1,diagnosals2);   //回溯
				queens[row]=-1;                                                //恢复状态，标记，去除
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
	
	
	//*****广度优先：比较简单，说白了就是一个队列存储，不停的进队出队，层次遍历。
	
	
	//*****queue的使用方法：用LinkedList声明，add添加，poll出队
	
	//934：岛屿最短距离
	//笨解法，不好：直接两次深度优先的解法：先用boolean做标记，dfs一次，找到第一个岛屿；然后再来一次，找到第二个岛屿；两次找岛屿过程中记录下点集合；最后求两个点集合的距离，|x1-x2|+|y1-y2|-1取min即为答案
	//dfs+bfs:更巧妙，而且更简单且容易理解
	//dfs找到第一个岛屿，我们用队列记录岛屿周围为0的格子，即记录离岛屿最近一层水，然后将这些水广度优先向四个方向扩展，如果碰到了1，那就是碰到了岛屿；没碰到，那就将遍历到的水放入队列，继续扩展。
	//这题的广度优先，就是水四个方向一起向外扩展，用队列实现。
	public int shortestBridge(int[][] grid) {
		int m=grid.length;
		int n=grid[0].length;
		int result=0;
		boolean flag=false;
		Queue<int[]> queue=new LinkedList<>();
		for(int i=0;i<m;i++) {
			if(flag) break;                                   //用boolean标记，只进行一次dfs
			for(int j=0;j<n;j++) {
				if(grid[i][j]==1) {
				dfs934(grid,m,n,i,j,queue);
				flag=true;
				break;
				}
			}
		}
		
		int x,y;
		while(!queue.isEmpty()) {                                //确保大循环，每次扩展后判断，队列不为空才能继续
			result++;
			int s=queue.size();
			for(int k=0;k<s;k++) {
				int[] sample=queue.poll();
				for(int kk=0;kk<4;kk++) {
					x=sample[0]+direction[kk][0];
					y=sample[1]+direction[kk][1];
					if(x>=0&&x<m&&y>=0&&y<n) {
						if(grid[x][y]==1) return result;         //为1，碰到水，直接返回
						if(grid[x][y]==2) continue;              //为2.这里是为了避免重复计算水，可以减少计算量，直接continue出去
						grid[x][y]=2;                            //遍历过的设置为2，就是为了避免重复添加入队列
						queue.add(new int[] {x,y});
					}
				}
			}
		}
		return 0;

    }
	
	public void dfs934(int[][] grid,int m,int n,int i,int j,Queue<int[]> queue) {
		if(i<0||i>=m||j<0||j>=n||grid[i][j]==2) return;
		if(grid[i][j]==0) {                                     //记录周边的水
			queue.add(new int[] {i,j});
			return;
		}
		grid[i][j]=2;
		dfs934(grid,m,n,i+1,j,queue);
		dfs934(grid,m,n,i-1,j,queue);
		dfs934(grid,m,n,i,j+1,queue);
		dfs934(grid,m,n,i,j-1,queue);
	}
	
	//126：（hard,思路很清晰）单词接龙
    //核心思路：BFS，画生成树，从开始单词进行一个位置字符变化，开启下一层，然后再下一层...直到找到终止单词
	//主要关键点是怎么确定下一层的单词：下一层和上一层对应的单词只相差一个字符且在单词表里面
	//所以我们的处理是：将本层的单词，单个字符替换为a-z，替换完后如果在单词表里面，说明可以留下继续下一层，保留；不在则直接去掉
	//注意一下，一条路径里面单词不能重复出现，所以每条BFS需要标记数组表示该单词遍历过了。
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
							p.remove(p.size()-1);                            //恢复状态，为了接下来继续
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
	
	public ArrayList<String> getNeighbors(String node,Set<String> dict){              //实现相邻层只差一个字符
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
	
	//练习题：
	
	//130：被包围的区域
	//根据深度优先的模板，解决岛屿类（网格类）问题非常easy,一遍过
	//深度优先，和海洋流出问题是一样的，逆向思维，根据题目的提示信息说的，从四条边的O向内流开启dfs，如果是O，就修改为N，表示不用改为X的；如果是X，return;
	//最终把剩下的O变为X,将N变为O，即为最终答案。节点:矩阵的四边，边就是四个方向
	//**思路2：实际上是岛屿问题，找到岛屿，且岛屿不能临边界，所以我们用flag1记录是否临边界，flag2记录是否访问过，也可以实现
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
	
	//257：二叉树的所有路径
	//*****深度优先不加回溯的话，就需要每次都传入新建的stringbuilder，防止重复加入出错
	//加回溯，要注意的是回溯的点在哪？-----删除刚加入的字符，而且要注意，在叶子节点处，如果用append加入，那就得再恢复
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
			  //res.add(cur.append(root.val).toString());                                  如果这里用了append修改cur的话，那后面必须还原，用delete立马删掉才行
			  res.add(cur.toString()+root.val);
			  return;
		  }
		  int pos=cur.length();
		  cur.append(root.val).append("->");
		 // dfs257(root.left,new StringBuilder(cur),res);                                 传参时候，直接新建并拷贝，这样就不会出现cur重复被使用，导致出错                  
		 // dfs257(root.right,new StringBuilder(cur),res);
		  dfs257(root.left,cur,res);
		  dfs257(root.right,cur,res);
		  cur.delete(pos, cur.length());                                                 //还原，删除下标从pos开始到cur.length的字符
	  }
	
	  //47：全排列，可重复字符
	  //去除重复path的方法：每一个位置，放入的值，只能出现一次，不能重复放入。比如说:nums=1,1,2;第一个位置[]，我们放了第一个1，那么该位置就不能放第二个1，因为这样[1],[1]重复会导致最后path重复
	  //核心去重实现：因为depth表示的就是从0开始的每个位置，第k层就是放入第k个位置的值，所以直接在backtracking里面加入一个hashset，记录已经在该位置放过的值，如果重复了，直接continue
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
			Set<Integer> hasVisited=new HashSet<>();                               //hashset用于当前位置的去重
			for(int i=0;i<n;i++) {
				if(used[i]) {
					continue;
				}                                                                             
				if(hasVisited.contains(nums[i])) {                                 //hashset用于当前位置的去重
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
		
		//40：组合求和：
		//借鉴组合题目的思路：改变了递归出口，并且递归多了一个变量记录当前和值
		//几个不同：
		//递归出口从层数，变成了和相等
		//利用hashset去重
		//每次记录和值，并且给一个新变量rs，如果一直用sum会导致重复出错
		public List<List<Integer>> combinationSum2(int[] candidates, int target) {
				List<List<Integer>> res=new ArrayList<>();
			    Arrays.sort(candidates);                                                                  //先排序，转化为组合问题
				boolean[] used=new boolean[candidates.length];
				List<Integer> path=new ArrayList<>();
				backtracking40(candidates,target,path,used,res,0);
				return res;
		}
			
			public void backtracking40(int[] candidates,int target,List<Integer> path,boolean[] used,List<List<Integer>> res,int sum) {
				if(sum==target) {                                                                         //递归出口变成了和等
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
						if(rs<=target) {                                                                 //<=,等于也要进入下一层递归才能出去，所以别忘了等于号
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
			
			//37：（hard，太难了）数独问题
			//和n皇后问题是一个思想，本题行，列，九宫格不能相同，也是三个标记数组，相较而言数独更复杂
			//数独问题比n皇后复杂在需要将空着的存储好，然后根据pos来进行填写，填写依据是三个为false
			boolean[][] line=new boolean[9][9];                                     //[][][],最后一个[]代表该数字的值-1
			boolean[][] column=new boolean[9][9];
			boolean[][][] block=new boolean[3][3][9];
			boolean valid=false;
			List<int[]> spaces=new ArrayList<>();
			public void solveSudoku(char[][] board) {
				for(int i=0;i<9;i++) {
					for(int j=0;j<9;j++){
						if(board[i][j]=='.') {                                     //为空，记录下来，用于后面填写
							spaces.add(new int[] {i,j});
						}else {
							int digit=board[i][j]-'0'-1;                           //记录数值
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
				for(int digit=0;digit<9&&!valid;digit++) {                                     //0-8通过+1依次看看能否填入
					if(!line[i][digit]&&!column[j][digit]&&!block[i/3][j/3][digit]) {
						board[i][j]=(char)(digit+'0'+1);
						line[i][digit]=column[j][digit]=block[i/3][j/3][digit]=true;
						sudoku(board,pos+1);
						line[i][digit]=column[j][digit]=block[i/3][j/3][digit]=false;
					}
				}
			}
			
			//310：最小高度树：我的解法磨练了回溯应用，虽然超时，但是是正确的回溯解法。
			//对所有节点开启dfs，每个dfs记录下遍历到最后（没有节点可遍历）的路径集合，在这些路径集合中取max即为本节点的深度
			//最后求所有节点的深度集合里最小的那个，即为答案。
			//不是节点，但是可以用对称数组来存储；如果给的是节点就非常easy了。
		    //path存一次路径，res存所有的路径。
			public List<Integer> findMinHeightTrees01(int n, int[][] edges) {
                 int[][] edge=new int[n][n];
                 for(int k=0;k<edges.length;k++) {                              //无向图，转化为对称矩阵来求解
                	 int i=edges[k][0];
                	 int j=edges[k][1];
                	 edge[j][i]=edge[i][j]=1;
                 }
                 int[] height=new int[n];
                 for(int m=0;m<n;m++) {                                         //对每个节点都dfs
                	 boolean[] used=new boolean[n];                             //标记数组，用于一次遍历的标记，防止重复
                	 List<Integer> path=new ArrayList<>();                      //path存单次遍历到没有节点可遍历的路径
                	 List<List<Integer>> res=new ArrayList<>();                 //res为路径集合
                	 path.add(m);                                               //本次遍历，先加入本节点并设置本节点为true
                	 used[m]=true;
                	 dfs310(edge,m,path,res,used);                              //开启dfs,主要是为了得到res
                	 int len=res.size();
                	 for(int k1=0;k1<len;k1++) {                                //求res中最长的路径
                		 height[m]=Math.max(height[m], res.get(k1).size());
                	 }
                 }
                 List<Integer> result=new ArrayList<>();
                 int minHeight=height[0];
                 for(int i1=0;i1<n;i1++) {                                      //求所有节点最长路径的最小值
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
				for(int j1=0;j1<edge.length;j1++) {                              //遍历所有可能
					if(j1==edge.length-1&&(edge[m][j1]==0||used[j1]==true)) {    //如果本行遍历到最后一列，仍然为0，或者最后一个节点为true，则没有路径继续向下了，所以加入res
						res.add(new ArrayList<Integer>(path));
						return;
					}
					if(edge[m][j1]==1&&used[j1]==false) {                        //如果是1，有边，且未被访问过，则可以递归
						path.add(j1);                                            //加入path，修改状态
						used[j1]=true;
						dfs310(edge,j1,path,res,used);                           //递归
						used[j1]=false;                                          //状态恢复，使得path和used可以重用
						path.remove(path.size()-1);
					}
				}
			}
			
			//巧妙解法：BFS
			//图最中间的节点，就是高度min的节点
			//所以我们从最外围，度为1的叶子节点开始，向内收缩，收缩到最后就是高度min，最中间的节点
			//需要记录的就是度和相邻节点
			public List<Integer> findMinHeightTrees(int n, int[][] edges){
				List<Integer> res=new ArrayList<>();
				if(n==1) {
					res.add(0);
					return res;
				}
				int[] degree=new int[n];                                             //存每个节点的度，方便找到度为1的，每次都出队;下标就是节点
				List<List<Integer>> map=new ArrayList<>();                           //map用来存每个节点的相邻节点，相邻关系
				for(int i=0;i<n;i++) {
					map.add(new ArrayList<>());
				}
				for(int[] edge:edges) {
					degree[edge[0]]++;
					degree[edge[1]]++;
					map.get(edge[0]).add(edge[1]);
					map.get(edge[1]).add(edge[0]);
				}
				Queue<Integer> queue=new LinkedList<>();                             //BFS队列，度1出队，然后相邻节点度-1，若变成了度为1，则入队
				for(int i=0;i<n;i++) {
					if(degree[i]==1) {
						queue.offer(i);
					}
				}
				while(!queue.isEmpty()) {
					res=new ArrayList<>();                                           //每次一个新的res，res存的就是叶子节点，最后一次存的叶子节点就是答案
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

