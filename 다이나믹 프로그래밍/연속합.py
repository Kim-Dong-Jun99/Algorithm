import sys
n = int(sys.stdin.readline())
ns = list(map(int,sys.stdin.readline().split()))

for i in range(1,n):
    if ns[i-1] + ns[i] > ns[i]:
        ns[i] = ns[i-1] + ns[i]
print(max(ns))