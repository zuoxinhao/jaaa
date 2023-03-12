package work;


public class Barrier {
	
	
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
		if(i==100) {
			System.out.print(1);
		}else {
			System.out.print(0);
		}
			
	}

}
