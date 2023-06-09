package work;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;


public class swordOffer {
	
	//记录剑指offer中的巧妙题目思路，重点注意题目
	//10道重点题，后续敲热点100题带着敲一遍
	
	//05： JAVA中字符串是不可变的，所以不能直接替换其中字符。需要用StringBuilder逐步添加
	
	//14：最大乘积问题：dp[i]=max{j*dp[i-j]}  注意点：dp数组没有分出0这种情况，所以可能是 j*(i-j)需要加入讨论
	//最后公式是dp[i]=max{j*dp[i-j]，j*(i-j)} 别忘了分出0的情况即可
	//有个规律性：一直切3是最优解，不停的切3，3*3*3*3...直到最后一个为2，3，4
	
	//17： 1<<1 10是二进制，代表的是2;Math.pow(10,1)才是十进制的10，小心点别搞混了。位运算的都是二进制运算，别和十进制数搞混了
	
	//重点题20：有效数值的字符串：状态自动机法：10种状态，5种输入类型，哈希表存操作+结果状态，分成10个哈希表，从0状态开始操作然后依次向下状态转移
	
	//27：输出镜像二叉树：简单而言就是左右子树交换，然后递归左右子树。别想得太复杂
	
	//重点题31：判断栈序列：模拟栈的弹出：当栈顶和popped中元素相同时，那就模拟栈的弹出，注意用while以免多次弹出，最后判断栈是否为空即可
	
	//32(3): 如果只用flag标记左右加入/右左加入是错误的解法，因为后续子节点加入顺序导致部分顺序有错误。正确解法：全都是先左后右加入，偶数层用一个栈来接队列输出
	//并且栈输出给list即可正确的实现完整的逆序。双端队列太复杂了，没必要，多加个栈即可逆序。
	
	//34：二叉树路径问题，因为要加入节点和路径，所以这道题要回溯。注意一下，把加入的节点，每次递归后remove掉
	
	//重点题35：复杂链表的复制：有点懵没看懂一开始：用哈希表存储源节点和新节点的映射关系，然后把源节点对应的next,random都给新节点即可
	
	//36：二叉搜索树转化双向链表：利用中序遍历，"根"中末尾pre=cur存储前驱节点，然后前面就操作链表相连即可，注意一下头节点
	
	//重点题43：找规律，分为high,cur,low,分0，1，2-9来讨论，只能说记忆就行，很难做的题目
	
	//48：思路很简单，但是要注意用一个hashset就可以了，因为遍历到重复字符发现哈希表中已经有了的时候，我们right指针不向下移动即可；只有不存在，加入才right++;
	//然后left++，remove,right继续判断刚才重复的是否在哈希set中即可
	
	//重点题49：丑数：dp[i]=min{dp[a]*2,dp[b]*3,dp[c]*5} 同时如果dp[i]由某一个dp[a],dp[b],dp[c]的来，那么对应的a,b,c就需要执行++操作
	//记住这个公式和规律
	
	//*****重点题51：逆序对的个数：归并排序添加一句代码实现逆序对：在归并中，如果左边数组元素>右边数组元素，那么左边i~mid所有元素都大于右边这个元素，所以res+=m-i+1
	//添加这一句代码即可，其余所有的都是归并排序的流程，res+=递归左+递归右
	
	//56(2):哈希表+数组两次桶排序可以的，但是官方解答很有意思：把每个数的32位二进制都进入32大小的数组中相加，然后每个都%3，为1的就是出现一次的
	
	//58(1):一个大的字符串，可以切分为多个小的字符串数组：String[] word=s.trim().split(" ");
	//**字符串的操作：截取子串，s.substring(i,j)  左闭右开  连接用的是StringBulider,或者直接用"+"
	
	//重点题60：骰子之和的概率：DP求解：f(n,x)=(i从1-6)f(n-1,x-i)*1/6 第n个骰子和为x,那么它应该由第n-1个骰子从x-i得来
	//所以从dp[n-1]递推到dp[n],而且dp数组需要扩大，n个骰子和的可能性，n-6n 即5n+1个，然后对于每个dp[i-1]的概率，乘以1/6，加入dp[i-1+k]的概率中即可。
	
	//**重点题62：圆圈删数：DP求解：dp[i]=(dp[i-1]+m)%i,具体求解：先m%n删除第一个数，然后把n个数问题转化为n-1个数的问题
	//然后找对应关系，推出最后的公式。
	
	//重点题64：不能用条件判断---想到用递归，然后想到了&&可以代替判断：boolean flag=(t>0)&&(sum+=sumOf(n-1))>0;   >0目的是：JAVA中非0值不能直接判断为true，和C++不同
	
	//重点题65：不用+-*/实现两数之和：用位运算：位运算的^恰好为两数之和的本位，& <<1 恰好是进位的规律，所以不断地求进位，本位，然后把进位给到b,直至b为0
	
	
	
	
	
	
	

}
