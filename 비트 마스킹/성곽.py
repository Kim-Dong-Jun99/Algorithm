import sys

m, n = map(int, sys.stdin.readline().split())
walls = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]


def cango(i, j):
    result = []
    if walls[i][j] & (1 << 0) != (1  << 0):
        result.append([i, j-1])
    if walls[i][j] & (1 << 1) != (1 << 1):
        result.append([i-1, j])
    if walls[i][j] & (1 << 2) != (1 << 2):
        result.append([i, j+1])
    if walls[i][j] & (1 << 3) != (1 << 3):
        result.append([i+1, j])
    return result


def check(i, j):
    result = []
    if i-1 > -1:
        result.append([i-1, j])
    if i+1 < n:
        result.append([i+1, j])
    if j-1 > -1:
        result.append([i, j-1])
    if j+1 < m:
        result.append([i,j+1])
    return result


table = {}
visited = [[0 for _ in range(m)] for _ in range(n)]
roomcount = 0
maxsize = 0
for i in range(n):
    for j in range(m):
        if visited[i][j] == 0:
            visited[i][j] = 1
            update = [[i,j]]
            nextV = [[i, j]]
            sizecount = 1
            roomcount += 1
            while nextV:
                temp = []
                for k in nextV:
                    for l in cango(k[0], k[1]):
                        if visited[l[0]][l[1]] == 0:
                            visited[l[0]][l[1]] = 1
                            update.append(l)
                            sizecount += 1
                            temp.append(l)
                nextV = temp
            if sizecount > maxsize:
                maxsize = sizecount
            for k in update:
                # roomsize[k[0]][k[1]].append([[i, j], sizecount])
                table[(k[0], k[1])] = [[i,j],sizecount]

maxsum = 0
for i in range(n):
    for j in range(m):
        for k in check(i, j):
            if table[(i,j)][0] != table[(k[0], k[1])][0]:
                tempsum = table[(i,j)][1] + table[(k[0], k[1])][1]
                if tempsum > maxsum:
                    maxsum = tempsum

print(roomcount)
print(maxsize)
print(maxsum)