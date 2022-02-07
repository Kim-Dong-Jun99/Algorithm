import sys
from collections import deque

n, m = map(int, sys.stdin.readline().split())

ns = list(map(int, sys.stdin.readline().split()))
ms = list(map(int, sys.stdin.readline().split()))

nindex = 0
mindex = 0

result = deque()
while nindex < n or mindex < m:
    if ns[nindex] < ms[mindex]:
        result.append(ns[nindex])
        nindex += 1
    else:
        result.append(ms[mindex])
        mindex += 1
    if nindex == n:
        while mindex < m:
            result.append(ms[mindex])
            mindex += 1
    elif mindex == m:
        while nindex < n:
            result.append(ns[nindex])
            nindex += 1

print(*result)