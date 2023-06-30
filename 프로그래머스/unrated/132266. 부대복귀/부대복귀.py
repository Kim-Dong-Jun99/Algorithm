def solution(n, roads, sources, destination):
    answer = []
    graph = [[] for _ in range(n+1)]
    visited= [0] * (n+1)
    distance = [-1] * (n+1)
    
    for a, b in roads:
        graph[a].append(b)
        graph[b].append(a)
    visited[destination] = 1
    distance[destination] = 0
    cur = [destination]
    while cur:
        temp = []
        for i in cur:
            for j in graph[i]:
                if visited[j] == 0:
                    visited[j] = 1
                    distance[j] = distance[i] + 1
                    temp.append(j)
        cur = temp
    
    for i in sources:
        answer.append(distance[i])
    return answer