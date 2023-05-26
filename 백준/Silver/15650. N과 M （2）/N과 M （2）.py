import sys
from itertools import combinations
n, m = map(int, sys.stdin.readline().split())
nums = [i for i in range(1,n+1)]
combi = list(combinations(nums, m))
for i in combi:
    for j in i:
        sys.stdout.write("%d "% j)
    sys.stdout.write("\n")
    