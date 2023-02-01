import sys
from collections import defaultdict


def can_go(i, j):
    result = []
    if j - 1 >= 0 and maps[i][j-1] == 1:
        result.append([i, j - 1])
    if j + 1 < w and maps[i][j+1] == 1:
        result.append([i, j + 1])
    if i - 1 >= 0:
        if maps[i-1][j] == 1:
            result.append([i - 1, j])
        if j - 1 >= 0 and maps[i - 1][j - 1] == 1:
            result.append([i - 1, j - 1])
        if j + 1 < w and maps[i - 1][j + 1]:
            result.append([i - 1, j + 1])
    if i + 1 < h:
        if maps[i + 1][j] == 1:
            result.append([i + 1, j])
        if j - 1 >= 0 and maps[i + 1][j - 1] == 1:
            result.append([i + 1, j - 1])
        if j + 1 < w and maps[i + 1][j + 1] == 1:
            result.append([i + 1, j + 1])
    return result


def bfs(go):
    while go:
        temp = []
        for i, j in go:
            for k,l in can_go(i, j):
                if visited[k][l] == 0:
                    visited[k][l] = 1
                    temp.append([k, l])
        go = temp


while True:
    w, h = map(int, sys.stdin.readline().split())
    if w == 0 and h == 0:
        break
    maps = [list(map(int, sys.stdin.readline().split())) for _ in range(h)]
    visited = [[0 for _ in range(w)] for _ in range(h)]
    result = 0
    for i in range(h):
        for j in range(w):
            if maps[i][j] == 1 and visited[i][j] == 0:
                result += 1
                visited[i][j] = 1
                bfs([[i, j]])

    print(result)
