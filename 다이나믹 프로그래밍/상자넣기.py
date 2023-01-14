import sys

def bigger(i,j):
    if i > j:
        return i
    return j

n = int(sys.stdin.readline())

ns = list(map(int, sys.stdin.readline().split()))

dp = [0]*n

for i in range(n):
    dp[i] += 1
    for j in range(i+1,n):
        if ns[j] > ns[i]:
            dp[j] = bigger(dp[i], dp[j])

print(max(dp))