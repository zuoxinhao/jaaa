package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;


public class sort {
	
	
	//���������ܽ᣺
	//1. �������ձ�����˺�ģ����ţ��鲢�ţ������׾Ϳ�������������ð�����򣬶��Ƚϼ򵥣�˼·֪�����ɡ�
	//2. Ͱ��������֣�û�и���ֱ��һ�μ������и�����Ҫ�����ϣ�����붯̬����
	//�����������Ƶ�ʵģ��Ǿ�Ҫ�뵽Ͱ����
	
	//*****Ͱ�����ܽ᣺
	//1. �򵥵�Ͱ����ԭ����ֵ��Ϊ�������±꣬Ȼ��������ֵͳ�Ƴ��ִ�����Ȼ��Ϳ��Եõ�Ƶ�ʡ����������ο��±����ԭ������ֵ����
	//2. ���ԭ����ֵ�и������ڣ�Ҳ�Ͳ���ֱ��ӳ���±��ˡ����ǵ������ǣ����δ�ŷ���
	//*****�ȷ����ϣ��ͳ�ƴ�����Ȼ��ѹ�ϣ����value��Ϊ��������±꣬keyΪ�������ֵ(�п���value�ظ�����������Ϊ��άlist)
	//�����Ӿ���ζ�������±���Ƶ�ʣ������Ѿ��ź����ˣ�ֱ��ȡ���ɡ�
	//�ص���347����ȡƵ�γ�����ߵ�k��ֵ��
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
	
	//*****���������⣺
	//1. ���ţ��鲢�ŵı�����
	//2. Ͱ���򣬼�����Ƶ�����⣬��Щʱ�򵥴���Ͱ�����޷��������Ҫ������ϣ����ʵ�ָ���
	
