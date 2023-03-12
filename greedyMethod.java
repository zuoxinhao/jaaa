package work;

import java.util.*;
import java.lang.*;


public class greedyMethod {
	
	//贪心算法总结：
	//*****直接记忆：数组按照第一维度排序升序：Arrays.sort(pairs,(a,b)->(a[0]-b[0]));
	//第二维度升序：Arrays.sort(pairs,(a,b)->(a[1]-b[1]));
	//第一维度降序：Arrays.sort(pairs,(a,b)->(b[0]-a[0]));
	//第二维度降序：Arrays.sort(pairs,(a,b)->(b[1]-a[1]));
	
	//1. 数组单个元素和左右比较的算法：两次遍历，每次遍历在对应的基础上更新
	//2. 区间问题：按照区间的末端点进行排序，因为末端点不同，就保证了末端错开，且区间只能向前延申，这样我们只需要
	//确保后面一个区间的前端点大于等于前一个区间的末端点即可。选择的区间结尾越小，留给其他的空间就越大，实现了贪心选择。
	//3. 二维数组的排序，重写Comparator接口，重写compare函数
	/*Arrays.sort(intervals, new Comparator<int[]>() {     //Comparator里面泛型传int[]表示多维数组              
        public int compare(int[] interval1, int[] interval2) {//重写compare函数  第一个参数表示是原顺序在前面的，第二个是在后面的
            return interval1[1] - interval2[1];      //return第一个参数减去第二个参数，表示升序；第二个减第一个表示降序                    
        }                 //多维数组的比较，数组[0]表示按照第一维度比较(行)，[1]按照第二维度比较(列)
    });*/
	//*****更好的写法：Arrays.sort(pairs,(a,b)->(a[1]-b[1]));
	//第三种写法，用lambda表达式：Arrays.sort(points,(a,b)->a[1]<b[1]?-1:1)和第二种一摸一样的，用第二种最好理解
	//**注意点：没反应过来，后一个左端点在前一个的右端点内，指的是后一个左<前一个右，别搞混了。
	//4. 从前向后遍历，最先出现的就是第一次的；从后向前遍历，最先出现的就是最后一次出现的，用hashmap存储即可。
	//5. 链表LinkedList的插入操作：list.add(index,element)
	
	
	
	
	
	
	
	//455：双数组指针后移类:
    //for循环写法
	public static int assign(int[] children,int[] snack) {
		Arrays.sort(children);
		Arrays.sort(snack);
		int res=0;
		int j=0;
		for(int i=0;i<children.length;i++) {
			for(;j<snack.length;j++) {
				if(children[i]<=snack[j]) {
					res++;
					j++;
					break;  //break直接跳出本层for循环，后面继续执行i++的循环即可
				}
			}
		}
		return res;	
	}
	
	//while循环写法
	public static int assign02(int[] children,int[] snack) {
		Arrays.sort(children);
		Arrays.sort(snack);
		int j=0;
		int i=0;
		while(i<children.length&&j<snack.length) {  
			if(children[i]<=snack[j]) i++;
			++j;
		}
		return i;
	}
	
	//135:数组中单个元素和左右两个元素相比较的算法：先从前向后遍历右比较一次，再从后向前遍历，左比较一次。
	public static int candy(int[] ratings) {
		int m=ratings.length;
		int[] candys=new int[m];                                  //java中创建定长数组，必须用new
		for(int i=0;i<m;i++) {
			candys[i]=1;
		}
		for(int j=0;j<m-1;j++) {
			if(ratings[j+1]>ratings[j]) candys[j+1]=candys[j]+1;
		}
		for(int s=m-1;s>0;s--) {
			//**注意点：如果左边糖果已经比右边高了，那就不需要在右边基础上+1了。
			if(ratings[s-1]>ratings[s]) candys[s-1]=Math.max(candys[s-1],candys[s]+1);
		}
		int sum=Arrays.stream(candys).sum();                        //数组元素求和：Arrays.stream().sum()
		return sum;
	}
	
	//435:区间不重叠问题：按末端排序，找不重叠即可，贪心选择。后一个的左端点**小于**前一个右端点，则需要舍弃。
	public static int overlapIntervals(int[][] intervals ) {
		int answer=0;
		int row=intervals.length;                                        //直接.length就是行，列是a[0].length
		Arrays.sort(intervals, new Comparator<int[]>() {                 //java中的二维数组排序，重写compare函数以实现按列排序
            public int compare(int[] interval1, int[] interval2) {
                return interval1[1] - interval2[1];                      //按第二列排序，就必须用下标1来减
            }
        });
		int prev=intervals[0][1];
		for(int i=1;i<row;i++) {
			//**注意点：没反应过来，后一个左端点在前一个的右端点内，指的是后一个左<前一个右，别搞混了。
			if(prev>intervals[i][0]) {
				answer++;
				}
			else {
				prev=intervals[i][1];
			}
		}
		return answer;
	}
	
