import sys
n = int(sys.stdin.readline())
ns = list(map(int,sys.stdin.readline().split()))
m = int(sys.stdin.readline())
ms = list(map(int,sys.stdin.readline().split()))
values = {}
for i in ns:
    try:
        values[i] = values[i]+1
    except:
        values[i] = 1
for i in ms:
    try:
        print(values[i],end = ' ')
    except:
        print(0,end = ' ')
print()