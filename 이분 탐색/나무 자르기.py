import sys
n,m = map(int,sys.stdin.readline().split())
trees = list(map(int,sys.stdin.readline().split()))
left = 1
right = max(trees)

while left <= right:
    mid = (left+right) // 2
    total = [i-mid if i>mid else 0 for i in trees]
    temp = sum(total)
    if temp >= m:
        left = mid + 1
    else:
        right = mid - 1
print(right)