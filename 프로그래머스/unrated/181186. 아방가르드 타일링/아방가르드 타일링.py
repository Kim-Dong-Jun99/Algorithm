def solution(n):
    dp = [1, 1, 3, 10, 23, 62]
    if n <= 5:  return dp[n]
    four = [2, 2, 0]
    five = [2, 0, 0]
    six = [0, 0, 0]
    for i in range(6, n+1):
        cur = dp[-1] + 2*dp[-2] + 5*dp[-3]
        idx = (i-2)%3
        five[idx] += dp[1] << 1
        cur += five[idx]
        idx = (i-1)%3
        four[idx] += dp[2] << 1
        cur += four[idx]
        idx = i%3
        six[idx] += dp[0] << 2
        cur += six[idx]
        dp = dp[1:] + [cur]
    return dp[-1] % 1_000_000_007