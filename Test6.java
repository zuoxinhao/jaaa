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

public class Test6 {
	
	//3. Barrier:其实是rendevous的扩展，约会模式专注于两个线程的会和，barrier指的是可以让多个线程一起会和
	//我们以三线程为例，会和点在10008，20009，30004
	//即要求是，10008位置<20010,<30005;20009<10009,<30005;30004<10009,<20010
	//由于要求非常苛刻，正例样本很难获取，所以我们先通过多次预筛选拿出少量的正确样本，然后再进行随机测试
	
	public static void main(String[] args) {
		int count=108;
		while(count-->0) {
		LinkedList<Integer> list1=new LinkedList<>();
		LinkedList<Integer> list2=new LinkedList<>();
		LinkedList<Integer> list4=new LinkedList<>();
		LinkedList<Integer> list3=new LinkedList<>();
		int res=0;
		int index1=0;
		int index2=0;
		int index3=0;
		int index4=0;
		for(int i=0;i<50;i++) {
			//增加链表用add,知道下标修改采用set
			list1.add(i, 10000+i);;
			}
		for(int i=0;i<50;i++) {
			list2.add(i, 20000+i);
			}
		for(int i=0;i<50;i++) {
			list4.add(i, 30000+i);
			}
		Random rand=new Random();
		while(index3<150) {
			int flag=rand.nextInt(3);
			if(flag==0&&index1<50) {
				list3.add(index3++, list1.get(index1++));
			}else if(flag==1&&index2<50) {
				list3.add(index3++, list2.get(index2++));
			}else if(flag==2&&index4<50) {
				list3.add(index3++, list4.get(index4++));
			}
		}
		int location1=list3.indexOf(10008);
		int location2=list3.indexOf(10009);
		int location3=list3.indexOf(20009);
		int location4=list3.indexOf(20010);
		int location5=list3.indexOf(30004);
		int location6=list3.indexOf(30005);
		if(location1<location4&&location1<location6&&location3<location2&&location3<location6&&location5<location2&&location5<location4) {
				res=1;
		}else {
			res=0;
		}
			
		
		
		
		
		
		
	
		//1.创建字符输出流
				FileWriter writeFile = null;
				try {
					//2.数据想写入的路径及文件
					File file = new File("data5.csv");
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
					//if(res==1) {
					for(int i=0;i<list3.size();i++) {
						writeFile.write(list3.get(i)+",");
					}
					writeFile.write(res+"");
					writeFile.write("\n");	
					//}
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




