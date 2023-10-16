import sys
from collections import deque

Log = 21


def set_parent():
    q = deque([(1, 0)])
    visited[1] = 1
    depth[1] = 0
    while q:
        i, d = q.popleft()
        for j, v in graph[i]:
            if visited[j] == 0:
                visited[j] = 1
                q.append((j, d + 1))
                depth[j] = d + 1
                Parent[j][0][0] = i
                Parent[j][0][1] = v

    for i in range(1, Log):
        for j in range(1, N + 1):
            Parent[j][i][0] = Parent[Parent[j][i - 1][0]][i - 1][0]
            Parent[j][i][1] = Parent[j][i - 1][1] + Parent[Parent[j][i - 1][0]][i - 1][1]


def LCA_1(D, E):
    result = 0
    if depth[D] != depth[E]:
        if depth[D] > depth[E]:
            D, E = E, D

        for i in range(Log - 1, -1, -1):
            if depth[E] - depth[D] >= (1 << i):
                result += Parent[E][i][1]
                E = Parent[E][i][0]

    if D == E:
        return result

    for i in range(Log - 1, -1, -1):
        if Parent[E][i][0] != Parent[D][i][0]:
            result += Parent[E][i][1]
            result += Parent[D][i][1]
            D = Parent[D][i][0]
            E = Parent[E][i][0]
    result += Parent[E][0][1]
    result += Parent[D][0][1]
    return result


def LCA_2(U, V, K):
    i_u = U
    i_v = V
    if depth[U] != depth[V]:
        if depth[U] > depth[V]:
            U, V = V, U

        for i in range(Log - 1, -1, -1):
            if depth[V] - depth[U] >= (1 << i):
                V = Parent[V][i][0]
    if U == V:
        lca = U
    else:
        for i in range(Log - 1, -1, -1):
            if Parent[V][i][0] != Parent[U][i][0]:
                U = Parent[U][i][0]
                V = Parent[V][i][0]
        lca = Parent[V][0][0]
    depth_diff = depth[i_u] - depth[lca] + 1
    if K <= depth_diff:
        target = depth[i_u] - K + 1
        for i in range(Log - 1, -1, -1):
            if depth[i_u] - target >= (1 << i):
                i_u = Parent[i_u][i][0]
        return i_u
    else:
        K = (depth[i_v] - depth[lca]) - (K - depth_diff)
        target = depth[i_v] - K
        for i in range(Log - 1, -1, -1):
            if depth[i_v] - target >= (1 << i):
                i_v = Parent[i_v][i][0]
        return i_v


N = int(sys.stdin.readline())

graph = [[] for _ in range(N + 1)]
visited = [0] * (N + 1)
depth = [0] * (N + 1)
Parent = [[[0, 0] for _ in range(Log)] for _ in range(N + 1)]

for _ in range(N - 1):
    u, v, w = map(int, sys.stdin.readline().split())
    graph[u].append([v, w])
    graph[v].append([u, w])

set_parent()

M = int(sys.stdin.readline())

for _ in range(M):
    query = list(map(int, sys.stdin.readline().split()))
    if query[0] == 1:
        sys.stdout.write(str(LCA_1(query[1], query[2])) + "\n")
    else:
        sys.stdout.write(str(LCA_2(query[1], query[2], query[3])) + "\n")
