import sys

dp = [[[1] * 21 for _ in range(21)] for _ in range(21)]

for i in range(1, 21):
    for j in range(1, 21):
        for k in range(1, 21):
            if i < j and j < k:
                dp[i][j][k] = dp[i][j][k-1] + dp[i][j-1][k-1] - dp[i][j-1][k]
            else:
                dp[i][j][k] = dp[i-1][j][k] + dp[i-1][j-1][k] + dp[i-1][j][k-1] - dp[i-1][j-1][k-1]
while True:
    a, b, c = map(int, sys.stdin.readline().split())
    if a == -1 and b == -1 and c == -1:
        break
    if a <= 0 or b <= 0 or c <= 0:
        result = 1
    elif a > 20 or b > 20 or c > 20:
        result = dp[20][20][20]
    else:
        result = dp[a][b][c]
    print("w(%d, %d, %d) = %d" % (a, b, c, result))
