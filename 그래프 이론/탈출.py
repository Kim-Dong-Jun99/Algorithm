import sys


def get_neighbor_racoon(i, j):
    temp = []
    if i - 1 >= 0 and graph[i - 1][j] != 'X' and water_visited[i - 1][j] == 0 and racoon_visited[i-1][j] == 0:
        temp.append([i - 1, j])
    if i + 1 < R and graph[i + 1][j] != 'X' and water_visited[i + 1][j] == 0 and racoon_visited[i+1][j] == 0:
        temp.append([i + 1, j])
    if j - 1 >= 0 and graph[i][j - 1] != 'X' and water_visited[i][j - 1] == 0 and racoon_visited[i][j-1] == 0:
        temp.append([i, j - 1])
    if j + 1 < C and graph[i][j + 1] != 'X' and water_visited[i][j + 1] == 0 and racoon_visited[i][j+1] == 0:
        temp.append([i, j + 1])
    return temp


def get_neighbor_water(i, j):
    temp = []
    if i - 1 >= 0 and graph[i - 1][j] == '.':
        temp.append([i - 1, j])
    if i + 1 < R and graph[i + 1][j] == '.':
        temp.append([i + 1, j])
    if j - 1 >= 0 and graph[i][j - 1] == '.':
        temp.append([i, j - 1])
    if j + 1 < C and graph[i][j + 1] == '.':
        temp.append([i, j + 1])
    return temp


R, C = map(int, sys.stdin.readline().split())

graph = [sys.stdin.readline().rstrip() for _ in range(R)]
edges = []

racoon_visited = [[0 for _ in range(C)] for _ in range(R)]
water_visited = [[0 for _ in range(C)] for _ in range(R)]
d_r, d_c, s_r, s_c = 0, 0, 0, 0
for i in range(R):
    for j in range(C):
        if graph[i][j] == 'D':
            d_r, d_c = i, j
        elif graph[i][j] == 'S':
            s_r, s_c = i, j
        elif graph[i][j] == '*':
            water_visited[i][j] = 1
            if len(get_neighbor_water(i, j)) > 0:
                edges.append([i, j])

result = 0
go = [[s_r, s_c]]
racoon_visited[s_r][s_c] = 1
done = False
while go:
    if racoon_visited[d_r][d_c] == 1:
        done = True
        break
    result += 1
    temp_edges = []
    for i, j in edges:
        for k, l in get_neighbor_water(i, j):
            if water_visited[k][l] == 0:
                water_visited[k][l] = 1
                temp_edges.append([k, l])
    edges = temp_edges
    temp_go = []
    for i, j in go:
        for k, l in get_neighbor_racoon(i, j):
            racoon_visited[k][l] = 1
            temp_go.append([k, l])
    go = temp_go

if done:
    print(result)
else:
    print("KAKTUS")
