import sys
import math
sys.setrecursionlimit(10**6)
def init(node,start,end):
    # global tempmax
    if start == end:
        tree[node] = start
        # if tree[node] * (end-start+1) > tempmax:
        #     tempmax = tree[node]*(end-start+1)
        return tree[node]
    else:
        temp = [init(node*2+1,start,(start+end)//2), init(node*2+2,(start+end)//2+1,end)]
        # tree[node] = min(temp)
        # if tree[node] * (end-start+1) > tempmax:
        #     tempmax = tree[node]*(end-start+1)


        if rc[temp[0]] < rc[temp[1]]:
            tree[node] = temp[0]
        else:
            tree[node] = temp[1]
        return tree[node]

def getmaxsize(start,end):
    index = getminindex(0,1,n,start,end)
    if abs(start - end) == 0:
        return rc[index]
    v1,v2,v3 = rc[index] * (end-start+1),0,0
    if index-1 >= start:
        v2 = getmaxsize(start,index-1)
    if index+1 <= end:
        v3 = getmaxsize(index+1,end)
    return max(v1,v2,v3)



def getminindex(node,start,end,x,y):
    if x > end or y < start:
        return -1
    if start >= x and end <= y:
        return tree[node]

    left = getminindex(node*2+1,start,(start+end)//2,x,y)
    right = getminindex(node*2+2,(start+end)//2+1,end,x,y)
    if left == -1:
        return right
    elif right == -1:
        return left
    else:
        if rc[left] >= rc[right]:
            return right
        else:
            return left
while True:
    rc = list(map(int,sys.stdin.readline().split()))
    if rc[0] == 0:
        break
    n = rc[0]
    h = math.log2(n)
    if int(h) == math.ceil(h):
        nodenum = 2*int(n)-1
    else:
        nodenum = 2**(math.ceil(h)+1) - 1
    tree = [0 for _ in range(nodenum)]

    init(0,1,n)
    print(getmaxsize(1,n))