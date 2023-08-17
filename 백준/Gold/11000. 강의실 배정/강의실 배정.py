import sys
import heapq

n = int(sys.stdin.readline())
endtimeq = []
pq = []
for _ in range(n):
    heapq.heappush(pq,list(map(int,sys.stdin.readline().split())))
maxClass = 0
for _ in range(n):
    temp = heapq.heappop(pq)
    if endtimeq != [] and temp[1] < endtimeq[0]:
        heapq.heappush(endtimeq, temp[1])
    else:
        while endtimeq and endtimeq[0] <= temp[0]:
            heapq.heappop(endtimeq)
        heapq.heappush(endtimeq,temp[1])
    if len(endtimeq) > maxClass:
        maxClass = len(endtimeq)
print(maxClass)
    