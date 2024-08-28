from collections import *
dy = [1, -1, 0, 0] 
dx = [0, 0, 1, -1]

def BFS(A1, A2):
  dq = deque([[*A1, *A1, 0]])
  while dq:
    y, x, ylast, xlast, d = dq.popleft()
    if visited[y][x]:
      continue
    visited[y][x] = ylast, xlast
    if [y,x] == A2:
      return d
    for i in range(4):
      y1, x1 = y + dy[i], x + dx[i]
      if N > y1 >= 0 and M > x1 >=0:
        dq.append((y1, x1, y, x, d+1))
  return 1e6

def solve(A1, A2, B1, B2):
  global visited
  visited = [[0]*M for i in range(N)]
  for y,x in [B1,B2]:
    visited[y][x] = 1
  d = BFS(A1, A2)
  if d == 1e6:
    return 1e6
  y,x = A2
  newvisited = [[0]*M for i in range(N)]
  while 1:
    if newvisited[y][x]:
      break
    newvisited[y][x] = 1
    y,x = visited[y][x]
  visited = newvisited
  return d+BFS(B1,B2)

N, M = map(lambda x:int(x)+1,input().split())
co = A1,A2,B1,B2 = [[*map(int,input().split())] for i in range(4)]
print("IMPOSSIBLE" if (result:=min(solve(A1,A2,B1,B2),solve(B1,B2,A1,A2)))>1e5 else result)