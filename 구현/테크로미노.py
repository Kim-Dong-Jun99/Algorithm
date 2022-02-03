import sys

n, m = map(int, sys.stdin.readline().split())

paper = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
visited = [[0 for _ in range(m)] for _ in range(n)]


def cango(i, j, si, sj):
    result = []
    if i - 1 > -1:
        # if i - 1 > si or j > sj:
        result.append([i - 1, j])
    if i + 1 < n:
        # if i + 1 > si or j > sj:
        result.append([i + 1, j])
    if j - 1 > -1:
        # if i > si or j - 1 > sj:
        result.append([i, j - 1])
    if j + 1 < m:
        # if i > si or j + 1 > sj:
        result.append([i, j + 1])
    return result


maxV = 0


def bt(i, j, si, sj, count, value):
    if count == 4:
        global maxV
        if value > maxV:
            maxV = value
    else:
        for k in cango(i, j, si, sj):
            if visited[k[0]][k[1]] == 0:
                visited[k[0]][k[1]] = 1
                bt(k[0], k[1], si, sj, count+1, value+paper[k[0]][k[1]])
                visited[k[0]][k[1]] = 0


def checkS(i, j):
    global maxV
    if (i == 0 or i == n-1) and (j == 0 or j == m-1):
        return
    if i == 0 or i == n-1:
        if i == 0:
            stan = [[1,-1],[1,0],[1,1]]
            temp = paper[i][j]
            for k in stan:
                temp += paper[i+k[0]][j+k[1]]
            # global maxV
            if maxV < temp:
                maxV = temp
        else:
            stan = [[-1, -1], [-1, 0], [-1, 1]]
            temp = paper[i][j]
            for k in stan:
                temp += paper[i + k[0]][j + k[1]]
            # global maxV
            if maxV < temp:
                maxV = temp
    elif j == 0 or j == m-1:
        if j == 0:
            stan = [[-1,1],[0,1],[1,1]]
            temp = paper[i][j]
            for k in stan:
                temp += paper[i + k[0]][j + k[1]]
            # global maxV
            if maxV < temp:
                maxV = temp
        else:
            stan = [[-1, -1], [0, -1], [1, -1]]
            temp = paper[i][j]
            for k in stan:
                temp += paper[i + k[0]][j + k[1]]
            # global maxV
            if maxV < temp:
                maxV = temp
    else:
        stan = [[[-1,-1],[-1,0],[-1,1]],[[-1,1],[0,1],[1,1]],[[1,1],[1,0],[1,-1]],[[-1,-1],[0,-1],[1,-1]]]
        # global maxV
        for k in stan:
            temp = paper[i][j]
            for l in k:
                temp += paper[i+l[0]][j+l[1]]
            if temp > maxV:
                maxV = temp


for i in range(n):
    for j in range(m):
        cur = [i, j]
        visited[i][j] = 1
        # si = i
        # sj = j
        bt(i, j, i, j, 1, paper[i][j])
        visited[i][j] = 0

        checkS(i,j)

print(maxV)


