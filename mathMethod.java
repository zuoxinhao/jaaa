package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;


public class mathMethod {
	
	//��ѧ�����ܽ᣺
	//1. շת�������(a,b)=(b,a%b)һֱ����ֱ������Ϊ0��a��b�������������b��a%b���������һ��
	//2. ����ת�����⣬�����˸���������Ҫ�ȴ���š�while(num!=0) ���̺����� Ȼ���̸�num
	//3. *****�������ѧ�⣺ͳ��n�׳�β��0�ĸ�����0--2*5�����Ծ���ͳ������2��5�ĸ�����2�ĸ�����ȻԶ����5�����Ծ���ͳ��
	//����5�ĸ�����n/5 ͳ������5����һ�εĸ�����n/25����25����һ�Σ�5�������εĸ���������Ϊͳ��һ��5ʱ�������25֮���
	//���֣����Եڶ��μ���n/25ʱ����Ҫ��2�ˡ������ǵݹ飺return (n==0)?0:n/5+dfs(n/5); n/5+n/25+n/125...
	//4. (����Ҫ)�ַ���/k��������ӣ��ӷ�ԭ�򣬴�ĩβ��ʼ��ӣ���¼����ֵ�ͽ�λֵ��**ע��һ�£��ַ���9����Ҫ��ȡֵ9����Ҫ����9��-��0��
	//5. *****������ң�ִ��n�Σ���i��λ�ã�ѡ��i+random.nextInt(n-i)��֮����,��[i,n)����ν�ĸ�ԭ�������ڴ���ǰcopyһ���������
	//6. nextInt(n)��������[0,n]�����ѡȡһ����
	//7. ǰ׺�͵����ã����˿ռ临�Ӷȣ���total+1�򻯵���w.length
	//8. (�˽�һ��)ˮ��������������1/n�������
	//���ڱ�������i��λ�ã���[0,i)������������������0���Ǿ������λ�õ�ֵ(����ans)���������0���Ǿ���һ��
	//9. ����˼���ʹ�ã����Ǻͽ�����0-25��ͬ��1-26���˸�26���˸�0��������Ҫ���⴦��26��k������(����Ϊ0�����)
	//���˸�0����ν����Ҫ�Ƕ��˸�26,��Ҫ���ǵ�����������Ϊ0�����������Ϊ0ת��Ϊz���ҳ�����Ҫ--
	//10. Ѱ��������ͶƱ�㷨����ͣ��+1��-1��Ϊ0�ͻ�candidate
	//11. (����Ҫ��)*****�ܾ����ֲ�����������randK()����
	//ͨ�ýⷨ���ܾ����ֲ���ϸ���
	//��������Ĺ��죨����С�Ĺ����ģ�����С�ķ����Σ���Σ��ܾ�һ���֣�ȡһ���֣��˻���õ��ڳ������ʣ�Ȼ��ӳ���һ���͵ڶ��������������Ҫ�����伴�ɼ���
	//��ν�ľܾ�һ���֣����൱�����������������rand7---�����rand6,rand5,rand4...
	//��������޷��ó˻��Ļ�������rand11,�Ǿ�������rand7���Ȼ��ѡȡ11���ȸ��ʵļ���(1-7,2-14,3-21...7-49,�����������)
	//�ص��⣺rand7--rand10
	//�ص���룺while(first==rand7()>2);   while(second==rand7()>5);
	/*public int rand7() {
		  return 0;
	  }
	  public int rand10() {
		  int first=0;
		  int second=0;
		  while((first=rand7())>2);                      //�ܾ�����һ���֣��൱�ڱ����rand2
		  while((second=rand7())>5);                     //rand5    
		  if(first==1) {
			  return second;
		  }else {
			  return 5+second;
		  } 
	    }*/
	//12. �������������������󣬾���Ҫ��hashsetȥ�ж��Ƿ��ѭ�����ص�����ڿ��ܻ�ѭ��
	
	
	
	
	//*****��ѧ���⣺
	//1. ��������ı���
	//2. ��������ı��ͣ��ܾ�������
	//3. ���õģ�ǰ׺�ͣ�ǰ׺�˻�
	//4. ������
	
	
	//204:��������
	//1. ��򵥵ģ�����2-n-1�����ÿ�������ж��Ƿ��г���1�ͱ���֮�����������
	//2. ��˹����ɸѡ�����ڴ�2��ʼ��ÿ����������k��(<n)�϶����Ǻ����������Ϳ�����һ��boolean��������¼����
	//���ߵĸ��Ӷ����󣬵��ǵ�һ�ֽⷨ���ڶ������������j*j<i,������n^(3/2)
	public int countPrimes(int n) {
		if(n<=2) return 0;
		int count=n-2;
		for(int i=2;i<n;i++) {
			for(int j=2;j*j<=i;j++) {
				if(i%j==0) {
					count--;
					break;
				}
			}
		}
		return count;
    }
	
