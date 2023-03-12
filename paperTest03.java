package work;

import work.paperTest02.MyRun;

public class paperTest03 {
	
	//第三种：多种运算结合(加法，乘法运算)，且交换运算顺序

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
