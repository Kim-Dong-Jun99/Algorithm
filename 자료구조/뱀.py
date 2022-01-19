import sys
from collections import deque

n = int(sys.stdin.readline())
apples = [[0 for i in range(n)] for j in range(n)]
m = int(sys.stdin.readline())
for _ in range(m):
    i = list(map(int, sys.stdin.readline().split()))
    apples[i[0] - 1][i[1] - 1] = 1
m = int(sys.stdin.readline())
turns = [list(sys.stdin.readline().split()) for i in range(m)]

cp = [0, 0]
q = deque()

second = 0
direction = 'r'


def rotate(cd, r):
    if r == 'D':
        if cd == 'r':
            return 'd'
        elif cd == 'l':
            return 'u'
        elif cd == 'u':
            return 'r'
        elif cd == 'd':
            return 'l'
    else:
        if cd == 'r':
            return 'u'
        elif cd == 'l':
            return 'd'
        elif cd == 'u':
            return 'l'
        elif cd == 'd':
            return 'r'


while True:
    q.append(cp[:])
    if direction == 'r':
        cp[1] += 1
    elif direction == 'l':
        cp[1] -= 1
    elif direction == 'u':
        cp[0] -= 1
    elif direction == 'd':
        cp[0] += 1
    second += 1

    if cp[0] < 0 or cp[0] > n - 1 or cp[1] < 0 or cp[1] > n - 1:
        print(second)
        break
    else:
        if ([cp[0], cp[1]] in q):
            print(second)
            break
        if apples[cp[0]][cp[1]] != 1:
            q.popleft()
        else:
            apples[cp[0]][cp[1]] = 0

    if turns and second == int(turns[0][0]):
        direction = rotate(direction, turns[0][1])
        turns.pop(0)



