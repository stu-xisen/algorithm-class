package class04;

import util.ArrayUtils;

import java.util.Arrays;

public class Code02_SmallSum {
    //递归实现
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0 ;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid)
                + process(arr, mid + 1, R)
                + merge(arr, mid, L, R);
    }

    private static int merge(int[] arr, int mid, int l, int r) {
        //辅助数组
        int[] help = new int[r - l + 1];
        //辅助数组指针
        int i = 0;
        //左指针
        int p1 = l;
        //右指针
        int p2 = mid + 1;
        int ans = 0;
        //p1,p2都没有越界
        while (p1 <= mid && p2 <= r) {
            ans += arr[p1] < arr[p2] ? (r-p2+1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
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
        return ans;
    }


    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }


    public static void main(String[] args) {

        for (int i = 0; i < 100000; i++) {
            int[] a = ArrayUtils.generateRandomArray(5);
            int[] b = Arrays.copyOf(a, a.length);
            if (comparator(a) != smallSum(b)){
                System.out.println("error......");
                break;
            }
        }
        System.out.println("success......");
    }
}
