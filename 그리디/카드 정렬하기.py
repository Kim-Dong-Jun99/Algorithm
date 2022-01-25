import heapq
import sys

n = int(sys.stdin.readline())

heap = []
for _ in range(n):
    heapq.heappush(heap,int(sys.stdin.readline()))
result = 0
while True:
    if len(heap) > 2:
        temp1 = heapq.heappop(heap)
        temp2 = heapq.heappop(heap)
        result += temp1+temp2
        heapq.heappush(heap,temp1+temp2)
    else:
        if len(heap) == 2:
            while heap:
                result += heapq.heappop(heap)
        break
print(result)