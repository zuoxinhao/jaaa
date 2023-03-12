package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

public class miniService {
	
	public static int[][] value;
	
	public static int minServices(int n,int[][] value,int k) {
		Queue<Integer> queue=new LinkedList<>();
		queue.offer(k-1);
		int res=0;
		int max=0;
		while(!queue.isEmpty()) {
			int size=queue.size();
			max=0;
			for(int i=0;i<size;i++) {
				int m=queue.poll();
				if(max<value[m][m]) max=value[m][m];
				for(int j=0;j<n;j++) {
					if(value[m][j]==1) {
						queue.offer(j);
					}
				}
			}
			res+=max;
		}
		return res;
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		value=new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				value[i][j]=sc.nextInt();
			}
		}
		int k=sc.nextInt();
		System.out.println(minServices(n,value,k));
		
		
	}

}
