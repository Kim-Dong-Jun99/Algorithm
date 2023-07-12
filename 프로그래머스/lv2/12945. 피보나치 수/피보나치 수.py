def solution(n):
    answer = 0
    mod = 1234567
    dp = [0,1]
    while len(dp) <= n:
        dp.append((dp[-1] + dp[-2]) % mod)
    return dp[n]