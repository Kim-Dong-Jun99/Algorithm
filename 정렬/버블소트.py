import sys
n = int(sys.stdin.readline())

ns = list(map(int,sys.stdin.readline().split()))
count = 0
for i in range(n-1,0,-1):
    for j in range(i):
        if ns[j] > ns[j+1]:
            ns[j],ns[j+1] = ns[j+1],ns[j]
            count += 1
print(count)