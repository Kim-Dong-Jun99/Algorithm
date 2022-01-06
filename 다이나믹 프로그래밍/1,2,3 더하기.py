testcase = int(input())
for i in range(testcase):
    n = int(input())
    dp = [[] for i in range(n + 1)]

    for i in range(1, n + 1):
        if i - 1 > -1:
            if dp[i - 1] == []:
                dp[i].append([1])
            else:
                for j in dp[i - 1]:
                    temp = j[:]
                    temp.append(1)
                    dp[i].append(temp)
        if i - 2 > -1:
            if dp[i - 2] == []:
                dp[i].append([2])
            else:
                for j in dp[i - 2]:
                    temp = j[:]
                    temp.append(2)
                    dp[i].append(temp)
        if i - 3 > -1:
            if dp[i - 3] == []:
                dp[i].append([3])
            else:
                for j in dp[i - 3]:
                    temp = j[:]
                    temp.append(3)
                    dp[i].append(temp)

    print(len(dp[n]))