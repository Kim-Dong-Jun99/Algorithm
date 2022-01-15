import sys
import heapq
m = int(sys.stdin.readline())
query = [int(sys.stdin.readline()) for i in range(m)]
heap = []
for i in query:
    if i == 0:
        if heap:
            print(heapq.heappop(heap))
        else:
            print(0)
    else:
        heapq.heappush(heap, i)