import sys
n = int(sys.stdin.readline())
bodies = [list(map(int,sys.stdin.readline().split())) for i in range(n)]
for i in range(n):
    count = 0
    for j in range(n):
        if j != i:
            if bodies[i][0] < bodies[j][0] and bodies[i][1] < bodies[j][1]:
                count += 1
    print(count+1)