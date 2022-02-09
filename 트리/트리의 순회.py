import sys

sys.setrecursionlimit(10 ** 9)
n = int(sys.stdin.readline())

inorder = list(map(int, sys.stdin.readline().split()))
postorder = list(map(int, sys.stdin.readline().split()))

table = {}
for i in range(n):
    table[inorder[i]] = i


# head = postorder[-1]


# result = []
def getfulltree(start, end, left, right):
    if left == right:
        # result.append(postorder[end])
        print(postorder[end])

    else:
        # result.append(postorder[end])
        print(postorder[end])

        headindex = table[postorder[end]]

        ln = headindex - left
        rn = right - headindex
        if start <= start + ln - 1:
            getfulltree(start, start + ln - 1, left, headindex - 1)
        if end - rn <= end - 1:
            getfulltree(end - rn, end - 1, headindex + 1, right)


getfulltree(0, n - 1, 0, n - 1)





