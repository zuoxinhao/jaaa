package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;
import java.lang.reflect.Array;


public class paperTest {
	
	static int i=0;
	
	static class MyRun implements Runnable{
		public void run() {
			i++;   //i--     i=i*2 i++   
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
