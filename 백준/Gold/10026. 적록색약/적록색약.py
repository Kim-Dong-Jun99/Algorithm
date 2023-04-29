import sys

n = int(sys.stdin.readline())
painting = [sys.stdin.readline().strip() for _ in range(n)]
visitedB = [[0 for _ in range(n)] for _ in range(n)]
visitedN = [[0 for _ in range(n)] for _ in range(n)]
area = 0
normal = 0

def cangoN(i,j,c):
    result = []
    if i - 1 > -1 and painting[i - 1][j] == c:
        result.append([i - 1, j])
    if i + 1 < n and painting[i + 1][j] == c:
        result.append([i + 1, j])
    if j - 1 > -1 and painting[i][j - 1] == c:
        result.append([i, j - 1])
    if j + 1 < n and painting[i][j + 1] == c:
        result.append([i, j + 1])
    return result

def cango(i, j, c):
    result = []
    if c == 'B':
        if i - 1 > -1 and painting[i - 1][j] == 'B':
            result.append([i - 1, j])
        if i + 1 < n and painting[i + 1][j] == 'B':
            result.append([i + 1, j])
        if j - 1 > -1 and painting[i][j - 1] == 'B':
            result.append([i, j - 1])
        if j + 1 < n and painting[i][j + 1] == 'B':
            result.append([i, j + 1])
    else:
        if i - 1 > -1 and painting[i - 1][j] != 'B':
            result.append([i - 1, j])
        if i + 1 < n and painting[i + 1][j] != 'B':
            result.append([i + 1, j])
        if j - 1 > -1 and painting[i][j - 1] != 'B':
            result.append([i, j - 1])
        if j + 1 < n and painting[i][j + 1] != 'B':
            result.append([i, j + 1])
    return result


for i in range(n):
    for j in range(n):
        if visitedB[i][j] == 0:
            nextV = [[i, j]]
            area += 1
            while nextV:
                temp = []
                for k in nextV:
                    for l in cango(k[0], k[1], painting[k[0]][k[1]]):
                        if visitedB[l[0]][l[1]] == 0:
                            temp.append(l)
                            visitedB[l[0]][l[1]] = 1
                nextV = temp
        if visitedN[i][j] == 0:
            nextV = [[i,j]]
            normal += 1
            while nextV:
                temp = []
                for k in nextV:
                    for l in cangoN(k[0],k[1],painting[i][j]):
                        if visitedN[l[0]][l[1]] == 0:
                            temp.append(l)
                            visitedN[l[0]][l[1]] = 1
                nextV = temp
print('%d %d' % (normal, area))