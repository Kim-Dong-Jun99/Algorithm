import sys

def can_go_air(i, j):
    temp = []
    if i - 1 >= 0 and cheese[i-1][j] == 0 and air_visited[i-1][j] == 0:
        temp.append([i-1, j])
    if i + 1 < R and cheese[i+1][j] == 0 and air_visited[i+1][j] == 0:
        temp.append([i+1, j])
    if j - 1 >= 0 and cheese[i][j-1] == 0 and air_visited[i][j-1] == 0:
        temp.append([i, j-1])
    if j + 1 < C and cheese[i][j+1] == 0 and air_visited[i][j+1] == 0:
        temp.append([i, j+1])
    return temp

def is_edge(i, j):
    if i - 1 >= 0 and cheese[i-1][j] == 0 and air_visited[i-1][j] == 1:
        return True
    if i + 1 < R and cheese[i+1][j] == 0 and air_visited[i+1][j] == 1:
        return True
    if j - 1 >= 0 and cheese[i][j-1] == 0 and air_visited[i][j-1] == 1:
        return True
    if j + 1 < C and cheese[i][j+1] == 0 and air_visited[i][j+1] == 1:
        return True
    return False

R, C = map(int, sys.stdin.readline().split())
cheese = [list(map(int, sys.stdin.readline().split())) for _ in range(R)]
time = 0
result = []
while True:
    air_visited = [[0] * C for _ in range(R)]

    go = [[0, 0]]
    while go:
        temp = []
        for i, j in go:
            for k, l in can_go_air(i, j):
                air_visited[k][l] = 1
                temp.append([k, l])
        go = temp
    edge = []
    for i in range(R):
        for  j in range(C):
            if cheese[i][j] == 1 and is_edge(i, j):
                edge.append([i, j])

    if len(edge) == 0:
        print(time)
        print(result[-1])
        break
    result.append(len(edge))
    time += 1
    for i, j in edge:
        cheese[i][j] = 0