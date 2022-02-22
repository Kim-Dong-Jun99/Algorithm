import sys

n, m = map(int, sys.stdin.readline().split())
rec = [sys.stdin.readline().strip() for _ in range(n)]


dp = [[0 for _ in range(m)] for _ in range(n)]
check = True
for i in range(n):
    for j in range(m):
        if rec[i][j] == '1':
            check = False
            break
if check:
    print(0)
    sys.exit()
for i in range(m):
    if rec[0][i] == '1':
        dp[0][i] = 1
curmax = 1
for i in range(1, n):
    for j in range(m):
        if rec[i][j] == '1':
            dp[i][j] = dp[i-1][j] + 1
        else:
            dp[i][j] = 0
    con = 0
    maxcon = 0
    for j in range(m):
        if dp[i][j] > curmax:
            con += 1
            if con > maxcon:
                maxcon = con
        else:
            con = 0
    if maxcon > curmax:
        curmax += 1

print(curmax ** 2)