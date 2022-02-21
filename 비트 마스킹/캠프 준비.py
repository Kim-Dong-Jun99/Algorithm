import sys
import math

n, l, r, x = map(int, sys.stdin.readline().split())

a = list(map(int, sys.stdin.readline().split()))
result = 0
for i in range(3, 2 ** n):
    if int(math.log2(i)) != math.ceil(math.log2(i)):
        maxv = 0
        minv = sys.maxsize
        ts = 0
        for j in range(n):
            if i & (1 << j) != 0:
                ts += a[j]
                if a[j] > maxv:
                    maxv = a[j]
                if a[j] < minv:
                    minv = a[j]
        if (l <= ts <= r) and maxv - minv >= x:
            result += 1

print(result)