import sys
n = int(sys.stdin.readline())
ns = [sys.stdin.readline().strip() for _ in range(n)]

dp = [sys.maxsize for _ in range(2**n)]
dp[1] = 0


def sol(s, cur):
    for i in range(n):
        if s & (1 << i) != (1 << i) and dp[s] <= int(ns[cur][i]):
            dp[s | (1 << i)] = int(ns[cur][i])
            sol(s | (1 << i), i)


sol(1, 0)
result = 0
for i in range(2**n):
    if dp[i] != sys.maxsize:
        temp = 0
        for j in range(n):
            if i & (1 << j) == (1 << j):
                temp += 1
        if temp > result:
            result = temp
# print(dp)
print(result)
