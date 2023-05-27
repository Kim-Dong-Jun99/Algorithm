import sys

ns = list(map(int, sys.stdin.readline().split()))

for i in range(len(ns)):
    ns[i] *= ns[i]
print(sum(ns) % 10)
    