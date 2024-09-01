import sys
from typing import *

input = sys.stdin.readline
print = sys.stdout.write

if __name__ == '__main__':
    n, m = map(int, input().split())
    memo : Set[str] = {input().rstrip() for _ in range(n)}
    for _ in range(m):
        keywords : List[str] = list(input().split(','))
        for kw in keywords:
            memo.discard(kw.rstrip())
        print(str(len(memo)))
        print("\n")
