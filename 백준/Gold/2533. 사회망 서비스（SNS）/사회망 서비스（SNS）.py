import sys
sys.setrecursionlimit(10**9)

n = int(sys.stdin.readline())
graph = [[] for _ in range(n+1)]

for _ in range(n-1):
    a,b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    graph[b].append(a)
dp = [[0, 0] for _ in range(n+1)]
visited = [0 for _ in range(n+1)]


def dfs(cur):
    visited[cur] = 1
    dp[cur][0] = 1
    for i in graph[cur]:
        if visited[i] == 0:
            dfs(i)
            dp[cur][0] += min(dp[i][0], dp[i][1])
            dp[cur][1] += dp[i][0]
            
            
dfs(1)
print(min(dp[1][0], dp[1][1]))

