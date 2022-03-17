import sys

# test
def find(x):
    if x == parent[x]:
        return parent[x]
    else:
        parent[x] = find(x)
        return parent[x]


def union(x, y):
    rx = find(x)
    ry = find(y)

    if rx != ry:
        rx = parent[ry]



def getH(x):
    if x == parent[x]:
        return [parent[x]]
    else:
        temp = [x]
        temp += getH(x)
        return temp

while True:
    n, m = map(int, sys.stdin.readline().split())
    if n == 0 and m == 0:
        break
    parent = [i for i in range(n+1)]
    height = [0 for _ in range(n+1)]
    value = {}
    for _ in range(m):
        query = list(sys.stdin.readline().split())
        if query[0] == '!':
            a = int(query[1])
            b = int(query[2])
            c = int(query[3])
            value[(a, b)] = c
            union(b, a)
        else:
            a = int(query[1])
            b = int(query[2])
            if find(a) != find(b):
                print('UNKNOWN')
            else:
                l = getH(a)
                r = getH(b)

                lv = 0
                rv = 0

                for i in range(len(l)-1):
                    lv += value[(l[i], l[i+1])]
                for i in range(len(r)-1):
                    rv += value[(r[i], r[i+1])]

                print(lv-rv)

            
            


# 어떻게 하면 높이 차이를 계산할 수 있을까