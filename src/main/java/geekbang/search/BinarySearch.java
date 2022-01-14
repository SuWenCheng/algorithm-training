package geekbang.search;

/**
 * 二分查找
 * 基于题目：求一个数的平方根？要求精确到小数点后 6 位
 */
public class BinarySearch {

    public static void main(String[] args) {
        System.out.println(square(6, 6));
    }

    public static double square(int num,  int precision) {
        if (num <= 1) {
            return num;
        }

        double result = 0;
        int left = 0;
        int right = num;
        int mid;
        // 计算整数部分
        while (left <= right) {
            mid = left + (right - left) / 2;
            double powValue = Math.pow(mid, 2);
            if (powValue == num) {
                return powValue;
            } else if (powValue > num) {
                right = mid - 1;
            } else {
                result = mid;
                left = mid + 1;
            }
        }

        // 计算小数部分
        double step = 1;
        for (int i = 1; i <= precision; i++) {
            step /= 10;
            left = 1;
            right = 9;
            int base = 0;
            while (left <= right) {
                mid = left + (right - left) / 2;
                double p = Math.pow(result + mid * step, 2);
                if (p > num) {
                    right = mid - 1;
                } else {
                    base = mid;
                    left = mid + 1;
                }
            }
            result += (base * step);
        }
        return result;
    }
}
