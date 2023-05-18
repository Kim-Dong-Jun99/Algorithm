import sys

n = int(sys.stdin.readline())
ns = list(map(int, sys.stdin.readline().split()))
m = max(ns)
new_ns = []
for i in ns:
    new_ns.append(i/m*100)
print(sum(new_ns)/n)