import sys
q = [int(sys.stdin.readline()) for i in range(2)]
if q[0] > 0 :
    if q[1] > 0:
        print(1)
    else:
        print(4)
else:
    if q[1] > 0:
        print(2)
    else:
        print(3)