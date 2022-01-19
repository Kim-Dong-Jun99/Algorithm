import sys
result = 0
for i in range(8):
    board = sys.stdin.readline().rstrip()
    if i % 2 == 0:
        for j in range(8):
            if board[j] == 'F' and j % 2 == 0:
                result += 1
    else:
        for j in range(8):
            if board[j] == 'F' and j % 2 == 1:
                result += 1
print(result)