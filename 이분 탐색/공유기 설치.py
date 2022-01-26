import sys

n, c = map(int, sys.stdin.readline().split())
homes = [int(sys.stdin.readline()) for _ in range(n)]
homes.sort()
left = 1
right = homes[-1] - homes[0]
result = 0
while left <= right:
    temp = homes[0]
    count = 1
    mid = (left+right) // 2
    for i in range(1,n):
        if temp+mid <= homes[i]:
            count += 1
            temp = homes[i]
    if count >= c:
        left = mid+1
        result = mid
    else:
        right = mid-1
print(result)