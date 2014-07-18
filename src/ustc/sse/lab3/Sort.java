/*
 * ============================================================
 * The SSE USTC Software License
 * 
 * Sort.java
 * 2013-11-7
 * 
 * Copyright (c) 2006 China Payment and Remittance Service Co.,Ltd        
 * All rights reserved.
 * ============================================================
 */
package ustc.sse.lab3;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * <p>
 * 实现功能： 常见排序算法的实现与性能比较<br />
 * 问题描述：实现合并排序，插入排序，希尔排序，快速排序，<br />
 * 冒泡排序，桶排序算法<br />
 * 实验要求：<br />
 * （1） N=10时，排序结果。 <br />
 * （2） N=1000，10000，100000时，<br />
 * 每个排序用不同的样本多试验几次（最低5次）得出<br />
 * 平均时间，比较不同排序算法所用的平均时间。
 * </p>
 * <p>
 * date author email notes<br />
 * -------- --------------------------- ---------------<br />
 * 2013-11-7 邱星 starqiu@mail.ustc.edu.cn 新建类<br />
 * </p>
 * 
 */
public class Sort {
	/** 每行显示的数字个数 */
	public static int MAXNUM = 100;

	/**
	 * 归并排序
	 * 
	 * @param array
	 *            待排序数组
	 * @return
	 */
	public static long mergeSort(int[] array) {
		long t1 = new Date().getTime();
		int len = array.length;
		subMergeSort(array, 0, len - 1);
		long t2 = new Date().getTime();
		return t2 - t1;
	}

	/**
	 * 归并排序子算法
	 * 
	 * @param array
	 * @param p
	 * @param r
	 */
	public static void subMergeSort(int[] array, int p, int r) {
		if (p < r) {
			int q = p + (r - p) / 2;
			subMergeSort(array, p, q);
			subMergeSort(array, q + 1, r);
			merge(array, p, q, r);
			printArray(array);
		}
	}

	/**
	 * 归并排序的合并两个子数组的算法
	 * 
	 * @param array
	 * @param p
	 *            起始位置
	 * @param q
	 *            分割位置
	 * @param r
	 *            结束位置
	 */
	public static void merge(int[] array, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
		int[] L = new int[n1 + 1];
		int[] R = new int[n2 + 1];
		for (int i = 0; i < n1; i++) {
			L[i] = array[p + i];
		}
		for (int i = 0; i < n2; i++) {
			R[i] = array[q + 1 + i];
		}
		L[n1] = Integer.MAX_VALUE;
		R[n2] = Integer.MAX_VALUE;
		int i = 0, j = 0;
		for (int k = p; k <= r; k++) {
			if (L[i] <= R[j]) {
				array[k] = L[i++];
			} else {
				array[k] = R[j++];
			}
		}
	}

	/**
	 * 插入排序
	 * 
	 * @param array
	 *            待排序数组
	 * @return
	 */
	public static long insertSort(int[] array) {
		long t1 = new Date().getTime();
		int len = array.length;
		int j = 0;
		for (int i = 1; i < len; i++) {
			for (j = i - 1; j >= 0 && array[j + 1] < array[j]; j--) {
				swap(array, j + 1, j);
			}
			printArray(array);
		}
		long t2 = new Date().getTime();
		return t2 - t1;
	}

	/**
	 * 希尔排序
	 * 
	 * @param array
	 *            待排序数组
	 * @return
	 */
	public static long shellSort(int[] array) {
		long t1 = new Date().getTime();
		int len = array.length;
		int step = 2;
		for (int gap = len / 2; gap > 0; gap /= step) {
			// 对每个子序列进行直接插入排序
			for (int i = gap; i < len; i++) {
				for (int j = i - gap; j >= 0 && array[j] > array[j + gap]; j -= gap) {
					swap(array, j, j + gap);
				}
			}
			printArray(array);
		}
		long t2 = new Date().getTime();
		return t2 - t1;
	}

	/**
	 * 快速排序
	 * 
	 * @param array
	 *            待排序数组
	 * @return
	 */
	public static long quickSort(int[] array) {
		long t1 = new Date().getTime();
		int len = array.length;
		randomQuickSort(array, 0, len - 1);
		long t2 = new Date().getTime();
		return t2 - t1;
	}

