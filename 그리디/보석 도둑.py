import sys
import heapq

n, k = map(int, sys.stdin.readline().split())
jewels = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
bags = [int(sys.stdin.readline()) for _ in range(k)]

result = 0

jewels.sort()
bags.sort()
index = 0
stack = []
for i in bags:

    while index < len(jewels) and jewels[index][0] <= i:
        heapq.heappush(stack, -jewels[index][1])
        index += 1
    if stack != []:
        result += -heapq.heappop(stack)
    if index >= len(jewels) and stack == []:
        break

print(result)