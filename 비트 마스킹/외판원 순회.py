import sys
n = int(sys.stdin.readline())
graph = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
dp = [[0 for _ in range(n)] for _ in range(2**n+1)]
# dp[1].append(0)

result = sys.maxsize

def dfs(cur, visited):
    print(dp)
    check = True
    for i in range(n):
        if visited & (1 << i) != (1 << i) and graph[cur][i] != 0:
            dp[visited | (1 << i)][i] = dp[visited][cur] + graph[cur][i]
            nvisited = visited | (1 << i)
            check = False
            dfs(i, nvisited)
    if check and visited == 2**n-1 and graph[cur][0] != 0:
        global result
        if result > dp[visited][cur] + graph[cur][0]:
            result = dp[visited][cur] + graph[cur][0]


dfs(0, 1)
# print(dp)
print(result)