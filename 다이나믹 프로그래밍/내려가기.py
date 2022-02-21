import sys

n = int(sys.stdin.readline())
#ns = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

# maxdp = [[0] * 3 for _ in range(2)]
# mindp = [[0] * 3 for _ in range(2)]
ns = list(map(int, sys.stdin.readline().split()))
mindp = ns
maxdp = ns
for i in range(1, n):
    ns = list(map(int, sys.stdin.readline().split()))
    tempmax = [0]*3
    tempmin = [0]*3
    tempmax[0] = max(maxdp[0], maxdp[1]) + ns[0]
    tempmax[1] = max(maxdp) + ns[1]
    tempmax[2] = max(maxdp[1], maxdp[2]) + ns[2]
    tempmin[0] = min(mindp[0], mindp[1]) + ns[0]
    tempmin[1] = min(mindp) + ns[1]
    tempmin[2] = min(mindp[1], mindp[2]) + ns[2]
    maxdp = tempmax
    mindp = tempmin
print("%d %d"%(max(maxdp), min(mindp)))

