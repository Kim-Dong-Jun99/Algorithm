import sys
from collections import deque
sys.setrecursionlimit(10**7)
n, m = map(int, sys.stdin.readline().split())
parent = [i for i in range(n)]
graph = [{} for _ in range(n)]
indegree = [0] * n

def find(x):
    if x == parent[x]:
        return parent[x]
    else:
        parent[x] = find(parent[x])
        return parent[x]


def union(x, y):
    rx = find(x)
    ry = find(y)

    if rx != ry:

        parent[rx] = ry


compare = []
tpcheck = set()
for _ in range(m):
    q = list(sys.stdin.readline().split())
    if q[1] == '=':
        union(int(q[0]), int(q[2]))
    elif q[1] == '>':
        compare.append((int(q[0]), int(q[2])))
        # graph[int(q[2])].append(int(q[0]))
        # indegree[int(q[0])] += 1
        # tpcheck.add(int(q[2]))
        # tpcheck.add(int(q[0]))

for i in compare:
    p1 = find(i[1])
    p0 = find(i[0])
    if graph[p1].get(p0, -1) == -1:
        graph[p1][p0] = 1
        indegree[p0] += 1
        tpcheck.add(p0)
        tpcheck.add(p1)

q = deque()
result = set()

for i in range(n):
    if indegree[i] == 0 and i in tpcheck:
        q.append(i)
check = False
while q:
    temp = q.popleft()
    for i in graph[temp].keys():
        indegree[i] -= 1
        if indegree[i] == 0:
            q.append(i)
    result.add(temp)

if len(tpcheck) == len(result):
    print('consistent')
else:
    print('inconsistent')