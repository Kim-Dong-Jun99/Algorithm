import sys
from itertools import combinations

n, m = map(int, sys.stdin.readline().split())
city = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

house = []
chicken = []
for i in range(n):
    for j in range(n):
        if city[i][j] == 1:
            house.append([i, j])
        elif city[i][j] == 2:
            chicken.append([i, j])


def getdis(i, j, x, y):
    return abs(i - x) + abs(j - y)


cd = [[0 for _ in range(len(chicken))] for _ in range(len(house))]
for i in range(len(house)):
    for j in range(len(chicken)):
        cd[i][j] = getdis(house[i][0], house[i][1], chicken[j][0], chicken[j][1])

cand = [i for i in range(len(chicken))]
minD = sys.maxsize
for i in combinations(cand, m):
    tempD = 0
    for j in range(len(house)):
        tempm = sys.maxsize
        for k in i:
            if cd[j][k] < tempm:
                tempm = cd[j][k]
        tempD += tempm
    if tempD < minD:
        minD = tempD
print(minD)

