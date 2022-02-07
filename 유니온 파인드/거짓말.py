import sys
from collections import deque
n, m = map(int, sys.stdin.readline().split())
temp = list(map(int, sys.stdin.readline().split()))

truth = deque(temp[1:])
parent = {}
visited = [0 for _ in range(n+1)]
canlie = [1 for _ in range(m)]
for i in truth:
    parent[i] = truth[0]
    visited[i] = 1
for i in range(1,n+1):
    if (i not in parent):
        parent[i] = i

def find(x):
    if parent[x] == x:
        return x
    else:
        parent[x] = find(parent[x])
        return parent[x]


def union(x, y):
    rx = find(x)
    ry = find(y)
    if rx != ry:
        parent[rx] = ry


# count = 0
query = [list(map(int, sys.stdin.readline().split())) for _ in range(m)]
while truth:
    # check = False
    for i in range(m):
        check = False
        for j in range(1, len(query[i])):
            if find(query[i][j]) == find(truth[0]):
                check = True
                break
        if check:
            canlie[i] = 0
            for j in range(1,len(query[i])):
                if find(query[i][j]) != find(truth[0]):
                    union(query[i][j], truth[0])
                    if visited[query[i][j]] == 0:
                        truth.append(query[i][j])
                        visited[query[i][j]] = 1
    truth.popleft()
print(sum(canlie))