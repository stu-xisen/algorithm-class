package class02;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Code02_EvenTimesOddTimes {

    //只有一个数出现奇数次，其他数都是偶数次
    public static void oddTimesNum1(int [] arr){
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result ^= arr[i];
        }
        System.out.println(result);;
    }

    //只有2个数出现奇数次，其他数都是偶数次
    public static void oddTimesNum2(int [] arr){
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        //设出现奇数次的两个数是a和b，则eor = a ^ b;
        //a != b --> eor != 0
        //取出eor最右侧的1。
        int rigthOne = getRigthOne(eor);
        //假如rightOne的值是0001010101000，则说明a和b再第3位值不同的，必然有一个是0，一个是1
        //所以只需要让数组中所有第3位位1的数做异或，就相当于是一个数出现奇数次，其他数出现偶数次，全部做异或必然可以得到a,b中第3位位1的那个
        int eor1 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rigthOne) == 0){
                eor1 ^= arr[i];
            }
        }
        System.out.println(eor1 + "," + (eor^eor1));

    }

    //取出一个数最右侧的1，其他位都为0
    public static int getRigthOne(int a){
        //System.out.println(a & (~a) + 1);
        return a & ((~a) + 1);
    }

    public static void main(String[] args) {

        int[] arr = new int[]{1,6,1,2,6,6,2,2,9,9,6,3};
        //oddTimesNum1(arr);
        oddTimesNum2(arr);

    }
}
