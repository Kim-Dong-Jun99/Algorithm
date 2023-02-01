import sys
from collections import defaultdict

def can_go(i, j, k):
    temp = []
    if i - 1 >= 0 and tomatoes[i-1][j][k] == 0:
        temp.append([i-1, j, k])
    if i + 1 < H and tomatoes[i+1][j][k] == 0:
        temp.append([i+1, j, k])
    if j - 1 >= 0 and tomatoes[i][j-1][k] == 0:
        temp.append([i, j-1, k])
    if j + 1 < N and tomatoes[i][j+1][k] == 0:
        temp.append([i, j+1, k])
    if k - 1 >= 0 and tomatoes[i][j][k-1] == 0:
        temp.append([i, j, k-1])
    if k + 1 < M and tomatoes[i][j][k+1] == 0:
        temp.append([i, j, k+1])
    return temp
        

M, N, H = map(int, sys.stdin.readline().split())
# visited = []
next_node = []
# tomatoes[0][0][1] - 0 번째 층 0번째 줄 1번째 칸
tomatoes = []
non_ripe = 0
for i in range(H):
    floor = []
    for j in range(N):
        floor.append(list(map(int, sys.stdin.readline().split())))
        for k in range(M):
            if floor[j][k] == 1:
#                 visited[i][j][k] = 1
                next_node.append([i, j, k])
            elif floor[j][k] == 0:
                non_ripe += 1
                
    tomatoes.append(floor)
    
if non_ripe == 0:
    print(0)
    sys.exit()
    
result = 0
while next_node:
    result += 1
    temp = []
    for i, j, k in next_node:
        for i_, j_, k_ in can_go(i, j, k):
            temp.append([i_, j_, k_])
            tomatoes[i_][j_][k_] = 1
            non_ripe -= 1
    next_node = temp
    if non_ripe == 0:
        break
        
    
if non_ripe == 0:
    print(result)
else:
    print(-1)