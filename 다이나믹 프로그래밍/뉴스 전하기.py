import sys

n = int(sys.stdin.readline())
graph = [[] for _ in range(n)]
ns = list(map(int, sys.stdin.readline().split()))
for i in range(1, n):
    graph[ns[i]].append(i)
    graph[i].append(ns[i])

dp = [0 for _ in range(n)]
visited = [0 for _ in range(n)]


def dfs(cur):
    visited[cur] = 1
    # tempmax = -1
    # maxcount = 0
    # tempmin = sys.maxsize
    temp = []
    count = 0
    for i in graph[cur]:
        if visited[i] != 0:
            continue
        count += 1
        dfs(i)
        temp.append(dp[i])
        # if dp[i] > tempmax:
        #     tempmax = dp[i]
        #     maxcount = 1
        # elif dp[i] == tempmax:
        #     maxcount += 1
        # if dp[i] < tempmin:
        #     tempmin = dp[i]
    # if tempmax == -1:
    #     dp[cur] = 0
    # else:
    #     if abs(tempmax - tempmin) < count:
    #         # dp[cur] = tempmax + 1
    #         dp[cur] = count + tempmin
    #     else:
    #         # dp[cur] = count + tempmin
    #         dp[cur] = tempmax + maxcount
    if count > 0:
        temp.sort(reverse=True)
        for j in range(count):
            if dp[cur] < temp[j] + j+1:
                dp[cur] = temp[j] + j+1

dfs(0)
# for i in range(n):
#     print('%d %d' % (i, dp[i]))
print(dp[0])
