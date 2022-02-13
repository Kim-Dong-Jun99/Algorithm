import sys
import math

n = int(sys.stdin.readline())

up = list(map(int, sys.stdin.readline().split()))
bottom = list(map(int, sys.stdin.readline().split()))

h = math.log2(n)
if int(h) == math.ceil(h):
    nodenum = 2 * n - 1
else:
    nodenum = 2 ** (math.ceil(h) + 1) - 1

table = {}
for i in range(n):
    table[bottom[i]] = i

tree = [0 for _ in range(nodenum)]
result = 0


def update(node, start, end, index):
    if index < start or end < index:
        return

    if start == end:
        tree[node] = 1
        return tree[node]
    mid = (start + end) // 2
    update(node * 2 + 1, start, mid, index)
    update(node * 2 + 2, mid + 1, end, index)
    tree[node] = tree[node * 2 + 1] + tree[node * 2 + 2]
    return tree[node]


def subsum(node, start, end, left, right):
    if right < start or left > end:
        return 0

    if left <= start and end <= right:
        return tree[node]

    mid = (start + end) // 2
    return subsum(node * 2 + 1, start, mid, left, right) + subsum(node * 2 + 2, mid + 1, end, left, right)


for i in up:
    index = table[i]
    result += subsum(0, 0, n - 1, index, n - 1)
    update(0, 0, n - 1, index)

print(result)