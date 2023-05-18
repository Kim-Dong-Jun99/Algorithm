import sys

t = int(sys.stdin.readline())

for _ in range(t):
    H, W, N = map(int, sys.stdin.readline().split())
    X = N // H + 1
    if N % H == 0:
        X -= 1
    Y = N % H
    if Y == 0:
        Y = H
    result = str(Y)
    if len(str(X)) == 1:
        result += "0" + str(X)
    else:
        result += str(X)
    print(result)