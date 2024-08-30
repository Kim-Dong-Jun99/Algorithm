import sys
from typing import *


input = sys.stdin.readline

def getRank() -> Tuple[int, int]:
    for i, r in enumerate(ranks):
        if r ==  T:
            index = i
            while (index < N and ranks[index] == T):
                index += 1
            return (i+1, index+1)
        elif r < T:
            return (i+1, i+1)
    return (len(ranks)+1, len(ranks)+1)


if __name__ == "__main__":
    N, T, P = map(int, input().split())
    if N ==0:
        print(1)
        sys.exit(0)
    ranks : list = list(map(int, input().split()))
    rank, post = getRank()
    if post <= P:
        print(rank)
    else :
        print(-1)
