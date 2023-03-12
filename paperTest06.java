package work;

import work.paperTest05.MyRun;

public class paperTest06 {
	
	//第六种：单输入多输出
	
		static int a=99;
		static int c=0;
		static int d=0;
		
		static class MyRun implements Runnable{
			public void run() {
				a++; 
				c=a;
				a++;
				d=a;
			}
		}
		
		public static void main(String[] args) {
			for(int k=0;k<100;k++) {
				Thread t=new Thread(new MyRun());
				t.start();
			}
			System.out.println(c);
			System.out.println(d);
		}

}
