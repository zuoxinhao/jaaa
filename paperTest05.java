package work;

import work.paperTest04.MyRun;

public class paperTest05 {
	
	//第五种：多输入多输出
	
	static int a=79;
	static int b=a;
	static int c=0;
	static int d=0;
	
	static class MyRun implements Runnable{
		public void run() {
			a++; 
			b++;
			c=a+b;
			a++;
			b++;
			d=a+b;
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
