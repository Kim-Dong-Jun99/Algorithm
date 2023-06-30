import sys
from collections import deque
n, k = map(int,sys.stdin.readline().split())

nextV = deque()
nextV.append(n)
visited = [-1]*(10**5+1)
visited[n] = 0

while nextV:
    cur = nextV.popleft()
    if cur == k:
        print(visited[cur])
        break
    if cur - 1 >= 0 and visited[cur-1] == -1:
        nextV.append(cur-1)
        visited[cur-1] = visited[cur]+1
    if cur*2 <= 10**5 and visited[cur*2] == -1:
        nextV.append(cur*2)
        visited[cur*2] = visited[cur]
        
    if cur + 1 <= 10**5 and visited[cur+1] == -1:
        nextV.append(cur+1)
        visited[cur+1] = visited[cur]+1
        
    
        
        

        
    