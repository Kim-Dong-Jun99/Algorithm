import sys

n = int(sys.stdin.readline())
ns = list(map(int, sys.stdin.readline().split()))
m = int(sys.stdin.readline())
ms = list(map(int, sys.stdin.readline().split()))
ns.sort()

for i in ms:
    left = 0
    right = n - 1
    found = False
    while left <= right:
        mid = int((left + right) / 2)
        if ns[mid] == i:
            found = True
            print(1, end=' ')
            break
        elif ns[mid] < i:
            left = mid + 1
        else:
            right = mid - 1
    if found == False:
        print(0, end=' ')