	/**
	 * 快速排序分割算法
	 * 
	 * @param array
	 * @param p
	 * @param r
	 * @return
	 */
	public static int partition(int[] array, int p, int r) {
		int x = array[r];
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (array[j] <= x) {
				i++;
				swap(array, i, j);
			}
		}
		swap(array, i + 1, r);
		return i + 1;
	}

	/**
	 * 快速排序分割算法的随机化版本
	 * 
	 * @param array
	 * @param p
	 * @param r
	 * @return
	 */
	public static int randomPartition(int[] array, int p, int r) {
		int i = new Random().nextInt(r - p + 1) + p;
		swap(array, i, r);
		return partition(array, p, r);
	}

	/**
	 * 快速排序算法的随机化版本
	 * 
	 * @param array
	 * @param p
	 * @param r
	 * @return
	 */
	public static void randomQuickSort(int[] array, int p, int r) {
		if (p < r) {
			int q = randomPartition(array, p, r);
			randomQuickSort(array, p, q - 1);
			randomQuickSort(array, q + 1, r);
			printArray(array);
		}
	}

	/**
	 * 冒泡排序
	 * 
	 * @param array
	 *            待排序数组
	 * @return
	 */
	public static long bubbleSort(int[] array) {
		long t1 = new Date().getTime();
		int len = array.length;
		for (int i = 0; i < len; i++) {
			for (int j = 1; j < len - i; j++) {
				if (array[j] < array[j - 1]) {
					swap(array, j, j - 1);
				}
			}
			printArray(array);
		}
		long t2 = new Date().getTime();
		return t2 - t1;
	}

	/**
	 * 桶排序
	 * 
	 * @param array
	 *            待排序数组
	 * @return
	 */
	public static long bucketSort(int[] array) {
		long t1 = new Date().getTime();
		int len = array.length;
		int bucketNum = (int) Math.sqrt(len) + 1;// 桶个数
		// 此处+1表示桶的第0位（bucket[no][0]）表示该桶的元素个数
		int[][] bucket = new int[bucketNum][bucketNum + 1];
		// 初始化桶中的元素个数为0
		for (int i = 0; i < bucketNum; i++) {
			bucket[i][0] = 0;
		}
		int left = 1, right = 0, mid = 0, no = 0;
		// 将数组中的数放到桶中
		for (int i = 0; i < len; i++) {
			// 定位一个桶
			// len此处表示数组中的最大值,array[i]-1表示从0开始计数
			// 这样做的目的是防止最大数单独占据最后一个桶，造成不必要的浪费
			no = (array[i] - 1) * bucketNum / len;
			// 往桶里插入数据（利用二分查找定位，再进行直接插入）,
			left = 1;
			right = bucket[no][0];
			while (left <= right) {
				mid = left + (right - left) / 2;
				if (array[i] < bucket[no][mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			for (int k = bucket[no][0]; k >= left; k--) {
				swap(bucket[no], k, k + 1);
			}
			bucket[no][left] = array[i];
			bucket[no][0]++;
		}
		// 将桶中的数依次放回到array数组中
		int index = 0;
		for (int i = 0; i < bucketNum; i++) {
			for (int j = 1; j <= bucket[i][0]; j++) {
				array[index++] = bucket[i][j];
			}
		}
		long t2 = new Date().getTime();
		return t2 - t1;
	}

	/**
	 * 元素交换
	 * 
	 * @param array
	 *            数组
	 * @param i
	 *            数组下标
	 * @param j
	 *            数组下标
	 */
	public static void swap(int[] array, int i, int j) {
		if (i == j) {
			return;
		}
		array[i] = array[i] ^ array[j];
		array[j] = array[i] ^ array[j];
		array[i] = array[i] ^ array[j];
	}

	public static void printArray(int[] array) {
		int len = array.length;
		for (int i = 0; i < len; i++) {
			System.out.printf("%5d ", array[i]);
			if ((MAXNUM - 1) == i % MAXNUM) {
				System.out.println();
			}
		}
		System.out.println();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 0, len = 0;
		Scanner scanner = new Scanner(System.in);
		int sortKind =0;
		while (true) {
			System.out.print("请输入一个自然数 n:");
			n = scanner.nextInt();
			len = n;
			int array[] = new int[n];
			List<Integer> aList = new LinkedList<Integer>();
			while (n > 0) {
				aList.add(n--);
			}
			Collections.shuffle(aList);

			int i = 0;
			System.out.println("生成的随机序列为：");
			for (Integer e : aList) {
				array[i] = e.intValue();
				System.out.printf("%5d ", array[i]);
				if ((MAXNUM - 1) == i % MAXNUM) {
					System.out.println();
				}
				i++;
			}
			System.out.println();
			System.out.println("请选择排序算法：");
			System.out.println("1.合并排序");
			System.out.println("2.插入排序");
			System.out.println("3.希尔排序");
			System.out.println("4.快速排序");
			System.out.println("5.冒泡排序");
			System.out.println("6.桶排序");
			sortKind = scanner.nextInt();
			//sortKind = scanner.nextInt();
			long t =0;
			System.out.println("排序后的序列为：");
			switch (sortKind) {
			case 1:
				t =mergeSort(array);
				//printArray(array);
				System.out.printf("长度为%d的合并排序耗时%d ms\n", len, t);
				break;
			case 2:
				t =insertSort(array);
				//printArray(array);
				System.out.printf("长度为%d的插入排序耗时%d ms\n", len, t);
				break;
			case 3:
				t =shellSort(array);
				//printArray(array);
				System.out.printf("长度为%d的希尔排序耗时%d ms\n", len, t);
				break;
			case 4:
				t =mergeSort(array);
				//printArray(array);
				System.out.printf("长度为%d的快速排序耗时%d ms\n", len, t);
				break;
			case 5:
				t =bubbleSort(array);
				//printArray(array);
				System.out.printf("长度为%d的冒泡排序耗时%d ms\n", len, t);
				break;
			case 6:
				t =bucketSort(array);
				printArray(array);
				System.out.printf("长度为%d的桶排序耗时%d ms\n", len, t);
				break;
			default:
				break;
			}
			
			
			//System.out.printf("长度为%d的冒泡排序耗时%d ms\n", len, bubbleSort(array));
			//System.out.printf("长度为%d的插入排序耗时%d ms\n", len, insertSort(array));
			//System.out.printf("长度为%d的快速排序耗时%d ms\n", len, quickSort(array));
			//System.out.printf("长度为%d的合并排序耗时%d ms\n", len, mergeSort(array));
			//System.out.printf("长度为%d的希尔排序耗时%d ms\n", len, shellSort(array));
			//System.out.printf("长度为%d的桶排序耗时%d ms\n", len, bucketSort(array));
			// t =bubbleSort(array);
			// t =insertSort(array);
			 //t =quickSort(array);
			//t =mergeSort(array);
			// t =shellSort(array);
			 //t =bucketSort(array);
			//printArray(array);
			//System.out.printf("长度为%d的排序耗时%d ms\n", len, t);
		}
		// int array[] = { 3, 6, 7, 4, 1, 2, 5 };
	}

}
