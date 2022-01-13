import sys
testcase = int(sys.stdin.readline())
for i in range(testcase):
    x1,y1,r1,x2,y2,r2 = map(int,sys.stdin.readline().split())
    if x1 == x2 and y1 == y2 and r1 == r2:
        print(-1)
    else:
        d = ((x1-x2)**2 + (y1-y2)**2)**(1/2)
        if d > r1+r2:
            print(0)
        elif d == r1+r2:
            print(1)
        elif d < r1+r2:
            if r2 > r1:
                if r2 == d + r1:
                    print(1)
                elif r2 > d + r1:
                    print(0)
                else:
                    print(2)
            else:
                if r1 == d + r2:
                    print(1)
                elif r1 > d + r2:
                    print(0)
                else:
                    print(2)