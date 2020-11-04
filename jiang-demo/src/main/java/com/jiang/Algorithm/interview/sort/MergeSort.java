package com.jiang.Algorithm.interview.sort;

import java.util.Arrays;

/**
 * 归并排序的算法实现
 */
public class MergeSort {
    //算法类不允许产生任何实例
    private MergeSort() {
    }

    //将arr[l...mid] 和arr[mid+1....r] 两部分进行归并
    private static void merge(Comparable[] arr, int l, int mid, int r) {
        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);
        //初始化，i指向左半部分的起始；j指向右半部分其实索引位置mid+1
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            //
            if (i > mid) {
                //左半部分元素已经全部处理完毕
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                //右半部分元素已经全部处理完毕
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l].compareTo(aux[j - i]) < 0) {
                //左半部分所指元素<右半部分所指元素
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    private static void sort(Comparable[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (r + l) / 2;
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }


    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    public static void main(String[] args) {
        // Merge Sort是我们学习的第一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        // 注意：不要轻易尝试使用SelectionSort, InsertionSort或者BubbleSort处理100万级的数据
        // 否则，你就见识了O(n^2)的算法和O(nlogn)算法的本质差异：）
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("com.jiang.Algorithm.interview.sort.MergeSort", arr);

        return;
    }
}
