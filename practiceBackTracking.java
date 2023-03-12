package work;

public class practiceBackTracking {
	
	//回溯法总结：
	//掌握两类题目：排列问题，组合问题
	//回溯法的模板和深度优先的区别在于两点：
	//1. 主函数直接开启回溯，不用关注节点开启；
	//2. 状态恢复，标记数组和path恢复
	
	//**(不用细看这个，主要看后面的整合)排列问题：全排列，斐波那契排列
	//排列题的回溯思路：一般不用标记，因为是有序的遍历
	//1. 递归出口：从头到尾遍历原数组，重新排序或者取出并加入其他list,所以递归出口必然有一个是判断到达数组末尾
	//2. 递归方向：从当前遍历点到末尾，用for循环
	//3. 在for循环中加入我们的判断，path添加，顺序变换swap
	//4. 别忘了递归后需要状态恢复
	
	//组合问题：组合题
	//组合题的回溯思路：一般需要标记，是选取类
	//1. 递归出口：因为是选取，所以出口是选取个数达到目的即为出口
	//2. 递归方向：选取不是有序的，所以我们是0-len-1都可以递归
	//3. 根据条件，修改path,修改标记
	//4. 状态恢复
	
	//*****整合：排列问题实际也是选取问题，所以排列题转化一下就是组合题
	//只要我们加入一个used[]标记数组，那么排列题就转化为了选取题
	//出口：到达末尾/特定出口   递归方向：随意选取,所有的0-len-1  for循环中用used[]数组判断是否选取过 然后加入path,标记数组的修改与回溯
    //*****排列组合去重：用hashset，辅函数里面每一次都要新建一个hashset用于记录该层的数字有什么，用来去重
	
	
}