	//504:7������ת��
	//����ת���������ͷ��룬�̼�������ֱ��Ϊ0
	//���������ȱ���������������ϸ��ż���
	//!!!�����������Ϸ��ã�������
	public String convertToBase7(int num) {
		if(num==0) return "0";
		StringBuilder res=new StringBuilder();
		boolean flag=false;
		if(num<0) {
			flag=true;
			num=-num;
		}
		while(num!=0) {
			int a=num/7;
			int b=num%7;
			res.append(b);
			num=a;
		}
		if(flag==true) {
			res.append('-');
		}
		return res.reverse().toString();
    }
	
	//172���׳�β��0�ĸ���
	//�������ѧ���⣺
	//�׳�β����0��������2*5���ã�����Ϊ2���������϶�ԶС��5��������������ת��Ϊ��1-n��ͳ������5���ܸ���
	//���磺n=130   ����5ͳ������5һ�Σ�25ͳ������5���Σ� 125ͳ������5���Σ� 130ͳ������5һ��
	//���ۣ�n/5=n1 �õ�һ������5�����ĸ���   n1/5=n2 �õ���������5�ĸ�������Ϊ�ڵ�һ��n1���Ѿ�ͳ�ƹ�һ���ˣ�����������ͳ��һ�μ��ɣ�ֱ��+�ϣ� 
	 public int trailingZeroes(int n) {
		 return n==0?0:n/5+trailingZeroes(n/5);
	    }
	
	//415:�ַ������
	//һ�֣���ÿ���ַ�����ת��Ϊ��������ӣ�Ȼ������%�õ�һ����������StringBuilder���Ӽ���
	//�ڶ��֣��ӷ�ԭ��ĩβ������ӣ�ÿ��ȡ�����һλ������ӣ��õ�������ֵ�Լ���λ�ͣ�add�������/10������ô��һ��λ����Ӿ���Ҫ���Ͻ�λ�ͼ���
	//�ⷨ����
	 public String addStrings(String num1, String num2) {
		 int len1=num1.length()-1;
		 int len2=num2.length()-1;
		 int add=0;
		 StringBuilder ans=new StringBuilder();
		 while(len1>=0||len2>=0||add>0) {                         //������������ѭ����ֹ����һ��add>0,��Ϊ���ֻ�н�λʱ��Ҳ��Ҫ��ǰ���ã���||������һ��Ϊ�յ�������Ƿ�Ϊ�յĴ�����ѭ��������
			 int x=len1>=0?num1.charAt(len1)-'0':0;               //!!!С�ĵ����ַ���Ҫ��ʾ�������Ļ�����Ҫ��ȥ�ַ�0����
			 int y=len2>=0?num2.charAt(len2)-'0':0;
			 int res=x+y+add;
			 add=res/10;
			 ans.append(res%10);
			 len1--;
			 len2--;
		 }
		 return ans.reverse().toString();
		 }
	 
	 //326���ж�3���ݴ�
	 //��ͣ�ĳ�3���������Ƿ���0��ֱ����Ϊ1��1%3=1 ������������Ϊ0ʱ����Ӧ�ĳ���Ӧ����1
	 public boolean isPowerOfThree(int n) {
		 while(n!=0&&n%3==0) {
			 n/=3;
		 }
		 return n==1;
	    }
	 
