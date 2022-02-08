import sys
import math
sys.setrecursionlimit(10**6)
n = int(sys.stdin.readline())

ns = list(map(int, sys.stdin.readline().split()))
h = math.log2(n)
if int(h) == math.ceil(h):
    nodenum = 2*n-1
else:
    nodenum = 2**(math.ceil(h) + 1) - 1

tree = [0 for _ in range(nodenum)]


def init(node, start, end):
    if start == end:
        tree[node] = start
        return tree[node]
    else:
        mid = (start+end)//2
        temp = [init(node*2+1, start, mid), init(node*2+2, mid+1, end)]
        # tree[node] = max(temp)
        if ns[temp[0]] > ns[temp[1]]:
            tree[node] = temp[0]
        else:
            tree[node] = temp[1]
        return tree[node]


init(0,0,n-1)


def getmaximumindex(node, start, end, left, right):
    if start > right or end < left:
        return-1
    if start == end:
        return tree[node]

    mid = (start+end)//2
    temp1 = getmaximumindex(node*2+1, start, mid, left, right)
    temp2 = getmaximumindex(node*2+2, mid+1, end, left, right)
    if temp1 == -1:
        return temp2
    elif temp2 == -1:
        return temp1
    else:
        if ns[temp1] > ns[temp2]:
            return temp1
        else:
            return temp2


count = 0
# for i in range(n-1,-1,-1):
#     tmi = getmaximumindex(0,0,n-1,i)
#     count += i - tmi
#     ns[i],ns[tmi] = ns[tmi],ns[i]
def calc(left, right):
    if left < right:
        tmi = getmaximumindex(0, 0, n-1, left, right)
        global count
        count += right - tmi
        calc(left,tmi-1)
        calc(tmi+1,right)

calc(0,n-1)
print(count)
# print(tree)