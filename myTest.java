package work;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class myTest {
	public static void main(String[] args) {
		int count=99;
		while(count-->0) {
		int[] res=new int[100];
		int resIndex=0;
		int index1=0;
		int index2=0;
		
		//��������ָ��Լ�����
		int[][] array1=new int[50][2];
		int[] a1=new int[50];
		for(int i=0;i<array1.length;i++) {
			for(int j=0;j<array1[0].length;j++) {
				if(j==0) array1[i][j]=1;
				if(j==1) array1[i][j]=i+1;
			}
			a1[i]=1*10000+i+1;
		}
		int[][] array2=new int[50][2];
		int[] a2=new int[50];
		for(int i=0;i<array2.length;i++) {
			for(int j=0;j<array2[0].length;j++) {
				if(j==0) array2[i][j]=2;
				if(j==1) array2[i][j]=i+1;
			}
			a2[i]=2*10000+i+1;
		}
		
		//����ָ����Ҫִ��˳�򣬱�֤��ȷִ�еĴ���
		//ǰָ��ֻ��Ҫ���ֽ�����˳��������������̲���
		int[] a11=new int[30];
		System.arraycopy(a1, 0, a11, 0, 30);
		int[] a22=new int[29];
		System.arraycopy(a2, 0, a22, 0, 29);
		while(resIndex<=58){
		Random rand=new Random();
		boolean flag=rand.nextBoolean();
		if(flag==true&&index1<a11.length) {
			res[resIndex++]=a11[index1++];
		}else if(flag==false&&index2<a22.length) {
			res[resIndex++]=a22[index2++];
		}
	 }
		//��λ�����ں�����ָ��
		res[resIndex++]=a2[29];
		
		//����ָ������
		int[] a111=new int[20];
		System.arraycopy(a1, 30, a111, 0, 20);
		int[] a222=new int[20];
		System.arraycopy(a2, 30, a222, 0, 20);
		index1=0;
		index2=0;
		Random rand=new Random();
		while(resIndex<100){
		boolean flag=rand.nextBoolean();
		if(flag==true&&index1<a111.length) {
			res[resIndex]=a111[index1];
			resIndex++;
			index1++;
		}else if(flag==false&&index2<a222.length) {
			res[resIndex]=a222[index2];
			resIndex++;
			index2++;
		}else {
			break;
		}
	 }
		if(index1>=a111.length) {
			while(resIndex<10&&index2<a222.length) res[resIndex++]=a222[index2++];
		}
		if(index2>=a222.length) {
			while(resIndex<10&&index1<a111.length) res[resIndex++]=a111[index1++];
		}
		
		
		
		for(int i=0;i<resIndex;i++) {
			System.out.println(res[i]+",");
		}
		
		
		
		
		
		
	
		//1.�����ַ������
				FileWriter writeFile = null;
				try {
					//2.������д���·�����ļ�
					File file = new File("filename.txt");
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
					for(int i=0;i<res.length;i++) {
						writeFile.write(res[i]+",");
					}
					writeFile.write("true");
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
