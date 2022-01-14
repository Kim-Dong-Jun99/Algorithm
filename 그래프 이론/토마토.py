import sys

m, n = map(int, sys.stdin.readline().split())
tomatoes = [list(map(int, sys.stdin.readline().split())) for i in range(n)]
nextV = []
day = 0
for i in range(n):
    for j in range(m):
        if tomatoes[i][j] == 1:
            nextV.append([i, j])


def ps(tomatoes, i, j):
    result = []
    if i - 1 > -1:
        if tomatoes[i - 1][j] == 0:
            result.append([i - 1, j])
    if i + 1 < len(tomatoes):
        if tomatoes[i + 1][j] == 0:
            result.append([i + 1, j])
    if j - 1 > -1:
        if tomatoes[i][j - 1] == 0:
            result.append([i, j - 1])
    if j + 1 < len(tomatoes[0]):
        if tomatoes[i][j + 1] == 0:
            result.append([i, j + 1])
    return result


while True:
    temp = []
    for i in nextV:
        for j in ps(tomatoes, i[0], i[1]):
            temp.append(j[:])
            tomatoes[j[0]][j[1]] = 1
    nextV = temp
    if nextV == []:
        break
    day += 1

check = True
for i in range(n):
    for j in range(m):
        if tomatoes[i][j] == 0:
            check = False
            break
    if check == False:
        break
if check:
    print(day)
else:
    print(-1)
