package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

public class binarySearch {
	
	//二分查找：主要题目集中在有序数组和旋转数组。
	//最后一题原理太难了，不好理解，没太大必要。
	
	//*****小总结：
	//1. 开始位置和结束位置放在一起查是不好写代码的，所以我们分开写会更简便，要分两个函数分开查。
	//2. 旋转数组元素可重复时候需要加入进一步判断：才能确定哪部分是递增的
	//当mid和首尾相同时，没办法判断哪边有序，我们只能i++,j--,看看内部的序列是否可判断.
	//3. 旋转数组的另一个注意点：就是判断可能在有序部分时候，注意还要判断一下是否在有序范围内，因为target可能超过
	//范围，跳到了另一边去了。所以一定要在mid到首/尾的范围内，需要加入判断。一共六种情况写代码用else可以归为四种。
	//4. **旋转数组的核心特点：旋转后，一部分是有序的，另一部分虽然无序，但是还是一个旋转数组，这样就可以继续实现二分
	//5. 寻找两个数组的中位数，需要用到鸽巢原理，原理太难了，出题的可能性不大，简单了解一下，看看思路。每次砍掉k/2个元素
	
	
	//*****数组中唯一单独的元素，重点题解法：核心在于将中间元素加入其中一边，数组元素个数奇偶性的判断
	//单独元素只会存在于奇数个数的数组里面(前提是mid放进左/右数组里面)
	//从数组元素个数的奇偶延伸到了判断mid下标奇偶，因为这样好求解；明面上用下标，实际上是元素个数的奇偶判断
	//核心：中间元素如果大于左边小于右边，那就是唯一的；
	//如果中间元素把数组切分成了两个奇数个数的数组，那么mid等于左或者右，将mid加入左/右数组，唯一的那个数必然出现在
	//奇数个元素的数组里面；
	//如果中间元素把数组切分成了两个偶数个数的数组，那么mid依旧需要加入左/右，唯一的那个数依然是在奇数个元素的数组里面
	//如何判断切分为奇偶数个元素的数组，用下标判断：mid为奇数，说明左右个数均为奇数；mid为偶数，说明左右个数均为偶数
	
	
	//*****二分的核心作用：分成两半，但是只搜索一半，降低了复杂度。
	//分治：分为简单点子问题来解决，也会降低复杂度，因为简单子问题复杂度会比大问题的复杂度缩减很多，而且有可能子问题可以
	//并行求解，肯定会降低复杂度。
	
	//*****二分查找：专门针对有序数组来用.
	//大部分都是数组类问题，利用二分来降低复杂度
	//旋转数组最具有代表性的，然后就利用二分（伪二分）找规律
    //最难的题：鸽巢原理	
	
	//*****二分边界问题：
	//1. 左闭右闭：while中为i<=j,i,j的初值，必须是可用范围，如：0-length-1;   （遍历到最后输出的）需要用res存满足的下标mid，初值为-1；不要遍历到最后再输出的，直接内部判断即可
	//2. 左闭右开：while中为i<j,i,j初值，一个可用，一个不可用，如：0-length  ！！！不用这个
	//*****选择第一种，while(i<=j) i=0;j=length-1  i,j均为可用范围下标，while里面是小于等于。
	//*****注意存储mid作为最后的结果返回，所以res=-1,循环中res=mid
	
	
	    //69:x的平方根,向下取整
	    //从1-x中查找即可，有序，用二分加快速度
	    //即求k^2<=x的最大k值
	  public int mySqrt(int x) {
		  if(x==0) return 0;
		  int left=1;
		  int right=x;
		  while(left<=right) {
			  int mid=(left+right)/2;
			  if((long)mid*mid<=x&&(long)((mid+1)*(mid+1))>x) {
				  return mid;
			  }else if((long)mid*mid>x) {
				  right=mid-1;
			  }else {
				  left=mid+1;
			  }
		  }
          return 0; 
	    }
	  
	   //考虑到整数溢出问题：平方数<=,那就可以更新res,直到最后一次更新，即为需要求取的值。利用while循环来求，比我上面的解法要好
	  public int mySqrt1(int x) {
		  int left=0;
		      	int right=x;
		      	int res=-1;
		      	while(left<=right) {
		      		int mid=(left+right)/2;
		      		if((long)mid*mid<=x) {
		      			res=mid;
		      			left=mid+1;
		      		}else {
		      			right=mid-1;
		      		}
		      	}
		      	return res;
      }
	  
