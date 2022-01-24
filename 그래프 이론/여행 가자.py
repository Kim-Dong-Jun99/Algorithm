import sys

n = int(sys.stdin.readline())
m = int(sys.stdin.readline())
connect = [list(map(int, sys.stdin.readline().split())) for i in range(n)]
plan = list(map(int, sys.stdin.readline().split()))
cango = set()
visited = [0 for i in range(n)]
nextV = [plan[0] - 1]
visited[plan[0] - 1] = 1
cango.add(plan[0])
while nextV:
    temp = []
    for i in nextV:
        for j in range(n):
            if connect[i][j] == 1:
                if visited[j] == 0:
                    visited[j] = 1
                    temp.append(j)
                    cango.add(j + 1)
    nextV = temp

if cango.intersection(set(plan)) == set(plan):
    print('YES')
else:
    print('NO')
