import sys
n,m=map(int,sys.stdin.readline().split())
graph=[]
result = []
start=[]
for i in range(n):
    temp = list(map(int, sys.stdin.readline().split()))
    graph.append(temp)
    result.append([-1]*m)
    for j in range(m):
        if temp[j] == 2:
            start.append([i,j])
        if graph[i][j] == 0:
            result[i][j] = 0
            
def can_go(i,j):
    cango = []
    global n
    global m
    if i-1 >=0:
        cango.append([i-1,j])
    if i + 1 < n:
        cango.append([i+1,j])
    if j-1>=0:
        cango.append([i,j-1])
    if j+1 < m:
        cango.append([i,j+1])
    return cango
result[start[0][0]][start[0][1]] = 0
visited = [[0 for _ in range(m)] for _ in range(n)]
visited[start[0][0]][start[0][1]] = 1
while start:
    temp = []
    for i, j in start:
        for k,l in can_go(i, j):
            if visited[k][l] == 0:
                visited[k][l] = 1
                if graph[k][l] != 0:
                    result[k][l] = result[i][j] + 1
                    temp.append([k,l])
    start = temp
for i in result:
    print(*i)