import sys
from typing import *
from collections import deque

input = sys.stdin.readline
print = sys.stdout.write

def isSame(head : int, tail : int) -> bool:
    if head <= tail:
        for i, index in enumerate(range(head, tail+1)):
            if A[i] != B[index]:
                return False
    else:
        for i, index in enumerate(range(head, tail-1, -1)):
            if A[i] != B[index]:
                return False
    return True
def canPopA(head : int, tail : int) -> bool:
    return B[tail] == 'A'

def canPopB(head : int, tail : int) -> bool:
    return B[head] == 'B'

if __name__ == '__main__':
    A : str = input().rstrip()
    B : str = input().rstrip()
    visited : List[List[bool]] = [[False for _ in range(len(B))] for _ in range(len(B))]
    current = deque([(0, len(B)-1)])
    visited[0][len(B)-1] = True
    while current:
        head, tail = current.popleft()
        if abs(head - tail) + 1 == len(A):
            if isSame(head, tail):
                print("1\n")
                sys.exit(0)
            else:
                continue
        if canPopA(head, tail):
            if head <= tail:
                if not visited[head][tail-1]:
                    visited[head][tail-1] = True
                    current.append((head, tail-1))
            else:
                if not visited[head][tail+1]:
                    visited[head][tail+1] = True
                    current.append((head, tail+1))
        if canPopB(head, tail):
            if head <= tail:
                if not visited[tail][head+1]:
                    visited[tail][head+1] = True
                    current.append((tail, head+1))
            else:
                if not visited[tail][head-1]:
                    visited[tail][head-1] = True
                    current.append((tail, head-1))
    print("0\n")

