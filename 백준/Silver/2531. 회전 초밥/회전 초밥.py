import sys
from collections import defaultdict
from typing import *

input = sys.stdin.readline
print = sys.stdout.write

def nextIndex(index : int) -> int:
    return (index + 1) % N

def countSushi() -> int:
    count : int = 0
    for k, v in sushiSet.items():
        if v != 0:
            count += 1
    return count

def initSushiSet():
    global answer
    for i in range(left, right + 1):
        sushiSet[sushi[i]] += 1
    count = countSushi()
    if sushiSet[c] == 0:
        answer = max(answer, count + 1)
    else:
        answer = max(answer, count)


if __name__ == '__main__':
    N, d, k, c = map(int, input().split())
    sushi : List[int] = [int(input()) for _ in range(N)]
    sushiSet = defaultdict(lambda : 0)
    left : int = 0
    right : int = k-1
    answer : int = 0
    initSushiSet()
    while left < N:
        sushiSet[sushi[left]] -= 1
        left += 1
        right = nextIndex(right)
        sushiSet[sushi[right]] += 1
        count = countSushi()
        if sushiSet[c] == 0:
            answer = max(answer, count + 1)
        else:
            answer = max(answer, count)
    print("%d"%answer)



