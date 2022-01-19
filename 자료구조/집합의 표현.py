import sys
n,m = map(int,sys.stdin.readline().split())
groups = {}
for i in range(1,n+1):
    groups[i] = set([i])
for i in range(m):
    query = list(map(int,sys.stdin.readline().split()))
    if query[0] == 0:
        temp1 = groups[query[1]]
        temp2 = groups[query[2]]
        newS = temp1.union(temp2)
        groups[query[1]] = newS
        groups[query[2]] = newS
    else:
        temp1 = groups[query[1]]
        temp2 = groups[query[2]]
        if temp1 == temp2:
            print('YES')
        else:
            print('NO')