	//���������ʵ�֣�
	//���ţ���ȷ������Ԫ�ص�λ�ã�����λ�û��������������ֵݹ飻ȷ��λ�ã��Ӻ�ǰ�ұ�֮С�Ľ�������ǰ����ұ�֮��Ľ���
	//*****���Ŵ���ʵ�ֵ�ע��㣺
	//1. ���ŵ��õ�ʱ�����Ҫָ�����ұ߽��ʼֵ(0,length-1),���������ڷ���ʵ���в���Ҫ���ǳ�ʼֵ��
	//���Ǻ������ʱ�����Ҫȥָ���ġ�quickSort����������Ҫ�������������飬��߽磬�ұ߽�
	//2. ���ŷ���ʵ�ֵ�ϸ�ڣ��ݹ���ڣ�partition���ֺ������ݹ��󣬵ݹ���
	//3. *****partition���ֺ��������и�ϸ�ڣ�����whileѭ������i<j�����ﲻ�ܼӵ��ںţ���ֹ���һ�η�������Խ�����
	//4. �Ӻ���ǰ�ұ�֮С��Ȼ�󽻻�����������Ҫtemp,��Ϊ����Ԫ�ص�ֵ���ǿ����ȴ浽key���棬Ȼ��ֱ�ӷžͿ����ˡ�
	//5. partition�����return i��j��һ���ģ���Ϊwhile������ʱ��i==j�����Զ����Է���
	//6. *****�Ż����ѽ����ĳɸ��ǣ�����ÿ���ҵ���/С��Ԫ�أ�ֱ�Ӱ������ǵ�֮ǰ��Ҫ������Ԫ�أ�����key����ÿ�ζ��ƶ�λ����
	//���ǰ�keyֵ���ֱ�ӷ��ڶ�Ӧλ���ϣ������ͽ�ʡ��ʱ�䡣
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
			//�Ż���ע��nums[j]=key;
			while(i<j&&nums[i]<=key) i++;
			nums[j]=nums[i];
			//nums[i]=key;
		}
		nums[i]=key;
		return i;
	}
	
	
	//�鲢�����ʵ�֣�
	//�鲢��˼·���ݹ���ڣ�ƽ���������Ҳ��ݹ飬���ϲ����鲢��Ҫ������ʱ����temp[] ���洢ÿ�ι鲢�Ľ��
	//*****�鲢��ϸ��֪ʶ�㣺
	//1. **�鲢һ��ʼ�ǲ�ͣ�ķָ�һ�룬�ָ�ݹ�ĳ���Ӧ����ֻʣһ��Ԫ�أ���l==r,���Եݹ���ڱ�����l>=r,����ӵ��ں�
	//2. merge�鲢�Ĺ�����ʵ����������������ϲ���һ����l��mid,��һ����mid+1��r,����ʱ�������ȡ��С�ķ��뼴��
	
	//�鲢�Ϳ��ŵ��ص����֣�
	//1. ���ŵĳ��ڿɵ��ڿɲ����ڣ��鲢�ĵݹ���ڱ�����ϵ��ں�
	//2. ������ȷ������Ԫ�ص�λ�ú󣬾Ͳ��������ˣ�ֱ�����ҵݹ���(l,p-1)(p+1,r),���ǹ鲢�����ǰ����м�ڵ�ָ�����
	//�ָ���֮����뻹�����м�Ԫ��һ������������(l��mid)(mid+1,r)
	//3. һ�����ȶ�partitionλ���ٵݹ飬һ�����ȵݹ����ҷָ���merge.��������ͺ������������
	
	
	public void mergeSort(int[] nums,int l,int r,int[] temp) {
		if(l>=r) return ; //**����ӵ��ں�
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
	//���ã�
	//quickSort(nums,0,nums.length-1);
	//mergeSort(nums,0,nums.length-1,temp);
	
	
	
	//215:Ѱ�ҵ�k���Ԫ��
	//���ÿ��ŵ�˼�뻮�ֵݹ�
	 public int findKthLargest(int[] nums, int k) {
		 return quickSort215(nums,0,nums.length-1,k);

	    }
	 
	 public int quickSort215(int[] nums,int start,int end,int k) {
		 int partition=part(nums,start,end);
		 //ֻ�еݹ���ڱ����£������Ļ���һ����˼·��
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
	 
	 //347:ǰk����ƵԪ��(����ͷ���)
	 //Ͱ����Ϊÿ��ֵ����һ��Ͱ��Ͱ��ͳ�Ƴ��ֵĴ�����Ҳ����֮ǰ���õģ������±��ֵ������ֵ�������������������Ͱ����
	 //��ɵ�һ��Ͱ����󣬿��Եõ�Ƶ�Σ���������һ��Ͱ������Ƶ��Ϊ�±꣬��ֵ��list��Ϊֵ�������Ӻ���ǰ�������õ�k�����
	 //������һ������list�������鶼��̫�ã�Ϊʲô����Ϊͳ�������Ҫ����ģ����������˳�򣬵���û�취��¼�±��ˡ�
	 //�������ǿ��Կ���һ��count���飬Ȼ�����ҵ�Ƶ�θߵ�Ƶ������Ȼ��ȥԭcount�������±�Ϳ��ԡ��ռ��õıȽ϶���
	 //���µĽ��ֻ����ԷǸ������������鿽��������ȡƵ��ȡ�±�
	 //*****�����Ҫ�����鿽�������Ǿ�����������Сֵ��Ȼ��Ϊ�����Ļ���Ϊ0���������Ӵ�С��ת��Ϊ�Ǹ�������������⼴�ɡ�
	 
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
	 
	 
	 //����������ã���Ҫ��ֵ����Ҫ���������hashmap
	 //����hashmap��ֵ��Ƶ�Σ�Ȼ����collections�ĺ��������valueֵ��Ȼ��ͨ��valueֵ�ҵ���Ӧ��key�õĵ�������Ȼ����Ǽ���𰸲�ɾ��key.
	  public int[] topKFrequent(int[] nums, int k) {
		  int[] res=new int[k];
		  HashMap<Integer,Integer> hashmap=new HashMap<>();
		  for(int i=0;i<nums.length;i++) {
			  hashmap.put(nums[i], hashmap.getOrDefault(nums[i], 0)+1);
		  }
		  int c=0;
		  while(hashmap.size()!=0) {
			  int max=Collections.max(hashmap.values());                      //�ҵ����valueֵ
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
	  
	  //451���ַ�����������
	  //����һ��һ��һ�������޸���hashmap����
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
	  
	  //75����ɫ����
	  //��򵥵ģ�ͳ��������ɫ�ĸ�����Ȼ���ͷ��β���뼴�ɣ���Ҫ����
	  //�ڶ��֣���ָ�룬��һ�˰�Ϊ0�ķ��뿪ͷ���ڶ��ˣ���Ϊ1�ķ���֮��Ŀ�ͷ����
	  //�����֣�˫ָ�룬ֻ����һ�ˣ�ͦ����˼�ģ���0������1������1�����͵ڶ���һ����ֱ�ӽ���Ȼ��++��0��������Ҫ����������ָ�붼���ƣ��������0ָ��С��1ָ��Ļ�
	  //������ȥ�Ŀ�����1�����Ի�Ҫ����ǰԪ��1��1ָ�뽻����ȷ��1����ǰ��
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

