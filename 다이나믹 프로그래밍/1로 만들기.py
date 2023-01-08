import sys

X = int(sys.stdin.readline())


def getSmaller(i, j):
    if i < j:
        return i
    return j


dp = [1000000 for _ in range(X + 1)]
dp[1] = 0
for i in range(1, X + 1):
    if i + 1 <= X:
        dp[i + 1] = getSmaller(dp[i] + 1, dp[i + 1])
    if i * 2 <= X:
        dp[i * 2] = getSmaller(dp[i] + 1, dp[i * 2])
    if i * 3 <= X:
        dp[i * 3] = getSmaller(dp[i] + 1, dp[i * 3])

print(dp[X])
