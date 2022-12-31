import sys
from collections import defaultdict
N, P, Q = map(int, sys.stdin.readline().split())
data = defaultdict(int)
data[0] = 1
def calc(n):
    if data[n] != 0:
        return data[n]
    data[n] = calc(n // P) + calc(n // Q)
    return data[n]


print(calc(N))
