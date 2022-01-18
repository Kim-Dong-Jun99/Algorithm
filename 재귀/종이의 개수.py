import sys

N = int(sys.stdin.readline())
paper = [list(map(int, sys.stdin.readline().split())) for i in range(N)]
result = [0, 0, 0]


def div(x: int, y: int, n: int):
    if check(x, y, n):
        result[paper[x][y] + 1] += 1
    else:
        for i in range(3):
            for j in range(3):
                div(x + i * (n // 3), y + j * (n // 3), n // 3)


def check(x: int, y: int, n: int):
    temp = paper[x][y]
    for i in range(n):
        for j in range(n):
            if temp != paper[x + i][y + j]:
                return False
    return True


div(0, 0, N)
for i in result:
    print(i)