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
 * ʵ�ֹ��ܣ� 
 * 
 * History
 * date	    author            email		           notes
 * --------	---------------------------	---------------
 *2013-9-25	Administrator xing.qiu_C@chinapnr.com			�½���
 *
 */
public class Gcd {
	
	
	/**
	 *ŷ���㷨����a��b�����Լ��
	 */
	public static int eculid(int a,int b){
		int q;//��
		q=a/b;
		a=a-q*b;//��
		if (0==a) {
			return b;
		}
		return eculid(b,a);
		
	}
	
	/**
	 *��չ��ŷ���㷨���㷨
	 */
	public static int sub_ex_eculid(int a, int b,int a1,int a2,int b1,int b2,int q){
		System.out.println(q+"    "+a1+"    "+a2+"    "+a+"    "+b1+"    "+b2+"    "+b);
		if (0==b) {//�������Լ��
			return a;
		}else if (1==b) {//���ʣ�����b mod a�˷���Ԫ
			return  b2;
		}else {
			q=a/b;
			return sub_ex_eculid(b, a-q*b, b1, b2, a1-q*b1, a2-q*b2,q);
		}
	}
	
	/**
	 *��չ��ŷ���㷨
	 */
	public static int ex_eculid(int a,int b) {
		if (b>a) {
			return ex_eculid(b, a);
		}
		
		//����Ԫʱ�����ܵõ���������Ҫmod a�õ�����
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

