import sys
N, M = map(int, sys.stdin.readline().split())
glacier = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

# 빙산 개수 카운팅, 빙산 개수 카운팅하면서, 빙산의 각 칸과 인접한 물의 수 구함
# 빙산 개수 >= 2면 루프 끝내고 값 출력, 아니면, 계속 진행

def get_neighbor(i, j):
    temp = []
    temp_ = 0
    if i - 1 >= 0:
        if glacier[i-1][j] > 0:
            if visited[i-1][j] == 0:
                temp.append([i-1, j])
        else:
            if melt[i-1][j] == 0:
                temp_ += 1
    if i + 1 < N:
        if glacier[i+1][j] > 0:
            if visited[i+1][j] == 0:
                temp.append(([i+1, j]))
        else:
            if melt[i+1][j] == 0:
                temp_ += 1
    if j - 1 >= 0:
        if glacier[i][j-1] > 0:
            if visited[i][j-1] == 0:
                temp.append([i, j-1])
        else:
            if melt[i][j-1] == 0:
                temp_ += 1
    if j + 1 < M:
        if glacier[i][j+1] > 0:
            if visited[i][j+1] == 0:
                temp.append([i, j+1])
        else:
            if melt[i][j+1] == 0:
                temp_ += 1
    return [temp, temp_]

def bfs(a, b):
    go = [[a, b]]
    result = [[a, b]]
    while go:
        temp = []
        for i, j in go:
            neighbors, melt_ = get_neighbor(i, j)
            for k, l in neighbors:
                visited[k][l] = 1
                temp.append([k, l ])
                result.append([k, l])
            melt[i][j] = melt_
            if glacier[i][j] >= melt_:
                glacier[i][j] -= melt_
            else:
                glacier[i][j] = 0

        go = temp

    return result

result = 0
success = False
while True:
    visited = [[0 for _ in range(M)] for _ in range(N)]
    melt = [[0 for _ in range(M)] for _ in range(N)]
    to_melt = []
    for i in range(N):
        for j in range(M):
            if glacier[i][j] > 0 and visited[i][j] == 0:
                visited[i][j] = 1
                to_melt.append(bfs(i, j))

    if len(to_melt) >= 2:
        success = True
        break
    result += 1

    # for ice in to_melt:
    #     for i, j in ice:
    #         if glacier[i][j] >= melt[i][j]:
    #             glacier[i][j] -= melt[i][j]
    #         else:
    #             glacier[i][j] = 0


if success:
    print(result)
else:
    print(0)

