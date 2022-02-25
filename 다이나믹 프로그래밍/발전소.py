import sys

n = int(sys.stdin.readline())
cost = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

dp = [sys.maxsize for _ in range(2 ** n)]

status = sys.stdin.readline().strip()

p = int(sys.stdin.readline())

s = 0
for i in range(len(status)):
    if status[i] == 'Y':
        s += 2**i

# print(s)
dp[s] = 0
for i in range(s, 2**n):
    if dp[i] != sys.maxsize:
        for j in range(n):
            if i & (1 << j) == (1 << j):
                # print(j)
                for k in range(n):
                    # print('j')
                    # print(abs(j-n-1))
                    if dp[i]+cost[j][k] < dp[i | (1 << k)]:
                        dp[i | (1 << k)] = dp[i] + cost[j][k]
        # print(bin(i))
        # for l in range(2**n):
        #     if dp[l] != sys.maxsize:
        #         print('%s %d'%(bin(l), dp[l]), end = ' ')
        # print()
        # print(dp)

minV = sys.maxsize
for i in range(2**n):
    if bin(i).count('1') >= p:
        if minV > dp[i]:
            minV = dp[i]
# print(dp)
if minV == sys.maxsize:
    print(-1)
else:
    print(minV)
