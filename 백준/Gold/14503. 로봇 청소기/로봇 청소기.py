import sys

n, m = map(int, sys.stdin.readline().split())
x, y, d = map(int, sys.stdin.readline().split())
room = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
dir = [[-1, 0], [0, 1], [1, 0], [0, -1]]
clean = 0
while True:
    # print('%d %d'%(x,y))
    if room[x][y] == 0:
        clean += 1
        room[x][y] = -1
    check = True
    for i in range(1, 5):
        if -1 < x + dir[d - i][0] < n and -1 < y + dir[d - i][1] < m and room[x + dir[d - i][0]][y + dir[d - i][1]] == 0:
            x += dir[d - i][0]
            y += dir[d - i][1]
            check = False
            d -= i
            if d < 0:
                d += 4
            break
    if check:
        if -1 < x - dir[d][0] < n and -1 < y-dir[d][1] < m and room[x-dir[d][0]][y-dir[d][1]] != 1:
            x -= dir[d][0]
            y -= dir[d][1]
        else:
            break

print(clean)

