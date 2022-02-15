import sys

sys.setrecursionlimit(10 ** 6)
v, e = map(int, sys.stdin.readline().split())
backgraph = [[] for _ in range(v + 1)]
graph = [[] for _ in range(v + 1)]
for _ in range(e):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    backgraph[b].append(a)


def dfs(cur, visited, stack):
    visited[cur] = 1
    for i in graph[cur]:
        if visited[i] == 0:
            dfs(i, visited, stack)
    stack.append(cur)


def reversedfs(cur, visited, stack):
    visited[cur] = 1
    stack.append(cur)
    for i in backgraph[cur]:
        if visited[i] == 0:
            reversedfs(i, visited, stack)


stack = []
visited = [0] * (v + 1)

for i in range(1, v + 1):
    if visited[i] == 0:
        dfs(i, visited, stack)

visited = [0] * (v + 1)
result = []
while stack:
    ssc = []
    node = stack.pop()
    if visited[node] == 0:
        reversedfs(node, visited, ssc)
        result.append(sorted(ssc))

print(len(result))
results = sorted(result)
for i in results:
    print(*i, -1)


