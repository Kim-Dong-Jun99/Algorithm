import sys

def bigger(i,j):
    if i > j:
        return i
    return j

n = int(sys.stdin.readline())
ns = list(map(int, sys.stdin.readline().split()))

dp = [1] * n

for i in range(n):
    for j in range(i + 1, n):
        if ns[i] > ns[j]:
            dp[j] = bigger(dp[i]+1, dp[j])

print(max(dp))