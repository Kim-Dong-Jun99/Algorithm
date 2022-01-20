import sys
c = [list(map(int,sys.stdin.readline().split())) for _ in range(3)]
def ccw(c):
    return (c[0][0]*c[1][1] + c[1][0]*c[2][1] + c[2][0]*c[0][1]) - (c[1][0]*c[0][1] + c[2][0]*c[1][1] + c[0][0]*c[2][1])

result = ccw(c)
if result > 0:
    print(1)
elif result < 0:
    print(-1)
else:
    print(0)

