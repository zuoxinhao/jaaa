package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;


public class sort {
	
	
	//排序问题总结：
	//1. 必须掌握必须手撕的：快排，归并排，很容易就考到。插入排序，冒泡排序，都比较简单，思路知道即可。
	//2. 桶排序的两种：没有负数直接一次计数；有负数需要先入哈希表再入动态数组
	//凡是数组计算频率的，那就要想到桶排序。
	
	//*****桶排序总结：
	//1. 简单的桶排序：原数组值作为新数组下标，然后新数组值统计出现次数，然后就可以得到频率。再排序，依次靠下标遍历原数组找值即可
	//2. 如果原数组值有负数存在，也就不能直接映射下标了。我们的做法是：两次存放法：
	//*****先放入哈希表统计次数，然后把哈希表中value作为新数组的下标，key为新数组的值(有可能value重复，所以设立为二维list)
	//这样子就意味着数组下标是频率，而且已经排好序了，直接取即可。
	//重点题347：求取频次出现最高的k个值：
	 public List<Integer> topKFrequent347(int[] nums, int k) {
		 HashMap<Integer,Integer> hashmap=new HashMap<Integer,Integer>();
		 for(int i=0;i<nums.length;i++) {
			 hashmap.put(nums[i], hashmap.getOrDefault(nums[i], 0)+1);
		 }
		 ArrayList<ArrayList<Integer>> res=new ArrayList<ArrayList<Integer>>();
		 Iterator iter=hashmap.entrySet().iterator();
		 while(iter.hasNext()) {
			 Map.Entry entry=(Map.Entry)iter.next();
			 int i=(Integer)entry.getValue();
			 if(res.get(i)==null) {
				 ArrayList<Integer> list=new ArrayList<Integer>();
				 res.add(i,list);
			 }
			 res.get(i).add((Integer)entry.getKey());
		 }
		 ArrayList<Integer> ans=new ArrayList<Integer>();
		 for(int j=res.size()-1;j>=0;j--) {
			 if(res.get(j)==null) continue;
			 for(int m=0;m<res.get(j).size();m++) {
				 ans.add(res.get(j).get(m));
				 k--;
				 if(k==0) return ans;
			 }
		 }
		 return ans;
	 }
	
	//*****排序类问题：
	//1. 快排，归并排的变型题
	//2. 桶排序，计数，频次问题，有些时候单纯的桶排序无法解决，需要借助哈希表来实现更好
	
	//快速排序的实现：
	//快排：先确定枢轴元素的位置，根据位置划分左右两个部分递归；确定位置：从后前找比之小的交换，从前向后找比之大的交换
	//*****快排代码实现的注意点：
	//1. 快排调用的时候才需要指定左右边界初始值(0,length-1),所以我们在方法实现中不需要考虑初始值，
	//这是后面调用时候才需要去指定的。quickSort方法里面需要三个参数，数组，左边界，右边界
	//2. 快排方法实现的细节：递归出口，partition划分函数，递归左，递归右
	//3. *****partition划分函数里面有个细节，就是while循环里面i<j，这里不能加等于号，防止最后一次发生数组越界错误。
	//4. 从后向前找比之小，然后交换，交换不需要temp,因为枢轴元素的值我们可以先存到key里面，然后直接放就可以了。
	//5. partition中最后return i和j是一样的，因为while结束的时候i==j，所以都可以返回
	//6. *****优化：把交换改成覆盖，我们每次找到大/小的元素，直接把它覆盖到之前需要交换的元素，后面key不用每次都移动位置了
	//我们把key值最后直接放在对应位置上，这样就节省了时间。
	public void quickSort(int[] nums,int l,int r) {
		if(l>=r) return ;
		int partition=parts(nums,l,r);
		quickSort(nums,l,partition-1);
		quickSort(nums,partition+1,r);
	}
	
	public int parts(int[] nums,int l,int r) {
		int key=nums[l];
		int i=l;
		int j=r;
		while(i<j) {
			while(i<j&&nums[j]>=key) j--;
			nums[i]=nums[j];
			//优化：注掉nums[j]=key;
			while(i<j&&nums[i]<=key) i++;
			nums[j]=nums[i];
			//nums[i]=key;
		}
		nums[i]=key;
		return i;
	}
	
	
	//归并排序的实现：
	//归并的思路：递归出口，平均划分左右并递归，最后合并。归并需要借助临时数组temp[] 来存储每次归并的结果
	//*****归并的细节知识点：
	//1. **归并一开始是不停的分割一半，分割递归的出口应该是只剩一个元素，即l==r,所以递归出口必须是l>=r,必须加等于号
	//2. merge归并的过程其实就是两个有序数组合并，一个是l到mid,另一个是mid+1到r,用临时数组进行取更小的放入即可
	
