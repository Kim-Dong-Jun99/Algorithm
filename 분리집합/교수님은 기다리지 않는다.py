import sys

sys.setrecursionlimit(1000000)


def find(x):
    if parent[x][0] == x:
        return x
    find_parent = find(parent[x][0])
    if find_parent != parent[x][0]:
        parent[x][1] += parent[parent[x][0]][1]
        parent[x][0] = find_parent

    return parent[x][0]


def union(x, y):
    px = find(x)
    py = find(y)

    if px != py:
        parent[px][0] = py


while True:
    N, M = map(int, sys.stdin.readline().split())

    if N == 0 and M == 0:
        break
    parent = [[i, 0] for i in range(N + 1)]
    for __ in range(M):
        query = list(sys.stdin.readline().split())
        q1 = int(query[1])
        q2 = int(query[2])
        if query[0] == '!':
            q3 = int(query[3])
            parent[q1][1] = q3
            union(q1, q2)
        else:
            if find(q1) != find(q2):
                sys.stdout.write("UNKNOWN\n")
            else:
                # print(1)
                sys.stdout.write(str(-(parent[q2][1] - parent[q1][1])) + "\n")
