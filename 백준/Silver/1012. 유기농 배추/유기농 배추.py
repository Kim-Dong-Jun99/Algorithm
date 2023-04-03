import sys
def ps(graph,i,j,m,n):
    result = []
    if i-1 > -1:
        if graph[i-1][j] == 1:
            result.append([i-1,j])
    if i+1 < m:
        if graph[i+1][j] == 1:
            result.append([i+1,j])
    if j-1 > -1:
        if graph[i][j-1] == 1:
            result.append([i,j-1])
    if j+1 < n:
        if graph[i][j+1] == 1:
            result.append([i,j+1])
    return result
testcase = int(sys.stdin.readline())
for tc in range(testcase):
    m,n,k = map(int,sys.stdin.readline().split())
    points = [list(map(int,sys.stdin.readline().split())) for i in range(k)]
    graph = [[0 for i in range(n)] for j in range(m)]
    for i in points:
        graph[i[0]][i[1]] = 1
    visited = []
    worms = 0

    for i in range(m):
        for j in range(n):
            if graph[i][j] == 1 and ([i,j] not in visited):
                nextV = [[i,j]]
                worms += 1
                visited.append([i,j])
                while nextV:
                    temp = []
                    for k in nextV:
                        for l in ps(graph,k[0],k[1],m,n):
                            if (l not in visited):
                                visited.append(l)
                                temp.append(l)
                    nextV = temp
    print(worms)