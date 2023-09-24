import sys


def get_neighbor_2(i, j):
    temp = []
    if i + 1 < N and visited[i + 1][j] == 0:
        bottom = abs(population[i][j] - population[i + 1][j])
        if L <= bottom and bottom <= R:
            temp.append([i + 1, j])
    if j + 1 < N and visited[i][j + 1] == 0:
        right = abs(population[i][j] - population[i][j + 1])
        if L <= right and right <= R:
            temp.append([i, j + 1])
    return temp


def get_neighbor_4(i, j):
    temp = []
    if i - 1 >= 0 and visited[i - 1][j] == 0:
        above = abs(population[i][j] - population[i - 1][j])
        if L <= above and above <= R:
            temp.append([i - 1, j])
    if i + 1 < N and visited[i + 1][j] == 0:
        bottom = abs(population[i][j] - population[i + 1][j])
        if L <= bottom and bottom <= R:
            temp.append([i + 1, j])
    if j - 1 >= 0 and visited[i][j - 1] == 0:
        left = abs(population[i][j] - population[i][j - 1])
        if L <= left and left <= R:
            temp.append([i, j - 1])
    if j + 1 < N and visited[i][j + 1] == 0:
        right = abs(population[i][j] - population[i][j + 1])
        if L <= right and right <= R:
            temp.append([i, j + 1])
    return temp


def bfs(a, b):
    result = [[a, b]]
    go = [[a, b]]
    while go:
        temp = []
        for i, j in go:
            for k, l in get_neighbor_4(i, j):
                temp.append([k, l])
                result.append([k, l])
                visited[k][l] = 1
        go = temp
    return result


N, L, R = map(int, sys.stdin.readline().split())

population = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
result = 0
while True:
    # 인구 이동이 필요한지 확인 -> 연합 생성
    # 연합 생성 안됐으면 loop 나감
    # 연합 생성됐으면 인구수 맞추기
    visited = [[0 for _ in range(N)] for _ in range(N)]
    unions = []
    for i in range(N):
        for j in range(N):
            if visited[i][j] == 0 and len(get_neighbor_2(i, j)) > 0:
                visited[i][j] = 1
                unions.append(bfs(i, j))

    if len(unions) == 0:
        break

    result += 1
    for cities in unions:
        # print(cities)
        city_sum = 0
        for i, j in cities:
            city_sum += population[i][j]
        city_sum = int(city_sum / len(cities))
        for i, j in cities:
            population[i][j] = city_sum
    # print(population)

print(result)
