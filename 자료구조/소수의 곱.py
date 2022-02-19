import sys
import heapq
k, n = map(int, sys.stdin.readline().split())
ps = list(map(int, sys.stdin.readline().split()))
heap = []
# table = {}
for i in ps:
    heapq.heappush(heap, i)
    # table[i] = 1

for i in range(n):
    a = heapq.heappop(heap)
    # print(table)
    # print(a)
    if i == n-1:
        print(a)
        break
    for j in range(k):
        # if table.get(a * ps[j], -1) == -1:
        #     heapq.heappush(heap, a * ps[j])
        #     table[a*ps[j]] = 1
        heapq.heappush(heap, a * ps[j])
        if a % ps[j] == 0:
            break
