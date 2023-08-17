import sys
n,k = map(int,sys.stdin.readline().split())
coins = [int(sys.stdin.readline()) for i in range(n)]
result = 0
temp = k
for i in range(n-1,-1,-1):
    result += temp // coins[i]
    temp = temp % coins[i]
    if temp == 0:
        break
print(result)