package work;

import work.paperTest01.MyRun;

public class paperTest02 {
	
	//�ڶ��֣�����������(�ӷ����˷�����)

	static int i=119;
	
	static class MyRun implements Runnable{
		public void run() {
			i=i*2;
			i++;   
		}
	}
	
	
	public static void main(String[] args) {
		for(int k=0;k<10;k++) {
			Thread t=new Thread(new MyRun());
			t.start();
		}
		System.out.print(i);	
	}

}
