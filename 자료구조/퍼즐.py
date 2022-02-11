import sys

puzzle = [list(map(int, sys.stdin.readline().split())) for _ in range(3)]

for i in range(3):
    for j in range(3):
        if puzzle[i][j] == 0:
            cur = [i, j]
            break


def cango(i, j):
    result = []
    if i - 1 > -1:
        result.append([i - 1, j])
    if i + 1 < 3:
        result.append([i + 1, j])
    if j - 1 > -1:
        result.append([i, j - 1])
    if j + 1 < 3:
        result.append([i, j + 1])
    return result


def getnp(i, j, k, l, p):
    np = [list(p[0]), list(p[1]), list(p[2])]
    result = []
    for _ in range(3):
        result.append(np[_][:])
    result[i][j], result[k][l] = result[k][l], result[i][j]
    return (tuple(result[0]), tuple(result[1]), tuple(result[2]))


def checkD(pz):
    if pz[0] == (1, 2, 3) and pz[1] == (4, 5, 6) and pz[2] == (7, 8, 0):
        return True
    return False


nextV = []
a = tuple(puzzle[0])
b = tuple(puzzle[1])
c = tuple(puzzle[2])
puzzle = (a, b, c)
nextV.append([cur, puzzle])
count = 1
check = False
visited = {}
visited[puzzle] = 1
if checkD(nextV[0][1]):
    print(0)
else:
    while nextV:
        temp = []

        for i in nextV:
            for j in cango(i[0][0], i[0][1]):
                np = getnp(i[0][0], i[0][1], j[0], j[1], i[1])
                if visited.get(np, -1) == -1:
                    if checkD(np):
                        print(count)
                        check = True
                        break
                    temp.append([j, np])
                    visited[np] = 1
            if check:
                    break
        if check:
            break
        count += 1
        nextV = temp
    if check == False:
        print(-1)