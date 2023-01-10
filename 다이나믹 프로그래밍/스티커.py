import sys

def bigger(i,j):
    if i > j:
        return i
    return j

T = int(sys.stdin.readline())
for _ in range(T):
    n = int(sys.stdin.readline())
    stickers = [list(map(int, sys.stdin.readline().split())) for _ in range(2)]
    dp = [[0] * n for _ in range(2)]
    dp[0][0] = stickers[0][0]
    dp[1][0] = stickers[1][0]
    for i in range(1,n):
        if i == 1:
            dp[0][i] = dp[1][i-1] + stickers[0][i]
            dp[1][i] = dp[0][i-1] + stickers[1][i]
        else:
            dp[0][i] = bigger(dp[1][i-1], bigger(dp[0][i-2], dp[1][i-2]) ) + stickers[0][i]
            dp[1][i] = bigger(dp[0][i - 1], bigger(dp[0][i - 2], dp[1][i - 2])) + stickers[1][i]
    print(bigger(dp[0][n-1], dp[1][n-1]))