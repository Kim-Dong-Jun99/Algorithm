n = input()
m = input()
dp = [[0 for j in range(len(m)+1)] for i in range(len(n)+1)]
for i in range(1,len(n)+1):
    for j in range(1,len(m)+1):
        if m[j-1] == n[i-1]:
            dp[i][j] = dp[i-1][j-1] +1
        else:
            dp[i][j] = max(dp[i-1][j],dp[i][j-1])
print(dp[len(n)][len(m)])