	 //384�������������
	 //������ң�ϴ���㷨��ͦ����˼��
	 //ϴ���㷨����1-n�����ѡȡһ������Ȼ����ʼ��λ�ý��н�������ô2-nλ���о���δ���ҵģ��������ҽ����ڶ���λ�á�����ѭ��n�μ��ɴ��ҵ����һ��λ��
	 //��i��λ�ã�ѡ��i+random.nextInt(n-i)��֮����,��[i,n)
	 //��ν�ĸ�ԭ�������ڴ���ǰcopyһ���������
	 int[] nums;
	 int[] origin;
	 public void solution(int[] nums) {
		 this.nums=nums;
		 this.origin=new int[nums.length];
		 System.arraycopy(nums, 0, origin, 0, nums.length);
	 }
	 
	 public int[] reset() {
		 System.arraycopy(origin, 0, nums, 0, nums.length);
		 return nums;
	 }
	 
	 public int[] shuffle() {
		 Random random=new Random();                            //�����ȡ����Random��
		 for(int i=0;i<nums.length;i++) {
			 int j=i+random.nextInt(nums.length-i);             //random.nextInt(n):��0-n�������ȡһ����
			 int temp=nums[i];
			 nums[i]=nums[j];
			 nums[j]=temp;
		 }
		 return nums;
	 }
	 
	 //528��Ȩ�����
	 //����һ��Ȩ�����飬����Ȩ�ؽ������
	 //�ҵ�˼·��[3,1]  �������½���һ�����飬������total�ܺͣ�3--��Ӧ��3������(Ӧ�����±������)��1--��Ӧ��Ԫ�ط�һ�Σ����������Ϳ��Եõ���Ȩ�ؽ����������飬Ȼ�������������
	 int[] temp528;
	 int total;
	 public void Solution528(int[] w) {
		 for(int i=0;i<w.length;i++) {
			 total+=w[i];
		 }
		 temp528=new int[total];
		 int j=0;
		 for(int i=0;i<w.length;i++) {
			 int count=w[i];
			 for(int k=0;k<count;k++) {
				 temp528[j++]=i;
			 }	 
		 }
	    }
	    
	 public int pickIndex528() {
		 Random random=new Random();
		 int i=random.nextInt(total);
		 return temp528[i];
	    }
	 //�����ռ����ƣ�ʵ��Ҫ����������ռ�Ϊw.length��С�����顣�ҵ���total+1�Ŀռ䣬������Ҫ��
	 
	 //��׼���ǰ׺�ͷ���˼��������ҵ���һ�������������һ�κܳ������飬ÿ�β��ִ���������������ĸ��±�
	 //��������Ѱ��ÿһ�εĹ�ʽ������ǰ׺��pre[i]��ʾ������w[i]��������Ӧ�ĺ�
	 //�ҹ��ɿ�֪���±�i��Ӧ�Ķεķ�Χ�ǣ�pre[i]-w[i]+1 <= i <=pre[i]
	 //���˿ռ临�Ӷ�
	 //!!!!!ע�⣬������֣��Ǵ�1��ʼ�ģ�1-n���仮��Ϊ���ٶΣ������Ҳ������1-n��ѡ��
	 int[] pre;
	 int[] w1;
	 public void Solution5282(int[] w) {
		 this.w1=w;
		 pre=new int[w.length];
		 for(int i=0;i<w.length;i++) {
			 total+=w[i];
		 }
		 pre[0]=w[0];
		 for(int i=1;i<w.length;i++) {
			 pre[i]=pre[i-1]+w[i];
		 }
	 }
	 
	 public int pickIndex5282() {
		 int i=(int)(Math.random()*total)+1;                        //!!!!!Math.random��Χ��[0,1),����total��[0,total)�Ӹ�1����[1,total+1)
		 for(int j=0;j<pre.length;j++) {
			 if(i>=pre[j]-w1[j]+1&&i<=pre[j]) {
				 return j;
			 }
		 }
		 return 0;
	 }
	 
	 //382:�������������
	 //��һ�֣�ֱ�Ӹ�������������������ռ临�Ӷ�O��n��
	 //�ڶ��֣��ռ临�Ӷ�O��1����ˮ�������������ڸ��ʼ���ķ���
	 
	 //*****ˮ����������������iλ��ʱ����֤���������i�ķ����ǣ���i����ѡ����i+1��i+2...������ѡ��
	 //*****�����ڲ�֪�����弯�ϴ�С��������Ȼ������1/n�ĸ������������ĳһ��
	 //*****֤��������Ĺ�ʽ����
	 
