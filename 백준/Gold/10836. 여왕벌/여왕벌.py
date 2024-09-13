from sys import stdin

input = stdin.readline
m,n = map(int, input().split())

def solv():
    worm = [1]*(2*m-1)
    for _ in range(n):
        a,b,c = map(int, input().split())
        for idx in range(a,a+b):
            worm[idx] += 1
        for idx in range(a+b,2*m-1):
            worm[idx] += 2

    for idx in range(m-1,-1,-1):
        print(*([worm[idx]]+worm[m:]))
solv()