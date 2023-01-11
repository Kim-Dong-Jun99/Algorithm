import sys

N, M = map(int, sys.stdin.readline().split())

chart = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
dp = [[0] * N for _ in range(N)]
dp[0][0] = chart[0][0]
for i in range(N):
    if i == 0:
        for j in range(1, N):
            dp[i][j] = dp[i][j - 1] + chart[i][j]
    else:
        for j in range(N):
            if j == 0:
                dp[i][j] = dp[i - 1][j] + chart[i][j]
            else:
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + chart[i][j] - dp[i - 1][j - 1]

for _ in range(M):
    x1, y1, x2, y2 = map(int, sys.stdin.readline().split())
    x1, y1, x2, y2 = x1 - 1, y1 - 1, x2 - 1, y2 - 1
    if (x1 == x2 and y1 == y2):
        print(chart[x1][y1])
    else:
        if x1 != 0:
            if y1 != 0:
                print(dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1])
            else:
                print(dp[x2][y2] - dp[x1 - 1][y2])
        else:
            if y1 != 0:
                print(dp[x2][y2] - dp[x2][y1 - 1])
            else:
                print(dp[x2][y2])
