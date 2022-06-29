import sys
N = int(sys.stdin.readline())
p = [0] + list(map(int, sys.stdin.readline().split()))
dp = [0 for _ in range(N+1)]


for i in range(1,N+1):
    for k in range(1,i+1):
        dp[i] = max(dp[i], dp[i-k] + p[k])
print(dp[-1])
