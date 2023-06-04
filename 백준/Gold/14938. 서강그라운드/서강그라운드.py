import sys
from collections import defaultdict
from heapq import heappop, heappush
n, m, r = map(int, sys.stdin.readline().split())
items = list(map(int, sys.stdin.readline().split()))
graph = defaultdict(lambda : [])

for ____ in range(r):
    a,b,l = map(int, sys.stdin.readline().split())
    a-=1
    b-=1
    graph[a].append([l, b])
    graph[b].append([l, a])
result = 0
for i in range(n):
    distances = [sys.maxsize]*n
    distances[i] = 0
    queue=[]
    heappush(queue, [0, i])
    while queue:
        current_distance, current_destination = heappop(queue)
        if distances[current_destination] < current_distance:
            continue
        for new_dis, new_dest in graph[current_destination]:
            distance = current_distance + new_dis
            if distance < distances[new_dest]:
                distances[new_dest] = distance
                heappush(queue, [distance, new_dest])
    temp_result = 0
    for j in range(n):
        if distances[j] <= m:
            temp_result += items[j]
    if result < temp_result:
        result = temp_result
    
sys.stdout.write("%d\n"%result)
    