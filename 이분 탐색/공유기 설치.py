import sys

n, c = map(int, sys.stdin.readline().split())
homes = [int(sys.stdin.readline()) for _ in range(n)]
homes.sort()
div = (homes[-1] - homes[0]) // (c - 1)
left = 0
right = len(homes) - 1
minD = div

for _ in range(c - 2):
    right = len(homes) - 1
    find = homes[left] + div

    while True:
        mid = (left + right) // 2
        if homes[mid] == find:
            left = mid
            break
        elif homes[mid] < find:
            left = mid+1
        else:
            right = mid-1
        if right < left:
            if abs(homes[right] - find) < abs(homes[left] - find):
                if find - abs(homes[right] - find) < minD:
                    minD = find - abs(homes[right] - find)
                left = right
            else:
                if find - abs(homes[left] - find) < minD:
                    minD = find - abs(homes[left] - find)
            break
print(minD)