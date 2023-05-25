import sys
import math
n = int(sys.stdin.readline())

papers = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

result = [0,0]

def recur(n,l,t):
    if n == 1:
        result[papers[l][t]] += 1
    else:
        done = False
        for i in range(n):
            for j in range(n):
                if papers[l][t] != papers[l+i][t+j]:
                    done = True
                    break
            if done:
                break
        if done:
            recur(n // 2,l,t)
            recur(n // 2,l,t+n//2)
            recur(n // 2,l+n//2,t)
            recur(n // 2,l+n//2,t+n//2)
        else:
            result[papers[l][t]] += 1
recur(n, 0, 0)    
print(result[0])
print(result[1])