	//归并和快排的重点区分：
	//1. 快排的出口可等于可不等于，归并的递归出口必须加上等于号
	//2. 快排是确定划分元素的位置后，就不带他玩了，直接左右递归是(l,p-1)(p+1,r),但是归并排序是按照中间节点分割区间
	//分割完之后必须还带上中间元素一起排序，所以是(l，mid)(mid+1,r)
	//3. 一个是先定partition位置再递归，一个是先递归左右分割再merge.先序遍历和后序遍历的区别。
	
	
	public void mergeSort(int[] nums,int l,int r,int[] temp) {
		if(l>=r) return ; //**必须加等于号
		int mid=(l+r)/2;
		mergeSort(nums,l,mid,temp);
		mergeSort(nums,mid+1,r,temp);
		merge(nums,l,mid,r,temp);
	}
	
	public void merge(int[] nums,int l,int mid,int r,int[] temp) {
		int i=l;
		int j=mid+1;
		int k=l;
		while(i<=mid&&j<=r) {
			if(nums[i]<=nums[j]) {
				temp[k++]=nums[i++];
			}else{
				temp[k++]=nums[j++];
			}
		}
		if(j>r) {
			while(i<=mid) {
				temp[k++]=nums[i++];
			}
		}
		if(i>mid) {
			while(j<=r) {
				temp[k++]=nums[j++];
			}
		}
		
		for(int m=l;m<=r;m++) {
			nums[m]=temp[m];
		}
	}
	//调用：
	//quickSort(nums,0,nums.length-1);
	//mergeSort(nums,0,nums.length-1,temp);
	
	
	
	//215:寻找第k大的元素
	//利用快排的思想划分递归
	 public int findKthLargest(int[] nums, int k) {
		 return quickSort215(nums,0,nums.length-1,k);

	    }
	 
	 public int quickSort215(int[] nums,int start,int end,int k) {
		 int partition=part(nums,start,end);
		 //只有递归出口变了下，其他的基本一样的思路。
		 if(k==partition+1) return nums[partition];
		 return partition+1>k?quickSort215(nums,start,partition-1,k):quickSort215(nums,partition+1,end,k);
	 }
	 
	 public int part(int[] nums,int start,int end) {
		 int pivot=nums[start];
		 while(start<end) {
			 while(start<end&&pivot>=nums[end]) end--;
			 nums[start]=nums[end];
			 while(start<end&&nums[start]>=pivot) start++;
			 nums[end]=nums[start];
		 }
		 nums[start]=pivot;                 
		 return start;
	 }
	 
	 //347:前k个高频元素(见开头解答)
	 //桶排序：为每个值设立一个桶，桶内统计出现的次数。也就是之前常用的，数组下标存值，数组值存次数这种做法。叫做桶排序。
	 //完成第一次桶排序后，可以得到频次，可以再来一次桶排序，以频次为下标，以值（list）为值，这样从后向前遍历，得到k个大的
	 //！！第一步，用list或者数组都不太好，为什么？因为统计完后需要排序的，会打乱数组顺序，导致没办法记录下标了。
	 //！！但是可以拷贝一下count数组，然后先找到频次高的频次数，然后去原count里面找下标就可以。空间用的比较多了
	 //以下的解答只能针对非负整数：用数组拷贝，排序，取频次取下标
	 //*****如果非要用数组拷贝排序，那就先找数组最小值，然后为负数的话归为0，集体增加大小，转化为非负整数数组来求解即可。
	 
