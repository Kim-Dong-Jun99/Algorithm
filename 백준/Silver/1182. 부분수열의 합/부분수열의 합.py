import sys
from itertools import combinations

n,s = map(int,sys.stdin.readline().split())
nums = list(map(int,sys.stdin.readline().split()))
cnt = 0

for i in range(1,n+1):
    for j in list(combinations(nums,i)):
        if sum(j)==s:
            cnt+=1
print(cnt)

    