import sys

n = int(sys.stdin.readline())
ns = [int(sys.stdin.readline()) for _ in range(n)]
# print(ns)


class Node:
    def __init__(self, leftChild, rightChild, value):
        self.leftChild = leftChild
        self.rightChild = rightChild
        self.value = value


def getMiddle(left, right):
    return int(sum([left, right]) / 2)


def createSegmentTree(left, right):
    if left == right:
        node = Node(None, None, ns[left])
        return node
    middle = getMiddle(left, right)
    leftChild = createSegmentTree(left, middle)
    rightChild = createSegmentTree(middle + 1, right)
    node = Node(leftChild, rightChild, min([leftChild.value, rightChild.value]))
    return node


root = createSegmentTree(0, n - 1)


def getMaxRect(node, left, right):
    curMax = node.value * (right - left + 1)
    if left == right:
        return curMax
    middle = getMiddle(left, right)
    if node.leftChild != None:




# def search_max_length(value, targetLeft, targetRight, left, right, node):
#     if right < targetLeft:
#         return 0
#     if left == right:
#         if value <= node.value:
#             return 1
#         else:
#             return 0
#     middle = getMiddle(left, right)
#     totalLength = 0
#     rightValue = 0
#     if node.leftChild != None:
#         totalLength = search_max_length(value, targetLeft, targetRight, left, middle, node.leftChild)
#     if node.rightChild != None:
#         rightValue += search_max_length(value, targetLeft, targetRight, middle + 1, right, node.rightChild)
#     if left < targetLeft or totalLength == middle - left + 1:
#         totalLength += rightValue
#
#     return totalLength
#
#
# maxSize = n * root.value
# for i in range(n):
#     tempSize = ns[i] * search_max_length(ns[i], i, n - 1, 0, n - 1, root)
#     # print(tempSize)
#     if tempSize > maxSize:
#         maxSize = tempSize
#
# print(maxSize)
