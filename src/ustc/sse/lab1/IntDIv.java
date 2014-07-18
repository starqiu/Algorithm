/*
 * ============================================================
 * The SSE USTC Software License
 * 
 * IntDIv.java
 * 2013-10-9
 * 
 * Copyright (c) 2006 China Payment and Remittance Service Co.,Ltd        
 * All rights reserved.
 * ============================================================
 */
package ustc.sse.lab1;

import java.util.Scanner;


/**
 * 实现功能： 整数划分类<br />
 * <p>问题描述：<br />
 * 	输入： 每组输入是两个整数n和k。(1 <= n <= 50, 1 <= k <= n) <br />
 * 	输出：<br />
 *	 对于每组输入，请输出六行。 <br />
 * 	第一行： 将n划分成若干正整数之和的划分数。<br /> 
 * 	第二行： 将n划分成k个正整数之和的划分数。 <br />
 * 	第三行： 将n划分成最大数不超过k的划分数。 <br />
 * 	第四行： 将n划分成若干奇正整数之和的划分数。 <br />
 * 	第五行： 将n划分成若干不同整数之和的划分数。 <br />
 * 	第六行： 打印一个空行。<br /></p>
 * 
 * <p>
 * date	    author            email		               notes<br />
 * --------	---------------------------	----------------------<br />
 *2013-10-9	  邱星                  starqiu@mail.ustc.edu.cn		      新建类<br /></p>
 *
 */
public class IntDIv {
	
	/**
	 * 当n>k时<br />
	 * f(n, k)将n划分成最大数不超过k的划分数<br />
	 * f(n-k, k)表示加数包含k的划分数(n=(n-k) + k)<br />
	 * f(n, k-1)表示加数最大为k-1（即不包含k）的划分数<br />
	 * 所以f(n, k)=f(n-k, k)+ f(n, k-1)<br />
	 * 当n=k时<br />
	 * f(n,n)即表示将n划分成若干正整数之和的划分数,易知f(n,n)=1+f(n,n-1)<br />
	 * 我们规定f(0,k)=1,则可将该分支合并到n>k的分支<br />
	 * 当n&ltk时<br />
	 * 则能划分的最大数只能是n 此时划分数为f(n,n)<br />
	 * 现考虑递归终止情况：如果k=1，则只能划分成n个1 ,即f(n,1)=1<br />
	 * 
	 * @param n 1<=n<=50
	 * @param k 1<=k<=n
	 * @return  将n划分成最大数不超过k的划分数
	 */
	public static int int_div_N(int n,int k){//f(n, k)
		if (0>n || 0>=k) {
			return 0;//不能划分
		}
		
		if (0 == n) {
			return 1;//规定，适用于f(n,n)=1+f(n,n-1)
		}
		
		if (1 == k) {
			return 1;//f(n, 1)=1
		}
		if (n<k) {
			return int_div_N(n, n);
		}else {
			//f(n-k, k)表示加数包含k的划分数(n=(n-k) + k)
			//f(n, k-1)表示加数最大为k-1（即不包含k）的划分数
			return int_div_N(n-k, k) + int_div_N(n, k-1);
		}
	}
	
	/**
	 * 当n>k时：<br />
	 * g(n, k)表示 将n划分成加数个数不超过k的划分数<br />
	 * h(n, k)表示将n划分成k个正整数之和的划分数<br />
	 * 假设有k个盒子，,把n分成n个1，先在每个盒子放一个1，这样还剩下n-k个1<br />
	 * 剩下的n-k 分成不超过k组的划分数为g(n-k, k)，亦即h(n, k)=g(n-k, k)<br />
	 * g(n, k)=h(n, 1)+h(n, 2)+...+h(n, k)亦即：<br />
	 * g(n, k)=g(n-k, 1)+g(n-k, 2)+...+g(n-k, k)此即为递归式<br />
	 * 当n=k时：<br/>
	 * g(n, n)即表示将n划分成最大数不超过n的划分数 亦即 f(n, n)
	 * 当n&ltk时：<br />
	 * n最多只能划分为n组,此时g(n, k)=f(n, n)<br />
	 * 现考虑递归终止情况：如果k=1，则只能划分成n ,即f(n,1)=1<br />
	 * 
	 * @param n 1<=n<=50
	 * @param k 1<=k<=n 加数最大个数
	 * @return 将n划分成加数个数不超过k的划分数
	 */
	public static int int_div_maxK(int n,int k){//g(n, k)
		
		if (0>=k ) {
			return 0;//n不能分成大于n个正整数
		}
		if (0==n) {
			return 1;// n=n 
		}
		if (n<=k) {
			return int_div_N(n,n);//n<=k时，n最多只能划分为n组
		}else {
			if (0==n) {
				return 1;// n=n 
			}else{//k>1
				int result = 0;
				for (int i = 1; i <= k; i++) {
					result += int_div_maxK(n-k, i);
				}
				return result;
			}
		}
	}
	
	/**
	 * @param n 1<=n<=50
	 * @param k 1<=k<=n 加数最大个数
	 * @return 将n划分成k个正整数之和的划分数
	 */
	public static int int_div_K(int n,int k){//h(n, k)
		//由int_div_maxK中的分析知，h(n, k) = g(n-k, k)
		return int_div_maxK(n-k,k);
	}
	
