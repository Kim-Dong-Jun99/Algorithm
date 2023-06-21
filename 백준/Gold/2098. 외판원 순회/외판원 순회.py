import sys

N = int(sys.stdin.readline())

roadmap = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

dp = [[0] * (1 << N) for _ in range(N)]
END = (1 << N) - 1
def TSP(now, visited):
    if visited == END:
        if roadmap[now][0] > 0:
            return roadmap[now][0]
        else:
            return sys.maxsize
    if dp[now][visited] != 0:
        return dp[now][visited]
    dp[now][visited] = sys.maxsize
    
    for i in range(N):
        if roadmap[now][i] == 0:
            continue
        if visited & (1 << i):
            continue
        temp = TSP(i, visited | 1 << i)
        dp[now][visited] = min(dp[now][visited], roadmap[now][i] + temp)
        
    return dp[now][visited]
    
    
sys.stdout.write("%d\n"%TSP(0,1))
