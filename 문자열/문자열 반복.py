import sys
testcase = int(sys.stdin.readline())
n = [list(sys.stdin.readline().split()) for i in range(testcase)]
for i in range(testcase):
    r = int(n[i][0])
    for j in n[i][1]:
        for k in range(r):
            print(j,end = '')
    print()