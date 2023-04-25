import sys
import math
testcase = int(sys.stdin.readline())
for _ in range(testcase):
    M, N, x, y = map(int, sys.stdin.readline().split())
    
    notSolved = True
    
    max_range = math.lcm(M,N)
    if M < N:
        index = y
        if x == M:
            x = 0
        while index <= max_range:
            if index % M == x:
                print(index)
                notSolved = False
                break
            index += N
    else:
        index = x
        if y == N:
            y = 0
        while index <= max_range:
            if index % N == y:
                print(index)
                notSolved = False
                break
            index += M
    
    if notSolved:
        print(-1)