	  //34：查找开始和结束位置
	  //分两次二分查找，开始位置：相等时候变右边界；结束位置：相等时候，变左边界，在右边继续查找
	  //要小心，res存放的是<=或者>=,可能会出现根本没有目标值，但是res变了。所以最后要加判断，res对应位置是否为target;
	  //也可能出现res没变，等于-1，结果直接越界，所以还要判断是否为-1
	  public int[] searchRange(int[] nums, int target) {
		  if(nums.length==0) return new int[] {-1,-1};
		  int low=lower(nums,target);
		  int high=higher(nums,target);
		  if(low==-1||high==-1||nums[low]!=target||nums[high]!=target) {
			  return new int[] {-1,-1};
		  }
		  return new int[] {low,high};
	    }
	  
	  public int higher(int[] nums,int target) {
		  int i=0;
		  int j=nums.length-1;
		  int res=-1;
		  while(i<=j) {
			  int mid=(i+j)/2;
			  if(nums[mid]<=target) {
				  res=mid;
				  i=mid+1;
			  }else {
				  j=mid-1;
			  }
		  }
          return res;
	  }
	  
	  public int lower(int[] nums,int target) {
		  int i=0;
		  int j=nums.length-1;
		  int res=-1;
		  while(i<=j) {
			  int mid=(i+j)/2;
			  if(nums[mid]>=target) {
				  res=mid;
				  j=mid-1;
			  }else {
				  i=mid+1;
			  }
		  }
          return res;
	  }
	  
	  //81：旋转数组的搜索变形版
	  //元素可重复：当mid和首尾相同时，没办法判断哪边有序，我们只能i++,j--,看看内部的序列是否可判断.
	  //备注：因为上述情况出现时，首==尾==mid元素，所以nums[mid]==target这个if条件就涵盖了首尾为target的判断。所以可以直接i++,j--
	  //当心！很容易犯的一个错误：在判断属于有序无序部分的时候，对于有序部分是范围判断。而不是单向判断；
	  //有序部分是范围判断，就怕出现target小（大）的过分，直接出范围了，到了右边（左边）去
	  public boolean search(int[] nums, int target) {
		  int i=0;
		  int j=nums.length-1;
		  while(i<=j) {
			  int mid=(i+j)/2;
			  if(nums[mid]==target) return true;                        //涵盖了首=尾=mid
			  if(nums[i]==nums[mid]&&nums[i]==nums[j]) {                //和1的不同就在这，多了一个首尾中相等时候的变换
				  i++;
				  j--;
			  }else if(nums[mid]>=nums[i]) {                           
				  if(target<nums[mid]&&target>=nums[i]) {               //注意：有序部分是范围判断，就怕出现target小的过分，直接出范围了，到了右边去
					  j=mid-1;                                          //*****刚好有序二分中，也是放到这个位置，所以刚好接着往下while
				  }else {
					  i=mid+1;                                     
				  }
			  }else {
				  if(target>nums[mid]&&target<=nums[nums.length-1]) {
					  i=mid+1; 
				  }else {
					  j=mid-1;
				  }
			  }
		  }
		  return false;
	    }
	  
	  //154：(hard,太简单了不像hard题)旋转数组的最小值
	  //和之前的思路是一样的，不过是用一个min记录最小值，如果是升序，把升序中的最小值看看是否小于当前min,小于的话直接存min中，然后循环无序部分即可
	  public int findMin(int[] nums) {
		  int i=0;
		  int j=nums.length-1;
		  int min=Integer.MAX_VALUE;
		  while(i<=j) {
			  int mid=(i+j)/2;
			  //**稍微注意下：中间值可能就是最小值，所以要先把mid和当前最小值比较下；然后再去寻找升序部分最小值比较；然后在无序序列(新的旋转数组)中继续查找
			  if(nums[mid]<min) min=nums[mid];
			  if(nums[i]==nums[mid]&&nums[i]==nums[j]) {
				  i++;
				  j--;
			  }else if(nums[mid]>=nums[i]) {
				  if(nums[i]<min) min=nums[i];                     //有序部分可以直接找到最小值，看是否需要更新，然后下次循环就是无序部分
				  i=mid+1;
			  }else {
				  if(nums[mid]<min) min=nums[mid];
				  j=mid-1;
		  }
		  }
			  return min;

	    }
	  
