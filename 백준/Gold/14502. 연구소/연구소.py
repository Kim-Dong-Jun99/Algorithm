import sys
from itertools import combinations
n,m = map(int,sys.stdin.readline().split())
labs = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

def ps(i,j):
    result = []
    if i-1 > -1:
        if labs[i-1][j] == 0:
            result.append((i-1,j))
    if i+1 < n:
        if labs[i+1][j] == 0:
            result.append((i+1,j))
    if j-1 > -1:
        if labs[i][j-1] == 0:
            result.append((i,j-1))
    if j+1 < m:
        if labs[i][j+1] == 0:
            result.append((i,j+1))
    return result


viruses = []
walls = 0
empty = []
for i in range(n):
    for j in range(m):
        if labs[i][j] == 2:
            viruses.append((i,j))
        elif labs[i][j] == 1:
            walls += 1
        else:
            empty.append((i,j))
maxSpace = -sys.maxsize
for itr in combinations(empty,3):
    tempwalls = walls+3
    visited = {}
    for j in itr:
        labs[j[0]][j[1]] = 1

    nextV = viruses[:]
    while nextV:
        temp = []
        for i in nextV:
            visited[i] = 1
            for j in ps(i[0],i[1]):
                try:
                    check = visited[j]
                except:
                    temp.append(j)
                    visited[j] = 1
        nextV = temp
    tempSize = n*m - len(list(visited.keys())) - tempwalls
    if tempSize > maxSpace:
        maxSpace = tempSize

    for j in itr:
        labs[j[0]][j[1]] = 0
print(maxSpace)
