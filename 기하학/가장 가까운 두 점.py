import sys

n = int(sys.stdin.readline())
points = list(set(tuple(map(int, sys.stdin.readline().split())) for i in range(n)))

points.sort(key=lambda points: points[0])


def dd(a, b):
    return (a[0] - b[0]) ** 2 + (a[1] - b[1]) ** 2


def getD(left, right):
    if right == left + 1:

        return dd(points[left], points[right])
    elif right == left + 2:
        return min(dd(points[left], points[left + 1]), dd(points[left + 1], points[right]),
                   dd(points[left], points[right]))
    else:
        mid = (left + right) // 2
        d = min(getD(left, mid), getD(mid, right))
        stan = points[mid][0]

        boundary = []
        for i in range(left, right + 1):
            if (points[i][0] - stan) ** 2 <= d:
                boundary.append(points[i])

        boundary.sort(key=lambda boundary: boundary[1])
        for i in range(len(boundary) - 1):
            j = i + 1
            while j < len(boundary) and (boundary[j][1] - boundary[i][1]) ** 2 < d:
                temp = dd(boundary[i], boundary[j])
                if temp < d:
                    d = temp
                j += 1
        return d


if len(points) != n:
    print(0)
else:
    print(getD(0, n - 1))

