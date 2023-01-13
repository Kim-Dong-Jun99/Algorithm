import sys


def smaller(i, j):
    if i < j:
        return i
    return j


n = int(sys.stdin.readline())

matrix = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
max_value = 2 ** 31
dp = [[max_value] * n for _ in range(n)]

for i in range(1, n):
    for j in range(n):  # 0,1 1,2 2,3 3,4 0,2 1,3 이렇게 바껴야됌
        if i + j < n:
            if i == 1:
                dp[j][i + j] = matrix[j][0] * matrix[j][1] * matrix[i + j][1]
            else:
                for k in range(i):
                    if k == 0:
                        dp[j][i + j] = smaller(dp[j][i + j],
                                            dp[k + 1][i] + matrix[0][0] * matrix[0][1] * matrix[i][1])
                    elif k == i - 1:
                        dp[j][i + j] = smaller(dp[j][i + j],
                                            dp[0][k] + matrix[0][0] * matrix[k][1] * matrix[i][1])
                    else:
                        dp[j][i + j] = smaller(dp[j][i + j],
                                            dp[0][k] + dp[k + 1][i] + matrix[0][0] * matrix[k][1] * matrix[i][1])

print(dp[0][n - 1])
