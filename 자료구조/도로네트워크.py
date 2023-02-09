import sys
from collections import deque

Log = 21


def smaller(i, j):
    if i < j:
        return i
    return j


def bigger(i, j):
    if i < j:
        return j
    return i


def set_parent():
    q = deque([(1, 0)])
    visited[1] = 1
    depth[1] = 0
    while q:
        i, d = q.popleft()
        for j, v in graph[i]:
            if visited[j] == 0:
                q.append((j, d+1))
                depth[j] = d+1
                visited[j] = 1
                Parent[j][0][0] = i
                Parent[j][0][1] = v
                Parent[j][0][2] = v

    for i in range(1, Log):
        for j in range(1, N + 1):
            Parent[j][i][0] = Parent[Parent[j][i - 1][0]][i - 1][0]
            Parent[j][i][1] = smaller(Parent[j][i - 1][1], Parent[Parent[j][i - 1][0]][i - 1][1])
            Parent[j][i][2] = bigger(Parent[j][i - 1][2], Parent[Parent[j][i - 1][0]][i - 1][2])


def LCA(D, E):
    result = [sys.maxsize, 0]

    if depth[D] != depth[E]:
        # E 높이를 더 낮게, 즉 높이 크기가 더 크게 변경하기
        if depth[D] > depth[E]:
            D, E = E, D
        for i in range(Log - 1, -1, -1):
            if depth[E] - depth[D] >= (1 << i):
                result[0] = smaller(result[0], Parent[E][i][1])
                result[1] = bigger(result[1], Parent[E][i][2])
                E = Parent[E][i][0]

    if D == E:
        return result

    for i in range(Log - 1, -1, -1):
        if Parent[E][i][0] != Parent[D][i][0]:
            result[0] = smaller(result[0], Parent[E][i][1])
            result[0] = smaller(result[0], Parent[D][i][1])
            result[1] = bigger(result[1], Parent[E][i][2])
            result[1] = bigger(result[1], Parent[D][i][2])
            E = Parent[E][i][0]
            D = Parent[D][i][0]

    result[0] = smaller(result[0], Parent[E][0][1])
    result[0] = smaller(result[0], Parent[D][0][1])
    result[1] = bigger(result[1], Parent[E][0][2])
    result[1] = bigger(result[1], Parent[D][0][2])
    return result


N = int(sys.stdin.readline())

graph = [[] for _ in range(N + 1)]
Parent = [[[0, sys.maxsize, 0] for _ in range(Log)] for _ in range(N + 1)]
depth = [0] * (N + 1)
visited = [0] * (N + 1)

for _ in range(N - 1):
    A, B, C = map(int, sys.stdin.readline().split())
    graph[A].append([B, C])
    graph[B].append([A, C])

set_parent()

K = int(sys.stdin.readline())
for _ in range(K):
    D, E = map(int, sys.stdin.readline().split())
    short, long = LCA(D, E)
    sys.stdout.write(str(short))
    sys.stdout.write(" ")
    sys.stdout.write(str(long))
    sys.stdout.write("\n")
