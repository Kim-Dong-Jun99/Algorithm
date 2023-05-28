import sys

N, M = map(int, sys.stdin.readline().split())
nums = list(map(int, sys.stdin.readline().split()))
s = 0
subsums = []
for i in nums:
    s += i
    subsums.append(s)
for _ in range(M):
    l, r = map(int,sys.stdin.readline().split())
    l -= 1
    r -= 1
    if l != 0:
        sys.stdout.write("%d\n"%(subsums[r] - subsums[l-1]))
    else:
        sys.stdout.write("%d\n"%(subsums[r]))
    