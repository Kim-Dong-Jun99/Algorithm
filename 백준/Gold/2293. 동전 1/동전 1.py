import sys
n,k = map(int,sys.stdin.readline().split())
cand = []
dp = [0 for i in range(k+1)]
for _ in range(n):
    temp = int(sys.stdin.readline())
    if temp <= k:
        cand.append(temp)
dp[0] = 1
for i in cand:
    for j in range(1,k+1):
        if j-i >=0:
            dp[j] += dp[j-i]
print(dp[k])
