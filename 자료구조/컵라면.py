import sys
import heapq

n = int(sys.stdin.readline())
hw = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
heap = []
# hw.sort(key=lambda a: a[1], reverse=True)
hw.sort()
# print(hw)
for i in hw:
    heapq.heappush(heap, i[1])
    if len(heap) > i[0]:
        heapq.heappop(heap)

print(sum(heap))