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
 * ʵ�ֹ��ܣ� ����������<br />
 * <p>����������<br />
 * 	���룺 ÿ����������������n��k��(1 <= n <= 50, 1 <= k <= n) <br />
 * 	�����<br />
 *	 ����ÿ�����룬��������С� <br />
 * 	��һ�У� ��n���ֳ�����������֮�͵Ļ�������<br /> 
 * 	�ڶ��У� ��n���ֳ�k��������֮�͵Ļ������� <br />
 * 	�����У� ��n���ֳ������������k�Ļ������� <br />
 * 	�����У� ��n���ֳ�������������֮�͵Ļ������� <br />
 * 	�����У� ��n���ֳ����ɲ�ͬ����֮�͵Ļ������� <br />
 * 	�����У� ��ӡһ�����С�<br /></p>
 * 
 * <p>
 * date	    author            email		               notes<br />
 * --------	---------------------------	----------------------<br />
 *2013-10-9	  ����                  starqiu@mail.ustc.edu.cn		      �½���<br /></p>
 *
 */
public class IntDIv {
	
	/**
	 * ��n>kʱ<br />
	 * f(n, k)��n���ֳ������������k�Ļ�����<br />
	 * f(n-k, k)��ʾ��������k�Ļ�����(n=(n-k) + k)<br />
	 * f(n, k-1)��ʾ�������Ϊk-1����������k���Ļ�����<br />
	 * ����f(n, k)=f(n-k, k)+ f(n, k-1)<br />
	 * ��n=kʱ<br />
	 * f(n,n)����ʾ��n���ֳ�����������֮�͵Ļ�����,��֪f(n,n)=1+f(n,n-1)<br />
	 * ���ǹ涨f(0,k)=1,��ɽ��÷�֧�ϲ���n>k�ķ�֧<br />
	 * ��n&ltkʱ<br />
	 * ���ܻ��ֵ������ֻ����n ��ʱ������Ϊf(n,n)<br />
	 * �ֿ��ǵݹ���ֹ��������k=1����ֻ�ܻ��ֳ�n��1 ,��f(n,1)=1<br />
	 * 
	 * @param n 1<=n<=50
	 * @param k 1<=k<=n
	 * @return  ��n���ֳ������������k�Ļ�����
	 */
	public static int int_div_N(int n,int k){//f(n, k)
		if (0>n || 0>=k) {
			return 0;//���ܻ���
		}
		
		if (0 == n) {
			return 1;//�涨��������f(n,n)=1+f(n,n-1)
		}
		
		if (1 == k) {
			return 1;//f(n, 1)=1
		}
		if (n<k) {
			return int_div_N(n, n);
		}else {
			//f(n-k, k)��ʾ��������k�Ļ�����(n=(n-k) + k)
			//f(n, k-1)��ʾ�������Ϊk-1����������k���Ļ�����
			return int_div_N(n-k, k) + int_div_N(n, k-1);
		}
	}
	
