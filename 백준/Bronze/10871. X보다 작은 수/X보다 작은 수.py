import sys

N, X = map(int, sys.stdin.readline().split())
ns = list(map(int, sys.stdin.readline().split()))

for i in ns:
    if i < X:
        sys.stdout.write("%d "%i)
        