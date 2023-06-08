import sys
from collections import deque
input = sys.stdin.readline


n,m = map(int,input().split())
graph = [list(map(int,input().split())) for _ in range(n)]

dx = [0,0,1,-1]
dy = [1,-1,0,0]


def bfs():
    q = deque()
    q.append([0,0])
    visited[0][0] = 1
    while q:
        x,y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0<=nx<n and 0<=ny<m and visited[nx][ny] == 0:
                if graph[nx][ny] >= 1:
                    graph[nx][ny] += 1
                else:
                    visited[nx][ny] = 1
                    q.append([nx,ny])
time = 0
while 1:
    visited = [[0]*m for _ in range(n)]
    bfs()
    flag = 0
    for i in range(n):
        for j in range(m):
            if graph[i][j] >= 3:
                graph[i][j] = 0
                flag = 1
            elif graph[i][j] == 2:
                graph[i][j] = 1
    if flag == 1:
        time += 1
    else:
        break

print(time)