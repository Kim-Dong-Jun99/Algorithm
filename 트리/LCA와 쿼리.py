import sys
from collections import deque

Log = 21


def set_parent():
    q = deque([(1, 0)])
    depth[1] = 0
    visited[1] = 1
    while q:
        i, d = q.popleft()
        for j in graph[i]:
            if visited[j] == 0:
                visited[j] = 1
                Parent[j][0] = i
                q.append((j, d + 1))
                depth[j] = d + 1

    for i in range(1, Log):
        for j in range(1, N + 1):
            Parent[j][i] = Parent[Parent[j][i - 1]][i - 1]


def lca_2(u, v):
    if depth[u] != depth[v]:
        if depth[u] > depth[v]:
            u, v = v, u

        for i in range(Log - 1, -1, -1):
            if depth[v] - depth[u] >= (1 << i):
                v = Parent[v][i]
    if u == v:
        return u
    for i in range(Log - 1, -1, -1):
        if Parent[u][i] != Parent[v][i]:
            u = Parent[u][i]
            v = Parent[v][i]
    u = Parent[u][0]
    return u


def lca(r, u, v):
    lca = lca_2(u, v)
    lca_r = lca_2(lca, r)
    if lca_r != lca:
        return lca
    else:
        lca_u = lca_2(u, r)
        lca_v = lca_2(v, r)
        if lca_u == lca and lca_v == lca:
            return lca
        elif lca_u == lca and lca_v != lca:
            return lca_v
        elif lca_u != lca and lca_v == lca:
            return lca_u


N = int(sys.stdin.readline())
graph = [[] for _ in range(N + 1)]
visited = [0] * (N + 1)
depth = [0] * (N + 1)
Parent = [[0] * Log for _ in range(N + 1)]

for _ in range(N - 1):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    graph[b].append(a)

set_parent()

M = int(sys.stdin.readline())

for _ in range(M):
    r, u, v = map(int, sys.stdin.readline().split())
    sys.stdout.write(str(lca(r, u, v)) + "\n")
