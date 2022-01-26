import sys

n, m = map(int, sys.stdin.readline().split())
bridges = [[] for i in range(n + 1)]

for _ in range(m):
    i, j, c = map(int, sys.stdin.readline().split())
    bridges[i].append([j, c])
    bridges[j].append([i, c])
start, end = map(int, sys.stdin.readline().split())

snw = sorted(bridges[start], key=lambda bridges: bridges[1])

left = 1
right = snw[-1][1]
result = 0
while left <= right:
    mid = (left + right) // 2

    visited = [0 for _ in range(n + 1)]
    nextV = []
    for i in snw:
        if i[1] >= mid:
            nextV.append(i[0])
            visited[i[0]] = 1
    while nextV:
        temp = []
        for i in nextV:
            for j in bridges[i]:
                if j[1] >= mid and visited[j[0]] == 0:
                    temp.append(j[0])
                    visited[j[0]] = 1
        nextV = temp
    if visited[end] == 1:
        left = mid + 1
        result = mid
    else:
        right = mid - 1
print(result)