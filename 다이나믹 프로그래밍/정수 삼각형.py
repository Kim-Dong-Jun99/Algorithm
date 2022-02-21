import sys

n = int(sys.stdin.readline())
tr = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
if n == 1:
    print(tr[0][0])
    sys.exit()
dp = [[0 for _ in range(n)] for _ in range(n)]
dp[0][0] = tr[0][0]
dp[1][0] = tr[0][0] + tr[1][0]
dp[1][1] = tr[0][0] + tr[1][1]
for i in range(2, n):
    for j in range(i + 1):
        if j == i:
            dp[i][j] = tr[i][j] + dp[i-1][j-1]
        elif j == 0:
            dp[i][j] = tr[i][j] + dp[i-1][j]
        else:
            dp[i][j] = tr[i][j] + max(dp[i - 1][j], dp[i - 1][j - 1])

# for i in dp:
#     print(i)
print(max(dp[-1]))