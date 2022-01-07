n = int(input())
ns = list(map(int,input().split()))
m = int(input())
ms = list(map(int,input().split()))

ns.sort()

def bs(ns,i,start,end):
    while start <= end:
        mid = (start+end) // 2
        if ns[mid] == i:
            return True
        elif ns[mid] > i:
            end = mid-1
        else:
            start = mid+1
    return False
for i in ms:
    if bs(ns,i,0,n-1):
        print(1)
    else:
        print(0)