class node():
    def __init__(self, data):
        self.data = data
        self.leftChild = None
        self.rightChild = None
        self.parent = None


class Tree():
    def __init__(self):
        self.head = node('A')
        self.storage = {'A': self.head}

    def insert(self, pdata, data, dir):
        newNode = node(data)
        self.storage[data] = newNode
        parent = self.storage[pdata]
        if dir == 0:
            parent.left_child = newNode
        else:
            parent.right_child = newNode
        newNode.parent = parent


def preorder(node):
    print(node.data, end='')
    if node.left_child:
        preorder(node.left_child)
    if node.right_child:
        preorder(node.right_child)


def inorder(node):
    if node.left_child:
        inorder(node.left_child)
    print(node.data, end='')
    if node.right_child:
        inorder(node.right_child)


def postorder(node):
    if node.left_child:
        postorder(node.left_child)
    if node.right_child:
        postorder(node.right_child)
    print(node.data, end='')


import sys

n = int(sys.stdin.readline())
query = [list(sys.stdin.readline().split()) for i in range(n)]
tree = Tree()
for i in query:
    if i[1].isalpha():
        tree.insert(i[0], i[1], 0)
    if i[2].isalpha():
        tree.insert(i[0], i[2], 1)
preorder(tree.head)
print()
inorder(tree.head)
print()
postorder(tree.head)
print()

