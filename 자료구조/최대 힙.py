import heapq
import sys

n = int(sys.stdin.readline())
query = [int(sys.stdin.readline()) for i in range(n)]
maxHeap = []
for i in query:
    if i == 0:
        if maxHeap:
            print(-heapq.heappop(maxHeap))
        else:
            print(0)
    else:
        heapq.heappush(maxHeap, -i)
