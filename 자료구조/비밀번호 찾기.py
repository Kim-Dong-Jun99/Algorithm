import sys
n,m = map(int, sys.stdin.readline().split())
keys = {}
for _ in range(n):
    a, b = sys.stdin.readline().split()
    keys[a] = b

for _ in range(m):
    temp = sys.stdin.readline().split()
    print(keys[temp[0]])