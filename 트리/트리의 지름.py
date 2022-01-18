import sys

n = int(sys.stdin.readline())


class node():
    def __init__(self, data):
        self.data = data
        self.child = {}
        self.edgevalue = {}
        self.parent = None
        self.maxValue = 0


class Tree():
    def __init__(self, n):
        self.head = node(1)
        self.table = {1: self.head}
        self.visited = [0 for i in range(n + 1)]

    def insert(self, parent, childdata, weight):
        parentnode = self.table[parent]
        childnode = node(childdata)
        childnode.parent = parentnode
        parentnode.child[childdata] = childnode
        parentnode.edgevalue[childdata] = weight
        self.table[childdata] = childnode

    def dfs(self):
        maxV = -1
        cur = self.table[1]
        self.visited[1] = 1
        stack = []
        while True:
            f = False
            for i in list(cur.child.keys()):
                if self.visited[i] == 0:
                    stack.append(cur)
                    self.visited[i] = 1
                    cur = cur.child[i]
                    f = True
                    break
            if f == False:
                tempMax = 0
                secondMax = 0
                for i in list(cur.child.keys()):
                    child = cur.child[i]
                    tempS = cur.edgevalue[i] + child.maxValue
                    if tempS >= tempMax:
                        secondMax = tempMax
                        tempMax = tempS
                    elif tempS > secondMax:
                        secondMax = tempS
                if tempMax + secondMax > maxV:
                    maxV = tempMax + secondMax
                cur.maxValue = tempMax
                if stack == []:
                    break
                cur = stack.pop()
        return maxV


tree = Tree(n)
for i in range(n - 1):
    query = list(map(int, sys.stdin.readline().split()))
    tree.insert(query[0], query[1], query[2])
print(tree.dfs())