	 //���ڱ�������i��λ�ã���[0,i)������������������0���Ǿ������λ�õ�ֵ���������0���Ǿ����ans(��ʼΪ0)
	 //��ô���i�ڵ�ֵ�ĸ���P=P(��i�ڵ������0)*P(��i+1�ڵ��������0)*P(��i+2�ڵ��������0)*P(��i+3�ڵ��������0)...*P(��n�ڵ��������0)
	 //P=1/i*(1-1/(i+1))*(1-1/(i+2))...*(1-1/n)=1/n������������ĸ��ʡ�
	  public class ListNode {
		      int val;
		      ListNode next;
		      ListNode() {}
		      ListNode(int val) { this.val = val; }
		      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
		  }
	  ListNode head;
	  Random random;
	  public void Solution382(ListNode head) {
		  this.head=head;
		  random=new Random();
	  }
	
	  public int getRandom() {
		  int i=1;
		  int ans=0;
		  for(ListNode h=head;h!=null;h=h.next) {
			  if(random.nextInt(i)==0) {
				  ans=h.val;
			  }
			  i++;
		  }
		  return ans;
	  }
	  
	  //168��excel�б�����
	  //����˼���ʹ�ã����Ǻͽ�����0-n-1��ͬ��������˸�n���˸�0��������Ҫ���⴦��n��k������
	  //���˸�0����ν����Ҫ�Ƕ��˸�n,��Ҫ���ǵ�����������Ϊ0���������Ϊ0ת��Ϊz���ҳ�����Ҫ--
	  public String convertToTitle(int num) {
			if(num==0) return "0";
			StringBuilder res=new StringBuilder();
			boolean flag=false;
			if(num<0) {
				flag=true;
				num=-num;
			}
			while(num!=0) {
				int a=num/26;
				int b=num%26;
				if(b==0) {                             //!!!Ϊ0������������'Z'��Ҫ��λ-1
					res.append('Z');           
					a--;
				}else {
				res.append((char)('A'+b-1));           //����Ҫǿת���ܱ��char
				}
				num=a;
			}
			if(flag==true) {
				res.append('-');
			}
			return res.reverse().toString();
	    }
	
	  //67:�������ַ����
	  //��ͼ���λ����10����-2���ƣ�ֻ�г���������ĳ������˶���
	  public String addBinary(String num1, String num2) {
			 int len1=num1.length()-1;
			 int len2=num2.length()-1;
			 int add=0;
			 StringBuilder ans=new StringBuilder();
			 while(len1>=0||len2>=0||add>0) {                         //������������ѭ����ֹ����һ��add>0,��Ϊ���ֻ�н�λʱ��Ҳ��Ҫ��ǰ���ã���||������һ��Ϊ�յ�������Ƿ�Ϊ�յĴ�����ѭ��������
				 int x=len1>=0?num1.charAt(len1)-'0':0;               //!!!С�ĵ����ַ���Ҫ��ʾ�������Ļ�����Ҫ��ȥ�ַ�0����
				 int y=len2>=0?num2.charAt(len2)-'0':0;
				 int res=x+y+add;
				 add=res/2;
				 ans.append(res%2);
				 len1--;
				 len2--;
			 }
			 return ans.reverse().toString();
			 }
	  
	  //238��������������г˻�
	  //��ʾ����ǰ׺�˻��ͺ�׺�˻������Ժܼ򵥣���0-i-1ǰ׺�˻�����i+1-n��׺�˻������߳˻�����
	  public int[] productExceptSelf(int[] nums) {
		  int[] preMulti=new int[nums.length];
		  int[] nextMulti=new int[nums.length];
		  int[] answer=new int[nums.length];
		  preMulti[0]=nums[0];
		  for(int i=1;i<nums.length;i++) {
			  preMulti[i]=preMulti[i-1]*nums[i];
		  }
		  nextMulti[nums.length-1]=nums[nums.length-1];
		  for(int i=nums.length-2;i>=0;i--) {
			  nextMulti[i]=nextMulti[i+1]*nums[i];
		  }
		  answer[0]=nextMulti[1];
		  answer[nums.length-1]=preMulti[nums.length-2];
		  for(int i=1;i<nums.length-1;i++) {
			  answer[i]=preMulti[i-1]*nextMulti[i+1];
		  }
          return answer;
	    }
	  
