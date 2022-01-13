import sys
n = int(sys.stdin.readline())
stair = [int(sys.stdin.readline()) for i in range(n)]
dp = [[] for i in range(n)]
dp[0].append([1, stair[0]])
if n > 1:
    temp = stair[0] + stair[1]
    dp[1].append([1, temp])
    dp[1].append([2, stair[1]])
for i in range(2, n):
    for j in dp[i - 1]:
        if j[0] != 1:
            temp = j[1] + stair[i]
            dp[i].append([1, temp])
    tempMax = None
    for j in dp[i - 2]:
        if tempMax == None:
            tempMax = j[1]
        elif tempMax < j[1]:
            tempMax = j[1]
    tempMax += stair[i]
    dp[i].append([2, tempMax])
maxSum = None
for i in dp[n - 1]:
    if maxSum == None:
        maxSum = i[1]
    elif i[1] > maxSum:
        maxSum = i[1]
    # print(dp)
print(maxSum)