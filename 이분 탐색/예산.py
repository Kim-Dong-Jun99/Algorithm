import sys
n = int(sys.stdin.readline())
ns = list(map(int,sys.stdin.readline().split()))
m = int(sys.stdin.readline())

mi,ma = 0,max(ns)
while mi <= ma:
    mid = (mi+ma)//2
    num = 0
    for i in ns:
        if i >= mid:
            num += mid
        else:
            num += i
    if num <= m:
        mi = mid + 1
    else:
        ma = mid - 1
print(ma)