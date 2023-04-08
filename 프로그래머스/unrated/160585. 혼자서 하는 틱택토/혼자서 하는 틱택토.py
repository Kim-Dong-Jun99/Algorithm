def solution(board):
    """
    22:27
    """
    o_count = 0
    x_count = 0
    for i in range(3):
        for j in range(3):
            if board[i][j] == "O":
                o_count += 1
            elif board[i][j] == "X":
                x_count += 1
    if o_count > x_count+1:
        return 0
    elif x_count > o_count:
        return 0
    else:
        if check_done_o(board) and o_count == x_count:
            return 0
        if check_done_x(board) and o_count > x_count:
            return 0
        return 1

def check_done_o(board):
    if board[0] == 'OOO' or board[1] == 'OOO' or board[2] == 'OOO':
        return True
    for i in range(3):
        if board[0][i] == board[1][i] and board[1][i] == board[2][i] and board[0][i] == 'O':
            return True
    if board[0][0] == board[1][1] and board[1][1] == board[2][2] and board[0][0] == 'O':
        return True
    if board[2][0] == board[1][1] and board[1][1] == board[0][2] and board[2][0] == 'O':
        return True
    return False

def check_done_x(board):
    if board[0] == 'XXX' or board[1] == 'XXX' or board[2] == 'XXX':
        return True
    for i in range(3):
        if board[0][i] == board[1][i] and board[1][i] == board[2][i] and board[0][i] == 'X':
            return True
    if board[0][0] == board[1][1] and board[1][1] == board[2][2] and board[0][0] == 'X':
        return True
    if board[2][0] == board[1][1] and board[1][1] == board[0][2] and board[2][0] == 'X':
        return True
    return False