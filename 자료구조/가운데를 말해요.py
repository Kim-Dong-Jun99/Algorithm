import sys
import heapq

n = int(sys.stdin.readline())

left = []
right = []

for _ in range(n):
    temp = int(sys.stdin.readline())
    if len(left) == len(right):
        if left and temp > -left[0]:
            heapq.heappush(right, temp)
            heapq.heappush(left, -heapq.heappop(right))
        else:
            heapq.heappush(left, -temp)
    else:
        if temp > -left[0]:
            heapq.heappush(right, temp)
        else:
            heapq.heappush(left, -temp)
            heapq.heappush(right, -heapq.heappop(left))
    print(-left[0])


