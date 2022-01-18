import sys

rc = [list(map(int, sys.stdin.readline().split())) for i in range(3)]
xs = []
ys = []
for i in rc:
    xs.append(i[0])
    ys.append(i[1])

rx = None
ry = None
for i in range(3):
    if xs.count(xs[i]) == 1:
        rx = xs[i]
    if ys.count(ys[i]) == 1:
        ry = ys[i]
print('%d %d' % (rx, ry))

