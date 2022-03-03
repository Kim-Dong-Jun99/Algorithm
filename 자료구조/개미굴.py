import sys


class Node():
    def __init__(self, data):
        self.data = data
        self.child = {}


class Trie():
    def __init__(self):
        self.head = Node(None)

    def insert(self, v):
        cur = self.head
        for i in range(1, len(v)):
            if (v[i] in cur.child):
                cur = cur.child[v[i]]
            else:
                cur.child[v[i]] = Node(v[i])
                cur = cur.child[v[i]]


def printT(cur, v):
    for i in sorted(list(cur.child.keys())):
        for j in range(v):
            print('--',end = '')
        print(i)
        printT(cur.child[i],v+1)


root = Trie()

n = int(sys.stdin.readline())
for _ in range(n):
    v = list(sys.stdin.readline().split())
    root.insert(v)

printT(root.head, 0)



