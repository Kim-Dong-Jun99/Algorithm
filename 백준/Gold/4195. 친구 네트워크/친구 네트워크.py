import sys
tc = int(sys.stdin.readline())

def find(x):
    if parent[x] == x:
        return x
    else:
        parent[x] = find(parent[x])
        return parent[x]


def union(x,y):
    rx = find(x)
    ry = find(y)
    if rx != ry:
        parent[ry] = rx
        number[rx] += number[ry]
for _ in range(tc):
    n = int(sys.stdin.readline())
    parent = {}
    number = {}
    for __ in range(n):
        x,y = sys.stdin.readline().split()
        if x not in parent:
            parent[x] = x
            number[x] = 1
        if y not in parent:
            parent[y] = y
            number[y] = 1
        union(x,y)
        print(number[find(x)])
    