	  //462��������Ԫ�صĲ���
	  //ת��Ϊ����λ�����⣬ԭ������target����Сֵ-���ֵ֮�䣬����ѡȡ���������ô���ֵ��Сֵ�ƶ���target�Ĳ�������max-target+target-min=max-min�ǹ̶�ֵ
	  //����ֱ��ɾ��max,min������ѡȡtarget��ͬ�����ɾ��...ֱ��ֻ������Ԫ��/һ��Ԫ�أ�ż������λ��������ѡһ���ɣ�������������λ��
	  public int minMoves2(int[] nums) {
		  Arrays.sort(nums);
		  int m=nums.length;
		  int res=0;
		  for(int i=0;i<nums.length;i++) {
			  res+=Math.abs(nums[i]-nums[m/2]);
		  }
		  return res;
	    }
	  
	  //169:Ѱ������
	  //��һ�֣��ù�ϣ���Ԫ�غͳ��ֵĴ�����O��n��Ҳͦ�õģ�����ռ����
	  //�ڶ��֣���ͶƱ�㷨��һ��candidate���ѡԪ�أ�һ��count�����������+1������-1��Ϊ0�Ǿͻ���candidate
	  public int majorityElement(int[] nums) {
		  Integer candidate=null;
		  int count=0;
		  for(int i=0;i<nums.length;i++) {
			  if(count==0) {
				  candidate=nums[i];
			  }
			  if(nums[i]==candidate) {
				  count++;
			  }else {
				  count--;
			  }
		  }
		  return candidate;
	    }
	  
	  //470����������Ĺ��죨�ܾ����ֲ�������
	  
	  //*****ͨ�ýⷨ��
	  //*****��������Ĺ��죨����С�Ĺ����ģ�����С�ķ����Σ���Σ��ܾ�һ���֣�ȡһ���֣��˻���õ��ڳ������ʣ�Ȼ��ӳ���һ���͵ڶ��������������Ҫ�����伴�ɼ���
	  //*****��ν�ľܾ�һ���֣����൱�����������������rand7---�����rand6,rand5,rand4...
	  //*****��������޷��ó˻��Ļ�������rand11,�Ǿ�������rand���Ȼ��ѡȡ11���ȸ��ʵļ���
	  
	  //��rand7�䵽rand10:ʵ�������������ԭ����-----�ȸ��ʣ���ÿ��Ԫ�س��и���Ϊ1/10����
	  //��rand7�оܾ���3-7��ֻʣ1-2��������1/2��Ȼ������һ��rand7���ܾ���6-7��ֻʣ1-5��������1/5��Ȼ������rand������ˣ�ÿ��Ԫ�صĸ��ʾ���1/10
	  //����������ӳ�䣺1-2��1-5Ҫӳ�䵽1-10���Ǿ���������һ����1��Ȼ��1-5ֱ��ӳ�䣻�����һ����2��ӳ�䵽5+�ڶ���������6=10.ӳ�����
	  public int rand7() {
		  return 0;
	  }
	  public int rand10() {
		  int first=0;
		  int second=0;
		  while((first=rand7())>2);                      //�ܾ�����һ���֣��൱�ڱ����rand2
		  while((second=rand7())>5);                     //rand5    
		  if(first==1) {
			  return second;
		  }else {
			  return 5+second;
		  } 
	    }
	  
	  //202��������
	  //�ѵ�����ڣ��ж�ѭ��������ù�ϣ����������û��Ҫ��floyd��Ȧ��,ֱ����hashset���ж��Ƿ��Ѿ����ڼ���
	  //�᲻���������󣿲��ᣬ999-243��9999-324��9999...---��Ӧ��ֻ��ȵ�ǰֵС��Ҳ����˵���־�����λ��ƽ�������ᵽ����󣬵���һ���̶�ֻ����ٵ�1��������ѭ��
	  public boolean isHappy(int n) {
		  HashSet<Integer> hash=new HashSet<>();
		  while(n!=1&&!hash.contains(n)) {
			  hash.add(n);
			  n=squareNum(n);
		  }
		  return n==1;
	    }
	  
	  public int squareNum(int n) {
		  int res=0;
		  while(n!=0) {
			  int i=n%10;
			  res+=i*i;
			  n/=10;
		  }
		  return res;
	  }
	
	  
	
	
	
	
	
	

}

