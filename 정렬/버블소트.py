import sys
def div(left,right):
    if left == right:
        return
    mid = (left+right) // 2
    div(left,mid)
    div(mid+1,right)
    swap(left,mid,mid+1,right)
    return

def swap(ll,lr,rl,rr):
    global count
    temp = []
    sindex = ll
    si = ll
    li = rr
    while ll <= lr and rl <= rr:
        if ns[ll] > ns[rl]:
            temp.append(ns[rl])
            count += (rl-sindex)
            rl += 1
            sindex += 1
        else:
            temp.append(ns[ll])
            ll += 1
            sindex += 1
    while ll <= lr:
        temp.append(ns[ll])
        ll += 1
    while rl <= rr:
        temp.append(ns[rl])
        rl += 1
    for i in range(li,si-1,-1):
        ns[i] = temp.pop()

n = int(sys.stdin.readline())
ns = list(map(int,sys.stdin.readline().split()))
count = 0
div(0,n-1)
print(count)