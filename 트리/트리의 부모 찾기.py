import sys
n = int(sys.stdin.readline())
edges = [list(map(int,sys.stdin.readline().split())) for i in range(n-1)]
graph = [[]for i in range(n+1)]
parents = [0 for i in range(n+1)]
for i in edges:
    graph[i[0]].append(i[1])
    graph[i[1]].append(i[0])
nextV = [1]
visited = [1]
while nextV:
    temp = []
    for i in nextV:
        for j in graph[i]:
            if parents[j] == 0:
                temp.append(j)
                visited.append(j)
                parents[j] = i
    nextV = temp
for i in range(2,n+1):
    print(parents[i])