import sys
from typing import *
from collections import defaultdict

input = sys.stdin.readline

if __name__ == "__main__":
    N, M = map(int, input().split())
    countMap = defaultdict(lambda : 0)
    for _ in range(N):
        word : str = input().rstrip()
        if len(word) < M:
            continue
        countMap[word] += 1
    wordList : List[Tuple[int, int, str]] = []
    for word, count in countMap.items():
        wordList.append((count, len(word), word))
    wordList.sort(key = lambda x : (-x[0], -x[1], x[2]))
    for count, length, word in wordList:
        print(word)
