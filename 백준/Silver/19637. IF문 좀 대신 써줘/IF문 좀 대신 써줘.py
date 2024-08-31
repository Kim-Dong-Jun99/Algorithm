import sys
from bisect import bisect_left, bisect_right
from typing import *

input = sys.stdin.readline
print = sys.stdout.write
if __name__ == "__main__":
    n, m = map(int, input().split())
    levels : List[int] = []
    levelDict = {}
    for _ in range(n):
        name, l = input().split()
        levels.append(int(l))
        if int(l) not in levelDict:
            levelDict[int(l)] = name
    for _ in range(m):
        level = int(input())
        lIndex = bisect_left(levels, level)
        rIndex = bisect_right(levels, level)
        print(levelDict[levels[lIndex]])
        print("\n")
