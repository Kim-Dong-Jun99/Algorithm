import sys
from typing import *

input = sys.stdin.readline

if __name__ == "__main__":
    N, X = map(int, input().split())
    visitors : List[int] = list(map(int, input().split()))
    if max(visitors) == 0:
        print("SAD")
        sys.exit(0)
    left = 0
    right = X-1
    cur = 0
    count = 1
    for i in range(left, right+1):
        cur += visitors[i]
    answer = max(0, cur)

    while (right < N - 1):
        cur -= visitors[left]
        left += 1
        right += 1
        cur += visitors[right]
        if answer == cur:
            count += 1
        elif answer < cur:
            answer = cur
            count = 1
    print(answer)
    print(count)


