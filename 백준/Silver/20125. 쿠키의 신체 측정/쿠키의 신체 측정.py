import sys
from typing import *

input = sys.stdin.readline
global N

def findHeart(board : List[str]) -> Tuple[int, int]: 
    global N
    for i in range(N):
        for j in range(N):
            if board[i][j] == "*":
                return (i, j)
    return (-1, -1)
    
def findLeftArm(x : int, y : int, board : List[str]):
    global N
    length = 0
    while (y - 1 >= 0 and board[x][y-1] == '*'):
        length += 1
        y -= 1
    print(length, end = ' ')
    
def findRightArm(x : int, y : int, board : List[str]):
    global N
    length = 0
    while (y + 1 < N and board[x][y+1] == '*'):
        length += 1
        y += 1
    print(length, end = ' ')


def findWaist(x : int, y : int, board : List[str]) -> Tuple[int, int]:
    global N
    length = 0
    while (x + 1 < N and board[x+1][y] == '*'):
        length += 1
        x += 1
    print(length, end=' ')
    return (x, y)
    

def findLeftLeg(x : int, y : int, board : List[str]):
    global N
    length = 1
    while (x + 1 < N and board[x+1][y] == '*'):
        length += 1
        x += 1
    print(length, end=' ')


def findRightLeg(x : int, y : int, board : List[str]):
    global N
    length = 1
    while (x + 1 < N and board[x+1][y] == '*'):
        length += 1
        x += 1
    print(length, end=' ')


if __name__ == "__main__":
    global N
    N : int = int(input())
    board : List[str] = [input() for _ in range(N)]
    head : Tuple[int, int] = findHeart(board)
    print(head[0]+2, head[1]+1)
    findLeftArm(head[0]+1, head[1], board)
    findRightArm(head[0]+1, head[1], board)
    waist : Tuple[int, int] = findWaist(head[0]+1, head[1], board)
    findLeftLeg(waist[0]+1, waist[1]-1, board)
    findRightLeg(waist[0]+1, waist[1]+1, board)
    