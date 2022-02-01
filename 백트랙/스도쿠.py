import sys

sdk = [list(map(int, sys.stdin.readline().split())) for _ in range(9)]

empty = []
for i in range(9):
    for j in range(9):
        if sdk[i][j] == 0:
            empty.append([i, j])


def check(i, j):
    comp = set([1, 2, 3, 4, 5, 6, 7, 8, 9])
    ni = (i // 3) * 3
    nj = (j // 3) * 3
    temp = [[ni, nj], [ni, nj + 1], [ni, nj + 2], [ni + 1, nj], [ni + 1, nj + 1], [ni + 1, nj + 2], [ni + 2, nj],
            [ni + 2, nj + 1], [ni + 2, nj + 2]]
    for k in range(9):
        if sdk[k][j] in comp:
            comp.remove(sdk[k][j])
        if sdk[i][k] in comp:
            comp.remove(sdk[i][k])
        if sdk[temp[k][0]][temp[k][1]] in comp:
            comp.remove(sdk[temp[k][0]][temp[k][1]])
    return list(comp)


def bt(i):
    if i == len(empty):
        for j in range(9):
            for k in range(9):
                print(sdk[j][k], end=' ')
            print()
        sys.exit()
    else:
        for j in check(empty[i][0], empty[i][1]):
            sdk[empty[i][0]][empty[i][1]] = j
            bt(i + 1)
            sdk[empty[i][0]][empty[i][1]] = 0


bt(0)
