import sys
tc = int(sys.stdin.readline())
def isin(x,y,r,cx,cy):
    d = ((x-cx)**2 + (y-cy)**2)**(1/2)
    if d < r:
        return True
    else:
        return False
for _ in range(tc):
    sx,sy,ex,ey = map(int,sys.stdin.readline().split())
    n = int(sys.stdin.readline())
    result = 0
    for __ in range(n):
        x,y,r = map(int,sys.stdin.readline().split())
        if isin(x,y,r,sx,sy) != isin(x,y,r,ex,ey):
            result +=1
    print(result)
        