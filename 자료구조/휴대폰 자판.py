import sys


class Node():
    def __init__(self, data):
        self.data = data
        self.child = {}
        self.count = 1
        self.end = False


class Trie():
    def __init__(self):
        self.head = Node(None)

    def insert(self, v):
        cur = self.head
        for i in v:
            if i not in cur.child:
                cur.child[i] = Node(i)
            cur = cur.child[i]
        cur.end = True


while True:
    try:
        n = int(sys.stdin.readline())
    except:
        break
    root = Trie()
    for _ in range(n):
        temp = sys.stdin.readline().strip()
        root.insert(temp)
    result = 0
    nextV = []
    for i in root.head.child.keys():
        nextV.append(root.head.child[i])
    while nextV:
        temp = []
        for i in nextV:
            if i.end:
                result += i.count
            if len(i.child) == 1 and i.end == False:
                for j in i.child.keys():
                    i.child[j].count = i.count
                    temp.append(i.child[j])
            else:
                for j in i.child.keys():
                    i.child[j].count = i.count + 1
                    temp.append(i.child[j])
        nextV = temp
    answer = result / n
    print('%0.2f' % answer)