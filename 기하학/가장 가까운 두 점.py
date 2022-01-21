import sys

n = int(sys.stdin.readline())
points = [list(map(int, sys.stdin.readline().split())) for i in range(n)]
points.sort()


def getD(left, right):
    if right == left + 1:
        d = (points[left][0] - points[right][0]) ** 2 + (points[left][1] - points[right][1]) ** 2
        return d
    elif right == left:
        return float('inf')
    else:
        mid = (left + right) // 2
        d = min(getD(left, mid), getD(mid, right))
        stan = points[mid][0]
        lb = mid - 1
        rb = mid + 1
        boundary = []
        while -1 < lb and d > (stan - points[lb][0]) ** 2:
            boundary.append(points[lb])
            lb -= 1

        while rb < len(points) and (points[rb][0] - stan) ** 2 < d:
            boundary.append(points[rb])
            rb += 1

        boundary.sort(key=lambda boundary: boundary[1])
        for i in range(len(boundary) - 1):
            j = i + 1
            while j < len(boundary) and (boundary[j][1] - boundary[i][1]) ** 2 < d:
                temp = (boundary[j][0] - boundary[i][0]) ** 2 + (boundary[j][1] - boundary[i][1]) ** 2
                if temp < d:
                    d = temp
                j += 1
        return d


checkset = list(set(map(tuple, points)))
if len(points) != len(checkset):
    print(0)
else:
    print(getD(0, n - 1))

