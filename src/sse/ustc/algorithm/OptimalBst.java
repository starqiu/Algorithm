/*
 * ============================================================
 * The SSE USTC Software License
 * 
 * OptimalBst.java
 * 2013-10-29
 * 
 * Copyright (c) 2006 China Payment and Remittance Service Co.,Ltd        
 * All rights reserved.
 * ============================================================
 */
package sse.ustc.algorithm;

/**
 * 实现功能： 最优二叉查找树
 * <p>
 * date	    author            email		           notes<br />
 * --------	---------------------------	---------------<br />
 *2013-10-29	 邱星            starqiu@mail.ustc.edu.cn	      新建类<br /></p>
 *
 */
public class OptimalBst {

	/**
	 * p,q为相应的概率乘以100后的值，所得结果e,w也都相应的是原来的100倍
	 * @param p 
	 * @param q
	 * @param e
	 * @param w
	 * @param root
	 * @return
	 */
	public static int optimalBst(int[] p,int[] q,int[][] e,int[][] w,int[][] root){
		int n = p.length;
		for (int i = 1; i <= n; i++) {
			e[i][i-1] = q[i-1];
			w[i][i-1] = q[i-1];
		}
		int j =0;
		int t =0;
		for(int l =1;l<=n;l++){
			for(int i=1;i<n-l+1;i++){
				j = i+l-1;
				e[i][j]=Integer.MAX_VALUE;
				w[i][j]=w[i][j-1]+p[j]+q[j];
				for (int r = i; r <=j; r++) {
					t = e[i][r-1]+e[r+1][j]+w[i][j];
					if (t<e[i][j]) {
						e[i][j]=t;
						root[i][j]=r;
					}
				}
			}
		}
		System.out.println("e[][]");
		for (int i = 0; i < n+1; i++) {
			for (int k = 0; k < n; k++) {
				System.out.printf("%5d ",e[i][k]);
			}
			System.out.println();
		}
		System.out.println("w[][]");
		for (int i = 0; i < n+1; i++) {
			for (int
					k = 0; k < n; k++) {
				System.out.printf("%3d ",w[i][k]);
			}
			System.out.println();
		}
		System.out.println("root[][]");
		for (int i = 0; i < n; i++) {
			for (int
					k = 0; k < n; k++) {
				System.out.printf("%3d ",root[i][k]);
			}
			System.out.println();
		}
		return 0;
	}
	
	public static int printOptimal(int[][] root,int i ,int j ){
		if(1==i&&(root[0].length-1) == j){
			System.out.println("树根是K"+root[i][j]);
		}
		if(i == j) {
			System.out.println("d"+(i-1)+"是K"+i+"的左孩子");
			System.out.println("d"+i+"是K"+i+"的右孩子");
		}
		if (i==j+1) {
			System.out.println("d"+j+"是K"+j+"的右孩子");
		}
		if(i<j){
			System.out.println("K"+root[i][root[i][j]-1]+"是K"+root[i][j]+"的左孩子");
			printOptimal(root, i, root[i][j]-1);
			System.out.println("K"+root[root[i][j]+1][j]+"是K"+root[i][j]+"的右孩子");
			printOptimal(root, root[i][j]+1,j);
			System.out.println();
		}
		return 0;
	}
	public static String printOptimal1(int[][] root,int i ,int j ){
		if (i == j) {
			return ("K"+i);
		}else if (j==i-1) {
			return ("d"+j);
		}else if (i==j+1) {
			return ("d"+i);
		}else {
			System.out.println("K"+root[i][j]+"左子树为"+printOptimal(root, i, root[i][j]-1));
			//printOptimal(root, i, root[i][j]-1);
			//System.out.println();
			System.out.print("K"+root[i][j]+"右子树为"+printOptimal(root, root[i][j]+1,j));
			//printOptimal(root, root[i][j]+1,j);
			//System.out.println();
		}
		return "";
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//int[] p = {30,35,15,5,10,20,25};//书上的例子
		/*int[] p = {0,15,10,5,10,20};
		int[] q = {5,10,5,5,5,10};*/
		int[] p = {0,4,6,8,2,10,12,14};
		int[] q = {6,6,6,6,5,5,5,5};
		int n =p.length;
		int e[][] = new int [n+1][n];
		int w[][] = new int [n+1][n];
		int root[][] = new int [n][n];
		optimalBst(p, q, e, w, root);
		//optimalBst(p, m, s);
		//System.out.println("树根为K"+root[1][n-1]);
		printOptimal(root, 1, n-1);
	}

}

