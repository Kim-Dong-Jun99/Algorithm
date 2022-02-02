n = int(input())
dp = [[0 for _ in range(10)] for _ in range(n)]
dp[0] = [0]+[1]*9
for i in range(1,n):
    for j in range(1,9):
        dp[i][j] += dp[i-1][j-1] + dp[i-1][j+1]
    dp[i][0] += dp[i-1][1]
    dp[i][9] += dp[i-1][8]
sum = 0
for i in range(10):
    sum += dp[n-1][i]
print(sum%(10**9))
# print(dp)