package work;

import java.util.*;
import java.lang.*;


public class greedyMethod {
	
	//̰���㷨�ܽ᣺
	//*****ֱ�Ӽ��䣺���鰴�յ�һά����������Arrays.sort(pairs,(a,b)->(a[0]-b[0]));
	//�ڶ�ά������Arrays.sort(pairs,(a,b)->(a[1]-b[1]));
	//��һά�Ƚ���Arrays.sort(pairs,(a,b)->(b[0]-a[0]));
	//�ڶ�ά�Ƚ���Arrays.sort(pairs,(a,b)->(b[1]-a[1]));
	
	//1. ���鵥��Ԫ�غ����ұȽϵ��㷨�����α�����ÿ�α����ڶ�Ӧ�Ļ����ϸ���
	//2. �������⣺���������ĩ�˵����������Ϊĩ�˵㲻ͬ���ͱ�֤��ĩ�˴���������ֻ����ǰ���꣬��������ֻ��Ҫ
	//ȷ������һ�������ǰ�˵���ڵ���ǰһ�������ĩ�˵㼴�ɡ�ѡ��������βԽС�����������Ŀռ��Խ��ʵ����̰��ѡ��
	//3. ��ά�����������дComparator�ӿڣ���дcompare����
	/*Arrays.sort(intervals, new Comparator<int[]>() {     //Comparator���淺�ʹ�int[]��ʾ��ά����              
        public int compare(int[] interval1, int[] interval2) {//��дcompare����  ��һ��������ʾ��ԭ˳����ǰ��ģ��ڶ������ں����
            return interval1[1] - interval2[1];      //return��һ��������ȥ�ڶ�����������ʾ���򣻵ڶ�������һ����ʾ����                    
        }                 //��ά����ıȽϣ�����[0]��ʾ���յ�һά�ȱȽ�(��)��[1]���յڶ�ά�ȱȽ�(��)
    });*/
	//*****���õ�д����Arrays.sort(pairs,(a,b)->(a[1]-b[1]));
	//������д������lambda���ʽ��Arrays.sort(points,(a,b)->a[1]<b[1]?-1:1)�͵ڶ���һ��һ���ģ��õڶ���������
	//**ע��㣺û��Ӧ��������һ����˵���ǰһ�����Ҷ˵��ڣ�ָ���Ǻ�һ����<ǰһ���ң������ˡ�
	//4. ��ǰ�����������ȳ��ֵľ��ǵ�һ�εģ��Ӻ���ǰ���������ȳ��ֵľ������һ�γ��ֵģ���hashmap�洢���ɡ�
	//5. ����LinkedList�Ĳ��������list.add(index,element)
	
	
	
	
	
	
	
