import sys
import math

n = int(sys.stdin.readline())

players = [int(sys.stdin.readline()) for _ in range(n)]

h = math.log2(n)
if int(h) == math.ceil(h):
    nodenum = 2*n-1
else:
    nodenum = 2**(math.ceil(h)+1)-1

compressed = sorted(players)
table = {}
for i in range(n):
    table[compressed[i]] = i

tree = [0]*nodenum

ns = [0]*n


def update(node, start, end, index):
    if index < start or index > end:
        return
    tree[node] += 1
    if start != end:
        mid = (start+end)//2
        update(node*2+1, start, mid, index)
        update(node*2+2, mid+1, end, index)


def subsum(node, start, end, left, right):
    if end < left or start > right:
        return 0

    if left <= start and end <= right:
        return tree[node]
    mid = (start+end)//2
    return subsum(node*2+1, start, mid, left, right) + subsum(node*2+2, mid+1, end, left, right)


for i in range(n):
    update(0, 0, n-1, table[players[i]])
    result = i+2 - subsum(0, 0, n-1, 0, table[players[i]])
    print(result, end = ' ')