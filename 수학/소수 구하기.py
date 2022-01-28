import sys
n,m = map(int,sys.stdin.readline().split())

for i in range(n,m+1):
    if i > 1:
        check = True
        for j in range(2, int(i**(1/2))+1):
            if i % j == 0:
                check = False
                break
        if check:
            print(i)

