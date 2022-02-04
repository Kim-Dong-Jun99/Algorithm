import sys
import heapq

n = int(sys.stdin.readline())
hq = []
for _ in range(n):
    temp = int(sys.stdin.readline())
    if temp != 0:
        heapq.heappush(hq, [abs(temp), temp])
    else:
        if hq:
            m = heapq.heappop(hq)
            print(m[1])
        else:
            print(0)
