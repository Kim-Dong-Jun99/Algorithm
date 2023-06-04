import sys
R, C, T = map(int, sys.stdin.readline().split())
room = []
ac = []
rotate_u = []
rotate_b = []
for i in range(R):
    row = list(map(int, sys.stdin.readline().split()))
    if row[0] == -1:
        ac.append(i)
    room.append(row)
    
for i in range(ac[0]-1, -1, -1):
    rotate_u.append([i,0])
for i in range(1,C):
    rotate_u.append([0,i])
for i in range(1, ac[0]+1):
    rotate_u.append([i,C-1])
for i in range(C-2, 0, -1):
    rotate_u.append([ac[0],i])
for i in range(ac[1]+1,R):
    rotate_b.append([i,0])
for i in range(1, C):
    rotate_b.append([R-1, i])
for i in range(R-1, ac[1]-1, -1):
    rotate_b.append([i, C-1])
for i in range(C-2,0,-1):
    rotate_b.append([ac[1], i])

# print(*rotate_u)
# print(*rotate_b)
    

def can_spread(i, j):
    result = []
    global R
    global C
    if i - 1 >= 0 and room[i-1][j] != -1:
        result.append([i-1, j])
    if i + 1 < R and room[i+1][j] != -1:
        result.append([i+1, j])
    if j - 1 >= 0 and room[i][j-1] != -1:
        result.append([i, j-1])
    if j + 1 < C and room[i][j+1] != -1:
        result.append([i, j+1])
    return result

def rotate():
    for i in range(len(rotate_u)-1):
        cx, cy = rotate_u[i]
        nx, ny = rotate_u[i+1]
        room[cx][cy] = room[nx][ny]
    for i in range(len(rotate_b)-1):
        cx, cy = rotate_b[i]
        nx, ny = rotate_b[i+1]
        room[cx][cy] = room[nx][ny]
    room[rotate_u[-1][0]][rotate_u[-1][1]] = 0
    room[rotate_b[-1][0]][rotate_b[-1][1]] = 0
    

for ____ in range(T):
    next_room = [[0 for _ in range(C)] for _ in range(R)]
    for i in range(R):
        for j in range(C):
            if room[i][j] >= 5:
                spreadable = can_spread(i, j)
                for k, l in spreadable:
                    next_room[k][l] += room[i][j] // 5
                next_room[i][j] += room[i][j] - (len(spreadable) * (room[i][j] // 5))
            else:
                next_room[i][j] += room[i][j]
    room = next_room
    rotate()
    # print()
    # for i in room:
    #     print(*i)
    
result = 0
for i in room:
    result += sum(i)
result += 2
sys.stdout.write("%d\n"%result)
