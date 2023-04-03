import sys
from collections import deque

tc = int(sys.stdin.readline())
for _ in range(tc):
    n, k = map(int, sys.stdin.readline().split())
    ctime = [0] + list(map(int, sys.stdin.readline().split()))

    graph = [[] for _ in range(n + 1)]
    
    indegree = [0 for _ in range(n+1)]
    dp = [0 for _ in range(n+1)]
    
    for _ in range(k):
        left,right = map(int,sys.stdin.readline().split())
        graph[left].append(right)
        indegree[right] += 1
    end = int(sys.stdin.readline())
    
    q = deque()
    for i in range(1,n+1):
        if indegree[i] == 0:
            q.append(i)
            dp[i] = ctime[i]
    while q:
        temp = q.popleft()
        for i in graph[temp]:
            indegree[i] -= 1
            dp[i] = max(dp[temp]+ctime[i],dp[i])
            if indegree[i] == 0:
                q.append(i)
    print(dp[end])