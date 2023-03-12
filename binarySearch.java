package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

public class binarySearch {
	
	//���ֲ��ң���Ҫ��Ŀ�����������������ת���顣
	//���һ��ԭ��̫���ˣ�������⣬û̫���Ҫ��
	
	//*****С�ܽ᣺
	//1. ��ʼλ�úͽ���λ�÷���һ����ǲ���д����ģ��������Ƿֿ�д�����㣬Ҫ�����������ֿ��顣
	//2. ��ת����Ԫ�ؿ��ظ�ʱ����Ҫ�����һ���жϣ�����ȷ���Ĳ����ǵ�����
	//��mid����β��ͬʱ��û�취�ж��ı���������ֻ��i++,j--,�����ڲ��������Ƿ���ж�.
	//3. ��ת�������һ��ע��㣺�����жϿ��������򲿷�ʱ��ע�⻹Ҫ�ж�һ���Ƿ�������Χ�ڣ���Ϊtarget���ܳ���
	//��Χ����������һ��ȥ�ˡ�����һ��Ҫ��mid����/β�ķ�Χ�ڣ���Ҫ�����жϡ�һ���������д������else���Թ�Ϊ���֡�
	//4. **��ת����ĺ����ص㣺��ת��һ����������ģ���һ������Ȼ���򣬵��ǻ���һ����ת���飬�����Ϳ��Լ���ʵ�ֶ���
	//5. Ѱ�������������λ������Ҫ�õ��볲ԭ��ԭ��̫���ˣ�����Ŀ����Բ��󣬼��˽�һ�£�����˼·��ÿ�ο���k/2��Ԫ��
	
	
	//*****������Ψһ������Ԫ�أ��ص���ⷨ���������ڽ��м�Ԫ�ؼ�������һ�ߣ�����Ԫ�ظ�����ż�Ե��ж�
	//����Ԫ��ֻ�������������������������(ǰ����mid�Ž���/����������)
	//������Ԫ�ظ�������ż���쵽���ж�mid�±���ż����Ϊ��������⣻���������±꣬ʵ������Ԫ�ظ�������ż�ж�
	//���ģ��м�Ԫ������������С���ұߣ��Ǿ���Ψһ�ģ�
	//����м�Ԫ�ذ������зֳ��������������������飬��ômid����������ң���mid������/�����飬Ψһ���Ǹ�����Ȼ������
	//������Ԫ�ص��������棻
	//����м�Ԫ�ذ������зֳ�������ż�����������飬��ômid������Ҫ������/�ң�Ψһ���Ǹ�����Ȼ����������Ԫ�ص���������
	//����ж��з�Ϊ��ż����Ԫ�ص����飬���±��жϣ�midΪ������˵�����Ҹ�����Ϊ������midΪż����˵�����Ҹ�����Ϊż��
	
	
	//*****���ֵĺ������ã��ֳ����룬����ֻ����һ�룬�����˸��Ӷȡ�
	//���Σ���Ϊ�򵥵��������������Ҳ�ή�͸��Ӷȣ���Ϊ�������⸴�ӶȻ�ȴ�����ĸ��Ӷ������ܶ࣬�����п������������
	//������⣬�϶��ή�͸��Ӷȡ�
	
	//*****���ֲ��ң�ר�����������������.
	//�󲿷ֶ������������⣬���ö��������͸��Ӷ�
	//��ת��������д����Եģ�Ȼ������ö��֣�α���֣��ҹ���
    //���ѵ��⣺�볲ԭ��	
	
	//*****���ֱ߽����⣺
	//1. ����ұգ�while��Ϊi<=j,i,j�ĳ�ֵ�������ǿ��÷�Χ���磺0-length-1;   ���������������ģ���Ҫ��res��������±�mid����ֵΪ-1����Ҫ���������������ģ�ֱ���ڲ��жϼ���
	//2. ����ҿ���while��Ϊi<j,i,j��ֵ��һ�����ã�һ�������ã��磺0-length  �������������
	//*****ѡ���һ�֣�while(i<=j) i=0;j=length-1  i,j��Ϊ���÷�Χ�±꣬while������С�ڵ��ڡ�
	//*****ע��洢mid��Ϊ���Ľ�����أ�����res=-1,ѭ����res=mid
	
	
	    //69:x��ƽ����,����ȡ��
	    //��1-x�в��Ҽ��ɣ������ö��ּӿ��ٶ�
	    //����k^2<=x�����kֵ
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
	  
	   //���ǵ�����������⣺ƽ����<=,�ǾͿ��Ը���res,ֱ�����һ�θ��£���Ϊ��Ҫ��ȡ��ֵ������whileѭ�����󣬱�������ĽⷨҪ��
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
	  
