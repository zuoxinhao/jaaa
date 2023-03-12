package work;

import work.paperTest07.MyRun;

public class paperTest08 {
	
	//µÚ°ËÖÖ£º¸´ÔÓÔËËã  (i+1)^2
	
	    static int i=19;
		
		static class MyRun implements Runnable{
			public void run() {
				i=i*i+2*i;
				i++;
			}
		}
		
		public static void main(String[] args) {
			for(int k=0;k<3;k++) {
				Thread t=new Thread(new MyRun());
				t.start();
			}
			System.out.print(i);	
		}

}
