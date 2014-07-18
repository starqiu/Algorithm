/*
 * ============================================================
 * The SSE USTC Software License
 * 
 * MagicSquare.java
 * 2013-10-26
 * 
 * Copyright (c) 2006 China Payment and Remittance Service Co.,Ltd        
 * All rights reserved.
 * ============================================================
 */
package ustc.sse.lab2;

import java.util.Date;
import java.util.Scanner;


/**
 * <p>实现功能： 幻方矩阵
 * 幻方是一种很有意思的数字矩阵，在很早著名的九宫八卦阵就与幻方有关。<br />
 * 幻方的定义为： <br />
 * 1 到 N*N 的整数填入N*N的方格中，每行和每列以及对角线的数字之和必须是相等的。<br />
 * 你作为八卦公司的顶级程序员，现在需要你解决一个问题，将任意奇数阶的幻方找出来。<br /></p>
 * <p>
 * date	    author            email		           notes<br />
 * --------	---------------------------	---------------<br />
 *2013-10-26	 邱星            starqiu@mail.ustc.edu.cn	      新建类<br /></p>
 *
 */
public class MagicSquare {
	
	/**
	 * <p>求n为奇数时的幻方<br />
	 * <li>(1) 将1放在第一行中间一列;</li>
　　	   <li>(2) 从2开始直到n×n止各数依次按下列规则存放：<br />
　　    按 45°方向行走，如向右上,每一个数存放的行比前一个数的行数减1，列数加1</li>
　　	   <li>(3) 如果行列范围超出矩阵范围，则回绕。<br />
　　	  例如1在第1行，则2应放在最下一行，列数同样加1;</li>
　　	   <li>(4) 如果按上面规则确定的位置上已有数，或上一个数是第1行第n列时，<br />
　　	  则把下一个数放在上一个数的下面。</li></p>
	 * @param n 为正奇数 且(1 <= n < 1000)
	 */
	public void magicSquare4Odd(int n){
		int a[][]=new int[n][n];
		for(int i=0;i<n;i++){
			for (int j = 0; j < n; j++) {
				a[i][j] = 0;
			}
		}
		int mid = n/2+1; 
		
		int max = n*n;
		int cur_row = 0;
		int cur_col = n/2;
		int pre_row = 0;
		int pre_col = 0;
		a[cur_row][cur_col]=1;
		for (int i = 2; i <= max; i++) {
			//保存当前行列
			pre_row = cur_row;
			pre_col = cur_col;
			
			//按 45°方向行走，如向右上,每一个数存放的行比前一个数的行数减1，列数加1
			cur_row--;
			cur_col++;
			
			//检查行列是否越界
			if (0 > cur_row) {
				cur_row = n - 1;
			}else if (n-1 <cur_row) {
				cur_row = 0;
			}
			if (0 > cur_col) {
				cur_col = n - 1;
			}else if (n-1 <cur_col) {
				cur_col = 0;
			}
			
			//如果当前行已有值，或者是第一行第n列时
			if (0 != a[cur_row][cur_col] || (0 == pre_row && n-1 == pre_col)) {
				cur_row = pre_row+1;
				cur_col = pre_col;
			}
			
			a[cur_row][cur_col]=i;
		}
		//打印
		for(int i=0;i<n;i++){
			for (int j = 0; j < n; j++) {
				System.out.printf("%5d ",a[i][j]);
			}
			System.out.println("");
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print("请输入正奇数n：");
			int n = scanner.nextInt();
			Date t1 =new Date();
			new MagicSquare(). magicSquare4Odd(n);
			Date t2 =new Date();
			System.out.println((t2.getTime()-t1.getTime())+"ms");
		}
	}

}

