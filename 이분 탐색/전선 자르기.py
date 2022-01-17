import sys
k,n = map(int,sys.stdin.readline().split())
wires = [int(sys.stdin.readline()) for i in range(k)]
left = 1
right = max(wires)

while left <= right:
    mid = (left+right) // 2
    temp = [i // mid if i >= mid else 0 for i in wires]
    tempSum = sum(temp)
    if tempSum >= n:
        left = mid + 1
    else:
        right = mid - 1
print(right)