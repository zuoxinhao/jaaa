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
	
	//3. Barrier:��ʵ��rendevous����չ��Լ��ģʽרע�������̵߳Ļ�ͣ�barrierָ���ǿ����ö���߳�һ����
	//���������߳�Ϊ������͵���10008��20009��30004
	//��Ҫ���ǣ�10008λ��<20010,<30005;20009<10009,<30005;30004<10009,<20010
	//����Ҫ��ǳ����̣������������ѻ�ȡ������������ͨ�����Ԥɸѡ�ó���������ȷ������Ȼ���ٽ����������
	
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
			//����������add,֪���±��޸Ĳ���set
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
			
		
		
		
		
		
		
	
		//1.�����ַ������
				FileWriter writeFile = null;
				try {
					//2.������д���·�����ļ�
					File file = new File("data5.csv");
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
					//if(res==1) {
					for(int i=0;i<list3.size();i++) {
						writeFile.write(list3.get(i)+",");
					}
					writeFile.write(res+"");
					writeFile.write("\n");	
					//}
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




