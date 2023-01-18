import sys

N, L = map(int, sys.stdin.readline().split())
leaks = list(map(int, sys.stdin.readline().split()))

leaks.sort()
result = 0
cur = -1000
for i in leaks:
    if cur + L <= i:
        result += 1
        cur = i

print(result)