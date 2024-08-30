import sys
from typing import *

input = sys.stdin.readline

def findSymmetric(index : int) -> Tuple[int, int]:
    left = index
    right = index
    while (0 <= left-1 and right+1 < N and switches[left-1] == switches[right+1]):
        left -= 1
        right += 1
    return (left, right)

def switch():
    for gender, num in students:
        if gender == 1:
            for i in range(N):
                if (i+1) % num == 0:
                    switches[i] ^= 1
        else:
            left, right = findSymmetric(num-1)
            for i in range(left, right + 1):
                switches[i] ^= 1

def printResult():
    for i, s in enumerate(switches):
        print(s, end=' ')
        if i % 20 == 19:
            print()

if __name__ == '__main__':
    N : int = int(input())
    switches : List[int] = list(map(int, input().split()))
    M : int = int(input())
    students : List[List[int]] = [list(map(int, input().split())) for _ in range(M)]
    switch()
    printResult()
