package class04;

/**
 * 求逆序对问题
 */
public class Code03_ReversePair {

    public static int reverPairNumber(int[] arr) {
        if (arr == null || arr.length < 2){
            return 0;
        }

        return process(arr,0,arr.length-1);
    }

    // arr[L..R]既要排好序，也要求逆序对数量返回
    // 所有merge时，产生的逆序对数量，累加，返回
    // 左 排序 merge并产生逆序对数量
    // 右 排序 merge并产生逆序对数量
    public static int process(int[] arr, int l, int r) {
        if (l == r){
            return 0;
        }

        int mid = l + ((r-l) >> 1);
        return process(arr,l,mid)
                + process(arr,mid+1,r)
                + merge(arr,l,mid,r);

    }

    //对于每一个数来说，就是求这个数的右边，有几个数比他小
    //所以可以逆序merge，比较左组和右组的数，copy较大的一个，当copy左组的数时，会产生逆序数。
    //注意相等的时候copy右组，因为此时不知道右边有几个数比左组的数小
    public static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r-l+1];
        int i = 0;
        int p1 = l;
        int p2 = m+1;
        int ans = 0;

        while (p1 <= m && p2 <= r){
            ans += arr[p1] > arr[p2] ? (r-p2+1) : 0;
            help[i++] = arr[p1] > arr[p2] ? arr[p1++] : arr[p2++];
        }

        //左组越界
        while (p2 <= r){
            help[i++] = arr[p2++];
        }

        //[5,4,4,2] [9,8,7,6]
        //右组越界
        while (p1 <= m){
            help[i++] = arr[p1++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
        return ans;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (reverPairNumber(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }


}
