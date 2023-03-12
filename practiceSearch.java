package work;


import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

public class practiceSearch {  //����������ص㣬����ֻ��Ҫ����������ϣ�������Ⱥ������һ�ǡ�
	
	//������ȵ���⣺����ģ��
	//1. ����Ҫת��Ϊͼ���⣬ȷ���ýڵ�ͱߣ��ڵ���ǿ��ܿ����ݹ�ĵط����߾������ǵݹ�ķ���
	//2. ���������������еĽڵ㣬**�������������ϱ�ǣ������ݹ飻
	//3. �����������ǵݹ����
	//������ģ�壺�ݹ����(һ���Ǳ߽������ͱ��/�ѷ��ʹ�)���޸ı��״̬�����ݱ�������һ��ݹ鷽��(�ݹ���һ����Խ��мӼ��˳������ȡ���շ���ֵ)
	
	//*****�ص���(���ο��Ժܶ����)547ʡ�ݹ���:ͨ���ڽӾ���������ͨ�����ĸ�������Ϊ֮ǰ����һ�α���
	//����������ͨ��ֱ��ͼ���洢��������������ͨ���ڽӾ���������ͨ���������ⷨ����������
	//����������m*n���ڵ㣬ÿ���ڵ�4���ߣ�ʡ�ݹ�����n���ڵ㣬ÿ���ڵ�n���ߡ�
	//���Ľⷨ��n���ڵ㣬ÿ���ڵ㶼��������������n�����У�������n����
	//�����������ݹ�������Ǹó���û�����ʹ����������ݹ�����Ƿ��ʹ����޸ķ��ʱ�ǣ�n����ֻ�е�û�����Ҷ�ӦֵΪ1���ܼ������µݹ�
	
	//��ˮ���⣺����˼ά��������(�ڵ�)��������(�������ĸ��Ҳ�Խ�缴��)��ֻҪ�ܵ���Ķ�����Ϊtrue���ɣ�������Ҫ������顣
	//�����������ֱ��ʾ�ܷ������������󣺵ݹ���ڣ�Խ����߱�ǹ���(������)���޸�״̬����Ϊ���������ݹ��ĸ�����
	//����������ȿ��Գ䵱���ʹ��ı�ǣ��ֿ��Ե���������ı�ǣ����ں����ж��ܷ����롣
	//*****���ٺ��ĵ���仯�⣺Ҳ������˼ά���������߿����ݹ飬�ٱ߽�ĵ���(O)������չ����չ���Ķ��Ǻ��治��Ҫ���(�ȸ�ΪN)
	//û��չ���ĵ���(O)�������Ҫ�޸�ΪX��
	
	//*****������������·�����⣺ע��һ���㣬����·���Ǻܶ�����������һ��StringBuilder����Ҫ���ݣ��������Ǿ�ÿ��dfs
	//���½�(new StringBuilder())������֮ǰ��path��Ȼ����ȥ����ýڵ㼴�ɡ����ÿ�ζ��½������룬��ֹ�������⡣
	
	
	//����������
	public int maxAreaOfIsland(int[][] grid) {
		int row=grid.length;
		int column=grid[0].length;
		int max=Integer.MIN_VALUE;
		for(int i=0;i<row;i++) {       //�������������нڵ�
			for(int j=0;j<column;j++) {
				if(grid[i][j]==1) {    //����������״̬�������ݹ���ø�������
					max=Math.max(max, dfs(grid,i,j));
				}
			}
		}
		return max;
	}
	
	//�����������ǵݹ麯��
	public int dfs(int[][] grid,int i,int j) {
		//�ݹ���ڣ��߽�������״̬���
		if(i<0||i>=grid.length||j<0||j>grid[0].length||grid[i][j]==0) return 0;
		//�޸�״̬
		grid[i][j]=0;
		//���ݱߵݹ���һ�㣬���Խ��б�Ҫ�Ĵ���
		int area=1;
		area+=dfs(grid,i-1,j)+dfs(grid,i+1,j)+dfs(grid,i,j+1)+dfs(grid,i,j-1);
		return area;
	}
	
	//��������:ֻ��Ҫ����������Ӹ�count��¼�����Ϳ����ˣ������������������Ҫ����Ĵ����ˣ������޸��±�ǣ�Ȼ��ݹ��޸��ĸ�����
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

	//*****�ص���:ʡ�ݹ�����ʵ����������ͨ�����ĸ��������������Ŀ�����ڽӾ���洢�ģ��͵���������ֱ��ͼ���治һ����
	//���Ľⷨ��n���ڵ㣬ÿ���ڵ㶼��������������n�����У�������n����
	//�����������ݹ�������Ǹó���û�����ʹ��������������Ƿ��ʹ����޸ķ��ʱ�ǣ�n����ֻ�е�û�����Ҷ�ӦֵΪ1���ܼ������µݹ�
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
