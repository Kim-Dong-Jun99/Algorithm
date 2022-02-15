import sys

n = int(sys.stdin.readline())
lines = [[0]] + [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
parent = [i for i in range(n + 1)]


def ccw(x1, y1, x2, y2, x3, y3):
    temp = (x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1)

    if temp > 0:
        return 1
    elif temp < 0:
        return -1
    else:
        return 0


def cross(a, b):
    x1, y1, x2, y2 = a[0], a[1], a[2], a[3]
    x3, y3, x4, y4 = b[0], b[1], b[2], b[3]
    result = False
    check = False
    if ccw(x1, y1, x2, y2, x3, y3) * ccw(x1, y1, x2, y2, x4, y4) == 0 and ccw(x3, y3, x4, y4, x1, y1) * ccw(x3, y3, x4,
                                                                                                            y4, x2,
                                                                                                            y2) == 0:
        check = True
        if min(x1, x2) <= max(x3, x4) and min(x3, x4) <= max(x1, x2) and min(y1, y2) <= max(y3, y4) and min(y3,
                                                                                                            y4) <= max(
                y1, y2):
            result = True
    if ccw(x1, y1, x2, y2, x3, y3) * ccw(x1, y1, x2, y2, x4, y4) <= 0 and ccw(x3, y3, x4, y4, x1, y1) * ccw(x3, y3, x4,
                                                                                                            y4, x2,
                                                                                                            y2) <= 0:
        if not check:
            result = True
    return result


def find(x):
    if x == parent[x]:
        return parent[x]
    else:
        parent[x] = find(parent[x])
        return parent[x]


def union(x, y):
    rx = find(x)
    ry = find(y)

    if rx != ry:
        parent[rx] = ry


for i in range(1, n):
    for j in range(i + 1, n + 1):
        if cross(lines[i], lines[j]):
            union(i, j)

table = {}
maxV = 0
for i in range(1, n + 1):
    temp = find(parent[i])
    if table.get(find(parent[i]), -1) == -1:
        table[temp] = 1
    else:
        table[temp] = table[temp] + 1
    if table[temp] > maxV:
        maxV = table[temp]
print(len(table))
print(maxV)