import sys

n,s = map(int,sys.stdin.readline().split())
ns = list(map(int,sys.stdin.readline().split()))

l,r = 0,1
minL = sys.maxsize
sums = [0 for _ in range(n+1)]

for i in range(1,n+1):
    sums[i] += sums[i-1]+ns[i-1]
while l != n:
    if sums[r] - sums[l] >= s:
        if r-l < minL:
            minL = r - l
        l += 1
    else:
        if r != n:
            r += 1
        else:
            l += 1
if minL != sys.maxsize:
    print(minL)
else:
    print(0)