	 public int[] topKFrequent1(int[] nums, int k) {
          int max=Integer.MIN_VALUE;
          for(int i=0;i<nums.length;i++) {
        	  max=Math.max(max, nums[i]);
          }
          int[] number=new int[max+1];
          int[] number1=new int[max+1];
          boolean[] flag=new boolean[max+1];
          for(int j=0;j<nums.length;j++) {
        	  number[nums[j]]++;
        	  number1[nums[j]]++;
          }
          Arrays.sort(number1);
          List<Integer> res=new ArrayList<>();
          for(int m=number1.length-1;m>=number1.length-k;m--) {
        	  for(int f=0;f<number.length;f++) {
        		  if(number[f]==number1[m]&&flag[f]==false) {
        			  res.add(f);
        			  flag[f]=true;
        			  break;
        		  }
        	  }
          }
          return res.stream().mapToInt(i->i).toArray();
	    }
	 
	 
	 //这个方法不好：既要存值，又要存次数，用hashmap
	 //先用hashmap存值和频次，然后用collections的函数求最大value值，然后通过value值找到对应的key用的迭代器。然后就是加入答案并删除key.
	  public int[] topKFrequent(int[] nums, int k) {
		  int[] res=new int[k];
		  HashMap<Integer,Integer> hashmap=new HashMap<>();
		  for(int i=0;i<nums.length;i++) {
			  hashmap.put(nums[i], hashmap.getOrDefault(nums[i], 0)+1);
		  }
		  int c=0;
		  while(hashmap.size()!=0) {
			  int max=Collections.max(hashmap.values());                      //找到最大value值
			  int maxKey=searchValue(hashmap,max);
			  res[c]=maxKey;
			  hashmap.remove(maxKey);
			  c++;
			  if(c==k) return res;
		  }
         return res;
	    }
	  
	  public int searchValue(HashMap<Integer,Integer> hashmap,int max) {                                              
		   Iterator iter=hashmap.entrySet().iterator();                          
		   while(iter.hasNext()) {
			   Map.Entry entry=(Map.Entry) iter.next();                      
			   Integer key=(Integer)entry.getKey();
			   Integer val=(Integer)entry.getValue();
			   if(hashmap.get(key)==max) {
				   return key;
			   }
		   }
		   return 0;
	   }
	  
	  //451：字符串降序排序
	  //和上一题一摸一样，简单修改下hashmap即可
	  public String frequencySort(String s) {
		  StringBuilder res=new StringBuilder();
		  HashMap<Character,Integer> hashmap=new HashMap<>();
		  for(int i=0;i<s.length();i++) {
			  hashmap.put(s.charAt(i), hashmap.getOrDefault(s.charAt(i), 0)+1);
		  }
		  while(hashmap.size()!=0) {
			  int max=Collections.max(hashmap.values());                      
			  Character maxKey=searchC(hashmap,max);
			  for(int j=0;j<max;j++) {
				  res.append(maxKey);
			  }
			  hashmap.remove(maxKey);
		  }
         return res.toString();
	    }
	  
	  public Character searchC(HashMap<Character,Integer> hashmap,int max) {                                              
		   Iterator iter=hashmap.entrySet().iterator();                          
		   while(iter.hasNext()) {
			   Map.Entry entry=(Map.Entry) iter.next();                      
			   Character key=(Character)entry.getKey();
			   Integer val=(Integer)entry.getValue();
			   if(hashmap.get(key)==max) {
				   return key;
			   }
		   }
		   return ' ';
	   }
	  
	  //75：三色排序
	  //最简单的：统计三个颜色的个数，然后从头到尾输入即可，需要两趟
	  //第二种：单指针，第一趟把为0的放入开头，第二趟，把为1的放入之后的开头即可
	  //第三种：双指针，只遍历一趟（挺有意思的），0交换，1交换：1交换和第二种一样就直接交换然后++，0交换不仅要交换，两个指针都后移，而且如果0指针小于1指针的话
	  //代表换出去的可能是1，所以还要将当前元素1和1指针交换，确保1还在前面
	  public void sortColors(int[] nums) {
		  int ptr0=0;
		  int ptr1=0;
		  for(int i=0;i<nums.length;i++) {
			  if(nums[i]==1) {
				  int temp=nums[i];
				  nums[i]=nums[ptr1];
				  nums[ptr1]=temp;
				  ptr1++;
			  }else if(nums[i]==0) {
				  int temp=nums[i];
				  nums[i]=nums[ptr0];
				  nums[ptr0]=temp;
                  if(ptr0<ptr1) {
                	  temp=nums[i];
    				  nums[i]=nums[ptr1];
    				  nums[ptr1]=temp;
                  }
                  ptr0++;
                  ptr1++; 
			  }
		  }
	    }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  public static void main(String[] args) {
		  int[] nums= {1,1,1,2,2,3};
		  int k=2;
		  System.out.print(1);
	  }
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

