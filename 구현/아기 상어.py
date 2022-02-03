import sys

n = int(sys.stdin.readline())

sea = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]


def cango(i, j, bs):
    result = []
    if i - 1 > -1 and sea[i - 1][j] <= bs:
        result.append([i - 1, j])
    if i + 1 < n and sea[i + 1][j] <= bs:
        result.append([i + 1, j])
    if j - 1 > -1 and sea[i][j - 1] <= bs:
        result.append([i, j - 1])
    if j + 1 < n and sea[i][j + 1] <= bs:
        result.append([i, j + 1])
    return result


for i in range(n):
    for j in range(n):
        if sea[i][j] == 9:
            bs = 2
            eaten = 0
            nextV = [[i, j]]
            tt = 0
            sea[i][j] = 0
            while True:
                visited = [[0 for _ in range(n)] for _ in range(n)]
                visited[nextV[0][0]][nextV[0][1]] = 1
                cantmove = True
                eatnext = None
                move = 0
                while nextV:
                    temp = []
                    for k in nextV:
                        for l in cango(k[0], k[1], bs):
                            if visited[l[0]][l[1]] == 0:
                                if sea[l[0]][l[1]] > 0 and sea[l[0]][l[1]] != bs:
                                    cantmove = False
                                    if eatnext == None or l[0] < eatnext[0] or (l[0] == eatnext[0] and l[1] < eatnext[1]):
                                        eatnext = l
                                temp.append(l)
                                visited[l[0]][l[1]] = 1
                    nextV = temp
                    move += 1
                    if cantmove == False:
                        break
                if cantmove:
                    print(tt)
                    break
                else:
                    sea[eatnext[0]][eatnext[1]] = 0
                    tt += move
                    nextV = [eatnext]
                    eaten += 1
                    if eaten == bs:
                        bs += 1
                        eaten = 0
            break