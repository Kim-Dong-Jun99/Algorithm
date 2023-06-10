import sys
from heapq import heappush, heappop
from collections import defaultdict
n=int(sys.stdin.readline())
m=int(sys.stdin.readline())
graph = defaultdict(lambda:[])
for _ in range(m):
    a,b,v = map(int,sys.stdin.readline().split())
    graph[a].append([b,v])
    
start,end=map(int, sys.stdin.readline().split())
queue=[]
past_node = [0] *(n+1)
distances = [sys.maxsize for _ in range(n+1)]
distances[start] = 0
heappush(queue, [0, start])

while queue:
    cur_dis, cur_dest = heappop(queue)
    if distances[cur_dest] < cur_dis:
        continue
        
    for new_dest, new_dis in graph[cur_dest]:
        distance = cur_dis + new_dis
        if distance < distances[new_dest]:
            distances[new_dest] = distance
            heappush(queue, [distance, new_dest])
            past_node[new_dest] = cur_dest
sys.stdout.write("%d\n"%distances[end])
cur = end
roadmap = []
while True:
    roadmap.append(cur)
    if cur == start:
        break
    cur = past_node[cur]
sys.stdout.write("%d\n"%len(roadmap))
for i in range(len(roadmap)-1, -1, -1):
    sys.stdout.write("%d "%roadmap[i])
sys.stdout.write("\n")            
