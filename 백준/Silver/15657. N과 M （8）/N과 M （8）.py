import sys
N, M = map(int, sys.stdin.readline().split())
ns = list(map(int, sys.stdin.readline().split()))
ns.sort()
s = []
def dfs(start):
    global N
    if len(s)==M:
        print(' '.join(map(str,s)))
        return

    for i in range(start, N):
        s.append(ns[i])
        dfs(i)
        s.pop()

dfs(0)