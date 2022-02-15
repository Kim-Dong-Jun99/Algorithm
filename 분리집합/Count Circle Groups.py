import sys

tc = int(sys.stdin.readline())


def find(x, parent):
    if x == parent[x]:
        return parent[x]
    else:
        parent[x] = find(parent[x], parent)
        return parent[x]


def union(x, y, parent):
    rx = find(x, parent)
    ry = find(y, parent)

    if rx != ry:
        parent[rx] = ry


def samegroup(x1, y1, r1, x2, y2, r2):
    if (x1 - x2) ** 2 + (y1 - y2) ** 2 <= (r1 + r2) ** 2:
        return True
    return False


for _ in range(tc):
    n = int(sys.stdin.readline())

    circles = [[0, 0, 0]]
    parent = [i for i in range(n + 1)]
    for __ in range(n):
        circles.append(list(map(int, sys.stdin.readline().split())))

    for i in range(1, n):
        for j in range(i + 1, n + 1):
            if samegroup(circles[i][0], circles[i][1], circles[i][2], circles[j][0], circles[j][1], circles[j][2]):
                union(i, j, parent)

    table = {}
    count = 0
    for i in range(1, n + 1):
        temp = find(parent[i], parent)
        if table.get(temp, -1) == -1:
            count += 1
            table[temp] = 1

    print(count)