import sys
sys.setrecursionlimit(10**6)
n, m = map(int, sys.stdin.readline().split())

point= {}
for i in range(n):
    point[i] = i


def find(x):
    if x == point[x]:
        return x
    else:
        point[x] = find(point[x])
        return point[x]


def union(x, y):
    rx = find(x)
    ry = find(y)
    if rx != ry:
        point[rx] = ry

done = True
for _ in range(m):
    i, j = map(int, sys.stdin.readline().split())
    parent_i = find(i)
    parent_j = find(j)
    if parent_i == parent_j:
        print(_+1)
        done = False
        break
    else:
        union(i,j)
if done:
    print(0)

