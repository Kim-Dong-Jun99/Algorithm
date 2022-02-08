import sys

sys.setrecursionlimit(10 ** 9)
n, m = map(int, sys.stdin.readline().split())
lake = [sys.stdin.readline().strip() for _ in range(n)]
parent = {}


def find(x):
    if x == parent[x]:
        return x
    else:
        parent[x] = find(parent[x])
        return parent[x]


def union(x, y):
    rx = find(x)
    ry = find(y)
    if rx != ry:
        parent[rx] = ry


def findn(i, j):
    result = []
    check = False
    if i - 1 > -1:
        if lake[i - 1][j] == '.' or lake[i - 1][j] == 'L':
            if parent.get((i - 1, j), -1) == -1:
                result.append((i - 1, j))
        else:
            check = True
    if i + 1 < n:
        if lake[i + 1][j] == '.' or lake[i + 1][j] == 'L':
            if parent.get((i + 1, j), -1) == -1:
                result.append((i + 1, j))
        else:
            check = True
    if j - 1 > -1:
        if lake[i][j - 1] == '.' or lake[i][j - 1] == 'L':
            if parent.get((i, j - 1), -1) == -1:
                result.append((i, j - 1))
        else:
            check = True
    if j + 1 < m:
        if lake[i][j + 1] == '.' or lake[i][j + 1] == 'L':
            if parent.get((i, j + 1), -1) == -1:
                result.append((i, j + 1))
        else:
            check = True
    if check:
        wateredges.append((i, j))
    return result


wateredges = []

swan = []
for i in range(n):
    for j in range(m):
        if lake[i][j] == 'L':
            swan.append((i, j))
        if (lake[i][j] == '.' or lake[i][j] == 'L') and parent.get((i, j), -1) == -1:

            parent[(i, j)] = (i, j)
            nextV = [(i, j)]
            while nextV:
                temp = []
                for k in nextV:
                    for l in findn(k[0], k[1]):
                        parent[l] = l
                        union((i, j), l)
                        temp.append(l)

                nextV = temp


def nextmelt(i, j):
    result = []
    if i - 1 > -1:
        result.append((i - 1, j))
    if i + 1 < n:
        result.append((i + 1, j))
    if j - 1 > -1:
        result.append((i, j - 1))
    if j + 1 < m:
        result.append((i, j + 1))
    return result


count = 0
while find(swan[0]) != find(swan[1]) and count < 10:

    tempW = []
    tempI = []

    for i in wateredges:
        for j in nextmelt(i[0], i[1]):
            if parent.get(j, -1) == -1:
                parent[j] = j
                union(i, j)
                tempW.append(j)
                for k in nextmelt(j[0], j[1]):
                    if parent.get(k, -1) != -1:
                        union(j, k)
            else:
                union(i, j)
    wateredges = tempW

    count += 1

print(count)