import sys
from collections import deque
tc = int(sys.stdin.readline())
for _ in range(tc):
    n = int(sys.stdin.readline())
    lastyear = list(map(int, sys.stdin.readline().split()))
    tree = [[] for _ in range(n+1)]
    rank = [0]*(n+1)
    indegree = [0] * (n+1)
    for i in range(n-1):
        for j in range(i+1,n):
            tree[lastyear[i]].append((lastyear[j]))
            indegree[lastyear[j]] += 1
    q = deque()
    m = int(sys.stdin.readline())
    for _ in range(m):
        a, b = map(int, sys.stdin.readline().split())
        check = True
        for i in tree[a]:
            if i == b:
                tree[a].remove(b)
                tree[b].append(a)
                indegree[b] -= 1
                indegree[a] += 1
                check = False
        if check:
            tree[b].remove(a)
            tree[a].append(b)
            indegree[a] -= 1
            indegree[b] += 1
    for i in range(1,n+1):
        if indegree[i] == 0:
            q.append(i)
    result = 0
    rlist = []
    if len(q) == 0:
        result = 1
    while q:
        cur = q.popleft()
        rlist.append(cur)
        check = 0
        for i in tree[cur]:
            indegree[i] -=1
            if indegree[i] == 0:
                q.append(i)
                check += 1
            elif indegree[i] < 0:
                result = 1
                break
        if check >= 2:
            result = 1
            break
    if result > 0 or len(rlist) < n:
        print('IMPOSSIBLE')
    else:
        print(*rlist)