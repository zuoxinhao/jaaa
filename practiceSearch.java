package work;


import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

public class practiceSearch {  //深度优先是重点，回溯只需要掌握排列组合，广度优先很巧妙记一记。
	
	//深度优先的求解：基本模板
	//1. 首先要转化为图问题，确定好节点和边，节点就是可能开启递归的地方，边就是我们递归的方向
	//2. 主函数：遍历所有的节点，**根据条件，带上标记，开启递归；
	//3. 辅函数：就是递归求解
	//辅函数模板：递归出口(一般是边界条件和标记/已访问过)，修改标记状态，根据边来定下一层递归方向，(递归下一层可以进行加减乘除运算获取最终返回值)
	
	//*****重点题(变形可以很多的题)547省份关联:通过邻接矩阵来求连通分量的个数：华为之前考过一次变形
	//岛屿数量是通过直观图来存储，求岛屿数量，和通过邻接矩阵来求连通分量个数解法有明显区别
	//岛屿数量是m*n个节点，每个节点4条边；省份关联是n个节点，每个节点n条边。
	//核心解法：n个节点，每个节点都可能连接上其他n个城市，所以是n条边
	//主函数开启递归的条件是该城市没被访问过，辅函数递归出口是访问过，修改访问标记，n条边只有当没访问且对应值为1才能继续向下递归
	
	//海水问题：逆向思维，从四周(节点)向里面流(方向是四个且不越界即可)，只要能到达的都设置为true即可，所以需要标记数组。
	//两个标记数组分别表示能否流到两个大洋：递归出口：越界或者标记过了(流过了)，修改状态设置为可流过，递归四个方向
	//这里标记数组既可以充当访问过的标记，又可以当可以流入的标记，便于后面判断能否流入。
	//*****不临海的岛屿变化题：也是逆向思维，从四条边开启递归，临边界的岛屿(O)向内扩展，扩展到的都是后面不需要变的(先改为N)
	//没扩展到的岛屿(O)就是最后要修改为X的
	
	//*****二叉树的所有路径问题：注意一个点，就是路径是很多个，如果共用一个StringBuilder就需要回溯，不共用那就每次dfs
	//都新建(new StringBuilder())并传入之前的path，然后再去加入该节点即可。最好每次都新建并传入，防止出现问题。
	
	
	//岛屿最大面积
	public int maxAreaOfIsland(int[][] grid) {
		int row=grid.length;
		int column=grid[0].length;
		int max=Integer.MIN_VALUE;
		for(int i=0;i<row;i++) {       //主函数遍历所有节点
			for(int j=0;j<column;j++) {
				if(grid[i][j]==1) {    //根据条件，状态，开启递归调用辅助函数
					max=Math.max(max, dfs(grid,i,j));
				}
			}
		}
		return max;
	}
	
	//辅助函数就是递归函数
	public int dfs(int[][] grid,int i,int j) {
		//递归出口：边界条件和状态标记
		if(i<0||i>=grid.length||j<0||j>grid[0].length||grid[i][j]==0) return 0;
		//修改状态
		grid[i][j]=0;
		//根据边递归下一层，可以进行必要的处理
		int area=1;
		area+=dfs(grid,i-1,j)+dfs(grid,i+1,j)+dfs(grid,i,j+1)+dfs(grid,i,j-1);
		return area;
	}
	
	//岛屿数量:只需要主函数里面加个count记录数量就可以了，辅函数里面根本不需要更多的处理了，就是修改下标记，然后递归修改四个即可
	public int findIsland(int[][] grid) {
		int row=grid.length;
		int column=grid[0].length;
		int count=0;
		for(int i=0;i<row;i++) {       
			for(int j=0;j<column;j++) {
				if(grid[i][j]==1) {    
					dfs01(grid,i,j);
					count++;
				}
			}
		}
		return count;
	}

	public void dfs01(int[][] grid,int i,int j) {
		if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0) return ;
		grid[i][j]=0;
		dfs01(grid,i-1,j);
		dfs01(grid,i+1,j);
		dfs01(grid,i,j+1);
		dfs01(grid,i,j-1);
	}

	//*****重点题:省份关联，实际上是找连通分量的个数，但是这道题目是用邻接矩阵存储的，和岛屿数量用直观图来存不一样的
	//核心解法：n个节点，每个节点都可能连接上其他n个城市，所以是n条边
	//主函数开启递归的条件是该城市没被访问过，辅函数出口是访问过，修改访问标记，n条边只有当没访问且对应值为1才能继续向下递归
	public int findCircleNum(int[][] isConnected) {
		boolean[] visited=new boolean[isConnected.length];
		int count=0;
		for(int i=0;i<isConnected.length;i++) {
			visited[i]=false;
		}
		for(int j=0;j<isConnected.length;j++) {
			if(visited[j]!=true) {
				dfs02(isConnected,j,visited);
				count++;
			}
		}
		return count;		
	}
	
	public void dfs02(int[][] isConnected,int j, boolean[] visited) {
		if(visited[j]==true) return ;
		visited[j]=true;
		for(int i=0;i<isConnected.length;i++) {
			if(visited[i]==false&&isConnected[j][i]==1) {
				dfs02(isConnected,i,visited);
			}
		}
	}
	
	
	
	
}
