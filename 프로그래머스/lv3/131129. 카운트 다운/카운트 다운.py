import sys
def solution(target):
    dp = [[sys.maxsize,0] for _ in range(target+1)]
    dp[0][0] = 0
    
    for i in range(target):
        for j in range(1,21):
            if i + j <= target:
                if dp[i+j][0] > dp[i][0] + 1:
                    dp[i+j][0] = dp[i][0] + 1
                    dp[i+j][1] = dp[i][1] + 1
                elif dp[i+j][0] == dp[i][0] + 1:
                    if dp[i+j][1] < dp[i][1] + 1:
                        dp[i+j][1] = dp[i][1] + 1
            for k in range(2,4):
                diff = k*j
                index = i + diff
                if index <= target:
                    if dp[index][0] > dp[i][0] + 1:
                        dp[index][0] = dp[i][0]+1
                        dp[index][1] = dp[i][1]
                    elif dp[index][0] == dp[i][0] + 1:
                        if dp[index][1] < dp[i][1]:
                            dp[index][1] = dp[i][1]
        if i + 50 <= target:
            if dp[i+50][0] > dp[i][0] + 1:
                dp[i + 50][0] = dp[i][0] + 1
                dp[i+50][1] = dp[i][1]+1
            elif dp[i+50][0] == dp[i][0] + 1:
                if dp[i+50][1] < dp[i][1] + 1:
                    dp[i+50][1] = dp[i][1] + 1
                    
    
    return dp[-1]

