import sys

n, m, k = map(int, sys.stdin.readline().split())

ns = [int(sys.stdin.readline()) for _ in range(n)]
stree = [0] * 3000000


def init(node, start, end):
    if start == end:
        stree[node] = ns[start]
        return stree[node]
    else:
        stree[node] = init(node * 2, start, (start + end) // 2) + init(node * 2 + 1, (start + end) // 2 + 1, end)
        return stree[node]


def subsum(node, start, end, left, right):
    if left > end or right < start:
        return 0
    if left <= start and end <= right:
        return stree[node]

    return subsum(node * 2, start, (start + end) // 2, left, right) + subsum(node * 2 + 1, (start + end) // 2 + 1, end,
                                                                             left, right)


def update(node, start, end, index, diff):
    if index < start or index > end:
        return
    stree[node] += diff

    if start != end:
        update(node * 2, start, (start + end) // 2, index, diff)
        update(node * 2 + 1, (start + end) // 2+1, end, index, diff)


init(1, 0, n - 1)
for _ in range(m + k):
    x, y, z = map(int, sys.stdin.readline().split())
    if x == 1:
        y -= 1
        diff = z - ns[y]
        ns[y] = z
        update(1, 0, n - 1, y, diff)
    elif x == 2:
        print(subsum(1, 0, n - 1, y - 1, z - 1))