import sys
from itertools import permutations

n, m = map(int, sys.stdin.readline().split())
nums = list(map(int, sys.stdin.readline().split()))
nums.sort()

combi = list(permutations(nums, m))
for i in combi:
    for j in i:
        sys.stdout.write("%d "%j)
    sys.stdout.write("\n")