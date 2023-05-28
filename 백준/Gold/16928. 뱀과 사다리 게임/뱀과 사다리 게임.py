import sys
from collections import defaultdict
N, M = map(int, sys.stdin.readline().split())
ladder = defaultdict(lambda : 0)
snake = defaultdict(lambda : 0)
for _ in range(N):
    x, y = map(int, sys.stdin.readline().split())
    ladder[x] = y
for _ in range(M):
    u, v = map(int, sys.stdin.readline().split())
    snake[u] = v
    
cur = set([1])
visited = [0] * 101
visited[1] = 1
result = 0
while cur:
    result += 1
    temp = set()
    for i in cur:
        for j in range(1,7):
            if i+j <= 100:
                if i+j in ladder:
                    if visited[ladder[i+j]] == 0:
                        visited[ladder[i+j]] = 1
                        temp.add(ladder[i+j])
                elif i+j in snake:
                    if visited[snake[i+j]] == 0:
                        visited[snake[i+j]] = 1
                        temp.add(snake[i+j])
                else:
                    if visited[i+j] == 0:
                        visited[i+j] = 1
                        temp.add(i+j)
    cur = temp
    if visited[100] == 1:
        break
    
sys.stdout.write("%d\n"%result)
    