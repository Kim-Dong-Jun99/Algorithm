import sys
n = int(sys.stdin.readline())
ns = list(map(int, sys.stdin.readline().split()))
result = [0]
for i in ns:
    if result[-1] < i:
        result.append(i)
    else:
        left = 0
        right = len(result)
        while left < right:
            mid = (left+right) // 2
            if result[mid] < i:
                left = mid + 1
            else:
                right = mid

        result[right] = i
print(n - len(result) + 1)