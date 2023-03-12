package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;
import java.lang.reflect.Array;


public class paperTest01 {
	
	//最基本的：单输入，单输出，简单运算
	
	static int i=72;
	
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
