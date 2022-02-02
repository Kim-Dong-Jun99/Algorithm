import sys
n = int(sys.stdin.readline())
ns = [0]
for _ in range(n):
    ns.append(int(sys.stdin.readline()))
dp = [0]
dp.append(ns[1])
if n > 1:
    dp.append(ns[1]+ns[2])
for i in range(3,n+1):
    dp.append(max(dp[i-1],dp[i-3]+ns[i-1]+ns[i],dp[i-2]+ns[i]))
print(dp[n])