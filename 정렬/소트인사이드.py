import sys
n = list(sys.stdin.readline().strip())
n.sort()
for i in range(len(n)-1,-1,-1):
    print(n[i],end ='')

