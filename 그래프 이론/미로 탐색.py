import sys

n, m = map(int, sys.stdin.readline().split())
maze = [sys.stdin.readline().strip() for i in range(n)]
nextV = [[0, 0]]
visited = [[0, 0]]
blocks = 1


def possible(i, j):
    result = []
    if i - 1 > -1:
        if maze[i - 1][j] == '1':
            result.append([i - 1, j])
    if j - 1 > -1:
        if maze[i][j - 1] == '1':
            result.append([i, j - 1])
    if i + 1 < len(maze):
        if maze[i + 1][j] == '1':
            result.append([i + 1, j])
    if j + 1 < len(maze[0]):
        if maze[i][j + 1] == '1':
            result.append([i, j + 1])

    return result


while ([n - 1, m - 1] not in nextV):
    temp = []
    for i in nextV:
        for j in possible(i[0], i[1]):
            if (j not in visited):
                temp.append(j)
                visited.append(j)
    nextV = temp
    blocks += 1
print(blocks)