import sys

n = int(sys.stdin.readline())
visited = []
graph = [sys.stdin.readline().strip() for i in range(n)]


def ps(graph, i, j):
    result = []
    if i - 1 > -1:
        if graph[i - 1][j] == '1':
            result.append([i - 1, j])
    if i + 1 < len(graph):
        if graph[i + 1][j] == '1':
            result.append([i + 1, j])
    if j - 1 > -1:
        if graph[i][j - 1] == '1':
            result.append([i, j - 1])
    if j + 1 < len(graph[0]):
        if graph[i][j + 1] == '1':
            result.append([i, j + 1])
    return result


result = []
for i in range(n):
    for j in range(n):
        if graph[i][j] == '1':
            if ([i, j] not in visited):
                nextV = [[i, j]]
                visited.append([i, j])
                size = 1
                while nextV:
                    temp = []
                    for k in nextV:
                        for l in ps(graph, k[0], k[1]):
                            if (l not in visited):
                                size += 1
                                temp.append(l[:])
                                visited.append(l[:])
                    nextV = temp
                result.append(size)
print(len(result))
result.sort()
for i in result:
    print(i)
