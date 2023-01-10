import sys

n = int(sys.stdin.readline())

ns = list(map(int, sys.stdin.readline().split()))

dp = [0] * n



def bigger(i, j):
    if i > j:
        return i
    return j


for i in range(n):
    dp[i] += ns[i]
    for j in range(i + 1, n):
        if ns[j] > ns[i]:
            dp[j] = bigger(dp[j], dp[i])

print(max(dp))
