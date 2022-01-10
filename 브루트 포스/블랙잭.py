import sys
from itertools import combinations
n,k = map(int,sys.stdin.readline().split())
nums = list(map(int,sys.stdin.readline().split()))
nearest = None
for i in combinations(nums,3):
    temp = sum(i)
    if nearest == None and temp <= k:
        nearest = temp
    elif temp <= k and k-nearest > k-temp:
        nearest = temp
print(nearest)