import sys
import math

n = int(sys.stdin.readline())
# ns = [0]*(10**6+1)
nodenum = 2**(math.ceil(math.log2(10**6)) + 1)-1
tree = [0] * nodenum

def update(node, start, end, index, diff):
    if start > index or end < index :
        return
    tree[node] += diff
    if start != end:
        mid = (start+end) // 2
        update(node*2+1, start, mid, index, diff)
        update(node*2+2, mid + 1, end, index, diff)


def subsum(node, start, end, left, right):
    if start > right or end < left:
        return 0

    if left <= start and end <= right:
        return tree[node]
    mid = (start+end) // 2
    return subsum(node*2+1, start, mid, left, right) + subsum(node*2+2, mid + 1, end, left, right)


def bst(v):
    left = 1
    right = 10**6

    while left < right:
        mid = (left + right) // 2
        temp = subsum(0, 0, 10**6, 0, mid)
        if temp >= v:
            right = mid
        else:
            left = mid + 1

    return right


for _ in range(n):
    q = list(map(int, sys.stdin.readline().split()))
    if q[0] == 1:
        index = bst(q[1])
        print(index)
        update(0, 0, 10**6, index, -1)
    else:
        update(0, 0, 10**6, q[1], q[2])