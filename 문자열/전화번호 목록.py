import sys

tc = int(sys.stdin.readline())

class Node():
    def __init__(self, data):
        self.data = data
        self.key = False
        self.child = {}


class Trie():
    def __init__(self):
        self.head = Node(None)

    def insert(self, key):
        cur = self.head
        for i in key:
            try:
                childnode = cur.child[i]
                cur = childnode
            except:
                childnode = Node(i)
                cur.child[i] = childnode
                cur = childnode
            if cur.key:
                return False
        cur.key = True
        return True


for _ in range(tc):
    n = int(sys.stdin.readline())
    trie = Trie()
    check = True
    keys = [sys.stdin.readline().strip() for i in range(n)]
    keys.sort()
    for i in keys:
        if trie.insert(i) == False:
            check = False
            break
    if check:
        print('YES')
    else:
        print('NO')