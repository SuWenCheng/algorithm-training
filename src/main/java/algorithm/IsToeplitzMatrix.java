package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 托普利茨矩阵
 *
 * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 *
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/toeplitz-matrix
 */
@Slf4j
public class IsToeplitzMatrix {

    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (n <= 1) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            int num = matrix[0][i];
            for (int j = 1; j < m; j++) {
                if (i + j >= n) {
                    break;
                }
                if (num != matrix[j][i + j]) {
                    return false;
                }
            }
        }
        for (int i = 1; i < m - 1; i++) {
            int first = matrix[i][0];
            for (int j = i + 1; j < m; j++) {
                if (j - i >= n) {
                    break;
                }
                if (first != matrix[j][j - i]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void test() {
        int[][] matrix = {{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        boolean flag = isToeplitzMatrix(matrix);
        log.info(flag + "");
    }

}
