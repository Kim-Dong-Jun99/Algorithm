multi_input = list(map(int, input().split()))
n = multi_input[0]
edges = multi_input[1]
graph = [[] for i in range(n + 1)]

dfs = [multi_input[2]]
bfs = [multi_input[2]]

for i in range(edges):
    temp = list(map(int, input().split()))
    j = 0
    already = False
    while j < len(graph[temp[0]]):
        if graph[temp[0]][j] > temp[1]:
            break
        elif graph[temp[0]][j] == temp[1]:
            already = True
            break
        else:
            j += 1
    if already == False:
        graph[temp[0]].insert(j, temp[1])
    j = 0
    while j < len(graph[temp[1]]):
        if graph[temp[1]][j] > temp[0]:
            break
        elif graph[temp[1]][j] == temp[0]:
            already = True
            break
        else:
            j += 1
    if already == False:
        graph[temp[1]].insert(j, temp[0])
# dfs
try:
    cur = multi_input[2]

    stack = []
    while True:
        f = False
        for i in graph[cur]:
            if (i in dfs) == False:
                stack.append(cur)
                dfs.append(i)
                cur = i
                f = True
                break
        if f == False:
            if cur == multi_input[2]:
                break
            else:
                cur = stack.pop()
except IndexError as e1:
    print(dfs)

# bfs
nextV = [multi_input[2]]
while nextV:
    temp = []
    for i in nextV:
        for j in graph[i]:
            if (j in bfs) == False:
                temp.append(j)
                bfs.append(j)

    nextV = temp

for i in dfs:
    print(i, end=' ')
print()
for j in bfs:
    print(j, end=' ')