import sys
from collections import deque
tc = int(sys.stdin.readline())

for _ in range(tc):
    n, m, t = map(int, sys.stdin.readline().split())
    s, g, h = map(int, sys.stdin.readline().split())

    graph = [[] for _ in range(n + 1)]
    # backgraph = [[] for _ in range(n+1)]
    for __ in range(m):
        a, b, c = map(int, sys.stdin.readline().split())
        graph[a].append([b, c])
        graph[b].append([a, c])
        # backgraph[b].append([a, c])
    cand = [int(sys.stdin.readline()) for _ in range(t)]
    result = []
    v = [sys.maxsize for _ in range(n+1)]
    v[s] = 0
    q = deque()
    q.append(s)
    while q:
        temp = q.popleft()
        for i, t in graph[temp]:
            if v[i] > v[temp] + t:
                v[i] = v[temp] + t
                q.append(i)
    # print(v)
    for i in cand:
        if v[i] == sys.maxsize:
            continue
        cur = [i]
        # before = None
        check = False
        visited = [0] *(n+1)
        while cur:
            temp = []

            for k in cur:
                for j, t in graph[k]:
                    if v[j] == v[k] - t:
                        # before = j
                        # break
                        if (j == g and k == h) or (j == h and k == g):
                            check = True
                            break
                        if j != s and visited[j] == 0:
                            temp.append(j)
                            visited[j] = 1
                if check:
                    break
            if check:
                break
            # if (before == g and cur == h) or (before == h and cur == g):
            #     check = True
            #     break
            cur = temp

        if check:
            result.append(i)
    result.sort()
    print(*result)

    #Backgraph 이용