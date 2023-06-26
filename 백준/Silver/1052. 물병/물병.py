import sys
N, K = map(int, sys.stdin.readline().split())

cnt = 0

while bin(N).count('1') > K:
    idx = bin(N)[::-1].index('1')
    cnt += 2**idx
    N += 2**idx

print(cnt)