	/**
	 * p(n, k)表示将n划分成最大数不超过k且加数都为奇正整数的划分数<br />
	 * 当k为偶数时：<br />
	 * 最大的奇正整数只能为k-1 ，所以p(n, k)=p(n, k-1)<br />
	 * 当k为奇数时：<br />
	 * 如果n>k<br />
	 * &nbsp;&nbsp;p(n-k, k)表示加数包含k的划分数(n=(n-k) + k)<br />
	 * &nbsp;&nbsp;p(n, k-2)表示加数最大为k-2（即不包含k）的划分数<br />
	 * &nbsp;&nbsp;则p(n,k)=p(n-k, k)+p(n, k-2)<br />
	 * 如果n=k<br />
	 * &nbsp;当n为奇数<br />
	 * &nbsp;&nbsp;则最大奇数为n，根据n>k时的划分<br />
	 * &nbsp;&nbsp;p(n,n)=p(0, n)+p(n, n-2)=1+p(n, n-2) 其中1为只含有n的情况 <br />
	 * &nbsp;当n为偶数时<br />
	 * &nbsp;&nbsp;则最大奇数为n-1 ，此时可先划分为1和n-1 ，n-1为奇数，情况同上<br />
	 * &nbsp;&nbsp;则p(n,n)=1+p(n-1,n-1) 其中1为只含有1和n-1的情况 <br />
	 * 当n&ltk时<br />
	 * &nbsp;&nbsp;最大奇数最多为n<br />
	 * &nbsp;&nbsp;此时p(n,k)=p(n,n)
	 * 考虑递归终止情况：<br />
	 * &nbsp;&nbsp;当k=1时，p(n, 1)=1(n划成n个1)<br />
	 * &nbsp;&nbsp;当0>n || 0>=k 时，不能划分，返回0<br />
	 * 
	 * 
	 * @param n 1<=n<=50
	 * @param k 1<=k<=n
	 * @return  将n划分成最大数不超过k且加数都为奇正整数的划分数
	 */
	public static int int_div_N_odds(int n,int k){//p(n, k)
		if (0>n || 0>=k) {
			return 0;//不能划分
		}
		
		if (!isOdd(k)) {//k为偶数时，最大加数只能为k-1（奇数）
			return int_div_N_odds(n, k-1);
		}else {//k为奇数
			if (0 == n) {
				return 1;//规定，适用于p(n,n)=1+p(n,n-2)
			}
			if (1 == k) {
				return 1;//p(n, 1)=1(n划成n个1)
			}
			if (n<k) {
				return int_div_N_odds(n, n);
			}else if (n == k) {
				if (isOdd(n)) {
					return  1+int_div_N_odds(n, n-2);//1为只含有n的情况 
				}else {
					return  1+int_div_N_odds(n-1, n-1);//1为只含有1和n-1 的情况
				}
			}else {
				//p(n-k, k)表示加数包含k的划分数(n=(n-k) + k)
				//p(n, k-2)表示加数最大为k-2（即不包含k）的划分数
				return int_div_N_odds(n-k, k) + int_div_N_odds(n, k-2);
			}
		}
		
	}
	
	/**
	 * @param n
	 * @return 如果n是奇数则返回true 否则返回false 
	 */
	public final  static boolean isOdd(int n) {//建议内联
		return (0== n%2)?false:true;
	}
	
	/**
	 * 背包问题<br />
	 * q(i,sum)表示到第i个背包为止，背包的重量为sum<br />
	 * 分为两种情况：<br />
	 * 取了第i个背包里的东西，则到第i-1个背包为止，背包的重量为sum-i 即q(i-1,sum-i)<br />
	 * 没取第i个背包里的东西，则到第i-1个背包为止，背包的重量为sum即q(i-1,sum)<br />
	 * 得递归式：q(i,sum)=q(i-1,sum)+q(i-1,sum-i)<br />
	 * @param i 第i个背包，背包重量与序号相同
	 * @param sum 到第i个背包为止，背包的重量
	 * @return 将sum划分成若干不同整数（1和i之间）之和的划分数
	 */
	public static int int_div_diff_N(int i,int sum){//q(i,sum)
		if (0 <= i ){
			if(0 == sum) {
				return 1;
			}else if (0 > sum) {//超载 ，不满足条件
				return 0;
			}else {//sum>0 未装满
				return int_div_diff_N(i-1, sum)+int_div_diff_N(i-1, sum-i);
			}
		}else {//未装满
			return 0;
		}
			
	}
	
	public static void main(String[] args) {
		//int n = 5,k = 2;
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print("请输入整数n：");
			int n = scanner.nextInt();
			System.out.print("请输入整数k：");
			int k = scanner.nextInt();
			System.out.println(int_div_N(n, n));//第一行
			System.out.println(int_div_K(n, k));//第二行
			System.out.println(int_div_N(n, k));//第三行
			System.out.println(int_div_N_odds(n,n));//第四行
			System.out.println(int_div_diff_N(n,n));//第五行
			System.out.println();//第六行
		}
	}

}

