import sys
from collections import defaultdict
def solution(numbers):
    
    grid = {
        "1":(0,0), "2":(0,1), "3":(0,2), 
        "4":(1,0), "5":(1,1), "6":(1,2),
        "7":(2,0), "8":(2,1), "9":(2,2),
        "*":(3,0), "0":(3,1), "#":(3,2)
    }
    
    n = len(numbers)
    
    def reorder(i,j):
        return tuple(sorted([i,j]))
    
    def calc_dis(n1, n2):
        i, j = grid[n1]
        k, l = grid[n2]
        if i == k and j == l:
            return 1
        if i == k:
            return 2 * abs(j-l)
        elif j == l:
            return 2 * abs(i-k)
        return 3 * min(abs(i-k), abs(j-l)) + 2 * (max(abs(i-k), abs(j-l)) - min(abs(i-k), abs(j-l)))
    
    dp = [defaultdict(lambda : sys.maxsize) for _ in range(n+1)]
    dp[0][("4", "6")] = 0
    
    for i in range(n):
        next_num = numbers[i]
        for j in dp[i].keys():
            if j[0] == next_num or j[1] == next_num:
                if dp[i+1][j] > dp[i][j] + 1:
                    dp[i+1][j] = dp[i][j] + 1
            else:
                j1_dis = calc_dis(next_num, j[1])
                j0_dis = calc_dis(next_num, j[0])
                j0 = reorder(next_num, j[0])
                j1 = reorder(next_num, j[1])
                if dp[i+1][j0] > dp[i][j] + j1_dis:
                    dp[i+1][j0] = dp[i][j] + j1_dis
                if dp[i+1][j1] > dp[i][j] + j0_dis:
                    dp[i+1][j1] = dp[i][j] + j0_dis
    
    return min(dp[-1].values())
                    
    