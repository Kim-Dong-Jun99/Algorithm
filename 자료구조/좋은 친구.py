import sys
from collections import deque

n, k = map(int, sys.stdin.readline().split())
names = [[] for _ in range(21)]
for i in range(n):
    temp = sys.stdin.readline().strip()
    names[len(temp)].append([i, temp])

result = 0
for i in range(2, 21):
    if len(names[i]) >= 2:
        q = deque()
        for j in names[i]:
            while q and q[0][0] + k < j[0]:
                q.popleft()
            result += len(q)
            q.append(j)

print(result)