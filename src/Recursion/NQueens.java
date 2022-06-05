package Recursion;

import java.util.LinkedList;
import java.util.List;

// n皇后，只能暴力递归，无低阶优化方式
// 使用迭代的方法，由于无回溯，只能找到一种结果

public class NQueens {
    List<List<String>> res = new LinkedList<>();
    List<String> ans = new LinkedList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] chess = new char[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                chess[row][col] = '.';
            }
        }

        dfs(chess, n, 0); // 放第一行
        return res;
    }

    private void dfs(char[][] chess, int n, int row) {
        if (row == n) { // 放完了最后一行
            res.add(new LinkedList<>(ans));
            return;
        }

        for (int col = 0; col < n; col++) { // 尝试本行的每一个位置
            if (isValid(chess, row, col)) {
                chess[row][col] = 'Q';
                ans.add(new String(chess[row]));
                dfs(chess, n, row + 1);

                chess[row][col] = '.'; // 恢复现场
                ans.remove(ans.size() - 1);
            }
        }
    }

    private boolean isValid(char[][] chess, int i, int j) {
        for (int row = 0; row < chess.length; row++) {
            for (int col = 0; col < chess[row].length; col++) {
                if (col == j && row < i && chess[row][col] == 'Q') { // 同一列
                    return false;
                }
                if (row == i && col < j && chess[row][col] == 'Q') { // 同一行
                    return false;
                }
                if (row < i && Math.abs(row - i) == Math.abs(col - j) && chess[row][col] == 'Q') {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new NQueens().solveNQueens(4));
    }
}
