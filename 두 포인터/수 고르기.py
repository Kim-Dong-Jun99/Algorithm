import sys

n, m = map(int, sys.stdin.readline().split())
ns = [int(sys.stdin.readline()) for _ in range(n)]
ns.sort()
l = 0
r = 1
mind = sys.maxsize
while l < n and r < n:
    temp = ns[r] - ns[l]
    if temp == m:
        print(temp)
        exit()
    if temp < m:
        r += 1
        continue
    l += 1
    mind = min(mind, temp)
print(mind)

