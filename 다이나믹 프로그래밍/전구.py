import sys

n, k = map(int,sys.stdin.readline().split())
ns = [0]+list(map(int, sys.stdin.readline().split()))

dp = [[-1 for _ in range(201)] for _ in range(201)]


def sol(l, r):
    if l == r:
        return 0
    if dp[l][r] != -1:
        return dp[l][r]
    dp[l][r] = sys.maxsize
    for i in range(1, r):
        temp = 0
        if ns[l] != ns[i+1]:
            temp += 1
        dp[l][r] = min(dp[l][r], sol(l,i) + sol(i+1,r) + temp)
    return dp[l][r]

print(sol(1, n))

