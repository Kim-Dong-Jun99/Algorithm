import sys

n = int(sys.stdin.readline())
stair = [int(sys.stdin.readline()) for i in range(n)]
dp = [[] for i in range(n)]
dp[0].append([0])
if n > 1:
    dp[1].append([0, 1])
    dp[1].append([1])
for i in range(2, n):
    for j in dp[i - 1]:
        if len(j) >= 2:
            if j[-1] != i - 1 or j[-2] != i - 2:
                temp = j[:]
                temp.append(i)
                dp[i].append(temp)
        else:
            temp = j[:]
            temp.append(i)
            dp[i].append(temp)
    for j in dp[i - 2]:
        temp = j[:]
        temp.append(i)
        dp[i].append(temp)
maxSum = None
for i in dp[n - 1]:
    tempSum = 0
    for j in i:
        tempSum += stair[j]
    if maxSum == None:
        maxSum = tempSum
    elif tempSum > maxSum:
        maxSum = tempSum

print(maxSum)