	  //34�����ҿ�ʼ�ͽ���λ��
	  //�����ζ��ֲ��ң���ʼλ�ã����ʱ����ұ߽磻����λ�ã����ʱ�򣬱���߽磬���ұ߼�������
	  //ҪС�ģ�res��ŵ���<=����>=,���ܻ���ָ���û��Ŀ��ֵ������res���ˡ��������Ҫ���жϣ�res��Ӧλ���Ƿ�Ϊtarget;
	  //Ҳ���ܳ���resû�䣬����-1�����ֱ��Խ�磬���Ի�Ҫ�ж��Ƿ�Ϊ-1
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
	  
	  //81����ת������������ΰ�
	  //Ԫ�ؿ��ظ�����mid����β��ͬʱ��û�취�ж��ı���������ֻ��i++,j--,�����ڲ��������Ƿ���ж�.
	  //��ע����Ϊ�����������ʱ����==β==midԪ�أ�����nums[mid]==target���if�����ͺ�������βΪtarget���жϡ����Կ���ֱ��i++,j--
	  //���ģ������׷���һ���������ж������������򲿷ֵ�ʱ�򣬶������򲿷��Ƿ�Χ�жϡ������ǵ����жϣ�
	  //���򲿷��Ƿ�Χ�жϣ����³���targetС���󣩵Ĺ��֣�ֱ�ӳ���Χ�ˣ������ұߣ���ߣ�ȥ
	  public boolean search(int[] nums, int target) {
		  int i=0;
		  int j=nums.length-1;
		  while(i<=j) {
			  int mid=(i+j)/2;
			  if(nums[mid]==target) return true;                        //��������=β=mid
			  if(nums[i]==nums[mid]&&nums[i]==nums[j]) {                //��1�Ĳ�ͬ�����⣬����һ����β�����ʱ��ı任
				  i++;
				  j--;
			  }else if(nums[mid]>=nums[i]) {                           
				  if(target<nums[mid]&&target>=nums[i]) {               //ע�⣺���򲿷��Ƿ�Χ�жϣ����³���targetС�Ĺ��֣�ֱ�ӳ���Χ�ˣ������ұ�ȥ
					  j=mid-1;                                          //*****�պ���������У�Ҳ�Ƿŵ����λ�ã����Ըպý�������while
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
	  
	  //154��(hard,̫���˲���hard��)��ת�������Сֵ
	  //��֮ǰ��˼·��һ���ģ���������һ��min��¼��Сֵ����������򣬰������е���Сֵ�����Ƿ�С�ڵ�ǰmin,С�ڵĻ�ֱ�Ӵ�min�У�Ȼ��ѭ�����򲿷ּ���
	  public int findMin(int[] nums) {
		  int i=0;
		  int j=nums.length-1;
		  int min=Integer.MAX_VALUE;
		  while(i<=j) {
			  int mid=(i+j)/2;
			  //**��΢ע���£��м�ֵ���ܾ�����Сֵ������Ҫ�Ȱ�mid�͵�ǰ��Сֵ�Ƚ��£�Ȼ����ȥѰ�����򲿷���Сֵ�Ƚϣ�Ȼ������������(�µ���ת����)�м�������
			  if(nums[mid]<min) min=nums[mid];
			  if(nums[i]==nums[mid]&&nums[i]==nums[j]) {
				  i++;
				  j--;
			  }else if(nums[mid]>=nums[i]) {
				  if(nums[i]<min) min=nums[i];                     //���򲿷ֿ���ֱ���ҵ���Сֵ�����Ƿ���Ҫ���£�Ȼ���´�ѭ���������򲿷�
				  i=mid+1;
			  }else {
				  if(nums[mid]<min) min=nums[mid];
				  j=mid-1;
		  }
		  }
			  return min;

	    }
	  
	  //540�������еĵ���Ԫ��
	  //**���ģ��м�Ԫ������������С���ұߣ��Ǿ���Ψһ�ģ�
	  //����м�Ԫ�ذ������зֳ��������������������飬��ômid����������ң���mid������/�����飬Ψһ���Ǹ�����Ȼ������
	  //������Ԫ�ص��������棻
	  //����м�Ԫ�ذ������зֳ�������ż�����������飬��ômid������Ҫ������/�ң�Ψһ���Ǹ�����Ȼ����������Ԫ�ص���������
	  //����ж��з�Ϊ��ż����Ԫ�ص����飬���±��жϣ�midΪ������˵�����Ҹ�����Ϊ������midΪż����˵�����Ҹ�����Ϊż��
	  //��Ϊ���������飬�����ö��֣����ֺ�mid���ܺ�mid+1,mid-1��ȣ�����Ҫ�����࣬�ٷ���ż��
	  public static int singleNonDuplicate(int[] nums) {
		  int l=0;
		  int r=nums.length-1;
		  while(l<=r) {
			  int mid=(l+r)/2;
			  if(mid+1<=nums.length-1&&nums[mid]==nums[mid+1]) {
				  if(mid%2==0||mid==0) {                                //�±���ż������ô����Ԫ�����Ҳ࣬ע��Ҫ�޳�������ȵ�����Ԫ�أ���mid+2
					  l=mid+2;
				  }else {
					  r=mid-1;                                          //�±�����������ô����Ԫ�������
				  }
			  }else if(mid-1>=0&&nums[mid-1]==nums[mid]) {
				  if((mid-1)%2==0||mid==0) {                            //*****%���������ƥ��ԭ������mid-1һ��Ҫ�����ţ���Ȼֱ�ӳ���
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
	  
	  //4����hard,��⣬�Ͽν̹�����ʵ�ֺ��ѣ������ú�һ���볲ԭ����ʵ�֣����������λ��
	  //��ʦ�Ͽν̹��������������飨m>n������λ����Ȼ��ѳ��ȴ�Ľ�ȡ(n/2),����С��Ҳ��n/2��Ȼ�������λ����
	  //ԭ��������λ�������ʣ�ȡmidʱ�򣬱Ƚϴ�С��mid��ģ������Ҳ಻�����ڸ�������࣬���Ҵ�����һ���������࣬���Դ�����һ��Ԫ�أ��϶�������λ����ͬ��õ�midС���ǲ���
	  //����Ϊ�˱�����λ�������ʣ�����ÿ��ֻ���������������ȡ��ͬ�ĳ��ȣ���Ϊ��λ�������뱣�����Ҳ���Ŀ��ͬ��
	  //�������ϵ�����˼��ⷨ�����У����ǻ���Ҫ��������ż�����ۡ����µĴ���ֻ�������������鶼����������ʱ�����
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
		  int mid2=(list2.size()-1)/2;                                                    //ֱ��ȡmidֻ�ܶ����������������������������ż��������
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
				  list2=list2.subList(0, mid2+1);                             //subList�ǲ�������ҿ����͵ģ����Ҫע���£�����
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
	  
	  //���нⷨ������ԭ��
	  //�ǳ�������ȱ���ת��Ϊ��m+n��Ԫ����������λ���������Ļ������ǵڣ�m+n+1��/2С�ģ�ż���Ļ������ǵڣ�m+n+1��/2�ͣ�m+n+2��/2С��Ԫ�ص�ƽ��ֵ
	  //����ֻҪʵ��һ�����������ҵ��������������е�kС��Ԫ�ؼ��ɡ�
	  //�볲ԭ�����������Ȼ��һ���Ǵ��ڳ���k/2���ȸ�Ԫ��С�ģ���֤�������������������k/2����֮С�ģ��Ǿ�ֱ�Ӳ�����
	  //�����ڵ��������ǰ��k/2��Ԫ��ɾ�����Ǿͱ�������������ҵ�k-k/2С��Ԫ�ء���������֤����ҵ������������
	  //�Ƚ���������ĵ�k/2С��Ԫ�أ�˭��С��˭���Ǵ������顣��֤���������С���Ǹ����ǣ��Ǿ�˵�������ڳ���k/2��С�ģ���ô����Ǹ����������ˣ�ֱ�ӳ���
	  //ÿ�ο���һ�룬Ҳ���۰���ң�����k==1,���ǱȽϿ�ͷԪ����С����
	  
	  //ת��Ϊ�ҵ�KСԪ�ص�����
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
	  
	  //��KС��ʵ�ִ���
	  public int getKthElement(int[] nums1,int[] nums2,int k) {
		  int length1=nums1.length;
		  int length2=nums2.length;
		  int index1=0;
		  int index2=0;
		  while(true) {
			  if(index1==length1) {                                        //���ĳһ�������ˣ��Ǿ�ֱ����һ���������KС����
				  return nums2[index2+k-1];
			  }
			  if(index2==length2) {
				  return nums1[index1+k-1];
			  }
			  if(k==1) {                                                   //���ֻ��Ҫ���һ���ˣ��Ǿ�ֱ�ӱȽϿ�ͷԪ��
				  return Math.min(nums1[index1], nums2[index2]);
			  }
			  int half=k/2;
			  int newIndex1=Math.min(index1+half, length1)-1;              //ҪС�Ŀ�������������Ҫ��min����
			  int newIndex2=Math.min(index2+half, length2)-1;
			  int pivot1=nums1[newIndex1];                                 //�Ƚϵ�k/2��Ԫ��
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
