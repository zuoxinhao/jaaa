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
	
	//1. signal,waitģʽ����Ҫȷ���߳�1��ĳ��ָ��A���������߳�2��ĳ��ָ��B��ͬʱ�����߳�����Ҫ����˳��ִ��
	//ָ��Aִ����󲻱�������ִ��B����Ҫ���ǵ��߳�2��ָ���Ƿ��ֵ�Bִ�У���ʱ�߳�1��A�ĺ���ָ�������B����ִ�С�
	
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
			//����������add,֪���±��޸Ĳ���set
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
			
		
		
		
		
		
		
	
		//1.�����ַ������
				FileWriter writeFile = null;
				try {
					//2.������д���·�����ļ�
					File file = new File("data1.csv");
					//3.������ļ������ڣ��ʹ���
					if(!file.exists()) {
						file.createNewFile();
					}
					//4.���ֽ����������ʵ��
					writeFile = new FileWriter(file,true);
					//5.ͨ��ѭ��������д��txt�ļ���
					//for(int i = 0; i < array.length; i++) {
						//6.����ǰn - 1��β������","
						
						//for(int j = 0; j < array[0].length - 1; j++) {
							//writeFile.write(array[i][j] + ",");
						//}
					for(int i=0;i<list3.size();i++) {
						writeFile.write(list3.get(i)+",");
					}
					writeFile.write(res+"");
					writeFile.write("\n");	
						
						//7.�������һ�к��治��","
						//writeFile.write(array[i][array[0].length - 1] + "");
						//8.���ϻ��з�
						
					//}
					//9.��writeFile�������ȫ��ˢ��һ�Σ�ȫ��д���ļ���
					writeFile.flush();
				} catch (Exception e) {//10.�쳣����
					e.printStackTrace();
				} finally {	
					try {
						//11.���writeFile��Ϊ�գ��ͽ���ر�
						if(writeFile != null)
							writeFile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				
		}

}
}


