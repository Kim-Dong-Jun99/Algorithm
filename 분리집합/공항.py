import sys

g = int(sys.stdin.readline())
p = int(sys.stdin.readline())
parent = {}
for i in range(g + 1):
    parent[i] = i

planes = [int(sys.stdin.readline()) for _ in range(p)]


def find(x):
    if x == parent[x]:
        return x
    else:
        parent[x] = find(parent[x])
        return parent[x]


def union(x, y):
    rx = find(x)
    ry = find(y)

    if rx != ry:
        parent[rx] = ry


count = 0
for i in planes:
    # print('----------')
    # print(parent)
    temp = find(i)
    if temp == 0:
        break
    count += 1
    union(temp,temp-1)
    # print(parent)
print(count)