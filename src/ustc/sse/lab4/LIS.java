/*
 * ============================================================
 * The SSE USTC Software License
 * 
 * LIS.java
 * 2013-11-7
 * 
 * Copyright (c) 2006 China Payment and Remittance Service Co.,Ltd        
 * All rights reserved.
 * ============================================================
 */
package ustc.sse.lab4;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * ʵ�ֹ��ܣ� �����������<br />
 * ���������� �������С�ڵ���n����Ȼ����һ�����У�<br />
 * ���������������У�����һ�����ɣ���<br />
 * n �ֱ�ȡ 1000��3000��10000��<br />
 * ���� n=5 �������Ϊ 5 1 4 2 3����ȷ���Ϊ1 2 3��<br />
 * ������Ϊ3�ĵ��������С�<br />
 * ��ʾ���ο�LCS��˼���ܷ�ﵽʱ�临�Ӷȣ�O(nlogn)��
 * </p>
 * <p>
 * date author email notes<br />
 * -------- --------------------------- ---------------<br />
 * 2013-11-7 ���� starqiu@mail.ustc.edu.cn �½���<br />
 * </p>
 * 
 */
public class LIS {
	/** ÿ����ʾ�����ָ���*/
	public static int MAXNUM =10;

	/**
	 * @param arr
	 *            ������������Ӵ����ִ������飩
	 * @return ������Ӵ����ִ�����
	 */
	public static int lis(int[] arr) {
		int arrLen = arr.length;// ���鳤��
		int curLen = 1;// ��ǰ��Ӵ�����
		int sub[] = new int[arrLen];// �洢�ִ�����Ϊi+1��iΪ�����±꣩����Сĩβ����
		int sub_index[] = new int[arrLen];// �洢�ִ�����Ϊi+1��iΪ�����±꣩����Сĩβ��������ԭ�����е�λ��
		int sub_pre[] = new int[arrLen];// �洢�ִ�����Ϊi+1��iΪ�����±꣩����Сĩβ���ֵ�ǰһ�����ֵ�����
		sub[0] = arr[0];//��ʼ��
		sub_pre[0]=-1;
		//���ֲ���ʹ�ñ���
		int left =0;
		int right =0; 
		int mid=0;
		
		//ɨ������
		for (int i = 1; i < arrLen; i++) {
			sub_pre[i]=-1;
			//���ֲ��ң���λ��ǰԪ��λ��(���ص�һ������arr[i]��λ��)��ʱ�临�Ӷ�O(lgN)
			left = 0;
			right =curLen-1;
			while(left <= right){
				mid = left + (right-left)/2;//���֣������
				if (sub[mid]<arr[i]) {
					left = mid +1;
				}else {
					right = mid -1;
				}
			}
			sub[left]=arr[i];
			sub_index[left]=i;
			if (left>0) {
				sub_pre[i]=sub_index[left-1];
			}
			if (left == curLen) {//���arr[i]��sub������Ԫ�ض���������һ��sub����Ԫ��
				curLen++;
			}
		}
		/*for (int i = 0; i < arrLen; i++) {
			System.out.print(" "+sub[i]);
			System.out.print(" "+sub_index[i]);
			System.out.print(" "+sub_pre[i]);
			System.out.println();
		}*/
		//����Ӵ�����洢��sub������
		int pre = sub_index[curLen-1];
		for (int i = curLen-1; i >=0; i--){
			sub[i]=arr[pre];
			pre = sub_pre[pre];
		}
		//��ӡ��ִ�
		System.out.printf("��Ӵ�����Ϊ %d ��ֵΪ��\n",curLen);
		for (int i = 0; i < curLen; i++) {
			System.out.printf("%5d ",sub[i]);
			if ((MAXNUM-1) == i%MAXNUM){
				System.out.println("");
			}
		}
		System.out.println();
		return curLen ;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 0;
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print("������һ����Ȼ�� n:");
			n = scanner.nextInt();
			int a[] = new int[n];
			List<Integer> aList = new LinkedList<Integer>();
			while(n>0){
				aList.add(n--);
			}
			Collections.shuffle(aList);//����
			int i=0;
			System.out.println("���ɵ��������Ϊ��");
			for (Integer e : aList) {
				a[i]=e.intValue();
				System.out.printf("%5d ",a[i]);
				if ((MAXNUM-1) == i%MAXNUM) {
					System.out.println();
				}
				i++;
			}
			System.out.println();
			lis(a);
		}
	}

}