	  //540：数组中的单独元素
	  //**核心：中间元素如果大于左边小于右边，那就是唯一的；
	  //如果中间元素把数组切分成了两个奇数个数的数组，那么mid等于左或者右，将mid加入左/右数组，唯一的那个数必然出现在
	  //奇数个元素的数组里面；
	  //如果中间元素把数组切分成了两个偶数个数的数组，那么mid依旧需要加入左/右，唯一的那个数依然是在奇数个元素的数组里面
	  //如何判断切分为奇偶数个元素的数组，用下标判断：mid为奇数，说明左右个数均为奇数；mid为偶数，说明左右个数均为偶数
	  //因为是有序数组，所以用二分，二分后mid可能和mid+1,mid-1相等，所以要分两类，再分奇偶性
	  public static int singleNonDuplicate(int[] nums) {
		  int l=0;
		  int r=nums.length-1;
		  while(l<=r) {
			  int mid=(l+r)/2;
			  if(mid+1<=nums.length-1&&nums[mid]==nums[mid+1]) {
				  if(mid%2==0||mid==0) {                                //下标是偶数，那么独立元素在右侧，注意要剔除本次相等的两个元素，是mid+2
					  l=mid+2;
				  }else {
					  r=mid-1;                                          //下标是奇数，那么独立元素在左侧
				  }
			  }else if(mid-1>=0&&nums[mid-1]==nums[mid]) {
				  if((mid-1)%2==0||mid==0) {                            //*****%求余是最近匹配原则，所以mid-1一定要加括号，不然直接出错
					  l=mid+1;
				  }else {
					  r=mid-2;
				  }
			  }else {
				  return nums[mid];
			  }
		  }
		  return 0;
	    }
	  
