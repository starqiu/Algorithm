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
 * 实现功能： 最长递增子序列<br />
 * 问题描述： 随机生成小于等于n的自然数的一个序列，<br />
 * 输出其最长递增子序列（任意一个即可）。<br />
 * n 分别取 1000，3000，10000。<br />
 * 例： n=5 随机序列为 5 1 4 2 3，正确输出为1 2 3，<br />
 * 即长度为3的递增子序列。<br />
 * 提示：参考LCS，思考能否达到时间复杂度（O(nlogn)）
 * </p>
 * <p>
 * date author email notes<br />
 * -------- --------------------------- ---------------<br />
 * 2013-11-7 邱星 starqiu@mail.ustc.edu.cn 新建类<br />
 * </p>
 * 
 */
public class LIS {
	/** 每行显示的数字个数*/
	public static int MAXNUM =10;

	/**
	 * @param arr
	 *            待查找最长递增子串的字串（数组）
	 * @return 最长递增子串的字串长度
	 */
	public static int lis(int[] arr) {
		int arrLen = arr.length;// 数组长度
		int curLen = 1;// 当前最长子串长度
		int sub[] = new int[arrLen];// 存储字串长度为i+1（i为数组下标）的最小末尾数字
		int sub_index[] = new int[arrLen];// 存储字串长度为i+1（i为数组下标）的最小末尾数字所在原数组中的位置
		int sub_pre[] = new int[arrLen];// 存储字串长度为i+1（i为数组下标）的最小末尾数字的前一个数字的索引
		sub[0] = arr[0];//初始化
		sub_pre[0]=-1;
		//二分查找使用变量
		int left =0;
		int right =0; 
		int mid=0;
		
		//扫描数组
		for (int i = 1; i < arrLen; i++) {
			sub_pre[i]=-1;
			//二分查找，定位当前元素位置(返回第一个大于arr[i]的位置)，时间复杂度O(lgN)
			left = 0;
			right =curLen-1;
			while(left <= right){
				mid = left + (right-left)/2;//二分，防溢出
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
			if (left == curLen) {//如果arr[i]比sub中所有元素都大，新增了一个sub数组元素
				curLen++;
			}
		}
		/*for (int i = 0; i < arrLen; i++) {
			System.out.print(" "+sub[i]);
			System.out.print(" "+sub_index[i]);
			System.out.print(" "+sub_pre[i]);
			System.out.println();
		}*/
		//将最长子串正序存储在sub数组中
		int pre = sub_index[curLen-1];
		for (int i = curLen-1; i >=0; i--){
			sub[i]=arr[pre];
			pre = sub_pre[pre];
		}
		//打印最长字串
		System.out.printf("最长子串长度为 %d ，值为：\n",curLen);
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
			System.out.print("请输入一个自然数 n:");
			n = scanner.nextInt();
			int a[] = new int[n];
			List<Integer> aList = new LinkedList<Integer>();
			while(n>0){
				aList.add(n--);
			}
			Collections.shuffle(aList);//乱序
			int i=0;
			System.out.println("生成的随机序列为：");
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
