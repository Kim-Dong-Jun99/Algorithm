import sys

n = int(sys.stdin.readline())
graph = [[] for i in range(n + 1)]
edge = int(sys.stdin.readline())
query = [list(map(int, sys.stdin.readline().split())) for i in range(edge)]
for i in query:
    graph[i[0]].append(i[1])
    graph[i[1]].append(i[0])

nextV = [1]
visited = [1]

while nextV:
    temp = []
    for i in nextV:
        for j in graph[i]:
            if (j not in visited):
                temp.append(j)
                visited.append(j)
    nextV = temp
print(len(visited) - 1)