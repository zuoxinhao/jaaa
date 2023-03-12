package work;

import work.paperTest03.MyRun;

public class paperTest04 {
	
	//第四种：多输入单输出
	
			static int a=69;
			static int b=a;
			static int c=0;
			
			static class MyRun implements Runnable{
				public void run() {
					a++; 
					b++;
					c=a+b;
				}
			}
			
			public static void main(String[] args) {
				for(int k=0;k<100;k++) {
					Thread t=new Thread(new MyRun());
					t.start();
				}
				System.out.print(c);	
			}

}
