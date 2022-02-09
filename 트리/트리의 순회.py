import sys

sys.setrecursionlimit(10 ** 9)
n = int(sys.stdin.readline())

inorder = list(map(int, sys.stdin.readline().split()))
postorder = list(map(int, sys.stdin.readline().split()))


# head = postorder[-1]

def getindex(left, right, v):
    for i in range(left, right + 1):
        if inorder[i] == v:
            return i


result = []


def getfulltree(start, end, left, right):
    if left == right:
        result.append(postorder[end])
        # print(postorder[end])

    else:
        result.append(postorder[end])
        # print(postorder[end])

        headindex = getindex(left, right, postorder[end])

        ln = headindex - left
        rn = right - headindex
        if start <= start + ln - 1:
            getfulltree(start, start + ln - 1, left, headindex - 1)
        if end - rn <= end - 1:
            getfulltree(end - rn, end - 1, headindex + 1, right)


getfulltree(0, n - 1, 0, n - 1)
print(*result)


