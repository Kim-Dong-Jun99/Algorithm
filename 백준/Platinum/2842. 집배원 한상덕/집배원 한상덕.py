import sys

n = int(sys.stdin.readline())
vilage = [sys.stdin.readline().strip() for _ in range(n)]
alt = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
maxalt = 0
cango = [[0, 1], [0, -1], [1, 0], [-1, 0], [1, 1], [-1, 1], [-1, -1], [1, -1]]
homes = 0
cur = None
ps = set()

for i in range(n):
    for j in range(n):
        if alt[i][j] > maxalt:
            maxalt = alt[i][j]
        ps.add(alt[i][j])
        if vilage[i][j] == 'P':
            cur = [i, j]
            # ps.append(alt[i][j])
        elif vilage[i][j] == 'K':
            # ps.append(alt[i][j])
            homes += 1
nps = sorted(ps)
left = 0
right = 0
result = sys.maxsize
# print(cur)
# print(homes)
# print(left)
# print(right)
while left < len(nps):
    nextV = [cur]
    tempresult = 0
    temp = []
    visited = [[0 for _ in range(n)] for _ in range(n)]
    visited[cur[0]][cur[1]] = 1
    if nps[left] <= alt[cur[0]][cur[1]] <= nps[right]:
        while nextV:
            temp = []
            for i in nextV:
                for j in cango:
                    r = i[0]+j[0]
                    c = i[1]+j[1]
                    if -1 < r < n and -1 < c < n:
                        if nps[left] <= alt[r][c] <= nps[right] and visited[r][c] == 0:
                            temp.append([r, c])
                            visited[r][c] = 1
                            if vilage[r][c] == 'K':
                                tempresult += 1
            nextV = temp
    if tempresult == homes:
        result = min(result, nps[right] - nps[left])
        left += 1
    elif right + 1< len(nps):
        right += 1
    else:
        break

print(result)
