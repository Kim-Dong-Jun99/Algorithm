import sys
tc = int(sys.stdin.readline())

for _ in range(tc):
    n, m, k = map(int, sys.stdin.readline().split())
    graph = [[] for _ in range(n+1)]
    for itr in range(k):
        a, b, c, d = map(int, sys.stdin.readline().split())
        
        graph[a].append([b, c, d])
    dp = [[sys.maxsize for _ in range(m+1)] for _ in range(n+1)]
    dp[1][0] = 0
    for c in range(m+1):
        for d in range(1,n+1):
            if dp[d][c] == sys.maxsize:
                continue
                
            t = dp[d][c]
            for dv, dc, dd in graph[d]:
                if dc +c > m:
                    continue
                dp[dv][dc + c] = min(dp[dv][dc+c], t+dd)
    result = min(dp[n])
    if result == sys.maxsize:
        print('Poor KCM')
    else:
        print(result)