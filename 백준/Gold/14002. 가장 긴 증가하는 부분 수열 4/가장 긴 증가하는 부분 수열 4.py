import sys

n = int(sys.stdin.readline())
ns = list(map(int, sys.stdin.readline().split()))

dp = [[] for _ in range(n)]
max_len = []
for i in range(n):
    dp[i].append(ns[i])
    if len(dp[i]) > len(max_len):
        max_len = dp[i].copy()
    for j in range(i + 1, n):
        if ns[j] > ns[i] and len(dp[i]) > len(dp[j]):
            dp[j] = dp[i].copy()
    # print(dp)
    # print(max_len)

print(len(max_len))
for i in max_len:
    print("%d"%i, end=' ')