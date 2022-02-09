import sys
sys.setrecursionlimit(10**9)
n = int(sys.stdin.readline())

inorder = list(map(int, sys.stdin.readline().split()))
postorder = list(map(int, sys.stdin.readline().split()))


# head = postorder[-1]
class Node():
    def __init__(self, data):
        self.data = data
        self.lc = None
        self.rc = None


def getindex(left, right, v):
    for i in range(left, right+1):
        if inorder[i] == v:
            return i


def getfulltree(start,end, left, right):
    if left == right:
        # print('-1-1-1-1-1')
        return Node(postorder[end])
    else:
        head = Node(postorder[end])
        # print('head %d'%postorder[end])
        # print('%d %d'%(start, end))
        # print('%d %d'%(left,right))
        headindex = getindex(left, right, postorder[end])
        # print(headindex)
        # head.lc = getfulltree(start, left + headindex-1, left, left + headindex)
        # head.rc = getfulltree(start, right-1, headindex+1, right-1)
        ln = headindex - left
        rn = right - headindex
        if start <= start+ln-1:
            head.lc = getfulltree(start, start+ln-1, left, headindex-1)
        if end - rn <= end-1:
            head.rc = getfulltree(end-rn, end-1, headindex+1, right)

        return head


root = getfulltree(0, n-1, 0, n-1)


def preorder(root):
    print(root.data,end = ' ')
    if root.lc != None:
        preorder(root.lc)
    if root.rc != None:
        preorder(root.rc)

preorder(root)