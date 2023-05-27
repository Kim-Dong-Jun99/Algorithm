import sys

N = int(sys.stdin.readline())

depth = 1
while True:
    if depth == 1:
        N -= 1
    else:
        N -= depth * 6 - 6 
    
    if N <= 0:
        sys.stdout.write("%d\n"%depth)
        break
    depth += 1