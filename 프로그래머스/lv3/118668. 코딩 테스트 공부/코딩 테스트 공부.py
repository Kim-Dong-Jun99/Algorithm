import sys
import math
def solution(alp, cop, problems):
    max_alp = 0
    max_cop = 0
    
    for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
        if max_alp < alp_req:
            max_alp = alp_req
        if max_cop < cop_req:
            max_cop = cop_req
    if max_alp <= alp and max_cop <= cop:
        return 0
    if max_alp <= alp and max_cop > cop:
        dp = [sys.maxsize for _ in range(max_cop + 1)]
        dp[cop] = 0
        for i in range(cop, max_cop+1):
            if i + 1 <= max_cop and dp[i+1] > dp[i] + 1:
                dp[i+1] = dp[i] + 1
            for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
                if cop_req <= i:
                    if i + cop_rwd <= max_cop:
                        if dp[i+cop_rwd] > dp[i] + cost:
                            dp[i+cop_rwd] = dp[i] + cost
                    else:
                        if dp[max_cop] > dp[i] + cost:
                            dp[max_cop] = dp[i] + cost
                
                    
        return dp[max_cop]
    if max_alp > alp and max_cop <= cop:
        dp = [sys.maxsize for _ in range(max_alp+1)]
        dp[alp] = 0
        for i in range(alp, max_alp+1):
            if i + 1 <= max_alp and dp[i+1] > dp[i] + 1:
                dp[i+1] = dp[i] + 1
            for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
                if alp_req <= i:
                    if i + alp_rwd <= max_alp:
                        if dp[i+alp_rwd] > dp[i] + cost:
                            dp[i+alp_rwd] = dp[i] + cost
                    else:
                        if dp[max_alp] > dp[i] + cost:
                            dp[max_alp] = dp[i] + cost
        return dp[max_alp]
    dp = [[sys.maxsize for _ in range(max_cop+1)] for _ in range(max_alp+1)]
    dp[alp][cop] = 0
    for i in range(alp, max_alp+1):
        for j in range(cop, max_cop+1):
            if i + 1 <= max_alp and dp[i+1][j] > dp[i][j] + 1:
                dp[i+1][j] = dp[i][j] + 1
            if j + 1 <= max_cop and dp[i][j+1] > dp[i][j] + 1:
                dp[i][j+1] = dp[i][j] + 1
            for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
                if alp_req <= i and cop_req <= j:
                    if i + alp_rwd <= max_alp and j + cop_rwd <= max_cop:
                        if dp[i+alp_rwd][j+cop_rwd] > dp[i][j]+cost:
                            dp[i+alp_rwd][j+cop_rwd] = dp[i][j]+cost
                    elif i+alp_rwd > max_alp and j + cop_rwd < max_cop:
                        if dp[max_alp][j+cop_rwd] > dp[i][j]+cost:
                            dp[max_alp][j+cop_rwd] = dp[i][j]+cost
                    elif i+alp_rwd < max_alp and j+ cop_rwd > max_cop:
                        if dp[i+alp_rwd][max_cop] > dp[i][j] + cost:
                            dp[i+alp_rwd][max_cop] = dp[i][j]+cost
                    else:
                        if dp[max_alp][max_cop] > dp[i][j] + cost:
                            dp[max_alp][max_cop] = dp[i][j] + cost
                                
                    
    return dp[max_alp][max_cop]
   