	  //4：（hard,理解，上课教过但是实现很难，所以用后一个鸽巢原理来实现）两数组的中位数
	  //老师上课教过：先求两个数组（m>n）的中位数，然后把长度大的截取(n/2),长度小的也是n/2，然后继续中位数。
	  //原理：利用中位数的性质，取mid时候，比较大小，mid大的，他的右侧不仅大于该数组左侧，而且大于另一个数组的左侧，所以大于了一半元素，肯定不是中位数；同理得到mid小的那部分
	  //但是为了保持中位数的性质，我们每次只能在两个数组里，截取相同的长度（因为中位数，必须保持左右侧数目相同）
	  //！！课上的这种思想解法，可行，但是还是要继续分奇偶数讨论。以下的代码只能满足两个数组都是奇数个数时的情况
	  public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
		  if(nums1.length==nums2.length&&nums1.length==0) return (double)0;
		  if(nums1.length==0) {
			  if(nums2.length%2==0) return (double)(nums2[nums2.length/2]+nums2[nums2.length/2-1])/2.0;
			  else {
				  return (double)nums2[nums2.length/2];
			  }
		  }
		  if(nums2.length==0) {
			  if(nums1.length%2==0) return (double)(nums1[nums1.length/2]+nums1[nums1.length/2-1])/2.0;
			  else {
				  return (double)nums1[nums1.length/2];
			  }
		  }
		  List<Integer> list1=Arrays.stream(nums1).boxed().collect(Collectors.toList());
		  List<Integer> list2=Arrays.stream(nums2).boxed().collect(Collectors.toList());
		  int mid1=(list1.size()-1)/2;
		  int mid2=(list2.size()-1)/2;                                                    //直接取mid只能对两个都是奇数的情况成立，对于偶数不成立
		  while(list1.get(mid1)!=list2.get(mid2)&&list1.size()!=1&&list2.size()!=1) {
		  if(list1.get(mid1)>list2.get(mid2)) {                                                  
			  if(list1.size()>=list2.size()) {
				  list1=list1.subList(0, list1.size()-list2.size()/2);
				  list2=list2.subList(mid2, list2.size());
			  }else {
				  list2=list2.subList(list1.size()/2,list2.size());
				  list1=list1.subList(0, mid1+1);
			  }
		  }
		  if(list1.get(mid1)<list2.get(mid2)) {
			  if(list1.size()>=list2.size()) {
				  list1=list1.subList(list2.size()/2, list1.size());
				  list2=list2.subList(0, mid2+1);                             //subList是不是左闭右开类型的，这个要注意下？？？
			  }else {
				  list2=list2.subList(0, list2.size()-list1.size()/2);
				  list1=list1.subList(mid1, list1.size());
			  }
		  }
		  mid1=(list1.size()-1)/2;
		  mid2=(list2.size()-1)/2;
		  }
		  if(list1.get(mid1)==list2.get(mid2)) return (double)mid1;
		  if(list1.size()==1) {
			  list2.add(list1.get(0));
			  Collections.sort(list2);
			  if(list2.size()%2==0) return (double)(list2.get(list2.size()/2)+list2.get(list2.size()/2-1))/2.0;
			  else {
				  return (double)list2.get(list2.size()/2);
			  }
		  }
		  if(list2.size()==1) {
			  list1.add(list2.get(0));
			  Collections.sort(list1);
			  if(list1.size()%2==0) return (double)(list1.get(list1.size()/2)+list1.get(list1.size()/2-1))/2.0;
			  else {
				  return (double)list1.get(list1.size()/2);
			  }
		  }
		  return (double)mid1;
	    }
	  
	  //大佬解法：鸽笼原理
	  //非常巧妙：首先本题转化为了m+n个元素里面找中位数：奇数的话，就是第（m+n+1）/2小的；偶数的话，就是第（m+n+1）/2和（m+n+2）/2小的元素的平均值
	  //所以只要实现一个函数，能找到两个有序数组中第k小的元素即可。
	  //鸽巢原理：两个数组必然有一个是存在超过k/2个比该元素小的，反证法：如果两个都不存在k/2个比之小的，那就直接不满足
	  //将存在的这个数组前面k/2个元素删除，那就变成了两个数组找第k-k/2小的元素。接下来论证如何找到这个待砍数组
	  //比较两个数组的第k/2小的元素，谁更小，谁就是待砍数组。反证法：如果更小的那个不是，那就说明不存在超过k/2个小的，那么大的那个更不存在了，直接出错。
	  //每次砍掉一半，也是折半查找；砍到k==1,就是比较开头元素最小即可
	  
	  //转化为找第K小元素的问题
	  public double findMedianSortedArrays(int[] nums1,int[] nums2) {
		  int length1=nums1.length;
		  int length2=nums2.length;
		  int totalLength=length1+length2;
		  if(totalLength%2==1) {
			  int midIndex=totalLength/2;
			  double median=getKthElement(nums1,nums2,midIndex+1);
			  return median;
		  }else {
			  int midIndex1=totalLength/2-1;
			  int midIndex2=totalLength/2;
			  double median=(getKthElement(nums1,nums2,midIndex1+1)+getKthElement(nums1,nums2,midIndex2+1))/2.0;
			  return median;
		  }
	  }
	  
	  //第K小的实现代码
	  public int getKthElement(int[] nums1,int[] nums2,int k) {
		  int length1=nums1.length;
		  int length2=nums2.length;
		  int index1=0;
		  int index2=0;
		  while(true) {
			  if(index1==length1) {                                        //如果某一个砍完了，那就直接另一个数组求第K小即可
				  return nums2[index2+k-1];
			  }
			  if(index2==length2) {
				  return nums1[index1+k-1];
			  }
			  if(k==1) {                                                   //如果只需要最后一个了，那就直接比较开头元素
				  return Math.min(nums1[index1], nums2[index2]);
			  }
			  int half=k/2;
			  int newIndex1=Math.min(index1+half, length1)-1;              //要小心砍完的情况，所以要用min函数
			  int newIndex2=Math.min(index2+half, length2)-1;
			  int pivot1=nums1[newIndex1];                                 //比较第k/2个元素
			  int pivot2=nums2[newIndex2];
			  if(pivot1<=pivot2) {
				  k-=(newIndex1-index1+1);
				  index1=newIndex1+1;
			  }else {
				  k-=(newIndex2-index2+1);
				  index2=newIndex2+1;
			  }
		  }
	  }
	  

	  
	  public static void main(String[] args) {
		  int[] nums1= {1,3};
		  int[] nums2= {2};
		  System.out.print(findMedianSortedArrays1(nums1,nums2));
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
