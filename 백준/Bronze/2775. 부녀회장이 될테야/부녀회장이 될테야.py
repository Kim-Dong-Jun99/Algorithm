import sys

TC = int(sys.stdin.readline())
apart = [[i for i in range(15)] for _ in range(15)]

for i in range(1, 15):
    for j in range(1, 15):
        apart[i][j] = apart[i][j-1] + apart[i-1][j]
for ____ in range(TC):
    k = int(sys.stdin.readline())
    n = int(sys.stdin.readline())
    sys.stdout.write("%d\n"%apart[k][n])
    
    