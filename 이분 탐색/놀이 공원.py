import sys
n, m = map(int, sys.stdin.readline().split())
rides = list(map(int, sys.stdin.readline().split()))

if n < m:
    print(n)
else:
    left = 0
    right = 60000000000
    result = 0
    while left <= right:
        # print(left)
        # print(right)
        total = m
        mid = (left+right) // 2
        for i in rides:
            total += mid // i
        if total >= n:
            result = mid
            right = mid - 1
        else:
            left = mid + 1
    # print(left)
    # print(right)
    cnt = m
    for i in range(m):
        cnt += (result-1) // rides[i]
    for i in range(m):
        if result % rides[i] == 0:
            cnt += 1
        if cnt == n:
            print(i+1)
            break

