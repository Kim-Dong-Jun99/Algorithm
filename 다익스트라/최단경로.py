import sys
n,m = map(int,sys.stdin.readline().split())
start = int(sys.stdin.readline())
graph = [[]for i in range(n+1)]
dijkstra = [sys.maxsize for i in range(n+1)]
visited = [0 for i in range(n+1)]
for i in range(m):
    query = list(map(int,sys.stdin.readline().split()))
    graph[query[0]].append([query[1],query[2]])
nextV = [start]
dijkstra[start] = 0
visited[start] = 1
while nextV:
    temp = []
    for i in nextV:
        for j in graph[i]:
            if dijkstra[j[0]] == sys.maxsize:
                dijkstra[j[0]] = dijkstra[i]+j[1]
                temp.append(j[0])
            elif dijkstra[j[0]] > dijkstra[i] + j[1]:
                dijkstra[j[0]] = dijkstra[i] + j[1]
                renew = [j[0]]
                while renew:
                    rtemp = []
                    for k in renew:
                        for l in graph[k]:
                            if dijkstra[l[0]] != sys.maxsize and dijkstra[l[0]] > dijkstra[k]+l[1]:
                                dijkstra[l[0]] = dijkstra[k] + l[1]
                                rtemp.append(l[0])
                    renew = rtemp
    nextV = temp
for i in range(1,n+1):
    if dijkstra[i] != sys.maxsize:
        print(dijkstra[i])
    else:
        print('INF')