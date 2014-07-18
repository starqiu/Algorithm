/*
 * ============================================================
 * The SSE USTC Software License
 * 
 * Gcd.java
 * 2013-9-25
 * 
 * Copyright (c) 2006 China Payment and Remittance Service Co.,Ltd        
 * All rights reserved.
 * ============================================================
 */
package com.ustc.crypt;


/**
 * 实现功能： 
 * 
 * History
 * date	    author            email		           notes
 * --------	---------------------------	---------------
 *2013-9-25	Administrator xing.qiu_C@chinapnr.com			新建类
 *
 */
public class Gcd {
	
	
	/**
	 *欧拉算法，求a和b的最大公约数
	 */
	public static int eculid(int a,int b){
		int q;//商
		q=a/b;
		a=a-q*b;//余
		if (0==a) {
			return b;
		}
		return eculid(b,a);
		
	}
	
	/**
	 *扩展的欧拉算法子算法
	 */
	public static int sub_ex_eculid(int a, int b,int a1,int a2,int b1,int b2,int q){
		System.out.println(q+"    "+a1+"    "+a2+"    "+a+"    "+b1+"    "+b2+"    "+b);
		if (0==b) {//返回最大公约数
			return a;
		}else if (1==b) {//互质，返回b mod a乘法逆元
			return  b2;
		}else {
			q=a/b;
			return sub_ex_eculid(b, a-q*b, b1, b2, a1-q*b1, a2-q*b2,q);
		}
	}
	
	/**
	 *扩展的欧拉算法
	 */
	public static int ex_eculid(int a,int b) {
		if (b>a) {
			return ex_eculid(b, a);
		}
		
		//求逆元时，可能得到负数，需要mod a得到正数
		int result = sub_ex_eculid(a, b, 1, 0, 0, 1, 0);
		if (0>result) {
			result = result - (result/a-1)*a;
		}
		
		return result;
	}
	public static void main(String[] args) {
		System.out.println(ex_eculid(7,480));
		System.out.println((7*343));
	}

}

