package work;

import work.paperTest08.MyRun;

public class paperTest09 {
	
	//µÚ¾ÅÖÖ£º¸´ÔÓÔËËã  (i+1)^3
	
    static int i=0;
	
	static class MyRun implements Runnable{
		public void run() {
			i=i*i*i+3*i*i+3*i;
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
