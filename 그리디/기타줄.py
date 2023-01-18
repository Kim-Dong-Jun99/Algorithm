import sys


def smaller(i, j):
    if i < j:
        return i
    return j


N, M = map(int, sys.stdin.readline().split())

lines = [list(map(int, sys.stdin.readline().split())) for _ in range(M)]
min_pack = 1000
min_single = 1000
for i, j in lines:
    if i < min_pack:
        min_pack = i
    if j < min_single:
        min_single = j

print(smaller((N // 6)*min_pack +  smaller((N % 6)*min_single, min_pack), N * min_single))
