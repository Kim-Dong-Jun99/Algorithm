from collections import deque
import sys
n,m = map(int,sys.stdin.readline().split())
visited = [[0]*m for _ in range(n)]
wvisited = [[0]*m for _ in range(n)]
lake = []
swan = []
q,qtemp,wq,wqtemp = deque(),deque(),deque(),deque()
for i in range(n):
    r = list(sys.stdin.readline().strip())
    lake.append(r)
    for j,k in enumerate(r):
        if lake[i][j] == 'L':
            swan.extend([i,j])
            wq.append([i,j])
        elif lake[i][j] == '.':
            wvisited[i][j] = 1
            wq.append([i,j])


x1,y1,x2,y2 = swan
q.append([x1,y1])
lake[x1][y1],lake[x2][y2],visited[x1][y1] = '.','.',1
count = 0

dx = [1,-1,0,0]
dy = [0,0,1,-1]


def bfs():
    while q:
        x,y = q.popleft()
        if x == x2 and y == y2:
            return 1
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < n and 0 <= ny < m:
                if visited[nx][ny] == 0:
                    if lake[nx][ny] == '.':
                        q.append([nx,ny])
                    else:
                        qtemp.append([nx,ny])
                    visited[nx][ny] = 1
    return 0


def melt():
    while wq:
        x,y = wq.popleft()
        if lake[x][y] == 'X':
            lake[x][y] = '.'
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]
            if 0 <= nx < n and 0 <= ny < m:
                if wvisited[nx][ny] == 0:
                    if lake[nx][ny] == 'X':
                        wqtemp.append([nx,ny])
                    else:
                        wq.append([nx,ny])
                    wvisited[nx][ny] = 1


while True:
    melt()
    if bfs():
        print(count)
        break
    q,wq = qtemp, wqtemp
    qtemp,wqtemp = deque(),deque()
    count += 1