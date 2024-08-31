import sys
from typing import *

input = sys.stdin.readline

dx : List[int] = [1, 1, 1]
dy : List[int] = [-1, 0, 1]

def isInner(x : int, y : int) -> bool:
    return 0 <= x < N and 0 <= y < M

if __name__ == '__main__':
    N, M = map(int, input().split())
    board : List[List[int]] = [list(map(int, input().split())) for _ in range(N)]

    cur : List[Tuple[int, int, int, int]] = [(0, i, -1, 0) for i in range(M)]
    answer : int = sys.maxsize
    while (cur):
        temp : List[Tuple[int, int, int, int]] = []
        for x, y, pastDirection, distance in cur:
            distance += board[x][y]
            if (x == N-1):
                answer = min(answer, distance)
            for d in range(3):
                if d == pastDirection:
                    continue
                nx : int = x + dx[d]
                ny : int = y + dy[d]
                if isInner(nx, ny):
                    temp.append((nx, ny, d, distance))
        cur = temp
    print(answer)

