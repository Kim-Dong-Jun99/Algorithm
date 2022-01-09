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
            parent.leftChild = newNode
        else:
            parent.rightChild = newNode
        newNode.parent = parent


def preorder(node):
    print(node.data, end='')
    if node.leftChild:
        preorder(node.leftChild)
    if node.rightChild:
        preorder(node.rightChild)


def inorder(node):
    if node.leftChild:
        inorder(node.leftChild)
    print(node.data, end='')
    if node.rightChild:
        inorder(node.rightChild)


def postorder(node):
    if node.leftChild:
        postorder(node.leftChild)
    if node.rightChild:
        postorder(node.rightChild)
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

