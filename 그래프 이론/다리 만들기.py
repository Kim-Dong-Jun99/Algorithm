import sys


def is_edge(i, j):
    if i - 1 >= 0 and map[i - 1][j] == 0:
        return True
    if i + 1 < N and map[i + 1][j] == 0:
        return True
    if j - 1 >= 0 and map[i][j - 1] == 0:
        return True
    if j + 1 < N and map[i][j + 1] == 0:
        return True
    return False


def can_go(i, j):
    temp = []
    if i - 1 >= 0 and visited[i - 1][j] == 0 and map[i - 1][j] == 1:
        temp.append([i - 1, j])
    if i + 1 < N and visited[i + 1][j] == 0 and map[i + 1][j] == 1:
        temp.append([i + 1, j])
    if j - 1 >= 0 and visited[i][j - 1] == 0 and map[i][j - 1] == 1:
        temp.append([i, j - 1])
    if j + 1 < N and visited[i][j + 1] == 0 and map[i][j + 1] == 1:
        temp.append([i, j + 1])
    return temp


def can_go_bridge(i, j):
    temp = []
    if i - 1 >= 0 and island_visited[i - 1][j] == 0:
        temp.append([i - 1, j])
    if i + 1 < N and island_visited[i + 1][j] == 0:
        temp.append([i + 1, j])
    if j - 1 >= 0 and island_visited[i][j - 1] == 0:
        temp.append([i, j - 1])
    if j + 1 < N and island_visited[i][j + 1] == 0:
        temp.append([i, j + 1])
    return temp


def smaller(i, j):
    if i < j:
        return i
    return j


def bfs(a, b):
    go = [[a, b]]
    # island.add((a, b))
    visited[a][b] = 1
    island_visited[a][b] = 1
    while go:
        temp = []
        for i, j in go:
            if is_edge(i, j):
                edge.append([i, j])
            for k, l in can_go(i, j):
                # island.add((k, l))
                island_visited[k][l] = 1
                visited[k][l] = 1
                temp.append([k, l])
        go = temp


N = int(sys.stdin.readline())

map = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
visited = [[0] * N for _ in range(N)]
result = 100000
for i in range(N):
    for j in range(N):
        if map[i][j] == 1 and visited[i][j] == 0:
            # island = set()
            island_visited = [[0] * N for _ in range(N)]
            edge = []
            bfs(i, j)
            temp_result = 0
            done = False
            while edge:
                temp = []
                for i_, j_ in edge:
                    for k_, l_ in can_go_bridge(i_, j_):
                        if map[k_][l_] == 1:
                            done = True
                        island_visited[k_][l_] = 1
                        temp.append([k_, l_])
                edge = temp
                if done:
                    break
                temp_result += 1

            result = smaller(temp_result, result)

print(result)