	//605:种花问题，别想的太复杂，从简单的来考虑，只要前后都为0，且本元素为0，则将0变成1即可；
	//再考虑一下首末特殊元素问题，00变10，00变01.
	public static boolean placeFlower(int[] flowerBed,int n) {
		int sum=0;
		int i=0;
		if(flowerBed.length==1) {
			if(flowerBed[0]==0) {
				sum++;
			}
			if(sum>=n) {
				return true;
			}else {
				return false;
			}
		}
		if(flowerBed[0]==flowerBed[1]&&flowerBed[0]==0) {
			flowerBed[0]=1;
			sum++;
		}
		for(i=1;i<flowerBed.length-1;i++) {
			if(flowerBed[i-1]==flowerBed[i+1]&&flowerBed[i-1]==flowerBed[i]&&flowerBed[i]==0) {
				flowerBed[i]=1;
				sum++;
			}
		}
		if(flowerBed[flowerBed.length-2]==flowerBed[flowerBed.length-1]&&flowerBed[flowerBed.length-1]==0) {
			sum++;
		}
		if(sum>=n) {
			return true;
		}else {
		return false;
	}
	}
	
	//452：气球爆炸问题和区间去重叠很类似，不用多思考。
	public static int overlapIntervals1(int[][] points ) {
		int answer=1;
		int row=points.length;                                        
		 Arrays.sort(points,(a,b)->Integer.compare(a[1],b[1]));    //当排序差值过大时，必须用此方法，包装类Integer.compare
		int prev=points[0][1];
		for(int i=1;i<row;i++) {
			if(prev<points[i][0]) {
				answer++;
				prev=points[i][1];
			}
		}
		return answer;
	}
	
	//763：最大字符串分割片数：
	//从头到尾遍历，保证留下的给后面区间最大空间，然后不断扩充当次区间的end,如果遍历到了end没有扩充，那就表示一个区间达成。
	//下一个区间从下一个元素开始即可。
	//区间扩充，记录字符最后一次的位置，然后根据区间进行扩充，分割；
	//这里的intervals区间扩充，不需要比较a.end和b.start，而是直接没到end，则扩充end为a.end,b.end的最大值即可
	public static List<Integer> partition(String s){
		int[] last=new int[26];
		int i;                                                  //java中字符串不是数组，只能调用函数求具体位置字符
		//用hashmap从后向前存储最后一次出现的位置是更优解
		for(i=0;i<s.length();i++) {                             //java中字符串单位置的字符不接受s[i]的用法，只能用charAt[i]函数来找单个字符
			last[s.charAt(i)-'a']=i;                            //非常好的方法，记录每个小写字母出现的最后位置，用charAt-'a'位置等于i
		}
		List<Integer> answer=new ArrayList<Integer>();          //变长数组，用List<Integer> answer=new ArrayList<Integer>()
		int start=0;
		int end=0;
		for(i=0;i<s.length();i++) {
			end=Math.max(end, last[s.charAt(i)-'a']);
			if(i==end) {
				answer.add(end-start+1);
				start=end+1;
				end=start;
			}
		}
		return answer;
	}
	
	//122:脑筋急转弯：数组中元素变化的差值等于元素一步步变化的差值和。如：A-B-C-D:差值为B-A+C-B+D-C=D-A就等于了首末差值
	//求不相交区间的和的最大值，实际上就是一步步拆分，长度为1的不相交区间的和的最大值：和本题无关
	public static int maxProfit(int[] prices) {
		int profit=0;
		for(int i=0;i<prices.length-1;i++) {
			profit+=Math.max(0, prices[i+1]-prices[i]);
		}
		return profit;
		
	}
	
	//406:（非常巧妙）根据身高重建队列问题：数对排序问题：一般第一个元素降序，第二个元素升序，错开元素再考虑，简化题目。
	//身高降序排序，身高高的人都在前面，后面考虑插入矮的人，假设矮的人ki，则就插入ki位置，因为前面都是比他高的，所以直接插入ki位置就满足了条件
	public static int[][] reconstrcution(int[][] queueHeight){
		Arrays.sort(queueHeight, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);//重写sort，第一个降序，第二个升序
		LinkedList<int[]> answer=new LinkedList<int[]>();     //插入，用链表更好
		for(int[] s:queueHeight) {
			answer.add(s[1], s);                             //list.add(index,element)这里的s[1]指的就是queueHight[i][1],s是一维行数组
		}
		return answer.toArray(new int[answer.size()][2]);     //链表再变成数组输出  
	}
	
	//665:变化一次成为非递减数组：需要注意到变化一次后只能满足两个数之间的非递减，还要注意之前的数。(可以用三个数的大小来判断连续性)
	//也可以用变化两次，用isSorted来判断两次。因为前一个大于后一个时，将前一个变成后一个，则能最大保证后面的非递减性；后一个变前一个,
	//则能最大的保证前面部分的非递减性，所以只要变化两次，判断两次有序性即可。
	public static boolean isSorted(int[] nums) {           
		for(int i=0;i<nums.length-1;i++) {
			if(nums[i]>nums[i+1]) {
				return false;
			}
		}
		return true;
	}
	public static boolean change(int[] nums) {
		int i;
		for(i=0;i<nums.length-1;i++) {
		     if(nums[i]>nums[i+1]) {
		    	 int s=nums[i];
		    	 nums[i]=nums[i+1];
		    	 if(isSorted(nums)) {
		    		 return true;
		    	 }
		    	 nums[i]=s;
		    	 nums[i+1]=s;
		    	 return isSorted(nums);
		     }
		}
		return true;
	}
}