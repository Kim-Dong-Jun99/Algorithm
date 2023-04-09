def solution(maps):
    """
    16:18
    """
    cur = []
    lever = []
    dest = []
    visited = [[0 for _ in range(len(maps[0]))] for _ in range(len(maps))]
    for i in range(len(maps)):
        for j in range(len(maps[0])):
            if maps[i][j] == "S":
                cur.append([i, j])
                visited[i][j] = 1
            if maps[i][j] == "L":
                lever = [i, j]
            if maps[i][j] == "E":
                dest = [i, j]
    isDone = False
    answer = 0
    while cur:
        temp = []
        for i,j in cur:
            for x, y in can_go(i,j,len(maps),len(maps[0]),maps):
                if visited[x][y] == 0:
                    visited[x][y] = 1
                    temp.append([x, y])
        cur = temp
        answer += 1
        if visited[lever[0]][lever[1]] == 1:
            isDone = True
            break
    if isDone == False:
        return -1
    visited = [[0 for _ in range(len(maps[0]))] for _ in range(len(maps))]
    cur = [lever]
    isDone = False
    while cur:
        temp = []
        for i,j in cur:
            for x, y in can_go(i,j,len(maps),len(maps[0]),maps):
                if visited[x][y] == 0:
                    visited[x][y] = 1
                    temp.append([x, y])
        cur = temp
        answer += 1
        if visited[dest[0]][dest[1]] == 1:
            isDone = True
            break
    if isDone == False:
        return -1
    
                
    return answer

def can_go(i, j, n, m, maps):
    result = []
    if i + 1 < n and maps[i+1][j] != "X":
        result.append([i+1, j])
    if i - 1 >= 0 and maps[i-1][j] != "X":
        result.append([i-1,j])
    if j - 1 >= 0 and maps[i][j-1] != "X":
        result.append([i, j-1])
    if j + 1 < m and maps[i][j+1] != "X":
        result.append([i, j+1])
    return result