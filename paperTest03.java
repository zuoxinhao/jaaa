package work;

import work.paperTest02.MyRun;

public class paperTest03 {
	
	//�����֣�����������(�ӷ����˷�����)���ҽ�������˳��

		static int i=70;
		
		static class MyRun implements Runnable{
			public void run() {
				i++;  
				i=i*2;
			}
		}
		
		
		public static void main(String[] args) {
			    	for(int k=0;k<10;k++) {
						Thread t=new Thread(new MyRun());
						t.start();
					}
					System.out.println(i);
    						
		}

}
