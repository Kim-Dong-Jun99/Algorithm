import sys
n = int(sys.stdin.readline())
rgb = [list(map(int,sys.stdin.readline().split())) for i in range(n)]
dp = [[]for i in range(n)]
for i in range(n):
    if i == 0:
        dp[i].append(rgb[i][0])
        dp[i].append(rgb[i][1])
        dp[i].append(rgb[i][2])
    else:
        for j in range(3):
            temp = dp[i-1][0:j]+dp[i-1][j+1:]
            dp[i].append(rgb[i][j]+min(temp))
print(min(dp[n-1]))