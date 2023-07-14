def solution(triangle):
    dp = [[0 for _ in range(len(triangle[-1]))] for _ in range(len(triangle))]
    if len(triangle) == 1:
        return max(triangle[0])
    for i in range(len(triangle)):
        for j in range(len(triangle[i])):
            dp[i][j] += triangle[i][j]
            if i + 1 < len(triangle):
                if dp[i][j] > dp[i+1][j]:
                    dp[i+1][j] = dp[i][j]
                if dp[i][j] > dp[i+1][j+1]:
                    dp[i+1][j+1] = dp[i][j]
        # print(*dp[i])
                
            
    return max(dp[-1])