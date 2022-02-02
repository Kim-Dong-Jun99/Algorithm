import sys
n = int(sys.stdin.readline())
ns = list(map(int,sys.stdin.readline().split()))
ns.sort()
print(ns[0]*ns[-1])