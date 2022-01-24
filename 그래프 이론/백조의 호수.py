import sys

def checkmelt(i,j):
    if i-1 > -1:
        if lake[i-1][j] == '.' or lake[i-1][j] == 'L':
            return True
    if j-1 > -1:
        if lake[i][j-1] == '.' or lake[i][j-1] == 'L':
            return True
    if i+1 < n:
        if lake[i+1][j] == '.' or lake[i+1][j] == 'L':
            return True
    if j+1 < m:
        if lake[i][j+1] == '.' or lake[i][j+1] == 'L':
            return True
    return False

def meltnext(i,j):
    result = []
    if i-1 > -1:
        if lake[i-1][j] == 'X':
            result.append([i-1,j])
    if j-1 > -1:
        if lake[i][j-1] == 'X':
            result.append([i,j-1])
    if i+1 < n:
        if lake[i+1][j] == 'X':
            result.append([i+1,j])
    if j+1 < m:
        if lake[i][j+1] == 'X':
            result.append([i,j+1])
    return result

def cango(i,j):
    result = []
    if i - 1 > -1:
        if lake[i - 1][j] == '.' or lake[i-1][j] == 'L':
            result.append([i - 1, j])
    if j - 1 > -1:
        if lake[i][j - 1] == '.' or lake[i][j-1] == 'L':
            result.append([i, j - 1])
    if i + 1 < n:
        if lake[i + 1][j] == '.' or lake[i+1][j] == 'L':
            result.append([i + 1, j])
    if j + 1 < m:
        if lake[i][j + 1] == '.' or lake[i][j+1] == 'L':
            result.append([i, j + 1])
    return result

n,m = map(int,sys.stdin.readline().split())
lake = [list(sys.stdin.readline().strip()) for i in range(n)]
melt = []
swan = []

for i in range(n):
    for j in range(m):
        if lake[i][j] == 'X':
            if checkmelt(i,j):
                melt.append([i,j])
        elif lake[i][j] == 'L':
            swan.append([i,j])
days = 0

nextmelt = [[0 for m_itr in range(m)] for n_itr in range(n)]
visited = [[0 for i_itr in range(m)] for j_itr in range(n)]
nextV = [swan[0]]
visited[swan[0][0]][swan[0][1]] = 1
edge = []
while nextV:
    tempEdge = []
    for i in nextV:
        check = True
        for j in cango(i[0],i[1]):
            if visited[j[0]][j[1]] == 0:
                visited[j[0]][j[1]] = 1
                tempEdge.append(j)
                check = False
        if check :
            edge.append(i)

    nextV = tempEdge

while True:
    if visited[swan[1][0]][swan[1][1]] == 1:
        print(days)
        break
    tempMelt = []
    for i in melt:
        lake[i[0]][i[1]] = '.'
        for j in meltnext(i[0],i[1]):
            if nextmelt[j[0]][j[1]] == 0:
                tempMelt.append(j)
                nextmelt[j[0]][j[1]] = 1
    melt = tempMelt
    days += 1

    nextEdge = []
    while edge:
        temp = []
        for i in edge:
            check = True
            for j in cango(i[0],i[1]):
                if visited[j[0]][j[1]] == 0:
                    temp.append(j)
                    visited[j[0]][j[1]] = 1
                    check = False
            nextEdge.append(i)
        edge = temp
    edge = nextEdge