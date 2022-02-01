import sys
tc = int(sys.stdin.readline())
dp = [0 for _ in range(101)]
dp[1] = 1
dp[2] = 1
dp[3] = 1
for i in range(0,98):
    dp[i+3] = dp[i]+dp[i+1]
for _ in range(tc):
    n = int(sys.stdin.readline())
    print(dp[n])