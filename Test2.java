package work;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

public class Test2 {
	
	//1. signal,wait模式。需要确保线程1中某条指令A必须先于线程2中某条指令B，同时单个线程内需要满足顺序执行
	//指令A执行完后不必须立刻执行B，需要考虑到线程2中指令是否轮到B执行，此时线程1中A的后续指令继续和B并发执行。
	
	public static void main(String[] args) {
		int count=200;
		while(count-->0) {
		LinkedList<Integer> list1=new LinkedList<>();
		LinkedList<Integer> list2=new LinkedList<>();
		LinkedList<Integer> list3=new LinkedList<>();
		int res;
		int index1=0;
		int index2=0;
		int index3=0;
		for(int i=0;i<50;i++) {
			//增加链表用add,知道下标修改采用set
			list1.add(i, 10000+i);;
			}
		for(int i=0;i<50;i++) {
			list2.add(i, 20000+i);
			}
		Random rand=new Random();
		while(index3<100) {
			boolean flag=rand.nextBoolean();
			if(flag==true&&index1<50) {
				list3.add(index3++, list1.get(index1++));
			}else if(flag==false&&index2<50) {
				list3.add(index3++, list2.get(index2++));
			}
		}
		int location1=list3.indexOf(10030);
		int location2=list3.indexOf(20029);
		if(location1>location2) {
			res=0;
		}else {
			res=1;
		}
			
		
		
		
		
		
		
	
		//1.创建字符输出流
				FileWriter writeFile = null;
				try {
					//2.数据想写入的路径及文件
					File file = new File("data1.csv");
					//3.如果该文件不存在，就创建
					if(!file.exists()) {
						file.createNewFile();
					}
					//4.给字节输出流赋予实例
					writeFile = new FileWriter(file,true);
					//5.通过循环将数组写入txt文件中
					//for(int i = 0; i < array.length; i++) {
						//6.数据前n - 1列尾部加入","
						
						//for(int j = 0; j < array[0].length - 1; j++) {
							//writeFile.write(array[i][j] + ",");
						//}
					for(int i=0;i<list3.size();i++) {
						writeFile.write(list3.get(i)+",");
					}
					writeFile.write(res+"");
					writeFile.write("\n");	
						
						//7.数组最后一列后面不加","
						//writeFile.write(array[i][array[0].length - 1] + "");
						//8.加上换行符
						
					//}
					//9.把writeFile里的数据全部刷新一次，全部写入文件中
					writeFile.flush();
				} catch (Exception e) {//10.异常捕获
					e.printStackTrace();
				} finally {	
					try {
						//11.如果writeFile不为空，就将其关闭
						if(writeFile != null)
							writeFile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				
		}

}
}


