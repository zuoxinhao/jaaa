package work;

import work.paperTest03.MyRun;

public class rendezvous {
	
	
	static int i=70;
	
	static class MyRun01 implements Runnable{
		public void run() {
			i++;  
		}
	}
	
	static class MyRun02 implements Runnable{
		public void run() {
			i++;  
		}
	}
	
	
	public static void main(String[] args) {
		    	
					Thread t01=new Thread(new MyRun01());
					Thread t02=new Thread(new MyRun02());
					
					t01.start();
					t02.start();
					
				
				System.out.println(i);
						
	}
	
	
	
	
	
	
	

}
