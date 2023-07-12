def solution(n):
    dp = [0] * (n+1)
    dp[0] = 1
    mod = 1234567
    for i in range(n):
        if i + 1 <= n :
            dp[i+1] += dp[i] % mod
        if i + 2 <= n:
            dp[i+2] += dp[i] % mod
    return dp[-1] % mod