import sys
from collections import deque

n = int(sys.stdin.readline())
ns = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
d = int(sys.stdin.readline())
for i in range(n):
    if ns[i][0] > ns[i][1]:
        ns[i][0], ns[i][1] = ns[i][1], ns[i][0]
ns.sort()
heap = deque()
index = 0
result = 0
# print(ns)
for i in range(n):
    temp = ns[i]
    if index < i:
        index = i+1
    while index < n and ns[index][1] <= temp[0] + d and temp[0] + d >= temp[1]:
        heap.append(index)
        index += 1
    if result < len(heap):
        result = len(heap)
    while heap and heap[0] < i:
        heap.popleft()


print(result)