	//455��˫����ָ�������:
    //forѭ��д��
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
					break;  //breakֱ����������forѭ�����������ִ��i++��ѭ������
				}
			}
		}
		return res;	
	}
	
	//whileѭ��д��
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
	
	//135:�����е���Ԫ�غ���������Ԫ����Ƚϵ��㷨���ȴ�ǰ�������ұȽ�һ�Σ��ٴӺ���ǰ��������Ƚ�һ�Ρ�
	public static int candy(int[] ratings) {
		int m=ratings.length;
		int[] candys=new int[m];                                  //java�д����������飬������new
		for(int i=0;i<m;i++) {
			candys[i]=1;
		}
		for(int j=0;j<m-1;j++) {
			if(ratings[j+1]>ratings[j]) candys[j+1]=candys[j]+1;
		}
		for(int s=m-1;s>0;s--) {
			//**ע��㣺�������ǹ��Ѿ����ұ߸��ˣ��ǾͲ���Ҫ���ұ߻�����+1�ˡ�
			if(ratings[s-1]>ratings[s]) candys[s-1]=Math.max(candys[s-1],candys[s]+1);
		}
		int sum=Arrays.stream(candys).sum();                        //����Ԫ����ͣ�Arrays.stream().sum()
		return sum;
	}
	
	//435:���䲻�ص����⣺��ĩ�������Ҳ��ص����ɣ�̰��ѡ�񡣺�һ������˵�**С��**ǰһ���Ҷ˵㣬����Ҫ������
	public static int overlapIntervals(int[][] intervals ) {
		int answer=0;
		int row=intervals.length;                                        //ֱ��.length�����У�����a[0].length
		Arrays.sort(intervals, new Comparator<int[]>() {                 //java�еĶ�ά����������дcompare������ʵ�ְ�������
            public int compare(int[] interval1, int[] interval2) {
                return interval1[1] - interval2[1];                      //���ڶ������򣬾ͱ������±�1����
            }
        });
		int prev=intervals[0][1];
		for(int i=1;i<row;i++) {
			//**ע��㣺û��Ӧ��������һ����˵���ǰһ�����Ҷ˵��ڣ�ָ���Ǻ�һ����<ǰһ���ң������ˡ�
			if(prev>intervals[i][0]) {
				answer++;
				}
			else {
				prev=intervals[i][1];
			}
		}
		return answer;
	}
	
	//605:�ֻ����⣬�����̫���ӣ��Ӽ򵥵������ǣ�ֻҪǰ��Ϊ0���ұ�Ԫ��Ϊ0����0���1���ɣ�
	//�ٿ���һ����ĩ����Ԫ�����⣬00��10��00��01.
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
	
	//452������ը���������ȥ�ص������ƣ����ö�˼����
	public static int overlapIntervals1(int[][] points ) {
		int answer=1;
		int row=points.length;                                        
		 Arrays.sort(points,(a,b)->Integer.compare(a[1],b[1]));    //�������ֵ����ʱ�������ô˷�������װ��Integer.compare
		int prev=points[0][1];
		for(int i=1;i<row;i++) {
			if(prev<points[i][0]) {
				answer++;
				prev=points[i][1];
			}
		}
		return answer;
	}
	
	//763������ַ����ָ�Ƭ����
	//��ͷ��β��������֤���µĸ������������ռ䣬Ȼ�󲻶����䵱�������end,�����������endû�����䣬�Ǿͱ�ʾһ�������ɡ�
	//��һ���������һ��Ԫ�ؿ�ʼ���ɡ�
	//�������䣬��¼�ַ����һ�ε�λ�ã�Ȼ���������������䣬�ָ
	//�����intervals�������䣬����Ҫ�Ƚ�a.end��b.start������ֱ��û��end��������endΪa.end,b.end�����ֵ����
	public static List<Integer> partition(String s){
		int[] last=new int[26];
		int i;                                                  //java���ַ����������飬ֻ�ܵ��ú��������λ���ַ�
		//��hashmap�Ӻ���ǰ�洢���һ�γ��ֵ�λ���Ǹ��Ž�
		for(i=0;i<s.length();i++) {                             //java���ַ�����λ�õ��ַ�������s[i]���÷���ֻ����charAt[i]�������ҵ����ַ�
			last[s.charAt(i)-'a']=i;                            //�ǳ��õķ�������¼ÿ��Сд��ĸ���ֵ����λ�ã���charAt-'a'λ�õ���i
		}
		List<Integer> answer=new ArrayList<Integer>();          //�䳤���飬��List<Integer> answer=new ArrayList<Integer>()
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
	
	//122:�Խת�䣺������Ԫ�ر仯�Ĳ�ֵ����Ԫ��һ�����仯�Ĳ�ֵ�͡��磺A-B-C-D:��ֵΪB-A+C-B+D-C=D-A�͵�������ĩ��ֵ
	//���ཻ����ĺ͵����ֵ��ʵ���Ͼ���һ������֣�����Ϊ1�Ĳ��ཻ����ĺ͵����ֵ���ͱ����޹�
	public static int maxProfit(int[] prices) {
		int profit=0;
		for(int i=0;i<prices.length-1;i++) {
			profit+=Math.max(0, prices[i+1]-prices[i]);
		}
		return profit;
		
	}
	
	//406:���ǳ������������ؽ��������⣺�����������⣺һ���һ��Ԫ�ؽ��򣬵ڶ���Ԫ�����򣬴�Ԫ���ٿ��ǣ�����Ŀ��
	//��߽���������߸ߵ��˶���ǰ�棬���濼�ǲ��밫���ˣ����谫����ki����Ͳ���kiλ�ã���Ϊǰ�涼�Ǳ����ߵģ�����ֱ�Ӳ���kiλ�þ�����������
	public static int[][] reconstrcution(int[][] queueHeight){
		Arrays.sort(queueHeight, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);//��дsort����һ�����򣬵ڶ�������
		LinkedList<int[]> answer=new LinkedList<int[]>();     //���룬���������
		for(int[] s:queueHeight) {
			answer.add(s[1], s);                             //list.add(index,element)�����s[1]ָ�ľ���queueHight[i][1],s��һά������
		}
		return answer.toArray(new int[answer.size()][2]);     //�����ٱ���������  
	}
	
	//665:�仯һ�γ�Ϊ�ǵݼ����飺��Ҫע�⵽�仯һ�κ�ֻ������������֮��ķǵݼ�����Ҫע��֮ǰ������(�������������Ĵ�С���ж�������)
	//Ҳ�����ñ仯���Σ���isSorted���ж����Ρ���Ϊǰһ�����ں�һ��ʱ����ǰһ����ɺ�һ�����������֤����ķǵݼ��ԣ���һ����ǰһ��,
	//�������ı�֤ǰ�沿�ֵķǵݼ��ԣ�����ֻҪ�仯���Σ��ж����������Լ��ɡ�
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