import sys
N = int(sys.stdin.readline())
board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

result = 0

def left(board):
    global N
    global result
    temp_board = [[0] * N for _ in range(N)]
    modified = [[0] * N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            temp_board[i][j] = board[i][j]
    diff = False
    for i in range(N):
        for j in range(1, N):
            if temp_board[i][j] != 0:
                for k in range(j,0,-1):
                    if temp_board[i][k-1] == 0:
                        diff = True
                        temp_board[i][k-1] = temp_board[i][k]
                        temp_board[i][k] = 0
                    elif temp_board[i][k-1] == temp_board[i][k] and modified[i][k-1] == 0:
                        diff = True
                        temp_board[i][k-1] += temp_board[i][k]
                        modified[i][k-1] = 1
                        temp_board[i][k] = 0
                        if result < temp_board[i][k-1]:
                            result = temp_board[i][k-1]
                        break
                    else:
                        break
    if diff:
        return temp_board
    else:
        return []

def right(board):
    global N
    global result
    temp_board = [[0] * N for _ in range(N)]
    modified = [[0] * N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            temp_board[i][j] = board[i][j]
    diff = False
    for i in range(N):
        for j in range(N-2,-1,-1):
            if temp_board[i][j] != 0:
                for k in range(j, N-1):
                    if temp_board[i][k+1] == 0:
                        diff = True
                        temp_board[i][k+1] = temp_board[i][k]
                        temp_board[i][k] = 0
                    elif temp_board[i][k+1] == temp_board[i][k] and modified[i][k+1] == 0:
                        diff = True
                        temp_board[i][k+1] += temp_board[i][k]
                        modified[i][k+1] = 1
                        temp_board[i][k] = 0
                        if result < temp_board[i][k+1]:
                            result = temp_board[i][k+1]
                        break
                    else:
                        break
    if diff:
        return temp_board
    else:
        return []
    
def up(board):
    global N
    global result
    temp_board = [[0] * N for _ in range(N)]
    modified = [[0] * N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            temp_board[i][j] = board[i][j]
    diff = False
    for i in range(N):
        for j in range(1,N):
            if temp_board[j][i] != 0:
                for k in range(j, 0, -1):
                    if temp_board[k-1][i] == 0:
                        diff = True
                        temp_board[k-1][i] = temp_board[k][i]
                        temp_board[k][i] = 0
                    elif temp_board[k-1][i] == temp_board[k][i] and modified[k-1][i] == 0:
                        diff = True
                        temp_board[k-1][i] += temp_board[k][i]
                        modified[k-1][i] = 1
                        temp_board[k][i] = 0
                        if result < temp_board[k-1][i]:
                            result = temp_board[k-1][i]
                        break
                    else:
                        break
    if diff:
        return temp_board
    else:
        return []

def down(board):
    global N
    global result
    temp_board = [[0] * N for _ in range(N)]
    modified = [[0] * N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            temp_board[i][j] = board[i][j]
    diff = False
    for i in range(N):
        for j in range(N-2,-1,-1):
            if temp_board[j][i] != 0:
                for k in range(j, N-1):
                    if temp_board[k+1][i] == 0:
                        diff = True
                        temp_board[k+1][i] = temp_board[k][i]
                        temp_board[k][i] = 0
                    elif temp_board[k+1][i] == temp_board[k][i] and modified[k+1][i] == 0:
                        diff = True
                        temp_board[k+1][i] += temp_board[k][i]
                        modified[k+1][i] = 1
                        temp_board[k][i] = 0
                        if result < temp_board[k+1][i]:
                            result = temp_board[k+1][i]
                        break
                    else:
                        break
    if diff:
        return temp_board
    else:
        return []

def backtrack(index, board):
    if index == 5:
        return
    else:
        left_ = left(board)
        if len(left_) > 0:
            backtrack(index+1, left_)
        right_ = right(board)
        if len(right_) > 0:
            backtrack(index+1, right_)
        up_ = up(board)
        if len(up_) > 0:
            backtrack(index+1, up_)
        down_ = down(board)
        if len(down_) > 0:
            backtrack(index+1, down_)
    
for i in range(N):
    for j in range(N):
        if result < board[i][j]:
            result = board[i][j]
backtrack(0, board)
print(result)