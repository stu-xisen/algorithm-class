package class04;

import util.ArrayUtils;

import java.util.Arrays;

public class Code01_MergeSort {


    //递归实现
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return;
        }

        process(arr, 0, arr.length - 1);
    }

    //迭代实现
    public static void mergeSort2(int[] arr) {
        int mergeSize = 1;
        int N = arr.length;
        while (mergeSize < N){
            int L = 0;

            //左组的第一个位置未越界
            while (L < N){
                //左组最后一个元素的位置
                int M = L + mergeSize - 1;
                if (M >= N){
                    //左组不够了，直接break，不进行mergele
                    break;
                }
                //右组的最后一个元素的位置
                int R = M + Math.min(mergeSize,N-M-1);
                merge(arr,M,L,R);
                L = R + 1;
            }

            mergeSize <<= 1;
        }


    }

    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, mid, L, R);
    }

    private static void merge(int[] arr, int mid, int l, int r) {
        //辅助数组
        int[] help = new int[r - l + 1];
        //辅助数组指针
        int i = 0;
        //左指针
        int p1 = l;
        //右指针
        int p2 = mid + 1;

        //p1,p2都没有越界
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        //p2越界,p1未越界
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }

        //p1越界,p2未越界
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }

    }



    public static void main(String[] args) {

        for (int i = 0; i < 100000; i++) {
            int[] a = ArrayUtils.generateRandomArray(5);
            int[] b = Arrays.copyOf(a, a.length);
            Arrays.sort(a);
            mergeSort2(b);
            if (!Arrays.equals(a, b)) {
                System.out.println("error......");
                System.out.println(a);
                System.out.println(b);
                break;
            }
        }
        System.out.println("success......");
    }
}
