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
 * ʵ�ֹ��ܣ� ���������㷨��ʵ�������ܱȽ�<br />
 * ����������ʵ�ֺϲ����򣬲�������ϣ�����򣬿�������<br />
 * ð������Ͱ�����㷨<br />
 * ʵ��Ҫ��<br />
 * ��1�� N=10ʱ���������� <br />
 * ��2�� N=1000��10000��100000ʱ��<br />
 * ÿ�������ò�ͬ�����������鼸�Σ����5�Σ��ó�<br />
 * ƽ��ʱ�䣬�Ƚϲ�ͬ�����㷨���õ�ƽ��ʱ�䡣
 * </p>
 * <p>
 * date author email notes<br />
 * -------- --------------------------- ---------------<br />
 * 2013-11-7 ���� starqiu@mail.ustc.edu.cn �½���<br />
 * </p>
 * 
 */
public class Sort {
	/** ÿ����ʾ�����ָ��� */
	public static int MAXNUM = 100;

	/**
	 * �鲢����
	 * 
	 * @param array
	 *            ����������
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
	 * �鲢�������㷨
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
	 * �鲢����ĺϲ�������������㷨
	 * 
	 * @param array
	 * @param p
	 *            ��ʼλ��
	 * @param q
	 *            �ָ�λ��
	 * @param r
	 *            ����λ��
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
	 * ��������
	 * 
	 * @param array
	 *            ����������
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
	 * ϣ������
	 * 
	 * @param array
	 *            ����������
	 * @return
	 */
	public static long shellSort(int[] array) {
		long t1 = new Date().getTime();
		int len = array.length;
		int step = 2;
		for (int gap = len / 2; gap > 0; gap /= step) {
			// ��ÿ�������н���ֱ�Ӳ�������
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
	 * ��������
	 * 
	 * @param array
	 *            ����������
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
	 * ��������ָ��㷨
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
	 * ��������ָ��㷨��������汾
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
	 * ���������㷨��������汾
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
	 * ð������
	 * 
	 * @param array
	 *            ����������
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
	 * Ͱ����
	 * 
	 * @param array
	 *            ����������
	 * @return
	 */
	public static long bucketSort(int[] array) {
		long t1 = new Date().getTime();
		int len = array.length;
		int bucketNum = (int) Math.sqrt(len) + 1;// Ͱ����
		// �˴�+1��ʾͰ�ĵ�0λ��bucket[no][0]����ʾ��Ͱ��Ԫ�ظ���
		int[][] bucket = new int[bucketNum][bucketNum + 1];
		// ��ʼ��Ͱ�е�Ԫ�ظ���Ϊ0
		for (int i = 0; i < bucketNum; i++) {
			bucket[i][0] = 0;
		}
		int left = 1, right = 0, mid = 0, no = 0;
		// �������е����ŵ�Ͱ��
		for (int i = 0; i < len; i++) {
			// ��λһ��Ͱ
			// len�˴���ʾ�����е����ֵ,array[i]-1��ʾ��0��ʼ����
			// ��������Ŀ���Ƿ�ֹ���������ռ�����һ��Ͱ����ɲ���Ҫ���˷�
			no = (array[i] - 1) * bucketNum / len;
			// ��Ͱ��������ݣ����ö��ֲ��Ҷ�λ���ٽ���ֱ�Ӳ��룩,
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
		// ��Ͱ�е������ηŻص�array������
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
	 * Ԫ�ؽ���
	 * 
	 * @param array
	 *            ����
	 * @param i
	 *            �����±�
	 * @param j
	 *            �����±�
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
			System.out.print("������һ����Ȼ�� n:");
			n = scanner.nextInt();
			len = n;
			int array[] = new int[n];
			List<Integer> aList = new LinkedList<Integer>();
			while (n > 0) {
				aList.add(n--);
			}
			Collections.shuffle(aList);

			int i = 0;
			System.out.println("���ɵ��������Ϊ��");
			for (Integer e : aList) {
				array[i] = e.intValue();
				System.out.printf("%5d ", array[i]);
				if ((MAXNUM - 1) == i % MAXNUM) {
					System.out.println();
				}
				i++;
			}
			System.out.println();
			System.out.println("��ѡ�������㷨��");
			System.out.println("1.�ϲ�����");
			System.out.println("2.��������");
			System.out.println("3.ϣ������");
			System.out.println("4.��������");
			System.out.println("5.ð������");
			System.out.println("6.Ͱ����");
			sortKind = scanner.nextInt();
			//sortKind = scanner.nextInt();
			long t =0;
			System.out.println("����������Ϊ��");
			switch (sortKind) {
			case 1:
				t =mergeSort(array);
				//printArray(array);
				System.out.printf("����Ϊ%d�ĺϲ������ʱ%d ms\n", len, t);
				break;
			case 2:
				t =insertSort(array);
				//printArray(array);
				System.out.printf("����Ϊ%d�Ĳ��������ʱ%d ms\n", len, t);
				break;
			case 3:
				t =shellSort(array);
				//printArray(array);
				System.out.printf("����Ϊ%d��ϣ�������ʱ%d ms\n", len, t);
				break;
			case 4:
				t =mergeSort(array);
				//printArray(array);
				System.out.printf("����Ϊ%d�Ŀ��������ʱ%d ms\n", len, t);
				break;
			case 5:
				t =bubbleSort(array);
				//printArray(array);
				System.out.printf("����Ϊ%d��ð�������ʱ%d ms\n", len, t);
				break;
			case 6:
				t =bucketSort(array);
				printArray(array);
				System.out.printf("����Ϊ%d��Ͱ�����ʱ%d ms\n", len, t);
				break;
			default:
				break;
			}
			
			
			//System.out.printf("����Ϊ%d��ð�������ʱ%d ms\n", len, bubbleSort(array));
			//System.out.printf("����Ϊ%d�Ĳ��������ʱ%d ms\n", len, insertSort(array));
			//System.out.printf("����Ϊ%d�Ŀ��������ʱ%d ms\n", len, quickSort(array));
			//System.out.printf("����Ϊ%d�ĺϲ������ʱ%d ms\n", len, mergeSort(array));
			//System.out.printf("����Ϊ%d��ϣ�������ʱ%d ms\n", len, shellSort(array));
			//System.out.printf("����Ϊ%d��Ͱ�����ʱ%d ms\n", len, bucketSort(array));
			// t =bubbleSort(array);
			// t =insertSort(array);
			 //t =quickSort(array);
			//t =mergeSort(array);
			// t =shellSort(array);
			 //t =bucketSort(array);
			//printArray(array);
			//System.out.printf("����Ϊ%d�������ʱ%d ms\n", len, t);
		}
		// int array[] = { 3, 6, 7, 4, 1, 2, 5 };
	}

}
