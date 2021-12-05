package class05;

import util.ArrayUtils;

import java.awt.*;
import java.util.Arrays;
import java.util.Stack;

public class Code02_PartitionAndQuickSort {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process(arr, 0, arr.length - 1);

    }

    //非迭代版本
    public static void quickSort2(int arr[]) {
        if (arr == null || arr.length < 2) {
            return;
        }

        Stack<Op> ops = new Stack<Op>();
        int[] equalArea = partition(arr, 0, arr.length - 1);
        ops.push(new Op(0, equalArea[0] - 1));
        ops.push(new Op(equalArea[1] + 1, arr.length - 1));

        while (!ops.isEmpty()) {
            Op pop = ops.pop();
            int L = pop.l;
            int R = pop.r;

            if (L < R) {
                int[] partition = partition(arr, L, R);
                ops.push(new Op(L, partition[0] - 1));
                ops.push(new Op(partition[1] + 1, R));
            }

        }
    }


    // 要处理的是什么范围上的排序
    public static class Op {
        public int l;
        public int r;

        public Op(int left, int right) {
            l = left;
            r = right;
        }
    }

    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        swap(arr, l + (int) Math.random() * (r - l + 1), r);
        int[] equalArea = partition(arr, l, r);
        process(arr, l, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, r);

    }

    private static int[] partition(int[] arr, int l, int r) {
        if (l == r) {
            return new int[]{l, r};
        }

        if (l > r) {
            return new int[]{-1, -1};
        }

        int less = l - 1;
        int more = r;
        int index = l;

        while (index < more) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }

        swap(arr, more, r);

        return new int[]{less + 1, more};
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }


    public static void main(String[] args) {
        int maxValue = 100;
        int maxSize = 100;
        int times = 10000;

        System.out.println("test started");

        for (int i = 0; i < times; i++) {
            int[] a = ArrayUtils.generateRandomArray(maxSize, maxValue);
            int[] b = Arrays.copyOf(a, a.length);

            quickSort2(a);
            comparator(b);

            if (!Arrays.equals(a, b)) {
                System.out.println("error......");
                System.out.println(Arrays.toString(a));
                System.out.println(Arrays.toString(b));
                break;
            }
        }

        System.out.println("test completed");
    }
}
