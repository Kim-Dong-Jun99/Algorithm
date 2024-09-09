import heapq
import sys


INF = 1e15


def dijkstra(start, limit):
    q = []
    distance = [INF] * (n + 1)
    heapq.heappush(q, (0, start))  
    distance[start] = 0

    while q:
        cost, index = heapq.heappop(q)
        if distance[index] < cost:
            continue
        for i in graph[index]:
            if i[0] > limit:
                if cost + 1 < distance[i[1]]:
                    distance[i[1]] = cost + 1
                    heapq.heappush(q, (cost + 1, i[1]))
            else:
                if cost < distance[i[1]]:
                    distance[i[1]] = cost
                    heapq.heappush(q, (cost, i[1]))

    if distance[n] > k:
        return False
    else:
        return True


if __name__ == "__main__":
    n, p, k = map(int, sys.stdin.readline().split())
    graph = [[] for _ in range(n + 1)]
    for _ in range(p):
        a, b, c = map(int, sys.stdin.readline().split())
        graph[a].append((c, b))
        graph[b].append((c, a))

    left, right = 0, 1000001
    answer = INF
    while left <= right:
        mid = (left + right) // 2
        flag = dijkstra(1, mid)
        if flag:
            right = mid - 1
            answer = mid
        else:
            left = mid + 1

    if answer == INF:
        print(-1)
    else:
        print(answer)