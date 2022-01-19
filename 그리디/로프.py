import sys
n = int(sys.stdin.readline())
ropes = [int(sys.stdin.readline()) for i in range(n)]
ropes.sort()
maxV = 0
for i in range(n):
    tempV = ropes[i]*(n-i)
    if tempV > maxV:
        maxV = tempV
print(maxV)