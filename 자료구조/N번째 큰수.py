import sys
import heapq

n = int(sys.stdin.readline())
heap = []
temp = list(map(int, sys.stdin.readline().split()))
for i in temp:
    heapq.heappush(heap, i)
for _ in range(1, n):
    q = list(map(int, sys.stdin.readline().split()))
    for i in q:
        heapq.heappush(heap, i)
    for i in range(n):
        heapq.heappop(heap)

print(heap[0])
