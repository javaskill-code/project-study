package com.jiang.Algorithm.interview.sort.shellSort;

/**
 * 希尔排序算法
 */
public class ShellSort {

    public static void main(String[] args) {
        new ShellSort().run();
    }

    private void run() {
        int a[] = {3, 1, 5, 7, 2, 4, 9, 6};
        System.err.println("=============希尔排序算法====================");
        shellSort(a);
        printResult(a, a.length);
    }

    /**
     * 希尔排序(缩小增量法) 属于插入类排序
     * 不稳定
     * @param a
     */
    private void shellSort(int[] a){
        int n=a.length;
        int gap=n/2;
        while(gap>=1){
            for(int i=gap;i<a.length;i++){
                int j=0;
                int temp = a[i];
                for(j=i-gap;j>=0 && temp<a[j];j=j-gap){
                    a[j+gap] = a[j];
                }
                a[j+gap] = temp;
            }
            printResult(a,a.length);
            gap = gap/2;
        }
    }

    /**
     * 严格按照定义来写的希尔排序
     *
     * @param a
     */
    private void shellSort2(int[] a) {
        int n = a.length;
        int i, j, k, gap;
        for (gap = n / 2; gap > 0; gap /= 2) {
            for (i = 0; i < gap; i++) {
                for (j = i + gap; j < n; j += gap) {
                    int temp = a[j];
                    for (k = j - gap; k >= 0 && a[k] > temp; k -= gap) {
                        a[k + gap] = a[k];
                    }
                    a[k + gap] = temp;
                }
            }
            printResult(a, a.length);
        }
    }

    private void printResult(int[] a, int n) {
        for (int j = 0; j < n; j++) {
            System.out.print(" " + a[j]);
        }
        System.out.println();
    }
}
