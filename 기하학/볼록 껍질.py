import sys

n = int(sys.stdin.readline())
points = []
minY = None
for i in range(n):
    query = list(map(int, sys.stdin.readline().split()))
    points.append(query)
    if minY == None:
        minY = query
    elif minY[1] > query[1]:
        minY = query
    elif minY[1] == query[1] and minY[0] > query[0]:
        minY = query


def calcangle(stan, compare):
    dx = compare[0] - stan[0]
    dy = compare[1] - stan[1]
    if dx >= 0 and dy == 0:
        return 0
    else:
        angle = abs(dy) / (abs(dx) + abs(dy))
        if dx < 0 and dy >= 0:
            angle = 2 - angle
        elif dx <= 0 and dy < 0:
            angle = 2 + angle
        elif dx > 0 and dy < 0:
            angle = 3 + angle
        return angle * 90


points.sort(key=lambda points: calcangle(minY, points))


def ccw(a, b, c):
    return (a[0] * b[1] + b[0] * c[1] + c[0] * a[1]) - (b[0] * a[1] + c[0] * b[1] + a[0] * c[1])


# 양수면 CCW

jmarch = [points[0], points[1], points[2]]
if ccw(jmarch[0],jmarch[1],jmarch[2]) == 0:
    jmarch.pop(1)
for i in range(3, n):
    while True:
        check = ccw(jmarch[-2], jmarch[-1], points[i])
        if check == 0:
            jmarch.pop()
            jmarch.append(points[i])
            break
        elif check > 0:
            jmarch.append(points[i])
            break
        else:
            jmarch.pop()
print(len(jmarch))

