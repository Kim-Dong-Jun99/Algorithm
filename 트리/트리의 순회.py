import sys

sys.setrecursionlimit(10 ** 9)
n = int(sys.stdin.readline())

inorder = list(map(int, sys.stdin.readline().split()))
postorder = list(map(int, sys.stdin.readline().split()))

table = {}
for i in range(n):
    table[inorder[i]] = i


def get_full_tree(start, end, left, right):
    if left == right:
        print(postorder[end])

    else:
        print(postorder[end])

        head_index = table[postorder[end]]

        ln = head_index - left
        rn = right - head_index
        if start <= start + ln - 1:
            get_full_tree(start, start + ln - 1, left, head_index - 1)
        if end - rn <= end - 1:
            get_full_tree(end - rn, end - 1, head_index + 1, right)


get_full_tree(0, n - 1, 0, n - 1)
