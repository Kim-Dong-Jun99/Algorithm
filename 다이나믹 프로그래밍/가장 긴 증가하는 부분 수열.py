import sys
n = int(sys.stdin.readline())
ns = list(map(int,sys.stdin.readline().split()))
dp = [1 for i in range(n)]
for i in range(n):
    for j in range(i):
        if ns[i] > ns[j]:
            dp[i] = max(dp[i],dp[j]+1)
print(max(dp))