from collections import deque
import sys

n, l = map(int, sys.stdin.readline().split())

ns = list(map(int, sys.stdin.readline().split()))

d = deque()
for i in range(n):
    temp = ns[i]

    while d and d[-1] > temp:
        d.pop()
    d.append(temp)

    if i >= l and d[0] == ns[i - l]:
        d.popleft()
    print(d[0], end=' ')
