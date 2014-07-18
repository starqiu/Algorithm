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
 * <p>ʵ�ֹ��ܣ� �÷�����
 * �÷���һ�ֺ�����˼�����־����ں��������ľŹ����������÷��йء�<br />
 * �÷��Ķ���Ϊ�� <br />
 * 1 �� N*N ����������N*N�ķ����У�ÿ�к�ÿ���Լ��Խ��ߵ�����֮�ͱ�������ȵġ�<br />
 * ����Ϊ���Թ�˾�Ķ�������Ա��������Ҫ����һ�����⣬�����������׵Ļ÷��ҳ�����<br /></p>
 * <p>
 * date	    author            email		           notes<br />
 * --------	---------------------------	---------------<br />
 *2013-10-26	 ����            starqiu@mail.ustc.edu.cn	      �½���<br /></p>
 *
 */
public class MagicSquare {
	
	/**
	 * <p>��nΪ����ʱ�Ļ÷�<br />
	 * <li>(1) ��1���ڵ�һ���м�һ��;</li>
����	   <li>(2) ��2��ʼֱ��n��nֹ�������ΰ����й����ţ�<br />
����    �� 45�㷽�����ߣ���������,ÿһ������ŵ��б�ǰһ������������1��������1</li>
����	   <li>(3) ������з�Χ��������Χ������ơ�<br />
����	  ����1�ڵ�1�У���2Ӧ��������һ�У�����ͬ����1;</li>
����	   <li>(4) ������������ȷ����λ����������������һ�����ǵ�1�е�n��ʱ��<br />
����	  �����һ����������һ���������档</li></p>
	 * @param n Ϊ������ ��(1 <= n < 1000)
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
			//���浱ǰ����
			pre_row = cur_row;
			pre_col = cur_col;
			
			//�� 45�㷽�����ߣ���������,ÿһ������ŵ��б�ǰһ������������1��������1
			cur_row--;
			cur_col++;
			
			//��������Ƿ�Խ��
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
			
			//�����ǰ������ֵ�������ǵ�һ�е�n��ʱ
			if (0 != a[cur_row][cur_col] || (0 == pre_row && n-1 == pre_col)) {
				cur_row = pre_row+1;
				cur_col = pre_col;
			}
			
			a[cur_row][cur_col]=i;
		}
		//��ӡ
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
			System.out.print("������������n��");
			int n = scanner.nextInt();
			Date t1 =new Date();
			new MagicSquare(). magicSquare4Odd(n);
			Date t2 =new Date();
			System.out.println((t2.getTime()-t1.getTime())+"ms");
		}
	}

}

