import sys
import math
t = int(sys.stdin.readline())

for _ in range(t):
    ns = list(map(int, sys.stdin.readline().split()))
    result = 0
    for i in range(1, len(ns)):
        for j in range(i+1, len(ns)):
            result += math.gcd(ns[i], ns[j])
    print(result)