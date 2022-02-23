import sys

n = int(sys.stdin.readline())
ns = [0] + list(map(int, sys.stdin.readline().split()))
graph = [[] for _ in range(n + 1)]
for _ in range(n - 1):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    graph[b].append(a)

dp = [[0, 0] for _ in range(n + 1)]
visited = [0 for _ in range(n + 1)]


# 0 분리집합에 들어감
def dfs(cur):
    visited[cur] = 1
    dp[cur][0] = ns[cur]
    for i in graph[cur]:
        if visited[i] == 0:
            dfs(i)
            dp[cur][1] += max(dp[i][0], dp[i][1])
            dp[cur][0] += dp[i][1]


dfs(1)
print(max(dp[1]))

btv = [0 for _ in range(n + 1)]
independent = [1 for _ in range(n + 1)]


# 1 이면 소속 안되있

def bt(cur, p):
    btv[cur] = 1
    if independent[p] == 1:
        if dp[cur][0] > dp[cur][1]:
            independent[cur] = 0
        for i in graph[cur]:
            if btv[i] == 0:
                bt(i, cur)
    else:
        for i in graph[cur]:
            if btv[i] == 0:
                bt(i, cur)


bt(1,0)
# print(independent)
for i in range(1, n+1):
    if independent[i] == 0:
        print(i, end = ' ')

