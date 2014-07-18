/*
 * ============================================================
 * The SSE USTC Software License
 * 
 * MatrixChainMutipate.java
 * 2013-10-29
 * 
 * Copyright (c) 2006 China Payment and Remittance Service Co.,Ltd        
 * All rights reserved.
 * ============================================================
 */
package sse.ustc.algorithm;

/**
 * 实现功能： 矩阵链乘法
 * <p>
 * date	    author            email		           notes<br />
 * --------	---------------------------	---------------<br />
 *2013-10-29	 邱星            starqiu@mail.ustc.edu.cn	      新建类<br /></p>
 *
 */
public class MatrixChainMutipate {

	public static int matrixChainOrder(int[] p,int[][] m,int[][] s){
		int n = p.length-1;
		for (int i = 0; i < n; i++) {
			m[i][i] = 0;
		}
		int j =0;
		int q =0;
		for(int l =2;l<=n;l++){
			for(int i=0;i<n-l+1;i++){
				j = i+l-1;
				m[i][j]=Integer.MAX_VALUE;
				for (int k = i; k <j; k++) {
					q = m[i][k]+m[k+1][j]+p[i]*p[k+1]*p[j+1];
					if (q<m[i][j]) {
						m[i][j]=q;
						s[i][j]=k;
					}
				}
			}
		}
		System.out.println("m[][]");
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				System.out.printf("%5d ",m[i][k]);
			}
			System.out.println();
		}
		System.out.println("s[][]");
		for (int i = 0; i < n; i++) {
			for (int
					k = 0; k < n; k++) {
				System.out.printf("%3d ",s[i][k]);
			}
			System.out.println();
		}
		return 0;
	}
	
	public static int printOptimal(int[][] s,int i ,int j ){
		if (i == j) {
			System.out.print("A["+i+"]");
		}else {
			System.out.print("(");
			printOptimal(s, i, s[i][j]);
			printOptimal(s, s[i][j]+1,j);
			System.out.print(")");
		}
		return 0;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//int[] p = {30,35,15,5,10,20,25};//书上的例子
		int[] p = {5,10,3,12,5,50,6};
		int n =p.length-1;
		int m[][] = new int [n][n];
		int s[][] = new int [n][n];
		matrixChainOrder(p, m, s);
		printOptimal(s, 0, n-1);
	}

}

