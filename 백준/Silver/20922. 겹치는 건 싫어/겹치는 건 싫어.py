import sys
from typing import *
from collections import defaultdict

input = sys.stdin.readline
print = sys.stdout.write

def canMoveRight() -> bool:
    return right + 1 < n and count[numbers[right+1]] + 1 <= k

def moveLeft():
    global left
    while numbers[left] != target:
        count[numbers[left]] -= 1
        left += 1
    count[numbers[left]] -= 1
    left += 1


if __name__ == "__main__":
    n, k = map(int, input().split())
    numbers : List[int] = list(map(int, input().split()))
    count = defaultdict(lambda : 0)
    answer : int = 0
    left : int = 0
    right : int = 0
    count[numbers[0]] += 1
    while True:
        while canMoveRight():
            right += 1
            count[numbers[right]] += 1
        answer = max(answer, right - left + 1)
        if right == n-1:
            break
        target : int = numbers[right+1]
        moveLeft()
    print("%d\n"%answer)
