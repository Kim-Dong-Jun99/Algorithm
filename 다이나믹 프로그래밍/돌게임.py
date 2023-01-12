import sys

def flip(i):
    if i == 1:
        return 0
    return 1


n = int(sys.stdin.readline())

dp = [0] * (n + 1)

for i in range(n + 1):
    if i + 3 <= n:
        dp[i + 3] = flip(dp[i])
    if i + 1 <= n:
        dp[i + 1] = flip(dp[i])

if dp[n] == 1:
    print("SK")
else:
    print("CY")