import sys

n, m = map(int, sys.stdin.readline().split())

board = [sys.stdin.readline().strip() for _ in range(n)]
red = None
blue = None

for i in range(n):
    for j in range(m):
        if board[i][j] == 'R':
            red = [i, j]
        elif board[i][j] == 'B':
            blue = [i, j]


def tilt(red, blue, d):
    newR = red[:]
    newB = blue[:]
    direction = [[0, 0], [-1, 0], [0, 1], [1, 0], [0, -1]]
    result = []
    if (d == 1 or d == 3) and newR[1] == newB[1]:
        out = False
        if d == 1:
            if newR[0] > newB[0]:
                # 블루 먼저
                # out = False
                while board[newB[0] + direction[d][0]][newB[1]] != '#':
                    newB[0] += direction[d][0]
                    # newB[1] += direction[d][1]
                    if board[newB[0]][newB[1]] == 'O':
                        out = True
                        break
                while board[newR[0] + direction[d][0]][newR[1]] != '#' and (newB[0] < newR[0] + direction[d][0] or out):
                    newR[0] += direction[d][0]
                    # newR[1] += direction[d][1]
                    if board[newR[0]][newR[1]] == 'O':
                        break
                result.append(newR)
                result.append(newB)
            else:
                # 레드 먼저
                # out = False
                while board[newR[0] + direction[d][0]][newR[1]] != '#':
                    newR[0] += direction[d][0]
                    # newR[1] += direction[d][1]
                    if board[newR[0]][newR[1]] == 'O':
                        out = True
                        break
                while board[newB[0] + direction[d][0]][newB[1]] != '#' and (newR[0] < newB[0] + direction[d][0] or out):
                    newB[0] += direction[d][0]
                    # newB[1] += direction[d][1]
                    if board[newB[0]][newB[1]] == 'O':
                        break
                result.append(newR)
                result.append(newB)
        else:
            if newR[0] > newB[0]:
                # 레드 먼저
                # out = False
                while board[newR[0] + direction[d][0]][newR[1]] != '#':
                    newR[0] += direction[d][0]
                    # newR[1] += direction[d][1]
                    if board[newR[0]][newR[1]] == 'O':
                        out = True
                        break
                while board[newB[0] + direction[d][0]][newB[1]] != '#' and (newR[0] > newB[0] + direction[d][0] or out):
                    newB[0] += direction[d][0]
                    # newB[1] += direction[d][1]
                    if board[newB[0]][newB[1]] == 'O':
                        break

                result.append(newR)
                result.append(newB)
            else:
                # 블루 먼저
                # out = False
                while board[newB[0] + direction[d][0]][newB[1]] != '#':
                    newB[0] += direction[d][0]
                    # newB[1] += direction[d][1]
                    if board[newB[0]][newB[1]] == 'O':
                        out = True
                        break
                while board[newR[0] + direction[d][0]][newR[1]] != '#' and (newB[0] > newR[0] + direction[d][0] or out):
                    newR[0] += direction[d][0]
                    # newR[1] += direction[d][1]
                    if board[newR[0]][newR[1]] == 'O':
                        break
                result.append(newR)
                result.append(newB)
    elif (d == 2 or d == 4) and newR[0] == newB[0]:
        out = False
        if d == 2:
            if newR[1] > newB[1]:
                # 레드 먼저
                # out = False
                while board[newR[0]][newR[1] + direction[d][1]] != '#':
                    # newR[0] += direction[d][0]
                    newR[1] += direction[d][1]
                    if board[newR[0]][newR[1]] == 'O':
                        out = True
                        break

                while board[newB[0]][newB[1] + direction[d][1]] != '#' and (newR[1] > newB[1] + direction[d][1] or out):
                    # newB[0] += direction[d][0]
                    newB[1] += direction[d][1]
                    if board[newB[0]][newB[1]] == 'O':
                        break
                result.append(newR)
                result.append(newB)
            else:
                # 블루 먼저
                # out = False
                while board[newB[0]][newB[1] + direction[d][1]] != '#':
                    # newB[0] += direction[d][0]
                    newB[1] += direction[d][1]
                    if board[newB[0]][newB[1]] == 'O':
                        out = True
                        break

                while board[newR[0]][newR[1] + direction[d][1]] != '#' and (newB[1] > newR[1] + direction[d][1] or out):
                    # newR[0] += direction[d][0]
                    newR[1] += direction[d][1]
                    if board[newR[0]][newR[1]] == 'O':
                        break
                result.append(newR)
                result.append(newB)
        else:
            if newR[1] > newB[1]:
                # 블루 먼저
                while board[newB[0]][newB[1] + direction[d][1]] != '#':
                    # newB[0] += direction[d][0]
                    newB[1] += direction[d][1]
                    if board[newB[0]][newB[1]] == 'O':
                        out = True
                        break
                while board[newR[0]][newR[1] + direction[d][1]] != '#' and (newB[1] < newR[1] + direction[d][1] or out):
                    # newR[0] += direction[d][0]
                    newR[1] += direction[d][1]
                    if board[newR[0]][newR[1]] == 'O':
                        break
                result.append(newR)
                result.append(newB)
            else:
                # 레드 먼저
                while board[newR[0]][newR[1] + direction[d][1]] != '#':
                    # newR[0] += direction[d][0]
                    newR[1] += direction[d][1]
                    if board[newR[0]][newR[1]] == 'O':
                        out = True
                        break

                while board[newB[0]][newB[1] + direction[d][1]] != '#' and (newR[1] < newB[1] + direction[d][1] or out):
                    # newB[0] += direction[d][0]
                    newB[1] += direction[d][1]
                    if board[newB[0]][newB[1]] == 'O':
                        break
                result.append(newR)
                result.append(newB)
    else:
        while board[newR[0] + direction[d][0]][newR[1] + direction[d][1]] != '#':
            newR[0] += direction[d][0]
            newR[1] += direction[d][1]
            if board[newR[0]][newR[1]] == 'O':
                break

        while board[newB[0] + direction[d][0]][newB[1] + direction[d][1]] != '#':
            newB[0] += direction[d][0]
            newB[1] += direction[d][1]
            if board[newB[0]][newB[1]] == 'O':
                break
        result.append(newR)
        result.append(newB)
    return result


visitedR = [[0 for _ in range(m)] for _ in range(n)]
visitedB = [[0 for _ in range(m)] for _ in range(n)]

visitedR[red[0]][red[1]] = 1
visitedB[blue[0]][blue[1]] = 1
nextV = [[red, blue]]
tcount = 0
check = False
while nextV and tcount < 10:
    temp = []
    tcount += 1
    for i in nextV:
        for j in range(1, 5):
            k = tilt(i[0], i[1], j)
            if visitedR[k[0][0]][k[0][1]] == 0 or visitedB[k[1][0]][k[1][1]] == 0:
                if board[k[0][0]][k[0][1]] == 'O' and board[k[1][0]][k[1][1]] != 'O':
                    check = True
                    break
                elif board[k[1][0]][k[1][1]] != 'O':
                    temp.append(k)
                    visitedR[k[0][0]][k[0][1]] = 1
                    visitedB[k[1][0]][k[1][1]] = 1
        if check:
            break
    if check:
        print(tcount)
        break
    else:
        nextV = temp

if check == False:
    print(-1)

