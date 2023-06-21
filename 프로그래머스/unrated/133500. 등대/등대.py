import sys
sys.setrecursionlimit(10**6)
def solution(n, lighthouse):
    graph = [[] for _ in range(n+1)]
    
    for a,b in lighthouse:
        graph[a].append(b)
        graph[b].append(a)
    """
    흠 dp를 이용해서 해결해볼까,
    루트를 1번으로 설정하고 서브트리를 탐색하는 느낌으로 해볼까,
    """
    # 0번 인덱스 불 끈 것, 1번 인덱스 불 킨것
    dp = [[0, 0] for _ in range(n+1)]
    
    visited = [0] * (n+1)
    def dfs(node):
        visited[node] = 1
        leaf_list = []
        for i in graph[node]:
            if visited[i] == 0:
                leaf_list.append(i)
                dfs(i)
        dp[node][0] = 0
        dp[node][1] = 1
        for leaf in leaf_list:
            dp[node][0] += dp[leaf][1]
            dp[node][1] += min(dp[leaf])
        return
        
    dfs(1)
    return min(dp[1])
    
    
    
    

    
    