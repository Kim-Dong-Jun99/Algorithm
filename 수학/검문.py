import sys
import math

n = int(sys.stdin.readline())
ns = []
result = []
gcd = 0
for i in range(n):
    ns.append(int(sys.stdin.readline()))
    if i == 1:
        gcd = abs(ns[1] - ns[0])
    gcd = math.gcd(abs(ns[i] - ns[i - 1]), gcd)
    root_gcd = int(gcd ** 0.5)

for i in range(2, root_gcd + 1):
    if gcd % i == 0:
        result.append(i)
        result.append(gcd // i)
result.append(gcd)
result = list(set(result))
result.sort()
for i in result:
    print(i, end=' ')