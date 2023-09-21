import sys

n, m, x, y, k = map(int, sys.stdin.readline().split())

area = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

cmd = list(map(int, sys.stdin.readline().split()))

dir = [[0, 0], [0, 1], [0, -1], [-1, 0], [1, 0]]


class Dice():
    def __init__(self):
        self.up = 0
        self.bottom = 0
        self.right = 0
        self.left = 0
        self.front = 0
        self.back = 0

    def roll(self, d):

        if d == 3 or d == 4:
            if d == 3:
                self.up, self.front, self.bottom, self.back = self.front, self.bottom, self.back, self.up
            else:
                self.up, self.front, self.bottom, self.back = self.back, self.up, self.front, self.bottom
        else:
            if d == 1:
                self.up, self.right, self.bottom, self.left = self.left, self.up, self.right, self.bottom
            else:
                self.up, self.right, self.bottom, self.left = self.right, self.bottom, self.left, self.up


dice = Dice()

for i in cmd:
    if -1 < x + dir[i][0] < n and -1 < y + dir[i][1] < m:
        dice.roll(i)
        x += dir[i][0]
        y += dir[i][1]
        if area[x][y] == 0:
            area[x][y] = dice.bottom
        else:
            dice.bottom = area[x][y]
            area[x][y] = 0
        print(dice.up)
