import sys

n = int(sys.stdin.readline())
ns = list(map(int, sys.stdin.readline().split()))
newns = list(set(ns))
newns.sort()
table = {}
for i in range(len(newns)):
    table[newns[i]] = i

for i in ns:
    print(table[i], end=' ')