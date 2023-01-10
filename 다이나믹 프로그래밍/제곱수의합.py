import sys

def smaller(i,j):
    if i < j:
        return i
    return j

n = int(sys.stdin.readline())
index = 1
dp = [100000]*(n+1)
dp[0] = 0
dp[1] = 1

for i in range(2,n+1):
    if i == (index+1)**2:
        index += 1
    for j in range(index,0,-1):
        dp[i] = smaller(dp[i - j**2] + 1, dp[i])

print(dp[n])