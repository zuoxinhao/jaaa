package work;

import work.paperTest04.MyRun;

public class paperTest07 {
	
	//第七种：无关变量
	
	static int a=0;
    static int i=0;
	
	static class MyRun implements Runnable{
		public void run() {
			i++;
		}
	}
	
	public static void main(String[] args) {
		for(int k=0;k<100;k++) {
			Thread t=new Thread(new MyRun());
			t.start();
		}
		System.out.print(i);	
	}

}