	/**
	 * ��n>kʱ��<br />
	 * g(n, k)��ʾ ��n���ֳɼ�������������k�Ļ�����<br />
	 * h(n, k)��ʾ��n���ֳ�k��������֮�͵Ļ�����<br />
	 * ������k�����ӣ�,��n�ֳ�n��1������ÿ�����ӷ�һ��1��������ʣ��n-k��1<br />
	 * ʣ�µ�n-k �ֳɲ�����k��Ļ�����Ϊg(n-k, k)���༴h(n, k)=g(n-k, k)<br />
	 * g(n, k)=h(n, 1)+h(n, 2)+...+h(n, k)�༴��<br />
	 * g(n, k)=g(n-k, 1)+g(n-k, 2)+...+g(n-k, k)�˼�Ϊ�ݹ�ʽ<br />
	 * ��n=kʱ��<br/>
	 * g(n, n)����ʾ��n���ֳ������������n�Ļ����� �༴ f(n, n)
	 * ��n&ltkʱ��<br />
	 * n���ֻ�ܻ���Ϊn��,��ʱg(n, k)=f(n, n)<br />
	 * �ֿ��ǵݹ���ֹ��������k=1����ֻ�ܻ��ֳ�n ,��f(n,1)=1<br />
	 * 
	 * @param n 1<=n<=50
	 * @param k 1<=k<=n ����������
	 * @return ��n���ֳɼ�������������k�Ļ�����
	 */
	public static int int_div_maxK(int n,int k){//g(n, k)
		
		if (0>=k ) {
			return 0;//n���ֳܷɴ���n��������
		}
		if (0==n) {
			return 1;// n=n 
		}
		if (n<=k) {
			return int_div_N(n,n);//n<=kʱ��n���ֻ�ܻ���Ϊn��
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
	 * @param k 1<=k<=n ����������
	 * @return ��n���ֳ�k��������֮�͵Ļ�����
	 */
	public static int int_div_K(int n,int k){//h(n, k)
		//��int_div_maxK�еķ���֪��h(n, k) = g(n-k, k)
		return int_div_maxK(n-k,k);
	}
	
	/**
	 * p(n, k)��ʾ��n���ֳ������������k�Ҽ�����Ϊ���������Ļ�����<br />
	 * ��kΪż��ʱ��<br />
	 * ������������ֻ��Ϊk-1 ������p(n, k)=p(n, k-1)<br />
	 * ��kΪ����ʱ��<br />
	 * ���n>k<br />
	 * &nbsp;&nbsp;p(n-k, k)��ʾ��������k�Ļ�����(n=(n-k) + k)<br />
	 * &nbsp;&nbsp;p(n, k-2)��ʾ�������Ϊk-2����������k���Ļ�����<br />
	 * &nbsp;&nbsp;��p(n,k)=p(n-k, k)+p(n, k-2)<br />
	 * ���n=k<br />
	 * &nbsp;��nΪ����<br />
	 * &nbsp;&nbsp;���������Ϊn������n>kʱ�Ļ���<br />
	 * &nbsp;&nbsp;p(n,n)=p(0, n)+p(n, n-2)=1+p(n, n-2) ����1Ϊֻ����n����� <br />
	 * &nbsp;��nΪż��ʱ<br />
	 * &nbsp;&nbsp;���������Ϊn-1 ����ʱ���Ȼ���Ϊ1��n-1 ��n-1Ϊ���������ͬ��<br />
	 * &nbsp;&nbsp;��p(n,n)=1+p(n-1,n-1) ����1Ϊֻ����1��n-1����� <br />
	 * ��n&ltkʱ<br />
	 * &nbsp;&nbsp;����������Ϊn<br />
	 * &nbsp;&nbsp;��ʱp(n,k)=p(n,n)
	 * ���ǵݹ���ֹ�����<br />
	 * &nbsp;&nbsp;��k=1ʱ��p(n, 1)=1(n����n��1)<br />
	 * &nbsp;&nbsp;��0>n || 0>=k ʱ�����ܻ��֣�����0<br />
	 * 
	 * 
	 * @param n 1<=n<=50
	 * @param k 1<=k<=n
	 * @return  ��n���ֳ������������k�Ҽ�����Ϊ���������Ļ�����
	 */
	public static int int_div_N_odds(int n,int k){//p(n, k)
		if (0>n || 0>=k) {
			return 0;//���ܻ���
		}
		
		if (!isOdd(k)) {//kΪż��ʱ��������ֻ��Ϊk-1��������
			return int_div_N_odds(n, k-1);
		}else {//kΪ����
			if (0 == n) {
				return 1;//�涨��������p(n,n)=1+p(n,n-2)
			}
			if (1 == k) {
				return 1;//p(n, 1)=1(n����n��1)
			}
			if (n<k) {
				return int_div_N_odds(n, n);
			}else if (n == k) {
				if (isOdd(n)) {
					return  1+int_div_N_odds(n, n-2);//1Ϊֻ����n����� 
				}else {
					return  1+int_div_N_odds(n-1, n-1);//1Ϊֻ����1��n-1 �����
				}
			}else {
				//p(n-k, k)��ʾ��������k�Ļ�����(n=(n-k) + k)
				//p(n, k-2)��ʾ�������Ϊk-2����������k���Ļ�����
				return int_div_N_odds(n-k, k) + int_div_N_odds(n, k-2);
			}
		}
		
	}
	
	/**
	 * @param n
	 * @return ���n�������򷵻�true ���򷵻�false 
	 */
	public final  static boolean isOdd(int n) {//��������
		return (0== n%2)?false:true;
	}
	
	/**
	 * ��������<br />
	 * q(i,sum)��ʾ����i������Ϊֹ������������Ϊsum<br />
	 * ��Ϊ���������<br />
	 * ȡ�˵�i��������Ķ������򵽵�i-1������Ϊֹ������������Ϊsum-i ��q(i-1,sum-i)<br />
	 * ûȡ��i��������Ķ������򵽵�i-1������Ϊֹ������������Ϊsum��q(i-1,sum)<br />
	 * �õݹ�ʽ��q(i,sum)=q(i-1,sum)+q(i-1,sum-i)<br />
	 * @param i ��i�����������������������ͬ
	 * @param sum ����i������Ϊֹ������������
	 * @return ��sum���ֳ����ɲ�ͬ������1��i֮�䣩֮�͵Ļ�����
	 */
	public static int int_div_diff_N(int i,int sum){//q(i,sum)
		if (0 <= i ){
			if(0 == sum) {
				return 1;
			}else if (0 > sum) {//���� ������������
				return 0;
			}else {//sum>0 δװ��
				return int_div_diff_N(i-1, sum)+int_div_diff_N(i-1, sum-i);
			}
		}else {//δװ��
			return 0;
		}
			
	}
	
	public static void main(String[] args) {
		//int n = 5,k = 2;
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print("����������n��");
			int n = scanner.nextInt();
			System.out.print("����������k��");
			int k = scanner.nextInt();
			System.out.println(int_div_N(n, n));//��һ��
			System.out.println(int_div_K(n, k));//�ڶ���
			System.out.println(int_div_N(n, k));//������
			System.out.println(int_div_N_odds(n,n));//������
			System.out.println(int_div_diff_N(n,n));//������
			System.out.println();//������
		}
	}

}

