import sys

n, m = map(int, sys.stdin.readline().split())
ns = list(map(int, sys.stdin.readline().split()))

left = max(ns)
right = sum(ns)

while left <= right:
    mid = (left + right) // 2
    total = 0
    cur = 0
    # for i in range(n):
    #     if cur + ns[i] <= mid:
    #         cur += ns[i]
    #         if i == n-1:
    #             total += 1
    #     else:
    #         cur = ns[i]
    #         total += 1

    for i in range(n):
        if cur + ns[i] > mid:
            total += 1
            cur = 0
        cur += ns[i]
    else:
        if cur:
            total += 1

    if total <= m:
        right = mid - 1
    else:
        left = mid + 1

print(left)

