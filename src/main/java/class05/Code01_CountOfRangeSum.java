package class05;

/**
 * 327. 区间和的个数
 * 给你一个整数数组nums 以及两个整数lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含lower和upper）之内的 区间和的个数 。
 *
 * 区间和S(i, j)表示在nums中，位置从i到j的元素之和，包含i和j(i ≤ j)。
 *
 * 示例 1：
 * 输入：nums = [-2,5,-1], lower = -2, upper = 2
 * 输出：3
 * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
 * 示例 2：
 *
 * 输入：nums = [0], lower = 0, upper = 0
 * 输出：1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-of-range-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 思路：
 * 前缀和数组：sum[i] = nums[0] + nums[1] +++ nums[i]
 * S[i,j] = sum[j] - sum[i]
 * 问题转化：
 *      1.假设以nums[i]为结尾的子数组求和的值，位于[lower,upper]的个数为a,则i从0递增到nums.length-1，计算出所有的a相加即是此题的结果。
 *        例如num[3]为结尾的子数组有：num[3]; num[3],num[2]; num[3],num[2],num[1]; num[3],num[2],num[1],num[0]; 分别计算这些子数组的和，并记录下和的值位于[lower,upper]的个数
 *      2.又因为以num[j]为结尾的子数组的和S[i,j] = sum[j] - sum[i]。若想lower<=S[i,j]<=upper，所以 sum[j]-upper <= sum[i] <= sum[j]-lower。
 *        所以求以num[j]为结尾的子数组的和落在[lower,upper]中的个数，就可以转化为，sum[j]左侧有多少个sum[i]落在范围[sum[j]-upper,sum[j]-lower]。
 *        举例求以num[5]为结尾的子数组的和落在[10,40]中的个数，且sum[5]=100。就可以转化为在sum[0]-sum[4]中有个多少个数落在范围[60,90](60=100-40,90=100-10)
 *        最终问题就可以转化成在sum数组中求一个数左侧，有多少个数符合某个指标，可以套用mergesort算法。
 *      3.在merge过程中对于右组的每一个数sum[i]=a,计算左组中多少个数落在区间[a-upper,a-lower]。
 *        可以使用两个指针windowL,windowR作为一个滑动的窗口来框住左侧符合要求的数。
 *        如果sum[windowL]<(a-upper),向前滑动一个元素（注意是<,而不是<=,因为如果是<= 则当sum[windowL]=(a-upper)时候指针windowL还会向前走一个元素，就会漏掉sum[windowL]这个元素）
 *        如果sum[windowR]<=(a-upper),向前滑动一个元素（注意是<=,而不是<,因为如果是< 则当sum[windowR]=(a-lower)时候指针windowR就不会向前走一个元素，目的是为了窗口是[windowL,windowR)）
 *        这样计算个数结果就只需要windowR-windowL
 *
 *
 */
public class Code01_CountOfRangeSum {

    public static int countOfRangeSum(int[] nums, int lower, int upper){
        if (nums == null || nums.length < 1){
            return 0;
        }

        //防止求和溢出
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i-1] + nums[i];
        }


        return process(sum,0,sum.length-1,lower,upper);
    }

    private static int process(long[] sum, int l, int r, int lower, int upper) {
        if (l==r){
            //base case，为在merge过程中对于nums[i]计算的左侧子数组中会漏掉num[0]-num[i]这个子数组
            //所以在此处计算num[0]-num[i]是否落在区间
            if (sum[l] >= lower && sum[l] <= upper){
                return 1;
            }else {
                return 0;
            }
        }

        int mid = l + ((r-l)>>1);
        return process(sum,l,mid,lower,upper)
                + process(sum,mid+1,r,lower,upper)
                + merge(sum,l,mid,r,lower,upper);
    }

    private static int merge(long[] sum, int l, int mid, int r, int lower, int upper) {
        int ans = 0;

        int windowL = l;
        int windowR = l;

        //遍历右组所有数
        for (int i = mid+1; i <= r ; i++) {
            long max = sum[i] - lower;
            long min = sum[i] - upper;
            //滑动窗口左指针
            while (windowL <= mid && sum[windowL] < min){
                windowL ++;
            }
            //滑动窗口右指针
            while (windowR <= mid && sum[windowR] <= max){
                windowR ++;
            }
            //[windowL,windowR)
            ans += windowR - windowL;
        }

        //merge
        int p1 = l;
        int p2 = mid + 1;
        long[] help = new long[r - l + 1];
        int i = 0;

        while (p1 <= mid && p2 <= r){
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }

        while (p1 <= mid){
            help[i++] = sum[p1++];
        }

        while (p2 <= r){
            help[i++] = sum[p2++];
        }

        for (int j = 0; j < help.length; j++) {
            sum[l + j] = help[j];
        }

        return ans;
    }
}
