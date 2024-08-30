import sys
import math
from typing import *

input = sys.stdin.readline

if __name__ == "__main__":
    N : int = int(input())
    M : int = int(input())
    lights : List[int] = list(map(int, input().split()))
    answer : int = 0
    for i, light in enumerate(lights):
        if i != M - 1:
            answer = max(answer, math.ceil((lights[i+1]-lights[i])/2))
    answer = max(answer, lights[0], N - lights[-1])
    print(answer)
