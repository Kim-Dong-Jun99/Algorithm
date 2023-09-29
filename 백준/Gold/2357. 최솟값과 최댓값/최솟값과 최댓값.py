import sys
import math

n, m = map(int,sys.stdin.readline().split())
h = math.log2(n)
if int(h) == math.ceil(h):
    nodenum = 2 * int(h) - 1
else:
    nodenum = 2 ** (math.ceil(h) + 1) - 1

tree = [[] for _ in range(nodenum)]
vs = [int(sys.stdin.readline()) for _ in range(n)]


def init(node, start, end):
    if start == end:
        tree[node].append(vs[start])
        return tree[node]
    else:
        temp = init(node * 2+1, start, (start + end) // 2) + init(node * 2 + 2, (start + end) // 2 + 1, end)
        tree[node].append(min(temp))
        tree[node].append(max(temp))
        return tree[node]


def getMinMax(node, start, end, left, right):
    if left > end or right < start:
        return 0
    if left <= start and end <= right:

        global tempmax
        global tempmin
        if tree[node][-1] > tempmax:
            tempmax = tree[node][-1]
        if tree[node][0] < tempmin:
            tempmin = tree[node][0]
        return 0
    getMinMax(node*2+1,start,(start+end)//2,left,right)
    getMinMax(node*2+2,(start+end)//2+1,end,left,right)


init(0,0,n-1)
# print(tree)
for _ in range(m):
    left, right = map(int, sys.stdin.readline().split())
    tempmax = 0
    tempmin = sys.maxsize
    getMinMax(0,0,n-1,left-1,right-1)
    print('%d %d'%(tempmin,tempmax))



